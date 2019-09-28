package com.qa.util;

import org.json.JSONObject;

public class TestUtil {
	
	public void readJson(JSONObject jsonObj) {
		
		System.out.println(jsonObj.get("per_page"));
	}
	
	

}
