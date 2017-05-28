package app.util;

import io.javalin.Request;

public class RequestUtil {

    public static String getQueryLocale(Request request) {
        return request.queryParam("locale");
    }

    public static String getParamIsbn(Request request) {
        return request.param("isbn");
    }

    public static String getQueryUsername(Request request) {
        return request.formParam("username");
    }

    public static String getQueryPassword(Request request) {
        return request.formParam("password");
    }

    public static String getQueryLoginRedirect(Request request) {
        return request.queryParam("loginRedirect");
    }

    public static String getSessionLocale(Request request) {
        return (String) request.unwrap().getSession().getAttribute("locale");
    }

    public static String getSessionCurrentUser(Request request) {
        return (String) request.unwrap().getSession().getAttribute("currentUser");
    }

    public static boolean removeSessionAttrLoggedOut(Request request) {
        String loggedOut = (String) request.unwrap().getSession().getAttribute("loggedOut");
        request.unwrap().getSession().removeAttribute("loggedOut");
        return loggedOut != null;
    }

    public static String removeSessionAttrLoginRedirect(Request request) {
        String loginRedirect = (String) request.unwrap().getSession().getAttribute("loginRedirect");
        request.unwrap().getSession().removeAttribute("loginRedirect");
        return loginRedirect;
    }

}
