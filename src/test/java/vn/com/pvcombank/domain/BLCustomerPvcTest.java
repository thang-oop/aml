package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class BLCustomerPvcTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BLCustomerPvc.class);
        BLCustomerPvc bLCustomerPvc1 = new BLCustomerPvc();
        bLCustomerPvc1.setId(1L);
        BLCustomerPvc bLCustomerPvc2 = new BLCustomerPvc();
        bLCustomerPvc2.setId(bLCustomerPvc1.getId());
        assertThat(bLCustomerPvc1).isEqualTo(bLCustomerPvc2);
        bLCustomerPvc2.setId(2L);
        assertThat(bLCustomerPvc1).isNotEqualTo(bLCustomerPvc2);
        bLCustomerPvc1.setId(null);
        assertThat(bLCustomerPvc1).isNotEqualTo(bLCustomerPvc2);
    }
}
