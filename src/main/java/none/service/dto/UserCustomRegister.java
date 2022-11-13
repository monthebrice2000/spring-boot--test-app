package none.service.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import none.domain.UserCustom;

/**
 * A DTO for the {@link UserCustom} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserCustomRegister implements Serializable {

    @NotNull
    @Size(min = 0, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 0, max = 20)
    private String lastName;

    @NotNull
    @Size(min = 0, max = 20)
    private String username;

    @NotNull
    @Size(min = 0, max = 255)
    private String email;

    @NotNull
    @Size(min = 0, max = 255)
    private String password;

    @NotNull
    private String role;

    //@NotNull
    //private String[] authorities;

    //@NotNull
    //private Boolean isNotLocked;

    //@NotNull
    //private Boolean isActived;

    //private Date joinDate;

    //private Date lastLoginDate;

    //private Date lastLoginDateDisplay;

    public UserCustomRegister() {}

    public UserCustomRegister(UserCustom userCustom) {
        //this.id = userCustom.getId();
        this.firstName = userCustom.getFirstName();
        this.lastName = userCustom.getLastName();
        this.username = userCustom.getUsername();
        this.email = userCustom.getEmail();
        this.password = userCustom.getPassword();
        this.role = userCustom.getRole();
        /*this.authorities = userCustom.getAuthorities();
        this.isNotLocked = userCustom.getIsNotLocked();
        this.isActived = userCustom.getIsActived();
        this.joinDate = userCustom.getJoinDate();
        this.lastLoginDate = userCustom.getLastLoginDate();
        this.lastLoginDateDisplay = userCustom.getLastLoginDateDisplay();*/
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /*public String[] getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String[] authorities) {
        this.authorities = authorities;
    }

    public Boolean getIsNotLocked() {
        return isNotLocked;
    }

    public void setIsNotLocked(Boolean isNotLocked) {
        this.isNotLocked = isNotLocked;
    }

    public Boolean getIsActived() {
        return isActived;
    }

    public void setIsActived(Boolean isActived) {
        this.isActived = isActived;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Date getLastLoginDateDisplay() {
        return lastLoginDateDisplay;
    }

    public void setLastLoginDateDisplay(Date lastLoginDateDisplay) {
        this.lastLoginDateDisplay = lastLoginDateDisplay;
    }*/

    /*@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserCustomRegister)) {
            return false;
        }

        UserCustomRegister userCustom = (UserCustomRegister) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userCustom.id);
    }*/

    /*@Override
    public int hashCode() {
        return Objects.hash(this.id);
    }*/

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCustom{" +
            //"id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", roles='" + getRole() + "'" +
//            ", authorities='" + getAuthorities() + "'" +
//            ", isNotLocked='" + getIsNotLocked() + "'" +
//            ", isActived='" + getIsActived() + "'" +
//            ", joinDate='" + getJoinDate() + "'" +
//            ", lastLoginDate='" + getLastLoginDate() + "'" +
//            ", lastLoginDateDisplay='" + getLastLoginDateDisplay() + "'" +
            "}";
    }
}
