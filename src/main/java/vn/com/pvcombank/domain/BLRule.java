package vn.com.pvcombank.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BLRule.
 */
@Entity
@Table(name = "bl_rule")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BLRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "source_ids")
    private String sourceIds;

    @Column(name = "customer_type")
    private String customerType;

    @Column(name = "score_minimum")
    private Long scoreMinimum;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BLRule id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public BLRule name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public BLRule description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceIds() {
        return this.sourceIds;
    }

    public BLRule sourceIds(String sourceIds) {
        this.sourceIds = sourceIds;
        return this;
    }

    public void setSourceIds(String sourceIds) {
        this.sourceIds = sourceIds;
    }

    public String getCustomerType() {
        return this.customerType;
    }

    public BLRule customerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Long getScoreMinimum() {
        return this.scoreMinimum;
    }

    public BLRule scoreMinimum(Long scoreMinimum) {
        this.scoreMinimum = scoreMinimum;
        return this;
    }

    public void setScoreMinimum(Long scoreMinimum) {
        this.scoreMinimum = scoreMinimum;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BLRule createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public BLRule dateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthoriseBy() {
        return this.authoriseBy;
    }

    public BLRule authoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
        return this;
    }

    public void setAuthoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public String getDateAuthorise() {
        return this.dateAuthorise;
    }

    public BLRule dateAuthorise(String dateAuthorise) {
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
        if (!(o instanceof BLRule)) {
            return false;
        }
        return id != null && id.equals(((BLRule) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLRule{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", sourceIds='" + getSourceIds() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", scoreMinimum=" + getScoreMinimum() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", authoriseBy='" + getAuthoriseBy() + "'" +
            ", dateAuthorise='" + getDateAuthorise() + "'" +

            "}";
    }
}
