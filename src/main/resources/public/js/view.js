'use strict';

//app to draw polymorphic shapes on canvas
let app;
let intervalID = -1


/**
 * Create the ball world app for a canvas
 * @param canvas The canvas to draw balls on
 * @returns {{drawBall: drawBall, clear: clear}}
 */
function createApp(canvas) {
    let c = canvas.getContext("2d");

    /**
     * Draw a Ball
     * @param x  The x location coordinate
     * @param y  The y location coordinate
     * @param radius  The circle radius
     * @param color The Ball color
     */
    let drawBall = function(x, y, radius, color, isCloaking, isConcentric, innerShape) {
        if (isCloaking) {
            c.fillStyle = "white";
        } else {
            c.fillStyle = color;
        }
        c.beginPath();
        c.arc(x, y, radius, 0, 2 * Math.PI, false);
        c.closePath();
        c.fill();

        if (isConcentric) {
            c.fillStyle = "white";
            if (innerShape === "circle") {
                c.beginPath();
                c.arc(x, y, radius * 0.5, 0, 2 * Math.PI, false);
                c.closePath();
                c.fill();
            } else if (innerShape === "triangle") {
                drawPolygon(x, y, radius * 0.5, 3, 0);
            } else if (innerShape === "square") {
                drawPolygon(x, y, radius * 0.5, 4, 0);
            } else if (innerShape === "pentagon") {
                drawPolygon(x, y, radius * 0.5, 5, 0);
            }
        }
    };

    let drawPolygon = function(x, y, radius, sides, rotateAngle) {
        c.beginPath();
        let angle = Math.PI * 2 / sides;
        c.translate(x, y);
        c.rotate(rotateAngle);
        c.moveTo(radius, 0);
        for (let i = 1; i < sides; i++) {
            c.lineTo(radius * Math.cos(angle * i), radius * Math.sin(angle * i))
        }
        c.closePath();
        c.fill();
        c.rotate(-rotateAngle);
        c.translate(-1 * x, -1 * y);
    }

    let drawFish = function(img, x, y, radius, direction) {
        if (direction === 1) {
            c.save();
            c.scale(-1, 1);
            c.drawImage(img, -x - radius, y, radius, radius);
            c.restore();
        } else {
            c.drawImage(img, x, y, radius, radius);

        }

    }

    let clear = function() {
        c.clearRect(0,0, canvas.width, canvas.height);
    };

    return {
        drawBall,
        drawFish,
        clear,
        dims: {height: canvas.height, width: canvas.width}
    }
}

window.onload = function() {
    app = createApp(document.querySelector("canvas"));
    canvasDims();
    $("#btn-clear").click(clear);

    // add corresponding shape
    $("#btn-add").click(function() {
        let behavior = $("#ballBehaviorSelect").val();
        let interaction = $("#ballInteractSelect").val();
        let canSwitchStrat = $("#ballCanSwitchStrat").val();
        loadBall(behavior, interaction, canSwitchStrat);
    });

    $("#btn-fish-add").click(function() {
        let behavior = $("#fishBehaviorSelect").val();
        let interaction = $("#fishInteractSelect").val();
        let canSwitchStrat = $("#fishCanSwitchStrat").val();
        loadFish(behavior, interaction, canSwitchStrat);
    });

    $("#btn-switch").click(function() {
        let strat = $("#changeStrat").val();
        switchStrategy(strat);
    })

    $("#btn-remove").click(function() {
        let strat = $("#removeStrat").val();
        remove(strat);
    })

    $("#btn-fish").click(function() {
        loadFish("rotate")
    })
};

/**
 * load ball at a location on the canvas
 */
function loadBall(behavior, interaction, canSwitchStrat) {
    $.post(`/load/ball/${behavior}/${interaction}/${canSwitchStrat}`, function (data) {
        // console.log(JSON.stringify(data));
        // console.log(JSON.stringify(d));
        drawBall(data);
        if (intervalID < 0) {
            intervalID = setInterval(updateBallWorld, 100);
        }
    }, "json");
}

/**
 * load fish at a location on the canvas
 */
function loadFish(behavior, interaction, canSwitchStrat) {
    $.post(`/load/fish/${behavior}/${interaction}/${canSwitchStrat}`, function (data) {
        drawFish(data);
        if (intervalID < 0) {
            intervalID = setInterval(updateBallWorld, 100);
        }
    }, "json");
}

/**
 * Switch ball strategies
 */
function switchStrategy(values) {
    $.post("/switch", { strategies: values}, function (data) {
        app.clear();

        data.forEach(function(po) {
            po = po.listener;
            if (po.type === "ball" || po.type === "size changeable ball" || po.type === "concentric ball")
                drawBall(po);
            else if (po.type === "fish")
                drawFish(po);
        });
    }, "json");
}

function updateBallWorld() {
    $.get("/update", function(data) {
        app.clear();

        data.forEach(function(po) {
            po = po.listener;
            if (po.type === "ball" || po.type === "size changeable ball" || po.type === "concentric ball")
                drawBall(po);
            else if (po.type === "fish")
                drawFish(po);
        });

    }, "json");
}

function drawBall(data) {
    // console.log(JSON.stringify(data));
    app.drawBall(data.loc.x, data.loc.y, data.radius, data.color, data.isCloaking, data.isConcentric, data.innerShape);
    // data.forEach(d => app.drawBall(d.listener.loc.x, d.listener.loc.y, d.listener.radius, d.listener.color));
}

let fishImg = new Image();
fishImg.src = 'fish.png';

function drawFish(data) {
    // console.log(JSON.stringify(data));
    app.drawFish(fishImg, data.loc.x, data.loc.y, data.radius, data.direction);
}


/**
 * Pass along the canvas dimensions
 */
function canvasDims() {
    $.post("/canvas/dims", {height: app.dims.height, width: app.dims.width});
    clear();
}

/**
 * Remove some balls
 */
function remove(strat) {
    $.get(`/remove/${strat}`, function(data) {
        app.clear();

        data.forEach(function(po) {
            po = po.listener;
            if (po.type === "ball" || po.type === "size changeable ball" || po.type === "concentric ball") {
                drawBall(po);
            } else if (po.type === "fish") {
                drawFish(po);
            }
        });

    }, "json");
}

/**
 * Clear the canvas
 */
function clear() {
    $.get("/clear", () => {
        app.clear();
        clearInterval(intervalID);
        intervalID = -1;
    });

}