package app.util;

import java.util.HashMap;
import java.util.Map;

import io.javalin.ErrorHandler;
import io.javalin.Request;
import io.javalin.Response;

import static app.util.RequestUtil.*;

public class ViewUtil {

    public static Map<String, Object> baseModel(Request req) {
        Map<String, Object> model = new HashMap<>();
        model.put("msg", new MessageBundle(getSessionLocale(req)));
        model.put("currentUser", getSessionCurrentUser(req));
        return model;
    }

    public static ErrorHandler notFound = (Request req, Response res) -> {
        res.renderVelocity(Path.Template.NOT_FOUND, baseModel(req));
    };

}
