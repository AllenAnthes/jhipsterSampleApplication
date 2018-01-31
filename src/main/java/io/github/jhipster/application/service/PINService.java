package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.PIN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing PIN.
 */
public interface PINService {

    /**
     * Save a pIN.
     *
     * @param pIN the entity to save
     * @return the persisted entity
     */
    PIN save(PIN pIN);

    /**
     * Get all the pINS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<PIN> findAll(Pageable pageable);

    /**
     * Get the "id" pIN.
     *
     * @param id the id of the entity
     * @return the entity
     */
    PIN findOne(Long id);

    /**
     * Delete the "id" pIN.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
