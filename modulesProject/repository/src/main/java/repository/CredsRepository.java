package repository;

import core.SuccessWriter;

import java.util.Objects;

public class CredsRepository {
    SuccessWriter writer = new SuccessWriter();

    public String getState(String username, String password) {
        if (Objects.equals(username, "admin") && Objects.equals(password, "123456")) {
            return "success";
        } else if (Objects.equals(username, "tester") && Objects.equals(password, "qwerty")) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void writeSuccessLogin(String username, String method) {
        writer.writeSuccessLoginLog(username, method);
    }
}
