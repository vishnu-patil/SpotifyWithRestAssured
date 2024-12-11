package SpotifySteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.Assert;

import com.spotify.pojo.Playlist;
import AuthManager.GetToken;
import Utility.DateAndTime;
import Utility.PropertyReader;
import Utility.SpecBuilder;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PlaylistSteps {

	RequestSpecification requestSpecification;
	Response response;
	Playlist reqPlaylist = new Playlist();
	static String playlistID;
	
	@Given("CREATE playlist api payload")
	public void create_playlist_api_payload() throws IOException {
		
		reqPlaylist.setName(PropertyReader.readExcelData("apiData", 1, 0) +" "+DateAndTime.getDateTime());
		reqPlaylist.setDescription(PropertyReader.readExcelData("apiData", 1, 1));
		reqPlaylist.setPublic(false);
		
		requestSpecification = given(SpecBuilder.reqSpec(GetToken.fetchToken()))
								.body(reqPlaylist);
	}

	@When("user calls with POST http request")
	public void user_calls_with_post_http_request() {
		response = requestSpecification.when()
					.post("users/31jqxv2gjqanizmcm4weemhitgpu/playlists");
	}

	@Then("API call executed with status code {int}")
	public void api_call_executed_with_status_code(Integer expStatusCode) {
		Playlist resPlaylist = response.as(Playlist.class);
		
		playlistID = resPlaylist.getId();
	
		Assert.assertEquals(response.statusCode(), expStatusCode,"Status Code is Mismatched...");
		
	}

	@Given("GET playlist api payload")
	public void get_playlist_api_payload() {
		requestSpecification = given(SpecBuilder.reqSpec(GetToken.fetchToken()))
				.pathParam("playlistId", playlistID);
	}

	@When("user calls with GET http request")
	public void user_calls_with_get_http_request() {
		response = requestSpecification.when()
				.get("playlists/{playlistId}");
	}

	@Given("UPDATE playlist api payload")
	public void update_playlist_api_payload() throws IOException {
		reqPlaylist.setName("Updated " + PropertyReader.readExcelData("apiData", 1, 0) +" "+ DateAndTime.getDateTime());
		reqPlaylist.setDescription(PropertyReader.readExcelData("apiData", 1, 1));
		reqPlaylist.setPublic(false);
		
		requestSpecification = given(SpecBuilder.reqSpec(GetToken.fetchToken()))
								.body(reqPlaylist);
	}

	@When("user calls with PUT http request")
	public void user_calls_with_put_http_request() {
		response = requestSpecification.when()
				.put("playlists/"+playlistID);
	}
	
	@Then("API call execute with status code {int}")
	public void api_call_execute_with_status_code(Integer expStatusCode) {
	
		Assert.assertEquals(response.statusCode(), expStatusCode,"Status Code is Mismatched...");
		
	}
}
