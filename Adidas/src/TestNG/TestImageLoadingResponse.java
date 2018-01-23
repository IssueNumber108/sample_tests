package TestNG;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestImageLoadingResponse {
	 private static SoftAssert p_assert = new SoftAssert();
  @Test
  public void ImageRespponseCode0001() throws InterruptedException, ClientProtocolException, URISyntaxException, IOException {
	 
  //File jsonInputFile = new File("/Users/atul/Downloads/test_api.txt");
	File jsonInputFile = new File("JSON.txt");
	InputStream is;

	try {
		is = new FileInputStream(jsonInputFile);
		// Create JsonReader from Json.
		JsonReader reader = Json.createReader(is);
		// Get the JsonObject structure from JsonReader.
		JsonObject empObj = reader.readObject();
		reader.close();
      Integer result = 1;
		JsonArray jObj = empObj.getJsonArray("component_presentations"); /// ---3----------
		for (int i = 0; i < jObj.size(); i++) {
			// System.out.println("------Iteration----" + i);
			{
				JsonArray arr = jObj.getJsonObject(i).getJsonObject("component").getJsonObject("content_fields")
						.getJsonArray("items"); /// -----3------

				String url = "";

				for (int j = 0; j < arr.size(); j++) {
					JsonObject jsonOBJ = arr.getJsonObject(j).getJsonObject("background_media");
					if (null != jsonOBJ)
						jsonOBJ = jsonOBJ.getJsonObject("desktop_image");
					if (null != jsonOBJ) {
						url = jsonOBJ.getString("url");
						result=executeURL(url); p_assert.assertEquals(result,1);
						System.out.println(jsonOBJ.getString("url"));
					}

					jsonOBJ = arr.getJsonObject(j).getJsonObject("background_media");
					if (null != jsonOBJ)
						jsonOBJ = jsonOBJ.getJsonObject("tablet_image");
					if (null != jsonOBJ) {
						url = jsonOBJ.getString("url");
						result=executeURL(url); p_assert.assertEquals(result,1);
						System.out.println(jsonOBJ.getString("url"));
					}

					jsonOBJ = arr.getJsonObject(j).getJsonObject("background_media");
					if (null != jsonOBJ)
						jsonOBJ = jsonOBJ.getJsonObject("mobile_image");
					if (null != jsonOBJ) {
						url = jsonOBJ.getString("url");
						result=executeURL(url); p_assert.assertEquals(result,1);
						System.out.println(jsonOBJ.getString("url"));
					}

					jsonOBJ = arr.getJsonObject(j).getJsonObject("media_items");
					if (null != jsonOBJ)
						jsonOBJ = jsonOBJ.getJsonObject("desktop_image");
					if (null != jsonOBJ) {
						url = jsonOBJ.getString("url"); 
						result=executeURL(url);  p_assert.assertEquals(result,1);
						System.out.println(jsonOBJ.getString("url"));
					}

					jsonOBJ = arr.getJsonObject(j).getJsonObject("media_items");
					if (null != jsonOBJ)
						jsonOBJ = jsonOBJ.getJsonObject("tablet_image");
					if (null != jsonOBJ) {
						url = jsonOBJ.getString("url");
						result=executeURL(url);  p_assert.assertEquals(result,1);
						System.out.println(jsonOBJ.getString("url"));
					}

					jsonOBJ = arr.getJsonObject(j).getJsonObject("media_items");
					if (null != jsonOBJ)
						jsonOBJ = jsonOBJ.getJsonObject("mobile_image");
					if (null != jsonOBJ) {
						url = jsonOBJ.getString("url");
						result=executeURL(url);  p_assert.assertEquals(result,1);
						System.out.println(jsonOBJ.getString("url"));
					}

				}
			}
			i++;
		}

	} catch (FileNotFoundException e) {
	
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("\n ------ END of Program ------\n");
	p_assert.assertAll();
}


public Integer executeURL(String url) throws InterruptedException, URISyntaxException, ClientProtocolException, IOException {
	
	Integer status = 5;
	// ============FOR CHROME-================
	
	 URI uri = new URI(url);
     HttpGet httpget = new HttpGet(uri);

     //HttpClient httpclient = new DefaultHttpClient();
     
     HttpClient httpClient = HttpClients.custom()
             .setDefaultRequestConfig(RequestConfig.custom()
                 .setCookieSpec(CookieSpecs.STANDARD).build())
             .build();

     HttpResponse response = httpClient.execute(httpget);
     // check response headers.
     String reasonPhrase = response.getStatusLine().getReasonPhrase();
     int statusCode = response.getStatusLine().getStatusCode();

     System.out.println(String.format("statusCode: %d", statusCode));
     System.out.println(String.format("reasonPhrase: %s", reasonPhrase));

	if(statusCode<200 && statusCode>299)
		{System.out.println("\n FAIL \n");status=0; }
		else{System.out.println("\n PASS \n"); status=1;}
		
	Thread.sleep(2000);
	return status;
	
	
	//=============For FIREFOX===========
	
/*	WebDriver driver1 = new FirefoxDriver();
	// visit a page
	driver1.get(url);

	// get the page load time
 	Long loadingInterval = (Long) ((JavascriptExecutor) driver1)
			.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");

  	System.out.println("Execution time in FIREFOX: "+loadingInterval);

   driver1.quit();
	Thread.sleep(2000);  */
	
	
}
}
