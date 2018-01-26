package com.credo.users.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credo.users.dto.UserData;
import com.credo.users.dto.UsersDTO;
import com.credo.users.dto.UsersPermisionsDTO;
import com.credo.users.dto.UsersPrevilegesDTO;
import com.credo.users.exceptions.UserNotFoundError;
import com.credo.users.model.UserPrevilegesResponse;
import com.credo.users.model.Users;
import com.credo.users.service.UserService;

@RestController
public class UsersController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "getUser", method = RequestMethod.GET, produces = "application/json")
	public List<UserPrevilegesResponse> getUsers(@RequestParam("uname") String uname) {		
		List<UserPrevilegesResponse> userPrevList = userService.getUser(uname);		
		return userPrevList;
	}
	
	@RequestMapping(value = "getAdminUser", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<UserPrevilegesResponse> getAdminUsers(@RequestBody UsersDTO reqUser) {		
		List<UserPrevilegesResponse> userPrevList = userService.getAdminUser(reqUser);		
		return userPrevList;
	}
	
	/*@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UsersPrevilegesDTO userPrevileges,HttpServletRequest request) {
		HttpSession hs=request.getSession();		
		if(null!=hs) {
			Users userDto=(Users)hs.getAttribute("LoggedUser");		
			if (userService.updateUser(userDto.getUserid(), userPrevileges))
				return new ResponseEntity<String>(HttpStatus.OK);
			else {
				return new ResponseEntity<UserNotFoundError>(
						new UserNotFoundError("User Not found, please enter valid user...."), HttpStatus.NOT_FOUND);
			}
		}else {
			return null;
		}
	}*/
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UsersPrevilegesDTO userPrevileges,HttpServletRequest request) {
		if (userService.updateUser(userPrevileges))
			return new ResponseEntity<String>(HttpStatus.OK);
		
		return new ResponseEntity<UserNotFoundError>(
						new UserNotFoundError("User Not found, please enter valid user...."), HttpStatus.NOT_FOUND);
	}
	@RequestMapping(value = "saveUser", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> saveUser(@RequestBody UsersDTO reqUser) {
		if (userService.saveUser(reqUser))
			return new ResponseEntity<String>("User Created Successfully....", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Please Enter the Mandatory Parameters to save....", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "isPendingTrainings", method = RequestMethod.GET,produces = "application/json")
	public boolean isPendingTrainings(@RequestParam("uname") String uname,@RequestParam("action") String action) {		
		
		if(userService.isPendingTrainings(uname,action)) {			
			return true;
		}
		else {
			return false;
		}		
	}
	

	
	
}
