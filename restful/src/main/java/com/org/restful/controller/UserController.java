package com.org.restful.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.org.restful.model.UserDetails;
import com.org.restful.model.UserInformation;
import com.org.restful.model.UserResponse;
import com.org.restful.service.UserService;
import com.org.restful.userdto.UserDTO;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	
@RequestMapping(value="/postdetails", method=RequestMethod.POST,produces="application/json", consumes ="application/json")
public List<UserResponse> getDetails(@RequestBody UserDTO userDTO ){
	List<UserResponse> info = userService.getUserResponse(userDTO);
	return info ;	
}

@RequestMapping(value="/updateDetails" , method=RequestMethod.PUT,consumes ="application/json" )
	public String updateDetails(@RequestBody UserResponse ur){
		
	if(userService.updateDetails(ur)) {
		
		return "Updated Successfully";
				}
	else 
		return "UnSuccessful";
	}


@RequestMapping(value="/getDetails", method=RequestMethod.GET, produces="application/json")
public List<UserResponse> getUserDetails(@RequestParam Long id){
	
	List<UserResponse> resp = userService.getUserDetails(id);
	return resp;
}


@RequestMapping(value="/register", method=RequestMethod.POST, consumes="application/json")
public String registerDetails(@RequestBody UserDTO dto) {
	
	if(userService.registerDetails(dto)) {
		
		return "Registration Successful";
	}
	
	else {
	return "UnSuccessful";
	}
	
}


@RequestMapping(value="/admin", method=RequestMethod.POST, produces="application/json", consumes="application/json")
public List<UserResponse> getAdmin(@RequestBody UserDTO dto){
	List<UserResponse> ad = userService.getAdmin(dto);
	return ad;
	
}



@RequestMapping(value="/multiply", method= RequestMethod.GET, produces="application/json")
public String multiplication(@RequestParam String input) {
	
	String temp = "";
	
	for(int i = input.length()-1; i>=0; i--) {
		temp = temp + input.charAt(i);
	}
	return temp;
}
}

