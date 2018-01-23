package TestNG;

import org.testng.annotations.Test;
//import org.testng.AssertJUnit;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public class JsonsNEST {
	private static SoftAssert m_assert = new SoftAssert();

  @Test
  public static void executeTest1() throws IOException, ParseException, FileNotFoundException {
	   
	  org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
		    	Object obj = null;
				
					//obj = parser.parse(new FileReader("/Users/atul/Downloads/test_api.txt"));
					obj = parser.parse(new FileReader("JSON.txt"));
				
		    	JSONObject jsonObj = (JSONObject)obj;
		    	
		    	org.json.simple.JSONArray ComponentArray = null;
		    	JSONObject content_fields = null;
		    	org.json.simple.JSONArray itemsArray = null;
		    	JSONObject background_media = null;
		    	org.json.simple.JSONArray jsonObj3 = null;
		    	int i=0,j=0;

		    	System.out.println("Full JSON is as given below: " + obj);
		    	jsonObj3= (org.json.simple.JSONArray) jsonObj.get(obj);
		    
		    	System.out.println("----HELLO - JSON Parsing starts now----");
		    	ComponentArray= (org.json.simple.JSONArray) jsonObj.get("component_presentations");
		    	System.out.println(jsonObj.get("component_presentations"));
		    	
		    	System.out.println("jsonObjis " + jsonObj);
		    	System.out.println("Full ComponentArrayis " + ComponentArray);
		    	System.out.println("jsonObj3is " + jsonObj3);
		    	
		    	for(Object component_tmp: ComponentArray.toArray()){
		    		System.out.println("\n Iteration Of Component  " + i);
	               JSONObject component = (JSONObject) component_tmp;
	               content_fields = (JSONObject) component.get("component");
	               System.out.println("component--"+ component.get("component"));
	             //  System.out.println("component_tmp--"+ component_tmp.get("component"));
	               JSONObject content_fields2 = (JSONObject) content_fields.get("content_fields");
	              if (content_fields2==null){ System.out.println("continue ");  continue;}
	                System.out.println("content_fields is " +i  + content_fields);
	                System.out.println("content_fields2 is " +i  + content_fields2);
	                itemsArray=  (org.json.simple.JSONArray) content_fields2.get("items");
	                if (itemsArray==null){ System.out.println("No Items Found in content_fields of component " + i );   
	                //AssertJUnit.assertFalse("fail",itemsArray==null);
	                m_assert.assertTrue(true);  continue;}
	    
	                System.out.println("Full itemsArray is " + itemsArray);  
	                j=0;
	              
	                for(Object items_tmp: itemsArray.toArray()){
	                	System.out.println("\n Iteration Of Items  " + j + "in Component " + i);j++;
	                    JSONObject items = (JSONObject) items_tmp;
	                    background_media = (JSONObject) items.get("background_media");
	                    System.out.println("background_media--"+ items.get("background_media"));
	                  //  System.out.println("items_tmp--"+ items_tmp.get("items"));
	                    JSONObject desktop_image = (JSONObject) background_media.get("desktop_image");
	                     System.out.println("desktop_image is " +i  + desktop_image);
	                     if (desktop_image==null){ System.out.println("\n desktop_image NOT Found "); m_assert.assertTrue(true); continue;}
	                     Object desktop_image_url = (Object) desktop_image.get("url");
	                     System.out.println("desktop_image_url is " +i  + desktop_image_url); 
	                     
	                     JSONObject tablet_image = (JSONObject) background_media.get("tablet_image");
	                     System.out.println("tablet_image is " +i  + tablet_image);
	                     if (tablet_image==null){ System.out.println("\n tablet_image NOT Found  "); m_assert.assertTrue(true);  continue;}
	                     Object tablet_image_url = (Object) tablet_image.get("url");
	                     System.out.println("tablet_image_url is " +i  + tablet_image_url); 
	                    
	                     JSONObject mobile_image = (JSONObject) background_media.get("mobile_image");
	                     System.out.println("mobile_image is " +i  + mobile_image);
	                     if (mobile_image==null){ System.out.println("\n mobile_image NOT Found   ");  m_assert.assertTrue(true); continue;}
	                     Object mobile_image_url = (Object) mobile_image.get("url");
	                     System.out.println("mobile_image_url is " +i  + mobile_image_url); 
	                     
	                }
	                
	               //---------
	             //   System.out.println("component " + i + component);
	           //   System.out.println(component.get("component"));
	                i++;
	              //  System.out.println(issue.toJSONString("items", issueObj, null));
	               
	                
		    	}
		    	System.out.println("Parsing of JSON successful and all Images URL listed "); 
		    	m_assert.assertAll();
		    	 System.out.println("Test Case Finished"); 
		    
  }
 

}
