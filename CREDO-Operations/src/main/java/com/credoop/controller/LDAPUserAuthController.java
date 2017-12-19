package com.credoop.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credoop.bean.LDAPUserBean;
import com.credoop.bean.LdapRequest;
import com.credoop.bean.LdapResponse;
import com.credoop.helper.LDAPHelper;

@RestController
@RequestMapping("/v1/rest/")
public class LDAPUserAuthController {

	@Autowired
	private LDAPHelper ldapHelper;

	@PostMapping("/finduser")
	public ResponseEntity<LdapResponse> findUser(@RequestBody LdapRequest request) {
		LdapResponse response = new LdapResponse();
		try {
			response = ldapHelper.findUser(request);
			//our logic//
		} catch (Exception e) {
			e.printStackTrace();
			response.setError(e.getMessage());
			return new ResponseEntity<LdapResponse>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LdapResponse>(response, HttpStatus.OK);
	}

	@GetMapping("/finduser/{appId}/{username:.*}/{password:.*}")
	public ResponseEntity<LdapResponse> findUser(@PathVariable("appId") final String appId,
			@PathVariable("username") final String username, @PathVariable("password") final String password) {
		LdapResponse response = new LdapResponse();
		try {
			LdapRequest request = new LdapRequest();
			request.setAppKey(appId);
			request.setUsername(username);
			request.setPassword(password);
			response = ldapHelper.findUser(request);
		} 
		
		
		catch (Exception e) {
			e.printStackTrace();
			response.setError(e.getMessage());
			return new ResponseEntity<LdapResponse>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<LdapResponse>(response, HttpStatus.OK);
	}
	}