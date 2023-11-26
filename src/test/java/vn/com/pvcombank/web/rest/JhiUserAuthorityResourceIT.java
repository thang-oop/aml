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
import vn.com.pvcombank.domain.JhiUserAuthority;
import vn.com.pvcombank.repository.JhiUserAuthorityRepository;
import vn.com.pvcombank.service.criteria.JhiUserAuthorityCriteria;

/**
 * Integration tests for the {@link JhiUserAuthorityResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JhiUserAuthorityResourceIT {

    private static final String DEFAULT_USER_ID = "AAAAAAAAAA";
    private static final String UPDATED_USER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHORITY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORITY_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/jhi-user-authorities";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JhiUserAuthorityRepository jhiUserAuthorityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJhiUserAuthorityMockMvc;

    private JhiUserAuthority jhiUserAuthority;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JhiUserAuthority createEntity(EntityManager em) {
        JhiUserAuthority jhiUserAuthority = new JhiUserAuthority().userId(DEFAULT_USER_ID).authorityName(DEFAULT_AUTHORITY_NAME);
        return jhiUserAuthority;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JhiUserAuthority createUpdatedEntity(EntityManager em) {
        JhiUserAuthority jhiUserAuthority = new JhiUserAuthority().userId(UPDATED_USER_ID).authorityName(UPDATED_AUTHORITY_NAME);
        return jhiUserAuthority;
    }

    @BeforeEach
    public void initTest() {
        jhiUserAuthority = createEntity(em);
    }

    @Test
    @Transactional
    void createJhiUserAuthority() throws Exception {
        int databaseSizeBeforeCreate = jhiUserAuthorityRepository.findAll().size();
        // Create the JhiUserAuthority
        restJhiUserAuthorityMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isCreated());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeCreate + 1);
        JhiUserAuthority testJhiUserAuthority = jhiUserAuthorityList.get(jhiUserAuthorityList.size() - 1);
        assertThat(testJhiUserAuthority.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testJhiUserAuthority.getAuthorityName()).isEqualTo(DEFAULT_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void createJhiUserAuthorityWithExistingId() throws Exception {
        // Create the JhiUserAuthority with an existing ID
        jhiUserAuthority.setId(1L);

        int databaseSizeBeforeCreate = jhiUserAuthorityRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJhiUserAuthorityMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isBadRequest());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthorities() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList
        restJhiUserAuthorityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jhiUserAuthority.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].authorityName").value(hasItem(DEFAULT_AUTHORITY_NAME)));
    }

    @Test
    @Transactional
    void getJhiUserAuthority() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get the jhiUserAuthority
        restJhiUserAuthorityMockMvc
            .perform(get(ENTITY_API_URL_ID, jhiUserAuthority.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jhiUserAuthority.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID))
            .andExpect(jsonPath("$.authorityName").value(DEFAULT_AUTHORITY_NAME));
    }

    @Test
    @Transactional
    void getJhiUserAuthoritiesByIdFiltering() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        Long id = jhiUserAuthority.getId();

        defaultJhiUserAuthorityShouldBeFound("id.equals=" + id);
        defaultJhiUserAuthorityShouldNotBeFound("id.notEquals=" + id);

        defaultJhiUserAuthorityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultJhiUserAuthorityShouldNotBeFound("id.greaterThan=" + id);

        defaultJhiUserAuthorityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultJhiUserAuthorityShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByUserIdIsEqualToSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where userId equals to DEFAULT_USER_ID
        defaultJhiUserAuthorityShouldBeFound("userId.equals=" + DEFAULT_USER_ID);

        // Get all the jhiUserAuthorityList where userId equals to UPDATED_USER_ID
        defaultJhiUserAuthorityShouldNotBeFound("userId.equals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByUserIdIsNotEqualToSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where userId not equals to DEFAULT_USER_ID
        defaultJhiUserAuthorityShouldNotBeFound("userId.notEquals=" + DEFAULT_USER_ID);

        // Get all the jhiUserAuthorityList where userId not equals to UPDATED_USER_ID
        defaultJhiUserAuthorityShouldBeFound("userId.notEquals=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByUserIdIsInShouldWork() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where userId in DEFAULT_USER_ID or UPDATED_USER_ID
        defaultJhiUserAuthorityShouldBeFound("userId.in=" + DEFAULT_USER_ID + "," + UPDATED_USER_ID);

        // Get all the jhiUserAuthorityList where userId equals to UPDATED_USER_ID
        defaultJhiUserAuthorityShouldNotBeFound("userId.in=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByUserIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where userId is not null
        defaultJhiUserAuthorityShouldBeFound("userId.specified=true");

        // Get all the jhiUserAuthorityList where userId is null
        defaultJhiUserAuthorityShouldNotBeFound("userId.specified=false");
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByUserIdContainsSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where userId contains DEFAULT_USER_ID
        defaultJhiUserAuthorityShouldBeFound("userId.contains=" + DEFAULT_USER_ID);

        // Get all the jhiUserAuthorityList where userId contains UPDATED_USER_ID
        defaultJhiUserAuthorityShouldNotBeFound("userId.contains=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByUserIdNotContainsSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where userId does not contain DEFAULT_USER_ID
        defaultJhiUserAuthorityShouldNotBeFound("userId.doesNotContain=" + DEFAULT_USER_ID);

        // Get all the jhiUserAuthorityList where userId does not contain UPDATED_USER_ID
        defaultJhiUserAuthorityShouldBeFound("userId.doesNotContain=" + UPDATED_USER_ID);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByAuthorityNameIsEqualToSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where authorityName equals to DEFAULT_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldBeFound("authorityName.equals=" + DEFAULT_AUTHORITY_NAME);

        // Get all the jhiUserAuthorityList where authorityName equals to UPDATED_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldNotBeFound("authorityName.equals=" + UPDATED_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByAuthorityNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where authorityName not equals to DEFAULT_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldNotBeFound("authorityName.notEquals=" + DEFAULT_AUTHORITY_NAME);

        // Get all the jhiUserAuthorityList where authorityName not equals to UPDATED_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldBeFound("authorityName.notEquals=" + UPDATED_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByAuthorityNameIsInShouldWork() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where authorityName in DEFAULT_AUTHORITY_NAME or UPDATED_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldBeFound("authorityName.in=" + DEFAULT_AUTHORITY_NAME + "," + UPDATED_AUTHORITY_NAME);

        // Get all the jhiUserAuthorityList where authorityName equals to UPDATED_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldNotBeFound("authorityName.in=" + UPDATED_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByAuthorityNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where authorityName is not null
        defaultJhiUserAuthorityShouldBeFound("authorityName.specified=true");

        // Get all the jhiUserAuthorityList where authorityName is null
        defaultJhiUserAuthorityShouldNotBeFound("authorityName.specified=false");
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByAuthorityNameContainsSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where authorityName contains DEFAULT_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldBeFound("authorityName.contains=" + DEFAULT_AUTHORITY_NAME);

        // Get all the jhiUserAuthorityList where authorityName contains UPDATED_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldNotBeFound("authorityName.contains=" + UPDATED_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void getAllJhiUserAuthoritiesByAuthorityNameNotContainsSomething() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        // Get all the jhiUserAuthorityList where authorityName does not contain DEFAULT_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldNotBeFound("authorityName.doesNotContain=" + DEFAULT_AUTHORITY_NAME);

        // Get all the jhiUserAuthorityList where authorityName does not contain UPDATED_AUTHORITY_NAME
        defaultJhiUserAuthorityShouldBeFound("authorityName.doesNotContain=" + UPDATED_AUTHORITY_NAME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultJhiUserAuthorityShouldBeFound(String filter) throws Exception {
        restJhiUserAuthorityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jhiUserAuthority.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID)))
            .andExpect(jsonPath("$.[*].authorityName").value(hasItem(DEFAULT_AUTHORITY_NAME)));

        // Check, that the count call also returns 1
        restJhiUserAuthorityMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultJhiUserAuthorityShouldNotBeFound(String filter) throws Exception {
        restJhiUserAuthorityMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restJhiUserAuthorityMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingJhiUserAuthority() throws Exception {
        // Get the jhiUserAuthority
        restJhiUserAuthorityMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJhiUserAuthority() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();

        // Update the jhiUserAuthority
        JhiUserAuthority updatedJhiUserAuthority = jhiUserAuthorityRepository.findById(jhiUserAuthority.getId()).get();
        // Disconnect from session so that the updates on updatedJhiUserAuthority are not directly saved in db
        em.detach(updatedJhiUserAuthority);
        updatedJhiUserAuthority.userId(UPDATED_USER_ID).authorityName(UPDATED_AUTHORITY_NAME);

        restJhiUserAuthorityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJhiUserAuthority.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJhiUserAuthority))
            )
            .andExpect(status().isOk());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
        JhiUserAuthority testJhiUserAuthority = jhiUserAuthorityList.get(jhiUserAuthorityList.size() - 1);
        assertThat(testJhiUserAuthority.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testJhiUserAuthority.getAuthorityName()).isEqualTo(UPDATED_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void putNonExistingJhiUserAuthority() throws Exception {
        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();
        jhiUserAuthority.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJhiUserAuthorityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jhiUserAuthority.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isBadRequest());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJhiUserAuthority() throws Exception {
        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();
        jhiUserAuthority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJhiUserAuthorityMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isBadRequest());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJhiUserAuthority() throws Exception {
        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();
        jhiUserAuthority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJhiUserAuthorityMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJhiUserAuthorityWithPatch() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();

        // Update the jhiUserAuthority using partial update
        JhiUserAuthority partialUpdatedJhiUserAuthority = new JhiUserAuthority();
        partialUpdatedJhiUserAuthority.setId(jhiUserAuthority.getId());

        restJhiUserAuthorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJhiUserAuthority.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJhiUserAuthority))
            )
            .andExpect(status().isOk());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
        JhiUserAuthority testJhiUserAuthority = jhiUserAuthorityList.get(jhiUserAuthorityList.size() - 1);
        assertThat(testJhiUserAuthority.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testJhiUserAuthority.getAuthorityName()).isEqualTo(DEFAULT_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void fullUpdateJhiUserAuthorityWithPatch() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();

        // Update the jhiUserAuthority using partial update
        JhiUserAuthority partialUpdatedJhiUserAuthority = new JhiUserAuthority();
        partialUpdatedJhiUserAuthority.setId(jhiUserAuthority.getId());

        partialUpdatedJhiUserAuthority.userId(UPDATED_USER_ID).authorityName(UPDATED_AUTHORITY_NAME);

        restJhiUserAuthorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJhiUserAuthority.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJhiUserAuthority))
            )
            .andExpect(status().isOk());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
        JhiUserAuthority testJhiUserAuthority = jhiUserAuthorityList.get(jhiUserAuthorityList.size() - 1);
        assertThat(testJhiUserAuthority.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testJhiUserAuthority.getAuthorityName()).isEqualTo(UPDATED_AUTHORITY_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingJhiUserAuthority() throws Exception {
        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();
        jhiUserAuthority.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJhiUserAuthorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jhiUserAuthority.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isBadRequest());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJhiUserAuthority() throws Exception {
        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();
        jhiUserAuthority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJhiUserAuthorityMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isBadRequest());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJhiUserAuthority() throws Exception {
        int databaseSizeBeforeUpdate = jhiUserAuthorityRepository.findAll().size();
        jhiUserAuthority.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJhiUserAuthorityMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jhiUserAuthority))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JhiUserAuthority in the database
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJhiUserAuthority() throws Exception {
        // Initialize the database
        jhiUserAuthorityRepository.saveAndFlush(jhiUserAuthority);

        int databaseSizeBeforeDelete = jhiUserAuthorityRepository.findAll().size();

        // Delete the jhiUserAuthority
        restJhiUserAuthorityMockMvc
            .perform(delete(ENTITY_API_URL_ID, jhiUserAuthority.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JhiUserAuthority> jhiUserAuthorityList = jhiUserAuthorityRepository.findAll();
        assertThat(jhiUserAuthorityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
