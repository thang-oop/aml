package vn.com.pvcombank.domain;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Bảng BLCustomer: lưu thông tin customer thuộc blacklist
 */
@ApiModel(description = "Bảng BLCustomer: lưu thông tin customer thuộc blacklist")
@Entity
@Table(name = "bl_customer")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BLCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_name_1")
    private String otherName1;

    @Column(name = "other_name_2")
    private String otherName2;

    @Column(name = "other_name_3")
    private String otherName3;

    @Column(name = "position_bl")
    private String positionBl;

    @Column(name = "date_of_birth_bl")
    private String dateOfBirthBl;

    @Column(name = "country_bl_1")
    private String countryBl1;

    @Column(name = "country_bl_2")
    private String countryBl2;

    @Column(name = "legal_id_type_bl_1")
    private String legalIdTypeBl1;

    @Column(name = "legal_id_number_1")
    private String legalIdNumber1;

    @Column(name = "legal_id_type_bl_2")
    private String legalIdTypeBl2;

    @Column(name = "legal_id_number_2")
    private String legalIdNumber2;

    @Column(name = "other_inf_legal_1")
    private Instant otherInfLegal1;

    @Column(name = "other_inf_legal_2")
    private Instant otherInfLegal2;

    @Column(name = "address_bl_1")
    private String addressBl1;

    @Column(name = "address_bl_2")
    private String addressBl2;

    @Column(name = "address_now_bl_1")
    private String addressNowBl1;

    @Column(name = "address_now_bl_2")
    private String addressNowBl2;

    @Column(name = "type_bl")
    private String typeBl;

    @Column(name = "source")
    private String source;

    @Column(name = "record_status")
    private String recordStatus;

    @Column(name = "upload_file_id")
    private String uploadFileId;

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

    public BLCustomer id(Long id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return this.fullName;
    }

    public BLCustomer fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public BLCustomer firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public BLCustomer lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherName1() {
        return this.otherName1;
    }

    public BLCustomer otherName1(String otherName1) {
        this.otherName1 = otherName1;
        return this;
    }

    public void setOtherName1(String otherName1) {
        this.otherName1 = otherName1;
    }

    public String getOtherName2() {
        return this.otherName2;
    }

    public BLCustomer otherName2(String otherName2) {
        this.otherName2 = otherName2;
        return this;
    }

    public void setOtherName2(String otherName2) {
        this.otherName2 = otherName2;
    }

    public String getOtherName3() {
        return this.otherName3;
    }

    public BLCustomer otherName3(String otherName3) {
        this.otherName3 = otherName3;
        return this;
    }

    public void setOtherName3(String otherName3) {
        this.otherName3 = otherName3;
    }

    public String getPositionBl() {
        return this.positionBl;
    }

    public BLCustomer positionBl(String positionBl) {
        this.positionBl = positionBl;
        return this;
    }

    public void setPositionBl(String positionBl) {
        this.positionBl = positionBl;
    }

    public String getDateOfBirthBl() {
        return this.dateOfBirthBl;
    }

    public BLCustomer dateOfBirthBl(String dateOfBirthBl) {
        this.dateOfBirthBl = dateOfBirthBl;
        return this;
    }

    public void setDateOfBirthBl(String dateOfBirthBl) {
        this.dateOfBirthBl = dateOfBirthBl;
    }

    public String getCountryBl1() {
        return this.countryBl1;
    }

    public BLCustomer countryBl1(String countryBl1) {
        this.countryBl1 = countryBl1;
        return this;
    }

    public void setCountryBl1(String countryBl1) {
        this.countryBl1 = countryBl1;
    }

    public String getCountryBl2() {
        return this.countryBl2;
    }

    public BLCustomer countryBl2(String countryBl2) {
        this.countryBl2 = countryBl2;
        return this;
    }

    public void setCountryBl2(String countryBl2) {
        this.countryBl2 = countryBl2;
    }

    public String getLegalIdTypeBl1() {
        return this.legalIdTypeBl1;
    }

    public BLCustomer legalIdTypeBl1(String legalIdTypeBl1) {
        this.legalIdTypeBl1 = legalIdTypeBl1;
        return this;
    }

    public void setLegalIdTypeBl1(String legalIdTypeBl1) {
        this.legalIdTypeBl1 = legalIdTypeBl1;
    }

    public String getLegalIdNumber1() {
        return this.legalIdNumber1;
    }

    public BLCustomer legalIdNumber1(String legalIdNumber1) {
        this.legalIdNumber1 = legalIdNumber1;
        return this;
    }

    public void setLegalIdNumber1(String legalIdNumber1) {
        this.legalIdNumber1 = legalIdNumber1;
    }

    public String getLegalIdTypeBl2() {
        return this.legalIdTypeBl2;
    }

    public BLCustomer legalIdTypeBl2(String legalIdTypeBl2) {
        this.legalIdTypeBl2 = legalIdTypeBl2;
        return this;
    }

    public void setLegalIdTypeBl2(String legalIdTypeBl2) {
        this.legalIdTypeBl2 = legalIdTypeBl2;
    }

    public String getLegalIdNumber2() {
        return this.legalIdNumber2;
    }

    public BLCustomer legalIdNumber2(String legalIdNumber2) {
        this.legalIdNumber2 = legalIdNumber2;
        return this;
    }

    public void setLegalIdNumber2(String legalIdNumber2) {
        this.legalIdNumber2 = legalIdNumber2;
    }

    public Instant getOtherInfLegal1() {
        return this.otherInfLegal1;
    }

    public BLCustomer otherInfLegal1(Instant otherInfLegal1) {
        this.otherInfLegal1 = otherInfLegal1;
        return this;
    }

    public void setOtherInfLegal1(Instant otherInfLegal1) {
        this.otherInfLegal1 = otherInfLegal1;
    }

    public Instant getOtherInfLegal2() {
        return this.otherInfLegal2;
    }

    public BLCustomer otherInfLegal2(Instant otherInfLegal2) {
        this.otherInfLegal2 = otherInfLegal2;
        return this;
    }

    public void setOtherInfLegal2(Instant otherInfLegal2) {
        this.otherInfLegal2 = otherInfLegal2;
    }

    public String getAddressBl1() {
        return this.addressBl1;
    }

    public BLCustomer addressBl1(String addressBl1) {
        this.addressBl1 = addressBl1;
        return this;
    }

    public void setAddressBl1(String addressBl1) {
        this.addressBl1 = addressBl1;
    }

    public String getAddressBl2() {
        return this.addressBl2;
    }

    public BLCustomer addressBl2(String addressBl2) {
        this.addressBl2 = addressBl2;
        return this;
    }

    public void setAddressBl2(String addressBl2) {
        this.addressBl2 = addressBl2;
    }

    public String getAddressNowBl1() {
        return this.addressNowBl1;
    }

    public BLCustomer addressNowBl1(String addressNowBl1) {
        this.addressNowBl1 = addressNowBl1;
        return this;
    }

    public void setAddressNowBl1(String addressNowBl1) {
        this.addressNowBl1 = addressNowBl1;
    }

    public String getAddressNowBl2() {
        return this.addressNowBl2;
    }

    public BLCustomer addressNowBl2(String addressNowBl2) {
        this.addressNowBl2 = addressNowBl2;
        return this;
    }

    public void setAddressNowBl2(String addressNowBl2) {
        this.addressNowBl2 = addressNowBl2;
    }

    public String getTypeBl() {
        return this.typeBl;
    }

    public BLCustomer typeBl(String typeBl) {
        this.typeBl = typeBl;
        return this;
    }

    public void setTypeBl(String typeBl) {
        this.typeBl = typeBl;
    }

    public String getSource() {
        return this.source;
    }

    public BLCustomer source(String source) {
        this.source = source;
        return this;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public BLCustomer recordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getUploadFileId() {
        return this.uploadFileId;
    }

    public BLCustomer uploadFileId(String uploadFileId) {
        this.uploadFileId = uploadFileId;
        return this;
    }

    public void setUploadFileId(String uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    public String getCoCode() {
        return this.coCode;
    }

    public BLCustomer coCode(String coCode) {
        this.coCode = coCode;
        return this;
    }

    public void setCoCode(String coCode) {
        this.coCode = coCode;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BLCustomer createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public BLCustomer dateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthoriseBy() {
        return this.authoriseBy;
    }

    public BLCustomer authoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
        return this;
    }

    public void setAuthoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public String getDateAuthorise() {
        return this.dateAuthorise;
    }

    public BLCustomer dateAuthorise(String dateAuthorise) {
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
        if (!(o instanceof BLCustomer)) {
            return false;
        }
        return id != null && id.equals(((BLCustomer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLCustomer{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", otherName1='" + getOtherName1() + "'" +
            ", otherName2='" + getOtherName2() + "'" +
            ", otherName3='" + getOtherName3() + "'" +
            ", positionBl='" + getPositionBl() + "'" +
            ", dateOfBirthBl='" + getDateOfBirthBl() + "'" +
            ", countryBl1='" + getCountryBl1() + "'" +
            ", countryBl2='" + getCountryBl2() + "'" +
            ", legalIdTypeBl1='" + getLegalIdTypeBl1() + "'" +
            ", legalIdNumber1='" + getLegalIdNumber1() + "'" +
            ", legalIdTypeBl2='" + getLegalIdTypeBl2() + "'" +
            ", legalIdNumber2='" + getLegalIdNumber2() + "'" +
            ", otherInfLegal1='" + getOtherInfLegal1() + "'" +
            ", otherInfLegal2='" + getOtherInfLegal2() + "'" +
            ", addressBl1='" + getAddressBl1() + "'" +
            ", addressBl2='" + getAddressBl2() + "'" +
            ", addressNowBl1='" + getAddressNowBl1() + "'" +
            ", addressNowBl2='" + getAddressNowBl2() + "'" +
            ", typeBl='" + getTypeBl() + "'" +
            ", source='" + getSource() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", uploadFileId='" + getUploadFileId() + "'" +
            ", coCode='" + getCoCode() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", authoriseBy='" + getAuthoriseBy() + "'" +
            ", dateAuthorise='" + getDateAuthorise() + "'" +
            "}";
    }
}
