package app.util;

import io.javalin.Context;

public class RequestUtil {

    public static String getQueryLocale(Context ctx) {
        return ctx.queryParam("locale");
    }

    public static String getParamIsbn(Context ctx) {
        return ctx.param("isbn");
    }

    public static String getQueryUsername(Context ctx) {
        return ctx.formParam("username");
    }

    public static String getQueryPassword(Context ctx) {
        return ctx.formParam("password");
    }

    public static String getQueryLoginRedirect(Context ctx) {
        return ctx.queryParam("loginRedirect");
    }

    public static String getSessionLocale(Context ctx) {
        return (String) ctx.request().getSession().getAttribute("locale");
    }

    public static String getSessionCurrentUser(Context ctx) {
        return (String) ctx.request().getSession().getAttribute("currentUser");
    }

    public static boolean removeSessionAttrLoggedOut(Context ctx) {
        String loggedOut = (String) ctx.request().getSession().getAttribute("loggedOut");
        ctx.request().getSession().removeAttribute("loggedOut");
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Context ctx) {
        String loginRedirect = (String) ctx.request().getSession().getAttribute("loginRedirect");
        ctx.request().getSession().removeAttribute("loginRedirect");
        return loginRedirect;
    }

}
