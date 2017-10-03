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
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;
/**
*
*/
public class DownloadAppAll {
	protected MyClient client =null;
	private String deviceOS;
	protected int whereToSave = 0;
	protected String deviceName = "temp";
	String testName = "DownloadAppAllTest";
	protected int isElemntFound[] = new int[10];
	public DownloadAppAll(String deviceOS, MyClient client) {
		this.deviceOS = deviceOS;
		this.client = client;
	};
	
	public int runTheTest(){
	
		 
		String pathToFile = MyRunnable.pathToFile;
		String pathForCreatingText = "temp";
	    if(deviceOS.contains("android")) {
		  
	      
	      pathForCreatingText = pathToFile + File.separator  + "DownloadAppAndroid.txt";
	      testName = "DownloadAppAndroid";	    
		}
		else {
	      pathForCreatingText = pathToFile + File.separator  + "DownloadAppiPhone.txt";
	      testName = "DownloadAppiPhone";
		}
	    String pathForCreatingMonitorData =  pathToFile + File.separator  + "MonitorData_DownloadApp.csv";

    	deviceName = MyRunnable.deviceName;
		if(deviceName.contains("failed")) {
			try {
	        	saveResultToFile(testName, pathForCreatingText, "couldn't connect to a device");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
		client.setMonitorPollingInterval(2000);

		String[] screenSizeString = client.myGetDeviceProperty(deviceName, testName, "device.screensize").split("x");
        int screenSize = Integer.parseInt(screenSizeString[1]);
		if(deviceOS.contains("android") && client.myGetNetworkConnection(deviceName, testName, "airplane_mode")){
			client.mySetNetworkConnection(deviceName, testName, "wifi", true);
			if(client.myGetNetworkConnection(deviceName, testName, "airplane_mode")){
				try {
		        	saveResultToFile(testName, pathForCreatingText, "coudln't connect to WiFi");
				} catch (IOException e) {
					e.printStackTrace();
				}
				return 2;
			}
		}
		String allInstalledApps = client.myGetInstalledApplications(deviceName, testName);
		if(allInstalledApps.contains("taki")) {
			uninstallTaki(screenSize);
		}
        closeAppStore();
		
		client.myDeviceAction(deviceName, testName,"Home");
		if(deviceOS.contains("android")) {
			if(!(client.mySwipeWhileNotFound(deviceName, testName,"Left", 50, 2000, "NATIVE", "xpath=//*[@text='Play Store']", 0, 1000, 5, true))){
	        	client.myDeviceAction(deviceName, testName,"Home");
	        	client.mySwipeWhileNotFound(deviceName, testName,"Right", 50, 2000, "NATIVE", "xpath=//*[@text='Play Store']", 0, 1000, 5, true);
	        }
		}
		else {
			 if(!(client.mySwipeWhileNotFound(deviceName, testName,"Left", 50, 2000, "NATIVE", "xpath=//*[@text='App Store']", 0, 1000, 5, true))){
		        	client.myDeviceAction(deviceName, testName,"Home");
		        	client.mySwipeWhileNotFound(deviceName, testName,"Right", 50, 2000, "NATIVE", "xpath=//*[@text='App Store']", 0, 1000, 5, true);
		        }
		}
       
        if(!client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='TOP CHARTS']", 0)) {
        	try {
	        	saveResultToFile(testName, pathForCreatingText, "coudln't find TOP CHARTS");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 3;
        }
        
        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='TOP CHARTS']", 0, 1);
        
        if(!client.myIsElementFound(deviceName, testName, "NATIVE","xpath=//*[@text='TOP FREE GAMES']", 0)) {
        	try {
	        	saveResultToFile(testName, pathForCreatingText, "coudln't find TOP FREE GAMES");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 4;
        }
        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='TOP FREE GAMES']", 0, 1);
        
        
        if(client.mySwipeWhileNotFound(deviceName, testName, "Down", screenSize/2, 20, "default", "li_thumbnail", 0, 1000, 5, false)){
        	client.myClick(deviceName, testName, "default", "li_thumbnail", 0, 1);
            client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='INSTALL']", 0, 1);
            while(!(client.waitForElement("NATIVE", "xpath=//*[@text='ACCEPT']", 0, 10000))) {
            	
            }
            client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='ACCEPT']", 0, 1);
            
           
           while(!(client.waitForElement("NATIVE", "xpath=//*[@text='UNINSTALL']", 0, 30000))){
            	
            }
           
           closeAppStore();
           
         //need to check that TAKI was installed
           allInstalledApps = client.myGetInstalledApplications(deviceName, testName);
           if(!allInstalledApps.contains("taki")) {
           	try {
               	saveResultToFile(testName, pathForCreatingText, "failed to download taki");
       		} catch (IOException e) {
       			e.printStackTrace();
       		}
           	
           	return 5;
           }
            
            
           uninstallTaki(screenSize);
            closeAppStore();
            
            	
        }
        else {
        	try {
            	saveResultToFile(testName, pathForCreatingText, "couldn't find Taki");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        	return 6; //need to check is TAKI installed or if failed to get to right screen
        }

        try {
        	saveResultToFile(testName, pathForCreatingText, "whatToPrint");
		} catch (IOException e) {
			e.printStackTrace();
		}
        client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);

        return 1;
	}
	
	public static void saveResultToFile(String testName, String fileNAmeForLogs, String whatToPrint)throws IOException{
		 PrintWriter outputWriter = new PrintWriter(fileNAmeForLogs, "UTF-8");
		 
		
		 outputWriter.write(testName + ":" + whatToPrint);
		
		  outputWriter.flush();  
		  outputWriter.close();  
		}
	
	public void uninstallTaki(int screenSize) {
		 client.myDeviceAction(deviceName, testName,"Home");
         client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Play Store']", 0, 2);
         client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='TOP CHARTS']", 0, 1);
         client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='TOP FREE GAMES']", 0, 1);
         client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@id='toggle_switch_button' and @width>0]", 0, 1);
         if(client.waitForElement("NATIVE", "xpath=//*[@text='TAKI']", 0, 30000)){
             // If statement
         }
         client.mySwipeWhileNotFound(deviceName, testName, "Down", screenSize/2, 20, "default", "li_thumbnail", 0, 1000, 5, false);
         client.myClick(deviceName, testName, "default", "li_thumbnail", 0, 1);
         while(!(client.waitForElement("NATIVE", "xpath=//*[@text='UNINSTALL']", 0, 30000))){
             // If statement
         }
         client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='UNINSTALL']", 0, 1);
         while(!(client.waitForElement("NATIVE", "xpath=//*[@text='OK']", 0, 10000))){
             // If statement
         }
         client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='OK']", 0, 1);
	}
	
	public void closeAppStore() {
		client.myDeviceAction(deviceName, testName,"Home");
        client.myDeviceAction(deviceName, testName,"Recent Apps");
        if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Clear all']", 0)) {
        	client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='CLOSE ALL']", 0, 1);
        }
        if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='CLOSE ALL']", 0)) {
        	client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='CLOSE ALL']", 0, 1);
        }
	}
	
}


