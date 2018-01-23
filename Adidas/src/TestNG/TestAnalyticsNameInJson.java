package TestNG;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.json.simple.parser.ParseException;

public class TestAnalyticsNameInJson {
	private static SoftAssert t_assert = new SoftAssert();
  @Test
  public void AnalyticsNameCheck0001()  throws IOException, ParseException{
		org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
    	Object obj = null;
		
			//obj = parser.parse(new FileReader("/Users/atul/Downloads/atul1.txt"));
			obj = parser.parse(new FileReader("JSON.txt"));
		
    	JSONObject jsonObj = (JSONObject)obj;
    	
    	org.json.simple.JSONArray ComponentArray = null;
    	JSONObject content_fields = null;
    	org.json.simple.JSONArray itemsArray = null;
    	//JSONObject item = null;

    	int i=0,j=0;

    	System.out.println("Full JSON " + obj);
    	
    
    	System.out.println("HELLO - JSON Parser");
    	ComponentArray= (org.json.simple.JSONArray) jsonObj.get("component_presentations");
    	System.out.println(jsonObj.get("component_presentations"));
    	
    	System.out.println("jsonObj- " + jsonObj);
    	System.out.println("Full ComponentArray is :- " + ComponentArray);
    	
    	
    	for(Object component_tmp: ComponentArray.toArray()){
    		i++;
    		System.out.println("\\r\\n Iteration Of Component  " + i);
           JSONObject component = (JSONObject) component_tmp;
           content_fields = (JSONObject) component.get("component");
           System.out.println("component--"+ component.get("component"));
         //  System.out.println("component_tmp--"+ component_tmp.get("component"));
           JSONObject content_fields2 = (JSONObject) content_fields.get("content_fields");
          if (content_fields2==null){ System.out.println("continue ");  t_assert.assertTrue(false); continue;}
            System.out.println("content_fields- " +i  + content_fields);
            System.out.println("content_fields2- " +i  + content_fields2);
            itemsArray=  (org.json.simple.JSONArray) content_fields2.get("items");
            System.out.println("Analytics_Name NOT Found : Full itemsArray is " + itemsArray);  
            if (itemsArray==null){ System.out.println("continue "); t_assert.assertTrue(false); continue;}
            j=0;
          
            for(Object items_tmp: itemsArray.toArray()){
            	System.out.println("\\r\\n Iteration Of Items  " + j);j++;
                JSONObject items = (JSONObject) items_tmp;
                JSONObject supporting_fields = (JSONObject) items.get("supporting_fields");
                System.out.println("supporting_fields--"+ items.get("supporting_fields"));
                if (supporting_fields==null){ System.out.println("Analytics_Name NOT Found continue = "); t_assert.assertTrue(false); continue;}

                JSONObject supporting_fields2 = (JSONObject) supporting_fields.get("supporting_fields");
                 System.out.println("supporting_fields2- " +i  + supporting_fields2);
                 if (supporting_fields2==null){ System.out.println("Analytics_Name NOT Found continue ");  t_assert.assertTrue(false); continue;}
            
                 
                 JSONObject standard_metadata = (JSONObject) supporting_fields2.get("standard_metadata");
                 System.out.println("standard_metadata- " +i  + standard_metadata);
                 if (standard_metadata==null){ System.out.println("Analytics_Name NOT Found continue "); t_assert.assertTrue(false); continue;}
                 Object analytics_name = (Object) standard_metadata.get("analytics_name");
                 System.out.println("Analytics_Name Found = " + i  + analytics_name); t_assert.assertTrue(true);
                
                
            }
            
    	}
    	System.out.println(" \n ----- Total of " + i + " Components Parsed in API JSON response-----" ); 
    	 System.out.println(" \n ----- Test Case Finished ------ \n"); 
    	 t_assert.assertAll();
    
      }
}
