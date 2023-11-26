package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.BLUploadFile} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.BLUploadFileResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /bl-upload-files?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BLUploadFileCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter fileName;

    private StringFilter systemFileName;

    private StringFilter description;

    private StringFilter tagetCompany;

    private StringFilter validate;

    private StringFilter serviceStatus;

    private LongFilter fileSize;

    private StringFilter recordStatus;

    private StringFilter uploadBy;

    private StringFilter dateUpload;

    private StringFilter authoriseBy;

    private StringFilter dateAuthorise;

    public BLUploadFileCriteria() {}

    public BLUploadFileCriteria(BLUploadFileCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.fileName = other.fileName == null ? null : other.fileName.copy();
        this.systemFileName = other.systemFileName == null ? null : other.systemFileName.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.tagetCompany = other.tagetCompany == null ? null : other.tagetCompany.copy();
        this.validate = other.validate == null ? null : other.validate.copy();
        this.serviceStatus = other.serviceStatus == null ? null : other.serviceStatus.copy();
        this.fileSize = other.fileSize == null ? null : other.fileSize.copy();
        this.recordStatus = other.recordStatus == null ? null : other.recordStatus.copy();
        this.uploadBy = other.uploadBy == null ? null : other.uploadBy.copy();
        this.dateUpload = other.dateUpload == null ? null : other.dateUpload.copy();
        this.authoriseBy = other.authoriseBy == null ? null : other.authoriseBy.copy();
        this.dateAuthorise = other.dateAuthorise == null ? null : other.dateAuthorise.copy();
    }

    @Override
    public BLUploadFileCriteria copy() {
        return new BLUploadFileCriteria(this);
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

    public StringFilter getFileName() {
        return fileName;
    }

    public StringFilter fileName() {
        if (fileName == null) {
            fileName = new StringFilter();
        }
        return fileName;
    }

    public void setFileName(StringFilter fileName) {
        this.fileName = fileName;
    }

    public StringFilter getSystemFileName() {
        return systemFileName;
    }

    public StringFilter systemFileName() {
        if (systemFileName == null) {
            systemFileName = new StringFilter();
        }
        return systemFileName;
    }

    public void setSystemFileName(StringFilter systemFileName) {
        this.systemFileName = systemFileName;
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

    public StringFilter getTagetCompany() {
        return tagetCompany;
    }

    public StringFilter tagetCompany() {
        if (tagetCompany == null) {
            tagetCompany = new StringFilter();
        }
        return tagetCompany;
    }

    public void setTagetCompany(StringFilter tagetCompany) {
        this.tagetCompany = tagetCompany;
    }

    public StringFilter getValidate() {
        return validate;
    }

    public StringFilter validate() {
        if (validate == null) {
            validate = new StringFilter();
        }
        return validate;
    }

    public void setValidate(StringFilter validate) {
        this.validate = validate;
    }

    public StringFilter getServiceStatus() {
        return serviceStatus;
    }

    public StringFilter serviceStatus() {
        if (serviceStatus == null) {
            serviceStatus = new StringFilter();
        }
        return serviceStatus;
    }

    public void setServiceStatus(StringFilter serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public LongFilter getFileSize() {
        return fileSize;
    }

    public LongFilter fileSize() {
        if (fileSize == null) {
            fileSize = new LongFilter();
        }
        return fileSize;
    }

    public void setFileSize(LongFilter fileSize) {
        this.fileSize = fileSize;
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

    public StringFilter getUploadBy() {
        return uploadBy;
    }

    public StringFilter uploadBy() {
        if (uploadBy == null) {
            uploadBy = new StringFilter();
        }
        return uploadBy;
    }

    public void setUploadBy(StringFilter uploadBy) {
        this.uploadBy = uploadBy;
    }

    public StringFilter getDateUpload() {
        return dateUpload;
    }

    public StringFilter dateUpload() {
        if (dateUpload == null) {
            dateUpload = new StringFilter();
        }
        return dateUpload;
    }

    public void setDateUpload(StringFilter dateUpload) {
        this.dateUpload = dateUpload;
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
        final BLUploadFileCriteria that = (BLUploadFileCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(fileName, that.fileName) &&
            Objects.equals(systemFileName, that.systemFileName) &&
            Objects.equals(description, that.description) &&
            Objects.equals(tagetCompany, that.tagetCompany) &&
            Objects.equals(validate, that.validate) &&
            Objects.equals(serviceStatus, that.serviceStatus) &&
            Objects.equals(fileSize, that.fileSize) &&
            Objects.equals(recordStatus, that.recordStatus) &&
            Objects.equals(uploadBy, that.uploadBy) &&
            Objects.equals(dateUpload, that.dateUpload) &&
            Objects.equals(authoriseBy, that.authoriseBy) &&
            Objects.equals(dateAuthorise, that.dateAuthorise)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            fileName,
            systemFileName,
            description,
            tagetCompany,
            validate,
            serviceStatus,
            fileSize,
            recordStatus,
            uploadBy,
            dateUpload,
            authoriseBy,
            dateAuthorise
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLUploadFileCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (fileName != null ? "fileName=" + fileName + ", " : "") +
            (systemFileName != null ? "systemFileName=" + systemFileName + ", " : "") +
            (description != null ? "description=" + description + ", " : "") +
            (tagetCompany != null ? "tagetCompany=" + tagetCompany + ", " : "") +
            (validate != null ? "validate=" + validate + ", " : "") +
            (serviceStatus != null ? "serviceStatus=" + serviceStatus + ", " : "") +
            (fileSize != null ? "fileSize=" + fileSize + ", " : "") +
            (recordStatus != null ? "recordStatus=" + recordStatus + ", " : "") +
            (uploadBy != null ? "uploadBy=" + uploadBy + ", " : "") +
            (dateUpload != null ? "dateUpload=" + dateUpload + ", " : "") +
            (authoriseBy != null ? "authoriseBy=" + authoriseBy + ", " : "") +
            (dateAuthorise != null ? "dateAuthorise=" + dateAuthorise + ", " : "") +
            "}";
    }
}
