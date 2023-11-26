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
import vn.com.pvcombank.domain.BLRule;
import vn.com.pvcombank.repository.BLRuleRepository;

/**
 * Integration tests for the {@link BLRuleResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BLRuleResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE_IDS = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_IDS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_TYPE = "BBBBBBBBBB";

    private static final Long DEFAULT_SCORE_MINIMUM = 1L;
    private static final Long UPDATED_SCORE_MINIMUM = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATED = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHORISE_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORISE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_AUTHORISE = "AAAAAAAAAA";
    private static final String UPDATED_DATE_AUTHORISE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bl-rules";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BLRuleRepository bLRuleRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBLRuleMockMvc;

    private BLRule bLRule;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLRule createEntity(EntityManager em) {
        BLRule bLRule = new BLRule()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .sourceIds(DEFAULT_SOURCE_IDS)
            .customerType(DEFAULT_CUSTOMER_TYPE)
            .scoreMinimum(DEFAULT_SCORE_MINIMUM)
            .createdBy(DEFAULT_CREATED_BY)
            .dateCreated(DEFAULT_DATE_CREATED)
            .authoriseBy(DEFAULT_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        return bLRule;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLRule createUpdatedEntity(EntityManager em) {
        BLRule bLRule = new BLRule()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .sourceIds(UPDATED_SOURCE_IDS)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .scoreMinimum(UPDATED_SCORE_MINIMUM)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        return bLRule;
    }

    @BeforeEach
    public void initTest() {
        bLRule = createEntity(em);
    }

    @Test
    @Transactional
    void createBLRule() throws Exception {
        int databaseSizeBeforeCreate = bLRuleRepository.findAll().size();
        // Create the BLRule
        restBLRuleMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isCreated());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeCreate + 1);
        BLRule testBLRule = bLRuleList.get(bLRuleList.size() - 1);
        assertThat(testBLRule.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBLRule.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBLRule.getSourceIds()).isEqualTo(DEFAULT_SOURCE_IDS);
        assertThat(testBLRule.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testBLRule.getScoreMinimum()).isEqualTo(DEFAULT_SCORE_MINIMUM);
        assertThat(testBLRule.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBLRule.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testBLRule.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLRule.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void createBLRuleWithExistingId() throws Exception {
        // Create the BLRule with an existing ID
        bLRule.setId(1L);

        int databaseSizeBeforeCreate = bLRuleRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBLRuleMockMvc
            .perform(
                post(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBLRules() throws Exception {
        // Initialize the database
        bLRuleRepository.saveAndFlush(bLRule);

        // Get all the bLRuleList
        restBLRuleMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLRule.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].sourceIds").value(hasItem(DEFAULT_SOURCE_IDS)))
            .andExpect(jsonPath("$.[*].customerType").value(hasItem(DEFAULT_CUSTOMER_TYPE)))
            .andExpect(jsonPath("$.[*].scoreMinimum").value(hasItem(DEFAULT_SCORE_MINIMUM.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateauthoriseBy").value(hasItem(UPDATED_DATE_AUTHORISE)));
    }

    @Test
    @Transactional
    void getBLRule() throws Exception {
        // Initialize the database
        bLRuleRepository.saveAndFlush(bLRule);

        // Get the bLRule
        restBLRuleMockMvc
            .perform(get(ENTITY_API_URL_ID, bLRule.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bLRule.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.sourceIds").value(DEFAULT_SOURCE_IDS))
            .andExpect(jsonPath("$.customerType").value(DEFAULT_CUSTOMER_TYPE))
            .andExpect(jsonPath("$.scoreMinimum").value(DEFAULT_SCORE_MINIMUM.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED))
            .andExpect(jsonPath("$.authoriseBy").value(DEFAULT_AUTHORISE_BY))
            .andExpect(jsonPath("$.dateauthoriseBy").value(UPDATED_DATE_AUTHORISE));
    }

    @Test
    @Transactional
    void getNonExistingBLRule() throws Exception {
        // Get the bLRule
        restBLRuleMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBLRule() throws Exception {
        // Initialize the database
        bLRuleRepository.saveAndFlush(bLRule);

        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();

        // Update the bLRule
        BLRule updatedBLRule = bLRuleRepository.findById(bLRule.getId()).get();
        // Disconnect from session so that the updates on updatedBLRule are not directly saved in db
        em.detach(updatedBLRule);
        updatedBLRule
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .sourceIds(UPDATED_SOURCE_IDS)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .scoreMinimum(UPDATED_SCORE_MINIMUM)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBLRule.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBLRule))
            )
            .andExpect(status().isOk());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
        BLRule testBLRule = bLRuleList.get(bLRuleList.size() - 1);
        assertThat(testBLRule.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBLRule.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLRule.getSourceIds()).isEqualTo(UPDATED_SOURCE_IDS);
        assertThat(testBLRule.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testBLRule.getScoreMinimum()).isEqualTo(UPDATED_SCORE_MINIMUM);
        assertThat(testBLRule.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLRule.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testBLRule.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLRule.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void putNonExistingBLRule() throws Exception {
        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();
        bLRule.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bLRule.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBLRule() throws Exception {
        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();
        bLRule.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLRuleMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBLRule() throws Exception {
        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();
        bLRule.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLRuleMockMvc
            .perform(
                put(ENTITY_API_URL).with(csrf()).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBLRuleWithPatch() throws Exception {
        // Initialize the database
        bLRuleRepository.saveAndFlush(bLRule);

        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();

        // Update the bLRule using partial update
        BLRule partialUpdatedBLRule = new BLRule();
        partialUpdatedBLRule.setId(bLRule.getId());

        partialUpdatedBLRule.description(UPDATED_DESCRIPTION).scoreMinimum(UPDATED_SCORE_MINIMUM).createdBy(UPDATED_CREATED_BY);

        restBLRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLRule.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLRule))
            )
            .andExpect(status().isOk());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
        BLRule testBLRule = bLRuleList.get(bLRuleList.size() - 1);
        assertThat(testBLRule.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBLRule.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLRule.getSourceIds()).isEqualTo(DEFAULT_SOURCE_IDS);
        assertThat(testBLRule.getCustomerType()).isEqualTo(DEFAULT_CUSTOMER_TYPE);
        assertThat(testBLRule.getScoreMinimum()).isEqualTo(UPDATED_SCORE_MINIMUM);
        assertThat(testBLRule.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLRule.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testBLRule.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLRule.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void fullUpdateBLRuleWithPatch() throws Exception {
        // Initialize the database
        bLRuleRepository.saveAndFlush(bLRule);

        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();

        // Update the bLRule using partial update
        BLRule partialUpdatedBLRule = new BLRule();
        partialUpdatedBLRule.setId(bLRule.getId());

        partialUpdatedBLRule
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .sourceIds(UPDATED_SOURCE_IDS)
            .customerType(UPDATED_CUSTOMER_TYPE)
            .scoreMinimum(UPDATED_SCORE_MINIMUM)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(UPDATED_DATE_AUTHORISE);

        restBLRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLRule.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLRule))
            )
            .andExpect(status().isOk());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
        BLRule testBLRule = bLRuleList.get(bLRuleList.size() - 1);
        assertThat(testBLRule.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBLRule.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLRule.getSourceIds()).isEqualTo(UPDATED_SOURCE_IDS);
        assertThat(testBLRule.getCustomerType()).isEqualTo(UPDATED_CUSTOMER_TYPE);
        assertThat(testBLRule.getScoreMinimum()).isEqualTo(UPDATED_SCORE_MINIMUM);
        assertThat(testBLRule.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLRule.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testBLRule.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLRule.getDateAuthorise()).isEqualTo(UPDATED_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void patchNonExistingBLRule() throws Exception {
        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();
        bLRule.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bLRule.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBLRule() throws Exception {
        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();
        bLRule.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLRuleMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBLRule() throws Exception {
        int databaseSizeBeforeUpdate = bLRuleRepository.findAll().size();
        bLRule.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLRuleMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLRule))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLRule in the database
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBLRule() throws Exception {
        // Initialize the database
        bLRuleRepository.saveAndFlush(bLRule);

        int databaseSizeBeforeDelete = bLRuleRepository.findAll().size();

        // Delete the bLRule
        restBLRuleMockMvc
            .perform(delete(ENTITY_API_URL_ID, bLRule.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BLRule> bLRuleList = bLRuleRepository.findAll();
        assertThat(bLRuleList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
