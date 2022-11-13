package none.service.dto;

import javax.validation.constraints.NotNull;

public class UserCustomLogin {

    @NotNull
    private String username;

    @NotNull
    private String password;

    public UserCustomLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserCustomLogin{" + "username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }
}
