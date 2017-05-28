package app.util;

import io.javalin.Handler;
import io.javalin.Request;
import io.javalin.Response;

import static app.util.RequestUtil.*;

public class Filters {

    // If a user manually manipulates paths and forgets to add
    // a trailing slash, redirect the user to the correct path
    public static Handler stripTrailingSlashes = (Request request, Response response) -> {
        if (!request.path().equals("/") && request.path().endsWith("/")) {
            response.redirect(request.path().substring(0, request.path().length() - 1));
        }
    };

    // Locale change can be initiated from any page
    // The locale is extracted from the request and saved to the user's session
    public static Handler handleLocaleChange = (Request request, Response response) -> {
        if (getQueryLocale(request) != null) {
            request.unwrap().getSession().setAttribute("locale", getQueryLocale(request));
            response.redirect(request.path());
        }
    };

}
