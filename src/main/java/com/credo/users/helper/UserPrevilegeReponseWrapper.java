package com.credo.users.helper;

import java.util.ArrayList;
import java.util.List;

import com.credo.users.model.UserPrevilegesResponse;
import com.credo.users.model.UsersPrevileges;

public class UserPrevilegeReponseWrapper {
	public List<UserPrevilegesResponse> convertToUserPrevilegesResponse(List<UsersPrevileges> userPrevilegeList){
		List<UserPrevilegesResponse> userPrevilegeResponseList=new ArrayList<UserPrevilegesResponse>(userPrevilegeList.size());
		/*for(UsersPrevileges userPrevilege:userPrevilegeList) {
			UserRoles ur=userPrevilege.getUserroles();
			UserTrainings ut=ur.getUsertrainings();
			UserPrevilegesResponse userPrevResp=new UserPrevilegesResponse();
		userPrevResp.setId(userPrevilege.getUserprevid());
			userPrevResp.setUsername(userPrevilege.getUsername());
			userPrevResp.setPrevileges(ur.getPrevilege());
			userPrevResp.setUserid(userPrevilege.getUserprevid());
			userPrevResp.setActions(ur.getActions());
			String completedTrainings=ut.getCompletedtrainings();
			String pendingTrainings=ut.getPendingtrainings();
			String jdExp=userPrevilege.getJdyearsexp();
			if(null!=completedTrainings) {
				String ct[]=completedTrainings.split(",");
				userPrevResp.setCompletedtrainings(ct);
			}
			if(null!=pendingTrainings) {
				String pt[]=pendingTrainings.split(",");
				userPrevResp.setPendingtrainings(pt);
			}			
			if(null!=jdExp) {			
				String jdYExp[]=jdExp.split(",");
				userPrevResp.setJdyearsexp(jdYExp);
			}
			userPrevilegeResponseList.add(userPrevResp);
		}*/
		return userPrevilegeResponseList;
	}
}
