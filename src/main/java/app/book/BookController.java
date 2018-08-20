package app.book;

import java.util.Map;

import app.util.Path;
import app.util.ViewUtil;
import io.javalin.Handler;

import static app.Main.*;
import static app.util.RequestUtil.*;

public class BookController {

    public static Handler fetchAllBooks = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("books", bookDao.getAllBooks());
        ctx.render(Path.Template.BOOKS_ALL, model);
    };

    public static Handler fetchOneBook = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("book", bookDao.getBookByIsbn(getParamIsbn(ctx)));
        ctx.render(Path.Template.BOOKS_ONE, model);
    };
}
