package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataController {
	
//	public List<Summary>
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user1", "user1");
		map.put("user2", "user1");
		map.put("user1", "user1");
		System.out.println(map);
	}
	
	
	
}
