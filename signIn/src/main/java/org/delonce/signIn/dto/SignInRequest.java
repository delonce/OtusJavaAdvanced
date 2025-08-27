package org.delonce.signIn.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.delonce.signIn.model.User;

@AllArgsConstructor
public class SignInRequest {
    @NotBlank
    public String login;

    @NotBlank
    @Size(min = 8, message = "Size of password should be greater then 8")
    public String password;

    public User createInstance() {
        User user = new User();

        user.setLogin(login);
        user.setPassword(password);

        return user;
    }
}
