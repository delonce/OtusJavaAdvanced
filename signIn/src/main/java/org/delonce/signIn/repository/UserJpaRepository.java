package org.delonce.signIn.repository;

import org.delonce.signIn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Integer> {
    boolean existsByLogin(String login);
}
