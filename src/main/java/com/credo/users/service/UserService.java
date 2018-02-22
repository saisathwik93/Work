package com.credo.users.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credo.users.dto.UpdateMaxYearsDTO;
import com.credo.users.dto.UpdateYearsList;

import com.credo.users.dto.UsersDTO;
import com.credo.users.dto.UsersPrevilegesDTO;
import com.credo.users.model.Actions;
import com.credo.users.model.ActionsToTrainings;
import com.credo.users.model.Experience;
import com.credo.users.model.ExperienceToTrainings;
import com.credo.users.model.Previleges;
import com.credo.users.model.PrevilegesToActions;
import com.credo.users.model.PrevilegesToTrainings;
import com.credo.users.model.Trainings;
import com.credo.users.model.TrainingsResponse;
import com.credo.users.model.UserCompletedTrainings;
import com.credo.users.model.UserExperience;
import com.credo.users.model.UserPrevilegesResponse;
import com.credo.users.model.Users;
import com.credo.users.model.UsersPrevileges;
import com.credo.users.repository.ActionsRepository;
import com.credo.users.repository.ActionsToTrainingsRepository;
import com.credo.users.repository.ExperienceRepository;
import com.credo.users.repository.ExperienceToTrainingsRespository;
import com.credo.users.repository.PrevilegesRepository;
import com.credo.users.repository.PrevilegesToActionsRepository;
import com.credo.users.repository.PrevilegesToTrainingsRepository;
import com.credo.users.repository.TrainingsRepository;
import com.credo.users.repository.UserCompletedTrainingsRepository;
import com.credo.users.repository.UserExperienceRepository;
import com.credo.users.repository.UsersPrevilegesRepository;
import com.credo.users.repository.UsersRepository;

@Service("userService")
public class UserService {
	@Autowired
	UsersRepository userRepository;
	
	@Autowired
	ActionsRepository actionsRepository;
	
	@Autowired
	ExperienceRepository experienceRepository;
	
	@Autowired
	TrainingsRepository trainingsRepository;	
	
	@Autowired
	UsersPrevilegesRepository usersPrevilegesRepository;
	
	@Autowired
	UserCompletedTrainingsRepository userCompletedTrainingsRepository;
	
	@Autowired
	UserExperienceRepository userExperienceRepository;
	
	@Autowired
	PrevilegesToActionsRepository previlegesToActionsRepository;
	
	@Autowired
	PrevilegesToTrainingsRepository previlegesToTrainingsRepository;
	
	@Autowired
	ActionsToTrainingsRepository actionsToTrainingsRepository;
	
	@Autowired
	ExperienceToTrainingsRespository experienceToTrainingsRepository;
	
	@Autowired
	PrevilegesRepository previlegesRepository;
	
	List<UsersPrevileges> userPrevilegesList = null;
	List<UserPrevilegesResponse> userPrevilegeResp = null;
	
