package Utility;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

	@Step
	public static RequestSpecification reqSpec(String token)
	{
		 return new RequestSpecBuilder()
		.setBaseUri("https://api.spotify.com")
		.setBasePath("v1")
		.addHeader("Content-Type", "application/json")
		.addHeader("Authorization", "Bearer " + token)
		.log(LogDetail.ALL)
		.addFilter(new AllureRestAssured())
		.build();
	}
	
	@Step
	public static ResponseSpecification resSpec(ContentType contentType,int statucCode)
	{
		 return new ResponseSpecBuilder()
		.expectContentType(contentType)
		.expectStatusCode(statucCode)
		.log(LogDetail.ALL)
		.build();
	}
}
