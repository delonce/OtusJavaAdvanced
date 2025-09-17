package org.delonce.signIn.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha512EncoderBenchmarks extends BaseEncoderBenchmarks {
    @Override
    public PasswordEncoder buildPasswordEncoder() {
        return new Sha512Encoder();
    }
}
