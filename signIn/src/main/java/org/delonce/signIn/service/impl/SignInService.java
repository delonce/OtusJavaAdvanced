package org.delonce.signIn.service.impl;

import lombok.RequiredArgsConstructor;
import org.delonce.signIn.model.User;
import org.delonce.signIn.repository.UserJpaRepository;
import org.delonce.signIn.service.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SignInService implements CRUDService<User> {
    @Autowired
    private final UserJpaRepository userJpaRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) throws RuntimeException {
        if (userJpaRepository.existsByLogin(user.getLogin())) {
            throw new RuntimeException("User with login " + user.getLogin() + " already exists");
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userJpaRepository.saveAndFlush(user);
    }

    @Override
    public User read(Integer id) {
        Optional<User> optionalUser = userJpaRepository.findById(id);

        return optionalUser.orElse(null);
    }

    @Override
    public User update(User existingUser) {
        return userJpaRepository.saveAndFlush(existingUser);
    }

    @Override
    public void delete(Integer id) {
        userJpaRepository.deleteById(id);
    }
}
