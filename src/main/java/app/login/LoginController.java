package app.login;

import java.util.Map;

import app.user.UserController;
import app.util.Path;
import app.util.ViewUtil;
import javalin.Handler;
import javalin.Request;
import javalin.Response;

import static app.util.RequestUtil.*;

public class LoginController {

    public static Handler serveLoginPage = (Request req, Response res) -> {
        Map<String, Object> model = ViewUtil.baseModel(req);
        model.put("loggedOut", removeSessionAttrLoggedOut(req));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(req));
        res.renderVelocity(Path.Template.LOGIN, model);
    };

    public static Handler handleLoginPost = (Request req, Response res) -> {
        Map<String, Object> model = ViewUtil.baseModel(req);
        if (!UserController.authenticate(getQueryUsername(req), getQueryPassword(req))) {
            model.put("authenticationFailed", true);
            res.renderVelocity(Path.Template.LOGIN, model);
        } else {
            req.unwrap().getSession().setAttribute("currentUser", getQueryUsername(req));
            model.put("authenticationSucceeded", true);
            model.put("currentUser", getQueryUsername(req));
            if (getQueryLoginRedirect(req) != null) {
                res.redirect(getQueryLoginRedirect(req));
            }
            res.renderVelocity(Path.Template.LOGIN, model);
        }

    };

    public static Handler handleLogoutPost = (Request req, Response res) -> {
        req.unwrap().getSession().removeAttribute("currentUser");
        req.unwrap().getSession().setAttribute("loggedOut", "true");
        res.redirect(Path.Web.LOGIN);
    };

    // The origin of the request (request.pathInfo()) is saved in the session so
    // the user can be redirected back after login
    public static Handler ensureLoginBeforeViewingBooks = (Request req, Response res) -> {
        if (!req.path().startsWith("/books")) {
            return;
        }
        if (req.unwrap().getSession().getAttribute("currentUser") == null) {
            req.unwrap().getSession().setAttribute("loginRedirect", req.path());
            res.redirect(Path.Web.LOGIN);
        }
    };

}
