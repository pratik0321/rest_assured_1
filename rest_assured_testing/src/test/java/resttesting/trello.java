package resttesting;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class trello {
	
		//url will be common 
	public static String baseurl = "https://api.trello.com";
	public static String id;
	
	@Test(priority = 0)
	public void createBoard()
	{
		
		//i want to fetch the base url so that i can add other thing to that base url
		//This is the first step to provide base url to the request
		RestAssured.baseURI = baseurl;
		
		//in rest assured we have three keyword
		//given : pre-condition includes like parameter,header, authorization,
		//when : condition i am testing like resource //iwe have to provide the name //that is called resource /1/board/ and the method name
		//then : post condition : is something like response check the response
		Response resposne = given().queryParam("name", "Pratik2's Moolya board")
				.queryParam("key", "c8f51673bd97970b77243209b9c601fb")
				.queryParam("token", "73ea153392a5aec8eeb46e2b0c3d165d994cb1fbee7293f0d2b8fddce84aea64")
				.header("Content-Type","application/json")
		
		.when()
		.post("/1/boards/")
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
		//This is to fetch the response as a string
		String jsonresponse = resposne.asString();
		//i want to convert the response in to json format
		//why do i use jsonpath to convert the string in to a json format
		JsonPath js = new JsonPath(jsonresponse);
		//nw i have to fetch the id
		id = js.get("id");
		
	}
	
	//if i make any @Test method enabled =false/ that method will not execute
	
	@Test(priority = 1)
	public void getBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
	Response response =	given()
			.queryParam("key", "c8f51673bd97970b77243209b9c601fb")
			.queryParam("token", "73ea153392a5aec8eeb46e2b0c3d165d994cb1fbee7293f0d2b8fddce84aea64")
			.header("Content-Type","application/json")
		
		.when()
		.get("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
		String jsonresponse = response.asString();
		
		System.out.println(jsonresponse);
	}
	
	@Test(priority = 2)
	public void DeleteBoard()
	{
		RestAssured.baseURI = baseurl;
		
		
	Response response =	given()
			.queryParam("key", "c8f51673bd97970b77243209b9c601fb")
			.queryParam("token", "73ea153392a5aec8eeb46e2b0c3d165d994cb1fbee7293f0d2b8fddce84aea64")
			.header("Content-Type","application/json")
		
		.when()
		.delete("/1/boards/"+id)
		
		.then()
		.assertThat().statusCode(200).contentType(ContentType.JSON)
		.extract().response();	
		String jsonresponse = response.asString();
		
		System.out.println(jsonresponse);
	}
}