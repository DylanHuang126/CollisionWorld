package edu.rice.comp504.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.rice.comp504.adapter.DispatchAdapter;
import edu.rice.comp504.model.BallWorldStore;

import static spark.Spark.*;


/**
 * The paint world controller creates the adapter(s) that communicate with the view.
 * The controller responds to requests from the view after contacting the adapter(s).
 */
public class BallWorldController {

    /**
     * The main entry point into the program.
     * @param args  The program arguments normally specified on the cmd line
     */
    public static void main(String[] args) {
        staticFiles.location("/public");
        port(getHerokuAssignedPort());
        Gson gson = new Gson();
        BallWorldStore bws = new BallWorldStore();
        DispatchAdapter dis = new DispatchAdapter(bws);

        post("/load/:type/:behavior/:interaction/:canSwitchStrat", (request, response) -> {
            String type = request.params(":type");
            String behavior = request.params(":behavior");
            String interaction = request.params(":interaction");
            Boolean canSwitchStrat = Boolean.parseBoolean(request.params(":canSwitchStrat"));
            return gson.toJson(dis.loadPaintObj(behavior, interaction, canSwitchStrat, type));
        });

        post("/switch", (request, response) -> {
            String strat = request.body().replace("strategies=", "");
            return gson.toJson(dis.switchStrategy(strat));
        });

        get("/update", (request, response) -> {
            return gson.toJson(dis.updateBallWorld());
        });

        post("/canvas/dims", (request, response) -> {
            String dims;
            if (request.body() == null) {
                dims = "height=800&width=800";
            } else {
                dims = request.body();
            }
            dis.setCanvasDims(dims);
            return gson.toJson("canvas dimensions are set");
        });

        get("/remove/:id", (request, response) -> {
            String strat = request.params(":id");
            return gson.toJson(dis.removeSome(strat));
        });

        get("/clear", (request, response) -> {
            dis.removeAll();
            return gson.toJson("removed all balls in paint world");
        });

        redirect.get("/ballworld", "/");

        notFound((request, response) -> {
            response.redirect("/");
            return gson.toJson(dis.updateBallWorld());
        });

    }

    /**
     * Get the heroku assigned port number.
     * @return The port number
     */
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
}
