package com.epam.mentoring.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class JsonHelper

{
	public String name;
	public String value;
	public static final String BASE_URL = PropertyReader.getBaseUrl();

	public JsonHelper(String name, String value) {
		this.name = "text";
		this.value = value;
	}

	public static JSONObject getJsonTemplate(String message) {
		JSONObject template = new JSONObject();
		JSONArray dataArray = new JSONArray();
		JSONObject dataObject = new JSONObject();
		JSONObject dataValues = new JSONObject();
		dataValues.put("name", "text");
		dataValues.put("value", message);
		dataArray.put(dataValues);
		dataObject.put("data", dataArray);
		template.put("template", dataObject);
		return template;
	}

	public static String sendGetRequest(String str) throws IOException {
		StringBuffer response = null;
		List<String> list = new ArrayList<String>();
	
		try {
			URL url = new URL(BASE_URL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			int responseCode = connection.getResponseCode();
			list.add(Integer.toString(responseCode));
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			//list.add(response.toString());
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return response.toString();
	}

	public static void sendPostRequest(String str) throws IOException {
		try {
			System.out.println(BASE_URL);
			URL url = new URL(BASE_URL);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			String urlParameters = getJsonTemplate(str).toString();

			// Send post request
			connection.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/*public static void main(String[] args) throws IOException {
		String str = "NewTextMessage";
		System.out.println(getJsonTemplate(str).toString());
		System.out.println("=================");
		sendPostRequest(str);
		sendGetRequest(str);
	}*/
}

// {"template" : {
// "data" : [
// {"name" : "text", "value" : "TextSample"}
// ]
// }
// }