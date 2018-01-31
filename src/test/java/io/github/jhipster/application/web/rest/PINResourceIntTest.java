package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.PIN;
import io.github.jhipster.application.repository.PINRepository;
import io.github.jhipster.application.service.PINService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PINResource REST controller.
 *
 * @see PINResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class PINResourceIntTest {

    private static final String DEFAULT_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_PIN = "AAAAAAAAAA";
    private static final String UPDATED_PIN = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEIP = "AAAAAAAAAA";
    private static final String UPDATED_CREATEIP = "BBBBBBBBBB";

    private static final String DEFAULT_CREATEUSER = "AAAAAAAAAA";
    private static final String UPDATED_CREATEUSER = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATETIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATETIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_EXPIRETIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EXPIRETIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_CLAIMTIMESTAMP = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CLAIMTIMESTAMP = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_CLAIMUSER = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMUSER = "BBBBBBBBBB";

    private static final String DEFAULT_CLAIMIP = "AAAAAAAAAA";
    private static final String UPDATED_CLAIMIP = "BBBBBBBBBB";

    @Autowired
    private PINRepository pINRepository;

    @Autowired
    private PINService pINService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPINMockMvc;

    private PIN pIN;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PINResource pINResource = new PINResource(pINService);
        this.restPINMockMvc = MockMvcBuilders.standaloneSetup(pINResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PIN createEntity(EntityManager em) {
        PIN pIN = new PIN()
            .account(DEFAULT_ACCOUNT)
            .pin(DEFAULT_PIN)
            .createip(DEFAULT_CREATEIP)
            .createuser(DEFAULT_CREATEUSER)
            .createtimestamp(DEFAULT_CREATETIMESTAMP)
            .expiretimestamp(DEFAULT_EXPIRETIMESTAMP)
            .claimtimestamp(DEFAULT_CLAIMTIMESTAMP)
            .claimuser(DEFAULT_CLAIMUSER)
            .claimip(DEFAULT_CLAIMIP);
        return pIN;
    }

    @Before
    public void initTest() {
        pIN = createEntity(em);
    }

    @Test
    @Transactional
    public void createPIN() throws Exception {
        int databaseSizeBeforeCreate = pINRepository.findAll().size();

        // Create the PIN
        restPINMockMvc.perform(post("/api/pins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pIN)))
            .andExpect(status().isCreated());

        // Validate the PIN in the database
        List<PIN> pINList = pINRepository.findAll();
        assertThat(pINList).hasSize(databaseSizeBeforeCreate + 1);
        PIN testPIN = pINList.get(pINList.size() - 1);
        assertThat(testPIN.getAccount()).isEqualTo(DEFAULT_ACCOUNT);
        assertThat(testPIN.getPin()).isEqualTo(DEFAULT_PIN);
        assertThat(testPIN.getCreateip()).isEqualTo(DEFAULT_CREATEIP);
        assertThat(testPIN.getCreateuser()).isEqualTo(DEFAULT_CREATEUSER);
        assertThat(testPIN.getCreatetimestamp()).isEqualTo(DEFAULT_CREATETIMESTAMP);
        assertThat(testPIN.getExpiretimestamp()).isEqualTo(DEFAULT_EXPIRETIMESTAMP);
        assertThat(testPIN.getClaimtimestamp()).isEqualTo(DEFAULT_CLAIMTIMESTAMP);
        assertThat(testPIN.getClaimuser()).isEqualTo(DEFAULT_CLAIMUSER);
        assertThat(testPIN.getClaimip()).isEqualTo(DEFAULT_CLAIMIP);
    }

    @Test
    @Transactional
    public void createPINWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = pINRepository.findAll().size();

        // Create the PIN with an existing ID
        pIN.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPINMockMvc.perform(post("/api/pins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pIN)))
            .andExpect(status().isBadRequest());

        // Validate the PIN in the database
        List<PIN> pINList = pINRepository.findAll();
        assertThat(pINList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPINS() throws Exception {
        // Initialize the database
        pINRepository.saveAndFlush(pIN);

        // Get all the pINList
        restPINMockMvc.perform(get("/api/pins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(pIN.getId().intValue())))
            .andExpect(jsonPath("$.[*].account").value(hasItem(DEFAULT_ACCOUNT.toString())))
            .andExpect(jsonPath("$.[*].pin").value(hasItem(DEFAULT_PIN.toString())))
            .andExpect(jsonPath("$.[*].createip").value(hasItem(DEFAULT_CREATEIP.toString())))
            .andExpect(jsonPath("$.[*].createuser").value(hasItem(DEFAULT_CREATEUSER.toString())))
            .andExpect(jsonPath("$.[*].createtimestamp").value(hasItem(DEFAULT_CREATETIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].expiretimestamp").value(hasItem(DEFAULT_EXPIRETIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].claimtimestamp").value(hasItem(DEFAULT_CLAIMTIMESTAMP.toString())))
            .andExpect(jsonPath("$.[*].claimuser").value(hasItem(DEFAULT_CLAIMUSER.toString())))
            .andExpect(jsonPath("$.[*].claimip").value(hasItem(DEFAULT_CLAIMIP.toString())));
    }

    @Test
    @Transactional
    public void getPIN() throws Exception {
        // Initialize the database
        pINRepository.saveAndFlush(pIN);

        // Get the pIN
        restPINMockMvc.perform(get("/api/pins/{id}", pIN.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(pIN.getId().intValue()))
            .andExpect(jsonPath("$.account").value(DEFAULT_ACCOUNT.toString()))
            .andExpect(jsonPath("$.pin").value(DEFAULT_PIN.toString()))
            .andExpect(jsonPath("$.createip").value(DEFAULT_CREATEIP.toString()))
            .andExpect(jsonPath("$.createuser").value(DEFAULT_CREATEUSER.toString()))
            .andExpect(jsonPath("$.createtimestamp").value(DEFAULT_CREATETIMESTAMP.toString()))
            .andExpect(jsonPath("$.expiretimestamp").value(DEFAULT_EXPIRETIMESTAMP.toString()))
            .andExpect(jsonPath("$.claimtimestamp").value(DEFAULT_CLAIMTIMESTAMP.toString()))
            .andExpect(jsonPath("$.claimuser").value(DEFAULT_CLAIMUSER.toString()))
            .andExpect(jsonPath("$.claimip").value(DEFAULT_CLAIMIP.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingPIN() throws Exception {
        // Get the pIN
        restPINMockMvc.perform(get("/api/pins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePIN() throws Exception {
        // Initialize the database
        pINService.save(pIN);

        int databaseSizeBeforeUpdate = pINRepository.findAll().size();

        // Update the pIN
        PIN updatedPIN = pINRepository.findOne(pIN.getId());
        // Disconnect from session so that the updates on updatedPIN are not directly saved in db
        em.detach(updatedPIN);
        updatedPIN
            .account(UPDATED_ACCOUNT)
            .pin(UPDATED_PIN)
            .createip(UPDATED_CREATEIP)
            .createuser(UPDATED_CREATEUSER)
            .createtimestamp(UPDATED_CREATETIMESTAMP)
            .expiretimestamp(UPDATED_EXPIRETIMESTAMP)
            .claimtimestamp(UPDATED_CLAIMTIMESTAMP)
            .claimuser(UPDATED_CLAIMUSER)
            .claimip(UPDATED_CLAIMIP);

        restPINMockMvc.perform(put("/api/pins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPIN)))
            .andExpect(status().isOk());

        // Validate the PIN in the database
        List<PIN> pINList = pINRepository.findAll();
        assertThat(pINList).hasSize(databaseSizeBeforeUpdate);
        PIN testPIN = pINList.get(pINList.size() - 1);
        assertThat(testPIN.getAccount()).isEqualTo(UPDATED_ACCOUNT);
        assertThat(testPIN.getPin()).isEqualTo(UPDATED_PIN);
        assertThat(testPIN.getCreateip()).isEqualTo(UPDATED_CREATEIP);
        assertThat(testPIN.getCreateuser()).isEqualTo(UPDATED_CREATEUSER);
        assertThat(testPIN.getCreatetimestamp()).isEqualTo(UPDATED_CREATETIMESTAMP);
        assertThat(testPIN.getExpiretimestamp()).isEqualTo(UPDATED_EXPIRETIMESTAMP);
        assertThat(testPIN.getClaimtimestamp()).isEqualTo(UPDATED_CLAIMTIMESTAMP);
        assertThat(testPIN.getClaimuser()).isEqualTo(UPDATED_CLAIMUSER);
        assertThat(testPIN.getClaimip()).isEqualTo(UPDATED_CLAIMIP);
    }

    @Test
    @Transactional
    public void updateNonExistingPIN() throws Exception {
        int databaseSizeBeforeUpdate = pINRepository.findAll().size();

        // Create the PIN

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restPINMockMvc.perform(put("/api/pins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(pIN)))
            .andExpect(status().isCreated());

        // Validate the PIN in the database
        List<PIN> pINList = pINRepository.findAll();
        assertThat(pINList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deletePIN() throws Exception {
        // Initialize the database
        pINService.save(pIN);

        int databaseSizeBeforeDelete = pINRepository.findAll().size();

        // Get the pIN
        restPINMockMvc.perform(delete("/api/pins/{id}", pIN.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PIN> pINList = pINRepository.findAll();
        assertThat(pINList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PIN.class);
        PIN pIN1 = new PIN();
        pIN1.setId(1L);
        PIN pIN2 = new PIN();
        pIN2.setId(pIN1.getId());
        assertThat(pIN1).isEqualTo(pIN2);
        pIN2.setId(2L);
        assertThat(pIN1).isNotEqualTo(pIN2);
        pIN1.setId(null);
        assertThat(pIN1).isNotEqualTo(pIN2);
    }
}
