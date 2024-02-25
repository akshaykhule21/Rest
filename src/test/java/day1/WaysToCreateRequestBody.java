package day1;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.JsonToken;
import com.google.gson.JsonObject;


import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.testng.annotations.Test;

/**
 * Diff ways to create post request body
 * 1) Post request body using hashmap
 * 2) Post request body creation using Org.JSON
 * 3) Post request body creation using POJO class
 * 4) post request using external json file data
 */

public class WaysToCreateRequestBody {
SoftAssert mark=new SoftAssert();
	
//	@Test(priority=1)
	void testPostUsingHashMap() {
		
		HashMap data=new HashMap();
		data.put("name", "Akshay");
		data.put("location", "India");
		data.put("phone", "1234567");
		
		// if you have multple values for one key 
		// then first store them into array and then pass that array into hashmap
		String coursearr[]= {"C","C++"};	
		data.put("courses", coursearr);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("Akshay"))
			.body("location",equalTo("India"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
//	@Test
	void testPostUsingJsonLibrary() {
		
		JSONObject data=new JSONObject();		//Create a object
		data.put("name", "Akshay");
		data.put("location", "India");
		data.put("phone", "1234567");
		
		String coursearr[]= {"C","C++"};	
		data.put("courses", coursearr);   
	
		
		given()
			.contentType("application/json")
			.body(data.toString())				// need to convert into string if we are using JSONObject to pass the data
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("Akshay"))
			.body("location",equalTo("India"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
	
//	@Test
	void testPostUsingPOJOCLASS() {
		
		POJO_PostRequest data=new POJO_PostRequest();
		data.setName("Akshay");
		data.setLocation("India");
		data.setPhone("1234567");
		String courseArr[]= {"C","C++"};
		data.setcourses(courseArr);
	
		
		given()
			.contentType("application/json")
			.body(data)				
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("Akshay"))
			.body("location",equalTo("India"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
//	4) post request using external json file data
	@Test
	void testPostUsingEXTJSONFile() throws FileNotFoundException {
		
//		File f =new File("body.json");
//		FileReader reader=new FileReader(f);
//		JSONTokener jt=new JSONTokener(reader);
//		JSONObject data =new JSONObject(jt);
		
		JSONObject data =new JSONObject(new JSONTokener(new FileReader(new File("body.json"))));
		
	
		given()
			.contentType("application/json")
			.body(data.toString())				
		.when()
			.post("https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("Akshay"))
			.body("location",equalTo("India"))
			.body("phone", equalTo("1234567"))
			.body("courses[0]", equalTo("C"))
			.body("courses[1]", equalTo("C++"))
			.header("Content-Type", "application/json; charset=utf-8")
			.log().all();
	}
}