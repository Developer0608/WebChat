package com.example.webchat.model;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
    private final String username;
    private final String email;
    
    public JwtResponse(String jwttoken, String username, String email) {
        this.jwttoken = jwttoken;
        this.username = username;
        this.email = email;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getJwttoken() {
        return jwttoken;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

	
}