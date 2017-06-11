package app.util;

import io.javalin.Handler;

import static app.util.RequestUtil.*;

public class Filters {

    // If a user manually manipulates paths and forgets to add
    // a trailing slash, redirect the user to the correct path
    public static Handler stripTrailingSlashes = ctx -> {
        if (!ctx.path().equals("/") && ctx.path().endsWith("/")) {
            ctx.redirect(ctx.path().substring(0, ctx.path().length() - 1));
        }
    };

    // Locale change can be initiated from any page
    // The locale is extracted from the request and saved to the user's session
    public static Handler handleLocaleChange = ctx -> {
        if (getQueryLocale(ctx) != null) {
            ctx.request().getSession().setAttribute("locale", getQueryLocale(ctx));
            ctx.redirect(ctx.path());
        }
    };

}
