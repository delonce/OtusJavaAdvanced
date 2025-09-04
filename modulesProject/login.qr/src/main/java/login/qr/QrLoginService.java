package login.qr;

import login.LoginRequest;
import login.LoginResponse;
import login.LoginService;
import repository.CredsRepository;

import java.util.Objects;

public class QrLoginService implements LoginService {
    CredsRepository repository = new CredsRepository();
    @Override
    public LoginResponse doLogin(LoginRequest request) {
        String state = repository.getState(request.getUsername(), request.getPassword());

        if (Objects.equals(state, "success")) {
            repository.writeSuccessLogin(request.getUsername(), "qr");
        }

        return new LoginResponse(
                request.getUsername(),
                state
        );
    }
}
