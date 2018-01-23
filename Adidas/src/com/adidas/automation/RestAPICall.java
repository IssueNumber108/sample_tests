package com.adidas.automation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.testng.asserts.SoftAssert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class RestAPICall {
	public static SoftAssert p_assert = new SoftAssert();
	public void callRestfulAPI() throws InterruptedException {
		
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

						// --------------AnalytticsValidation--------
					/*	jsonOBJ = arr.getJsonObject(j).getJsonObject("supporting_fields");
						if (null != jsonOBJ)
							jsonOBJ = jsonOBJ.getJsonObject("supporting_fields");
						if (null != jsonOBJ) {
							jsonOBJ = jsonOBJ.getJsonObject("standard_metadata");
							System.out.println(jsonOBJ);

							if (null != jsonOBJ) {
								if (jsonOBJ.containsKey("analytics_name")) {
									url = jsonOBJ.getString("analytics_name");
									System.out.println("\n");
									System.out.println("Analytics Name displayed: " + url);
									System.out.println("\n");
								}
							}
						}*/
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

	public Integer executeURL(String url) throws InterruptedException {
		
		Integer status = 5;
		// ============FOR CHROME-================
		
		System.setProperty("webdriver.chrome.driver","/Users/atul/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
		// long start = System.currentTimeMillis();  -- We can use this also for Duration calculation
		// visit a page
		driver.get(url);

		// get the page load time in miliseconds (time is evaluated in miliseconds since the UNIX epoch)
		Long loadingInterval = (Long) ((JavascriptExecutor) driver)
				.executeScript("return performance.timing.loadEventEnd - performance.timing.navigationStart;");

		System.out.println("Execution time in CHROME: "+loadingInterval);
		
		/*long finish = System.currentTimeMillis();
		long totalTime = finish - start; 
		System.out.println("Total Time for page load - "+totalTime); */
		
		if(loadingInterval<1000)
			{System.out.println("PASS "+loadingInterval);status=1; }
			else{System.out.println("FAIL "+loadingInterval); status=0;}
			

		driver.quit();
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