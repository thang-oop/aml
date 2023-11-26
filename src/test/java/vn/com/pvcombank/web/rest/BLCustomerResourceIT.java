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
import vn.com.pvcombank.domain.BLCustomer;
import vn.com.pvcombank.repository.BLCustomerRepository;

/**
 * Integration tests for the {@link BLCustomerResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BLCustomerResourceIT {

    private static final String DEFAULT_FULL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FULL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NAME_1 = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NAME_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NAME_2 = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NAME_2 = "BBBBBBBBBB";

    private static final String DEFAULT_OTHER_NAME_3 = "AAAAAAAAAA";
    private static final String UPDATED_OTHER_NAME_3 = "BBBBBBBBBB";

    private static final String DEFAULT_POSITION_BL = "AAAAAAAAAA";
    private static final String UPDATED_POSITION_BL = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_OF_BIRTH_BL = "AAAAAAAAAA";
    private static final String UPDATED_DATE_OF_BIRTH_BL = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_BL_1 = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_BL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_BL_2 = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_BL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID_TYPE_BL_1 = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID_TYPE_BL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID_NUMBER_1 = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID_NUMBER_1 = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID_TYPE_BL_2 = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID_TYPE_BL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_ID_NUMBER_2 = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_ID_NUMBER_2 = "BBBBBBBBBB";

    private static final Instant DEFAULT_OTHER_INF_LEGAL_1 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OTHER_INF_LEGAL_1 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_OTHER_INF_LEGAL_2 = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OTHER_INF_LEGAL_2 = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_ADDRESS_BL_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_BL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_BL_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_BL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_NOW_BL_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_NOW_BL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS_NOW_BL_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS_NOW_BL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_BL = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_BL = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    private static final String DEFAULT_RECORD_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_RECORD_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_UPLOAD_FILE_ID = "AAAAAAAAAA";
    private static final String UPDATED_UPLOAD_FILE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CO_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CO_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_createdBy = "AAAAAAAAAA";
    private static final String UPDATED_createdBy = "BBBBBBBBBB";

    private static final String DEFAULT_date_created = "";
    private static final String UPDATED_date_created = "";

    private static final String DEFAULT_AUTHORISE_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORISE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_AUTHORISE = "";
    private static final String UPDATED_DATE_AUTHORISE = "";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bl-customers";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BLCustomerRepository bLCustomerRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBLCustomerMockMvc;

    private BLCustomer bLCustomer;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLCustomer createEntity(EntityManager em) {
        BLCustomer bLCustomer = new BLCustomer()
            .fullName(DEFAULT_FULL_NAME)
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .otherName1(DEFAULT_OTHER_NAME_1)
            .otherName2(DEFAULT_OTHER_NAME_2)
            .otherName3(DEFAULT_OTHER_NAME_3)
            .positionBl(DEFAULT_POSITION_BL)
            .dateOfBirthBl(DEFAULT_DATE_OF_BIRTH_BL)
            .countryBl1(DEFAULT_COUNTRY_BL_1)
            .countryBl2(DEFAULT_COUNTRY_BL_2)
            .legalIdTypeBl1(DEFAULT_LEGAL_ID_TYPE_BL_1)
            .legalIdNumber1(DEFAULT_LEGAL_ID_NUMBER_1)
            .legalIdTypeBl2(DEFAULT_LEGAL_ID_TYPE_BL_2)
            .legalIdNumber2(DEFAULT_LEGAL_ID_NUMBER_2)
            .otherInfLegal1(DEFAULT_OTHER_INF_LEGAL_1)
            .otherInfLegal2(DEFAULT_OTHER_INF_LEGAL_2)
            .addressBl1(DEFAULT_ADDRESS_BL_1)
            .addressBl2(DEFAULT_ADDRESS_BL_2)
            .addressNowBl1(DEFAULT_ADDRESS_NOW_BL_1)
            .addressNowBl2(DEFAULT_ADDRESS_NOW_BL_2)
            .typeBl(DEFAULT_TYPE_BL)
            .source(DEFAULT_SOURCE)
            .recordStatus(DEFAULT_RECORD_STATUS)
            .uploadFileId(DEFAULT_UPLOAD_FILE_ID)
            .coCode(DEFAULT_CO_CODE)
            .createdBy(DEFAULT_CREATED_BY)
            .dateCreated(DEFAULT_DATE_CREATED)
            .authoriseBy(DEFAULT_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);
        return bLCustomer;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLCustomer createUpdatedEntity(EntityManager em) {
        BLCustomer bLCustomer = new BLCustomer()
            .fullName(UPDATED_FULL_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .otherName1(UPDATED_OTHER_NAME_1)
            .otherName2(UPDATED_OTHER_NAME_2)
            .otherName3(UPDATED_OTHER_NAME_3)
            .positionBl(UPDATED_POSITION_BL)
            .dateOfBirthBl(UPDATED_DATE_OF_BIRTH_BL)
            .countryBl1(UPDATED_COUNTRY_BL_1)
            .countryBl2(UPDATED_COUNTRY_BL_2)
            .legalIdTypeBl1(UPDATED_LEGAL_ID_TYPE_BL_1)
            .legalIdNumber1(UPDATED_LEGAL_ID_NUMBER_1)
            .legalIdTypeBl2(UPDATED_LEGAL_ID_TYPE_BL_2)
            .legalIdNumber2(UPDATED_LEGAL_ID_NUMBER_2)
            .otherInfLegal1(UPDATED_OTHER_INF_LEGAL_1)
            .otherInfLegal2(UPDATED_OTHER_INF_LEGAL_2)
            .addressBl1(UPDATED_ADDRESS_BL_1)
            .addressBl2(UPDATED_ADDRESS_BL_2)
            .addressNowBl1(UPDATED_ADDRESS_NOW_BL_1)
            .addressNowBl2(UPDATED_ADDRESS_NOW_BL_2)
            .typeBl(UPDATED_TYPE_BL)
            .source(UPDATED_SOURCE)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadFileId(UPDATED_UPLOAD_FILE_ID)
            .coCode(UPDATED_CO_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);
        return bLCustomer;
    }

    @BeforeEach
    public void initTest() {
        bLCustomer = createEntity(em);
    }

    @Test
    @Transactional
    void createBLCustomer() throws Exception {
        int databaseSizeBeforeCreate = bLCustomerRepository.findAll().size();
        // Create the BLCustomer
        restBLCustomerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isCreated());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeCreate + 1);
        BLCustomer testBLCustomer = bLCustomerList.get(bLCustomerList.size() - 1);
        assertThat(testBLCustomer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testBLCustomer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testBLCustomer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testBLCustomer.getOtherName1()).isEqualTo(DEFAULT_OTHER_NAME_1);
        assertThat(testBLCustomer.getOtherName2()).isEqualTo(DEFAULT_OTHER_NAME_2);
        assertThat(testBLCustomer.getOtherName3()).isEqualTo(DEFAULT_OTHER_NAME_3);
        assertThat(testBLCustomer.getPositionBl()).isEqualTo(DEFAULT_POSITION_BL);
        assertThat(testBLCustomer.getDateOfBirthBl()).isEqualTo(DEFAULT_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomer.getCountryBl1()).isEqualTo(DEFAULT_COUNTRY_BL_1);
        assertThat(testBLCustomer.getCountryBl2()).isEqualTo(DEFAULT_COUNTRY_BL_2);
        assertThat(testBLCustomer.getLegalIdTypeBl1()).isEqualTo(DEFAULT_LEGAL_ID_TYPE_BL_1);
        assertThat(testBLCustomer.getLegalIdNumber1()).isEqualTo(DEFAULT_LEGAL_ID_NUMBER_1);
        assertThat(testBLCustomer.getLegalIdTypeBl2()).isEqualTo(DEFAULT_LEGAL_ID_TYPE_BL_2);
        assertThat(testBLCustomer.getLegalIdNumber2()).isEqualTo(DEFAULT_LEGAL_ID_NUMBER_2);
        assertThat(testBLCustomer.getOtherInfLegal1()).isEqualTo(DEFAULT_OTHER_INF_LEGAL_1);
        assertThat(testBLCustomer.getOtherInfLegal2()).isEqualTo(DEFAULT_OTHER_INF_LEGAL_2);
        assertThat(testBLCustomer.getAddressBl1()).isEqualTo(DEFAULT_ADDRESS_BL_1);
        assertThat(testBLCustomer.getAddressBl2()).isEqualTo(DEFAULT_ADDRESS_BL_2);
        assertThat(testBLCustomer.getAddressNowBl1()).isEqualTo(DEFAULT_ADDRESS_NOW_BL_1);
        assertThat(testBLCustomer.getAddressNowBl2()).isEqualTo(DEFAULT_ADDRESS_NOW_BL_2);
        assertThat(testBLCustomer.getTypeBl()).isEqualTo(DEFAULT_TYPE_BL);
        assertThat(testBLCustomer.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testBLCustomer.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testBLCustomer.getUploadFileId()).isEqualTo(DEFAULT_UPLOAD_FILE_ID);
        assertThat(testBLCustomer.getCoCode()).isEqualTo(DEFAULT_CO_CODE);
        assertThat(testBLCustomer.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBLCustomer.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testBLCustomer.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLCustomer.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void createBLCustomerWithExistingId() throws Exception {
        // Create the BLCustomer with an existing ID
        bLCustomer.setId(1L);

        int databaseSizeBeforeCreate = bLCustomerRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBLCustomerMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBLCustomers() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList
        restBLCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLCustomer.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].otherName1").value(hasItem(DEFAULT_OTHER_NAME_1)))
            .andExpect(jsonPath("$.[*].otherName2").value(hasItem(DEFAULT_OTHER_NAME_2)))
            .andExpect(jsonPath("$.[*].otherName3").value(hasItem(DEFAULT_OTHER_NAME_3)))
            .andExpect(jsonPath("$.[*].positionBl").value(hasItem(DEFAULT_POSITION_BL)))
            .andExpect(jsonPath("$.[*].dateOfBirthBl").value(hasItem(DEFAULT_DATE_OF_BIRTH_BL)))
            .andExpect(jsonPath("$.[*].countryBl1").value(hasItem(DEFAULT_COUNTRY_BL_1)))
            .andExpect(jsonPath("$.[*].countryBl2").value(hasItem(DEFAULT_COUNTRY_BL_2)))
            .andExpect(jsonPath("$.[*].legalIdTypeBl1").value(hasItem(DEFAULT_LEGAL_ID_TYPE_BL_1)))
            .andExpect(jsonPath("$.[*].legalIdNumber1").value(hasItem(DEFAULT_LEGAL_ID_NUMBER_1)))
            .andExpect(jsonPath("$.[*].legalIdTypeBl2").value(hasItem(DEFAULT_LEGAL_ID_TYPE_BL_2)))
            .andExpect(jsonPath("$.[*].legalIdNumber2").value(hasItem(DEFAULT_LEGAL_ID_NUMBER_2)))
            .andExpect(jsonPath("$.[*].otherInfLegal1").value(hasItem(DEFAULT_OTHER_INF_LEGAL_1.toString())))
            .andExpect(jsonPath("$.[*].otherInfLegal2").value(hasItem(DEFAULT_OTHER_INF_LEGAL_2.toString())))
            .andExpect(jsonPath("$.[*].addressBl1").value(hasItem(DEFAULT_ADDRESS_BL_1)))
            .andExpect(jsonPath("$.[*].addressBl2").value(hasItem(DEFAULT_ADDRESS_BL_2)))
            .andExpect(jsonPath("$.[*].addressNowBl1").value(hasItem(DEFAULT_ADDRESS_NOW_BL_1)))
            .andExpect(jsonPath("$.[*].addressNowBl2").value(hasItem(DEFAULT_ADDRESS_NOW_BL_2)))
            .andExpect(jsonPath("$.[*].typeBl").value(hasItem(DEFAULT_TYPE_BL)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].uploadFileId").value(hasItem(DEFAULT_UPLOAD_FILE_ID)))
            .andExpect(jsonPath("$.[*].coCode").value(hasItem(DEFAULT_CO_CODE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(UPDATED_DATE_AUTHORISE.toString())));
    }

    @Test
    @Transactional
    void getBLCustomer() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get the bLCustomer
        restBLCustomerMockMvc
            .perform(get(ENTITY_API_URL_ID, bLCustomer.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bLCustomer.getId().intValue()))
            .andExpect(jsonPath("$.fullName").value(DEFAULT_FULL_NAME))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.otherName1").value(DEFAULT_OTHER_NAME_1))
            .andExpect(jsonPath("$.otherName2").value(DEFAULT_OTHER_NAME_2))
            .andExpect(jsonPath("$.otherName3").value(DEFAULT_OTHER_NAME_3))
            .andExpect(jsonPath("$.positionBl").value(DEFAULT_POSITION_BL))
            .andExpect(jsonPath("$.dateOfBirthBl").value(DEFAULT_DATE_OF_BIRTH_BL))
            .andExpect(jsonPath("$.countryBl1").value(DEFAULT_COUNTRY_BL_1))
            .andExpect(jsonPath("$.countryBl2").value(DEFAULT_COUNTRY_BL_2))
            .andExpect(jsonPath("$.legalIdTypeBl1").value(DEFAULT_LEGAL_ID_TYPE_BL_1))
            .andExpect(jsonPath("$.legalIdNumber1").value(DEFAULT_LEGAL_ID_NUMBER_1))
            .andExpect(jsonPath("$.legalIdTypeBl2").value(DEFAULT_LEGAL_ID_TYPE_BL_2))
            .andExpect(jsonPath("$.legalIdNumber2").value(DEFAULT_LEGAL_ID_NUMBER_2))
            .andExpect(jsonPath("$.otherInfLegal1").value(DEFAULT_OTHER_INF_LEGAL_1.toString()))
            .andExpect(jsonPath("$.otherInfLegal2").value(DEFAULT_OTHER_INF_LEGAL_2.toString()))
            .andExpect(jsonPath("$.addressBl1").value(DEFAULT_ADDRESS_BL_1))
            .andExpect(jsonPath("$.addressBl2").value(DEFAULT_ADDRESS_BL_2))
            .andExpect(jsonPath("$.addressNowBl1").value(DEFAULT_ADDRESS_NOW_BL_1))
            .andExpect(jsonPath("$.addressNowBl2").value(DEFAULT_ADDRESS_NOW_BL_2))
            .andExpect(jsonPath("$.typeBl").value(DEFAULT_TYPE_BL))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE))
            .andExpect(jsonPath("$.recordStatus").value(DEFAULT_RECORD_STATUS))
            .andExpect(jsonPath("$.uploadFileId").value(DEFAULT_UPLOAD_FILE_ID))
            .andExpect(jsonPath("$.coCode").value(DEFAULT_CO_CODE))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED))
            .andExpect(jsonPath("$.authoriseBy").value(DEFAULT_AUTHORISE_BY))
            .andExpect(jsonPath("$.dateAuthorise").value(UPDATED_DATE_AUTHORISE.toString()));
    }

    @Test
    @Transactional
    void getBLCustomersByIdFiltering() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        Long id = bLCustomer.getId();

        defaultBLCustomerShouldBeFound("id.equals=" + id);
        defaultBLCustomerShouldNotBeFound("id.notEquals=" + id);

        defaultBLCustomerShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBLCustomerShouldNotBeFound("id.greaterThan=" + id);

        defaultBLCustomerShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBLCustomerShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFullNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where fullName equals to DEFAULT_FULL_NAME
        defaultBLCustomerShouldBeFound("fullName.equals=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerList where fullName equals to UPDATED_FULL_NAME
        defaultBLCustomerShouldNotBeFound("fullName.equals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFullNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where fullName not equals to DEFAULT_FULL_NAME
        defaultBLCustomerShouldNotBeFound("fullName.notEquals=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerList where fullName not equals to UPDATED_FULL_NAME
        defaultBLCustomerShouldBeFound("fullName.notEquals=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFullNameIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where fullName in DEFAULT_FULL_NAME or UPDATED_FULL_NAME
        defaultBLCustomerShouldBeFound("fullName.in=" + DEFAULT_FULL_NAME + "," + UPDATED_FULL_NAME);

        // Get all the bLCustomerList where fullName equals to UPDATED_FULL_NAME
        defaultBLCustomerShouldNotBeFound("fullName.in=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFullNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where fullName is not null
        defaultBLCustomerShouldBeFound("fullName.specified=true");

        // Get all the bLCustomerList where fullName is null
        defaultBLCustomerShouldNotBeFound("fullName.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByFullNameContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where fullName contains DEFAULT_FULL_NAME
        defaultBLCustomerShouldBeFound("fullName.contains=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerList where fullName contains UPDATED_FULL_NAME
        defaultBLCustomerShouldNotBeFound("fullName.contains=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFullNameNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where fullName does not contain DEFAULT_FULL_NAME
        defaultBLCustomerShouldNotBeFound("fullName.doesNotContain=" + DEFAULT_FULL_NAME);

        // Get all the bLCustomerList where fullName does not contain UPDATED_FULL_NAME
        defaultBLCustomerShouldBeFound("fullName.doesNotContain=" + UPDATED_FULL_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFirstNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where firstName equals to DEFAULT_FIRST_NAME
        defaultBLCustomerShouldBeFound("firstName.equals=" + DEFAULT_FIRST_NAME);

        // Get all the bLCustomerList where firstName equals to UPDATED_FIRST_NAME
        defaultBLCustomerShouldNotBeFound("firstName.equals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFirstNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where firstName not equals to DEFAULT_FIRST_NAME
        defaultBLCustomerShouldNotBeFound("firstName.notEquals=" + DEFAULT_FIRST_NAME);

        // Get all the bLCustomerList where firstName not equals to UPDATED_FIRST_NAME
        defaultBLCustomerShouldBeFound("firstName.notEquals=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFirstNameIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where firstName in DEFAULT_FIRST_NAME or UPDATED_FIRST_NAME
        defaultBLCustomerShouldBeFound("firstName.in=" + DEFAULT_FIRST_NAME + "," + UPDATED_FIRST_NAME);

        // Get all the bLCustomerList where firstName equals to UPDATED_FIRST_NAME
        defaultBLCustomerShouldNotBeFound("firstName.in=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFirstNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where firstName is not null
        defaultBLCustomerShouldBeFound("firstName.specified=true");

        // Get all the bLCustomerList where firstName is null
        defaultBLCustomerShouldNotBeFound("firstName.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByFirstNameContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where firstName contains DEFAULT_FIRST_NAME
        defaultBLCustomerShouldBeFound("firstName.contains=" + DEFAULT_FIRST_NAME);

        // Get all the bLCustomerList where firstName contains UPDATED_FIRST_NAME
        defaultBLCustomerShouldNotBeFound("firstName.contains=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByFirstNameNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where firstName does not contain DEFAULT_FIRST_NAME
        defaultBLCustomerShouldNotBeFound("firstName.doesNotContain=" + DEFAULT_FIRST_NAME);

        // Get all the bLCustomerList where firstName does not contain UPDATED_FIRST_NAME
        defaultBLCustomerShouldBeFound("firstName.doesNotContain=" + UPDATED_FIRST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLastNameIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where lastName equals to DEFAULT_LAST_NAME
        defaultBLCustomerShouldBeFound("lastName.equals=" + DEFAULT_LAST_NAME);

        // Get all the bLCustomerList where lastName equals to UPDATED_LAST_NAME
        defaultBLCustomerShouldNotBeFound("lastName.equals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLastNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where lastName not equals to DEFAULT_LAST_NAME
        defaultBLCustomerShouldNotBeFound("lastName.notEquals=" + DEFAULT_LAST_NAME);

        // Get all the bLCustomerList where lastName not equals to UPDATED_LAST_NAME
        defaultBLCustomerShouldBeFound("lastName.notEquals=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLastNameIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where lastName in DEFAULT_LAST_NAME or UPDATED_LAST_NAME
        defaultBLCustomerShouldBeFound("lastName.in=" + DEFAULT_LAST_NAME + "," + UPDATED_LAST_NAME);

        // Get all the bLCustomerList where lastName equals to UPDATED_LAST_NAME
        defaultBLCustomerShouldNotBeFound("lastName.in=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLastNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where lastName is not null
        defaultBLCustomerShouldBeFound("lastName.specified=true");

        // Get all the bLCustomerList where lastName is null
        defaultBLCustomerShouldNotBeFound("lastName.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByLastNameContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where lastName contains DEFAULT_LAST_NAME
        defaultBLCustomerShouldBeFound("lastName.contains=" + DEFAULT_LAST_NAME);

        // Get all the bLCustomerList where lastName contains UPDATED_LAST_NAME
        defaultBLCustomerShouldNotBeFound("lastName.contains=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLastNameNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where lastName does not contain DEFAULT_LAST_NAME
        defaultBLCustomerShouldNotBeFound("lastName.doesNotContain=" + DEFAULT_LAST_NAME);

        // Get all the bLCustomerList where lastName does not contain UPDATED_LAST_NAME
        defaultBLCustomerShouldBeFound("lastName.doesNotContain=" + UPDATED_LAST_NAME);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName1IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName1 equals to DEFAULT_OTHER_NAME_1
        defaultBLCustomerShouldBeFound("otherName1.equals=" + DEFAULT_OTHER_NAME_1);

        // Get all the bLCustomerList where otherName1 equals to UPDATED_OTHER_NAME_1
        defaultBLCustomerShouldNotBeFound("otherName1.equals=" + UPDATED_OTHER_NAME_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName1 not equals to DEFAULT_OTHER_NAME_1
        defaultBLCustomerShouldNotBeFound("otherName1.notEquals=" + DEFAULT_OTHER_NAME_1);

        // Get all the bLCustomerList where otherName1 not equals to UPDATED_OTHER_NAME_1
        defaultBLCustomerShouldBeFound("otherName1.notEquals=" + UPDATED_OTHER_NAME_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName1IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName1 in DEFAULT_OTHER_NAME_1 or UPDATED_OTHER_NAME_1
        defaultBLCustomerShouldBeFound("otherName1.in=" + DEFAULT_OTHER_NAME_1 + "," + UPDATED_OTHER_NAME_1);

        // Get all the bLCustomerList where otherName1 equals to UPDATED_OTHER_NAME_1
        defaultBLCustomerShouldNotBeFound("otherName1.in=" + UPDATED_OTHER_NAME_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName1IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName1 is not null
        defaultBLCustomerShouldBeFound("otherName1.specified=true");

        // Get all the bLCustomerList where otherName1 is null
        defaultBLCustomerShouldNotBeFound("otherName1.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName1ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName1 contains DEFAULT_OTHER_NAME_1
        defaultBLCustomerShouldBeFound("otherName1.contains=" + DEFAULT_OTHER_NAME_1);

        // Get all the bLCustomerList where otherName1 contains UPDATED_OTHER_NAME_1
        defaultBLCustomerShouldNotBeFound("otherName1.contains=" + UPDATED_OTHER_NAME_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName1NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName1 does not contain DEFAULT_OTHER_NAME_1
        defaultBLCustomerShouldNotBeFound("otherName1.doesNotContain=" + DEFAULT_OTHER_NAME_1);

        // Get all the bLCustomerList where otherName1 does not contain UPDATED_OTHER_NAME_1
        defaultBLCustomerShouldBeFound("otherName1.doesNotContain=" + UPDATED_OTHER_NAME_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName2IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName2 equals to DEFAULT_OTHER_NAME_2
        defaultBLCustomerShouldBeFound("otherName2.equals=" + DEFAULT_OTHER_NAME_2);

        // Get all the bLCustomerList where otherName2 equals to UPDATED_OTHER_NAME_2
        defaultBLCustomerShouldNotBeFound("otherName2.equals=" + UPDATED_OTHER_NAME_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName2 not equals to DEFAULT_OTHER_NAME_2
        defaultBLCustomerShouldNotBeFound("otherName2.notEquals=" + DEFAULT_OTHER_NAME_2);

        // Get all the bLCustomerList where otherName2 not equals to UPDATED_OTHER_NAME_2
        defaultBLCustomerShouldBeFound("otherName2.notEquals=" + UPDATED_OTHER_NAME_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName2IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName2 in DEFAULT_OTHER_NAME_2 or UPDATED_OTHER_NAME_2
        defaultBLCustomerShouldBeFound("otherName2.in=" + DEFAULT_OTHER_NAME_2 + "," + UPDATED_OTHER_NAME_2);

        // Get all the bLCustomerList where otherName2 equals to UPDATED_OTHER_NAME_2
        defaultBLCustomerShouldNotBeFound("otherName2.in=" + UPDATED_OTHER_NAME_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName2IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName2 is not null
        defaultBLCustomerShouldBeFound("otherName2.specified=true");

        // Get all the bLCustomerList where otherName2 is null
        defaultBLCustomerShouldNotBeFound("otherName2.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName2ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName2 contains DEFAULT_OTHER_NAME_2
        defaultBLCustomerShouldBeFound("otherName2.contains=" + DEFAULT_OTHER_NAME_2);

        // Get all the bLCustomerList where otherName2 contains UPDATED_OTHER_NAME_2
        defaultBLCustomerShouldNotBeFound("otherName2.contains=" + UPDATED_OTHER_NAME_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName2NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName2 does not contain DEFAULT_OTHER_NAME_2
        defaultBLCustomerShouldNotBeFound("otherName2.doesNotContain=" + DEFAULT_OTHER_NAME_2);

        // Get all the bLCustomerList where otherName2 does not contain UPDATED_OTHER_NAME_2
        defaultBLCustomerShouldBeFound("otherName2.doesNotContain=" + UPDATED_OTHER_NAME_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName3IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName3 equals to DEFAULT_OTHER_NAME_3
        defaultBLCustomerShouldBeFound("otherName3.equals=" + DEFAULT_OTHER_NAME_3);

        // Get all the bLCustomerList where otherName3 equals to UPDATED_OTHER_NAME_3
        defaultBLCustomerShouldNotBeFound("otherName3.equals=" + UPDATED_OTHER_NAME_3);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName3IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName3 not equals to DEFAULT_OTHER_NAME_3
        defaultBLCustomerShouldNotBeFound("otherName3.notEquals=" + DEFAULT_OTHER_NAME_3);

        // Get all the bLCustomerList where otherName3 not equals to UPDATED_OTHER_NAME_3
        defaultBLCustomerShouldBeFound("otherName3.notEquals=" + UPDATED_OTHER_NAME_3);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName3IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName3 in DEFAULT_OTHER_NAME_3 or UPDATED_OTHER_NAME_3
        defaultBLCustomerShouldBeFound("otherName3.in=" + DEFAULT_OTHER_NAME_3 + "," + UPDATED_OTHER_NAME_3);

        // Get all the bLCustomerList where otherName3 equals to UPDATED_OTHER_NAME_3
        defaultBLCustomerShouldNotBeFound("otherName3.in=" + UPDATED_OTHER_NAME_3);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName3IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName3 is not null
        defaultBLCustomerShouldBeFound("otherName3.specified=true");

        // Get all the bLCustomerList where otherName3 is null
        defaultBLCustomerShouldNotBeFound("otherName3.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName3ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName3 contains DEFAULT_OTHER_NAME_3
        defaultBLCustomerShouldBeFound("otherName3.contains=" + DEFAULT_OTHER_NAME_3);

        // Get all the bLCustomerList where otherName3 contains UPDATED_OTHER_NAME_3
        defaultBLCustomerShouldNotBeFound("otherName3.contains=" + UPDATED_OTHER_NAME_3);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherName3NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherName3 does not contain DEFAULT_OTHER_NAME_3
        defaultBLCustomerShouldNotBeFound("otherName3.doesNotContain=" + DEFAULT_OTHER_NAME_3);

        // Get all the bLCustomerList where otherName3 does not contain UPDATED_OTHER_NAME_3
        defaultBLCustomerShouldBeFound("otherName3.doesNotContain=" + UPDATED_OTHER_NAME_3);
    }

    @Test
    @Transactional
    void getAllBLCustomersByPositionBlIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where positionBl equals to DEFAULT_POSITION_BL
        defaultBLCustomerShouldBeFound("positionBl.equals=" + DEFAULT_POSITION_BL);

        // Get all the bLCustomerList where positionBl equals to UPDATED_POSITION_BL
        defaultBLCustomerShouldNotBeFound("positionBl.equals=" + UPDATED_POSITION_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByPositionBlIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where positionBl not equals to DEFAULT_POSITION_BL
        defaultBLCustomerShouldNotBeFound("positionBl.notEquals=" + DEFAULT_POSITION_BL);

        // Get all the bLCustomerList where positionBl not equals to UPDATED_POSITION_BL
        defaultBLCustomerShouldBeFound("positionBl.notEquals=" + UPDATED_POSITION_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByPositionBlIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where positionBl in DEFAULT_POSITION_BL or UPDATED_POSITION_BL
        defaultBLCustomerShouldBeFound("positionBl.in=" + DEFAULT_POSITION_BL + "," + UPDATED_POSITION_BL);

        // Get all the bLCustomerList where positionBl equals to UPDATED_POSITION_BL
        defaultBLCustomerShouldNotBeFound("positionBl.in=" + UPDATED_POSITION_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByPositionBlIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where positionBl is not null
        defaultBLCustomerShouldBeFound("positionBl.specified=true");

        // Get all the bLCustomerList where positionBl is null
        defaultBLCustomerShouldNotBeFound("positionBl.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByPositionBlContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where positionBl contains DEFAULT_POSITION_BL
        defaultBLCustomerShouldBeFound("positionBl.contains=" + DEFAULT_POSITION_BL);

        // Get all the bLCustomerList where positionBl contains UPDATED_POSITION_BL
        defaultBLCustomerShouldNotBeFound("positionBl.contains=" + UPDATED_POSITION_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByPositionBlNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where positionBl does not contain DEFAULT_POSITION_BL
        defaultBLCustomerShouldNotBeFound("positionBl.doesNotContain=" + DEFAULT_POSITION_BL);

        // Get all the bLCustomerList where positionBl does not contain UPDATED_POSITION_BL
        defaultBLCustomerShouldBeFound("positionBl.doesNotContain=" + UPDATED_POSITION_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateOfBirthBlIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateOfBirthBl equals to DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldBeFound("dateOfBirthBl.equals=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerList where dateOfBirthBl equals to UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldNotBeFound("dateOfBirthBl.equals=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateOfBirthBlIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateOfBirthBl not equals to DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldNotBeFound("dateOfBirthBl.notEquals=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerList where dateOfBirthBl not equals to UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldBeFound("dateOfBirthBl.notEquals=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateOfBirthBlIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateOfBirthBl in DEFAULT_DATE_OF_BIRTH_BL or UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldBeFound("dateOfBirthBl.in=" + DEFAULT_DATE_OF_BIRTH_BL + "," + UPDATED_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerList where dateOfBirthBl equals to UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldNotBeFound("dateOfBirthBl.in=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateOfBirthBlIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateOfBirthBl is not null
        defaultBLCustomerShouldBeFound("dateOfBirthBl.specified=true");

        // Get all the bLCustomerList where dateOfBirthBl is null
        defaultBLCustomerShouldNotBeFound("dateOfBirthBl.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateOfBirthBlContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateOfBirthBl contains DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldBeFound("dateOfBirthBl.contains=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerList where dateOfBirthBl contains UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldNotBeFound("dateOfBirthBl.contains=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateOfBirthBlNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateOfBirthBl does not contain DEFAULT_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldNotBeFound("dateOfBirthBl.doesNotContain=" + DEFAULT_DATE_OF_BIRTH_BL);

        // Get all the bLCustomerList where dateOfBirthBl does not contain UPDATED_DATE_OF_BIRTH_BL
        defaultBLCustomerShouldBeFound("dateOfBirthBl.doesNotContain=" + UPDATED_DATE_OF_BIRTH_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl1IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl1 equals to DEFAULT_COUNTRY_BL_1
        defaultBLCustomerShouldBeFound("countryBl1.equals=" + DEFAULT_COUNTRY_BL_1);

        // Get all the bLCustomerList where countryBl1 equals to UPDATED_COUNTRY_BL_1
        defaultBLCustomerShouldNotBeFound("countryBl1.equals=" + UPDATED_COUNTRY_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl1 not equals to DEFAULT_COUNTRY_BL_1
        defaultBLCustomerShouldNotBeFound("countryBl1.notEquals=" + DEFAULT_COUNTRY_BL_1);

        // Get all the bLCustomerList where countryBl1 not equals to UPDATED_COUNTRY_BL_1
        defaultBLCustomerShouldBeFound("countryBl1.notEquals=" + UPDATED_COUNTRY_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl1IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl1 in DEFAULT_COUNTRY_BL_1 or UPDATED_COUNTRY_BL_1
        defaultBLCustomerShouldBeFound("countryBl1.in=" + DEFAULT_COUNTRY_BL_1 + "," + UPDATED_COUNTRY_BL_1);

        // Get all the bLCustomerList where countryBl1 equals to UPDATED_COUNTRY_BL_1
        defaultBLCustomerShouldNotBeFound("countryBl1.in=" + UPDATED_COUNTRY_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl1IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl1 is not null
        defaultBLCustomerShouldBeFound("countryBl1.specified=true");

        // Get all the bLCustomerList where countryBl1 is null
        defaultBLCustomerShouldNotBeFound("countryBl1.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl1ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl1 contains DEFAULT_COUNTRY_BL_1
        defaultBLCustomerShouldBeFound("countryBl1.contains=" + DEFAULT_COUNTRY_BL_1);

        // Get all the bLCustomerList where countryBl1 contains UPDATED_COUNTRY_BL_1
        defaultBLCustomerShouldNotBeFound("countryBl1.contains=" + UPDATED_COUNTRY_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl1NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl1 does not contain DEFAULT_COUNTRY_BL_1
        defaultBLCustomerShouldNotBeFound("countryBl1.doesNotContain=" + DEFAULT_COUNTRY_BL_1);

        // Get all the bLCustomerList where countryBl1 does not contain UPDATED_COUNTRY_BL_1
        defaultBLCustomerShouldBeFound("countryBl1.doesNotContain=" + UPDATED_COUNTRY_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl2IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl2 equals to DEFAULT_COUNTRY_BL_2
        defaultBLCustomerShouldBeFound("countryBl2.equals=" + DEFAULT_COUNTRY_BL_2);

        // Get all the bLCustomerList where countryBl2 equals to UPDATED_COUNTRY_BL_2
        defaultBLCustomerShouldNotBeFound("countryBl2.equals=" + UPDATED_COUNTRY_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl2 not equals to DEFAULT_COUNTRY_BL_2
        defaultBLCustomerShouldNotBeFound("countryBl2.notEquals=" + DEFAULT_COUNTRY_BL_2);

        // Get all the bLCustomerList where countryBl2 not equals to UPDATED_COUNTRY_BL_2
        defaultBLCustomerShouldBeFound("countryBl2.notEquals=" + UPDATED_COUNTRY_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl2IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl2 in DEFAULT_COUNTRY_BL_2 or UPDATED_COUNTRY_BL_2
        defaultBLCustomerShouldBeFound("countryBl2.in=" + DEFAULT_COUNTRY_BL_2 + "," + UPDATED_COUNTRY_BL_2);

        // Get all the bLCustomerList where countryBl2 equals to UPDATED_COUNTRY_BL_2
        defaultBLCustomerShouldNotBeFound("countryBl2.in=" + UPDATED_COUNTRY_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl2IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl2 is not null
        defaultBLCustomerShouldBeFound("countryBl2.specified=true");

        // Get all the bLCustomerList where countryBl2 is null
        defaultBLCustomerShouldNotBeFound("countryBl2.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl2ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl2 contains DEFAULT_COUNTRY_BL_2
        defaultBLCustomerShouldBeFound("countryBl2.contains=" + DEFAULT_COUNTRY_BL_2);

        // Get all the bLCustomerList where countryBl2 contains UPDATED_COUNTRY_BL_2
        defaultBLCustomerShouldNotBeFound("countryBl2.contains=" + UPDATED_COUNTRY_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCountryBl2NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where countryBl2 does not contain DEFAULT_COUNTRY_BL_2
        defaultBLCustomerShouldNotBeFound("countryBl2.doesNotContain=" + DEFAULT_COUNTRY_BL_2);

        // Get all the bLCustomerList where countryBl2 does not contain UPDATED_COUNTRY_BL_2
        defaultBLCustomerShouldBeFound("countryBl2.doesNotContain=" + UPDATED_COUNTRY_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl1IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl1 equals to DEFAULT_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldBeFound("legalIdTypeBl1.equals=" + DEFAULT_LEGAL_ID_TYPE_BL_1);

        // Get all the bLCustomerList where legalIdTypeBl1 equals to UPDATED_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl1.equals=" + UPDATED_LEGAL_ID_TYPE_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl1 not equals to DEFAULT_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl1.notEquals=" + DEFAULT_LEGAL_ID_TYPE_BL_1);

        // Get all the bLCustomerList where legalIdTypeBl1 not equals to UPDATED_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldBeFound("legalIdTypeBl1.notEquals=" + UPDATED_LEGAL_ID_TYPE_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl1IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl1 in DEFAULT_LEGAL_ID_TYPE_BL_1 or UPDATED_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldBeFound("legalIdTypeBl1.in=" + DEFAULT_LEGAL_ID_TYPE_BL_1 + "," + UPDATED_LEGAL_ID_TYPE_BL_1);

        // Get all the bLCustomerList where legalIdTypeBl1 equals to UPDATED_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl1.in=" + UPDATED_LEGAL_ID_TYPE_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl1IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl1 is not null
        defaultBLCustomerShouldBeFound("legalIdTypeBl1.specified=true");

        // Get all the bLCustomerList where legalIdTypeBl1 is null
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl1.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl1ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl1 contains DEFAULT_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldBeFound("legalIdTypeBl1.contains=" + DEFAULT_LEGAL_ID_TYPE_BL_1);

        // Get all the bLCustomerList where legalIdTypeBl1 contains UPDATED_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl1.contains=" + UPDATED_LEGAL_ID_TYPE_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl1NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl1 does not contain DEFAULT_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl1.doesNotContain=" + DEFAULT_LEGAL_ID_TYPE_BL_1);

        // Get all the bLCustomerList where legalIdTypeBl1 does not contain UPDATED_LEGAL_ID_TYPE_BL_1
        defaultBLCustomerShouldBeFound("legalIdTypeBl1.doesNotContain=" + UPDATED_LEGAL_ID_TYPE_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber1IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber1 equals to DEFAULT_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldBeFound("legalIdNumber1.equals=" + DEFAULT_LEGAL_ID_NUMBER_1);

        // Get all the bLCustomerList where legalIdNumber1 equals to UPDATED_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldNotBeFound("legalIdNumber1.equals=" + UPDATED_LEGAL_ID_NUMBER_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber1 not equals to DEFAULT_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldNotBeFound("legalIdNumber1.notEquals=" + DEFAULT_LEGAL_ID_NUMBER_1);

        // Get all the bLCustomerList where legalIdNumber1 not equals to UPDATED_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldBeFound("legalIdNumber1.notEquals=" + UPDATED_LEGAL_ID_NUMBER_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber1IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber1 in DEFAULT_LEGAL_ID_NUMBER_1 or UPDATED_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldBeFound("legalIdNumber1.in=" + DEFAULT_LEGAL_ID_NUMBER_1 + "," + UPDATED_LEGAL_ID_NUMBER_1);

        // Get all the bLCustomerList where legalIdNumber1 equals to UPDATED_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldNotBeFound("legalIdNumber1.in=" + UPDATED_LEGAL_ID_NUMBER_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber1IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber1 is not null
        defaultBLCustomerShouldBeFound("legalIdNumber1.specified=true");

        // Get all the bLCustomerList where legalIdNumber1 is null
        defaultBLCustomerShouldNotBeFound("legalIdNumber1.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber1ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber1 contains DEFAULT_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldBeFound("legalIdNumber1.contains=" + DEFAULT_LEGAL_ID_NUMBER_1);

        // Get all the bLCustomerList where legalIdNumber1 contains UPDATED_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldNotBeFound("legalIdNumber1.contains=" + UPDATED_LEGAL_ID_NUMBER_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber1NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber1 does not contain DEFAULT_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldNotBeFound("legalIdNumber1.doesNotContain=" + DEFAULT_LEGAL_ID_NUMBER_1);

        // Get all the bLCustomerList where legalIdNumber1 does not contain UPDATED_LEGAL_ID_NUMBER_1
        defaultBLCustomerShouldBeFound("legalIdNumber1.doesNotContain=" + UPDATED_LEGAL_ID_NUMBER_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl2IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl2 equals to DEFAULT_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldBeFound("legalIdTypeBl2.equals=" + DEFAULT_LEGAL_ID_TYPE_BL_2);

        // Get all the bLCustomerList where legalIdTypeBl2 equals to UPDATED_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl2.equals=" + UPDATED_LEGAL_ID_TYPE_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl2 not equals to DEFAULT_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl2.notEquals=" + DEFAULT_LEGAL_ID_TYPE_BL_2);

        // Get all the bLCustomerList where legalIdTypeBl2 not equals to UPDATED_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldBeFound("legalIdTypeBl2.notEquals=" + UPDATED_LEGAL_ID_TYPE_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl2IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl2 in DEFAULT_LEGAL_ID_TYPE_BL_2 or UPDATED_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldBeFound("legalIdTypeBl2.in=" + DEFAULT_LEGAL_ID_TYPE_BL_2 + "," + UPDATED_LEGAL_ID_TYPE_BL_2);

        // Get all the bLCustomerList where legalIdTypeBl2 equals to UPDATED_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl2.in=" + UPDATED_LEGAL_ID_TYPE_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl2IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl2 is not null
        defaultBLCustomerShouldBeFound("legalIdTypeBl2.specified=true");

        // Get all the bLCustomerList where legalIdTypeBl2 is null
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl2.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl2ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl2 contains DEFAULT_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldBeFound("legalIdTypeBl2.contains=" + DEFAULT_LEGAL_ID_TYPE_BL_2);

        // Get all the bLCustomerList where legalIdTypeBl2 contains UPDATED_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl2.contains=" + UPDATED_LEGAL_ID_TYPE_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdTypeBl2NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdTypeBl2 does not contain DEFAULT_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldNotBeFound("legalIdTypeBl2.doesNotContain=" + DEFAULT_LEGAL_ID_TYPE_BL_2);

        // Get all the bLCustomerList where legalIdTypeBl2 does not contain UPDATED_LEGAL_ID_TYPE_BL_2
        defaultBLCustomerShouldBeFound("legalIdTypeBl2.doesNotContain=" + UPDATED_LEGAL_ID_TYPE_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber2IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber2 equals to DEFAULT_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldBeFound("legalIdNumber2.equals=" + DEFAULT_LEGAL_ID_NUMBER_2);

        // Get all the bLCustomerList where legalIdNumber2 equals to UPDATED_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldNotBeFound("legalIdNumber2.equals=" + UPDATED_LEGAL_ID_NUMBER_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber2 not equals to DEFAULT_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldNotBeFound("legalIdNumber2.notEquals=" + DEFAULT_LEGAL_ID_NUMBER_2);

        // Get all the bLCustomerList where legalIdNumber2 not equals to UPDATED_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldBeFound("legalIdNumber2.notEquals=" + UPDATED_LEGAL_ID_NUMBER_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber2IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber2 in DEFAULT_LEGAL_ID_NUMBER_2 or UPDATED_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldBeFound("legalIdNumber2.in=" + DEFAULT_LEGAL_ID_NUMBER_2 + "," + UPDATED_LEGAL_ID_NUMBER_2);

        // Get all the bLCustomerList where legalIdNumber2 equals to UPDATED_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldNotBeFound("legalIdNumber2.in=" + UPDATED_LEGAL_ID_NUMBER_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber2IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber2 is not null
        defaultBLCustomerShouldBeFound("legalIdNumber2.specified=true");

        // Get all the bLCustomerList where legalIdNumber2 is null
        defaultBLCustomerShouldNotBeFound("legalIdNumber2.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber2ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber2 contains DEFAULT_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldBeFound("legalIdNumber2.contains=" + DEFAULT_LEGAL_ID_NUMBER_2);

        // Get all the bLCustomerList where legalIdNumber2 contains UPDATED_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldNotBeFound("legalIdNumber2.contains=" + UPDATED_LEGAL_ID_NUMBER_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByLegalIdNumber2NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where legalIdNumber2 does not contain DEFAULT_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldNotBeFound("legalIdNumber2.doesNotContain=" + DEFAULT_LEGAL_ID_NUMBER_2);

        // Get all the bLCustomerList where legalIdNumber2 does not contain UPDATED_LEGAL_ID_NUMBER_2
        defaultBLCustomerShouldBeFound("legalIdNumber2.doesNotContain=" + UPDATED_LEGAL_ID_NUMBER_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal1IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal1 equals to DEFAULT_OTHER_INF_LEGAL_1
        defaultBLCustomerShouldBeFound("otherInfLegal1.equals=" + DEFAULT_OTHER_INF_LEGAL_1);

        // Get all the bLCustomerList where otherInfLegal1 equals to UPDATED_OTHER_INF_LEGAL_1
        defaultBLCustomerShouldNotBeFound("otherInfLegal1.equals=" + UPDATED_OTHER_INF_LEGAL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal1 not equals to DEFAULT_OTHER_INF_LEGAL_1
        defaultBLCustomerShouldNotBeFound("otherInfLegal1.notEquals=" + DEFAULT_OTHER_INF_LEGAL_1);

        // Get all the bLCustomerList where otherInfLegal1 not equals to UPDATED_OTHER_INF_LEGAL_1
        defaultBLCustomerShouldBeFound("otherInfLegal1.notEquals=" + UPDATED_OTHER_INF_LEGAL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal1IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal1 in DEFAULT_OTHER_INF_LEGAL_1 or UPDATED_OTHER_INF_LEGAL_1
        defaultBLCustomerShouldBeFound("otherInfLegal1.in=" + DEFAULT_OTHER_INF_LEGAL_1 + "," + UPDATED_OTHER_INF_LEGAL_1);

        // Get all the bLCustomerList where otherInfLegal1 equals to UPDATED_OTHER_INF_LEGAL_1
        defaultBLCustomerShouldNotBeFound("otherInfLegal1.in=" + UPDATED_OTHER_INF_LEGAL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal1IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal1 is not null
        defaultBLCustomerShouldBeFound("otherInfLegal1.specified=true");

        // Get all the bLCustomerList where otherInfLegal1 is null
        defaultBLCustomerShouldNotBeFound("otherInfLegal1.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal2IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal2 equals to DEFAULT_OTHER_INF_LEGAL_2
        defaultBLCustomerShouldBeFound("otherInfLegal2.equals=" + DEFAULT_OTHER_INF_LEGAL_2);

        // Get all the bLCustomerList where otherInfLegal2 equals to UPDATED_OTHER_INF_LEGAL_2
        defaultBLCustomerShouldNotBeFound("otherInfLegal2.equals=" + UPDATED_OTHER_INF_LEGAL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal2 not equals to DEFAULT_OTHER_INF_LEGAL_2
        defaultBLCustomerShouldNotBeFound("otherInfLegal2.notEquals=" + DEFAULT_OTHER_INF_LEGAL_2);

        // Get all the bLCustomerList where otherInfLegal2 not equals to UPDATED_OTHER_INF_LEGAL_2
        defaultBLCustomerShouldBeFound("otherInfLegal2.notEquals=" + UPDATED_OTHER_INF_LEGAL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal2IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal2 in DEFAULT_OTHER_INF_LEGAL_2 or UPDATED_OTHER_INF_LEGAL_2
        defaultBLCustomerShouldBeFound("otherInfLegal2.in=" + DEFAULT_OTHER_INF_LEGAL_2 + "," + UPDATED_OTHER_INF_LEGAL_2);

        // Get all the bLCustomerList where otherInfLegal2 equals to UPDATED_OTHER_INF_LEGAL_2
        defaultBLCustomerShouldNotBeFound("otherInfLegal2.in=" + UPDATED_OTHER_INF_LEGAL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByOtherInfLegal2IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where otherInfLegal2 is not null
        defaultBLCustomerShouldBeFound("otherInfLegal2.specified=true");

        // Get all the bLCustomerList where otherInfLegal2 is null
        defaultBLCustomerShouldNotBeFound("otherInfLegal2.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl1IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl1 equals to DEFAULT_ADDRESS_BL_1
        defaultBLCustomerShouldBeFound("addressBl1.equals=" + DEFAULT_ADDRESS_BL_1);

        // Get all the bLCustomerList where addressBl1 equals to UPDATED_ADDRESS_BL_1
        defaultBLCustomerShouldNotBeFound("addressBl1.equals=" + UPDATED_ADDRESS_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl1 not equals to DEFAULT_ADDRESS_BL_1
        defaultBLCustomerShouldNotBeFound("addressBl1.notEquals=" + DEFAULT_ADDRESS_BL_1);

        // Get all the bLCustomerList where addressBl1 not equals to UPDATED_ADDRESS_BL_1
        defaultBLCustomerShouldBeFound("addressBl1.notEquals=" + UPDATED_ADDRESS_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl1IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl1 in DEFAULT_ADDRESS_BL_1 or UPDATED_ADDRESS_BL_1
        defaultBLCustomerShouldBeFound("addressBl1.in=" + DEFAULT_ADDRESS_BL_1 + "," + UPDATED_ADDRESS_BL_1);

        // Get all the bLCustomerList where addressBl1 equals to UPDATED_ADDRESS_BL_1
        defaultBLCustomerShouldNotBeFound("addressBl1.in=" + UPDATED_ADDRESS_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl1IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl1 is not null
        defaultBLCustomerShouldBeFound("addressBl1.specified=true");

        // Get all the bLCustomerList where addressBl1 is null
        defaultBLCustomerShouldNotBeFound("addressBl1.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl1ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl1 contains DEFAULT_ADDRESS_BL_1
        defaultBLCustomerShouldBeFound("addressBl1.contains=" + DEFAULT_ADDRESS_BL_1);

        // Get all the bLCustomerList where addressBl1 contains UPDATED_ADDRESS_BL_1
        defaultBLCustomerShouldNotBeFound("addressBl1.contains=" + UPDATED_ADDRESS_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl1NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl1 does not contain DEFAULT_ADDRESS_BL_1
        defaultBLCustomerShouldNotBeFound("addressBl1.doesNotContain=" + DEFAULT_ADDRESS_BL_1);

        // Get all the bLCustomerList where addressBl1 does not contain UPDATED_ADDRESS_BL_1
        defaultBLCustomerShouldBeFound("addressBl1.doesNotContain=" + UPDATED_ADDRESS_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl2IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl2 equals to DEFAULT_ADDRESS_BL_2
        defaultBLCustomerShouldBeFound("addressBl2.equals=" + DEFAULT_ADDRESS_BL_2);

        // Get all the bLCustomerList where addressBl2 equals to UPDATED_ADDRESS_BL_2
        defaultBLCustomerShouldNotBeFound("addressBl2.equals=" + UPDATED_ADDRESS_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl2 not equals to DEFAULT_ADDRESS_BL_2
        defaultBLCustomerShouldNotBeFound("addressBl2.notEquals=" + DEFAULT_ADDRESS_BL_2);

        // Get all the bLCustomerList where addressBl2 not equals to UPDATED_ADDRESS_BL_2
        defaultBLCustomerShouldBeFound("addressBl2.notEquals=" + UPDATED_ADDRESS_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl2IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl2 in DEFAULT_ADDRESS_BL_2 or UPDATED_ADDRESS_BL_2
        defaultBLCustomerShouldBeFound("addressBl2.in=" + DEFAULT_ADDRESS_BL_2 + "," + UPDATED_ADDRESS_BL_2);

        // Get all the bLCustomerList where addressBl2 equals to UPDATED_ADDRESS_BL_2
        defaultBLCustomerShouldNotBeFound("addressBl2.in=" + UPDATED_ADDRESS_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl2IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl2 is not null
        defaultBLCustomerShouldBeFound("addressBl2.specified=true");

        // Get all the bLCustomerList where addressBl2 is null
        defaultBLCustomerShouldNotBeFound("addressBl2.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl2ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl2 contains DEFAULT_ADDRESS_BL_2
        defaultBLCustomerShouldBeFound("addressBl2.contains=" + DEFAULT_ADDRESS_BL_2);

        // Get all the bLCustomerList where addressBl2 contains UPDATED_ADDRESS_BL_2
        defaultBLCustomerShouldNotBeFound("addressBl2.contains=" + UPDATED_ADDRESS_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressBl2NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressBl2 does not contain DEFAULT_ADDRESS_BL_2
        defaultBLCustomerShouldNotBeFound("addressBl2.doesNotContain=" + DEFAULT_ADDRESS_BL_2);

        // Get all the bLCustomerList where addressBl2 does not contain UPDATED_ADDRESS_BL_2
        defaultBLCustomerShouldBeFound("addressBl2.doesNotContain=" + UPDATED_ADDRESS_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl1IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl1 equals to DEFAULT_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldBeFound("addressNowBl1.equals=" + DEFAULT_ADDRESS_NOW_BL_1);

        // Get all the bLCustomerList where addressNowBl1 equals to UPDATED_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldNotBeFound("addressNowBl1.equals=" + UPDATED_ADDRESS_NOW_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl1IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl1 not equals to DEFAULT_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldNotBeFound("addressNowBl1.notEquals=" + DEFAULT_ADDRESS_NOW_BL_1);

        // Get all the bLCustomerList where addressNowBl1 not equals to UPDATED_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldBeFound("addressNowBl1.notEquals=" + UPDATED_ADDRESS_NOW_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl1IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl1 in DEFAULT_ADDRESS_NOW_BL_1 or UPDATED_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldBeFound("addressNowBl1.in=" + DEFAULT_ADDRESS_NOW_BL_1 + "," + UPDATED_ADDRESS_NOW_BL_1);

        // Get all the bLCustomerList where addressNowBl1 equals to UPDATED_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldNotBeFound("addressNowBl1.in=" + UPDATED_ADDRESS_NOW_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl1IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl1 is not null
        defaultBLCustomerShouldBeFound("addressNowBl1.specified=true");

        // Get all the bLCustomerList where addressNowBl1 is null
        defaultBLCustomerShouldNotBeFound("addressNowBl1.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl1ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl1 contains DEFAULT_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldBeFound("addressNowBl1.contains=" + DEFAULT_ADDRESS_NOW_BL_1);

        // Get all the bLCustomerList where addressNowBl1 contains UPDATED_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldNotBeFound("addressNowBl1.contains=" + UPDATED_ADDRESS_NOW_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl1NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl1 does not contain DEFAULT_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldNotBeFound("addressNowBl1.doesNotContain=" + DEFAULT_ADDRESS_NOW_BL_1);

        // Get all the bLCustomerList where addressNowBl1 does not contain UPDATED_ADDRESS_NOW_BL_1
        defaultBLCustomerShouldBeFound("addressNowBl1.doesNotContain=" + UPDATED_ADDRESS_NOW_BL_1);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl2IsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl2 equals to DEFAULT_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldBeFound("addressNowBl2.equals=" + DEFAULT_ADDRESS_NOW_BL_2);

        // Get all the bLCustomerList where addressNowBl2 equals to UPDATED_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldNotBeFound("addressNowBl2.equals=" + UPDATED_ADDRESS_NOW_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl2IsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl2 not equals to DEFAULT_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldNotBeFound("addressNowBl2.notEquals=" + DEFAULT_ADDRESS_NOW_BL_2);

        // Get all the bLCustomerList where addressNowBl2 not equals to UPDATED_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldBeFound("addressNowBl2.notEquals=" + UPDATED_ADDRESS_NOW_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl2IsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl2 in DEFAULT_ADDRESS_NOW_BL_2 or UPDATED_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldBeFound("addressNowBl2.in=" + DEFAULT_ADDRESS_NOW_BL_2 + "," + UPDATED_ADDRESS_NOW_BL_2);

        // Get all the bLCustomerList where addressNowBl2 equals to UPDATED_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldNotBeFound("addressNowBl2.in=" + UPDATED_ADDRESS_NOW_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl2IsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl2 is not null
        defaultBLCustomerShouldBeFound("addressNowBl2.specified=true");

        // Get all the bLCustomerList where addressNowBl2 is null
        defaultBLCustomerShouldNotBeFound("addressNowBl2.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl2ContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl2 contains DEFAULT_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldBeFound("addressNowBl2.contains=" + DEFAULT_ADDRESS_NOW_BL_2);

        // Get all the bLCustomerList where addressNowBl2 contains UPDATED_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldNotBeFound("addressNowBl2.contains=" + UPDATED_ADDRESS_NOW_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByAddressNowBl2NotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where addressNowBl2 does not contain DEFAULT_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldNotBeFound("addressNowBl2.doesNotContain=" + DEFAULT_ADDRESS_NOW_BL_2);

        // Get all the bLCustomerList where addressNowBl2 does not contain UPDATED_ADDRESS_NOW_BL_2
        defaultBLCustomerShouldBeFound("addressNowBl2.doesNotContain=" + UPDATED_ADDRESS_NOW_BL_2);
    }

    @Test
    @Transactional
    void getAllBLCustomersByTypeBlIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where typeBl equals to DEFAULT_TYPE_BL
        defaultBLCustomerShouldBeFound("typeBl.equals=" + DEFAULT_TYPE_BL);

        // Get all the bLCustomerList where typeBl equals to UPDATED_TYPE_BL
        defaultBLCustomerShouldNotBeFound("typeBl.equals=" + UPDATED_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByTypeBlIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where typeBl not equals to DEFAULT_TYPE_BL
        defaultBLCustomerShouldNotBeFound("typeBl.notEquals=" + DEFAULT_TYPE_BL);

        // Get all the bLCustomerList where typeBl not equals to UPDATED_TYPE_BL
        defaultBLCustomerShouldBeFound("typeBl.notEquals=" + UPDATED_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByTypeBlIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where typeBl in DEFAULT_TYPE_BL or UPDATED_TYPE_BL
        defaultBLCustomerShouldBeFound("typeBl.in=" + DEFAULT_TYPE_BL + "," + UPDATED_TYPE_BL);

        // Get all the bLCustomerList where typeBl equals to UPDATED_TYPE_BL
        defaultBLCustomerShouldNotBeFound("typeBl.in=" + UPDATED_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByTypeBlIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where typeBl is not null
        defaultBLCustomerShouldBeFound("typeBl.specified=true");

        // Get all the bLCustomerList where typeBl is null
        defaultBLCustomerShouldNotBeFound("typeBl.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByTypeBlContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where typeBl contains DEFAULT_TYPE_BL
        defaultBLCustomerShouldBeFound("typeBl.contains=" + DEFAULT_TYPE_BL);

        // Get all the bLCustomerList where typeBl contains UPDATED_TYPE_BL
        defaultBLCustomerShouldNotBeFound("typeBl.contains=" + UPDATED_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersByTypeBlNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where typeBl does not contain DEFAULT_TYPE_BL
        defaultBLCustomerShouldNotBeFound("typeBl.doesNotContain=" + DEFAULT_TYPE_BL);

        // Get all the bLCustomerList where typeBl does not contain UPDATED_TYPE_BL
        defaultBLCustomerShouldBeFound("typeBl.doesNotContain=" + UPDATED_TYPE_BL);
    }

    @Test
    @Transactional
    void getAllBLCustomersBySourceIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where source equals to DEFAULT_SOURCE
        defaultBLCustomerShouldBeFound("source.equals=" + DEFAULT_SOURCE);

        // Get all the bLCustomerList where source equals to UPDATED_SOURCE
        defaultBLCustomerShouldNotBeFound("source.equals=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBySourceIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where source not equals to DEFAULT_SOURCE
        defaultBLCustomerShouldNotBeFound("source.notEquals=" + DEFAULT_SOURCE);

        // Get all the bLCustomerList where source not equals to UPDATED_SOURCE
        defaultBLCustomerShouldBeFound("source.notEquals=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBySourceIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where source in DEFAULT_SOURCE or UPDATED_SOURCE
        defaultBLCustomerShouldBeFound("source.in=" + DEFAULT_SOURCE + "," + UPDATED_SOURCE);

        // Get all the bLCustomerList where source equals to UPDATED_SOURCE
        defaultBLCustomerShouldNotBeFound("source.in=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBySourceIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where source is not null
        defaultBLCustomerShouldBeFound("source.specified=true");

        // Get all the bLCustomerList where source is null
        defaultBLCustomerShouldNotBeFound("source.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersBySourceContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where source contains DEFAULT_SOURCE
        defaultBLCustomerShouldBeFound("source.contains=" + DEFAULT_SOURCE);

        // Get all the bLCustomerList where source contains UPDATED_SOURCE
        defaultBLCustomerShouldNotBeFound("source.contains=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBySourceNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where source does not contain DEFAULT_SOURCE
        defaultBLCustomerShouldNotBeFound("source.doesNotContain=" + DEFAULT_SOURCE);

        // Get all the bLCustomerList where source does not contain UPDATED_SOURCE
        defaultBLCustomerShouldBeFound("source.doesNotContain=" + UPDATED_SOURCE);
    }

    @Test
    @Transactional
    void getAllBLCustomersByRecordStatusIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where recordStatus equals to DEFAULT_RECORD_STATUS
        defaultBLCustomerShouldBeFound("recordStatus.equals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLCustomerShouldNotBeFound("recordStatus.equals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomersByRecordStatusIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where recordStatus not equals to DEFAULT_RECORD_STATUS
        defaultBLCustomerShouldNotBeFound("recordStatus.notEquals=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerList where recordStatus not equals to UPDATED_RECORD_STATUS
        defaultBLCustomerShouldBeFound("recordStatus.notEquals=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomersByRecordStatusIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where recordStatus in DEFAULT_RECORD_STATUS or UPDATED_RECORD_STATUS
        defaultBLCustomerShouldBeFound("recordStatus.in=" + DEFAULT_RECORD_STATUS + "," + UPDATED_RECORD_STATUS);

        // Get all the bLCustomerList where recordStatus equals to UPDATED_RECORD_STATUS
        defaultBLCustomerShouldNotBeFound("recordStatus.in=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomersByRecordStatusIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where recordStatus is not null
        defaultBLCustomerShouldBeFound("recordStatus.specified=true");

        // Get all the bLCustomerList where recordStatus is null
        defaultBLCustomerShouldNotBeFound("recordStatus.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByRecordStatusContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where recordStatus contains DEFAULT_RECORD_STATUS
        defaultBLCustomerShouldBeFound("recordStatus.contains=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerList where recordStatus contains UPDATED_RECORD_STATUS
        defaultBLCustomerShouldNotBeFound("recordStatus.contains=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomersByRecordStatusNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where recordStatus does not contain DEFAULT_RECORD_STATUS
        defaultBLCustomerShouldNotBeFound("recordStatus.doesNotContain=" + DEFAULT_RECORD_STATUS);

        // Get all the bLCustomerList where recordStatus does not contain UPDATED_RECORD_STATUS
        defaultBLCustomerShouldBeFound("recordStatus.doesNotContain=" + UPDATED_RECORD_STATUS);
    }

    @Test
    @Transactional
    void getAllBLCustomersByUploadFileIdIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where uploadFileId equals to DEFAULT_UPLOAD_FILE_ID
        defaultBLCustomerShouldBeFound("uploadFileId.equals=" + DEFAULT_UPLOAD_FILE_ID);

        // Get all the bLCustomerList where uploadFileId equals to UPDATED_UPLOAD_FILE_ID
        defaultBLCustomerShouldNotBeFound("uploadFileId.equals=" + UPDATED_UPLOAD_FILE_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomersByUploadFileIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where uploadFileId not equals to DEFAULT_UPLOAD_FILE_ID
        defaultBLCustomerShouldNotBeFound("uploadFileId.notEquals=" + DEFAULT_UPLOAD_FILE_ID);

        // Get all the bLCustomerList where uploadFileId not equals to UPDATED_UPLOAD_FILE_ID
        defaultBLCustomerShouldBeFound("uploadFileId.notEquals=" + UPDATED_UPLOAD_FILE_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomersByUploadFileIdIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where uploadFileId in DEFAULT_UPLOAD_FILE_ID or UPDATED_UPLOAD_FILE_ID
        defaultBLCustomerShouldBeFound("uploadFileId.in=" + DEFAULT_UPLOAD_FILE_ID + "," + UPDATED_UPLOAD_FILE_ID);

        // Get all the bLCustomerList where uploadFileId equals to UPDATED_UPLOAD_FILE_ID
        defaultBLCustomerShouldNotBeFound("uploadFileId.in=" + UPDATED_UPLOAD_FILE_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomersByUploadFileIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where uploadFileId is not null
        defaultBLCustomerShouldBeFound("uploadFileId.specified=true");

        // Get all the bLCustomerList where uploadFileId is null
        defaultBLCustomerShouldNotBeFound("uploadFileId.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByUploadFileIdContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where uploadFileId contains DEFAULT_UPLOAD_FILE_ID
        defaultBLCustomerShouldBeFound("uploadFileId.contains=" + DEFAULT_UPLOAD_FILE_ID);

        // Get all the bLCustomerList where uploadFileId contains UPDATED_UPLOAD_FILE_ID
        defaultBLCustomerShouldNotBeFound("uploadFileId.contains=" + UPDATED_UPLOAD_FILE_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomersByUploadFileIdNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where uploadFileId does not contain DEFAULT_UPLOAD_FILE_ID
        defaultBLCustomerShouldNotBeFound("uploadFileId.doesNotContain=" + DEFAULT_UPLOAD_FILE_ID);

        // Get all the bLCustomerList where uploadFileId does not contain UPDATED_UPLOAD_FILE_ID
        defaultBLCustomerShouldBeFound("uploadFileId.doesNotContain=" + UPDATED_UPLOAD_FILE_ID);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCoCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where coCode equals to DEFAULT_CO_CODE
        defaultBLCustomerShouldBeFound("coCode.equals=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerList where coCode equals to UPDATED_CO_CODE
        defaultBLCustomerShouldNotBeFound("coCode.equals=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCoCodeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where coCode not equals to DEFAULT_CO_CODE
        defaultBLCustomerShouldNotBeFound("coCode.notEquals=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerList where coCode not equals to UPDATED_CO_CODE
        defaultBLCustomerShouldBeFound("coCode.notEquals=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCoCodeIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where coCode in DEFAULT_CO_CODE or UPDATED_CO_CODE
        defaultBLCustomerShouldBeFound("coCode.in=" + DEFAULT_CO_CODE + "," + UPDATED_CO_CODE);

        // Get all the bLCustomerList where coCode equals to UPDATED_CO_CODE
        defaultBLCustomerShouldNotBeFound("coCode.in=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCoCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where coCode is not null
        defaultBLCustomerShouldBeFound("coCode.specified=true");

        // Get all the bLCustomerList where coCode is null
        defaultBLCustomerShouldNotBeFound("coCode.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByCoCodeContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where coCode contains DEFAULT_CO_CODE
        defaultBLCustomerShouldBeFound("coCode.contains=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerList where coCode contains UPDATED_CO_CODE
        defaultBLCustomerShouldNotBeFound("coCode.contains=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCoCodeNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where coCode does not contain DEFAULT_CO_CODE
        defaultBLCustomerShouldNotBeFound("coCode.doesNotContain=" + DEFAULT_CO_CODE);

        // Get all the bLCustomerList where coCode does not contain UPDATED_CO_CODE
        defaultBLCustomerShouldBeFound("coCode.doesNotContain=" + UPDATED_CO_CODE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBycreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy equals to DEFAULT_createdBy
        defaultBLCustomerShouldBeFound("createdBy.equals=" + DEFAULT_createdBy);

        // Get all the bLCustomerList where createdBy equals to UPDATED_createdBy
        defaultBLCustomerShouldNotBeFound("createdBy.equals=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomersBycreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy not equals to DEFAULT_createdBy
        defaultBLCustomerShouldNotBeFound("createdBy.notEquals=" + DEFAULT_createdBy);

        // Get all the bLCustomerList where createdBy not equals to UPDATED_createdBy
        defaultBLCustomerShouldBeFound("createdBy.notEquals=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomersBycreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy in DEFAULT_createdBy or UPDATED_createdBy
        defaultBLCustomerShouldBeFound("createdBy.in=" + DEFAULT_createdBy + "," + UPDATED_createdBy);

        // Get all the bLCustomerList where createdBy equals to UPDATED_createdBy
        defaultBLCustomerShouldNotBeFound("createdBy.in=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomersBycreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy is not null
        defaultBLCustomerShouldBeFound("createdBy.specified=true");

        // Get all the bLCustomerList where createdBy is null
        defaultBLCustomerShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersBycreatedByContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy contains DEFAULT_createdBy
        defaultBLCustomerShouldBeFound("createdBy.contains=" + DEFAULT_createdBy);

        // Get all the bLCustomerList where createdBy contains UPDATED_createdBy
        defaultBLCustomerShouldNotBeFound("createdBy.contains=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomersBycreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy does not contain DEFAULT_createdBy
        defaultBLCustomerShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_createdBy);

        // Get all the bLCustomerList where createdBy does not contain UPDATED_createdBy
        defaultBLCustomerShouldBeFound("createdBy.doesNotContain=" + UPDATED_createdBy);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated equals to DEFAULT_date_created
        defaultBLCustomerShouldBeFound("dateCreated.equals=" + DEFAULT_date_created);

        // Get all the bLCustomerList where dateCreated equals to UPDATED_date_created
        defaultBLCustomerShouldNotBeFound("dateCreated.equals=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated not equals to DEFAULT_date_created
        defaultBLCustomerShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_date_created);

        // Get all the bLCustomerList where dateCreated not equals to UPDATED_date_created
        defaultBLCustomerShouldBeFound("dateCreated.notEquals=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated in DEFAULT_date_created or UPDATED_date_created
        defaultBLCustomerShouldBeFound("dateCreated.in=" + DEFAULT_date_created + "," + UPDATED_date_created);

        // Get all the bLCustomerList where dateCreated equals to UPDATED_date_created
        defaultBLCustomerShouldNotBeFound("dateCreated.in=" + UPDATED_date_created);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated is not null
        defaultBLCustomerShouldBeFound("dateCreated.specified=true");

        // Get all the bLCustomerList where dateCreated is null
        defaultBLCustomerShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByauthoriseByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where authoriseBy equals to DEFAULT_AUTHORISE_BY
        defaultBLCustomerShouldBeFound("authoriseBy.equals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerList where authoriseBy equals to UPDATED_authoriseBy
        defaultBLCustomerShouldNotBeFound("authoriseBy.equals=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByauthoriseByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where authoriseBy not equals to DEFAULT_AUTHORISE_BY
        defaultBLCustomerShouldNotBeFound("authoriseBy.notEquals=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerList where authoriseBy not equals to UPDATED_authoriseBy
        defaultBLCustomerShouldBeFound("authoriseBy.notEquals=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByauthoriseByIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where authoriseBy in DEFAULT_AUTHORISE_BY or UPDATED_authoriseBy
        defaultBLCustomerShouldBeFound("authoriseBy.in=" + DEFAULT_AUTHORISE_BY + "," + UPDATED_AUTHORISE_BY);

        // Get all the bLCustomerList where authoriseBy equals to UPDATED_authoriseBy
        defaultBLCustomerShouldNotBeFound("authoriseBy.in=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByauthoriseByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where authoriseBy is not null
        defaultBLCustomerShouldBeFound("authoriseBy.specified=true");

        // Get all the bLCustomerList where authoriseBy is null
        defaultBLCustomerShouldNotBeFound("authoriseBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByauthoriseByContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where authoriseBy contains DEFAULT_AUTHORISE_BY
        defaultBLCustomerShouldBeFound("authoriseBy.contains=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerList where authoriseBy contains UPDATED_authoriseBy
        defaultBLCustomerShouldNotBeFound("authoriseBy.contains=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByauthoriseByNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where authoriseBy does not contain DEFAULT_AUTHORISE_BY
        defaultBLCustomerShouldNotBeFound("authoriseBy.doesNotContain=" + DEFAULT_AUTHORISE_BY);

        // Get all the bLCustomerList where authoriseBy does not contain UPDATED_authoriseBy
        defaultBLCustomerShouldBeFound("authoriseBy.doesNotContain=" + UPDATED_AUTHORISE_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateAuthorisequalToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateAuthorise equals to UPDATED_DATE_AUTHORISE
        defaultBLCustomerShouldBeFound("dateAuthorise.equals=" + UPDATED_DATE_AUTHORISE);

        // Get all the bLCustomerList where dateAuthorise equals to UPDATED_date_authorise
        defaultBLCustomerShouldNotBeFound("dateAuthorise.equals=" + UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateAuthoriseIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateAuthorise not equals to UPDATED_DATE_AUTHORISE
        defaultBLCustomerShouldNotBeFound("dateAuthorise.notEquals=" + UPDATED_DATE_AUTHORISE);

        // Get all the bLCustomerList where dateAuthorise not equals to UPDATED_date_authorise
        defaultBLCustomerShouldBeFound("dateAuthorise.notEquals=" + UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateAuthoriseIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateAuthorise in UPDATED_DATE_AUTHORISE or UPDATED_date_authorise
        defaultBLCustomerShouldBeFound("dateAuthorise.in=" + UPDATED_DATE_AUTHORISE + "," + UPDATED_DATE_AUTHORISE);

        // Get all the bLCustomerList where dateAuthorise equals to UPDATED_date_authorise
        defaultBLCustomerShouldNotBeFound("dateAuthorise.in=" + UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void getAllBLCustomersBydateAuthoriseIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateAuthorise is not null
        defaultBLCustomerShouldBeFound("dateAuthorise.specified=true");

        // Get all the bLCustomerList where dateAuthorise is null
        defaultBLCustomerShouldNotBeFound("dateAuthorise.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy equals to DEFAULT_CREATED_BY
        defaultBLCustomerShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerList where createdBy equals to UPDATED_CREATED_BY
        defaultBLCustomerShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy not equals to DEFAULT_CREATED_BY
        defaultBLCustomerShouldNotBeFound("createdBy.notEquals=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerList where createdBy not equals to UPDATED_CREATED_BY
        defaultBLCustomerShouldBeFound("createdBy.notEquals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
        defaultBLCustomerShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

        // Get all the bLCustomerList where createdBy equals to UPDATED_CREATED_BY
        defaultBLCustomerShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy is not null
        defaultBLCustomerShouldBeFound("createdBy.specified=true");

        // Get all the bLCustomerList where createdBy is null
        defaultBLCustomerShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy contains DEFAULT_CREATED_BY
        defaultBLCustomerShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerList where createdBy contains UPDATED_CREATED_BY
        defaultBLCustomerShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where createdBy does not contain DEFAULT_CREATED_BY
        defaultBLCustomerShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

        // Get all the bLCustomerList where createdBy does not contain UPDATED_CREATED_BY
        defaultBLCustomerShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated equals to DEFAULT_DATE_CREATED
        defaultBLCustomerShouldBeFound("dateCreated.equals=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLCustomerShouldNotBeFound("dateCreated.equals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated not equals to DEFAULT_DATE_CREATED
        defaultBLCustomerShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerList where dateCreated not equals to UPDATED_DATE_CREATED
        defaultBLCustomerShouldBeFound("dateCreated.notEquals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated in DEFAULT_DATE_CREATED or UPDATED_DATE_CREATED
        defaultBLCustomerShouldBeFound("dateCreated.in=" + DEFAULT_DATE_CREATED + "," + UPDATED_DATE_CREATED);

        // Get all the bLCustomerList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLCustomerShouldNotBeFound("dateCreated.in=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated is not null
        defaultBLCustomerShouldBeFound("dateCreated.specified=true");

        // Get all the bLCustomerList where dateCreated is null
        defaultBLCustomerShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateCreatedContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated contains DEFAULT_DATE_CREATED
        defaultBLCustomerShouldBeFound("dateCreated.contains=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerList where dateCreated contains UPDATED_DATE_CREATED
        defaultBLCustomerShouldNotBeFound("dateCreated.contains=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLCustomersByDateCreatedNotContainsSomething() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        // Get all the bLCustomerList where dateCreated does not contain DEFAULT_DATE_CREATED
        defaultBLCustomerShouldNotBeFound("dateCreated.doesNotContain=" + DEFAULT_DATE_CREATED);

        // Get all the bLCustomerList where dateCreated does not contain UPDATED_DATE_CREATED
        defaultBLCustomerShouldBeFound("dateCreated.doesNotContain=" + UPDATED_DATE_CREATED);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBLCustomerShouldBeFound(String filter) throws Exception {
        restBLCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLCustomer.getId().intValue())))
            .andExpect(jsonPath("$.[*].fullName").value(hasItem(DEFAULT_FULL_NAME)))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].otherName1").value(hasItem(DEFAULT_OTHER_NAME_1)))
            .andExpect(jsonPath("$.[*].otherName2").value(hasItem(DEFAULT_OTHER_NAME_2)))
            .andExpect(jsonPath("$.[*].otherName3").value(hasItem(DEFAULT_OTHER_NAME_3)))
            .andExpect(jsonPath("$.[*].positionBl").value(hasItem(DEFAULT_POSITION_BL)))
            .andExpect(jsonPath("$.[*].dateOfBirthBl").value(hasItem(DEFAULT_DATE_OF_BIRTH_BL)))
            .andExpect(jsonPath("$.[*].countryBl1").value(hasItem(DEFAULT_COUNTRY_BL_1)))
            .andExpect(jsonPath("$.[*].countryBl2").value(hasItem(DEFAULT_COUNTRY_BL_2)))
            .andExpect(jsonPath("$.[*].legalIdTypeBl1").value(hasItem(DEFAULT_LEGAL_ID_TYPE_BL_1)))
            .andExpect(jsonPath("$.[*].legalIdNumber1").value(hasItem(DEFAULT_LEGAL_ID_NUMBER_1)))
            .andExpect(jsonPath("$.[*].legalIdTypeBl2").value(hasItem(DEFAULT_LEGAL_ID_TYPE_BL_2)))
            .andExpect(jsonPath("$.[*].legalIdNumber2").value(hasItem(DEFAULT_LEGAL_ID_NUMBER_2)))
            .andExpect(jsonPath("$.[*].otherInfLegal1").value(hasItem(DEFAULT_OTHER_INF_LEGAL_1.toString())))
            .andExpect(jsonPath("$.[*].otherInfLegal2").value(hasItem(DEFAULT_OTHER_INF_LEGAL_2.toString())))
            .andExpect(jsonPath("$.[*].addressBl1").value(hasItem(DEFAULT_ADDRESS_BL_1)))
            .andExpect(jsonPath("$.[*].addressBl2").value(hasItem(DEFAULT_ADDRESS_BL_2)))
            .andExpect(jsonPath("$.[*].addressNowBl1").value(hasItem(DEFAULT_ADDRESS_NOW_BL_1)))
            .andExpect(jsonPath("$.[*].addressNowBl2").value(hasItem(DEFAULT_ADDRESS_NOW_BL_2)))
            .andExpect(jsonPath("$.[*].typeBl").value(hasItem(DEFAULT_TYPE_BL)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)))
            .andExpect(jsonPath("$.[*].recordStatus").value(hasItem(DEFAULT_RECORD_STATUS)))
            .andExpect(jsonPath("$.[*].uploadFileId").value(hasItem(DEFAULT_UPLOAD_FILE_ID)))
            .andExpect(jsonPath("$.[*].coCode").value(hasItem(DEFAULT_CO_CODE)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_createdBy)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_date_created.toString())))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateAuthorise").value(hasItem(UPDATED_DATE_AUTHORISE.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)));

        // Check, that the count call also returns 1
        restBLCustomerMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBLCustomerShouldNotBeFound(String filter) throws Exception {
        restBLCustomerMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBLCustomerMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBLCustomer() throws Exception {
        // Get the bLCustomer
        restBLCustomerMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBLCustomer() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();

        // Update the bLCustomer
        BLCustomer updatedBLCustomer = bLCustomerRepository.findById(bLCustomer.getId()).get();
        // Disconnect from session so that the updates on updatedBLCustomer are not directly saved in db
        em.detach(updatedBLCustomer);
        updatedBLCustomer
            .fullName(UPDATED_FULL_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .otherName1(UPDATED_OTHER_NAME_1)
            .otherName2(UPDATED_OTHER_NAME_2)
            .otherName3(UPDATED_OTHER_NAME_3)
            .positionBl(UPDATED_POSITION_BL)
            .dateOfBirthBl(UPDATED_DATE_OF_BIRTH_BL)
            .countryBl1(UPDATED_COUNTRY_BL_1)
            .countryBl2(UPDATED_COUNTRY_BL_2)
            .legalIdTypeBl1(UPDATED_LEGAL_ID_TYPE_BL_1)
            .legalIdNumber1(UPDATED_LEGAL_ID_NUMBER_1)
            .legalIdTypeBl2(UPDATED_LEGAL_ID_TYPE_BL_2)
            .legalIdNumber2(UPDATED_LEGAL_ID_NUMBER_2)
            .otherInfLegal1(UPDATED_OTHER_INF_LEGAL_1)
            .otherInfLegal2(UPDATED_OTHER_INF_LEGAL_2)
            .addressBl1(UPDATED_ADDRESS_BL_1)
            .addressBl2(UPDATED_ADDRESS_BL_2)
            .addressNowBl1(UPDATED_ADDRESS_NOW_BL_1)
            .addressNowBl2(UPDATED_ADDRESS_NOW_BL_2)
            .typeBl(UPDATED_TYPE_BL)
            .source(UPDATED_SOURCE)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadFileId(UPDATED_UPLOAD_FILE_ID)
            .coCode(UPDATED_CO_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBLCustomer.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBLCustomer))
            )
            .andExpect(status().isOk());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
        BLCustomer testBLCustomer = bLCustomerList.get(bLCustomerList.size() - 1);
        assertThat(testBLCustomer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testBLCustomer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testBLCustomer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testBLCustomer.getOtherName1()).isEqualTo(UPDATED_OTHER_NAME_1);
        assertThat(testBLCustomer.getOtherName2()).isEqualTo(UPDATED_OTHER_NAME_2);
        assertThat(testBLCustomer.getOtherName3()).isEqualTo(UPDATED_OTHER_NAME_3);
        assertThat(testBLCustomer.getPositionBl()).isEqualTo(UPDATED_POSITION_BL);
        assertThat(testBLCustomer.getDateOfBirthBl()).isEqualTo(UPDATED_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomer.getCountryBl1()).isEqualTo(UPDATED_COUNTRY_BL_1);
        assertThat(testBLCustomer.getCountryBl2()).isEqualTo(UPDATED_COUNTRY_BL_2);
        assertThat(testBLCustomer.getLegalIdTypeBl1()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL_1);
        assertThat(testBLCustomer.getLegalIdNumber1()).isEqualTo(UPDATED_LEGAL_ID_NUMBER_1);
        assertThat(testBLCustomer.getLegalIdTypeBl2()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL_2);
        assertThat(testBLCustomer.getLegalIdNumber2()).isEqualTo(UPDATED_LEGAL_ID_NUMBER_2);
        assertThat(testBLCustomer.getOtherInfLegal1()).isEqualTo(UPDATED_OTHER_INF_LEGAL_1);
        assertThat(testBLCustomer.getOtherInfLegal2()).isEqualTo(UPDATED_OTHER_INF_LEGAL_2);
        assertThat(testBLCustomer.getAddressBl1()).isEqualTo(UPDATED_ADDRESS_BL_1);
        assertThat(testBLCustomer.getAddressBl2()).isEqualTo(UPDATED_ADDRESS_BL_2);
        assertThat(testBLCustomer.getAddressNowBl1()).isEqualTo(UPDATED_ADDRESS_NOW_BL_1);
        assertThat(testBLCustomer.getAddressNowBl2()).isEqualTo(UPDATED_ADDRESS_NOW_BL_2);
        assertThat(testBLCustomer.getTypeBl()).isEqualTo(UPDATED_TYPE_BL);
        assertThat(testBLCustomer.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testBLCustomer.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLCustomer.getUploadFileId()).isEqualTo(UPDATED_UPLOAD_FILE_ID);
        assertThat(testBLCustomer.getCoCode()).isEqualTo(UPDATED_CO_CODE);

        assertThat(testBLCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCustomer.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testBLCustomer.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCustomer.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void putNonExistingBLCustomer() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();
        bLCustomer.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bLCustomer.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBLCustomer() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();
        bLCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBLCustomer() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();
        bLCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBLCustomerWithPatch() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();

        // Update the bLCustomer using partial update
        BLCustomer partialUpdatedBLCustomer = new BLCustomer();
        partialUpdatedBLCustomer.setId(bLCustomer.getId());

        partialUpdatedBLCustomer
            .positionBl(UPDATED_POSITION_BL)
            .dateOfBirthBl(UPDATED_DATE_OF_BIRTH_BL)
            .countryBl1(UPDATED_COUNTRY_BL_1)
            .countryBl2(UPDATED_COUNTRY_BL_2)
            .legalIdTypeBl1(UPDATED_LEGAL_ID_TYPE_BL_1)
            .legalIdTypeBl2(UPDATED_LEGAL_ID_TYPE_BL_2)
            .addressBl1(UPDATED_ADDRESS_BL_1)
            .addressBl2(UPDATED_ADDRESS_BL_2)
            .addressNowBl1(UPDATED_ADDRESS_NOW_BL_1)
            .addressNowBl2(UPDATED_ADDRESS_NOW_BL_2)
            .typeBl(UPDATED_TYPE_BL)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLCustomer.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLCustomer))
            )
            .andExpect(status().isOk());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
        BLCustomer testBLCustomer = bLCustomerList.get(bLCustomerList.size() - 1);
        assertThat(testBLCustomer.getFullName()).isEqualTo(DEFAULT_FULL_NAME);
        assertThat(testBLCustomer.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testBLCustomer.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testBLCustomer.getOtherName1()).isEqualTo(DEFAULT_OTHER_NAME_1);
        assertThat(testBLCustomer.getOtherName2()).isEqualTo(DEFAULT_OTHER_NAME_2);
        assertThat(testBLCustomer.getOtherName3()).isEqualTo(DEFAULT_OTHER_NAME_3);
        assertThat(testBLCustomer.getPositionBl()).isEqualTo(UPDATED_POSITION_BL);
        assertThat(testBLCustomer.getDateOfBirthBl()).isEqualTo(UPDATED_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomer.getCountryBl1()).isEqualTo(UPDATED_COUNTRY_BL_1);
        assertThat(testBLCustomer.getCountryBl2()).isEqualTo(UPDATED_COUNTRY_BL_2);
        assertThat(testBLCustomer.getLegalIdTypeBl1()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL_1);
        assertThat(testBLCustomer.getLegalIdNumber1()).isEqualTo(DEFAULT_LEGAL_ID_NUMBER_1);
        assertThat(testBLCustomer.getLegalIdTypeBl2()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL_2);
        assertThat(testBLCustomer.getLegalIdNumber2()).isEqualTo(DEFAULT_LEGAL_ID_NUMBER_2);
        assertThat(testBLCustomer.getOtherInfLegal1()).isEqualTo(DEFAULT_OTHER_INF_LEGAL_1);
        assertThat(testBLCustomer.getOtherInfLegal2()).isEqualTo(DEFAULT_OTHER_INF_LEGAL_2);
        assertThat(testBLCustomer.getAddressBl1()).isEqualTo(UPDATED_ADDRESS_BL_1);
        assertThat(testBLCustomer.getAddressBl2()).isEqualTo(UPDATED_ADDRESS_BL_2);
        assertThat(testBLCustomer.getAddressNowBl1()).isEqualTo(UPDATED_ADDRESS_NOW_BL_1);
        assertThat(testBLCustomer.getAddressNowBl2()).isEqualTo(UPDATED_ADDRESS_NOW_BL_2);
        assertThat(testBLCustomer.getTypeBl()).isEqualTo(UPDATED_TYPE_BL);
        assertThat(testBLCustomer.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testBLCustomer.getRecordStatus()).isEqualTo(DEFAULT_RECORD_STATUS);
        assertThat(testBLCustomer.getUploadFileId()).isEqualTo(DEFAULT_UPLOAD_FILE_ID);
        assertThat(testBLCustomer.getCoCode()).isEqualTo(DEFAULT_CO_CODE);
        assertThat(testBLCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCustomer.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testBLCustomer.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCustomer.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void fullUpdateBLCustomerWithPatch() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();

        // Update the bLCustomer using partial update
        BLCustomer partialUpdatedBLCustomer = new BLCustomer();
        partialUpdatedBLCustomer.setId(bLCustomer.getId());

        partialUpdatedBLCustomer
            .fullName(UPDATED_FULL_NAME)
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .otherName1(UPDATED_OTHER_NAME_1)
            .otherName2(UPDATED_OTHER_NAME_2)
            .otherName3(UPDATED_OTHER_NAME_3)
            .positionBl(UPDATED_POSITION_BL)
            .dateOfBirthBl(UPDATED_DATE_OF_BIRTH_BL)
            .countryBl1(UPDATED_COUNTRY_BL_1)
            .countryBl2(UPDATED_COUNTRY_BL_2)
            .legalIdTypeBl1(UPDATED_LEGAL_ID_TYPE_BL_1)
            .legalIdNumber1(UPDATED_LEGAL_ID_NUMBER_1)
            .legalIdTypeBl2(UPDATED_LEGAL_ID_TYPE_BL_2)
            .legalIdNumber2(UPDATED_LEGAL_ID_NUMBER_2)
            .otherInfLegal1(UPDATED_OTHER_INF_LEGAL_1)
            .otherInfLegal2(UPDATED_OTHER_INF_LEGAL_2)
            .addressBl1(UPDATED_ADDRESS_BL_1)
            .addressBl2(UPDATED_ADDRESS_BL_2)
            .addressNowBl1(UPDATED_ADDRESS_NOW_BL_1)
            .addressNowBl2(UPDATED_ADDRESS_NOW_BL_2)
            .typeBl(UPDATED_TYPE_BL)
            .source(UPDATED_SOURCE)
            .recordStatus(UPDATED_RECORD_STATUS)
            .uploadFileId(UPDATED_UPLOAD_FILE_ID)
            .coCode(UPDATED_CO_CODE)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLCustomer.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLCustomer))
            )
            .andExpect(status().isOk());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
        BLCustomer testBLCustomer = bLCustomerList.get(bLCustomerList.size() - 1);
        assertThat(testBLCustomer.getFullName()).isEqualTo(UPDATED_FULL_NAME);
        assertThat(testBLCustomer.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testBLCustomer.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testBLCustomer.getOtherName1()).isEqualTo(UPDATED_OTHER_NAME_1);
        assertThat(testBLCustomer.getOtherName2()).isEqualTo(UPDATED_OTHER_NAME_2);
        assertThat(testBLCustomer.getOtherName3()).isEqualTo(UPDATED_OTHER_NAME_3);
        assertThat(testBLCustomer.getPositionBl()).isEqualTo(UPDATED_POSITION_BL);
        assertThat(testBLCustomer.getDateOfBirthBl()).isEqualTo(UPDATED_DATE_OF_BIRTH_BL);
        assertThat(testBLCustomer.getCountryBl1()).isEqualTo(UPDATED_COUNTRY_BL_1);
        assertThat(testBLCustomer.getCountryBl2()).isEqualTo(UPDATED_COUNTRY_BL_2);
        assertThat(testBLCustomer.getLegalIdTypeBl1()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL_1);
        assertThat(testBLCustomer.getLegalIdNumber1()).isEqualTo(UPDATED_LEGAL_ID_NUMBER_1);
        assertThat(testBLCustomer.getLegalIdTypeBl2()).isEqualTo(UPDATED_LEGAL_ID_TYPE_BL_2);
        assertThat(testBLCustomer.getLegalIdNumber2()).isEqualTo(UPDATED_LEGAL_ID_NUMBER_2);
        assertThat(testBLCustomer.getOtherInfLegal1()).isEqualTo(UPDATED_OTHER_INF_LEGAL_1);
        assertThat(testBLCustomer.getOtherInfLegal2()).isEqualTo(UPDATED_OTHER_INF_LEGAL_2);
        assertThat(testBLCustomer.getAddressBl1()).isEqualTo(UPDATED_ADDRESS_BL_1);
        assertThat(testBLCustomer.getAddressBl2()).isEqualTo(UPDATED_ADDRESS_BL_2);
        assertThat(testBLCustomer.getAddressNowBl1()).isEqualTo(UPDATED_ADDRESS_NOW_BL_1);
        assertThat(testBLCustomer.getAddressNowBl2()).isEqualTo(UPDATED_ADDRESS_NOW_BL_2);
        assertThat(testBLCustomer.getTypeBl()).isEqualTo(UPDATED_TYPE_BL);
        assertThat(testBLCustomer.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testBLCustomer.getRecordStatus()).isEqualTo(UPDATED_RECORD_STATUS);
        assertThat(testBLCustomer.getUploadFileId()).isEqualTo(UPDATED_UPLOAD_FILE_ID);
        assertThat(testBLCustomer.getCoCode()).isEqualTo(UPDATED_CO_CODE);

        assertThat(testBLCustomer.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCustomer.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testBLCustomer.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCustomer.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void patchNonExistingBLCustomer() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();
        bLCustomer.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bLCustomer.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBLCustomer() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();
        bLCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBLCustomer() throws Exception {
        int databaseSizeBeforeUpdate = bLCustomerRepository.findAll().size();
        bLCustomer.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLCustomerMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCustomer))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLCustomer in the database
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBLCustomer() throws Exception {
        // Initialize the database
        bLCustomerRepository.saveAndFlush(bLCustomer);

        int databaseSizeBeforeDelete = bLCustomerRepository.findAll().size();

        // Delete the bLCustomer
        restBLCustomerMockMvc
            .perform(delete(ENTITY_API_URL_ID, bLCustomer.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BLCustomer> bLCustomerList = bLCustomerRepository.findAll();
        assertThat(bLCustomerList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
