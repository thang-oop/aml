package vn.com.pvcombank.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BLCondition.
 */
@Entity
@Table(name = "bl_condition")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BLCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "black_list_flds")
    private String blackListFlds;

    @Column(name = "customer_flds")
    private String customerFlds;

    @Column(name = "weight_point")
    private Long weightPoint;

    @Column(name = "rule_id")
    private Long ruleId;

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

    public BLCondition id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public BLCondition name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public BLCondition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBlackListFlds() {
        return this.blackListFlds;
    }

    public BLCondition blackListFlds(String blackListFlds) {
        this.blackListFlds = blackListFlds;
        return this;
    }

    public void setBlackListFlds(String blackListFlds) {
        this.blackListFlds = blackListFlds;
    }

    public String getCustomerFlds() {
        return this.customerFlds;
    }

    public BLCondition customerFlds(String customerFlds) {
        this.customerFlds = customerFlds;
        return this;
    }

    public void setCustomerFlds(String customerFlds) {
        this.customerFlds = customerFlds;
    }

    public Long getWeightPoint() {
        return this.weightPoint;
    }

    public BLCondition weightPoint(Long weightPoint) {
        this.weightPoint = weightPoint;
        return this;
    }

    public void setWeightPoint(Long weightPoint) {
        this.weightPoint = weightPoint;
    }

    public Long getRuleId() {
        return this.ruleId;
    }

    public BLCondition ruleId(Long ruleId) {
        this.ruleId = ruleId;
        return this;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BLCondition createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public BLCondition dateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthoriseBy() {
        return this.authoriseBy;
    }

    public BLCondition authoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
        return this;
    }

    public void setAuthoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public String getDateAuthorise() {
        return this.dateAuthorise;
    }

    public BLCondition dateAuthorise(String dateAuthorise) {
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
        if (!(o instanceof BLCondition)) {
            return false;
        }
        return id != null && id.equals(((BLCondition) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLCondition{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", blackListFlds='" + getBlackListFlds() + "'" +
            ", customerFlds='" + getCustomerFlds() + "'" +
            ", weightPoint=" + getWeightPoint() +
            ", ruleId=" + getRuleId() +
            ", createdBy='" + getCreatedBy() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", authoriseBy='" + getAuthoriseBy() + "'" +
            ", dateauthoriseBy='" + getDateAuthorise() + "'" +
            "}";
    }
}
