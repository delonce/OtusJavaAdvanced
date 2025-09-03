package org.delonce.signIn.dto;


import lombok.Getter;
import lombok.Setter;
import org.delonce.signIn.model.User;

@Getter
@Setter
public class SignInResponse {
    private String login;
    private String error;

    public SignInResponse(User user) {
        this.login = user.getLogin();
    }

    public SignInResponse(User user, String error) {
        this.login = user.getLogin();
        this.error = error;
    }
}