	public Users findUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	// Updating max years from excel sheet into database .
	public boolean updateMaxYears(List<UpdateMaxYearsDTO> updateMaxYearsList) {		
		List<Boolean> flagList=new ArrayList<Boolean>();		
		for(UpdateMaxYearsDTO updateMaxYearsDTO:updateMaxYearsList) {
				Long exptrid=updateMaxYearsDTO.getExperiencetotrainingid();
				ExperienceToTrainings ett=experienceToTrainingsRepository.findOne(exptrid);			
				Trainings tr=ett.getTraining();
				Experience exp=ett.getExperience();
				if(null!=ett && tr.getTrainingid()== updateMaxYearsDTO.getTrainingids() && exp.getExpid()==updateMaxYearsDTO.getExpids()) {
					experienceToTrainingsRepository.updateExperience(updateMaxYearsDTO.getMaxyears(), exptrid); // Updating the max years of experience and storing it into database
					flagList.add(true);
				}
		}
		if(flagList.contains(false))
			return false;
		else 
			return true;
	}
	
	
	// Checking whether the user has pending training associated to his privilege as well as the action he is performing
	public boolean isPendingTrainings(String uname, String action) {
		Users user=userRepository.getUserByUsername(uname); // Fetch the username from repository.
		List<String> pts=new ArrayList<String>();
		UsersPrevileges userPrev=user.getUsersPrevileges();
		Previleges prev=userPrev.getPrevileges();
		List<String> ptList=new ArrayList<String>();
		Set<PrevilegesToTrainings> pttList=prev.getPrevilegestotrainings();
		for(PrevilegesToTrainings ptl:pttList) {
			pts.add(ptl.getTrainings().getTrainingname());  // Fetching the privilegestotrainings of the user
		}
		
		Actions act=actionsRepository.getActionByName(action); // Get the actions of the user
		ActionsToTrainings att=act.getActionstotrainings(); // Get the actionstotrainings associated
		pts.add(att.getTrainings().getTrainingname());
		for(String pendingTr:pts) {				
			Trainings pendingT=trainingsRepository.getTrainingByName(pendingTr);
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTraining(pendingT);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
			if(et.getMaxyears()>ue.getYears()) {      
				ptList.add(pendingTr);            // If the max years is greater than user's years of experience add to the list
			}
		}
		if(ptList.size()>0) {
			return false; // returns false if there is a training in the list
		}		
		else return true; // returns true if the list is empty.
	}
	
	
	public boolean updateUser(UsersPrevilegesDTO userPrevileges) {
		boolean retFlag = false;
		Users user=userRepository.findOne(userPrevileges.getUserid());
		if (user.isFullaccess()) {					                  // updating the user's years of experience by Admin
			if (null != userPrevileges.getJd() && !userPrevileges.getJd().isEmpty() && null != userPrevileges.getYears()
					&& !userPrevileges.getYears().isEmpty()) {
				updateYears(user, userPrevileges);
			}
			retFlag = true;
		} else {
			updateYears(user, userPrevileges);
			retFlag = true;
		}
		return retFlag;
	}


	
	
	private void updateYears(Users user, UsersPrevilegesDTO userPrevileges) {
		List<UpdateYearsList> updateYearsList=new ArrayList<UpdateYearsList>();
		List<Long> yearsList=userPrevileges.getYears();				
	
		Set<UserExperience> userExpList=user.getUserExperience();		// Get the userExpList					
		for(UserExperience ue:userExpList) {			
			Experience e=ue.getExperience();   // Get the experience of the user
			List<String> jdLists=userPrevileges.getJd();
			for(String jd:jdLists) {
				if(e.getExpname().equalsIgnoreCase(jd)) {        // if the experience is equal to the JD then update the years of experience.
					UpdateYearsList uyl=new UpdateYearsList();					
					uyl.setUserExpId(ue.getUserexpid());
					uyl.setYears(yearsList.get(jdLists.indexOf(jd)));
					updateYearsList.add(uyl); // update the years
				}
			}	
		}
		for(UpdateYearsList uy:updateYearsList) {
			userExperienceRepository.updateExperience(uy.getYears(),uy.getUserExpId()); // Updating the userexperience in the expereince repository based on the user id.
		}
	
		userRepository.save(user); // Update the details of the user.
	}
	
	public Users getLoggedInUser(UsersDTO reqUser) {
		Users user = userRepository.getUser(reqUser.getUsername(), reqUser.getPassword());
		if(null!=user)
			return user;
		else return null;
	}
	
	// Get the details of all the users in the database using Admin access
	public List<UserPrevilegesResponse> getAdminUser(UsersDTO reqUser) {		
		Users user = userRepository.getUser(reqUser.getUsername(),reqUser.getPassword());	
		
		if (null != user) {
			if (user.isFullaccess()) {                                       // Check the access of the user
				userPrevilegeResp=new ArrayList<UserPrevilegesResponse>();				
				userPrevilegesList = usersPrevilegesRepository.findAll();    // For Admin access find the details of all the users.
				for(UsersPrevileges userPrevilege: userPrevilegesList) {					
					UserPrevilegesResponse upr=new UserPrevilegesResponse();
					Users users=userPrevilege.getUsers();
					Actions action = new Actions();
					generateUserPrevResponse(userPrevilege,upr,users,action);	  //Generating the userprivilege response.		
				}
			}
		}
		return userPrevilegeResp;
	}	
	
