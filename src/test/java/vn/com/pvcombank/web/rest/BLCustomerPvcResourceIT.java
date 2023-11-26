package vn.com.pvcombank.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import vn.com.pvcombank.IntegrationTest;
import vn.com.pvcombank.domain.BLCustomerPvc;
import vn.com.pvcombank.repository.BLCustomerPvcRepository;

/**
 * Integration tests for the {@link BLCustomerPvcResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BLCustomerPvcResourceIT {

    private static final String DEFAULT_CIF = "AAAAAAAAAA";
    private static final String UPDATED_CIF = "BBBBBBBBBB";

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_BIRTH = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_BRANCH = "AAAAAAAAAA";
    private static final String UPDATED_BRANCH = "BBBBBBBBBB";

    private static final String DEFAULT_BL_CUSTOMER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BL_CUSTOMER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME_BL = "AAAAAAAAAA";
    private static final String UPDATED_NAME_BL = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_BIRTH_BL = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_BIRTH_BL = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID_TYPE_BL = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID_TYPE_BL = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MATCH_ATTR = "AAAAAAAAAA";
    private static final String UPDATED_MATCH_ATTR = "BBBBBBBBBB";

    private static final String DEFAULT_VALUE_ATTR = "AAAAAAAAAA";
    private static final String UPDATED_VALUE_ATTR = "BBBBBBBBBB";

    private static final String DEFAULT_WEIGHT_ATTR = "AAAAAAAAAA";
    private static final String UPDATED_WEIGHT_ATTR = "BBBBBBBBBB";

    private static final String DEFAULT_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_createdBy = "AAAAAAAAAA";
    private static final String UPDATED_createdBy = "BBBBBBBBBB";

    private static final Instant DEFAULT_date_created = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_date_created = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_AUTHORISE_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORISE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_AUTHORISE = "";
    private static final String UPDATED_DATE_AUTHORISE = "";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bl-customer-pvcs";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BLCustomerPvcRepository bLCustomerPvcRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBLCustomerPvcMockMvc;

    private BLCustomerPvc bLCustomerPvc;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLCustomerPvc createEntity(EntityManager em) {
        BLCustomerPvc bLCustomerPvc = new BLCustomerPvc()
            .cif(DEFAULT_CIF)
            .fullName(DEFAULT_FULL_NAME)
            .dateOfBirth(DEFAULT_DATE_OF_BIRTH)
            .legalId(DEFAULT_LEGAL_ID)
            .legalType(DEFAULT_LEGAL_TYPE)
            .branch(DEFAULT_BRANCH)
            .blCustomerId(DEFAULT_BL_CUSTOMER_ID)
            .nameBl(DEFAULT_NAME_BL)
            .dateOfBirthBl(DEFAULT_DATE_OF_BIRTH_BL)
            .legalIdTypeBl(DEFAULT_LEGAL_ID_TYPE_BL)
            .legalIdNumber(DEFAULT_LEGAL_ID_NUMBER)
            .matchAttr(DEFAULT_MATCH_ATTR)
            .valueAttr(DEFAULT_VALUE_ATTR)
            .weightAttr(DEFAULT_WEIGHT_ATTR)
            .score(DEFAULT_SCORE)
            .status(DEFAULT_STATUS)
            .remark(DEFAULT_REMARK)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .coCode(DEFAULT_CO_CODE)
            .createdBy(DEFAULT_CREATED_BY)
            .dateCreated(DEFAULT_DATE_CREATED)
            .authoriseBy(DEFAULT_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE);
        return bLCustomerPvc;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLCustomerPvc createUpdatedEntity(EntityManager em) {
        BLCustomerPvc bLCustomerPvc = new BLCustomerPvc()
            .cif(UPDATED_CIF)
            .fullName(UPDATED_FULL_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .legalId(UPDATED_LEGAL_ID)
            .legalType(UPDATED_LEGAL_TYPE)
            .branch(UPDATED_BRANCH)
            .blCustomerId(UPDATED_BL_CUSTOMER_ID)
            .nameBl(UPDATED_NAME_BL)
            .dateOfBirthBl(UPDATED_DATE_OF_BIRTH_BL)
            .legalIdTypeBl(UPDATED_LEGAL_ID_TYPE_BL)
            .legalIdNumber(UPDATED_LEGAL_ID_NUMBER)
            .matchAttr(UPDATED_MATCH_ATTR)
            .valueAttr(UPDATED_VALUE_ATTR)
            .weightAttr(UPDATED_WEIGHT_ATTR)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .recordStatus(UPDATED_RECORD_STATUS)
            .coCode(UPDATED_CO_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE);

        return bLCustomerPvc;
    }

    @BeforeEach
    public void initTest() {
        bLCustomerPvc = createEntity(em);
    }

    @Test
    @Transactional
    void createBLCustomerPvc() throws Exception {
        int databaseSizeBeforeCreate = bLCustomerPvcRepository.findAll().size();
        // Create the BLCustomerPvc
        restBLCustomerPvcMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isCreated());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeCreate + 1);
        BLCustomerPvc testBLCustomerPvc = bLCustomerPvcList.get(bLCustomerPvcList.size() - 1);
        assertThat(testBLCustomerPvc.getCif()).isEqualTo(DEFAULT_CIF);
        assertThat(testBLCustomerPvc.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testBLCustomerPvc.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testBLCustomerPvc.getLegalId()).isEqualTo(DEFAULT_LEGAL_ID);
        assertThat(testBLCustomerPvc.getLegalType()).isEqualTo(DEFAULT_LEGAL_TYPE);
        assertThat(testBLCustomerPvc.getBranch()).isEqualTo(DEFAULT_BRANCH);
        assertThat(testBLCustomerPvc.getBlCustomerId()).isEqualTo(DEFAULT_BL_CUSTOMER_ID);
        assertThat(testBLCustomerPvc.getNameBl()).isEqualTo(DEFAULT_NAME_BL);
        assertThat(testBLCustomerPvc.getDateOfBirthBl()).isEqualTo(DEFAULT_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomerPvc.getLegalIdTypeBl()).isEqualTo(DEFAULT_LEGAL_ID_TYPE_BL);
        assertThat(testBLCustomerPvc.getLegalIdNumber()).isEqualTo(DEFAULT_LEGAL_ID_NUMBER);
        assertThat(testBLCustomerPvc.getMatchAttr()).isEqualTo(DEFAULT_MATCH_ATTR);
        assertThat(testBLCustomerPvc.getValueAttr()).isEqualTo(DEFAULT_VALUE_ATTR);
        assertThat(testBLCustomerPvc.getWeightAttr()).isEqualTo(DEFAULT_WEIGHT_ATTR);
        assertThat(testBLCustomerPvc.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testBLCustomerPvc.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testBLCustomerPvc.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testBLCustomerPvc.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testBLCustomerPvc.getCoCode()).isEqualTo(DEFAULT_CO_CODE);
        assertThat(testBLCustomerPvc.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBLCustomerPvc.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testBLCustomerPvc.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLCustomerPvc.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void createBLCustomerPvcWithExistingId() throws Exception {
        // Create the BLCustomerPvc with an existing ID
        bLCustomerPvc.setId(1L);

        int databaseSizeBeforeCreate = bLCustomerPvcRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBLCustomerPvcMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcs() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList
        restBLCustomerPvcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLCustomerPvc.getId().intValue())))
            .andExpect(jsonPath("$.[*].cif").value(hasItem(DEFAULT_CIF)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].legalId").value(hasItem(DEFAULT_LEGAL_ID)))
            .andExpect(jsonPath("$.[*].legalType").value(hasItem(DEFAULT_LEGAL_TYPE)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)))
            .andExpect(jsonPath("$.[*].blCustomerId").value(hasItem(DEFAULT_BL_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].nameBl").value(hasItem(DEFAULT_NAME_BL)))
            .andExpect(jsonPath("$.[*].dateOfBirthBl").value(hasItem(DEFAULT_DATE_OF_BIRTH_BL)))
            .andExpect(jsonPath("$.[*].legalIdTypeBl").value(hasItem(DEFAULT_LEGAL_ID_TYPE_BL)))
            .andExpect(jsonPath("$.[*].legalIdNumber").value(hasItem(DEFAULT_LEGAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].matchAttr").value(hasItem(DEFAULT_MATCH_ATTR)))
            .andExpect(jsonPath("$.[*].valueAttr").value(hasItem(DEFAULT_VALUE_ATTR)))
            .andExpect(jsonPath("$.[*].weightAttr").value(hasItem(DEFAULT_WEIGHT_ATTR)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].coCode").value(hasItem(DEFAULT_CO_CODE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(DEFAULT_DATE_AUTHORISE.toString())));
    }

    @Test
    @Transactional
    void getBLCustomerPvc() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get the bLCustomerPvc
        restBLCustomerPvcMockMvc
            .perform(get(ENTITY_API_URL_ID, bLCustomerPvc.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bLCustomerPvc.getId().intValue()))
            .andExpect(jsonPath("$.cif").value(DEFAULT_CIF))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.dateOfBirth").value(DEFAULT_DATE_OF_BIRTH))
            .andExpect(jsonPath("$.legalId").value(DEFAULT_LEGAL_ID))
            .andExpect(jsonPath("$.legalType").value(DEFAULT_LEGAL_TYPE))
            .andExpect(jsonPath("$.branch").value(DEFAULT_BRANCH))
            .andExpect(jsonPath("$.blCustomerId").value(DEFAULT_BL_CUSTOMER_ID))
            .andExpect(jsonPath("$.nameBl").value(DEFAULT_NAME_BL))
            .andExpect(jsonPath("$.dateOfBirthBl").value(DEFAULT_DATE_OF_BIRTH_BL))
            .andExpect(jsonPath("$.legalIdTypeBl").value(DEFAULT_LEGAL_ID_TYPE_BL))
            .andExpect(jsonPath("$.legalIdNumber").value(DEFAULT_LEGAL_ID_NUMBER))
            .andExpect(jsonPath("$.matchAttr").value(DEFAULT_MATCH_ATTR))
            .andExpect(jsonPath("$.valueAttr").value(DEFAULT_VALUE_ATTR))
            .andExpect(jsonPath("$.weightAttr").value(DEFAULT_WEIGHT_ATTR))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
            .andExpect(jsonPath("$.coCode").value(DEFAULT_CO_CODE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED))
            .andExpect(jsonPath("$.authoriseBy").value(DEFAULT_AUTHORISE_BY))
            .andExpect(jsonPath("$.dateAuthorise").value(DEFAULT_DATE_AUTHORISE.toString()));
    }

    @Test
    @Transactional
    void getBLCustomerPvcsByIdFiltering() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        Long id = bLCustomerPvc.getId();

        defaultBLCustomerPvcShouldBeFound("id.equals=" + id);
        defaultBLCustomerPvcShouldNotBeFound("id.notEquals=" + id);

        defaultBLCustomerPvcShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBLCustomerPvcShouldNotBeFound("id.greaterThan=" + id);

        defaultBLCustomerPvcShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBLCustomerPvcShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCifIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where cif equals to DEFAULT_CIF
        defaultBLCustomerPvcShouldBeFound("cif.equals=" + DEFAULT_CIF);

        // Get all the bLCustomerPvcList where cif equals to UPDATED_CIF
        defaultBLCustomerPvcShouldNotBeFound("cif.equals=" + UPDATED_CIF);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCifIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where cif not equals to DEFAULT_CIF
        defaultBLCustomerPvcShouldNotBeFound("cif.notEquals=" + DEFAULT_CIF);

        // Get all the bLCustomerPvcList where cif not equals to UPDATED_CIF
        defaultBLCustomerPvcShouldBeFound("cif.notEquals=" + UPDATED_CIF);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCifIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where cif in DEFAULT_CIF or UPDATED_CIF
        defaultBLCustomerPvcShouldBeFound("cif.in=" + DEFAULT_CIF + "," + UPDATED_CIF);

        // Get all the bLCustomerPvcList where cif equals to UPDATED_CIF
        defaultBLCustomerPvcShouldNotBeFound("cif.in=" + UPDATED_CIF);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCifIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where cif is not null
        defaultBLCustomerPvcShouldBeFound("cif.specified=true");

        // Get all the bLCustomerPvcList where cif is null
        defaultBLCustomerPvcShouldNotBeFound("cif.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCifContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where cif contains DEFAULT_CIF
        defaultBLCustomerPvcShouldBeFound("cif.contains=" + DEFAULT_CIF);

        // Get all the bLCustomerPvcList where cif contains UPDATED_CIF
        defaultBLCustomerPvcShouldNotBeFound("cif.contains=" + UPDATED_CIF);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCifNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where cif does not contain DEFAULT_CIF
        defaultBLCustomerPvcShouldNotBeFound("cif.doesNotContain=" + DEFAULT_CIF);

        // Get all the bLCustomerPvcList where cif does not contain UPDATED_CIF
        defaultBLCustomerPvcShouldBeFound("cif.doesNotContain=" + UPDATED_CIF);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where fullName equals to DEFAULT_FULL_NAME
        defaultBLCustomerPvcShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerPvcList where fullName equals to UPDATED_FULL_NAME
        defaultBLCustomerPvcShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByFullNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where fullName not equals to DEFAULT_FULL_NAME
        defaultBLCustomerPvcShouldNotBeFound("fullName.notEquals=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerPvcList where fullName not equals to UPDATED_FULL_NAME
        defaultBLCustomerPvcShouldBeFound("fullName.notEquals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultBLCustomerPvcShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the bLCustomerPvcList where fullName equals to UPDATED_FULL_NAME
        defaultBLCustomerPvcShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where fullName is not null
        defaultBLCustomerPvcShouldBeFound("fullName.specified=true");

        // Get all the bLCustomerPvcList where fullName is null
        defaultBLCustomerPvcShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByFullNameContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where fullName contains DEFAULT_FULL_NAME
        defaultBLCustomerPvcShouldBeFound("fullName.contains=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerPvcList where fullName contains UPDATED_FULL_NAME
        defaultBLCustomerPvcShouldNotBeFound("fullName.contains=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByFullNameNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where fullName does not contain DEFAULT_FULL_NAME
        defaultBLCustomerPvcShouldNotBeFound("fullName.doesNotContain=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerPvcList where fullName does not contain UPDATED_FULL_NAME
        defaultBLCustomerPvcShouldBeFound("fullName.doesNotContain=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirth equals to DEFAULT_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldBeFound("dateOfBirth.equals=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the bLCustomerPvcList where dateOfBirth equals to UPDATED_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirth.equals=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirth not equals to DEFAULT_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirth.notEquals=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the bLCustomerPvcList where dateOfBirth not equals to UPDATED_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldBeFound("dateOfBirth.notEquals=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirth in DEFAULT_DATE_OF_BIRTH or UPDATED_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldBeFound("dateOfBirth.in=" + DEFAULT_DATE_OF_BIRTH + "," + UPDATED_DATE_OF_BIRTH);

        // Get all the bLCustomerPvcList where dateOfBirth equals to UPDATED_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirth.in=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirth is not null
        defaultBLCustomerPvcShouldBeFound("dateOfBirth.specified=true");

        // Get all the bLCustomerPvcList where dateOfBirth is null
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirth.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirth contains DEFAULT_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldBeFound("dateOfBirth.contains=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the bLCustomerPvcList where dateOfBirth contains UPDATED_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirth.contains=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirth does not contain DEFAULT_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirth.doesNotContain=" + DEFAULT_DATE_OF_BIRTH);

        // Get all the bLCustomerPvcList where dateOfBirth does not contain UPDATED_DATE_OF_BIRTH
        defaultBLCustomerPvcShouldBeFound("dateOfBirth.doesNotContain=" + UPDATED_DATE_OF_BIRTH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalId equals to DEFAULT_LEGAL_ID
        defaultBLCustomerPvcShouldBeFound("legalId.equals=" + DEFAULT_LEGAL_ID);

        // Get all the bLCustomerPvcList where legalId equals to UPDATED_LEGAL_ID
        defaultBLCustomerPvcShouldNotBeFound("legalId.equals=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalId not equals to DEFAULT_LEGAL_ID
        defaultBLCustomerPvcShouldNotBeFound("legalId.notEquals=" + DEFAULT_LEGAL_ID);

        // Get all the bLCustomerPvcList where legalId not equals to UPDATED_LEGAL_ID
        defaultBLCustomerPvcShouldBeFound("legalId.notEquals=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalId in DEFAULT_LEGAL_ID or UPDATED_LEGAL_ID
        defaultBLCustomerPvcShouldBeFound("legalId.in=" + DEFAULT_LEGAL_ID + "," + UPDATED_LEGAL_ID);

        // Get all the bLCustomerPvcList where legalId equals to UPDATED_LEGAL_ID
        defaultBLCustomerPvcShouldNotBeFound("legalId.in=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalId is not null
        defaultBLCustomerPvcShouldBeFound("legalId.specified=true");

        // Get all the bLCustomerPvcList where legalId is null
        defaultBLCustomerPvcShouldNotBeFound("legalId.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalId contains DEFAULT_LEGAL_ID
        defaultBLCustomerPvcShouldBeFound("legalId.contains=" + DEFAULT_LEGAL_ID);

        // Get all the bLCustomerPvcList where legalId contains UPDATED_LEGAL_ID
        defaultBLCustomerPvcShouldNotBeFound("legalId.contains=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalId does not contain DEFAULT_LEGAL_ID
        defaultBLCustomerPvcShouldNotBeFound("legalId.doesNotContain=" + DEFAULT_LEGAL_ID);

        // Get all the bLCustomerPvcList where legalId does not contain UPDATED_LEGAL_ID
        defaultBLCustomerPvcShouldBeFound("legalId.doesNotContain=" + UPDATED_LEGAL_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalType equals to DEFAULT_LEGAL_TYPE
        defaultBLCustomerPvcShouldBeFound("legalType.equals=" + DEFAULT_LEGAL_TYPE);

        // Get all the bLCustomerPvcList where legalType equals to UPDATED_LEGAL_TYPE
        defaultBLCustomerPvcShouldNotBeFound("legalType.equals=" + UPDATED_LEGAL_TYPE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalType not equals to DEFAULT_LEGAL_TYPE
        defaultBLCustomerPvcShouldNotBeFound("legalType.notEquals=" + DEFAULT_LEGAL_TYPE);

        // Get all the bLCustomerPvcList where legalType not equals to UPDATED_LEGAL_TYPE
        defaultBLCustomerPvcShouldBeFound("legalType.notEquals=" + UPDATED_LEGAL_TYPE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalTypeIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalType in DEFAULT_LEGAL_TYPE or UPDATED_LEGAL_TYPE
        defaultBLCustomerPvcShouldBeFound("legalType.in=" + DEFAULT_LEGAL_TYPE + "," + UPDATED_LEGAL_TYPE);

        // Get all the bLCustomerPvcList where legalType equals to UPDATED_LEGAL_TYPE
        defaultBLCustomerPvcShouldNotBeFound("legalType.in=" + UPDATED_LEGAL_TYPE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalType is not null
        defaultBLCustomerPvcShouldBeFound("legalType.specified=true");

        // Get all the bLCustomerPvcList where legalType is null
        defaultBLCustomerPvcShouldNotBeFound("legalType.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalTypeContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalType contains DEFAULT_LEGAL_TYPE
        defaultBLCustomerPvcShouldBeFound("legalType.contains=" + DEFAULT_LEGAL_TYPE);

        // Get all the bLCustomerPvcList where legalType contains UPDATED_LEGAL_TYPE
        defaultBLCustomerPvcShouldNotBeFound("legalType.contains=" + UPDATED_LEGAL_TYPE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalTypeNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalType does not contain DEFAULT_LEGAL_TYPE
        defaultBLCustomerPvcShouldNotBeFound("legalType.doesNotContain=" + DEFAULT_LEGAL_TYPE);

        // Get all the bLCustomerPvcList where legalType does not contain UPDATED_LEGAL_TYPE
        defaultBLCustomerPvcShouldBeFound("legalType.doesNotContain=" + UPDATED_LEGAL_TYPE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBranchIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where branch equals to DEFAULT_BRANCH
        defaultBLCustomerPvcShouldBeFound("branch.equals=" + DEFAULT_BRANCH);

        // Get all the bLCustomerPvcList where branch equals to UPDATED_BRANCH
        defaultBLCustomerPvcShouldNotBeFound("branch.equals=" + UPDATED_BRANCH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBranchIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where branch not equals to DEFAULT_BRANCH
        defaultBLCustomerPvcShouldNotBeFound("branch.notEquals=" + DEFAULT_BRANCH);

        // Get all the bLCustomerPvcList where branch not equals to UPDATED_BRANCH
        defaultBLCustomerPvcShouldBeFound("branch.notEquals=" + UPDATED_BRANCH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBranchIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where branch in DEFAULT_BRANCH or UPDATED_BRANCH
        defaultBLCustomerPvcShouldBeFound("branch.in=" + DEFAULT_BRANCH + "," + UPDATED_BRANCH);

        // Get all the bLCustomerPvcList where branch equals to UPDATED_BRANCH
        defaultBLCustomerPvcShouldNotBeFound("branch.in=" + UPDATED_BRANCH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBranchIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where branch is not null
        defaultBLCustomerPvcShouldBeFound("branch.specified=true");

        // Get all the bLCustomerPvcList where branch is null
        defaultBLCustomerPvcShouldNotBeFound("branch.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBranchContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where branch contains DEFAULT_BRANCH
        defaultBLCustomerPvcShouldBeFound("branch.contains=" + DEFAULT_BRANCH);

        // Get all the bLCustomerPvcList where branch contains UPDATED_BRANCH
        defaultBLCustomerPvcShouldNotBeFound("branch.contains=" + UPDATED_BRANCH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBranchNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where branch does not contain DEFAULT_BRANCH
        defaultBLCustomerPvcShouldNotBeFound("branch.doesNotContain=" + DEFAULT_BRANCH);

        // Get all the bLCustomerPvcList where branch does not contain UPDATED_BRANCH
        defaultBLCustomerPvcShouldBeFound("branch.doesNotContain=" + UPDATED_BRANCH);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBlCustomerIdIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where blCustomerId equals to DEFAULT_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldBeFound("blCustomerId.equals=" + DEFAULT_BL_CUSTOMER_ID);

        // Get all the bLCustomerPvcList where blCustomerId equals to UPDATED_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldNotBeFound("blCustomerId.equals=" + UPDATED_BL_CUSTOMER_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBlCustomerIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where blCustomerId not equals to DEFAULT_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldNotBeFound("blCustomerId.notEquals=" + DEFAULT_BL_CUSTOMER_ID);

        // Get all the bLCustomerPvcList where blCustomerId not equals to UPDATED_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldBeFound("blCustomerId.notEquals=" + UPDATED_BL_CUSTOMER_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBlCustomerIdIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where blCustomerId in DEFAULT_BL_CUSTOMER_ID or UPDATED_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldBeFound("blCustomerId.in=" + DEFAULT_BL_CUSTOMER_ID + "," + UPDATED_BL_CUSTOMER_ID);

        // Get all the bLCustomerPvcList where blCustomerId equals to UPDATED_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldNotBeFound("blCustomerId.in=" + UPDATED_BL_CUSTOMER_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBlCustomerIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where blCustomerId is not null
        defaultBLCustomerPvcShouldBeFound("blCustomerId.specified=true");

        // Get all the bLCustomerPvcList where blCustomerId is null
        defaultBLCustomerPvcShouldNotBeFound("blCustomerId.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBlCustomerIdContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where blCustomerId contains DEFAULT_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldBeFound("blCustomerId.contains=" + DEFAULT_BL_CUSTOMER_ID);

        // Get all the bLCustomerPvcList where blCustomerId contains UPDATED_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldNotBeFound("blCustomerId.contains=" + UPDATED_BL_CUSTOMER_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByBlCustomerIdNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where blCustomerId does not contain DEFAULT_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldNotBeFound("blCustomerId.doesNotContain=" + DEFAULT_BL_CUSTOMER_ID);

        // Get all the bLCustomerPvcList where blCustomerId does not contain UPDATED_BL_CUSTOMER_ID
        defaultBLCustomerPvcShouldBeFound("blCustomerId.doesNotContain=" + UPDATED_BL_CUSTOMER_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByNameBlIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where nameBl equals to DEFAULT_NAME_BL
        defaultBLCustomerPvcShouldBeFound("nameBl.equals=" + DEFAULT_NAME_BL);

        // Get all the bLCustomerPvcList where nameBl equals to UPDATED_NAME_BL
        defaultBLCustomerPvcShouldNotBeFound("nameBl.equals=" + UPDATED_NAME_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByNameBlIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where nameBl not equals to DEFAULT_NAME_BL
        defaultBLCustomerPvcShouldNotBeFound("nameBl.notEquals=" + DEFAULT_NAME_BL);

        // Get all the bLCustomerPvcList where nameBl not equals to UPDATED_NAME_BL
        defaultBLCustomerPvcShouldBeFound("nameBl.notEquals=" + UPDATED_NAME_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByNameBlIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where nameBl in DEFAULT_NAME_BL or UPDATED_NAME_BL
        defaultBLCustomerPvcShouldBeFound("nameBl.in=" + DEFAULT_NAME_BL + "," + UPDATED_NAME_BL);

        // Get all the bLCustomerPvcList where nameBl equals to UPDATED_NAME_BL
        defaultBLCustomerPvcShouldNotBeFound("nameBl.in=" + UPDATED_NAME_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByNameBlIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where nameBl is not null
        defaultBLCustomerPvcShouldBeFound("nameBl.specified=true");

        // Get all the bLCustomerPvcList where nameBl is null
        defaultBLCustomerPvcShouldNotBeFound("nameBl.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByNameBlContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where nameBl contains DEFAULT_NAME_BL
        defaultBLCustomerPvcShouldBeFound("nameBl.contains=" + DEFAULT_NAME_BL);

        // Get all the bLCustomerPvcList where nameBl contains UPDATED_NAME_BL
        defaultBLCustomerPvcShouldNotBeFound("nameBl.contains=" + UPDATED_NAME_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByNameBlNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where nameBl does not contain DEFAULT_NAME_BL
        defaultBLCustomerPvcShouldNotBeFound("nameBl.doesNotContain=" + DEFAULT_NAME_BL);

        // Get all the bLCustomerPvcList where nameBl does not contain UPDATED_NAME_BL
        defaultBLCustomerPvcShouldBeFound("nameBl.doesNotContain=" + UPDATED_NAME_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthBlIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirthBl equals to DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldBeFound("dateOfBirthBl.equals=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerPvcList where dateOfBirthBl equals to UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirthBl.equals=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthBlIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirthBl not equals to DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirthBl.notEquals=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerPvcList where dateOfBirthBl not equals to UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldBeFound("dateOfBirthBl.notEquals=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthBlIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirthBl in DEFAULT_DATE_OF_BIRTH_BL or UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldBeFound("dateOfBirthBl.in=" + DEFAULT_DATE_OF_BIRTH_BL + "," + UPDATED_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerPvcList where dateOfBirthBl equals to UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirthBl.in=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthBlIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirthBl is not null
        defaultBLCustomerPvcShouldBeFound("dateOfBirthBl.specified=true");

        // Get all the bLCustomerPvcList where dateOfBirthBl is null
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirthBl.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthBlContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirthBl contains DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldBeFound("dateOfBirthBl.contains=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerPvcList where dateOfBirthBl contains UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirthBl.contains=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateOfBirthBlNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateOfBirthBl does not contain DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldNotBeFound("dateOfBirthBl.doesNotContain=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerPvcList where dateOfBirthBl does not contain UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerPvcShouldBeFound("dateOfBirthBl.doesNotContain=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdTypeBlIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdTypeBl equals to DEFAULT_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldBeFound("legalIdTypeBl.equals=" + DEFAULT_LEGAL_ID_TYPE_BL);

        // Get all the bLCustomerPvcList where legalIdTypeBl equals to UPDATED_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldNotBeFound("legalIdTypeBl.equals=" + UPDATED_LEGAL_ID_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdTypeBlIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdTypeBl not equals to DEFAULT_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldNotBeFound("legalIdTypeBl.notEquals=" + DEFAULT_LEGAL_ID_TYPE_BL);

        // Get all the bLCustomerPvcList where legalIdTypeBl not equals to UPDATED_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldBeFound("legalIdTypeBl.notEquals=" + UPDATED_LEGAL_ID_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdTypeBlIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdTypeBl in DEFAULT_LEGAL_ID_TYPE_BL or UPDATED_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldBeFound("legalIdTypeBl.in=" + DEFAULT_LEGAL_ID_TYPE_BL + "," + UPDATED_LEGAL_ID_TYPE_BL);

        // Get all the bLCustomerPvcList where legalIdTypeBl equals to UPDATED_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldNotBeFound("legalIdTypeBl.in=" + UPDATED_LEGAL_ID_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdTypeBlIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdTypeBl is not null
        defaultBLCustomerPvcShouldBeFound("legalIdTypeBl.specified=true");

        // Get all the bLCustomerPvcList where legalIdTypeBl is null
        defaultBLCustomerPvcShouldNotBeFound("legalIdTypeBl.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdTypeBlContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdTypeBl contains DEFAULT_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldBeFound("legalIdTypeBl.contains=" + DEFAULT_LEGAL_ID_TYPE_BL);

        // Get all the bLCustomerPvcList where legalIdTypeBl contains UPDATED_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldNotBeFound("legalIdTypeBl.contains=" + UPDATED_LEGAL_ID_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdTypeBlNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdTypeBl does not contain DEFAULT_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldNotBeFound("legalIdTypeBl.doesNotContain=" + DEFAULT_LEGAL_ID_TYPE_BL);

        // Get all the bLCustomerPvcList where legalIdTypeBl does not contain UPDATED_LEGAL_ID_TYPE_BL
        defaultBLCustomerPvcShouldBeFound("legalIdTypeBl.doesNotContain=" + UPDATED_LEGAL_ID_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdNumber equals to DEFAULT_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldBeFound("legalIdNumber.equals=" + DEFAULT_LEGAL_ID_NUMBER);

        // Get all the bLCustomerPvcList where legalIdNumber equals to UPDATED_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldNotBeFound("legalIdNumber.equals=" + UPDATED_LEGAL_ID_NUMBER);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdNumber not equals to DEFAULT_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldNotBeFound("legalIdNumber.notEquals=" + DEFAULT_LEGAL_ID_NUMBER);

        // Get all the bLCustomerPvcList where legalIdNumber not equals to UPDATED_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldBeFound("legalIdNumber.notEquals=" + UPDATED_LEGAL_ID_NUMBER);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdNumberIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdNumber in DEFAULT_LEGAL_ID_NUMBER or UPDATED_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldBeFound("legalIdNumber.in=" + DEFAULT_LEGAL_ID_NUMBER + "," + UPDATED_LEGAL_ID_NUMBER);

        // Get all the bLCustomerPvcList where legalIdNumber equals to UPDATED_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldNotBeFound("legalIdNumber.in=" + UPDATED_LEGAL_ID_NUMBER);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdNumber is not null
        defaultBLCustomerPvcShouldBeFound("legalIdNumber.specified=true");

        // Get all the bLCustomerPvcList where legalIdNumber is null
        defaultBLCustomerPvcShouldNotBeFound("legalIdNumber.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdNumberContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdNumber contains DEFAULT_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldBeFound("legalIdNumber.contains=" + DEFAULT_LEGAL_ID_NUMBER);

        // Get all the bLCustomerPvcList where legalIdNumber contains UPDATED_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldNotBeFound("legalIdNumber.contains=" + UPDATED_LEGAL_ID_NUMBER);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByLegalIdNumberNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where legalIdNumber does not contain DEFAULT_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldNotBeFound("legalIdNumber.doesNotContain=" + DEFAULT_LEGAL_ID_NUMBER);

        // Get all the bLCustomerPvcList where legalIdNumber does not contain UPDATED_LEGAL_ID_NUMBER
        defaultBLCustomerPvcShouldBeFound("legalIdNumber.doesNotContain=" + UPDATED_LEGAL_ID_NUMBER);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByMatchAttrIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where matchAttr equals to DEFAULT_MATCH_ATTR
        defaultBLCustomerPvcShouldBeFound("matchAttr.equals=" + DEFAULT_MATCH_ATTR);

        // Get all the bLCustomerPvcList where matchAttr equals to UPDATED_MATCH_ATTR
        defaultBLCustomerPvcShouldNotBeFound("matchAttr.equals=" + UPDATED_MATCH_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByMatchAttrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where matchAttr not equals to DEFAULT_MATCH_ATTR
        defaultBLCustomerPvcShouldNotBeFound("matchAttr.notEquals=" + DEFAULT_MATCH_ATTR);

        // Get all the bLCustomerPvcList where matchAttr not equals to UPDATED_MATCH_ATTR
        defaultBLCustomerPvcShouldBeFound("matchAttr.notEquals=" + UPDATED_MATCH_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByMatchAttrIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where matchAttr in DEFAULT_MATCH_ATTR or UPDATED_MATCH_ATTR
        defaultBLCustomerPvcShouldBeFound("matchAttr.in=" + DEFAULT_MATCH_ATTR + "," + UPDATED_MATCH_ATTR);

        // Get all the bLCustomerPvcList where matchAttr equals to UPDATED_MATCH_ATTR
        defaultBLCustomerPvcShouldNotBeFound("matchAttr.in=" + UPDATED_MATCH_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByMatchAttrIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where matchAttr is not null
        defaultBLCustomerPvcShouldBeFound("matchAttr.specified=true");

        // Get all the bLCustomerPvcList where matchAttr is null
        defaultBLCustomerPvcShouldNotBeFound("matchAttr.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByMatchAttrContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where matchAttr contains DEFAULT_MATCH_ATTR
        defaultBLCustomerPvcShouldBeFound("matchAttr.contains=" + DEFAULT_MATCH_ATTR);

        // Get all the bLCustomerPvcList where matchAttr contains UPDATED_MATCH_ATTR
        defaultBLCustomerPvcShouldNotBeFound("matchAttr.contains=" + UPDATED_MATCH_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByMatchAttrNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where matchAttr does not contain DEFAULT_MATCH_ATTR
        defaultBLCustomerPvcShouldNotBeFound("matchAttr.doesNotContain=" + DEFAULT_MATCH_ATTR);

        // Get all the bLCustomerPvcList where matchAttr does not contain UPDATED_MATCH_ATTR
        defaultBLCustomerPvcShouldBeFound("matchAttr.doesNotContain=" + UPDATED_MATCH_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByValueAttrIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where valueAttr equals to DEFAULT_VALUE_ATTR
        defaultBLCustomerPvcShouldBeFound("valueAttr.equals=" + DEFAULT_VALUE_ATTR);

        // Get all the bLCustomerPvcList where valueAttr equals to UPDATED_VALUE_ATTR
        defaultBLCustomerPvcShouldNotBeFound("valueAttr.equals=" + UPDATED_VALUE_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByValueAttrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where valueAttr not equals to DEFAULT_VALUE_ATTR
        defaultBLCustomerPvcShouldNotBeFound("valueAttr.notEquals=" + DEFAULT_VALUE_ATTR);

        // Get all the bLCustomerPvcList where valueAttr not equals to UPDATED_VALUE_ATTR
        defaultBLCustomerPvcShouldBeFound("valueAttr.notEquals=" + UPDATED_VALUE_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByValueAttrIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where valueAttr in DEFAULT_VALUE_ATTR or UPDATED_VALUE_ATTR
        defaultBLCustomerPvcShouldBeFound("valueAttr.in=" + DEFAULT_VALUE_ATTR + "," + UPDATED_VALUE_ATTR);

        // Get all the bLCustomerPvcList where valueAttr equals to UPDATED_VALUE_ATTR
        defaultBLCustomerPvcShouldNotBeFound("valueAttr.in=" + UPDATED_VALUE_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByValueAttrIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where valueAttr is not null
        defaultBLCustomerPvcShouldBeFound("valueAttr.specified=true");

        // Get all the bLCustomerPvcList where valueAttr is null
        defaultBLCustomerPvcShouldNotBeFound("valueAttr.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByValueAttrContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where valueAttr contains DEFAULT_VALUE_ATTR
        defaultBLCustomerPvcShouldBeFound("valueAttr.contains=" + DEFAULT_VALUE_ATTR);

        // Get all the bLCustomerPvcList where valueAttr contains UPDATED_VALUE_ATTR
        defaultBLCustomerPvcShouldNotBeFound("valueAttr.contains=" + UPDATED_VALUE_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByValueAttrNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where valueAttr does not contain DEFAULT_VALUE_ATTR
        defaultBLCustomerPvcShouldNotBeFound("valueAttr.doesNotContain=" + DEFAULT_VALUE_ATTR);

        // Get all the bLCustomerPvcList where valueAttr does not contain UPDATED_VALUE_ATTR
        defaultBLCustomerPvcShouldBeFound("valueAttr.doesNotContain=" + UPDATED_VALUE_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByWeightAttrIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where weightAttr equals to DEFAULT_WEIGHT_ATTR
        defaultBLCustomerPvcShouldBeFound("weightAttr.equals=" + DEFAULT_WEIGHT_ATTR);

        // Get all the bLCustomerPvcList where weightAttr equals to UPDATED_WEIGHT_ATTR
        defaultBLCustomerPvcShouldNotBeFound("weightAttr.equals=" + UPDATED_WEIGHT_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByWeightAttrIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where weightAttr not equals to DEFAULT_WEIGHT_ATTR
        defaultBLCustomerPvcShouldNotBeFound("weightAttr.notEquals=" + DEFAULT_WEIGHT_ATTR);

        // Get all the bLCustomerPvcList where weightAttr not equals to UPDATED_WEIGHT_ATTR
        defaultBLCustomerPvcShouldBeFound("weightAttr.notEquals=" + UPDATED_WEIGHT_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByWeightAttrIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where weightAttr in DEFAULT_WEIGHT_ATTR or UPDATED_WEIGHT_ATTR
        defaultBLCustomerPvcShouldBeFound("weightAttr.in=" + DEFAULT_WEIGHT_ATTR + "," + UPDATED_WEIGHT_ATTR);

        // Get all the bLCustomerPvcList where weightAttr equals to UPDATED_WEIGHT_ATTR
        defaultBLCustomerPvcShouldNotBeFound("weightAttr.in=" + UPDATED_WEIGHT_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByWeightAttrIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where weightAttr is not null
        defaultBLCustomerPvcShouldBeFound("weightAttr.specified=true");

        // Get all the bLCustomerPvcList where weightAttr is null
        defaultBLCustomerPvcShouldNotBeFound("weightAttr.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByWeightAttrContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where weightAttr contains DEFAULT_WEIGHT_ATTR
        defaultBLCustomerPvcShouldBeFound("weightAttr.contains=" + DEFAULT_WEIGHT_ATTR);

        // Get all the bLCustomerPvcList where weightAttr contains UPDATED_WEIGHT_ATTR
        defaultBLCustomerPvcShouldNotBeFound("weightAttr.contains=" + UPDATED_WEIGHT_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByWeightAttrNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where weightAttr does not contain DEFAULT_WEIGHT_ATTR
        defaultBLCustomerPvcShouldNotBeFound("weightAttr.doesNotContain=" + DEFAULT_WEIGHT_ATTR);

        // Get all the bLCustomerPvcList where weightAttr does not contain UPDATED_WEIGHT_ATTR
        defaultBLCustomerPvcShouldBeFound("weightAttr.doesNotContain=" + UPDATED_WEIGHT_ATTR);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByScoreIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where score equals to DEFAULT_SCORE
        defaultBLCustomerPvcShouldBeFound("score.equals=" + DEFAULT_SCORE);

        // Get all the bLCustomerPvcList where score equals to UPDATED_SCORE
        defaultBLCustomerPvcShouldNotBeFound("score.equals=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByScoreIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where score not equals to DEFAULT_SCORE
        defaultBLCustomerPvcShouldNotBeFound("score.notEquals=" + DEFAULT_SCORE);

        // Get all the bLCustomerPvcList where score not equals to UPDATED_SCORE
        defaultBLCustomerPvcShouldBeFound("score.notEquals=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByScoreIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where score in DEFAULT_SCORE or UPDATED_SCORE
        defaultBLCustomerPvcShouldBeFound("score.in=" + DEFAULT_SCORE + "," + UPDATED_SCORE);

        // Get all the bLCustomerPvcList where score equals to UPDATED_SCORE
        defaultBLCustomerPvcShouldNotBeFound("score.in=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByScoreIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where score is not null
        defaultBLCustomerPvcShouldBeFound("score.specified=true");

        // Get all the bLCustomerPvcList where score is null
        defaultBLCustomerPvcShouldNotBeFound("score.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByScoreContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where score contains DEFAULT_SCORE
        defaultBLCustomerPvcShouldBeFound("score.contains=" + DEFAULT_SCORE);

        // Get all the bLCustomerPvcList where score contains UPDATED_SCORE
        defaultBLCustomerPvcShouldNotBeFound("score.contains=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByScoreNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where score does not contain DEFAULT_SCORE
        defaultBLCustomerPvcShouldNotBeFound("score.doesNotContain=" + DEFAULT_SCORE);

        // Get all the bLCustomerPvcList where score does not contain UPDATED_SCORE
        defaultBLCustomerPvcShouldBeFound("score.doesNotContain=" + UPDATED_SCORE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where status equals to DEFAULT_STATUS
        defaultBLCustomerPvcShouldBeFound("status.equals=" + DEFAULT_STATUS);

        // Get all the bLCustomerPvcList where status equals to UPDATED_STATUS
        defaultBLCustomerPvcShouldNotBeFound("status.equals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where status not equals to DEFAULT_STATUS
        defaultBLCustomerPvcShouldNotBeFound("status.notEquals=" + DEFAULT_STATUS);

        // Get all the bLCustomerPvcList where status not equals to UPDATED_STATUS
        defaultBLCustomerPvcShouldBeFound("status.notEquals=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByStatusIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where status in DEFAULT_STATUS or UPDATED_STATUS
        defaultBLCustomerPvcShouldBeFound("status.in=" + DEFAULT_STATUS + "," + UPDATED_STATUS);

        // Get all the bLCustomerPvcList where status equals to UPDATED_STATUS
        defaultBLCustomerPvcShouldNotBeFound("status.in=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where status is not null
        defaultBLCustomerPvcShouldBeFound("status.specified=true");

        // Get all the bLCustomerPvcList where status is null
        defaultBLCustomerPvcShouldNotBeFound("status.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByStatusContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where status contains DEFAULT_STATUS
        defaultBLCustomerPvcShouldBeFound("status.contains=" + DEFAULT_STATUS);

        // Get all the bLCustomerPvcList where status contains UPDATED_STATUS
        defaultBLCustomerPvcShouldNotBeFound("status.contains=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByStatusNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where status does not contain DEFAULT_STATUS
        defaultBLCustomerPvcShouldNotBeFound("status.doesNotContain=" + DEFAULT_STATUS);

        // Get all the bLCustomerPvcList where status does not contain UPDATED_STATUS
        defaultBLCustomerPvcShouldBeFound("status.doesNotContain=" + UPDATED_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRemarkIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where remark equals to DEFAULT_REMARK
        defaultBLCustomerPvcShouldBeFound("remark.equals=" + DEFAULT_REMARK);

        // Get all the bLCustomerPvcList where remark equals to UPDATED_REMARK
        defaultBLCustomerPvcShouldNotBeFound("remark.equals=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRemarkIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where remark not equals to DEFAULT_REMARK
        defaultBLCustomerPvcShouldNotBeFound("remark.notEquals=" + DEFAULT_REMARK);

        // Get all the bLCustomerPvcList where remark not equals to UPDATED_REMARK
        defaultBLCustomerPvcShouldBeFound("remark.notEquals=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRemarkIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where remark in DEFAULT_REMARK or UPDATED_REMARK
        defaultBLCustomerPvcShouldBeFound("remark.in=" + DEFAULT_REMARK + "," + UPDATED_REMARK);

        // Get all the bLCustomerPvcList where remark equals to UPDATED_REMARK
        defaultBLCustomerPvcShouldNotBeFound("remark.in=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRemarkIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where remark is not null
        defaultBLCustomerPvcShouldBeFound("remark.specified=true");

        // Get all the bLCustomerPvcList where remark is null
        defaultBLCustomerPvcShouldNotBeFound("remark.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRemarkContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where remark contains DEFAULT_REMARK
        defaultBLCustomerPvcShouldBeFound("remark.contains=" + DEFAULT_REMARK);

        // Get all the bLCustomerPvcList where remark contains UPDATED_REMARK
        defaultBLCustomerPvcShouldNotBeFound("remark.contains=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRemarkNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where remark does not contain DEFAULT_REMARK
        defaultBLCustomerPvcShouldNotBeFound("remark.doesNotContain=" + DEFAULT_REMARK);

        // Get all the bLCustomerPvcList where remark does not contain UPDATED_REMARK
        defaultBLCustomerPvcShouldBeFound("remark.doesNotContain=" + UPDATED_REMARK);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRecordStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where recordStatus equals to DEFAULT_RECORD_STATUS
        defaultBLCustomerPvcShouldBeFound("recordStatus.equals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerPvcList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLCustomerPvcShouldNotBeFound("recordStatus.equals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRecordStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where recordStatus not equals to DEFAULT_RECORD_STATUS
        defaultBLCustomerPvcShouldNotBeFound("recordStatus.notEquals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerPvcList where recordStatus not equals to UPDATED_RECORD_STATUS
        defaultBLCustomerPvcShouldBeFound("recordStatus.notEquals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRecordStatusIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where recordStatus in DEFAULT_RECORD_STATUS or UPDATED_RECORD_STATUS
        defaultBLCustomerPvcShouldBeFound("recordStatus.in=" + DEFAULT_RECORD_STATUS + "," + UPDATED_RECORD_STATUS);

        // Get all the bLCustomerPvcList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLCustomerPvcShouldNotBeFound("recordStatus.in=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRecordStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where recordStatus is not null
        defaultBLCustomerPvcShouldBeFound("recordStatus.specified=true");

        // Get all the bLCustomerPvcList where recordStatus is null
        defaultBLCustomerPvcShouldNotBeFound("recordStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRecordStatusContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where recordStatus contains DEFAULT_RECORD_STATUS
        defaultBLCustomerPvcShouldBeFound("recordStatus.contains=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerPvcList where recordStatus contains UPDATED_RECORD_STATUS
        defaultBLCustomerPvcShouldNotBeFound("recordStatus.contains=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByRecordStatusNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where recordStatus does not contain DEFAULT_RECORD_STATUS
        defaultBLCustomerPvcShouldNotBeFound("recordStatus.doesNotContain=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerPvcList where recordStatus does not contain UPDATED_RECORD_STATUS
        defaultBLCustomerPvcShouldBeFound("recordStatus.doesNotContain=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCoCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where coCode equals to DEFAULT_CO_CODE
        defaultBLCustomerPvcShouldBeFound("coCode.equals=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerPvcList where coCode equals to UPDATED_CO_CODE
        defaultBLCustomerPvcShouldNotBeFound("coCode.equals=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCoCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where coCode not equals to DEFAULT_CO_CODE
        defaultBLCustomerPvcShouldNotBeFound("coCode.notEquals=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerPvcList where coCode not equals to UPDATED_CO_CODE
        defaultBLCustomerPvcShouldBeFound("coCode.notEquals=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCoCodeIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where coCode in DEFAULT_CO_CODE or UPDATED_CO_CODE
        defaultBLCustomerPvcShouldBeFound("coCode.in=" + DEFAULT_CO_CODE + "," + UPDATED_CO_CODE);

        // Get all the bLCustomerPvcList where coCode equals to UPDATED_CO_CODE
        defaultBLCustomerPvcShouldNotBeFound("coCode.in=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCoCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where coCode is not null
        defaultBLCustomerPvcShouldBeFound("coCode.specified=true");

        // Get all the bLCustomerPvcList where coCode is null
        defaultBLCustomerPvcShouldNotBeFound("coCode.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCoCodeContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where coCode contains DEFAULT_CO_CODE
        defaultBLCustomerPvcShouldBeFound("coCode.contains=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerPvcList where coCode contains UPDATED_CO_CODE
        defaultBLCustomerPvcShouldNotBeFound("coCode.contains=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCoCodeNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where coCode does not contain DEFAULT_CO_CODE
        defaultBLCustomerPvcShouldNotBeFound("coCode.doesNotContain=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerPvcList where coCode does not contain UPDATED_CO_CODE
        defaultBLCustomerPvcShouldBeFound("coCode.doesNotContain=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBycreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy equals to DEFAULT_createdBy
        defaultBLCustomerPvcShouldBeFound("createdBy.equals=" + DEFAULT_createdBy);

        // Get all the bLCustomerPvcList where createdBy equals to UPDATED_createdBy
        defaultBLCustomerPvcShouldNotBeFound("createdBy.equals=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBycreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy not equals to DEFAULT_createdBy
        defaultBLCustomerPvcShouldNotBeFound("createdBy.notEquals=" + DEFAULT_createdBy);

        // Get all the bLCustomerPvcList where createdBy not equals to UPDATED_createdBy
        defaultBLCustomerPvcShouldBeFound("createdBy.notEquals=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBycreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy in DEFAULT_createdBy or UPDATED_createdBy
        defaultBLCustomerPvcShouldBeFound("createdBy.in=" + DEFAULT_createdBy + "," + UPDATED_createdBy);

        // Get all the bLCustomerPvcList where createdBy equals to UPDATED_createdBy
        defaultBLCustomerPvcShouldNotBeFound("createdBy.in=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBycreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy is not null
        defaultBLCustomerPvcShouldBeFound("createdBy.specified=true");

        // Get all the bLCustomerPvcList where createdBy is null
        defaultBLCustomerPvcShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBycreatedByContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy contains DEFAULT_createdBy
        defaultBLCustomerPvcShouldBeFound("createdBy.contains=" + DEFAULT_createdBy);

        // Get all the bLCustomerPvcList where createdBy contains UPDATED_createdBy
        defaultBLCustomerPvcShouldNotBeFound("createdBy.contains=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBycreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy does not contain DEFAULT_createdBy
        defaultBLCustomerPvcShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_createdBy);

        // Get all the bLCustomerPvcList where createdBy does not contain UPDATED_createdBy
        defaultBLCustomerPvcShouldBeFound("createdBy.doesNotContain=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated equals to DEFAULT_date_created
        defaultBLCustomerPvcShouldBeFound("dateCreated.equals=" + DEFAULT_date_created);

        // Get all the bLCustomerPvcList where dateCreated equals to UPDATED_date_created
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.equals=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated not equals to DEFAULT_date_created
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_date_created);

        // Get all the bLCustomerPvcList where dateCreated not equals to UPDATED_date_created
        defaultBLCustomerPvcShouldBeFound("dateCreated.notEquals=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated in DEFAULT_date_created or UPDATED_date_created
        defaultBLCustomerPvcShouldBeFound("dateCreated.in=" + DEFAULT_date_created + "," + UPDATED_date_created);

        // Get all the bLCustomerPvcList where dateCreated equals to UPDATED_date_created
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.in=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated is not null
        defaultBLCustomerPvcShouldBeFound("dateCreated.specified=true");

        // Get all the bLCustomerPvcList where dateCreated is null
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByauthoriseByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where authoriseBy equals to DEFAULT_AUTHORISE_BY
        defaultBLCustomerPvcShouldBeFound("authoriseBy.equals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerPvcList where authoriseBy equals to UPDATED_AUTHORISE_BY
        defaultBLCustomerPvcShouldNotBeFound("authoriseBy.equals=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByauthoriseByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where authoriseBy not equals to DEFAULT_AUTHORISE_BY
        defaultBLCustomerPvcShouldNotBeFound("authoriseBy.notEquals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerPvcList where authoriseBy not equals to UPDATED_AUTHORISE_BY
        defaultBLCustomerPvcShouldBeFound("authoriseBy.notEquals=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByauthoriseByIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where authoriseBy in DEFAULT_AUTHORISE_BY or UPDATED_AUTHORISE_BY
        defaultBLCustomerPvcShouldBeFound("authoriseBy.in=" + DEFAULT_AUTHORISE_BY + "," + UPDATED_AUTHORISE_BY);

        // Get all the bLCustomerPvcList where authoriseBy equals to UPDATED_AUTHORISE_BY
        defaultBLCustomerPvcShouldNotBeFound("authoriseBy.in=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByauthoriseByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where authoriseBy is not null
        defaultBLCustomerPvcShouldBeFound("authoriseBy.specified=true");

        // Get all the bLCustomerPvcList where authoriseBy is null
        defaultBLCustomerPvcShouldNotBeFound("authoriseBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByauthoriseByContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where authoriseBy contains DEFAULT_AUTHORISE_BY
        defaultBLCustomerPvcShouldBeFound("authoriseBy.contains=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerPvcList where authoriseBy contains UPDATED_AUTHORISE_BY
        defaultBLCustomerPvcShouldNotBeFound("authoriseBy.contains=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByauthoriseByNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where authoriseBy does not contain DEFAULT_AUTHORISE_BY
        defaultBLCustomerPvcShouldNotBeFound("authoriseBy.doesNotContain=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerPvcList where authoriseBy does not contain UPDATED_AUTHORISE_BY
        defaultBLCustomerPvcShouldBeFound("authoriseBy.doesNotContain=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateAuthorisequalToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateAuthorise equals to DEFAULT_DATE_AUTHORISE
        defaultBLCustomerPvcShouldBeFound("dateAuthorise.equals=" + DEFAULT_DATE_AUTHORISE);

        // Get all the bLCustomerPvcList where dateAuthorise equals to DEFAULT_DATE_AUTHORISE
        defaultBLCustomerPvcShouldNotBeFound("dateAuthorise.equals=" + DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateAuthoriseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateAuthorise not equals to DEFAULT_DATE_AUTHORISE
        defaultBLCustomerPvcShouldNotBeFound("dateAuthorise.notEquals=" + DEFAULT_DATE_AUTHORISE);

        // Get all the bLCustomerPvcList where dateAuthorise not equals to DEFAULT_DATE_AUTHORISE
        defaultBLCustomerPvcShouldBeFound("dateAuthorise.notEquals=" + DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateAuthoriseIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateAuthorise in DEFAULT_DATE_AUTHORISE or DEFAULT_DATE_AUTHORISE
        defaultBLCustomerPvcShouldBeFound("dateAuthorise.in=" + DEFAULT_DATE_AUTHORISE + "," + DEFAULT_DATE_AUTHORISE);

        // Get all the bLCustomerPvcList where dateAuthorise equals to DEFAULT_DATE_AUTHORISE
        defaultBLCustomerPvcShouldNotBeFound("dateAuthorise.in=" + DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsBydateAuthoriseIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateAuthorise is not null
        defaultBLCustomerPvcShouldBeFound("dateAuthorise.specified=true");

        // Get all the bLCustomerPvcList where dateAuthorise is null
        defaultBLCustomerPvcShouldNotBeFound("dateAuthorise.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy equals to DEFAULT_CREATED_BY
        defaultBLCustomerPvcShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerPvcList where createdBy equals to UPDATED_CREATED_BY
        defaultBLCustomerPvcShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy not equals to DEFAULT_CREATED_BY
        defaultBLCustomerPvcShouldNotBeFound("createdBy.notEquals=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerPvcList where createdBy not equals to UPDATED_CREATED_BY
        defaultBLCustomerPvcShouldBeFound("createdBy.notEquals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
        defaultBLCustomerPvcShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

        // Get all the bLCustomerPvcList where createdBy equals to UPDATED_CREATED_BY
        defaultBLCustomerPvcShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy is not null
        defaultBLCustomerPvcShouldBeFound("createdBy.specified=true");

        // Get all the bLCustomerPvcList where createdBy is null
        defaultBLCustomerPvcShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy contains DEFAULT_CREATED_BY
        defaultBLCustomerPvcShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerPvcList where createdBy contains UPDATED_CREATED_BY
        defaultBLCustomerPvcShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where createdBy does not contain DEFAULT_CREATED_BY
        defaultBLCustomerPvcShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerPvcList where createdBy does not contain UPDATED_CREATED_BY
        defaultBLCustomerPvcShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated equals to DEFAULT_DATE_CREATED
        defaultBLCustomerPvcShouldBeFound("dateCreated.equals=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerPvcList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.equals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated not equals to DEFAULT_DATE_CREATED
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerPvcList where dateCreated not equals to UPDATED_DATE_CREATED
        defaultBLCustomerPvcShouldBeFound("dateCreated.notEquals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated in DEFAULT_DATE_CREATED or UPDATED_DATE_CREATED
        defaultBLCustomerPvcShouldBeFound("dateCreated.in=" + DEFAULT_DATE_CREATED + "," + UPDATED_DATE_CREATED);

        // Get all the bLCustomerPvcList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.in=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated is not null
        defaultBLCustomerPvcShouldBeFound("dateCreated.specified=true");

        // Get all the bLCustomerPvcList where dateCreated is null
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateCreatedContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated contains DEFAULT_DATE_CREATED
        defaultBLCustomerPvcShouldBeFound("dateCreated.contains=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerPvcList where dateCreated contains UPDATED_DATE_CREATED
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.contains=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomerPvcsByDateCreatedNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        // Get all the bLCustomerPvcList where dateCreated does not contain DEFAULT_DATE_CREATED
        defaultBLCustomerPvcShouldNotBeFound("dateCreated.doesNotContain=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerPvcList where dateCreated does not contain UPDATED_DATE_CREATED
        defaultBLCustomerPvcShouldBeFound("dateCreated.doesNotContain=" + UPDATED_DATE_CREATED);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBLCustomerPvcShouldBeFound(String filter) throws Exception {
        restBLCustomerPvcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLCustomerPvc.getId().intValue())))
            .andExpect(jsonPath("$.[*].cif").value(hasItem(DEFAULT_CIF)))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].dateOfBirth").value(hasItem(DEFAULT_DATE_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].legalId").value(hasItem(DEFAULT_LEGAL_ID)))
            .andExpect(jsonPath("$.[*].legalType").value(hasItem(DEFAULT_LEGAL_TYPE)))
            .andExpect(jsonPath("$.[*].branch").value(hasItem(DEFAULT_BRANCH)))
            .andExpect(jsonPath("$.[*].blCustomerId").value(hasItem(DEFAULT_BL_CUSTOMER_ID)))
            .andExpect(jsonPath("$.[*].nameBl").value(hasItem(DEFAULT_NAME_BL)))
            .andExpect(jsonPath("$.[*].dateOfBirthBl").value(hasItem(DEFAULT_DATE_OF_BIRTH_BL)))
            .andExpect(jsonPath("$.[*].legalIdTypeBl").value(hasItem(DEFAULT_LEGAL_ID_TYPE_BL)))
            .andExpect(jsonPath("$.[*].legalIdNumber").value(hasItem(DEFAULT_LEGAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].matchAttr").value(hasItem(DEFAULT_MATCH_ATTR)))
            .andExpect(jsonPath("$.[*].valueAttr").value(hasItem(DEFAULT_VALUE_ATTR)))
            .andExpect(jsonPath("$.[*].weightAttr").value(hasItem(DEFAULT_WEIGHT_ATTR)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].coCode").value(hasItem(DEFAULT_CO_CODE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_createdBy)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_date_created.toString())))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(DEFAULT_DATE_AUTHORISE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)));

        // Check, that the count call also returns 1
        restBLCustomerPvcMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBLCustomerPvcShouldNotBeFound(String filter) throws Exception {
        restBLCustomerPvcMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBLCustomerPvcMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBLCustomerPvc() throws Exception {
        // Get the bLCustomerPvc
        restBLCustomerPvcMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBLCustomerPvc() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();

        // Update the bLCustomerPvc
        BLCustomerPvc updatedBLCustomerPvc = bLCustomerPvcRepository.findById(bLCustomerPvc.getId()).get();
        // Disconnect from session so that the updates on updatedBLCustomerPvc are not directly saved in db
        em.detach(updatedBLCustomerPvc);
        updatedBLCustomerPvc
            .cif(UPDATED_CIF)
            .fullName(UPDATED_FULL_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .legalId(UPDATED_LEGAL_ID)
            .legalType(UPDATED_LEGAL_TYPE)
            .branch(UPDATED_BRANCH)
            .blCustomerId(UPDATED_BL_CUSTOMER_ID)
            .nameBl(UPDATED_NAME_BL)
            .dateOfBirthBl(UPDATED_DATE_OF_BIRTH_BL)
            .legalIdTypeBl(UPDATED_LEGAL_ID_TYPE_BL)
            .legalIdNumber(UPDATED_LEGAL_ID_NUMBER)
            .matchAttr(UPDATED_MATCH_ATTR)
            .valueAttr(UPDATED_VALUE_ATTR)
            .weightAttr(UPDATED_WEIGHT_ATTR)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .recordStatus(UPDATED_RECORD_STATUS)
            .coCode(UPDATED_CO_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE);

        restBLCustomerPvcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBLCustomerPvc.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBLCustomerPvc))
            )
            .andExpect(status().isOk());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
        BLCustomerPvc testBLCustomerPvc = bLCustomerPvcList.get(bLCustomerPvcList.size() - 1);
        assertThat(testBLCustomerPvc.getCif()).isEqualTo(UPDATED_CIF);
        assertThat(testBLCustomerPvc.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testBLCustomerPvc.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testBLCustomerPvc.getLegalId()).isEqualTo(UPDATED_LEGAL_ID);
        assertThat(testBLCustomerPvc.getLegalType()).isEqualTo(UPDATED_LEGAL_TYPE);
        assertThat(testBLCustomerPvc.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testBLCustomerPvc.getBlCustomerId()).isEqualTo(UPDATED_BL_CUSTOMER_ID);
        assertThat(testBLCustomerPvc.getNameBl()).isEqualTo(UPDATED_NAME_BL);
        assertThat(testBLCustomerPvc.getDateOfBirthBl()).isEqualTo(UPDATED_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomerPvc.getLegalIdTypeBl()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL);
        assertThat(testBLCustomerPvc.getLegalIdNumber()).isEqualTo(UPDATED_LEGAL_ID_NUMBER);
        assertThat(testBLCustomerPvc.getMatchAttr()).isEqualTo(UPDATED_MATCH_ATTR);
        assertThat(testBLCustomerPvc.getValueAttr()).isEqualTo(UPDATED_VALUE_ATTR);
        assertThat(testBLCustomerPvc.getWeightAttr()).isEqualTo(UPDATED_WEIGHT_ATTR);
        assertThat(testBLCustomerPvc.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testBLCustomerPvc.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBLCustomerPvc.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBLCustomerPvc.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLCustomerPvc.getCoCode()).isEqualTo(UPDATED_CO_CODE);
        assertThat(testBLCustomerPvc.getCreatedBy()).isEqualTo(UPDATED_createdBy);
        assertThat(testBLCustomerPvc.getDateCreated()).isEqualTo(UPDATED_date_created);
        assertThat(testBLCustomerPvc.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCustomerPvc.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
        assertThat(testBLCustomerPvc.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCustomerPvc.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void putNonExistingBLCustomerPvc() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();
        bLCustomerPvc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLCustomerPvcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bLCustomerPvc.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBLCustomerPvc() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();
        bLCustomerPvc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerPvcMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBLCustomerPvc() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();
        bLCustomerPvc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerPvcMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBLCustomerPvcWithPatch() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();

        // Update the bLCustomerPvc using partial update
        BLCustomerPvc partialUpdatedBLCustomerPvc = new BLCustomerPvc();
        partialUpdatedBLCustomerPvc.setId(bLCustomerPvc.getId());

        partialUpdatedBLCustomerPvc
            .cif(UPDATED_CIF)
            .legalType(UPDATED_LEGAL_TYPE)
            .branch(UPDATED_BRANCH)
            .legalIdTypeBl(UPDATED_LEGAL_ID_TYPE_BL)
            .legalIdNumber(UPDATED_LEGAL_ID_NUMBER)
            .valueAttr(UPDATED_VALUE_ATTR)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .recordStatus(UPDATED_RECORD_STATUS)
            .createdBy(UPDATED_createdBy)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE)
            .createdBy(UPDATED_CREATED_BY);

        restBLCustomerPvcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLCustomerPvc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLCustomerPvc))
            )
            .andExpect(status().isOk());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
        BLCustomerPvc testBLCustomerPvc = bLCustomerPvcList.get(bLCustomerPvcList.size() - 1);
        assertThat(testBLCustomerPvc.getCif()).isEqualTo(UPDATED_CIF);
        assertThat(testBLCustomerPvc.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testBLCustomerPvc.getDateOfBirth()).isEqualTo(DEFAULT_DATE_OF_BIRTH);
        assertThat(testBLCustomerPvc.getLegalId()).isEqualTo(DEFAULT_LEGAL_ID);
        assertThat(testBLCustomerPvc.getLegalType()).isEqualTo(UPDATED_LEGAL_TYPE);
        assertThat(testBLCustomerPvc.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testBLCustomerPvc.getBlCustomerId()).isEqualTo(DEFAULT_BL_CUSTOMER_ID);
        assertThat(testBLCustomerPvc.getNameBl()).isEqualTo(DEFAULT_NAME_BL);
        assertThat(testBLCustomerPvc.getDateOfBirthBl()).isEqualTo(DEFAULT_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomerPvc.getLegalIdTypeBl()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL);
        assertThat(testBLCustomerPvc.getLegalIdNumber()).isEqualTo(UPDATED_LEGAL_ID_NUMBER);
        assertThat(testBLCustomerPvc.getMatchAttr()).isEqualTo(DEFAULT_MATCH_ATTR);
        assertThat(testBLCustomerPvc.getValueAttr()).isEqualTo(UPDATED_VALUE_ATTR);
        assertThat(testBLCustomerPvc.getWeightAttr()).isEqualTo(DEFAULT_WEIGHT_ATTR);
        assertThat(testBLCustomerPvc.getScore()).isEqualTo(DEFAULT_SCORE);
        assertThat(testBLCustomerPvc.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBLCustomerPvc.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBLCustomerPvc.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLCustomerPvc.getCoCode()).isEqualTo(DEFAULT_CO_CODE);
        assertThat(testBLCustomerPvc.getCreatedBy()).isEqualTo(UPDATED_createdBy);
        assertThat(testBLCustomerPvc.getDateCreated()).isEqualTo(DEFAULT_date_created);
        assertThat(testBLCustomerPvc.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCustomerPvc.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
        assertThat(testBLCustomerPvc.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCustomerPvc.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
    }

    @Test
    @Transactional
    void fullUpdateBLCustomerPvcWithPatch() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();

        // Update the bLCustomerPvc using partial update
        BLCustomerPvc partialUpdatedBLCustomerPvc = new BLCustomerPvc();
        partialUpdatedBLCustomerPvc.setId(bLCustomerPvc.getId());

        partialUpdatedBLCustomerPvc
            .cif(UPDATED_CIF)
            .fullName(UPDATED_FULL_NAME)
            .dateOfBirth(UPDATED_DATE_OF_BIRTH)
            .legalId(UPDATED_LEGAL_ID)
            .legalType(UPDATED_LEGAL_TYPE)
            .branch(UPDATED_BRANCH)
            .blCustomerId(UPDATED_BL_CUSTOMER_ID)
            .nameBl(UPDATED_NAME_BL)
            .dateOfBirthBl(UPDATED_DATE_OF_BIRTH_BL)
            .legalIdTypeBl(UPDATED_LEGAL_ID_TYPE_BL)
            .legalIdNumber(UPDATED_LEGAL_ID_NUMBER)
            .matchAttr(UPDATED_MATCH_ATTR)
            .valueAttr(UPDATED_VALUE_ATTR)
            .weightAttr(UPDATED_WEIGHT_ATTR)
            .score(UPDATED_SCORE)
            .status(UPDATED_STATUS)
            .remark(UPDATED_REMARK)
            .recordStatus(UPDATED_RECORD_STATUS)
            .coCode(UPDATED_CO_CODE)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED);

        restBLCustomerPvcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLCustomerPvc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLCustomerPvc))
            )
            .andExpect(status().isOk());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
        BLCustomerPvc testBLCustomerPvc = bLCustomerPvcList.get(bLCustomerPvcList.size() - 1);
        assertThat(testBLCustomerPvc.getCif()).isEqualTo(UPDATED_CIF);
        assertThat(testBLCustomerPvc.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testBLCustomerPvc.getDateOfBirth()).isEqualTo(UPDATED_DATE_OF_BIRTH);
        assertThat(testBLCustomerPvc.getLegalId()).isEqualTo(UPDATED_LEGAL_ID);
        assertThat(testBLCustomerPvc.getLegalType()).isEqualTo(UPDATED_LEGAL_TYPE);
        assertThat(testBLCustomerPvc.getBranch()).isEqualTo(UPDATED_BRANCH);
        assertThat(testBLCustomerPvc.getBlCustomerId()).isEqualTo(UPDATED_BL_CUSTOMER_ID);
        assertThat(testBLCustomerPvc.getNameBl()).isEqualTo(UPDATED_NAME_BL);
        assertThat(testBLCustomerPvc.getDateOfBirthBl()).isEqualTo(UPDATED_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomerPvc.getLegalIdTypeBl()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL);
        assertThat(testBLCustomerPvc.getLegalIdNumber()).isEqualTo(UPDATED_LEGAL_ID_NUMBER);
        assertThat(testBLCustomerPvc.getMatchAttr()).isEqualTo(UPDATED_MATCH_ATTR);
        assertThat(testBLCustomerPvc.getValueAttr()).isEqualTo(UPDATED_VALUE_ATTR);
        assertThat(testBLCustomerPvc.getWeightAttr()).isEqualTo(UPDATED_WEIGHT_ATTR);
        assertThat(testBLCustomerPvc.getScore()).isEqualTo(UPDATED_SCORE);
        assertThat(testBLCustomerPvc.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testBLCustomerPvc.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testBLCustomerPvc.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLCustomerPvc.getCoCode()).isEqualTo(UPDATED_CO_CODE);
        assertThat(testBLCustomerPvc.getCreatedBy()).isEqualTo(UPDATED_createdBy);
        assertThat(testBLCustomerPvc.getDateCreated()).isEqualTo(UPDATED_date_created);
        assertThat(testBLCustomerPvc.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCustomerPvc.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
        assertThat(testBLCustomerPvc.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCustomerPvc.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void patchNonExistingBLCustomerPvc() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();
        bLCustomerPvc.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLCustomerPvcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bLCustomerPvc.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBLCustomerPvc() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();
        bLCustomerPvc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerPvcMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBLCustomerPvc() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerPvcRepository.findAll().size();
        bLCustomerPvc.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerPvcMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomerPvc))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLCustomerPvc in the database
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBLCustomerPvc() throws Exception {
        // Initialize the database
        bLCustomerPvcRepository.saveAndFlush(bLCustomerPvc);

        int databaseSizeBeforeDelete = bLCustomerPvcRepository.findAll().size();

        // Delete the bLCustomerPvc
        restBLCustomerPvcMockMvc
            .perform(delete(ENTITY_API_URL_ID, bLCustomerPvc.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BLCustomerPvc> bLCustomerPvcList = bLCustomerPvcRepository.findAll();
        assertThat(bLCustomerPvcList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
