package org.delonce.signIn.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha256EncoderBenchmarks extends BaseEncoderBenchmarks {
    @Override
    public PasswordEncoder buildPasswordEncoder() {
        return new Sha256Encoder();
    }
}
