package app.login;

import java.util.Map;

import app.user.UserController;
import app.util.Path;
import app.util.ViewUtil;
import io.javalin.Handler;

import static app.util.RequestUtil.*;

public class LoginController {

    public static Handler serveLoginPage = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        model.put("loggedOut", removeSessionAttrLoggedOut(ctx));
        model.put("loginRedirect", removeSessionAttrLoginRedirect(ctx));
        ctx.renderVelocity(Path.Template.LOGIN, model);
    };

    public static Handler handleLoginPost = ctx -> {
        Map<String, Object> model = ViewUtil.baseModel(ctx);
        if (!UserController.authenticate(getQueryUsername(ctx), getQueryPassword(ctx))) {
            model.put("authenticationFailed", true);
            ctx.renderVelocity(Path.Template.LOGIN, model);
        } else {
            ctx.request().getSession().setAttribute("currentUser", getQueryUsername(ctx));
            model.put("authenticationSucceeded", true);
            model.put("currentUser", getQueryUsername(ctx));
            if (getQueryLoginRedirect(ctx) != null) {
                ctx.redirect(getQueryLoginRedirect(ctx));
            }
            ctx.renderVelocity(Path.Template.LOGIN, model);
        }

    };

    public static Handler handleLogoutPost = ctx -> {
        ctx.request().getSession().removeAttribute("currentUser");
        ctx.request().getSession().setAttribute("loggedOut", "true");
        ctx.redirect(Path.Web.LOGIN);
    };

    // The origin of the request (request.pathInfo()) is saved in the session so
    // the user can be redirected back after login
    public static Handler ensureLoginBeforeViewingBooks = ctx -> {
        if (!ctx.path().startsWith("/books")) {
            return;
        }
        if (ctx.request().getSession().getAttribute("currentUser") == null) {
            ctx.request().getSession().setAttribute("loginRedirect", ctx.path());
            ctx.redirect(Path.Web.LOGIN);
        }
    };

}
