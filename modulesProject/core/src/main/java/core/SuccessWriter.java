package core;

public class SuccessWriter {
    public void writeSuccessLoginLog(String username, String method) {
        System.out.printf("User with name '%s' has entered at %s by %s!\n", username, java.time.Instant.now(), method);
    }
}
