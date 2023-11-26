package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.BLParamter} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.BLParamterResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bl-paramters?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BLParamterCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter keyId;

    private StringFilter keyValue;

    private StringFilter recordStatus;

    private StringFilter description;

    private StringFilter createdBy;

    private StringFilter dateCreated;

    private StringFilter authoriseBy;

    private StringFilter dateAuthorise;

    public BLParamterCriteria() {}

    public BLParamterCriteria(BLParamterCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.keyId = other.keyId == null ? null : other.keyId.copy();
        this.keyValue = other.keyValue == null ? null : other.keyValue.copy();
        this.recordStatus = other.recordStatus == null ? null : other.recordStatus.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.dateCreated = other.dateCreated == null ? null : other.dateCreated.copy();
        this.authoriseBy = other.authoriseBy == null ? null : other.authoriseBy.copy();
        this.dateAuthorise = other.dateAuthorise == null ? null : other.dateAuthorise.copy();
    }

    @Override
    public BLParamterCriteria copy() {
        return new BLParamterCriteria(this);
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

    public StringFilter getKeyId() {
        return keyId;
    }

    public StringFilter keyId() {
        if (keyId == null) {
            keyId = new StringFilter();
        }
        return keyId;
    }

    public void setKeyId(StringFilter keyId) {
        this.keyId = keyId;
    }

    public StringFilter getKeyValue() {
        return keyValue;
    }

    public StringFilter keyValue() {
        if (keyValue == null) {
            keyValue = new StringFilter();
        }
        return keyValue;
    }

    public void setKeyValue(StringFilter keyValue) {
        this.keyValue = keyValue;
    }

    public StringFilter getDescription() {
        return description;
    }

    public StringFilter description() {
        if (description == null) {
            description = new StringFilter();
        }
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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
        final BLParamterCriteria that = (BLParamterCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(keyId, that.keyId) &&
            Objects.equals(keyValue, that.keyValue) &&
            Objects.equals(description, that.description) &&
            Objects.equals(recordStatus, that.recordStatus) &&
            Objects.equals(createdBy, that.createdBy) &&
            Objects.equals(dateCreated, that.dateCreated) &&
            Objects.equals(authoriseBy, that.authoriseBy) &&
            Objects.equals(dateAuthorise, that.dateAuthorise)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, keyId, keyValue, description, recordStatus, createdBy, dateCreated, authoriseBy, dateAuthorise);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLParamterCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (keyId != null ? "keyId=" + keyId + ", " : "") +
            (keyValue != null ? "keyValue=" + keyValue + ", " : "") +
            (recordStatus != null ? "recordStatus=" + recordStatus + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (dateCreated != null ? "dateCreated=" + dateCreated + ", " : "") +
            (authoriseBy != null ? "authoriseBy=" + authoriseBy + ", " : "") +
            (dateAuthorise != null ? "dateAuthorise=" + dateAuthorise + ", " : "") +
            "}";
    }
}
