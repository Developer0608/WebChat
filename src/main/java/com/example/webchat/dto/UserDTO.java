package com.example.webchat.dto;

public class UserDTO {
    private String username;
    private String email;
    private String password;
    private int otp;

    public UserDTO() {

    }

    public UserDTO(String email) {
        this.email = email;
    }

    public UserDTO(String username, String email, String password, int otp) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.otp = otp;
    }

    public int getOtp() {
        return otp;
    }

    public void setOtp(int otp) {
        this.otp = otp;
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

    @Override
    public String toString() {
        return "UserDTO [email=" + email + ", otp=" + otp + ", password=" + password + ", username=" + username + "]";
    }
}
