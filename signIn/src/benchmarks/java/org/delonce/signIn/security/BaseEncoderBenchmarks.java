package org.delonce.signIn.security;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.springframework.security.crypto.password.PasswordEncoder;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.All)
@Fork(value = 1)
@Warmup(iterations = 2)
@Measurement(iterations = 2)
public abstract class BaseEncoderBenchmarks {
    private PasswordEncoder passwordEncoder;

    @Setup(Level.Trial)
    public void setUp() {
        passwordEncoder = buildPasswordEncoder();
    }

    @Benchmark
    public void testPasswordEncoder(Blackhole bh) {
        bh.consume(passwordEncoder.encode("MySuperSecretPasswordForBenches"));
    }

    public abstract PasswordEncoder buildPasswordEncoder();
}
