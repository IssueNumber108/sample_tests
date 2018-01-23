package com.adidas.automation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;

public class JSONParser {

	// http://localhost:8080/RESTfulExample/json/product/get
	public static void main(String[] args) throws JSONException {

	  try {

		URL url = new URL("https://www.adidas.fi/api/pages/landing?path=/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String output;
		System.out.println("Output from Server .... \n");
		StringBuffer sb = new StringBuffer();
		
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			sb.append(output);
		}
		
		writeDataToTXT(sb);
		conn.disconnect();
		
		
		//Parsing of JSON and checking the time of page loading
		RestAPICall restAPICall = new RestAPICall();
		try {
			restAPICall.callRestfulAPI();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }

	}
	
	
	
	public static void writeDataToTXT(StringBuffer sb)
	{
		String FILENAME = "/Users/atul/Downloads/test_api.txt";
		
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = sb.toString();

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
}