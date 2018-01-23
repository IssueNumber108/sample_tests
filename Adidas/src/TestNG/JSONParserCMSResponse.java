package TestNG;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import org.testng.asserts.SoftAssert;

import org.testng.annotations.Test;

import com.adidas.automation.RestAPICall;

public class JSONParserCMSResponse {

	@Test
	public void executeJSONFromRESTAPI() throws InterruptedException {

		try {

			long start = System.currentTimeMillis();
			URL url = new URL("https://www.adidas.fi/api/pages/landing?path=/");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
           
			long finish = System.currentTimeMillis();
			long totalTime = finish - start; 
			System.out.println("Total Time for API Response - "+totalTime); 
			/* We can also use -get the page load time in miliseconds - as used in this test case 
			for calculating loading time of Image -- for more accurate response time */
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			StringBuffer sb = new StringBuffer();

			while ((output = br.readLine()) != null) {
				System.out.println(output);
				sb.append(output);
			}

			writeDataToTXT(sb);
			conn.disconnect();

			// Parsing of JSON and checking the time of page loading
			RestAPICall restAPICall = new RestAPICall();
			restAPICall.callRestfulAPI();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static void writeDataToTXT(StringBuffer sb) {
	//	String FILENAME = "/Users/atul/Downloads/test_api.txt";
		String FILENAME = "JSON.txt";
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
