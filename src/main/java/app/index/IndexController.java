package app.index;

import java.util.Map;

import app.util.Path;
import app.util.ViewUtil;
import javalin.Handler;
import javalin.Request;
import javalin.Response;

import static app.Main.*;

public class IndexController {
    public static Handler serveIndexPage = (Request req, Response res) -> {
        Map<String, Object> model = ViewUtil.baseModel(req);
        model.put("users", userDao.getAllUserNames());
        model.put("book", bookDao.getRandomBook());
        res.renderVelocity(Path.Template.INDEX, model);
    };
}
