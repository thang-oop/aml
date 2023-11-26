package vn.com.pvcombank.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BLParamter.
 */
@Entity
@Table(name = "bl_paramter")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BLParamter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "key_id")
    private String keyId;

    @Column(name = "key_value")
    private String keyValue;

    @Column(name = "description")
    private String description;

    @Column(name = "record_status")
    private String recordStatus;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "date_created")
    private String dateCreated;

    @Column(name = "authorise_by")
    private String authoriseBy;

    @Column(name = "date_authorise")
    private String dateAuthorise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BLParamter id(Long id) {
        this.id = id;
        return this;
    }

    public String getKeyId() {
        return this.keyId;
    }

    public BLParamter keyId(String keyId) {
        this.keyId = keyId;
        return this;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getKeyValue() {
        return this.keyValue;
    }

    public BLParamter keyValue(String keyValue) {
        this.keyValue = keyValue;
        return this;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getDescription() {
        return this.description;
    }

    public BLParamter description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public BLParamter recordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BLParamter createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public BLParamter dateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthoriseBy() {
        return this.authoriseBy;
    }

    public BLParamter authoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
        return this;
    }

    public void setAuthoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public String getDateAuthorise() {
        return this.dateAuthorise;
    }

    public BLParamter dateAuthorise(String dateAuthorise) {
        this.dateAuthorise = dateAuthorise;
        return this;
    }

    public void setDateAuthorise(String dateAuthorise) {
        this.dateAuthorise = dateAuthorise;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BLParamter)) {
            return false;
        }
        return id != null && id.equals(((BLParamter) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLParamter{" +
            "id=" + getId() +
            ", keyId='" + getKeyId() + "'" +
            ", keyValue='" + getKeyValue() + "'" +
            ", description='" + getDescription() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", authoriseBy='" + getAuthoriseBy() + "'" +
            ", dateAuthorise='" + getDateAuthorise() + "'" +
            "}";
    }
}
