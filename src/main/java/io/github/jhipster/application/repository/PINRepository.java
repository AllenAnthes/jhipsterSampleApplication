package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.PIN;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the PIN entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PINRepository extends JpaRepository<PIN, Long> {

}
