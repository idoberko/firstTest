package seetestTutorial;

//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
import seetestTutorial.Main;

/**
*
*/
//package <set your test package>;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;
/**
*
*/
public class CNNSecondTestForAll {
	protected MyClient client =null;
	private String deviceOS;
	protected int whereToSave = 0;
	protected String deviceName = "temp";
	protected int resultsOfSecondTestAndroid[][] = new int[2][13];
	protected String whatWeWhereLookingFor[] = new String[13];
	protected String whatWeFound[] = new String[13];
	protected String testName = "CNNText";
	public CNNSecondTestForAll(String deviceOS, MyClient client) {
		this.deviceOS = deviceOS;
		this.client = client;
	};
	
	public void runTheTest() {
		String pathToFile = "temp";
	    String pathForCreatingText = "temp";
	    String textForLaunch = "temp";
	    String textForMenu = "temp";
	    if(deviceOS.contains("android")) {
		  pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
		  if (!(MyRunnable.isRunAllTests)) {
			  new File(pathToFile).mkdirs();
			  
			}
	      pathForCreatingText = pathToFile + File.separator  + "CNNSecondTestAndroid.txt";
	      testName = "CNNSecondTestAndroid";
	      textForLaunch = "chrome:http://edition.cnn.com";
	      textForMenu = "id=menu";
		}
		else {
	      pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
	      if (!(MyRunnable.isRunAllTests)) {
			  new File(pathToFile).mkdirs();
			  
			}
	      pathForCreatingText = pathToFile + File.separator  + "CNNSecondTestiPhone.txt";
	      testName = "CNNSecondTestiPhone";
	      textForLaunch = "safari:edition.cnn.com";
	      textForMenu = "xpath=//div[@class=\"nav-menu__hamburger\"]";
		}
	    String pathForCreatingMonitorData =  pathToFile + File.separator  + "MonitorData_CNNSecondTestiPhone.csv";
		deviceName = MyRunnable.deviceName;
		if(deviceName.isEmpty()) {
			return;
		}
		
	client.myStartMonitor(deviceName, testName, "com.apple.mobilesafari");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "text=Regions", "regions");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "text=U.S. Politics", "politics");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "css=IMG.nav-menu__img-money", "money");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "css=IMG.nav-menu__img-entertainment", "entertainment");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "css=IMG.nav-menu__img-tech", "technology");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "text=Sport", "sport");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "css=A.nav-flyout__section-title[href='/travel']", "travel");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "xpath=(//*[@id='nav-expanded-menu']/*/*/*[@nodeName='IMG' and ./parent::*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV']]])[5]", "style");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "text=Health", "health");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "text=Features", "specials");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "text=Video", "videos");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "css=IMG.nav-menu__img-vr360", "vr");
   	clickNCompare(textForLaunch, textForMenu, "WEB", "text=More…", "more");

   
       
       try {
			MyClient.saveArrayToFile(pathForCreatingText, resultsOfSecondTestAndroid, whatWeWhereLookingFor, whatWeFound);
		} catch (IOException e) {
			e.printStackTrace();
		}
       
      // if (!(MyRunnable.isRunAllTests)) {
       //    client.releaseDeviceIfNotSuite(deviceName, testName, deviceOS);
      // }
       client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);
	}
	
	protected void clickNCompare(String textForLaunch, String textForMenu,  String zoneForMenu, String elementForMenu, String toCompare) {
        
		client.myLaunch(deviceName, testName, textForLaunch, true, true);
        try{client.hybridWaitForPageLoad(60000);} catch(Exception e) {};
        if(client.myIsElementFound(deviceName, testName, "WEB", "xpath=//*[@text='Safari could not open the page because the server stopped responding.']",0)) {
        	client.myLaunch(deviceName, testName, textForLaunch, true, true);
            try{client.hybridWaitForPageLoad(60000);} catch(Exception e) {};
        }
        if(client.myIsElementFound(deviceName, testName, "WEB", "xpath=(//*[@nodeName='DIV' and ./parent::*[@nodeName='DIV' and ./parent::*[@id='breaking-news']]]/*[@nodeName='DIV' and @width>0])[1]", 0)){
        		client.myClick(deviceName, testName, "WEB", "xpath=(//*[@nodeName='DIV' and ./parent::*[@nodeName='DIV' and ./parent::*[@id='breaking-news']]]/*[@nodeName='DIV' and @width>0])[1]", 0, 1);
        }
        if(client.myIsElementFound(deviceName, testName, "WEB", textForMenu, 0)) {
            client.myClick(deviceName, testName, "WEB", textForMenu, 0, 1);
        }
    	if(client.myIsElementFound(deviceName, testName, zoneForMenu, elementForMenu, 0)){
            // If statement
      	  resultsOfSecondTestAndroid[0][whereToSave] = 1;
      	  client.myClick(deviceName, testName, zoneForMenu, elementForMenu, 0, 1); 
      	try{client.hybridWaitForPageLoad(60000);} catch(Exception e) {};
      	String fromURL = getUrl(deviceName, testName);
      	whatWeFound[whereToSave] = fromURL;
      	
      	  if(fromURL.contains(toCompare)) {
      		  resultsOfSecondTestAndroid[1][whereToSave] = 1;
      	  }
      	  else {
      		  String whatWentWrong = "found" + fromURL;
      		  client.WhatWasTheText(deviceName, testName, whatWentWrong);
      		  resultsOfSecondTestAndroid[1][whereToSave] = 0;
      	  }
        }
        else {
      	  resultsOfSecondTestAndroid[0][whereToSave] = 0;
        }
    	whatWeWhereLookingFor[whereToSave] = toCompare;
  	  whereToSave++;
    }
    
    public String getUrl(String deviceName, String testName) {
    	if(deviceOS.contains("ios")) {
    		if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Address']", 0)) {
        		client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Address']", 0, 1);
    		}
        	String urlName = client.myGetTextIn(deviceName, testName, "NATIVE", "xpath=//*[@placeholder='Search or enter website name']", 0, "TEXT", "Inside", 0, 0);
        	if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@class='UIAKeyboard']", 0)){
            	client.myCloseKeyboard(deviceName, testName);

        	}
        	if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Cancel']", 0)) {
            	client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Cancel']", 0, 1);
        	}
        	return urlName;
    	}
    	else {
    		//client.myClick(deviceName, testName, "NATIVE", "id=url_bar", 0, 1);
    	  	  String urlName = client.myelementGetText(deviceName, testName, "NATIVE", "id=url_bar", 0);
          	//client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Cancel']", 0, 1);

    	  	return urlName;
    	}
    	
  	 
    }
}
