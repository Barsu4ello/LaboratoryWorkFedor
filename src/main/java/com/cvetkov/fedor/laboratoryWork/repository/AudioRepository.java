package com.cvetkov.fedor.laboratoryWork.repository;

import com.cvetkov.fedor.laboratoryWork.model.Audio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends JpaRepository<Audio, Long> {
}
