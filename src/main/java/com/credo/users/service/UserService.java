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

import com.credo.users.dto.UpdateYearsList;
import com.credo.users.dto.UserData;
import com.credo.users.dto.UsersDTO;
import com.credo.users.dto.UsersPermisionsDTO;
import com.credo.users.dto.UsersPrevilegesDTO;
import com.credo.users.helper.UserPrevilegeReponseWrapper;
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
	ExperienceToTrainingsRespository experienceToTrainingsRepository;
	
	@Autowired
	PrevilegesRepository previlegesRepository;
	
	List<UsersPrevileges> userPrevilegesList = null;
	List<UserPrevilegesResponse> userPrevilegeResp = null;
	
	public Users findUserById(Long id) {
		return userRepository.findOne(id);
	}
	
	public boolean isPendingTrainings(String uname, String action) {
		Users user=userRepository.getUserByUsername(uname);
		List<String> pts=new ArrayList<String>();
		UsersPrevileges userPrev=user.getUsersPrevileges();
		Previleges prev=userPrev.getPrevileges();
		List<String> ptList=new ArrayList<String>();
		//List<String> pendingTrainingList=getPendingTrainings(userPrev,user);
		Set<PrevilegesToTrainings> pttList=prev.getPrevilegestotrainings();
		for(PrevilegesToTrainings ptl:pttList) {
			pts.add(ptl.getTrainings().getTrainingname());
		}
		//Set<PrevilegesToActions> prevToActions=prev.getPrevilegestoactions();		
		Actions act=actionsRepository.getActionByName(action);
		ActionsToTrainings att=act.getActionstotrainings();
		pts.add(att.getTrainings().getTrainingname());
		for(String pendingTr:pts) {				
			Trainings pendingT=trainingsRepository.getTrainingByName(pendingTr);
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTrainingName(pendingT);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
			if(et.getMaxyears()>ue.getYears()) {
				ptList.add(pendingTr);
			}
		}
		if(ptList.size()>0) {
			return false;
		}		
		else return true;
	}
	
	
	
	/*public boolean updateUser(long id, UsersPrevilegesDTO userPrevileges) {
		Users loggeduser = findUserById(id);		
		boolean retFlag=false;
		//Experience exp;
		if (null != loggeduser) {
			Users user = findUserById(userPrevileges.getUserid());
			if (loggeduser.isFullaccess()) {
				if(null!=userPrevileges.getPrevileges() && !userPrevileges.getPrevileges().isEmpty()) {
					updatePrevileges(user,userPrevileges);
				}
				if(null!=userPrevileges.getActions() && !userPrevileges.getActions().isEmpty()) {
					updateActions(user,userPrevileges);
				}
				if(null!=userPrevileges.getCompletedtrainings() && !userPrevileges.getCompletedtrainings().isEmpty()) {
					updateCompletedTrainings(user,userPrevileges);
				}
				if(null!=userPrevileges.getPendingtrainings() && !userPrevileges.getPendingtrainings().isEmpty()) {
					updatePendingTrainigns(user,userPrevileges);
				}
				if(null!=userPrevileges.getJd() && !userPrevileges.getJd().isEmpty() && null!=userPrevileges.getYears() && !userPrevileges.getYears().isEmpty()) {
					updateYears(user,userPrevileges);
				}
				retFlag=true;
			}else {			
				updateYears(user,userPrevileges);
				retFlag=true;
			}
		}
		return retFlag;
	}*/
	public boolean updateUser(UsersPrevilegesDTO userPrevileges) {
		boolean retFlag = false;
		//Users users = findUserById(userPrevileges.getUserid());
		Users user=userRepository.findOne(userPrevileges.getUserid());
		if (user.isFullaccess()) {			
			if (null != userPrevileges.getPrevileges() && !userPrevileges.getPrevileges().isEmpty()) {
				updatePrevileges(user, userPrevileges);
			}
			if (null != userPrevileges.getActions() && !userPrevileges.getActions().isEmpty()) {
				updateActions(user, userPrevileges);
			}
			if (null != userPrevileges.getCompletedtrainings() && !userPrevileges.getCompletedtrainings().isEmpty()) {
				updateCompletedTrainings(user, userPrevileges);
			}
//			if (null != userPrevileges.getPendingtrainings() && !userPrevileges.getPendingtrainings().isEmpty()) {
//				updatePendingTrainigns(user, userPrevileges);
//			}
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


	private void updatePrevileges(Users user,UsersPrevilegesDTO userPrevileges){
		Previleges prev=previlegesRepository.getUserPrevByName(userPrevileges.getPrevileges());
		UsersPrevileges userPrev=user.getUsersPrevileges();
		userPrev.setPrevileges(prev);
		//usersPrevilegesRepository.save(userPrev);
		user.setUsersPrevileges(userPrev);
		userRepository.save(user);
	}
	private void updateActions(Users user,UsersPrevilegesDTO userPrevileges){
		UsersPrevileges up=user.getUsersPrevileges();
		Previleges p=up.getPrevileges();
		String actionNames[]=userPrevileges.getActions().split(",");
		Set<PrevilegesToActions> ptaset=new HashSet<PrevilegesToActions>();
		for(String actionName:actionNames) {
			Actions action=actionsRepository.getActionByName(actionName);
			PrevilegesToActions pta=new PrevilegesToActions();
			pta.setPrevileges(p);
			pta.setActions(action);
			ptaset.add(pta);
		}		
		Set<PrevilegesToActions> prevActns=p.getPrevilegestoactions();
		for(PrevilegesToActions e:prevActns) {
			previlegesToActionsRepository.deleteActions(e.getPrevilegestoactionid());
		}
		p.setPrevilegestoactions(ptaset);
		up.setPrevileges(p);
		user.setUsersPrevileges(up);
		//previlegesToActionsRepository.save(ptaset);
		userRepository.save(user);
		
	}
	private void updateCompletedTrainings(Users user,UsersPrevilegesDTO userPrevileges){		
		List<String> compltedTrainingsList=userPrevileges.getCompletedtrainings();
		Set<UserCompletedTrainings> ptaset=new HashSet<UserCompletedTrainings>();
		for(String completedTraining:compltedTrainingsList) {
			Trainings trng=trainingsRepository.getTrainingByName(completedTraining);
			UserCompletedTrainings uct=new UserCompletedTrainings();
			uct.setUsers(user);
			uct.setTrainings(trng);
			ptaset.add(uct);
		}		
		Set<UserCompletedTrainings> userComplTrngs=user.getUsercompletedtrainings();
		for(UserCompletedTrainings userComp:userComplTrngs) {
			userCompletedTrainingsRepository.deleteCompletedTraining(userComp.getUsercompletedtrainingid());
		}
		user.setUsercompletedtrainings(ptaset);		
		userRepository.save(user);
	}
//	private void updatePendingTrainigns(Users user,UsersPrevilegesDTO userPrevileges){
//		UsersPrevileges userPrev=user.getUsersPrevileges();
//		Previleges prev=userPrev.getPrevileges();
//		
//		Set<PrevilegesToTrainings> prevTrainingList=new HashSet<PrevilegesToTrainings>();		
//		List<String> pendingTraingsList=userPrevileges.getPendingtrainings();		
//		for(String trainingName:pendingTraingsList) {
//			Trainings training=trainingsRepository.getTrainingByName(trainingName);
//			PrevilegesToTrainings prevTraining=new PrevilegesToTrainings();
//			prevTraining.setTrainings(training);
//			prevTraining.setPrevileges(prev);
//			prevTrainingList.add(prevTraining);
//		}
//		Set<PrevilegesToTrainings> prevToTrngs=prev.getPrevilegestotrainings();
//		for(PrevilegesToTrainings prevTrng:prevToTrngs) {
//			previlegesToTrainingsRepository.deletePrevelegesToTraining(prevTrng.getPrevilegestotrainingid());
//		}
//		prev.setPrevilegestotrainings(prevTrainingList);
//		userPrev.setPrevileges(prev);
//		user.setUsersPrevileges(userPrev);
//		userRepository.save(user);
//	}
	
	private void updateYears(Users user, UsersPrevilegesDTO userPrevileges) {
		List<UpdateYearsList> updateYearsList=new ArrayList<UpdateYearsList>();
		List<Long> yearsList=userPrevileges.getYears();		
			
		int i=0;
		Set<UserExperience> userExpList=user.getUserExperience();		
						
		for(UserExperience ue:userExpList) {			
			Experience e=ue.getExperience();
			List<String> jdLists=userPrevileges.getJd();
			for(String jd:jdLists) {
				if(e.getExpname().equalsIgnoreCase(jd)) {
					UpdateYearsList uyl=new UpdateYearsList();					
					uyl.setUserExpId(ue.getUserexpid());
					uyl.setYears(yearsList.get(jdLists.indexOf(jd)));
					updateYearsList.add(uyl);
				}
			}			
			/*if(i>=jdList.size()) { break;}			
			Experience e1=experienceRepository.getUserExpByName(jdList.get(i));
			if(e.getExpname().equalsIgnoreCase(e1.getExpname())){			
				Set<ExperienceToTrainings> experienceToTrainings=e.getExperiencetotrainings();			
				for(ExperienceToTrainings et:experienceToTrainings) {
					if(et.getMaxyears()<=yearsList.get(i)) {
						//removeList.add(et.getTraining());
						Trainings t=et.getTraining();
						removeList.add(t);
					}
				}				
			}
			i++;*/			
		}
		for(UpdateYearsList uy:updateYearsList) {
			userExperienceRepository.updateExperience(uy.getYears(),uy.getUserExpId());
		}
//		Set<UserCompletedTrainings> uctl=new HashSet<UserCompletedTrainings>();
//		List<String> pendingTrainsList=getPendingTrainings(user.getUsersPrevileges(),user);
//		for(String pendingTr:pendingTrainsList) {				
//			Trainings pendingT=trainingsRepository.getTrainingByName(pendingTr);
//			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTrainingName(pendingT);
//			for(Long year:yearsList) {
//				if(et.getMaxyears()<=year) {
//					UserCompletedTrainings uct=new UserCompletedTrainings();
//					uct.setTrainings(pendingT);
//					uct.setUsers(user);
//					uctl.add(uct);
//				}			
//			
//			}
//		}
//		user.getUsercompletedtrainings().addAll(uctl);
		/*Set<UserCompletedTrainings> uctl=user.getUsercompletedtrainings();
		for(Trainings ett:removeList) {
			previlegesToTrainingsRepository.deletePrevilegesToTrainingByTraining(ett);
			UserCompletedTrainings uct=new UserCompletedTrainings();
			uct.setTrainings(ett);
			uct.setUsers(user);
			uctl.add(uct);
		}*/
		
		
		/*Set<UserCompletedTrainings> uctl=user.getUsercompletedtrainings();
		List<String> arr=getPendingTrainings(user.getUsersPrevileges());
		for(String pt:arr) {
			Trainings t=trainingsRepository.getTrainingByName(pt);
			ExperienceToTrainings ett=experienceToTrainingsRepository.getExperienceByTrainingName(t);
			if(ett.getMaxyears()<=userPrevileges.ge) {
				UserCompletedTrainings uct=new UserCompletedTrainings();
				uct.setTrainings(t);
				uct.setUsers(user);
				uctl.add(uct);
			}
		}*/
		userRepository.save(user);
	}
	
	public void updatePrevilege(Users user,UsersPrevilegesDTO userPrevileges) {
		UsersPrevileges userPrev=user.getUsersPrevileges();
		Previleges previlege=previlegesRepository.getUserPrevByName(userPrevileges.getPrevileges());
		userPrev.setPrevileges(previlege);
		user.setUsersPrevileges(userPrev);
		userRepository.save(user);
	}
	

	public boolean saveUser(UsersDTO reqUser) {
		boolean saveFlag = false;
		if (null != reqUser) {
			Users user = new Users();
			user.setUsername(reqUser.getUsername());
			user.setPassword(reqUser.getPassword());
			user.setFullaccess(reqUser.isFullaccess());
			if (!reqUser.isFullaccess()) {
				Previleges previlege=previlegesRepository.getUserPrevByName(reqUser.getUserprev());
				UsersPrevileges up = new UsersPrevileges();				
				up.setPrevileges(previlege);
				up.setUsers(user);
				up.setPrevileges(previlege);
				user.setUsersPrevileges(up);
				userRepository.save(user);
				usersPrevilegesRepository.save(up);
				saveFlag=true;
			} else {				
				user.setUsersPrevileges(null);
				userRepository.save(user);
				saveFlag=true;
			}
		} else {
			saveFlag=false;
		}
			return saveFlag;
	}
	public Users getLoggedInUser(UsersDTO reqUser) {
		Users user = userRepository.getUser(reqUser.getUsername(), reqUser.getPassword());
		if(null!=user)
			return user;
		else return null;
	}
	public List<UserPrevilegesResponse> getAdminUser(UsersDTO reqUser) {		
		Users user = userRepository.getUser(reqUser.getUsername(),reqUser.getPassword());	
		//user = getLoggedInUser(reqUser);
		/*if (null != user) {
			if (user.isFullaccess()) {
				userPrevilegeResp=new ArrayList<UserPrevilegesResponse>();				
				userPrevilegesList = usersPrevilegesRepository.findAll();
				for(UsersPrevileges userPrevilege: userPrevilegesList) {					
					UserPrevilegesResponse upr=new UserPrevilegesResponse();
					generateUserPrevResponse(userPrevilege,upr,user);					
				}
			} else {
				userPrevilegeResp = new ArrayList<UserPrevilegesResponse>(1);				
				UserPrevilegesResponse upr=new UserPrevilegesResponse();
				UsersPrevileges uprev=user.getUsersPrevileges();
				generateUserPrevResponse(uprev,upr,user);				
			}
		}*/
		if (null != user) {
			if (user.isFullaccess()) {
				userPrevilegeResp=new ArrayList<UserPrevilegesResponse>();				
				userPrevilegesList = usersPrevilegesRepository.findAll();
				//usersPrevilegesRepository.findAll();
				for(UsersPrevileges userPrevilege: userPrevilegesList) {					
					UserPrevilegesResponse upr=new UserPrevilegesResponse();
					Users users=userPrevilege.getUsers();
					generateUserPrevResponse(userPrevilege,upr,users);					
				}
			}
		}
		return userPrevilegeResp;
	}	
	
	public List<UserPrevilegesResponse> getUser(String uname) {		
		Users user = userRepository.getUserByUsername(uname);
		userPrevilegeResp = new ArrayList<UserPrevilegesResponse>(1);				
		UserPrevilegesResponse upr=new UserPrevilegesResponse();
		UsersPrevileges uprev=user.getUsersPrevileges();
		generateUserPrevResponse(uprev,upr,user);
		return userPrevilegeResp;
	}	
	
//	private List<String> getPendingTrainings(UsersPrevileges up, Users user) {		
//		Set<PrevilegesToTrainings> pttList=up.getPrevileges().getPrevilegestotrainings();
//		List<String> pts=new ArrayList<String>();
//		List<String> ptList=new ArrayList<String>();
//		for(PrevilegesToTrainings ptl:pttList) {
//			pts.add(ptl.getTrainings().getTrainingname());
//		}
//		//get Trainings based on actions to trainings 
//		Previleges prev=up.getPrevileges(); // possible to simplify api calls
//		Set<PrevilegesToActions> prevToActions=prev.getPrevilegestoactions();
//		for(PrevilegesToActions prevAct:prevToActions) {
//			Actions action=prevAct.getActions();
//			ActionsToTrainings actionToTraining=action.getActionstotrainings();
//			pts.add(actionToTraining.getTrainings().getTrainingname());
//		}
//		for(String pendingTr:pts) {				
//			Trainings pendingT=trainingsRepository.getTrainingByName(pendingTr);
//			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTrainingName(pendingT);
//			Experience e=et.getExperience();
//			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
//			if(et.getMaxyears()>ue.getYears()) {
//				ptList.add(pendingTr);
//			}
//		}
//		return ptList;
//	}
	
	private void getPendingAndWaivedTrainings(UsersPrevileges up, Users user, Set<UserCompletedTrainings> completedTraining,
			UserPrevilegesResponse upr  // modified!
			) {		

		// Get privileges
		Previleges prev=up.getPrevileges(); 

		// Get Trainings based on privilege
		Set<PrevilegesToTrainings> pttList=prev.getPrevilegestotrainings(); 

		// Get Trainings based on actions associated with the privilege
		Set<ActionsToTrainings> actList = new HashSet<>();
		for(PrevilegesToActions prevAct:prev.getPrevilegestoactions()) {
			Actions action=prevAct.getActions();
			actList.add(action.getActionstotrainings());
		}
				
		// Filter out the completed trainings from both sets
		Set<Long> completedIds = new HashSet<>();
		for(UserCompletedTrainings pt : completedTraining) completedIds.add(pt.getTrainings().getTrainingid()); // Get ids of the training						
		pttList.removeIf((PrevilegesToTrainings pt) -> {
		    return (completedIds.contains(pt.getTrainings().getTrainingid()));
		});
		actList.removeIf((ActionsToTrainings act) -> {
		    return (completedIds.contains(act.getTrainings().getTrainingid()));
		});
		
		 // Temp storage for training names
		List<String> pts=new ArrayList<String>();     
		for(PrevilegesToTrainings ptl:pttList) {
			pts.add(ptl.getTrainings().getTrainingname());
		}
		for(ActionsToTrainings actl:actList) {
			pts.add(actl.getTrainings().getTrainingname());
		}
		
		// Adding into the training response object the url
		List<TrainingsResponse> tresponses = new ArrayList<>();
		for(PrevilegesToTrainings ptl:pttList) {
			TrainingsResponse tr = new TrainingsResponse();
			tr.setName(ptl.getTrainings().getTrainingname());
			tr.setUrl(ptl.getTrainings().getUrl());
			tresponses.add(tr);
		}
		for(ActionsToTrainings actl:actList) {
			TrainingsResponse tr = new TrainingsResponse();
			tr.setName(actl.getTrainings().getTrainingname());
			tr.setUrl(actl.getTrainings().getUrl());
			tresponses.add(tr);
		}

		// Based on user experience, adds to pending training list or waived trainings list
		List<String> ptList=new ArrayList<String>();   // Pending Trainngs return storage
		List<String> wtList=new ArrayList<String>();   // Waived trainings return storage
		for(String pendingTr:pts) {				
			Trainings pendingT=trainingsRepository.getTrainingByName(pendingTr);  // TODO: this needs to be by ID. If two trainngs have the same name, will be by a bug
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTrainingName(pendingT);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
			if(et.getMaxyears()>ue.getYears()) {
				ptList.add(pendingTr);
			} else {
				wtList.add(pendingTr);
			}
		}
		

		List<TrainingsResponse> ptList2 = new ArrayList<>();
		for(TrainingsResponse pendingTrainingsResponse:tresponses) {	
			String pendingTr = pendingTrainingsResponse.getName();
			Trainings pendingT=trainingsRepository.getTrainingByName(pendingTr);  // TODO: this needs to be by ID. If two trainngs have the same name, will be by a bug
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTrainingName(pendingT);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
			if(et.getMaxyears()>ue.getYears()) {
				ptList2.add(pendingTrainingsResponse);
			} else {
//				wtList.add(pendingTr);
			}
		}

		
		// Sort
		Collections.sort(ptList);
		Collections.sort(wtList);
		
		Collections.sort(ptList2, new Comparator<TrainingsResponse>() {
			@Override
			public int compare (TrainingsResponse o1, TrainingsResponse o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		upr.setPendingtrainings2(ptList2);


		String[] ptarray;
		if(ptList.size()<=0) {
			ptarray=new String[1];
		}
		 ptarray= new String[ptList.size()];
		 ptList.toArray(ptarray);
		upr.setPendingtrainings(ptarray);

		
		String[] wtarray;
		if(wtList.size()<=0) {
			wtarray=new String[1];
		}
		wtarray= new String[wtList.size()];
		 wtList.toArray(wtarray);
		upr.setWaivedtrainings(wtarray);

	}
	
	private void generateUserPrevResponse(UsersPrevileges userPrevilege, UserPrevilegesResponse upr,Users user) {

		// something
		Users users=userPrevilege.getUsers();
		upr.setUserid(users.getUserid());//1
		upr.setUsername(users.getUsername());//2
		
		UsersPrevileges up=users.getUsersPrevileges();  // same as userPrivileges?
		upr.setPrevileges(up.getPrevileges().getPrevname());//4
		Long userPrevId=up.getPrevileges().getPrevid(); // redundant call
		
		PrevilegesToActions previlegeToAction=previlegesToActionsRepository.findOne(userPrevId);
		Set<PrevilegesToActions> prevToActionsList=previlegeToAction.getPrevileges().getPrevilegestoactions();
		String actionNames=null;
		for(PrevilegesToActions prevAction:prevToActionsList) {
			if(null==actionNames) actionNames=prevAction.getActions().getActionname();
			else actionNames=actionNames+","+prevAction.getActions().getActionname();    
		}
		upr.setActions(actionNames);//5
		
		//get completed Trainings
		Set<UserCompletedTrainings> userCompletedTrainingsList=user.getUsercompletedtrainings();
		List<String> cts=new ArrayList<String>();
		for(UserCompletedTrainings uct:userCompletedTrainingsList) {
			cts.add(uct.getTrainings().getTrainingname());
		}
		Collections.sort(cts);
		String[] ctarray = new String[cts.size()];
		cts.toArray(ctarray );
		upr.setCompletedtrainings(ctarray);
		
		/*
		//get Pending Trainings
		Set<PrevilegesToTrainings> pttList=up.getPrevileges().getPrevilegestotrainings();
		List<String> pts=new ArrayList<String>();
		for(PrevilegesToTrainings ptl:pttList) {
			pts.add(ptl.getTrainings().getTrainingname());
		}
		//get Trainings based on actions to trainings 
		Previleges prev=up.getPrevileges();
		Set<PrevilegesToActions> prevToActions=prev.getPrevilegestoactions();
		for(PrevilegesToActions prevAct:prevToActions) {
			Actions action=prevAct.getActions();
			ActionsToTrainings actionToTraining=action.getActionstotrainings();
			pts.add(actionToTraining.getTrainings().getTrainingname());
		}*/		
		
		//get Experience
		List<String> jdList=new ArrayList<String>();
		List<Long> yearsList=new ArrayList<Long>();
		Set<UserExperience> userExpList=users.getUserExperience();		
		for(UserExperience uex:userExpList) {
			jdList.add(uex.getExperience().getExpname());
			yearsList.add(uex.getYears());
			//getPending Trainings			
		}
		//List<String> ptList=new ArrayList<String>();
//		List<String> ptList=getPendingTrainings(up,user);
	    getPendingAndWaivedTrainings(up,user,userCompletedTrainingsList, upr);
		/*for(String pendingTr:pendingTrainsList) {				
			Trainings pendingT=trainingsRepository.getTrainingByName(pendingTr);
			ExperienceToTrainings et=experienceToTrainingsRepository.getExperienceByTrainingName(pendingT);
			Experience e=et.getExperience();
			UserExperience ue=userExperienceRepository.getExperienceByExpId(e,user);
			if(et.getMaxyears()>ue.getYears()) {
				ptList.add(pendingTr);
			}
		}*/
//		String[] ptarray;
//		if(ptList.size()<=0) {
//			ptarray=new String[1];
//		}
//		 ptarray= new String[ptList.size()];
//		 ptList.toArray(ptarray);
//		upr.setPendingtrainings(ptarray);
		
		String[] exp = new String[userExpList.size()];
		jdList.toArray(exp);
		upr.setJd(exp);
		
		Long[] yearsExp = new Long[userExpList.size()];
		yearsList.toArray(yearsExp);
		upr.setYears(yearsExp);
		userPrevilegeResp.add(upr);
	}
}