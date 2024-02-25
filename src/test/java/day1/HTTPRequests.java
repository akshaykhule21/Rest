package day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

/*
given()
 	content type, set cookies, add auth, add param, set headers info etc....

when()
	get, post, put, delete

then()
	validate status code, extract response, extract headers cookies & response body....

*/
// https://reqres.in/api/users/2
public class HTTPRequests {
	int id;
	
	//1. Get user
	@Test(priority=1)
	void getUsers() {
		given()
		
		.when()
			.get("https://reqres.in/api/users?page=2")
			
		.then()
			.statusCode(200)
			.body("page",equalTo(2))
			.log().all();	 	
	}	
	
	//2. Post user
	@Test(priority=2)
	void createUser() {
		
		
		HashMap data=new HashMap();
		data.put("name", "akshay");
		data.put("job", "QA");
		
		id=given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.post("https://reqres.in/api/users")
			.jsonPath().getInt("id");
		
//		.then()
//			.statusCode(201)
//			.log().all();
		System.out.println(id);
	}
	
	//3. update user
	@Test(priority=3,dependsOnMethods = {"createUser()"})
	void updateUser() {
		
		HashMap data=new HashMap();
		data.put("name", "abc");
		data.put("job", "Dev");
		
		given()
			.contentType("application/json")
			.body(data)
		
		.when()
			.put("https://reqres.in/api/users/"+id)

		
		.then()
			.statusCode(200)
			.log().all();
		System.out.println(id);
	}
	
	@Test(priority=4,dependsOnMethods = {"updateUser()"})
	void deleteUser() {
		given()
		
		.when()
			.delete("https://reqres.in/api/users/"+id)
		
		.then()
			.statusCode(204)
			.log().all();
	}
}