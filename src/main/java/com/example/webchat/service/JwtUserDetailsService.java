package com.example.webchat.service;

import java.util.ArrayList;
import java.util.Map;

import com.example.webchat.dto.UserDTO;
import com.example.webchat.repository.UserDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Map<String, Object> userDetails = userDAO.getUserDetail(email);

		if (userDetails.size() > 0) {
            return new User(userDetails.get("email").toString(), userDetails.get("password").toString(), new ArrayList<>());
        } else {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
	}

    public String save(UserDTO userDTO) throws Exception {
        // user.setPassword(bcryptEncoder.encode(user.getPassword()));

        com.example.webchat.model.User user = new com.example.webchat.model.User();

        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUsername());
        user.setPassword(bcryptEncoder.encode(userDTO.getPassword()));

		userDAO.save(user);

        return "Registered";
	}
}