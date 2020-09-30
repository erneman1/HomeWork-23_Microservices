package com.cursor.hippolita.rating_service.repository;

import com.cursor.hippolita.rating_service.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {
    Rate findById(long id);
}
