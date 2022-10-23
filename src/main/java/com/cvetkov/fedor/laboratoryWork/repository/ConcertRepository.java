package com.cvetkov.fedor.laboratoryWork.repository;

import com.cvetkov.fedor.laboratoryWork.model.Concert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, Long> {
}
