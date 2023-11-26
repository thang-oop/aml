package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class BLCustomerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BLCustomer.class);
        BLCustomer bLCustomer1 = new BLCustomer();
        bLCustomer1.setId(1L);
        BLCustomer bLCustomer2 = new BLCustomer();
        bLCustomer2.setId(bLCustomer1.getId());
        assertThat(bLCustomer1).isEqualTo(bLCustomer2);
        bLCustomer2.setId(2L);
        assertThat(bLCustomer1).isNotEqualTo(bLCustomer2);
        bLCustomer1.setId(null);
        assertThat(bLCustomer1).isNotEqualTo(bLCustomer2);
    }
}
