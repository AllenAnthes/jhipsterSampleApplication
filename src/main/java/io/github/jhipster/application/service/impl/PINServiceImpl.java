package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.PINService;
import io.github.jhipster.application.domain.PIN;
import io.github.jhipster.application.repository.PINRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing PIN.
 */
@Service
@Transactional
public class PINServiceImpl implements PINService {

    private final Logger log = LoggerFactory.getLogger(PINServiceImpl.class);

    private final PINRepository pINRepository;

    public PINServiceImpl(PINRepository pINRepository) {
        this.pINRepository = pINRepository;
    }

    /**
     * Save a pIN.
     *
     * @param pIN the entity to save
     * @return the persisted entity
     */
    @Override
    public PIN save(PIN pIN) {
        log.debug("Request to save PIN : {}", pIN);
        return pINRepository.save(pIN);
    }

    /**
     * Get all the pINS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PIN> findAll(Pageable pageable) {
        log.debug("Request to get all PINS");
        return pINRepository.findAll(pageable);
    }

    /**
     * Get one pIN by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PIN findOne(Long id) {
        log.debug("Request to get PIN : {}", id);
        return pINRepository.findOne(id);
    }

    /**
     * Delete the pIN by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PIN : {}", id);
        pINRepository.delete(id);
    }
}
