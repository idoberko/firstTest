package seetestTutorial;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.*;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
/**
 *
*/
public class DownloadAppAndroid {
    private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
    protected MyClient client =null;
    String testName = "DownloadAppAndroidTest";
    protected String deviceOS = MyRunnable.deviceOS;
    protected String deviceName = MyRunnable.deviceName;
    protected int numOfFailures = 0;
    
    @Before
    public void setUp(){
  	  //client = new MyClient(host, port, true);  
  	  
    	client = MyRunnable.client;
    	client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
  	  	client.mySetReporter(deviceOS , testName, "xml", "reports", testName);
    }

    @Test
    public void testDownloadAppAndroid(){
    	//DownloadAppAll runTheTest = new DownloadAppAll(deviceOS, client);
    	//It will have to be changed to switch case - for ex. if installing worked but uninstalling had failed
    	int numOfError = runTheTest();
    	if(numOfError != 1) { 
    		//not ==0 for future upgrade: if, I'll need to change to reset specific element in the device
    		failureMechanism(numOfError);
    		numOfError = runTheTest();
    		if(numOfError != 1) {
    			failureMechanism(numOfError);
    			rebootDevice();
    			runTheTest();
    			
    		}
    	}
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
        //client.releaseClient();
    }
    
    
    public void failureMechanism(int numOfError) {
    	switch (numOfError) {
    	case 0: {
			return; //couldn't connect to the device, nothing to reboot
            //break;
		}
		case 1: {
			//shit just hit the fan
            break;
		}
		case 2:{
			rebootDevice();
            break;
		}
		case 3:{
			closeAppStore();
            break;

		}
		case 4:{
			closeAppStore();
            break;

		}
		case 5:{
			rebootDevice();
            break;

		}
		case 6:{
			client.myDeviceAction(deviceName, testName,"Home");
	        client.myDeviceAction(deviceName, testName,"Recent Apps");
	        if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Clear all']", 0)) {
	        	client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='CLOSE ALL']", 0, 1);
	        }
	        else{
	        	client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='CLOSE ALL']", 0, 1);
	        }
			break;
		}
		default:
			
		}
    }
    
    
    
    public void rebootDevice() {
    	if(client.myReboot(deviceName,  testName, 180000)) {
			client.myDeviceAction(deviceName, testName, "Unlock");
			if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Close app']", 0)){
				client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Close app']", 0, 1);
			}
		}
    }
    
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
	    
    	deviceName = MyRunnable.deviceName;
		if(deviceName.contains("failed")) {
			try {
	        	saveResultToFile(testName, pathForCreatingText, "couldn't connect to a device");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return 0;
		}
		
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
