package seetestTutorial;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.*;
/**
 *
*/
public class DownloadAppIPhone {
    private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
    protected MyClient client =null;
    String testName = "DownloadAppIPhoneTest";
    protected String deviceOS = MyRunnable.deviceOS;
    protected String deviceName = MyRunnable.deviceName;
    protected int numOfFailures = 0;
	String pathToFile = "temp";
    String pathForCreatingText = "temp";


    
    @Before
    public void setUp(){
  	  //client = new MyClient(host, port, true);  
  	  
    	client = MyRunnable.client;
    	client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
  	  	client.mySetReporter(deviceOS , testName, "xml", "reports", testName);
    }

    @Test
    public void testDownloadAppIPhone(){
    	//DownloadAppAll runTheTest = new DownloadAppAll(deviceOS, client);
    	//int numOfError = runTheTest.runTheTest();
    	//if(numOfError != 1) { 
    	//	//not ==0 for future upgrade: if, I'll need to change to reset specific element in the device
    	//	failureMechanism(numOfError);
    	//	numOfError = runTheTest.runTheTest();
    	//	if(numOfError != 1) {
    	//		failureMechanism(numOfError);
    	//		rebootDevice();
    	//		runTheTest.runTheTest();
    			
    	//	}
    	//}
    	boolean didWeWon = runTheTest();
    	String result = "temp";
    	if(didWeWon) {
    		result = "we installed the app";
    	}
    	else {
    		result = "installetion failed";
    	}
    	try {
        	saveResultToFile(testName, pathForCreatingText, result);
		} catch (IOException e) {
			e.printStackTrace();
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
    
    public void rebootDevice() {
    	if(client.myReboot(deviceName,  testName, 180000)) {
			client.myDeviceAction(deviceName, testName, "Unlock");
			if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Close app']", 0)){
				client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Close app']", 0, 1);
			}
		}
    }
    
      
    public boolean runTheTest() {
    	pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
	      if (!(MyRunnable.isRunAllTests)) {
			  new File(pathToFile).mkdirs();
			  
			}
	      pathForCreatingText = pathToFile + File.separator  + testName + ".txt";
	      String pathForCreatingMonitorData =  pathToFile + File.separator  + "MonitorData_DownloadApp.csv";
	      client.setMonitorPollingInterval(2000);

	      String[] screenSizeString = client.myGetDeviceProperty(deviceName, testName, "device.screensize").split("x");
	      int screenSize = Integer.parseInt(screenSizeString[1]);
	        
	      String allInstalledApps = client.myGetInstalledApplications(deviceName, testName);
			if(allInstalledApps.contains("taki")) {
				if(client.myUninstall(deviceName, testName, "com.kinkajoo.taki")){
		               // returns true even if the app isn't installed 
		           }
			}
    	 client.myLaunch(deviceName, testName, "com.apple.AppStore", true, true);
    	 client.myLaunch(deviceName, testName, "com.apple.AppStore", true, true);
    	 while(!(client.myWaitForElement(deviceName, testName, "NATIVE", "xpath=//*[@text='Top Charts' and @class='UIAButton']", 0, 30000))) {
         	
         }
    	 client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Top Charts' and @class='UIAButton']", 0, 1);
        if(client.myWaitForElement(deviceName, testName, "NATIVE", "xpath=//*[@class='UIACollectionView']", 0, 30000)){
            // If statement
        }
        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Categories' and ./parent::*[@text='Top Charts']]", 0, 1);
        if(client.myWaitForElement(deviceName, testName, "NATIVE", "xpath=//*[@text='Games']", 0, 30000)){
            // If statement
        }
        if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Games']", 0)){
            client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Games']", 0, 1);

        }
        if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='All Games']", 0)) {
            client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='All Games']", 0, 1);
        }
        while(!(client.myWaitForElement(deviceName, testName, "NATIVE", "xpath=//*[@text='Free']", 0, 30000))) {
         	
        }
        if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Free']", 0)) {
            client.click("NATIVE", "xpath=//*[@text='Free']", 0, 1);
        }
        if(client.mySwipeWhileNotFound(deviceName, testName, "Down", screenSize/2, 20, "default", "TAKI_1", 0, 1000, 5, true)){
        	//client.myClick(deviceName, testName, "default", "TAKI_1", 0, 1);
        	if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='INSTALL']", 0)) {
        		client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='INSTALL']", 0, 1);
        	}
        	if(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Download']", 0)) {
        		client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Download']", 0, 1);
        	}
            
                        
            while(client.myWaitForElementToVanish(deviceName, testName, "NATIVE", "xpath=//*[@text='Cancel download']", 0, 40000)) {
            	
            }
            while(!(client.myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='OPEN']", 0))) {
            	
            }
            
            //client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='ACCEPT']", 0, 1);
            
           
           //while(!(client.myWaitForElement(deviceName, testName, "NATIVE", "xpath=//*[@text='UNINSTALL']", 0, 30000))){
            	
            //}
                      
         //need to check that TAKI was installed
           //allInstalledApps = client.myGetInstalledApplications(deviceName, testName);
           
            client.myDeviceAction(deviceName, testName, "Home");
   
            
            	
        }
        
        allInstalledApps = client.myGetInstalledApplications(deviceName, testName);
        boolean installetionFlag = false;
		if(allInstalledApps.contains("taki")) {
			installetionFlag = true;
		}
       if(client.myUninstall(deviceName, testName, "com.kinkajoo.taki")){
           // returns true even if the app isn't installed 
       }
       client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);

        return installetionFlag;
        
    }
    
    public static void saveResultToFile(String testName, String fileNAmeForLogs, String whatToPrint)throws IOException{
		 PrintWriter outputWriter = new PrintWriter(fileNAmeForLogs, "UTF-8");
		 
		
		 outputWriter.write(testName + ":" + whatToPrint);
		
		  outputWriter.flush();  
		  outputWriter.close();  
		}
}
