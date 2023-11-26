package vn.com.pvcombank.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.UniqueElements;

/**
 * A BLMappingParam.
 */
@Entity
@Table(name = "bl_source_data")
public class BLSourceData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "source_name")
    private String sourceName;

    @Column(name = "source_file_prefix")
    private String sourceFilePrefix;

    @Column(name = "source_cols")
    private String sourceCols;

    @Column(name = "source_ref")
    private String sourceRef;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BLSourceData id(Long id) {
        this.id = id;
        return this;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public BLSourceData sourceName(String sourceName) {
        this.sourceName = sourceName;
        return this;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceFilePrefix() {
        return this.sourceFilePrefix;
    }

    public BLSourceData sourceFilePrefix(String sourceFilePrefix) {
        this.sourceFilePrefix = sourceFilePrefix;
        return this;
    }

    public void setSourceFilePrefix(String sourceFilePrefix) {
        this.sourceFilePrefix = sourceFilePrefix;
    }

    public String getSourceCols() {
        return this.sourceCols;
    }

    public BLSourceData sourceCols(String sourceCols) {
        this.sourceCols = sourceCols;
        return this;
    }

    public void setSourceCols(String sourceCols) {
        this.sourceCols = sourceCols;
    }

    public String getSourceRef() {
        return this.sourceRef;
    }

    public BLSourceData sourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
        return this;
    }

    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    public String getRecordStatus() {
        return this.recordStatus;
    }

    public BLSourceData recordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
        return this;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BLSourceData createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateCreated() {
        return this.dateCreated;
    }

    public BLSourceData dateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthoriseBy() {
        return this.authoriseBy;
    }

    public BLSourceData authoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
        return this;
    }

    public void setAuthoriseBy(String authoriseBy) {
        this.authoriseBy = authoriseBy;
    }

    public String getDateAuthorise() {
        return this.dateAuthorise;
    }

    public BLSourceData dateAuthorise(String dateAuthorise) {
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
        if (!(o instanceof BLSourceData)) {
            return false;
        }
        return id != null && id.equals(((BLSourceData) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BLSourceData{" +
            "id=" + getId() +
            ", sourceName='" + getSourceName() + "'" +
            ", sourceFilePrefix='" + getSourceFilePrefix() + "'" +
            ", sourceCols='" + getSourceCols() + "'" +
            ", sourceRef='" + getSourceRef() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", authoriseBy='" + getAuthoriseBy() + "'" +
            ", dateAuthorise='" + getDateAuthorise() + "'" +
            "}";
    }
}
