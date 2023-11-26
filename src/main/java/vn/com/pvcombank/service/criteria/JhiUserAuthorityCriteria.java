package vn.com.pvcombank.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link vn.com.pvcombank.domain.JhiUserAuthority} entity. This class is used
 * in {@link vn.com.pvcombank.web.rest.JhiUserAuthorityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /jhi-user-authorities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class JhiUserAuthorityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter userId;

    private StringFilter authorityName;

    public JhiUserAuthorityCriteria() {}

    public JhiUserAuthorityCriteria(JhiUserAuthorityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.userId = other.userId == null ? null : other.userId.copy();
        this.authorityName = other.authorityName == null ? null : other.authorityName.copy();
    }

    @Override
    public JhiUserAuthorityCriteria copy() {
        return new JhiUserAuthorityCriteria(this);
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

    public StringFilter getUserId() {
        return userId;
    }

    public StringFilter userId() {
        if (userId == null) {
            userId = new StringFilter();
        }
        return userId;
    }

    public void setUserId(StringFilter userId) {
        this.userId = userId;
    }

    public StringFilter getAuthorityName() {
        return authorityName;
    }

    public StringFilter authorityName() {
        if (authorityName == null) {
            authorityName = new StringFilter();
        }
        return authorityName;
    }

    public void setAuthorityName(StringFilter authorityName) {
        this.authorityName = authorityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JhiUserAuthorityCriteria that = (JhiUserAuthorityCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(authorityName, that.authorityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, authorityName);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JhiUserAuthorityCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (userId != null ? "userId=" + userId + ", " : "") +
            (authorityName != null ? "authorityName=" + authorityName + ", " : "") +
            "}";
    }
}
