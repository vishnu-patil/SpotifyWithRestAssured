package SpotifySteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

import AuthManager.GetToken;
import Utility.SpecBuilder;
import io.cucumber.datatable.DataTable;

public class SearchStep {

	RequestSpecification requestSpecification;
	Response response;
	
	@Given("GET a search track payload")
	public void get_a_search_track_payload(DataTable dataTable) {
	    
		List<Map<String, String>> map = dataTable.asMaps();
		String songName = map.get(0).get("songname");
		String type = map.get(0).get("type");
		
		requestSpecification = given(SpecBuilder.reqSpec(GetToken.fetchToken()))
								.queryParams("q", songName, "type", type);
	}

	@When("user calls with Get request")
	public void user_calls_with_get_request() {
		response = requestSpecification.when()
					.get("search");
	}

	@Then("API executes with status code {int}")
	public void api_executes_with_status_code(Integer int1) {
	    response.then()
	    .spec(SpecBuilder.resSpec(ContentType.JSON, int1))
	    .assertThat()
	    .statusLine("HTTP/1.1 200 OK");
	    
	}
}
