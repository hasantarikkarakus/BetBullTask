package com.betbull.step_definitions;

import com.betbull.utilities.ConfigurationReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class RegistrationStepDefs {

    Response response;
    RequestSpecification requestSpecification;

    @Given("Accept type is applicationJson")
    public void accept_type_is_applicationJson() {

        requestSpecification = given().accept(ContentType.JSON);
    }

    @When("User send POST request to the endpoint with email and password")
    public void user_send_POST_request_to_the_endpoint_with_email_and_password() {

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email", ConfigurationReader.get("email"));
        requestMap.put("password", ConfigurationReader.get("password"));

        response = requestSpecification.and().contentType(ContentType.JSON)
                   .and().body(requestMap).when().post(ConfigurationReader.get("api_url")+"/register");

    }

    @Then("Status code should be {int}")
    public void status_code_should_be(int statusCode) {

        assertEquals(statusCode,response.statusCode());

    }

    @Then("Response should include token")
    public void response_should_include_token() {

        String token = response.path("token");
        System.out.println("token = " + token);
    }

    @When("User send POST request to the endpoint with just email")
    public void user_send_POST_request_to_the_endpoint_with_just_email() {

        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email", ConfigurationReader.get("email"));

        response = requestSpecification.and().contentType(ContentType.JSON)
                .and().body(requestMap).when().post(ConfigurationReader.get("api_url")+"/register");
    }

    @Then("Response should include {string} message")
    public void response_should_include_message(String expectedMessage) {

        String actualMessage = response.path("error");
        System.out.println("actualMessage = " + actualMessage);

        assertEquals(expectedMessage,actualMessage);
    }

    @When("I send GET request to the endpoint")
    public void i_send_GET_request_to_the_endpoint() {

        response = requestSpecification.and().contentType(ContentType.JSON)
                .when().get(ConfigurationReader.get("api_url")+"/users");
    }

    @Then("Response should include following names")
    public void response_should_include_following_names(List<String> expectedNames) {

        List<String> actualNames = response.path("data.first_name");

        for (int i=0 ; i<expectedNames.size() ; i++){
            assertEquals(expectedNames.get(i),actualNames.get(i));
            System.out.println(actualNames.get(i));
        }

    }

}
