package com.cvetkov.fedor.laboratoryWork.repository;

import com.cvetkov.fedor.laboratoryWork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findUSersByCityId(Long id);

    List<User> findUSersByAuthorId(Long id);

    Optional<User> findByUsername(String userName);

    boolean existsByUsername(String username);
}
