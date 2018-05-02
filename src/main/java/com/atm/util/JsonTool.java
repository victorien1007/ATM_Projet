package com.atm.util;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonTool {

	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static String Obj2Json(Object obj)
	{
		try {
			 return mapper.writeValueAsString(obj);
		} catch (JsonGenerationException e) {
		} catch (JsonMappingException e) {
		} catch (IOException e) {
		}
		
		return null;
	}
	
	public static Object Json2Obj(String json) 
	{
        try {
			User zhang = mapper.readValue(json, User.class);
			return zhang;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
	}
	
	 public static Map json2Map(String json) {  
	        try {  
	            return (Map)mapper.readValue(json, Map.class);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }  
	 
	   public static <T> List<T> json2List(String json) {  
	        try {  
	              
	            return (List<T>)mapper.readValue(json, List.class);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        return null;  
	    }
	   
	   
	 
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
		
		//String json = "[{\"name\":\"张三\",\"age\":23,\"address\":\"深圳市\"},{\"name\":\"张四\",\"age\":232,\"address\":\"深圳\"}]";
		
		String json = "{\"name\":\"张三\",\"age\":23,\"address\":\"深圳市\"}";
		
		User user = (User)JsonTool.Json2Obj(json);
		
		System.out.println(user.getAge());
		System.out.println(user.getName());
		System.out.println(user.getNote());
		System.out.println(user.getAddress());
		
//		Map<String,Object> map = JsonTool.json2Map(json);
//		System.out.println(map.get("age"));
		
		//List<Map<String,Object>> lists = JsonTool.json2List(json);
		
		//System.out.println(lists.get(0));
		
		//System.out.println(lists.get(1).get("123"));
		
		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		System.out.println(UUID.randomUUID().toString());
		
	}
	
	
}

class User{
	String name;
	int age;
	String address;
	String note;
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}