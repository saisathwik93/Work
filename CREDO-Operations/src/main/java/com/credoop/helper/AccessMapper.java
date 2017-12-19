package com.credoop.helper;

import java.util.HashMap;
import java.util.Map;

public class AccessMapper {

	// TODO: we should move this to DB and issue different app_id for different
	// environment.
	private static Map<String, String> WHITELISTED_APPLICATIONS = new HashMap<>();
	static {
		WHITELISTED_APPLICATIONS.put("6ef8a0ab-ae1a-11e7-8bc4-0050568c4695", "TEST-APP");
	}
public static boolean checkPermission(final String appKey){
		return WHITELISTED_APPLICATIONS.get(appKey) != null ? true : false;
	}
}