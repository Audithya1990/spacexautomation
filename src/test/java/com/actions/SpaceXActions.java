package com.actions;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.automation.frameworkUtils.FrameworkException;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SpaceXActions {

	public static Response response = null;
	public static JsonPath jsonPathValidator = null;

	public static void getLatestSpaceDetails() {
		verifyId();
		getShipDetails();
		getAllDetails();
	}

	public static void restConnection(String url) {
		try {
			RestAssured.baseURI = url;
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.contentType("application/json\r\n");
			response = httpRequest.request(Method.GET, "/latest");
			System.out.println(response.getBody().asString());
			int status = response.getStatusCode();
			if (status == 200) {
				Assert.assertTrue(true, "Response status is " + status);
				System.out.println("Response Status is :" + status);
			} else {
				Assert.assertFalse(false, "Response status is " + status);
				System.out.println("Response Status is :" + status);
			}

		} catch (Exception exception) {
			new FrameworkException(exception);
		}
	}

	public static void verifyId() {
		try {
			System.out.println("First scenario is : Get Latest Space X ID");
			jsonPathValidator = response.jsonPath();
			String id = jsonPathValidator.get("id");
			System.out.println("Latest spaceX ID is" + id);

		} catch (Exception exception) {
			new FrameworkException(exception);
		}
	}

	public static void getShipDetails() {
		try {
			System.out.println("Second scenario is : Get ship Details");
			jsonPathValidator = response.jsonPath();
			List<String> ships = jsonPathValidator.getList("ships");
			System.out.println(ships);
			for (int i = 0; i < ships.size(); i++) {
				System.out.println("Ship id is :" + ships.get(i).toString());
			}

		} catch (Exception exception) {
			new FrameworkException(exception);
		}
	}

	public static void getAllDetails() {
		try {
			System.out.println("Third scenario is : Get Core Details");
			JSONObject obj = new JSONObject(response.asString());
			JSONArray arr = obj.getJSONArray("cores");
			System.out.println(arr);
			for (int i = 0; i < arr.length(); i++) {
				System.out.println("Core is :" + arr.getJSONObject(i).getString("core"));
				System.out.println("flight number is :" + arr.getJSONObject(i).getInt("flight"));
				System.out.println("gridfins flag is :" + arr.getJSONObject(i).getBoolean("gridfins"));
				System.out.println("legs flag is :" + arr.getJSONObject(i).getBoolean("legs"));
				System.out.println("reused flag is :" + arr.getJSONObject(i).getBoolean("reused"));
				System.out.println("landing attempt flag is :" + arr.getJSONObject(i).getBoolean("landing_attempt"));
				System.out.println("landing success flag is :" + arr.getJSONObject(i).getBoolean("landing_success"));
				System.out.println("landing type is :" + arr.getJSONObject(i).getString("landing_type"));
				System.out.println("landpad id is :" + arr.getJSONObject(i).getString("landpad"));
			}
		} catch (Exception exception) {
			new FrameworkException(exception);
		}

	}
}
