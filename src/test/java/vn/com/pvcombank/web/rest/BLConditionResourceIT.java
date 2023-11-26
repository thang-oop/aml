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
import vn.com.pvcombank.domain.BLCondition;
import vn.com.pvcombank.repository.BLConditionRepository;

/**
 * Integration tests for the {@link BLConditionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class BLConditionResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_BLACK_LIST_FLDS = "AAAAAAAAAA";
    private static final String UPDATED_BLACK_LIST_FLDS = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_FLDS = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_FLDS = "BBBBBBBBBB";

    private static final Long DEFAULT_WEIGHT_POINT = 1L;
    private static final Long UPDATED_WEIGHT_POINT = 2L;

    private static final Long DEFAULT_RULE_ID = 1L;
    private static final Long UPDATED_RULE_ID = 2L;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_CREATED = "AAAAAAAAAA";
    private static final String UPDATED_DATE_CREATED = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHORISE_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORISE_BY = "BBBBBBBBBB";

    private static final String DEFAULT_DATE_AUTHORISE = "AAAAAAAAAA";
    private static final String UPDATED_DATE_AUTHORISE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHORISER_BY = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORISER_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/bl-conditions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private BLConditionRepository bLConditionRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBLConditionMockMvc;

    private BLCondition bLCondition;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLCondition createEntity(EntityManager em) {
        BLCondition bLCondition = new BLCondition()
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .blackListFlds(DEFAULT_BLACK_LIST_FLDS)
            .customerFlds(DEFAULT_CUSTOMER_FLDS)
            .weightPoint(DEFAULT_WEIGHT_POINT)
            .ruleId(DEFAULT_RULE_ID)
            .createdBy(DEFAULT_CREATED_BY)
            .dateCreated(DEFAULT_DATE_CREATED)
            .authoriseBy(DEFAULT_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE);

        return bLCondition;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BLCondition createUpdatedEntity(EntityManager em) {
        BLCondition bLCondition = new BLCondition()
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .blackListFlds(UPDATED_BLACK_LIST_FLDS)
            .customerFlds(UPDATED_CUSTOMER_FLDS)
            .weightPoint(UPDATED_WEIGHT_POINT)
            .ruleId(UPDATED_RULE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE);

        return bLCondition;
    }

    @BeforeEach
    public void initTest() {
        bLCondition = createEntity(em);
    }

    @Test
    @Transactional
    void createBLCondition() throws Exception {
        int databaseSizeBeforeCreate = bLConditionRepository.findAll().size();
        // Create the BLCondition
        restBLConditionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isCreated());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeCreate + 1);
        BLCondition testBLCondition = bLConditionList.get(bLConditionList.size() - 1);
        assertThat(testBLCondition.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBLCondition.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBLCondition.getBlackListFlds()).isEqualTo(DEFAULT_BLACK_LIST_FLDS);
        assertThat(testBLCondition.getCustomerFlds()).isEqualTo(DEFAULT_CUSTOMER_FLDS);
        assertThat(testBLCondition.getWeightPoint()).isEqualTo(DEFAULT_WEIGHT_POINT);
        assertThat(testBLCondition.getRuleId()).isEqualTo(DEFAULT_RULE_ID);
        assertThat(testBLCondition.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBLCondition.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testBLCondition.getAuthoriseBy()).isEqualTo(DEFAULT_AUTHORISE_BY);
        assertThat(testBLCondition.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void createBLConditionWithExistingId() throws Exception {
        // Create the BLCondition with an existing ID
        bLCondition.setId(1L);

        int databaseSizeBeforeCreate = bLConditionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restBLConditionMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllBLConditions() throws Exception {
        // Initialize the database
        bLConditionRepository.saveAndFlush(bLCondition);

        // Get all the bLConditionList
        restBLConditionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bLCondition.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].blackListFlds").value(hasItem(DEFAULT_BLACK_LIST_FLDS)))
            .andExpect(jsonPath("$.[*].customerFlds").value(hasItem(DEFAULT_CUSTOMER_FLDS)))
            .andExpect(jsonPath("$.[*].weightPoint").value(hasItem(DEFAULT_WEIGHT_POINT.intValue())))
            .andExpect(jsonPath("$.[*].ruleId").value(hasItem(DEFAULT_RULE_ID.intValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED)))
            .andExpect(jsonPath("$.[*].authoriseBy").value(hasItem(DEFAULT_AUTHORISE_BY)))
            .andExpect(jsonPath("$.[*].dateauthoriseBy").value(hasItem(DEFAULT_DATE_AUTHORISE)));
    }

    @Test
    @Transactional
    void getBLCondition() throws Exception {
        // Initialize the database
        bLConditionRepository.saveAndFlush(bLCondition);

        // Get the bLCondition
        restBLConditionMockMvc
            .perform(get(ENTITY_API_URL_ID, bLCondition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(bLCondition.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.blackListFlds").value(DEFAULT_BLACK_LIST_FLDS))
            .andExpect(jsonPath("$.customerFlds").value(DEFAULT_CUSTOMER_FLDS))
            .andExpect(jsonPath("$.weightPoint").value(DEFAULT_WEIGHT_POINT.intValue()))
            .andExpect(jsonPath("$.ruleId").value(DEFAULT_RULE_ID.intValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED))
            .andExpect(jsonPath("$.authoriseBy").value(DEFAULT_AUTHORISE_BY))
            .andExpect(jsonPath("$.dateauthoriseBy").value(DEFAULT_DATE_AUTHORISE));
    }

    @Test
    @Transactional
    void getNonExistingBLCondition() throws Exception {
        // Get the bLCondition
        restBLConditionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewBLCondition() throws Exception {
        // Initialize the database
        bLConditionRepository.saveAndFlush(bLCondition);

        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();

        // Update the bLCondition
        BLCondition updatedBLCondition = bLConditionRepository.findById(bLCondition.getId()).get();
        // Disconnect from session so that the updates on updatedBLCondition are not directly saved in db
        em.detach(updatedBLCondition);
        updatedBLCondition
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .blackListFlds(UPDATED_BLACK_LIST_FLDS)
            .customerFlds(UPDATED_CUSTOMER_FLDS)
            .weightPoint(UPDATED_WEIGHT_POINT)
            .ruleId(UPDATED_RULE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE);

        restBLConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedBLCondition.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedBLCondition))
            )
            .andExpect(status().isOk());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
        BLCondition testBLCondition = bLConditionList.get(bLConditionList.size() - 1);
        assertThat(testBLCondition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBLCondition.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLCondition.getBlackListFlds()).isEqualTo(UPDATED_BLACK_LIST_FLDS);
        assertThat(testBLCondition.getCustomerFlds()).isEqualTo(UPDATED_CUSTOMER_FLDS);
        assertThat(testBLCondition.getWeightPoint()).isEqualTo(UPDATED_WEIGHT_POINT);
        assertThat(testBLCondition.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testBLCondition.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCondition.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testBLCondition.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCondition.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void putNonExistingBLCondition() throws Exception {
        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();
        bLCondition.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, bLCondition.getId())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchBLCondition() throws Exception {
        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();
        bLCondition.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLConditionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamBLCondition() throws Exception {
        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();
        bLCondition.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLConditionMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateBLConditionWithPatch() throws Exception {
        // Initialize the database
        bLConditionRepository.saveAndFlush(bLCondition);

        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();

        // Update the bLCondition using partial update
        BLCondition partialUpdatedBLCondition = new BLCondition();
        partialUpdatedBLCondition.setId(bLCondition.getId());

        partialUpdatedBLCondition
            .name(UPDATED_NAME)
            .blackListFlds(UPDATED_BLACK_LIST_FLDS)
            .weightPoint(UPDATED_WEIGHT_POINT)
            .ruleId(UPDATED_RULE_ID)
            .authoriseBy(UPDATED_AUTHORISE_BY);

        restBLConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLCondition.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLCondition))
            )
            .andExpect(status().isOk());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
        BLCondition testBLCondition = bLConditionList.get(bLConditionList.size() - 1);
        assertThat(testBLCondition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBLCondition.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testBLCondition.getBlackListFlds()).isEqualTo(UPDATED_BLACK_LIST_FLDS);
        assertThat(testBLCondition.getCustomerFlds()).isEqualTo(DEFAULT_CUSTOMER_FLDS);
        assertThat(testBLCondition.getWeightPoint()).isEqualTo(UPDATED_WEIGHT_POINT);
        assertThat(testBLCondition.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testBLCondition.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testBLCondition.getDateCreated()).isEqualTo(DEFAULT_DATE_CREATED);
        assertThat(testBLCondition.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCondition.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void fullUpdateBLConditionWithPatch() throws Exception {
        // Initialize the database
        bLConditionRepository.saveAndFlush(bLCondition);

        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();

        // Update the bLCondition using partial update
        BLCondition partialUpdatedBLCondition = new BLCondition();
        partialUpdatedBLCondition.setId(bLCondition.getId());

        partialUpdatedBLCondition
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .blackListFlds(UPDATED_BLACK_LIST_FLDS)
            .customerFlds(UPDATED_CUSTOMER_FLDS)
            .weightPoint(UPDATED_WEIGHT_POINT)
            .ruleId(UPDATED_RULE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .dateCreated(UPDATED_DATE_CREATED)
            .authoriseBy(UPDATED_AUTHORISE_BY)
            .dateAuthorise(DEFAULT_DATE_AUTHORISE);

        restBLConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedBLCondition.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedBLCondition))
            )
            .andExpect(status().isOk());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
        BLCondition testBLCondition = bLConditionList.get(bLConditionList.size() - 1);
        assertThat(testBLCondition.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBLCondition.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testBLCondition.getBlackListFlds()).isEqualTo(UPDATED_BLACK_LIST_FLDS);
        assertThat(testBLCondition.getCustomerFlds()).isEqualTo(UPDATED_CUSTOMER_FLDS);
        assertThat(testBLCondition.getWeightPoint()).isEqualTo(UPDATED_WEIGHT_POINT);
        assertThat(testBLCondition.getRuleId()).isEqualTo(UPDATED_RULE_ID);
        assertThat(testBLCondition.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testBLCondition.getDateCreated()).isEqualTo(UPDATED_DATE_CREATED);
        assertThat(testBLCondition.getAuthoriseBy()).isEqualTo(UPDATED_AUTHORISE_BY);
        assertThat(testBLCondition.getDateAuthorise()).isEqualTo(DEFAULT_DATE_AUTHORISE);
    }

    @Test
    @Transactional
    void patchNonExistingBLCondition() throws Exception {
        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();
        bLCondition.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBLConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, bLCondition.getId())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchBLCondition() throws Exception {
        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();
        bLCondition.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLConditionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isBadRequest());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamBLCondition() throws Exception {
        int databaseSizeBeforeUpdate = bLConditionRepository.findAll().size();
        bLCondition.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restBLConditionMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .with(csrf())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(bLCondition))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the BLCondition in the database
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteBLCondition() throws Exception {
        // Initialize the database
        bLConditionRepository.saveAndFlush(bLCondition);

        int databaseSizeBeforeDelete = bLConditionRepository.findAll().size();

        // Delete the bLCondition
        restBLConditionMockMvc
            .perform(delete(ENTITY_API_URL_ID, bLCondition.getId()).with(csrf()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<BLCondition> bLConditionList = bLConditionRepository.findAll();
        assertThat(bLConditionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
