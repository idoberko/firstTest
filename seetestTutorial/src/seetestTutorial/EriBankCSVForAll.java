package seetestTutorial;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;

public class EriBankCSVForAll {
	private String host = "localhost";
	  private int port = 8889;
	  private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
	  protected MyClient client =null;
	  private String deviceOS;
	public EriBankCSVForAll(String deviceOS, MyClient client) {
		this.deviceOS = deviceOS;
		this.client = client;
	};
	
	public void RunTheTest(){
		//String testName = "EriBankCSVAndroid";
		
		String testName = null;
		//client = new MyClient(host, port, true);  
	   // client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
	    //client.mySetReporter(deviceOS, testName, "xml", "reports", "EriBankCSVAndroid");
	    
	    int[] howMuchDataWasCreated = new int[2]; 
		 
		  //getting device name
		  
		 // String deviceName = client.getDiviceName(testName);
	      //client.mySetDevice(deviceName, testName);
	      
	      String pathForCreatingCSV = "temp";
	      String pathForCreatingText = "temp";
	      String pathToFile = "temp";
	      //create CSV file
		  if(deviceOS.contains("android")) {
		      pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
		      if (!(MyRunnable.isRunAllTests)) {
				  new File(pathToFile).mkdirs();
				  
				}
		      pathForCreatingCSV = pathToFile + File.separator  + "EriBankCSVAndroid.csv";
		      pathForCreatingText = pathToFile + File.separator  + "EriBankCSVAndroid.txt";
		      testName = "EriBankCSVAndroid";
		  }
		  else {
		      pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
		      if (!(MyRunnable.isRunAllTests)) {
				  new File(pathToFile).mkdirs();
				  
				}
		      pathForCreatingCSV = pathToFile + File.separator  + "EriBankCSViPhone.csv";
		      pathForCreatingText = pathToFile + File.separator  + "EriBankCSViPhone.txt";
		      testName = "EriBankCSVIPhone";
		  }
		  String pathForCreatingMonitorData = pathToFile + File.separator  + "MonitorData_EriBankCSV.csv";
		  String deviceName = MyRunnable.deviceName;

	      

	      
	      try {
	    	  howMuchDataWasCreated = MyClient.printToCSVFile(pathForCreatingCSV); //returns how many lines where saved and how much data in each line
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      client.myDeviceAction(deviceName, testName, "Home");
	      //start with cleaning the last installed version 
	        if(client.myUninstall(deviceName, testName, "com.experitest.ExperiBank")){
	            // If statement
	        }
	        //install new version
	        if(client.myInstall(deviceName, testName, "com.experitest.ExperiBank", true, false)){
	        	client.myStartMonitor(deviceName, testName, "com.experitest.ExperiBank");
	        	//String[][] retrieveData = new String[howMuchDataWasCreated[1]][howMuchDataWasCreated[0]];
	        	 client.myLaunch(deviceName, testName, "com.experitest.ExperiBank", true, true);
	        	 boolean didWeWon = client.checkLogin(deviceName, testName, pathForCreatingCSV, howMuchDataWasCreated);
	        	 try {
						MyClient.saveResultToFile(testName, pathForCreatingText, didWeWon);
					} catch (IOException e) {
						e.printStackTrace();
					}
	        	 client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);
	        }
	        else {
	        	try {
					MyClient.saveResultToFile(testName, pathForCreatingText, false);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        
	        
	       /* if (!(MyRunnable.isRunAllTests)) {
                client.releaseDeviceIfNotSuite(deviceName, testName, deviceOS);
            }*/
	        
	        

	}
}
