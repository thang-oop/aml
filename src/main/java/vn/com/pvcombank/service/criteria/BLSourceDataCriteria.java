package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.BLSourceData} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.BLSourceDataResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bl-mapping-params?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BLSourceDataCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter sourceName;

    private StringFilter sourceFilePrefix;

    private StringFilter sourceCols;

    private StringFilter sourceRef;

    private StringFilter recordStatus;

    private StringFilter createdBy;

    private StringFilter dateCreated;

    private StringFilter authoriseBy;

    private StringFilter dateAuthorise;

    public BLSourceDataCriteria() {}

    public BLSourceDataCriteria(BLSourceDataCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.sourceName = other.sourceName == null ? null : other.sourceName.copy();
        this.sourceFilePrefix = other.sourceFilePrefix == null ? null : other.sourceFilePrefix.copy();
        this.sourceCols = other.sourceCols == null ? null : other.sourceCols.copy();
        this.sourceRef = other.sourceRef == null ? null : other.sourceRef.copy();
        this.recordStatus = other.recordStatus == null ? null : other.recordStatus.copy();
        this.createdBy = other.createdBy == null ? null : other.createdBy.copy();
        this.dateCreated = other.dateCreated == null ? null : other.dateCreated.copy();
        this.authoriseBy = other.authoriseBy == null ? null : other.authoriseBy.copy();
        this.dateAuthorise = other.dateAuthorise == null ? null : other.dateAuthorise.copy();
    }

    @Override
    public BLSourceDataCriteria copy() {
        return new BLSourceDataCriteria(this);
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

    public StringFilter getSourceName() {
        return sourceName;
    }

    public StringFilter sourceName() {
        if (sourceName == null) {
            sourceName = new StringFilter();
        }
        return sourceName;
    }

    public void setSourceName(StringFilter sourceName) {
        this.sourceName = sourceName;
    }

    public StringFilter getSourceFilePrefix() {
        return sourceFilePrefix;
    }

    public StringFilter sourceFilePrefix() {
        if (sourceFilePrefix == null) {
            sourceFilePrefix = new StringFilter();
        }
        return sourceFilePrefix;
    }

    public void setSourceFilePrefix(StringFilter sourceFilePrefix) {
        this.sourceFilePrefix = sourceFilePrefix;
    }

    public StringFilter getSourceCols() {
        return sourceCols;
    }

    public StringFilter sourceCols() {
        if (sourceCols == null) {
            sourceCols = new StringFilter();
        }
        return sourceCols;
    }

    public void setSourceCols(StringFilter sourceCols) {
        this.sourceCols = sourceCols;
    }

    public StringFilter getSourceRef() {
        return sourceRef;
    }

    public StringFilter sourceRef() {
        if (sourceRef == null) {
            sourceRef = new StringFilter();
        }
        return sourceRef;
    }

    public void setSourceRef(StringFilter sourceRef) {
        this.sourceRef = sourceRef;
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
        final BLSourceDataCriteria that = (BLSourceDataCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(sourceName, that.sourceName) &&
            Objects.equals(sourceFilePrefix, that.sourceFilePrefix) &&
            Objects.equals(sourceCols, that.sourceCols) &&
            Objects.equals(sourceRef, that.sourceRef) &&
            Objects.equals(recordStatus, that.recordStatus) &&
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
            sourceName,
            sourceFilePrefix,
            sourceCols,
            sourceRef,
            recordStatus,
            createdBy,
            dateCreated,
            authoriseBy,
            dateAuthorise
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLSourceDataCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (sourceName != null ? "sourceName=" + sourceName + ", " : "") +
            (sourceFilePrefix != null ? "sourceFilePrefix=" + sourceFilePrefix + ", " : "") +
            (sourceCols != null ? "sourceCols=" + sourceCols + ", " : "") +
            (sourceRef != null ? "sourceRef=" + sourceRef + ", " : "") +
            (recordStatus != null ? "recordStatus=" + recordStatus + ", " : "") +
            (createdBy != null ? "createdBy=" + createdBy + ", " : "") +
            (dateCreated != null ? "dateCreated=" + dateCreated + ", " : "") +
            (authoriseBy != null ? "authoriseBy=" + authoriseBy + ", " : "") +
            (dateAuthorise != null ? "dateAuthorise=" + dateAuthorise + ", " : "") +
            "}";
    }
}
