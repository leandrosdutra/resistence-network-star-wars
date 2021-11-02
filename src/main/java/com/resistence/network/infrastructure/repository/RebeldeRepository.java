package com.resistence.network.infrastructure.repository;

import com.resistence.network.domain.Rebelde;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RebeldeRepository extends JpaRepository<Rebelde, Long> {
}
