package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class BLRuleTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BLRule.class);
        BLRule bLRule1 = new BLRule();
        bLRule1.setId(1L);
        BLRule bLRule2 = new BLRule();
        bLRule2.setId(bLRule1.getId());
        assertThat(bLRule1).isEqualTo(bLRule2);
        bLRule2.setId(2L);
        assertThat(bLRule1).isNotEqualTo(bLRule2);
        bLRule1.setId(null);
        assertThat(bLRule1).isNotEqualTo(bLRule2);
    }
}
