package com.cvetkov.fedor.laboratoryWork.repository;

import com.cvetkov.fedor.laboratoryWork.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
