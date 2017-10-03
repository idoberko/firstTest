package seetestTutorial;

//package <set your test package>;
import com.experitest.client.*;

import org.apache.tools.ant.types.Path;
import org.junit.*;
import seetestTutorial.Main;

/**
*
*/
//package <set your test package>;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;
/**
*
*/
public class GetBest10ForAll {
	protected MyClient client =null;
	private String deviceOS;
	protected int whereToSave = 0;
	protected String deviceName = "temp";
	String testName = "DownloadAppAndroidTest";
	protected int isElemntFound[] = new int[10];
	protected String whatWeFound[] = new String[10];
	public GetBest10ForAll(String deviceOS, MyClient client) {
		this.deviceOS = deviceOS;
		this.client = client;
	};
	
	public void runTheTest(){
	
		 
		String pathToFile = "temp";
	    String pathForCreatingText = "temp";
	    String textForLaunch = "temp";
	    String textForMenu = "temp";
	    if(deviceOS.contains("android")) {
		  pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
	      if (!(MyRunnable.isRunAllTests)) {
			  new File(pathToFile).mkdirs();
			  
			}
	      
	      pathForCreatingText = pathToFile + File.separator  + "GetBest10Android.txt";
	      testName = "GetBest10Android";
	      //textForLaunch = "chrome:http://edition.cnn.com";
	      //textForMenu = "id=menu";
		}
		else {
	      pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
	      if (!(MyRunnable.isRunAllTests)) {
			  new File(pathToFile).mkdirs();
			  
			}
	      pathForCreatingText = pathToFile + File.separator  + "GetBest10iPhone.txt";
	      testName = "GetBest10iPhone";
	      //textForLaunch = "safari:edition.cnn.com";
	      //textForMenu = "xpath=//div[@class=\"nav-menu__hamburger\"]";
		}
		  String pathForCreatingMonitorData =  pathToFile + File.separator  + "MonitorData_GetBest10.csv";

    	deviceName = client.myWaitForDevice(testName, deviceOS, 30000);
		if(deviceName.isEmpty()) {
			return;
		}
	      client.setMonitorPollingInterval(2000);

		client.myDeviceAction(deviceName, testName, "Home");
        client.myDeviceAction(deviceName, testName, "Recent Apps");
        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Clear all']", 0, 1);
       
        client.myDeviceAction(deviceName, testName, "Home");
        if(!(client.mySwipeWhileNotFound(deviceName, testName, "Left", 50, 2000, "NATIVE", "xpath=//*[@text='Play Store']", 0, 1000, 5, true))){
        	client.myDeviceAction(deviceName, testName, "Home");
        	client.mySwipeWhileNotFound(deviceName, testName, "Right", 50, 2000, "NATIVE", "xpath=//*[@text='Play Store']", 0, 1000, 5, true);
        }
        
        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='TOP CHARTS']", 0, 1);
        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@id='toggle_switch_button' and @width>0]", 0, 1);
        clickNVarify();

	    try {
			MyClient.saveBest10ToFile(pathForCreatingText, whatWeFound);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	    if (!(MyRunnable.isRunAllTests)) {
            client.releaseDeviceIfNotSuite(deviceName, testName, deviceOS);
        }
		        
        client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);

	}
		    
		    protected void clickNVarify() {
	    		String elementToFind = "xpath=//*[@text='1']";

		    	
		    	for(int i = 1; i<11; i++) {
		    		String whatToFind = String.valueOf(i-1);
		    		String whatToInsert = String.valueOf(i);
		    		String whatsNext = String.valueOf(i+1);
		    		//elementToFind.replace("1", whatToFind);
		    		String temp = elementToFind.replaceAll(whatToFind, whatToInsert);
		    		String tempPOne = elementToFind.replaceAll(whatToFind, whatsNext);
		    		elementToFind = temp;
		    		 String[] screenSizeString = client.myGetDeviceProperty(deviceName, testName, "device.screensize").split("x");
				        int screenSize = Integer.parseInt(screenSizeString[1]);
				        while(!(client.mySwipeWhileNotFound(deviceName, testName, "Down", screenSize/2, 20, "NATIVE", tempPOne, 0, 1000, 5, false))) {
			    		}
				       // client.swipe("Down", screenSize-400, 10);
				        
		    		while(!(client.mySwipeWhileNotFound(deviceName, testName, "Down", 50, 2000, "NATIVE", temp, 0, 1000, 5, false))) {
		    		}
		    		if(client.myIsElementFound(deviceName, testName, "NATIVE",  temp, 0)){
		    			
			        	String str4 = client.myGetTextIn(deviceName, testName, "NATIVE", temp, 0, "TEXT", "Right", 0, 0, 250, 0);
			        	String[] splitFinding = str4.split("\n");
			        	whatWeFound[whereToSave] = splitFinding[0];  
			        }
			        else {
			        	isElemntFound[whereToSave] = 0;
			        }
			        whereToSave++;
			       
		    	}
		        
		    
		    }
	
}
