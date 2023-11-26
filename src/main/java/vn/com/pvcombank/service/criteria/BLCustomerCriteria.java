package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.InstantFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.BLCustomer} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.BLCustomerResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bl-customers?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BLCustomerCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fullName;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter otherName1;

    private StringFilter otherName2;

    private StringFilter otherName3;

    private StringFilter positionBl;

    private StringFilter dateOfBirthBl;

    private StringFilter countryBl1;

    private StringFilter countryBl2;

    private StringFilter legalIdTypeBl1;

    private StringFilter legalIdNumber1;

    private StringFilter legalIdTypeBl2;

    private StringFilter legalIdNumber2;

    private InstantFilter otherInfLegal1;

    private InstantFilter otherInfLegal2;

    private StringFilter addressBl1;

    private StringFilter addressBl2;

    private StringFilter addressNowBl1;

    private StringFilter addressNowBl2;

    private StringFilter typeBl;

    private StringFilter source;

    private StringFilter recordStatus;

    private StringFilter uploadFileId;

    private StringFilter coCode;

    private StringFilter createdBy;

    private StringFilter dateCreated;

    private StringFilter authoriseBy;

    private StringFilter dateAuthorise;

    public BLCustomerCriteria() {}

    public BLCustomerCriteria(BLCustomerCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.otherName1 = other.otherName1 == null ? null : other.otherName1.copy();
        this.otherName2 = other.otherName2 == null ? null : other.otherName2.copy();
        this.otherName3 = other.otherName3 == null ? null : other.otherName3.copy();
        this.positionBl = other.positionBl == null ? null : other.positionBl.copy();
        this.dateOfBirthBl = other.dateOfBirthBl == null ? null : other.dateOfBirthBl.copy();
        this.countryBl1 = other.countryBl1 == null ? null : other.countryBl1.copy();
        this.countryBl2 = other.countryBl2 == null ? null : other.countryBl2.copy();
        this.legalIdTypeBl1 = other.legalIdTypeBl1 == null ? null : other.legalIdTypeBl1.copy();
        this.legalIdNumber1 = other.legalIdNumber1 == null ? null : other.legalIdNumber1.copy();
        this.legalIdTypeBl2 = other.legalIdTypeBl2 == null ? null : other.legalIdTypeBl2.copy();
        this.legalIdNumber2 = other.legalIdNumber2 == null ? null : other.legalIdNumber2.copy();
        this.otherInfLegal1 = other.otherInfLegal1 == null ? null : other.otherInfLegal1.copy();
        this.otherInfLegal2 = other.otherInfLegal2 == null ? null : other.otherInfLegal2.copy();
        this.addressBl1 = other.addressBl1 == null ? null : other.addressBl1.copy();
        this.addressBl2 = other.addressBl2 == null ? null : other.addressBl2.copy();
        this.addressNowBl1 = other.addressNowBl1 == null ? null : other.addressNowBl1.copy();
        this.addressNowBl2 = other.addressNowBl2 == null ? null : other.addressNowBl2.copy();
        this.typeBl = other.typeBl == null ? null : other.typeBl.copy();
        this.source = other.source == null ? null : other.source.copy();
        this.recordStatus = other.recordStatus == null ? null : other.recordStatus.copy();
        this.uploadFileId = other.uploadFileId == null ? null : other.uploadFileId.copy();
        this.coCode = other.coCode == null ? null : other.coCode.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.dateCreated = other.dateCreated == null ? null : other.dateCreated.copy();
        this.authoriseBy = other.authoriseBy == null ? null : other.authoriseBy.copy();
        this.dateAuthorise = other.dateAuthorise == null ? null : other.dateAuthorise.copy();
    }

    @Override
    public BLCustomerCriteria copy() {
        return new BLCustomerCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getFullName() {
        return fullName;
    }

    public StringFilter fullName() {
        if (fullName == null) {
            fullName = new StringFilter();
        }
        return fullName;
    }

    public void setFullName(StringFilter fullName) {
        this.fullName = fullName;
    }

    public StringFilter getFirstName() {
        return firstName;
    }

    public StringFilter firstName() {
        if (firstName == null) {
            firstName = new StringFilter();
        }
        return firstName;
    }

    public void setFirstName(StringFilter firstName) {
        this.firstName = firstName;
    }

    public StringFilter getLastName() {
        return lastName;
    }

    public StringFilter lastName() {
        if (lastName == null) {
            lastName = new StringFilter();
        }
        return lastName;
    }

    public void setLastName(StringFilter lastName) {
        this.lastName = lastName;
    }

    public StringFilter getOtherName1() {
        return otherName1;
    }

    public StringFilter otherName1() {
        if (otherName1 == null) {
            otherName1 = new StringFilter();
        }
        return otherName1;
    }

    public void setOtherName1(StringFilter otherName1) {
        this.otherName1 = otherName1;
    }

    public StringFilter getOtherName2() {
        return otherName2;
    }

    public StringFilter otherName2() {
        if (otherName2 == null) {
            otherName2 = new StringFilter();
        }
        return otherName2;
    }

    public void setOtherName2(StringFilter otherName2) {
        this.otherName2 = otherName2;
    }

    public StringFilter getOtherName3() {
        return otherName3;
    }

    public StringFilter otherName3() {
        if (otherName3 == null) {
            otherName3 = new StringFilter();
        }
        return otherName3;
    }

    public void setOtherName3(StringFilter otherName3) {
        this.otherName3 = otherName3;
    }

    public StringFilter getPositionBl() {
        return positionBl;
    }

    public StringFilter positionBl() {
        if (positionBl == null) {
            positionBl = new StringFilter();
        }
        return positionBl;
    }

    public void setPositionBl(StringFilter positionBl) {
        this.positionBl = positionBl;
    }

    public StringFilter getDateOfBirthBl() {
        return dateOfBirthBl;
    }

    public StringFilter dateOfBirthBl() {
        if (dateOfBirthBl == null) {
            dateOfBirthBl = new StringFilter();
        }
        return dateOfBirthBl;
    }

    public void setDateOfBirthBl(StringFilter dateOfBirthBl) {
        this.dateOfBirthBl = dateOfBirthBl;
    }

    public StringFilter getCountryBl1() {
        return countryBl1;
    }

    public StringFilter countryBl1() {
        if (countryBl1 == null) {
            countryBl1 = new StringFilter();
        }
        return countryBl1;
    }

    public void setCountryBl1(StringFilter countryBl1) {
        this.countryBl1 = countryBl1;
    }

    public StringFilter getCountryBl2() {
        return countryBl2;
    }

    public StringFilter countryBl2() {
        if (countryBl2 == null) {
            countryBl2 = new StringFilter();
        }
        return countryBl2;
    }

    public void setCountryBl2(StringFilter countryBl2) {
        this.countryBl2 = countryBl2;
    }

    public StringFilter getLegalIdTypeBl1() {
        return legalIdTypeBl1;
    }

    public StringFilter legalIdTypeBl1() {
        if (legalIdTypeBl1 == null) {
            legalIdTypeBl1 = new StringFilter();
        }
        return legalIdTypeBl1;
    }

    public void setLegalIdTypeBl1(StringFilter legalIdTypeBl1) {
        this.legalIdTypeBl1 = legalIdTypeBl1;
    }

    public StringFilter getLegalIdNumber1() {
        return legalIdNumber1;
    }

    public StringFilter legalIdNumber1() {
        if (legalIdNumber1 == null) {
            legalIdNumber1 = new StringFilter();
        }
        return legalIdNumber1;
    }

    public void setLegalIdNumber1(StringFilter legalIdNumber1) {
        this.legalIdNumber1 = legalIdNumber1;
    }

    public StringFilter getLegalIdTypeBl2() {
        return legalIdTypeBl2;
    }

    public StringFilter legalIdTypeBl2() {
        if (legalIdTypeBl2 == null) {
            legalIdTypeBl2 = new StringFilter();
        }
        return legalIdTypeBl2;
    }

    public void setLegalIdTypeBl2(StringFilter legalIdTypeBl2) {
        this.legalIdTypeBl2 = legalIdTypeBl2;
    }

    public StringFilter getLegalIdNumber2() {
        return legalIdNumber2;
    }

    public StringFilter legalIdNumber2() {
        if (legalIdNumber2 == null) {
            legalIdNumber2 = new StringFilter();
        }
        return legalIdNumber2;
    }

    public void setLegalIdNumber2(StringFilter legalIdNumber2) {
        this.legalIdNumber2 = legalIdNumber2;
    }

    public InstantFilter getOtherInfLegal1() {
        return otherInfLegal1;
    }

    public InstantFilter otherInfLegal1() {
        if (otherInfLegal1 == null) {
            otherInfLegal1 = new InstantFilter();
        }
        return otherInfLegal1;
    }

    public void setOtherInfLegal1(InstantFilter otherInfLegal1) {
        this.otherInfLegal1 = otherInfLegal1;
    }

    public InstantFilter getOtherInfLegal2() {
        return otherInfLegal2;
    }

    public InstantFilter otherInfLegal2() {
        if (otherInfLegal2 == null) {
            otherInfLegal2 = new InstantFilter();
        }
        return otherInfLegal2;
    }

    public void setOtherInfLegal2(InstantFilter otherInfLegal2) {
        this.otherInfLegal2 = otherInfLegal2;
    }

    public StringFilter getAddressBl1() {
        return addressBl1;
    }

    public StringFilter addressBl1() {
        if (addressBl1 == null) {
            addressBl1 = new StringFilter();
        }
        return addressBl1;
    }

    public void setAddressBl1(StringFilter addressBl1) {
        this.addressBl1 = addressBl1;
    }

    public StringFilter getAddressBl2() {
        return addressBl2;
    }

    public StringFilter addressBl2() {
        if (addressBl2 == null) {
            addressBl2 = new StringFilter();
        }
        return addressBl2;
    }

    public void setAddressBl2(StringFilter addressBl2) {
        this.addressBl2 = addressBl2;
    }

    public StringFilter getAddressNowBl1() {
        return addressNowBl1;
    }

    public StringFilter addressNowBl1() {
        if (addressNowBl1 == null) {
            addressNowBl1 = new StringFilter();
        }
        return addressNowBl1;
    }

    public void setAddressNowBl1(StringFilter addressNowBl1) {
        this.addressNowBl1 = addressNowBl1;
    }

    public StringFilter getAddressNowBl2() {
        return addressNowBl2;
    }

    public StringFilter addressNowBl2() {
        if (addressNowBl2 == null) {
            addressNowBl2 = new StringFilter();
        }
        return addressNowBl2;
    }

    public void setAddressNowBl2(StringFilter addressNowBl2) {
        this.addressNowBl2 = addressNowBl2;
    }

    public StringFilter getTypeBl() {
        return typeBl;
    }

    public StringFilter typeBl() {
        if (typeBl == null) {
            typeBl = new StringFilter();
        }
        return typeBl;
    }

    public void setTypeBl(StringFilter typeBl) {
        this.typeBl = typeBl;
    }

    public StringFilter getSource() {
        return source;
    }

    public StringFilter source() {
        if (source == null) {
            source = new StringFilter();
        }
        return source;
    }

    public void setSource(StringFilter source) {
        this.source = source;
    }

    public StringFilter getRecordStatus() {
        return recordStatus;
    }

    public StringFilter recordStatus() {
        if (recordStatus == null) {
            recordStatus = new StringFilter();
        }
        return recordStatus;
    }

    public void setRecordStatus(StringFilter recordStatus) {
        this.recordStatus = recordStatus;
    }

    public StringFilter getUploadFileId() {
        return uploadFileId;
    }

    public StringFilter uploadFileId() {
        if (uploadFileId == null) {
            uploadFileId = new StringFilter();
        }
        return uploadFileId;
    }

    public void setUploadFileId(StringFilter uploadFileId) {
        this.uploadFileId = uploadFileId;
    }

    public StringFilter getCoCode() {
        return coCode;
    }

    public StringFilter coCode() {
        if (coCode == null) {
            coCode = new StringFilter();
        }
        return coCode;
    }

    public void setCoCode(StringFilter coCode) {
        this.coCode = coCode;
    }

    public StringFilter getCreatedBy() {
        return createdBy;
    }

    public StringFilter createdBy() {
        if (createdBy == null) {
            createdBy = new StringFilter();
        }
        return createdBy;
    }

    public void setCreatedBy(StringFilter createdBy) {
        this.createdBy = createdBy;
    }

    public StringFilter getDateCreated() {
        return dateCreated;
    }

    public StringFilter dateCreated() {
        if (dateCreated == null) {
            dateCreated = new StringFilter();
        }
        return dateCreated;
    }

    public void setDateCreated(StringFilter dateCreated) {
        this.dateCreated = dateCreated;
    }

    public StringFilter getAuthoriseBy() {
        return authoriseBy;
    }

    public StringFilter authoriseBy() {
        if (authoriseBy == null) {
            authoriseBy = new StringFilter();
        }
        return authoriseBy;
    }

    public void setAuthoriseBy(StringFilter authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public StringFilter getDateAuthorise() {
        return dateAuthorise;
    }

    public StringFilter dateAuthorise() {
        if (dateAuthorise == null) {
            dateAuthorise = new StringFilter();
        }
        return dateAuthorise;
    }

    public void setDateAuthorise(StringFilter dateAuthorise) {
        this.dateAuthorise = dateAuthorise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BLCustomerCriteria that = (BLCustomerCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(otherName1, that.otherName1) &&
            Objects.equals(otherName2, that.otherName2) &&
            Objects.equals(otherName3, that.otherName3) &&
            Objects.equals(positionBl, that.positionBl) &&
            Objects.equals(dateOfBirthBl, that.dateOfBirthBl) &&
            Objects.equals(countryBl1, that.countryBl1) &&
            Objects.equals(countryBl2, that.countryBl2) &&
            Objects.equals(legalIdTypeBl1, that.legalIdTypeBl1) &&
            Objects.equals(legalIdNumber1, that.legalIdNumber1) &&
            Objects.equals(legalIdTypeBl2, that.legalIdTypeBl2) &&
            Objects.equals(legalIdNumber2, that.legalIdNumber2) &&
            Objects.equals(otherInfLegal1, that.otherInfLegal1) &&
            Objects.equals(otherInfLegal2, that.otherInfLegal2) &&
            Objects.equals(addressBl1, that.addressBl1) &&
            Objects.equals(addressBl2, that.addressBl2) &&
            Objects.equals(addressNowBl1, that.addressNowBl1) &&
            Objects.equals(addressNowBl2, that.addressNowBl2) &&
            Objects.equals(typeBl, that.typeBl) &&
            Objects.equals(source, that.source) &&
            Objects.equals(recordStatus, that.recordStatus) &&
            Objects.equals(uploadFileId, that.uploadFileId) &&
            Objects.equals(coCode, that.coCode) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(dateCreated, that.dateCreated) &&
            Objects.equals(authoriseBy, that.authoriseBy) &&
            Objects.equals(dateAuthorise, that.dateAuthorise)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            fullName,
            firstName,
            lastName,
            otherName1,
            otherName2,
            otherName3,
            positionBl,
            dateOfBirthBl,
            countryBl1,
            countryBl2,
            legalIdTypeBl1,
            legalIdNumber1,
            legalIdTypeBl2,
            legalIdNumber2,
            otherInfLegal1,
            otherInfLegal2,
            addressBl1,
            addressBl2,
            addressNowBl1,
            addressNowBl2,
            typeBl,
            source,
            recordStatus,
            uploadFileId,
            coCode,
            createdBy,
            dateCreated,
            authoriseBy,
            dateAuthorise
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLCustomerCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (fullName != null ? "fullName=" + fullName + ", " : "") +
            (firstName != null ? "firstName=" + firstName + ", " : "") +
            (lastName != null ? "lastName=" + lastName + ", " : "") +
            (otherName1 != null ? "otherName1=" + otherName1 + ", " : "") +
            (otherName2 != null ? "otherName2=" + otherName2 + ", " : "") +
            (otherName3 != null ? "otherName3=" + otherName3 + ", " : "") +
            (positionBl != null ? "positionBl=" + positionBl + ", " : "") +
            (dateOfBirthBl != null ? "dateOfBirthBl=" + dateOfBirthBl + ", " : "") +
            (countryBl1 != null ? "countryBl1=" + countryBl1 + ", " : "") +
            (countryBl2 != null ? "countryBl2=" + countryBl2 + ", " : "") +
            (legalIdTypeBl1 != null ? "legalIdTypeBl1=" + legalIdTypeBl1 + ", " : "") +
            (legalIdNumber1 != null ? "legalIdNumber1=" + legalIdNumber1 + ", " : "") +
            (legalIdTypeBl2 != null ? "legalIdTypeBl2=" + legalIdTypeBl2 + ", " : "") +
            (legalIdNumber2 != null ? "legalIdNumber2=" + legalIdNumber2 + ", " : "") +
            (otherInfLegal1 != null ? "otherInfLegal1=" + otherInfLegal1 + ", " : "") +
            (otherInfLegal2 != null ? "otherInfLegal2=" + otherInfLegal2 + ", " : "") +
            (addressBl1 != null ? "addressBl1=" + addressBl1 + ", " : "") +
            (addressBl2 != null ? "addressBl2=" + addressBl2 + ", " : "") +
            (addressNowBl1 != null ? "addressNowBl1=" + addressNowBl1 + ", " : "") +
            (addressNowBl2 != null ? "addressNowBl2=" + addressNowBl2 + ", " : "") +
            (typeBl != null ? "typeBl=" + typeBl + ", " : "") +
            (source != null ? "source=" + source + ", " : "") +
            (recordStatus != null ? "recordStatus=" + recordStatus + ", " : "") +
            (uploadFileId != null ? "uploadFileId=" + uploadFileId + ", " : "") +
            (coCode != null ? "coCode=" + coCode + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (dateCreated != null ? "dateCreated=" + dateCreated + ", " : "") +
            (authoriseBy != null ? "authoriseBy=" + authoriseBy + ", " : "") +
            (dateAuthorise != null ? "dateAuthorise=" + dateAuthorise + ", " : "") +
            "}";
    }
}
