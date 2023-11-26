package vn.com.pvcombank.domain;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Bảng BLCustomer: lưu thông tin customer thuộc blacklist
 */
@ApiModel(description = "Bảng BLCustomer: lưu thông tin customer thuộc blacklist")
@Entity
@Table(name = "bl_customer_pvc")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BLCustomerPvc implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "cif")
    private String cif;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "legal_id")
    private String legalId;

    @Column(name = "legal_type")
    private String legalType;

    @Column(name = "branch")
    private String branch;

    @Column(name = "bl_customer_id")
    private String blCustomerId;

    @Column(name = "name_bl")
    private String nameBl;

    @Column(name = "date_of_birth_bl")
    private String dateOfBirthBl;

    @Column(name = "legal_id_type_bl")
    private String legalIdTypeBl;

    @Column(name = "legal_id_number")
    private String legalIdNumber;

    @Column(name = "match_attr")
    private String matchAttr;

    @Column(name = "value_attr")
    private String valueAttr;

    @Column(name = "weight_attr")
    private String weightAttr;

    @Column(name = "score")
    private String score;

    @Column(name = "status")
    private String status;

    @Column(name = "remark")
    private String remark;

    @Column(name = "record_status")
    private String recordStatus;

    @Column(name = "co_code")
    private String coCode;

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

    public BLCustomerPvc id(Long id) {
        this.id = id;
        return this;
    }

    public String getCif() {
        return this.cif;
    }

    public BLCustomerPvc cif(String cif) {
        this.cif = cif;
        return this;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getFullName() {
        return this.fullName;
    }

    public BLCustomerPvc fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public BLCustomerPvc dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLegalId() {
        return this.legalId;
    }

    public BLCustomerPvc legalId(String legalId) {
        this.legalId = legalId;
        return this;
    }

    public void setLegalId(String legalId) {
        this.legalId = legalId;
    }

    public String getLegalType() {
        return this.legalType;
    }

    public BLCustomerPvc legalType(String legalType) {
        this.legalType = legalType;
        return this;
    }

    public void setLegalType(String legalType) {
        this.legalType = legalType;
    }

    public String getBranch() {
        return this.branch;
    }

    public BLCustomerPvc branch(String branch) {
        this.branch = branch;
        return this;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getBlCustomerId() {
        return this.blCustomerId;
    }

    public BLCustomerPvc blCustomerId(String blCustomerId) {
        this.blCustomerId = blCustomerId;
        return this;
    }

    public void setBlCustomerId(String blCustomerId) {
        this.blCustomerId = blCustomerId;
    }

    public String getNameBl() {
        return this.nameBl;
    }

    public BLCustomerPvc nameBl(String nameBl) {
        this.nameBl = nameBl;
        return this;
    }

    public void setNameBl(String nameBl) {
        this.nameBl = nameBl;
    }

    public String getDateOfBirthBl() {
        return this.dateOfBirthBl;
    }

    public BLCustomerPvc dateOfBirthBl(String dateOfBirthBl) {
        this.dateOfBirthBl = dateOfBirthBl;
        return this;
    }

    public void setDateOfBirthBl(String dateOfBirthBl) {
        this.dateOfBirthBl = dateOfBirthBl;
    }

    public String getLegalIdTypeBl() {
        return this.legalIdTypeBl;
    }

    public BLCustomerPvc legalIdTypeBl(String legalIdTypeBl) {
        this.legalIdTypeBl = legalIdTypeBl;
        return this;
    }

    public void setLegalIdTypeBl(String legalIdTypeBl) {
        this.legalIdTypeBl = legalIdTypeBl;
    }

    public String getLegalIdNumber() {
        return this.legalIdNumber;
    }

    public BLCustomerPvc legalIdNumber(String legalIdNumber) {
        this.legalIdNumber = legalIdNumber;
        return this;
    }

    public void setLegalIdNumber(String legalIdNumber) {
        this.legalIdNumber = legalIdNumber;
    }

    public String getMatchAttr() {
        return this.matchAttr;
    }

    public BLCustomerPvc matchAttr(String matchAttr) {
        this.matchAttr = matchAttr;
        return this;
    }

    public void setMatchAttr(String matchAttr) {
        this.matchAttr = matchAttr;
    }

    public String getValueAttr() {
        return this.valueAttr;
    }

    public BLCustomerPvc valueAttr(String valueAttr) {
        this.valueAttr = valueAttr;
        return this;
    }

    public void setValueAttr(String valueAttr) {
        this.valueAttr = valueAttr;
    }

    public String getWeightAttr() {
        return this.weightAttr;
    }

    public BLCustomerPvc weightAttr(String weightAttr) {
        this.weightAttr = weightAttr;
        return this;
    }

    public void setWeightAttr(String weightAttr) {
        this.weightAttr = weightAttr;
    }

    public String getScore() {
        return this.score;
    }

    public BLCustomerPvc score(String score) {
        this.score = score;
        return this;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStatus() {
        return this.status;
    }

    public BLCustomerPvc status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return this.remark;
    }

    public BLCustomerPvc remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public BLCustomerPvc recordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCoCode() {
        return this.coCode;
    }

    public BLCustomerPvc coCode(String coCode) {
        this.coCode = coCode;
        return this;
    }

    public void setCoCode(String coCode) {
        this.coCode = coCode;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BLCustomerPvc createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public BLCustomerPvc dateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthoriseBy() {
        return this.authoriseBy;
    }

    public BLCustomerPvc authoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
        return this;
    }

    public void setAuthoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public String getDateAuthorise() {
        return this.dateAuthorise;
    }

    public BLCustomerPvc dateAuthorise(String dateAuthorise) {
        this.dateAuthorise = dateAuthorise;
        return this;
    }

    public void setDateAuthorise(String dateAuthorise) {
        this.dateAuthorise = dateAuthorise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BLCustomerPvc)) {
            return false;
        }
        return id != null && id.equals(((BLCustomerPvc) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLCustomerPvc{" +
            "id=" + getId() +
            ", cif='" + getCif() + "'" +
            ", fullName='" + getFullName() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", legalId='" + getLegalId() + "'" +
            ", legalType='" + getLegalType() + "'" +
            ", branch='" + getBranch() + "'" +
            ", blCustomerId='" + getBlCustomerId() + "'" +
            ", nameBl='" + getNameBl() + "'" +
            ", dateOfBirthBl='" + getDateOfBirthBl() + "'" +
            ", legalIdTypeBl='" + getLegalIdTypeBl() + "'" +
            ", legalIdNumber='" + getLegalIdNumber() + "'" +
            ", matchAttr='" + getMatchAttr() + "'" +
            ", valueAttr='" + getValueAttr() + "'" +
            ", weightAttr='" + getWeightAttr() + "'" +
            ", score='" + getScore() + "'" +
            ", status='" + getStatus() + "'" +
            ", remark='" + getRemark() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", coCode='" + getCoCode() + "'" +
            ", authoriseBy='" + getAuthoriseBy() + "'" +
            ", dateAuthorise='" + getDateAuthorise() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            "}";
    }
}
