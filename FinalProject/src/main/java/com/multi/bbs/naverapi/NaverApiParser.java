package com.multi.bbs.naverapi;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class NaverApiParser {

	
	public static String parseFirstImageUrl(String json) throws org.json.simple.parser.ParseException {
	    JSONParser jsonParser = new JSONParser();
	    JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
	    JSONArray items = (JSONArray) jsonObject.get("items");
	    // items가 null이 아니며, 크기가 0보다 클 경우에만
	    if (items != null && items.size() > 0) {
	        JSONObject firstItem = (JSONObject) items.get(0);
	        return (String) firstItem.get("link");
	    }
	    return null;
	}

	
	
}
