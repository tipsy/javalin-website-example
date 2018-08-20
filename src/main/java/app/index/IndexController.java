package app.index;

import java.util.Map;

import app.util.Path;
import app.util.ViewUtil;
import io.javalin.Handler;

import static app.Main.*;

public class IndexController {
    public static Handler serveIndexPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("users", userDao.getAllUserNames());
        model.put("book", bookDao.getRandomBook());
        ctx.render(Path.Template.INDEX, model);
    };
}
