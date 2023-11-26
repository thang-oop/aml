package vn.com.pvcombank.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import vn.com.pvcombank.domain.BLParamter;
import vn.com.pvcombank.repository.BLParamterRepository;
import vn.com.pvcombank.service.criteria.BLParamterCriteria;

/**
 * Integration tests for the {@link BLParamterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BLParamterResourceIT {

    private static final String DEFAULT_KEY_ID = "AAAAAAAAAA";
    private static final String UPDATED_KEY_ID = "BBBBBBBBBB";

    private static final String DEFAULT_KEY_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_KEY_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATED = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bl-paramters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BLParamterRepository bLParamterRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBLParamterMockMvc;

    private BLParamter bLParamter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLParamter createEntity(EntityManager em) {
        BLParamter bLParamter = new BLParamter()
            .keyId(DEFAULT_KEY_ID)
            .keyValue(DEFAULT_KEY_VALUE)
            .description(DEFAULT_DESCRIPTION)
            .createdBy(DEFAULT_CREATED_BY)
            .dateCreated(DEFAULT_DATE_CREATED);
        return bLParamter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLParamter createUpdatedEntity(EntityManager em) {
        BLParamter bLParamter = new BLParamter()
            .keyId(UPDATED_KEY_ID)
            .keyValue(UPDATED_KEY_VALUE)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED);
        return bLParamter;
    }

    @BeforeEach
    public void initTest() {
        bLParamter = createEntity(em);
    }

    @Test
    @Transactional
    void createBLParamter() throws Exception {
        int databaseSizeBeforeCreate = bLParamterRepository.findAll().size();
        // Create the BLParamter
        restBLParamterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isCreated());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeCreate + 1);
        BLParamter testBLParamter = bLParamterList.get(bLParamterList.size() - 1);
        assertThat(testBLParamter.getKeyId()).isEqualTo(DEFAULT_KEY_ID);
        assertThat(testBLParamter.getKeyValue()).isEqualTo(DEFAULT_KEY_VALUE);
        assertThat(testBLParamter.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBLParamter.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBLParamter.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
    }

    @Test
    @Transactional
    void createBLParamterWithExistingId() throws Exception {
        // Create the BLParamter with an existing ID
        bLParamter.setId(1L);

        int databaseSizeBeforeCreate = bLParamterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBLParamterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBLParamters() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList
        restBLParamterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLParamter.getId().intValue())))
            .andExpect(jsonPath("$.[*].keyId").value(hasItem(DEFAULT_KEY_ID)))
            .andExpect(jsonPath("$.[*].keyValue").value(hasItem(DEFAULT_KEY_VALUE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)));
    }

    @Test
    @Transactional
    void getBLParamter() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get the bLParamter
        restBLParamterMockMvc
            .perform(get(ENTITY_API_URL_ID, bLParamter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bLParamter.getId().intValue()))
            .andExpect(jsonPath("$.keyId").value(DEFAULT_KEY_ID))
            .andExpect(jsonPath("$.keyValue").value(DEFAULT_KEY_VALUE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED));
    }

    @Test
    @Transactional
    void getBLParamtersByIdFiltering() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        Long id = bLParamter.getId();

        defaultBLParamterShouldBeFound("id.equals=" + id);
        defaultBLParamterShouldNotBeFound("id.notEquals=" + id);

        defaultBLParamterShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultBLParamterShouldNotBeFound("id.greaterThan=" + id);

        defaultBLParamterShouldBeFound("id.lessThanOrEqual=" + id);
        defaultBLParamterShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyIdIsEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyId equals to DEFAULT_KEY_ID
        defaultBLParamterShouldBeFound("keyId.equals=" + DEFAULT_KEY_ID);

        // Get all the bLParamterList where keyId equals to UPDATED_KEY_ID
        defaultBLParamterShouldNotBeFound("keyId.equals=" + UPDATED_KEY_ID);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyId not equals to DEFAULT_KEY_ID
        defaultBLParamterShouldNotBeFound("keyId.notEquals=" + DEFAULT_KEY_ID);

        // Get all the bLParamterList where keyId not equals to UPDATED_KEY_ID
        defaultBLParamterShouldBeFound("keyId.notEquals=" + UPDATED_KEY_ID);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyIdIsInShouldWork() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyId in DEFAULT_KEY_ID or UPDATED_KEY_ID
        defaultBLParamterShouldBeFound("keyId.in=" + DEFAULT_KEY_ID + "," + UPDATED_KEY_ID);

        // Get all the bLParamterList where keyId equals to UPDATED_KEY_ID
        defaultBLParamterShouldNotBeFound("keyId.in=" + UPDATED_KEY_ID);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyId is not null
        defaultBLParamterShouldBeFound("keyId.specified=true");

        // Get all the bLParamterList where keyId is null
        defaultBLParamterShouldNotBeFound("keyId.specified=false");
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyIdContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyId contains DEFAULT_KEY_ID
        defaultBLParamterShouldBeFound("keyId.contains=" + DEFAULT_KEY_ID);

        // Get all the bLParamterList where keyId contains UPDATED_KEY_ID
        defaultBLParamterShouldNotBeFound("keyId.contains=" + UPDATED_KEY_ID);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyIdNotContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyId does not contain DEFAULT_KEY_ID
        defaultBLParamterShouldNotBeFound("keyId.doesNotContain=" + DEFAULT_KEY_ID);

        // Get all the bLParamterList where keyId does not contain UPDATED_KEY_ID
        defaultBLParamterShouldBeFound("keyId.doesNotContain=" + UPDATED_KEY_ID);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyValueIsEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyValue equals to DEFAULT_KEY_VALUE
        defaultBLParamterShouldBeFound("keyValue.equals=" + DEFAULT_KEY_VALUE);

        // Get all the bLParamterList where keyValue equals to UPDATED_KEY_VALUE
        defaultBLParamterShouldNotBeFound("keyValue.equals=" + UPDATED_KEY_VALUE);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyValueIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyValue not equals to DEFAULT_KEY_VALUE
        defaultBLParamterShouldNotBeFound("keyValue.notEquals=" + DEFAULT_KEY_VALUE);

        // Get all the bLParamterList where keyValue not equals to UPDATED_KEY_VALUE
        defaultBLParamterShouldBeFound("keyValue.notEquals=" + UPDATED_KEY_VALUE);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyValueIsInShouldWork() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyValue in DEFAULT_KEY_VALUE or UPDATED_KEY_VALUE
        defaultBLParamterShouldBeFound("keyValue.in=" + DEFAULT_KEY_VALUE + "," + UPDATED_KEY_VALUE);

        // Get all the bLParamterList where keyValue equals to UPDATED_KEY_VALUE
        defaultBLParamterShouldNotBeFound("keyValue.in=" + UPDATED_KEY_VALUE);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyValue is not null
        defaultBLParamterShouldBeFound("keyValue.specified=true");

        // Get all the bLParamterList where keyValue is null
        defaultBLParamterShouldNotBeFound("keyValue.specified=false");
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyValueContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyValue contains DEFAULT_KEY_VALUE
        defaultBLParamterShouldBeFound("keyValue.contains=" + DEFAULT_KEY_VALUE);

        // Get all the bLParamterList where keyValue contains UPDATED_KEY_VALUE
        defaultBLParamterShouldNotBeFound("keyValue.contains=" + UPDATED_KEY_VALUE);
    }

    @Test
    @Transactional
    void getAllBLParamtersByKeyValueNotContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where keyValue does not contain DEFAULT_KEY_VALUE
        defaultBLParamterShouldNotBeFound("keyValue.doesNotContain=" + DEFAULT_KEY_VALUE);

        // Get all the bLParamterList where keyValue does not contain UPDATED_KEY_VALUE
        defaultBLParamterShouldBeFound("keyValue.doesNotContain=" + UPDATED_KEY_VALUE);
    }

    @Test
    @Transactional
    void getAllBLParamtersByOperatorIsEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByOperatorIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByOperatorIsInShouldWork() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByOperatorIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where operator is not null
        defaultBLParamterShouldBeFound("operator.specified=true");

        // Get all the bLParamterList where operator is null
        defaultBLParamterShouldNotBeFound("operator.specified=false");
    }

    @Test
    @Transactional
    void getAllBLParamtersByOperatorContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByOperatorNotContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByParentKeyIsEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByParentKeyIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByParentKeyIsInShouldWork() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByParentKeyIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where parentKey is not null
        defaultBLParamterShouldBeFound("parentKey.specified=true");

        // Get all the bLParamterList where parentKey is null
        defaultBLParamterShouldNotBeFound("parentKey.specified=false");
    }

    @Test
    @Transactional
    void getAllBLParamtersByParentKeyContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByParentKeyNotContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where description equals to DEFAULT_DESCRIPTION
        defaultBLParamterShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the bLParamterList where description equals to UPDATED_DESCRIPTION
        defaultBLParamterShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDescriptionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where description not equals to DEFAULT_DESCRIPTION
        defaultBLParamterShouldNotBeFound("description.notEquals=" + DEFAULT_DESCRIPTION);

        // Get all the bLParamterList where description not equals to UPDATED_DESCRIPTION
        defaultBLParamterShouldBeFound("description.notEquals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultBLParamterShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the bLParamterList where description equals to UPDATED_DESCRIPTION
        defaultBLParamterShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where description is not null
        defaultBLParamterShouldBeFound("description.specified=true");

        // Get all the bLParamterList where description is null
        defaultBLParamterShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllBLParamtersByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where description contains DEFAULT_DESCRIPTION
        defaultBLParamterShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the bLParamterList where description contains UPDATED_DESCRIPTION
        defaultBLParamterShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where description does not contain DEFAULT_DESCRIPTION
        defaultBLParamterShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the bLParamterList where description does not contain UPDATED_DESCRIPTION
        defaultBLParamterShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllBLParamtersByCreatedByIsEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where createdBy equals to DEFAULT_CREATED_BY
        defaultBLParamterShouldBeFound("createdBy.equals=" + DEFAULT_CREATED_BY);

        // Get all the bLParamterList where createdBy equals to UPDATED_CREATED_BY
        defaultBLParamterShouldNotBeFound("createdBy.equals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLParamtersByCreatedByIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where createdBy not equals to DEFAULT_CREATED_BY
        defaultBLParamterShouldNotBeFound("createdBy.notEquals=" + DEFAULT_CREATED_BY);

        // Get all the bLParamterList where createdBy not equals to UPDATED_CREATED_BY
        defaultBLParamterShouldBeFound("createdBy.notEquals=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLParamtersByCreatedByIsInShouldWork() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where createdBy in DEFAULT_CREATED_BY or UPDATED_CREATED_BY
        defaultBLParamterShouldBeFound("createdBy.in=" + DEFAULT_CREATED_BY + "," + UPDATED_CREATED_BY);

        // Get all the bLParamterList where createdBy equals to UPDATED_CREATED_BY
        defaultBLParamterShouldNotBeFound("createdBy.in=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLParamtersByCreatedByIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where createdBy is not null
        defaultBLParamterShouldBeFound("createdBy.specified=true");

        // Get all the bLParamterList where createdBy is null
        defaultBLParamterShouldNotBeFound("createdBy.specified=false");
    }

    @Test
    @Transactional
    void getAllBLParamtersByCreatedByContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where createdBy contains DEFAULT_CREATED_BY
        defaultBLParamterShouldBeFound("createdBy.contains=" + DEFAULT_CREATED_BY);

        // Get all the bLParamterList where createdBy contains UPDATED_CREATED_BY
        defaultBLParamterShouldNotBeFound("createdBy.contains=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLParamtersByCreatedByNotContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where createdBy does not contain DEFAULT_CREATED_BY
        defaultBLParamterShouldNotBeFound("createdBy.doesNotContain=" + DEFAULT_CREATED_BY);

        // Get all the bLParamterList where createdBy does not contain UPDATED_CREATED_BY
        defaultBLParamterShouldBeFound("createdBy.doesNotContain=" + UPDATED_CREATED_BY);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where dateCreated equals to DEFAULT_DATE_CREATED
        defaultBLParamterShouldBeFound("dateCreated.equals=" + DEFAULT_DATE_CREATED);

        // Get all the bLParamterList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLParamterShouldNotBeFound("dateCreated.equals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where dateCreated not equals to DEFAULT_DATE_CREATED
        defaultBLParamterShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_DATE_CREATED);

        // Get all the bLParamterList where dateCreated not equals to UPDATED_DATE_CREATED
        defaultBLParamterShouldBeFound("dateCreated.notEquals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where dateCreated in DEFAULT_DATE_CREATED or UPDATED_DATE_CREATED
        defaultBLParamterShouldBeFound("dateCreated.in=" + DEFAULT_DATE_CREATED + "," + UPDATED_DATE_CREATED);

        // Get all the bLParamterList where dateCreated equals to UPDATED_DATE_CREATED
        defaultBLParamterShouldNotBeFound("dateCreated.in=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where dateCreated is not null
        defaultBLParamterShouldBeFound("dateCreated.specified=true");

        // Get all the bLParamterList where dateCreated is null
        defaultBLParamterShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    void getAllBLParamtersByDateCreatedContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where dateCreated contains DEFAULT_DATE_CREATED
        defaultBLParamterShouldBeFound("dateCreated.contains=" + DEFAULT_DATE_CREATED);

        // Get all the bLParamterList where dateCreated contains UPDATED_DATE_CREATED
        defaultBLParamterShouldNotBeFound("dateCreated.contains=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void getAllBLParamtersByDateCreatedNotContainsSomething() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        // Get all the bLParamterList where dateCreated does not contain DEFAULT_DATE_CREATED
        defaultBLParamterShouldNotBeFound("dateCreated.doesNotContain=" + DEFAULT_DATE_CREATED);

        // Get all the bLParamterList where dateCreated does not contain UPDATED_DATE_CREATED
        defaultBLParamterShouldBeFound("dateCreated.doesNotContain=" + UPDATED_DATE_CREATED);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultBLParamterShouldBeFound(String filter) throws Exception {
        restBLParamterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLParamter.getId().intValue())))
            .andExpect(jsonPath("$.[*].keyId").value(hasItem(DEFAULT_KEY_ID)))
            .andExpect(jsonPath("$.[*].keyValue").value(hasItem(DEFAULT_KEY_VALUE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)));

        // Check, that the count call also returns 1
        restBLParamterMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultBLParamterShouldNotBeFound(String filter) throws Exception {
        restBLParamterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restBLParamterMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingBLParamter() throws Exception {
        // Get the bLParamter
        restBLParamterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBLParamter() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();

        // Update the bLParamter
        BLParamter updatedBLParamter = bLParamterRepository.findById(bLParamter.getId()).get();
        // Disconnect from session so that the updates on updatedBLParamter are not directly saved in db
        em.detach(updatedBLParamter);
        updatedBLParamter
            .keyId(UPDATED_KEY_ID)
            .keyValue(UPDATED_KEY_VALUE)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED);

        restBLParamterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBLParamter.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBLParamter))
            )
            .andExpect(status().isOk());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
        BLParamter testBLParamter = bLParamterList.get(bLParamterList.size() - 1);
        assertThat(testBLParamter.getKeyId()).isEqualTo(UPDATED_KEY_ID);
        assertThat(testBLParamter.getKeyValue()).isEqualTo(UPDATED_KEY_VALUE);
        assertThat(testBLParamter.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLParamter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLParamter.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void putNonExistingBLParamter() throws Exception {
        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();
        bLParamter.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLParamterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bLParamter.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBLParamter() throws Exception {
        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();
        bLParamter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLParamterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBLParamter() throws Exception {
        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();
        bLParamter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLParamterMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBLParamterWithPatch() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();

        // Update the bLParamter using partial update
        BLParamter partialUpdatedBLParamter = new BLParamter();
        partialUpdatedBLParamter.setId(bLParamter.getId());

        partialUpdatedBLParamter
            .keyId(UPDATED_KEY_ID)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED);

        restBLParamterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLParamter.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLParamter))
            )
            .andExpect(status().isOk());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
        BLParamter testBLParamter = bLParamterList.get(bLParamterList.size() - 1);
        assertThat(testBLParamter.getKeyId()).isEqualTo(UPDATED_KEY_ID);
        assertThat(testBLParamter.getKeyValue()).isEqualTo(DEFAULT_KEY_VALUE);
        assertThat(testBLParamter.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLParamter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLParamter.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void fullUpdateBLParamterWithPatch() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();

        // Update the bLParamter using partial update
        BLParamter partialUpdatedBLParamter = new BLParamter();
        partialUpdatedBLParamter.setId(bLParamter.getId());

        partialUpdatedBLParamter
            .keyId(UPDATED_KEY_ID)
            .keyValue(UPDATED_KEY_VALUE)
            .description(UPDATED_DESCRIPTION)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED);

        restBLParamterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLParamter.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLParamter))
            )
            .andExpect(status().isOk());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
        BLParamter testBLParamter = bLParamterList.get(bLParamterList.size() - 1);
        assertThat(testBLParamter.getKeyId()).isEqualTo(UPDATED_KEY_ID);
        assertThat(testBLParamter.getKeyValue()).isEqualTo(UPDATED_KEY_VALUE);
        assertThat(testBLParamter.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLParamter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLParamter.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    void patchNonExistingBLParamter() throws Exception {
        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();
        bLParamter.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLParamterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bLParamter.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBLParamter() throws Exception {
        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();
        bLParamter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLParamterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBLParamter() throws Exception {
        int databaseSizeBeforeUpdate = bLParamterRepository.findAll().size();
        bLParamter.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLParamterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLParamter))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLParamter in the database
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBLParamter() throws Exception {
        // Initialize the database
        bLParamterRepository.saveAndFlush(bLParamter);

        int databaseSizeBeforeDelete = bLParamterRepository.findAll().size();

        // Delete the bLParamter
        restBLParamterMockMvc
            .perform(delete(ENTITY_API_URL_ID, bLParamter.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BLParamter> bLParamterList = bLParamterRepository.findAll();
        assertThat(bLParamterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
