package vn.com.pvcombank.domain;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Bảng BLUploadFile : lưu thông tin các file upload
 */
@ApiModel(description = "Bảng BLUploadFile : lưu thông tin các file upload")
@Entity
@Table(name = "bl_upload_file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BLUploadFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "source_file_name")
    private String sourceFileName;

    @Column(name = "source_file_size")
    private Long sourceFileSize;

    @Column(name = "source_total_record")
    private Long sourceTotalRecord;

    @Column(name = "system_file_name")
    private String systemFileName;

    @Column(name = "system_file_size")
    private Long systemFileSize;

    @Column(name = "system_total_record")
    private Long systemTotalRecord;

    @Column(name = "taget_company")
    private String tagetCompany;

    @Column(name = "validate")
    private String validate;

    @Column(name = "source_id")
    private Long sourceId;

    @Column(name = "service_status")
    private String serviceStatus;

    @Column(name = "record_status")
    private String recordStatus;

    @Column(name = "upload_by")
    private String uploadBy;

    @Column(name = "date_upload")
    private String dateUpload;

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

    public BLUploadFile id(Long id) {
        this.id = id;
        return this;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public BLUploadFile sourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
        return this;
    }

    public Long getSourceFileSize() {
        return sourceFileSize;
    }

    public void setSourcefileSize(Long sourceFileSize) {
        this.sourceFileSize = sourceFileSize;
    }

    public Long getSourceTotalRecord() {
        return sourceTotalRecord;
    }

    public void setSourceTotalRecord(Long sourceTotalRecord) {
        this.sourceTotalRecord = sourceTotalRecord;
    }

    public Long getSystemFileSize() {
        return systemFileSize;
    }

    public void setSystemFileSize(Long systemFileSize) {
        this.systemFileSize = systemFileSize;
    }

    public Long getSystemTotalRecord() {
        return systemTotalRecord;
    }

    public void setSystemTotalRecord(Long systemTotalRecord) {
        this.systemTotalRecord = systemTotalRecord;
    }

    public String getSystemFileName() {
        return this.systemFileName;
    }

    public BLUploadFile systemFileName(String systemFileName) {
        this.systemFileName = systemFileName;
        return this;
    }

    public void setSystemFileName(String systemFileName) {
        this.systemFileName = systemFileName;
    }

    public String getTagetCompany() {
        return this.tagetCompany;
    }

    public BLUploadFile tagetCompany(String tagetCompany) {
        this.tagetCompany = tagetCompany;
        return this;
    }

    public void setTagetCompany(String tagetCompany) {
        this.tagetCompany = tagetCompany;
    }

    public void setSourceFileSize(Long sourceFileSize) {
        this.sourceFileSize = sourceFileSize;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public BLUploadFile sourceId(Long sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public String getValidate() {
        return this.validate;
    }

    public BLUploadFile validate(String validate) {
        this.validate = validate;
        return this;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public String getServiceStatus() {
        return this.serviceStatus;
    }

    public BLUploadFile serviceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
        return this;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public BLUploadFile systemFileSize(Long systemFileSize) {
        this.systemFileSize = systemFileSize;
        return this;
    }

    public BLUploadFile sourceFileSize(Long sourceFileSize) {
        this.sourceFileSize = sourceFileSize;
        return this;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public BLUploadFile recordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getUploadBy() {
        return this.uploadBy;
    }

    public BLUploadFile uploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
        return this;
    }

    public void setUploadBy(String uploadBy) {
        this.uploadBy = uploadBy;
    }

    public String getDateUpload() {
        return this.dateUpload;
    }

    public BLUploadFile dateUpload(String dateUpload) {
        this.dateUpload = dateUpload;
        return this;
    }

    public void setDateUpload(String dateUpload) {
        this.dateUpload = dateUpload;
    }

    public String getAuthoriseBy() {
        return this.authoriseBy;
    }

    public BLUploadFile authoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
        return this;
    }

    public void setAuthoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public String getDateAuthorise() {
        return this.dateAuthorise;
    }

    public BLUploadFile dateAuthorise(String dateAuthorise) {
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
        if (!(o instanceof BLUploadFile)) {
            return false;
        }
        return id != null && id.equals(((BLUploadFile) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLUploadFile{" +
            "id=" + getId() +
            ", sourceFileName='" + getSourceFileName() + "'" +
            ", systemFileName='" + getSystemFileName() + "'" +
            ", tagetCompany='" + getTagetCompany() + "'" +
            ", validate='" + getValidate() + "'" +
            ", serviceStatus='" + getServiceStatus() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", uploadBy='" + getUploadBy() + "'" +
            ", dateUpload='" + getDateUpload() + "'" +
            ", authoriseBy='" + getAuthoriseBy() + "'" +
            ", dateAuthorise='" + getDateAuthorise() + "'" +
            "}";
    }
}
