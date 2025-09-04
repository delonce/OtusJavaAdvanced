package api;

import login.LoginRequest;
import login.LoginService;
import login.email.EmailLoginService;
import login.qr.QrLoginService;

import static spark.Spark.*;

public class ApiController {

    public static void main(String[] args) {
        port(8080);

        // GET /api/email/login?admin=john&password=123456
        get("/api/email/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");

            LoginService loginService = new EmailLoginService();

            return loginService.doLogin(
                    new LoginRequest(username, password)
            ).toString();
        });

        // GET /api/qr/login?username=tester&password=qwerty
        get("/api/qr/login", (req, res) -> {
            String username = req.queryParams("username");
            String password = req.queryParams("password");

            LoginService loginService = new QrLoginService();

            return loginService.doLogin(
                    new LoginRequest(username, password)
            ).toString();
        });
    }
}