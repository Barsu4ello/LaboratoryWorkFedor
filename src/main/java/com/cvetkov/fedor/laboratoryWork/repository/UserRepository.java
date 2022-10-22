package com.cvetkov.fedor.laboratoryWork.repository;

import com.cvetkov.fedor.laboratoryWork.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
