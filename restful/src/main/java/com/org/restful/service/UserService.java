package com.org.restful.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.restful.model.UserDetails;
import com.org.restful.model.UserInformation;
import com.org.restful.model.UserResponse;
import com.org.restful.repository.UserDetailsRepository;
import com.org.restful.repository.UserInformationRepository;
import com.org.restful.userdto.UserDTO;


@Service("UserService")
public class UserService {

	@Autowired 
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserInformationRepository userInformationRepository;
	
	List<UserInformation> userInformationList = null;
	List<UserResponse> userResponse = null;
	List<UserDetails> userDetailsList = null;
	
	
	public Optional<UserDetails> findUserById(Long userid) {
		return userDetailsRepository.findById(userid);
	}
	
	
public List<UserResponse> getUserResponse(UserDTO userDTO)	{
	
  UserDetails userdetails = userDetailsRepository.getUserDetails(userDTO.getUsername(), userDTO.getPassword());
	
	if(null!=userdetails) {
		 UserDetails userdetail = userDetailsRepository.getUserByUsername(userDTO.getUsername());
		 userResponse = new ArrayList<UserResponse>(1);	
		 UserResponse ur = new UserResponse();
		 UserInformation ui = userdetail.getUserInformation();
		 generateResponse(ui,ur,userdetail);
	}
	
	return userResponse;
}


private void generateResponse(UserInformation ui, UserResponse ur, UserDetails userdetail) {
	
	//Generating the user response to be sent to the client
	ui=userdetail.getUserInformation();
	    ur.setId(userdetail.getUserid());
		ur.setUsername(userdetail.getUsername());
		ur.setDesignation(ui.getDesignation());
		ur.setCity(ui.getCity());
		ur.setCountry(ui.getCountry());
		userResponse.add(ur);
	
}


public boolean updateDetails(UserResponse ur) {
	// TODO Auto-generated method stub
	boolean status=false;
	UserInformation ui = new UserInformation();
	UserDetails ud = userDetailsRepository.getOne(ur.getId());
	if(ud!=null) {
		updateUserDetails(ud, ui,ur);
		status=true;
	}
	
	
	return status;
}


private void updateUserDetails(UserDetails ud, UserInformation ui, UserResponse ur) {
	// TODO Auto-generated method stub
	
	ui =ud.getUserInformation();	
	ui.setCity(ur.getCity());
	ui.setCountry(ur.getCountry());
	ui.setDesignation(ur.getDesignation());
	userInformationRepository.save(ui);
}


public List<UserResponse> getUserDetails(Long id) {
	
	userResponse =new ArrayList<>(1);
	UserDetails ud = userDetailsRepository.getOne(id);
	UserResponse ur = new UserResponse();
	UserInformation ui = ud.getUserInformation();
	generateResponse(ui, ur, ud);
	return userResponse;
}


public boolean registerDetails(UserDTO dto) {
	// TODO Auto-generated method stub
	
	boolean status =false;
	
	if(dto!=null) {
		
		UserDetails ud = new UserDetails();
		//ud.setUserid(dto.getId());
		ud.setUsername(dto.getUsername());
		ud.setPassword(dto.getPassword());
		userDetailsRepository.save(ud);
		
		status=true;
	}
	
	return status;
}


public List<UserResponse> getAdmin(UserDTO dto) {
	// TODO Auto-generated method stub
	
	if(dto.getUsername().equals("admin") && (dto.getPassword().equals("admin"))) {
		userResponse = new ArrayList<UserResponse>();
		userInformationList = userInformationRepository.findAll();
		
		for(UserInformation ui: userInformationList) {
			 UserResponse ur = new UserResponse();
			 UserDetails ud = ui.getUserDetails();
	       	generateResponse(ui, ur, ud);	 
		 }	
	}
	return userResponse;
}
}
