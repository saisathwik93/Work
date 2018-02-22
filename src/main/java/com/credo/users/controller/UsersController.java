package com.credo.users.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.credo.users.dto.UpdateMaxYearsDTO;
import com.credo.users.dto.UsersDTO;
import com.credo.users.dto.UsersPrevilegesDTO;
import com.credo.users.exceptions.UserNotFoundError;
import com.credo.users.model.UserPrevilegesResponse;
import com.credo.users.service.UserService;

@RestController
public class UsersController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "updateMaxYears", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> getUsers(@RequestBody UpdateMaxYearsDTO updateMaxYears) {
		if (userService.updateMaxYears(updateMaxYears))
			return new ResponseEntity<String>("Max Experience Updated Successfully....", HttpStatus.OK);
		else {
			return new ResponseEntity<String>("Updating Experience Got failed....", HttpStatus.OK);
		}
	}
	
	
		@RequestMapping(value = "getUser", method = RequestMethod.GET, produces = "application/json")
		public List<UserPrevilegesResponse> getUsers(@RequestParam("uname") String uname, @RequestParam("action") String action) {		
			List<UserPrevilegesResponse> userPrevList = userService.getUser(uname, action);		
			return userPrevList;
		}
	
	@RequestMapping(value = "getAdminUser", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public List<UserPrevilegesResponse> getAdminUsers(@RequestBody UsersDTO reqUser) {		
		List<UserPrevilegesResponse> userPrevList = userService.getAdminUser(reqUser);		
		return userPrevList;
	}
	
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.PUT, consumes = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody UsersPrevilegesDTO userPrevileges,HttpServletRequest request) {
		if (userService.updateUser(userPrevileges))
			return new ResponseEntity<String>(HttpStatus.OK);
		return new ResponseEntity<UserNotFoundError>(
						new UserNotFoundError("User Not found, please enter valid user...."), HttpStatus.NOT_FOUND);
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
