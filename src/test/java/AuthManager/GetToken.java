package AuthManager;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import java.util.LinkedHashMap;
import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetToken {

	@Test
	public static String fetchToken()
	{
		LinkedHashMap<String, String> param = new LinkedHashMap<String, String>();
		param.put("grant_type", "refresh_token");
		param.put("refresh_token", "AQBwFDmVtW0E2faIajTZGTwWt2MZ3uAjmlsarLQ4j1RCX0ZVcfOI4kmxmOPpTWDtKlvcWFi8bOobdNcNuqL2L-G6ZcQv5yzkdac1qpq9dzU3KyJEDMp1IS0eZNBL5wmf3Bo");
		param.put("client_id", "b21969dcc83d457dbec9c23a2b9e9fe7");
		param.put("client_secret", "191f92e4dc984cfba343bf172bc08780");
		
		baseURI = "https://accounts.spotify.com/";
		
		Response response = given()
								.header("Content-Type","application/x-www-form-urlencoded")
								.formParams(param)
							.when()
								.post("api/token")
							.then()
								.extract()
								.response();
		
		JsonPath jsonpath = response.jsonPath();
		String token = jsonpath.getString("access_token");
		if(response.statusCode() != 200)
		{
			throw new RuntimeException("Failed to Generate Token...");
		}
		
		return token;
		
	}
}
