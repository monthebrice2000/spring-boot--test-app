package none.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.*;

/**
 * Criteria class for the {@link none.domain.UserCustom} entity. This class is used
 * in {@link none.web.rest.UserCustomResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /user-customs?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserCustomCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter firstName;

    private StringFilter lastName;

    private StringFilter username;

    private StringFilter email;

    private StringFilter password;

    private StringFilter roles;

    private StringFilter authorities;

    private BooleanFilter isNotLocked;

    private BooleanFilter isActived;

    private LocalDateFilter joinDate;

    private LocalDateFilter lastLoginDate;

    private LocalDateFilter lastLoginDateDisplay;

    private Boolean distinct;

    public UserCustomCriteria() {}

    public UserCustomCriteria(UserCustomCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.firstName = other.firstName == null ? null : other.firstName.copy();
        this.lastName = other.lastName == null ? null : other.lastName.copy();
        this.username = other.username == null ? null : other.username.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.password = other.password == null ? null : other.password.copy();
        this.roles = other.roles == null ? null : other.roles.copy();
        this.authorities = other.authorities == null ? null : other.authorities.copy();
        this.isNotLocked = other.isNotLocked == null ? null : other.isNotLocked.copy();
        this.isActived = other.isActived == null ? null : other.isActived.copy();
        this.joinDate = other.joinDate == null ? null : other.joinDate.copy();
        this.lastLoginDate = other.lastLoginDate == null ? null : other.lastLoginDate.copy();
        this.lastLoginDateDisplay = other.lastLoginDateDisplay == null ? null : other.lastLoginDateDisplay.copy();
        this.distinct = other.distinct;
    }

    @Override
    public UserCustomCriteria copy() {
        return new UserCustomCriteria(this);
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

    public StringFilter getUsername() {
        return username;
    }

    public StringFilter username() {
        if (username == null) {
            username = new StringFilter();
        }
        return username;
    }

    public void setUsername(StringFilter username) {
        this.username = username;
    }

    public StringFilter getEmail() {
        return email;
    }

    public StringFilter email() {
        if (email == null) {
            email = new StringFilter();
        }
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getPassword() {
        return password;
    }

    public StringFilter password() {
        if (password == null) {
            password = new StringFilter();
        }
        return password;
    }

    public void setPassword(StringFilter password) {
        this.password = password;
    }

    public StringFilter getRoles() {
        return roles;
    }

    public StringFilter roles() {
        if (roles == null) {
            roles = new StringFilter();
        }
        return roles;
    }

    public void setRoles(StringFilter roles) {
        this.roles = roles;
    }

    public StringFilter getAuthorities() {
        return authorities;
    }

    public StringFilter authorities() {
        if (authorities == null) {
            authorities = new StringFilter();
        }
        return authorities;
    }

    public void setAuthorities(StringFilter authorities) {
        this.authorities = authorities;
    }

    public BooleanFilter getIsNotLocked() {
        return isNotLocked;
    }

    public BooleanFilter isNotLocked() {
        if (isNotLocked == null) {
            isNotLocked = new BooleanFilter();
        }
        return isNotLocked;
    }

    public void setIsNotLocked(BooleanFilter isNotLocked) {
        this.isNotLocked = isNotLocked;
    }

    public BooleanFilter getIsActived() {
        return isActived;
    }

    public BooleanFilter isActived() {
        if (isActived == null) {
            isActived = new BooleanFilter();
        }
        return isActived;
    }

    public void setIsActived(BooleanFilter isActived) {
        this.isActived = isActived;
    }

    public LocalDateFilter getJoinDate() {
        return joinDate;
    }

    public LocalDateFilter joinDate() {
        if (joinDate == null) {
            joinDate = new LocalDateFilter();
        }
        return joinDate;
    }

    public void setJoinDate(LocalDateFilter joinDate) {
        this.joinDate = joinDate;
    }

    public LocalDateFilter getLastLoginDate() {
        return lastLoginDate;
    }

    public LocalDateFilter lastLoginDate() {
        if (lastLoginDate == null) {
            lastLoginDate = new LocalDateFilter();
        }
        return lastLoginDate;
    }

    public void setLastLoginDate(LocalDateFilter lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public LocalDateFilter getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public LocalDateFilter lastLoginDateDisplay() {
        if (lastLoginDateDisplay == null) {
            lastLoginDateDisplay = new LocalDateFilter();
        }
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(LocalDateFilter lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserCustomCriteria that = (UserCustomCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(username, that.username) &&
            Objects.equals(email, that.email) &&
            Objects.equals(password, that.password) &&
            Objects.equals(roles, that.roles) &&
            Objects.equals(authorities, that.authorities) &&
            Objects.equals(isNotLocked, that.isNotLocked) &&
            Objects.equals(isActived, that.isActived) &&
            Objects.equals(joinDate, that.joinDate) &&
            Objects.equals(lastLoginDate, that.lastLoginDate) &&
            Objects.equals(lastLoginDateDisplay, that.lastLoginDateDisplay) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            id,
            firstName,
            lastName,
            username,
            email,
            password,
            roles,
            authorities,
            isNotLocked,
            isActived,
            joinDate,
            lastLoginDate,
            lastLoginDateDisplay,
            distinct
        );
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCustomCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (firstName != null ? "firstName=" + firstName + ", " : "") +
            (lastName != null ? "lastName=" + lastName + ", " : "") +
            (username != null ? "username=" + username + ", " : "") +
            (email != null ? "email=" + email + ", " : "") +
            (password != null ? "password=" + password + ", " : "") +
            (roles != null ? "roles=" + roles + ", " : "") +
            (authorities != null ? "authorities=" + authorities + ", " : "") +
            (isNotLocked != null ? "isNotLocked=" + isNotLocked + ", " : "") +
            (isActived != null ? "isActived=" + isActived + ", " : "") +
            (joinDate != null ? "joinDate=" + joinDate + ", " : "") +
            (lastLoginDate != null ? "lastLoginDate=" + lastLoginDate + ", " : "") +
            (lastLoginDateDisplay != null ? "lastLoginDateDisplay=" + lastLoginDateDisplay + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
