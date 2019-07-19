package com.material.types;

public class LoginMsg {
    String token;
    String error;

    public String getToken() {
        return token;
    }

    public String getError() {
        return error;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setError(String error) {
        this.error = error;
    }

    public LoginMsg(String token, String error) {
        this.token = token;
        this.error = error;
    }
}
