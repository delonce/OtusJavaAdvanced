package login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {
    private String username;
    private String status;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "username='" + username + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
