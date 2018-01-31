package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.PIN;
import io.github.jhipster.application.service.PINService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing PIN.
 */
@RestController
@RequestMapping("/api")
public class PINResource {

    private final Logger log = LoggerFactory.getLogger(PINResource.class);

    private static final String ENTITY_NAME = "pIN";

    private final PINService pINService;

    public PINResource(PINService pINService) {
        this.pINService = pINService;
    }

    /**
     * POST  /pins : Create a new pIN.
     *
     * @param pIN the pIN to create
     * @return the ResponseEntity with status 201 (Created) and with body the new pIN, or with status 400 (Bad Request) if the pIN has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/pins")
    @Timed
    public ResponseEntity<PIN> createPIN(@RequestBody PIN pIN) throws URISyntaxException {
        log.debug("REST request to save PIN : {}", pIN);
        if (pIN.getId() != null) {
            throw new BadRequestAlertException("A new pIN cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PIN result = pINService.save(pIN);
        return ResponseEntity.created(new URI("/api/pins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /pins : Updates an existing pIN.
     *
     * @param pIN the pIN to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated pIN,
     * or with status 400 (Bad Request) if the pIN is not valid,
     * or with status 500 (Internal Server Error) if the pIN couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/pins")
    @Timed
    public ResponseEntity<PIN> updatePIN(@RequestBody PIN pIN) throws URISyntaxException {
        log.debug("REST request to update PIN : {}", pIN);
        if (pIN.getId() == null) {
            return createPIN(pIN);
        }
        PIN result = pINService.save(pIN);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, pIN.getId().toString()))
            .body(result);
    }

    /**
     * GET  /pins : get all the pINS.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of pINS in body
     */
    @GetMapping("/pins")
    @Timed
    public ResponseEntity<List<PIN>> getAllPINS(Pageable pageable) {
        log.debug("REST request to get a page of PINS");
        Page<PIN> page = pINService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/pins");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /pins/:id : get the "id" pIN.
     *
     * @param id the id of the pIN to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the pIN, or with status 404 (Not Found)
     */
    @GetMapping("/pins/{id}")
    @Timed
    public ResponseEntity<PIN> getPIN(@PathVariable Long id) {
        log.debug("REST request to get PIN : {}", id);
        PIN pIN = pINService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(pIN));
    }

    /**
     * DELETE  /pins/:id : delete the "id" pIN.
     *
     * @param id the id of the pIN to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/pins/{id}")
    @Timed
    public ResponseEntity<Void> deletePIN(@PathVariable Long id) {
        log.debug("REST request to delete PIN : {}", id);
        pINService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
