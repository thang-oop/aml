package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class BLMappingParamTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BLSourceData.class);
        BLSourceData bLMappingParam1 = new BLSourceData();
        bLMappingParam1.setId(1L);
        BLSourceData bLMappingParam2 = new BLSourceData();
        bLMappingParam2.setId(bLMappingParam1.getId());
        assertThat(bLMappingParam1).isEqualTo(bLMappingParam2);
        bLMappingParam2.setId(2L);
        assertThat(bLMappingParam1).isNotEqualTo(bLMappingParam2);
        bLMappingParam1.setId(null);
        assertThat(bLMappingParam1).isNotEqualTo(bLMappingParam2);
    }
}