	// Fetching the details of the user based on the username and action.
	public List<UserPrevilegesResponse> getUser(String uname,String actionName) {
		Actions action=actionsRepository.getActionByName(actionName);
		Users user = userRepository.getUserByUsername(uname); // Fetch the username from the repository.
		userPrevilegeResp = new ArrayList<UserPrevilegesResponse>(1);				
		UserPrevilegesResponse upr=new UserPrevilegesResponse();
		UsersPrevileges uprev=user.getUsersPrevileges();		 // Fetching the user privileges associated to the user.
		generateUserPrevResponse(uprev,upr,user,action);         // Generating the userresponse.
		return userPrevilegeResp;
	}	
	


	
	private void getPendingAndWaivedTrainings(UsersPrevileges up, Users user, Set<UserCompletedTrainings> completedTraining,
			UserPrevilegesResponse upr, Actions actions) {		
		List<String> ptList=new ArrayList<String>();   // Pending Training return storage
		List<String> wtList=new ArrayList<String>();   // Waived training return storage
		List<TrainingsResponse> bts=new ArrayList<>();
		List<TrainingsResponse> ptList2 = new ArrayList<>();
		// Get privileges
		Previleges prev=up.getPrevileges(); 

		// Get Trainings based on privilege
		Set<PrevilegesToTrainings> pttList=prev.getPrevilegestotrainings(); 

		// Get Trainings based on actions associated with the privilege
		Set<ActionsToTrainings> actList = new HashSet<>();		
		Set<PrevilegesToActions> prevToActionsList=prev.getPrevilegestoactions();
		for(PrevilegesToActions prevAct:prevToActionsList) {
			Actions action=prevAct.getActions();
			actList.add(action.getActionstotrainings());
		
			/*ActionsToTrainings actiontraining=actionsToTrainingsRepository.getTrainingByAction(action);
			Trainings training=actiontraining.getTrainings();*/
			Trainings training=action.getActionstotrainings().getTrainings();
		
			if(action.getActionname().equals(actions.getActionname())) {  // Get the action name
				TrainingsResponse tr = new TrainingsResponse();
				tr.setName(training.getTrainingname());
				tr.setUrl(training.getUrl());
				ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTraining(training);
				Experience e=et.getExperience();
				UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
				if(et.getMaxyears()>ue.getYears()) {
					bts.add(tr); // Get the training based on the action name.					
				} 			
			}
		}
	
		
		upr.setBlockedtrainings(bts);
		
		// Filter out the completed trainings from both sets
		Set<Long> completedIds = new HashSet<>();
		for(UserCompletedTrainings pt : completedTraining) completedIds.add(pt.getTrainings().getTrainingid()); // Get ids of the training						
		pttList.removeIf((PrevilegesToTrainings pt) -> {
		    return (completedIds.contains(pt.getTrainings().getTrainingid()));
		});
		actList.removeIf((ActionsToTrainings act) -> {
		    return (completedIds.contains(act.getTrainings().getTrainingid()));
		});
		
		
		List<Trainings> trainingList=new ArrayList<Trainings>();
		for(PrevilegesToTrainings ptl:pttList) {
			trainingList.add(ptl.getTrainings());
		}
		for(ActionsToTrainings actl:actList) {
			trainingList.add(actl.getTrainings());
		}
		List<Trainings> traingRespList=new ArrayList<Trainings>();
	

		
		// Adding into the training response object the url
		List<TrainingsResponse> tresponses = new ArrayList<>();
		for(PrevilegesToTrainings ptl:pttList) {
			TrainingsResponse tr = new TrainingsResponse();
			Trainings trn=ptl.getTrainings();    // Get the trainings
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTraining(trn);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user); //get the userexperience by experience id.
			traingRespList.add(trn);
			tr.setName(trn.getTrainingname());
			tr.setUrl(trn.getUrl());
			//tresponses.add(tr);
			if(et.getMaxyears()>ue.getYears()) {    // if the max years are greater than the user's training add to the list
				ptList2.add(tr);
			} 
		}
		
