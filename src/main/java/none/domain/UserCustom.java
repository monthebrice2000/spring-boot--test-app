package none.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A UserCustom.
 */
@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserCustom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 0, max = 20)
    @Column(name = "first_name", length = 20, nullable = false, unique = true)
    private String firstName;

    @NotNull
    @Size(min = 0, max = 20)
    @Column(name = "last_name", length = 20, nullable = false)
    private String lastName;

    @NotNull
    @Size(min = 0, max = 20)
    @Column(name = "username", length = 20, nullable = false, unique = true)
    private String username;

    @NotNull
    @Size(min = 0, max = 255)
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @NotNull
    @Size(min = 0, max = 150)
    @Column(name = "password", length = 150, nullable = false)
    private String password;

    @NotNull
    @Column(name = "role", nullable = false)
    private String role;

    @NotNull
    @Column(name = "authorities", nullable = false)
    private String[] authorities;

    @NotNull
    @Column(name = "is_not_locked", nullable = false)
    private Boolean isNotLocked;

    @NotNull
    @Column(name = "is_actived", nullable = false)
    private Boolean isActived;

    @Column(name = "join_date")
    private Date joinDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Column(name = "last_login_date_display")
    private Date lastLoginDateDisplay;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public UserCustom id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public UserCustom firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public UserCustom lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public UserCustom username(String username) {
        this.setUsername(username);
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public UserCustom email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public UserCustom password(String password) {
        this.setPassword(password);
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public UserCustom roles(String role) {
        this.setRole(role);
        return this;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getAuthorities() {
        return this.authorities;
    }

    public UserCustom authorities(String[] authorities) {
        this.setAuthorities(authorities);
        return this;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public Boolean getIsNotLocked() {
        return this.isNotLocked;
    }

    public UserCustom isNotLocked(Boolean isNotLocked) {
        this.setIsNotLocked(isNotLocked);
        return this;
    }

    public void setIsNotLocked(Boolean isNotLocked) {
        this.isNotLocked = isNotLocked;
    }

    public Boolean getIsActived() {
        return this.isActived;
    }

    public UserCustom isActived(Boolean isActived) {
        this.setIsActived(isActived);
        return this;
    }

    public void setIsActived(Boolean isActived) {
        this.isActived = isActived;
    }

    public Date getJoinDate() {
        return this.joinDate;
    }

    public UserCustom joinDate(Date joinDate) {
        this.setJoinDate(joinDate);
        return this;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLastLoginDate() {
        return this.lastLoginDate;
    }

    public UserCustom lastLoginDate(Date lastLoginDate) {
        this.setLastLoginDate(lastLoginDate);
        return this;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return this.lastLoginDateDisplay;
    }

    public UserCustom lastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.setLastLoginDateDisplay(lastLoginDateDisplay);
        return this;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCustom)) {
            return false;
        }
        return id != null && id.equals(((UserCustom) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCustom{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", role='" + getRole() + "'" +
            ", authorities='" + getAuthorities() + "'" +
            ", isNotLocked='" + getIsNotLocked() + "'" +
            ", isActived='" + getIsActived() + "'" +
            ", joinDate='" + getJoinDate() + "'" +
            ", lastLoginDate='" + getLastLoginDate() + "'" +
            ", lastLoginDateDisplay='" + getLastLoginDateDisplay() + "'" +
            "}";
    }
}
