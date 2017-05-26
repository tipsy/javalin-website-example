package app.book;

import java.util.Map;

import app.util.Path;
import app.util.ViewUtil;
import javalin.Handler;
import javalin.Request;
import javalin.Response;

import static app.Main.*;
import static app.util.RequestUtil.*;

public class BookController {

    public static Handler fetchAllBooks = (Request req, Response res) -> {
        Map<String, Object> model = ViewUtil.baseModel(req);
        model.put("books", bookDao.getAllBooks());
        res.renderVelocity(Path.Template.BOOKS_ALL, model);
    };

    public static Handler fetchOneBook = (Request req, Response res) -> {
        Map<String, Object> model = ViewUtil.baseModel(req);
        model.put("book", bookDao.getBookByIsbn(getParamIsbn(req)));
        res.renderVelocity(Path.Template.BOOKS_ONE, model);
    };
}
