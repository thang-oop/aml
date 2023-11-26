package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class BLConditionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BLCondition.class);
        BLCondition bLCondition1 = new BLCondition();
        bLCondition1.setId(1L);
        BLCondition bLCondition2 = new BLCondition();
        bLCondition2.setId(bLCondition1.getId());
        assertThat(bLCondition1).isEqualTo(bLCondition2);
        bLCondition2.setId(2L);
        assertThat(bLCondition1).isNotEqualTo(bLCondition2);
        bLCondition1.setId(null);
        assertThat(bLCondition1).isNotEqualTo(bLCondition2);
    }
}
