package vn.com.pvcombank.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import vn.com.pvcombank.web.rest.TestUtil;

class JhiUserAuthorityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JhiUserAuthority.class);
        JhiUserAuthority jhiUserAuthority1 = new JhiUserAuthority();
        jhiUserAuthority1.setId(1L);
        JhiUserAuthority jhiUserAuthority2 = new JhiUserAuthority();
        jhiUserAuthority2.setId(jhiUserAuthority1.getId());
        assertThat(jhiUserAuthority1).isEqualTo(jhiUserAuthority2);
        jhiUserAuthority2.setId(2L);
        assertThat(jhiUserAuthority1).isNotEqualTo(jhiUserAuthority2);
        jhiUserAuthority1.setId(null);
        assertThat(jhiUserAuthority1).isNotEqualTo(jhiUserAuthority2);
    }
}
