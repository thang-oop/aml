package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class BLParamterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BLParamter.class);
        BLParamter bLParamter1 = new BLParamter();
        bLParamter1.setId(1L);
        BLParamter bLParamter2 = new BLParamter();
        bLParamter2.setId(bLParamter1.getId());
        assertThat(bLParamter1).isEqualTo(bLParamter2);
        bLParamter2.setId(2L);
        assertThat(bLParamter1).isNotEqualTo(bLParamter2);
        bLParamter1.setId(null);
        assertThat(bLParamter1).isNotEqualTo(bLParamter2);
    }
}
