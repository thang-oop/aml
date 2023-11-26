package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class BLUploadFileTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BLUploadFile.class);
        BLUploadFile bLUploadFile1 = new BLUploadFile();
        bLUploadFile1.setId(1L);
        BLUploadFile bLUploadFile2 = new BLUploadFile();
        bLUploadFile2.setId(bLUploadFile1.getId());
        assertThat(bLUploadFile1).isEqualTo(bLUploadFile2);
        bLUploadFile2.setId(2L);
        assertThat(bLUploadFile1).isNotEqualTo(bLUploadFile2);
        bLUploadFile1.setId(null);
        assertThat(bLUploadFile1).isNotEqualTo(bLUploadFile2);
    }
}
