package org.delonce.signIn.security;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Md5EncoderBenchmarks extends BaseEncoderBenchmarks {
    @Override
    public PasswordEncoder buildPasswordEncoder() {
        return new Md5Encoder();
    }
}