		// Checking the ActionstoTrainings List
		for(ActionsToTrainings actl:actList) {
			TrainingsResponse tr = new TrainingsResponse();
			Trainings trn=actl.getTrainings();
			traingRespList.add(trn);         //Add the trainings
			tr.setName(trn.getTrainingname()); // Get the training name
			tr.setUrl(trn.getUrl());            // Get the Url associated with the training
			tresponses.add(tr);			
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTraining(trn);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
			tr.setName(trn.getTrainingname());      //Set the training name
			tr.setUrl(trn.getUrl());                 //Set the Url
			
			if(et.getMaxyears()>ue.getYears()) {
				ptList2.add(tr);
			} 
		}
		

		// Based on user experience, adds to pending training list or waived training list
		
		for(Trainings trang:trainingList) {				
			//Trainings pendingT=trainingsRepository.findOne(trang);  // TODO: this needs to be by ID. If two trainngs have the same name, will be by a bug
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTraining(trang);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
			if(et.getMaxyears()>ue.getYears()) {
				ptList.add(trang.getTrainingname());
			} else {
				wtList.add(trang.getTrainingname());
			}
		}
		
		// Sort the list of waived training
		Collections.sort(wtList);
		
		// Sort the pending training list
		Collections.sort(ptList2, new Comparator<TrainingsResponse>() {
			@Override
			public int compare (TrainingsResponse o1, TrainingsResponse o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		ptList2.removeAll(bts);
		upr.setPendingtrainings(ptList2);
		
		String[] wtarray;
		if(wtList.size()<=0) {
			wtarray=new String[1];
		}
		wtarray= new String[wtList.size()];
		 wtList.toArray(wtarray);
		upr.setWaivedtrainings(wtarray);
		
		
		String[] ptArray;
		ptArray= new String[ptList.size()];
		ptList.toArray(ptArray);
		upr.setPendingtrainings2(ptArray);
	}
	
		
	
	private void generateUserPrevResponse(UsersPrevileges userPrevilege, UserPrevilegesResponse upr,Users user,Actions actions) {

		//Fetch the user's based on the privilege
		Users users=userPrevilege.getUsers();
		upr.setUserid(users.getUserid());//1           //1.set the user id
		upr.setUsername(users.getUsername());      // 2.Set the user name
		
		UsersPrevileges up=users.getUsersPrevileges();  // 
		upr.setPrevileges(up.getPrevileges().getPrevname()); //3.Set the privileges
		Long userPrevId=up.getPrevileges().getPrevid(); //Get the user privilege id.
		
		PrevilegesToActions previlegeToAction=previlegesToActionsRepository.findOne(userPrevId);
		Set<PrevilegesToActions> prevToActionsList=previlegeToAction.getPrevileges().getPrevilegestoactions();
		
		
		String actionNames=null;
		for(PrevilegesToActions prevAction:prevToActionsList) {
			if(null==actionNames) actionNames=prevAction.getActions().getActionname();
			else actionNames=actionNames+","+prevAction.getActions().getActionname();    
		}
		upr.setActions(actionNames);//4.Set the Action names.
		
		//get completed Trainings
		Set<UserCompletedTrainings> userCompletedTrainingsList=user.getUsercompletedtrainings();
		List<String> cts=new ArrayList<String>();
		for(UserCompletedTrainings uct:userCompletedTrainingsList) {
			cts.add(uct.getTrainings().getTrainingname());
		}
		Collections.sort(cts);
		String[] ctarray = new String[cts.size()];
		cts.toArray(ctarray );
		upr.setCompletedtrainings(ctarray);   // 5.Set the completed training
		
		
		//get Experience
		List<String> jdList=new ArrayList<String>();
		List<Long> yearsList=new ArrayList<Long>();
		Set<UserExperience> userExpList=users.getUserExperience();		
		for(UserExperience uex:userExpList) {
			jdList.add(uex.getExperience().getExpname());
			yearsList.add(uex.getYears());  
				
		}
		//6.getPending Training with Url		
	    getPendingAndWaivedTrainings(up,user,userCompletedTrainingsList, upr,actions);
		
		String[] exp = new String[userExpList.size()];
		jdList.toArray(exp);
		upr.setJd(exp);       // 7. Set the jd.
		
		Long[] yearsExp = new Long[userExpList.size()];
		yearsList.toArray(yearsExp);
		upr.setYears(yearsExp);  //8. Set the years of experience
		userPrevilegeResp.add(upr);  // Set the user privilege response.
	}
}