package vn.com.pvcombank.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JhiUserAuthority.
 */
@Entity
@Table(name = "jhi_user_authority")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JhiUserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "authority_name")
    private String authorityName;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JhiUserAuthority id(Long id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return this.userId;
    }

    public JhiUserAuthority userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAuthorityName() {
        return this.authorityName;
    }

    public JhiUserAuthority authorityName(String authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JhiUserAuthority)) {
            return false;
        }
        return id != null && id.equals(((JhiUserAuthority) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JhiUserAuthority{" +
            "id=" + getId() +
            ", userId='" + getUserId() + "'" +
            ", authorityName='" + getAuthorityName() + "'" +
            "}";
    }
}
