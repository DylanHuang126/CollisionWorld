package edu.rice.comp504.model;

import edu.rice.comp504.model.paintobj.*;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class BallWorldStoreTest {
    BallWorldStore bws = new BallWorldStore();

    @Test
    public void updateBallWorld() {

        BallWorldStore.setCanvasDims("height=800&width=800");
        APaintObject obj;

        // test content: fish properly change direction when hitting a wall
        obj = bws.loadPaintObj("straight", "null", false, "fish");
        Fish fish = (Fish) obj;
        fish.setRadius(30);
        fish.setVelocity(new Point(10, 0));
        fish.setLocation(new Point(770, 200));
        assertEquals("fish direction before", 0, fish.getDirection());
        bws.updateBallWorld();
        assertEquals("fish direction after", 1, fish.getDirection());


        // test content: each interaction ball behavior affects the other ball involved in the collision as expected
        Point loc1 = new Point(400, 400);
        Point loc2 = new Point(400, 410);

        APaintObject staticBall;
        Ball sBall;

        staticBall = bws.loadPaintObj("static", "null", false, "ball");
        sBall = (Ball) staticBall;
        sBall.setLocation(loc1);
        sBall.setRadius(20);

//        case "cloaking":
//        return CloakingStrategy.make();
//        case "stop":
//        return StopStrategy.make();
//        case "change color":
//        return ChangeColorStrategy.make();
//        case "zoom in":
//        return ZoomInStrategy.make();
//        case "zoom out":
//        return ZoomOutStrategy.make();
//        case "concentric":
//        return ConcentricStrategy.make();
//        default:
//        return InterNullStrategy.make();

        Ball ball;

        // change color
        obj = bws.loadPaintObj("straight", "change color", true, "ball");
        ball = (Ball) obj;
        ball.setLocation(loc2);
        ball.setRadius(20);
        ball.setVelocity(new Point(-10,-10));
        String oColor = ball.getColor();
        bws.updateBallWorld();
        String nColor = ball.getColor();
        assertNotEquals("color", oColor, nColor);

        bws.removeListeners();

        // clocking
        staticBall = bws.loadPaintObj("static", "null", false, "ball");
        sBall = (Ball) staticBall;
        sBall.setLocation(loc1);
        sBall.setRadius(20);

        obj = bws.loadPaintObj("straight", "cloaking", true, "ball");
        ball = (Ball) obj;
        ball.setLocation(loc2);
        ball.setVelocity(new Point(-10,-10));
        boolean oldC = ball.isCloaking();
        assertEquals("initial cloaking value", false, oldC);
        bws.updateBallWorld();
        boolean newC = ball.isCloaking();
        assertNotEquals("cloaking value", oldC, newC);

        bws.removeListeners();

        // stop
        staticBall = bws.loadPaintObj("static", "null", false, "ball");
        sBall = (Ball) staticBall;
        sBall.setLocation(loc1);
        sBall.setRadius(20);

        obj = bws.loadPaintObj("straight", "stop", true, "ball");
        ball = (Ball) obj;
        ball.setLocation(loc2);
        ball.setVelocity(new Point(-10,-10));
        int velX = ball.getVelocity().x;
        int velY = ball.getVelocity().y;
        assertNotEquals("velocity of x", 0, velX);
        assertNotEquals("velocity of y", 0, velY);
        bws.updateBallWorld();
        Point oLoc = ball.getLocation();
        bws.updateBallWorld();
        Point nLoc = ball.getLocation();
        assertEquals("location", oLoc, nLoc);

        bws.removeListeners();

        // zoom in

        staticBall = bws.loadPaintObj("static", "null", false, "ball");
        sBall = (Ball) staticBall;
        sBall.setLocation(loc1);
        sBall.setRadius(20);

        obj = bws.loadPaintObj("straight", "zoom in", true, "ball");
        ball = (Ball) obj;
        ball.setRadius(20);
        ball.setLocation(loc2);
        ball.setVelocity(new Point(-10,-10));
        bws.updateBallWorld();
        double newRadius = ball.getRadius();
        assertEquals("radius", 24, newRadius, 0.00001);

        bws.removeListeners();

        // zoom out
        staticBall = bws.loadPaintObj("static", "null", false, "ball");
        sBall = (Ball) staticBall;
        sBall.setLocation(loc1);
        sBall.setRadius(20);

        obj = bws.loadPaintObj("straight", "zoom out", true, "ball");
        ball = (Ball) obj;
        ball.setRadius(20);
        ball.setLocation(loc2);
        ball.setVelocity(new Point(-10,-10));
        bws.updateBallWorld();
        newRadius = ball.getRadius();
        assertEquals("radius", 16, newRadius, 0.00001);
        bws.removeListeners();

        // concentric
        staticBall = bws.loadPaintObj("static", "null", false, "ball");
        sBall = (Ball) staticBall;
        sBall.setLocation(loc1);
        sBall.setRadius(20);

        APaintObject obj2 = bws.loadPaintObj("straight", "concentric", true, "ball");
        ConcentricBall cBall = (ConcentricBall) obj2;
        cBall.setRadius(20);
        cBall.setLocation(loc2);
        cBall.setVelocity(new Point(-10,-10));
        boolean oldCon = cBall.isConcentric();
        assertEquals("initial concentric value", false, oldCon);
        bws.updateBallWorld();
        boolean newCon = cBall.isConcentric();
        assertNotEquals("concentric value", oldCon, newCon);

        bws.removeListeners();
    }
}