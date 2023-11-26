package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.BLCustomerPvc} entity.
 * This class is used
 * in {@link vn.com.pvcombank.web.rest.BLCustomerPvcResource} to receive all the
 * possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bl-customer-pvcs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific
 * {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BLCustomerPvcCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter cif;

    private StringFilter fullName;

    private StringFilter dateOfBirth;

    private StringFilter legalId;

    private StringFilter legalType;

    private StringFilter branch;

    private StringFilter blCustomerId;

    private StringFilter nameBl;

    private StringFilter dateOfBirthBl;

    private StringFilter legalIdTypeBl;

    private StringFilter legalIdNumber;

    private StringFilter matchAttr;

    private StringFilter valueAttr;

    private StringFilter weightAttr;

    private StringFilter score;

    private StringFilter status;

    private StringFilter remark;

    private StringFilter recordStatus;

    private StringFilter coCode;

    private StringFilter createdBy;

    private StringFilter dateCreated;

    private StringFilter authoriseBy;

    private StringFilter dateAuthorise;

    public BLCustomerPvcCriteria() {}

    public BLCustomerPvcCriteria(BLCustomerPvcCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.cif = other.cif == null ? null : other.cif.copy();
        this.fullName = other.fullName == null ? null : other.fullName.copy();
        this.dateOfBirth = other.dateOfBirth == null ? null : other.dateOfBirth.copy();
        this.legalId = other.legalId == null ? null : other.legalId.copy();
        this.legalType = other.legalType == null ? null : other.legalType.copy();
        this.branch = other.branch == null ? null : other.branch.copy();
        this.blCustomerId = other.blCustomerId == null ? null : other.blCustomerId.copy();
        this.nameBl = other.nameBl == null ? null : other.nameBl.copy();
        this.dateOfBirthBl = other.dateOfBirthBl == null ? null : other.dateOfBirthBl.copy();
        this.legalIdTypeBl = other.legalIdTypeBl == null ? null : other.legalIdTypeBl.copy();
        this.legalIdNumber = other.legalIdNumber == null ? null : other.legalIdNumber.copy();
        this.matchAttr = other.matchAttr == null ? null : other.matchAttr.copy();
        this.valueAttr = other.valueAttr == null ? null : other.valueAttr.copy();
        this.weightAttr = other.weightAttr == null ? null : other.weightAttr.copy();
        this.score = other.score == null ? null : other.score.copy();
        this.status = other.status == null ? null : other.status.copy();
        this.remark = other.remark == null ? null : other.remark.copy();
        this.recordStatus = other.recordStatus == null ? null : other.recordStatus.copy();
        this.coCode = other.coCode == null ? null : other.coCode.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.dateCreated = other.dateCreated == null ? null : other.dateCreated.copy();
        this.authoriseBy = other.authoriseBy == null ? null : other.authoriseBy.copy();
        this.dateAuthorise = other.dateAuthorise == null ? null : other.dateAuthorise.copy();
    }

    @Override
    public BLCustomerPvcCriteria copy() {
        return new BLCustomerPvcCriteria(this);
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

    public StringFilter getCif() {
        return cif;
    }

    public StringFilter cif() {
        if (cif == null) {
            cif = new StringFilter();
        }
        return cif;
    }

    public void setCif(StringFilter cif) {
        this.cif = cif;
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

    public StringFilter getDateOfBirth() {
        return dateOfBirth;
    }

    public StringFilter dateOfBirth() {
        if (dateOfBirth == null) {
            dateOfBirth = new StringFilter();
        }
        return dateOfBirth;
    }

    public void setDateOfBirth(StringFilter dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public StringFilter getLegalId() {
        return legalId;
    }

    public StringFilter legalId() {
        if (legalId == null) {
            legalId = new StringFilter();
        }
        return legalId;
    }

    public void setLegalId(StringFilter legalId) {
        this.legalId = legalId;
    }

    public StringFilter getLegalType() {
        return legalType;
    }

    public StringFilter legalType() {
        if (legalType == null) {
            legalType = new StringFilter();
        }
        return legalType;
    }

    public void setLegalType(StringFilter legalType) {
        this.legalType = legalType;
    }

    public StringFilter getBranch() {
        return branch;
    }

    public StringFilter branch() {
        if (branch == null) {
            branch = new StringFilter();
        }
        return branch;
    }

    public void setBranch(StringFilter branch) {
        this.branch = branch;
    }

    public StringFilter getBlCustomerId() {
        return blCustomerId;
    }

    public StringFilter blCustomerId() {
        if (blCustomerId == null) {
            blCustomerId = new StringFilter();
        }
        return blCustomerId;
    }

    public void setBlCustomerId(StringFilter blCustomerId) {
        this.blCustomerId = blCustomerId;
    }

    public StringFilter getNameBl() {
        return nameBl;
    }

    public StringFilter nameBl() {
        if (nameBl == null) {
            nameBl = new StringFilter();
        }
        return nameBl;
    }

    public void setNameBl(StringFilter nameBl) {
        this.nameBl = nameBl;
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

    public StringFilter getLegalIdTypeBl() {
        return legalIdTypeBl;
    }

    public StringFilter legalIdTypeBl() {
        if (legalIdTypeBl == null) {
            legalIdTypeBl = new StringFilter();
        }
        return legalIdTypeBl;
    }

    public void setLegalIdTypeBl(StringFilter legalIdTypeBl) {
        this.legalIdTypeBl = legalIdTypeBl;
    }

    public StringFilter getLegalIdNumber() {
        return legalIdNumber;
    }

    public StringFilter legalIdNumber() {
        if (legalIdNumber == null) {
            legalIdNumber = new StringFilter();
        }
        return legalIdNumber;
    }

    public void setLegalIdNumber(StringFilter legalIdNumber) {
        this.legalIdNumber = legalIdNumber;
    }

    public StringFilter getMatchAttr() {
        return matchAttr;
    }

    public StringFilter matchAttr() {
        if (matchAttr == null) {
            matchAttr = new StringFilter();
        }
        return matchAttr;
    }

    public void setMatchAttr(StringFilter matchAttr) {
        this.matchAttr = matchAttr;
    }

    public StringFilter getValueAttr() {
        return valueAttr;
    }

    public StringFilter valueAttr() {
        if (valueAttr == null) {
            valueAttr = new StringFilter();
        }
        return valueAttr;
    }

    public void setValueAttr(StringFilter valueAttr) {
        this.valueAttr = valueAttr;
    }

    public StringFilter getWeightAttr() {
        return weightAttr;
    }

    public StringFilter weightAttr() {
        if (weightAttr == null) {
            weightAttr = new StringFilter();
        }
        return weightAttr;
    }

    public void setWeightAttr(StringFilter weightAttr) {
        this.weightAttr = weightAttr;
    }

    public StringFilter getScore() {
        return score;
    }

    public StringFilter score() {
        if (score == null) {
            score = new StringFilter();
        }
        return score;
    }

    public void setScore(StringFilter score) {
        this.score = score;
    }

    public StringFilter getStatus() {
        return status;
    }

    public StringFilter status() {
        if (status == null) {
            status = new StringFilter();
        }
        return status;
    }

    public void setStatus(StringFilter status) {
        this.status = status;
    }

    public StringFilter getRemark() {
        return remark;
    }

    public StringFilter remark() {
        if (remark == null) {
            remark = new StringFilter();
        }
        return remark;
    }

    public void setRemark(StringFilter remark) {
        this.remark = remark;
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
        final BLCustomerPvcCriteria that = (BLCustomerPvcCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(cif, that.cif) &&
            Objects.equals(fullName, that.fullName) &&
            Objects.equals(dateOfBirth, that.dateOfBirth) &&
            Objects.equals(legalId, that.legalId) &&
            Objects.equals(legalType, that.legalType) &&
            Objects.equals(branch, that.branch) &&
            Objects.equals(blCustomerId, that.blCustomerId) &&
            Objects.equals(nameBl, that.nameBl) &&
            Objects.equals(dateOfBirthBl, that.dateOfBirthBl) &&
            Objects.equals(legalIdTypeBl, that.legalIdTypeBl) &&
            Objects.equals(legalIdNumber, that.legalIdNumber) &&
            Objects.equals(matchAttr, that.matchAttr) &&
            Objects.equals(valueAttr, that.valueAttr) &&
            Objects.equals(weightAttr, that.weightAttr) &&
            Objects.equals(score, that.score) &&
            Objects.equals(status, that.status) &&
            Objects.equals(remark, that.remark) &&
            Objects.equals(recordStatus, that.recordStatus) &&
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
            cif,
            fullName,
            dateOfBirth,
            legalId,
            legalType,
            branch,
            blCustomerId,
            nameBl,
            dateOfBirthBl,
            legalIdTypeBl,
            legalIdNumber,
            matchAttr,
            valueAttr,
            weightAttr,
            score,
            status,
            remark,
            recordStatus,
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
        return "BLCustomerPvcCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (cif != null ? "cif=" + cif + ", " : "") +
                (fullName != null ? "fullName=" + fullName + ", " : "") +
                (dateOfBirth != null ? "dateOfBirth=" + dateOfBirth + ", " : "") +
                (legalId != null ? "legalId=" + legalId + ", " : "") +
                (legalType != null ? "legalType=" + legalType + ", " : "") +
                (branch != null ? "branch=" + branch + ", " : "") +
                (blCustomerId != null ? "blCustomerId=" + blCustomerId + ", " : "") +
                (nameBl != null ? "nameBl=" + nameBl + ", " : "") +
                (dateOfBirthBl != null ? "dateOfBirthBl=" + dateOfBirthBl + ", " : "") +
                (legalIdTypeBl != null ? "legalIdTypeBl=" + legalIdTypeBl + ", " : "") +
                (legalIdNumber != null ? "legalIdNumber=" + legalIdNumber + ", " : "") +
                (matchAttr != null ? "matchAttr=" + matchAttr + ", " : "") +
                (valueAttr != null ? "valueAttr=" + valueAttr + ", " : "") +
                (weightAttr != null ? "weightAttr=" + weightAttr + ", " : "") +
                (score != null ? "score=" + score + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (remark != null ? "remark=" + remark + ", " : "") +
                (recordStatus != null ? "recordStatus=" + recordStatus + ", " : "") +
                (coCode != null ? "coCode=" + coCode + ", " : "") +
                (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
                (dateCreated != null ? "dateCreated=" + dateCreated + ", " : "") +
                (authoriseBy != null ? "authoriseBy=" + authoriseBy + ", " : "") +
                (dateAuthorise != null ? "dateAuthorise=" + dateAuthorise + ", " : "") +
                "}";
    }
}
