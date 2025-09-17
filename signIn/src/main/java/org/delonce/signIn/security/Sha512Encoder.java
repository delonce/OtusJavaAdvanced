package org.delonce.signIn.security;

import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public class Sha512Encoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return Sha512DigestUtils.shaHex(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String rawEncoded = encode(rawPassword);
        return Objects.equals(rawEncoded, encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
}
