package model;

import java.time.LocalDateTime;

public class User {

    private String username;

    private String password;

    private String tokenId;

    private LocalDateTime tokenExpires;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public LocalDateTime getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(LocalDateTime tokenExpires) {
        this.tokenExpires = tokenExpires;
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
}
