package seetestTutorial;

import com.experitest.client.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;

public class MakePaymentForAll {
	  protected MyClient client =null;
	  private String deviceOS;
	public MakePaymentForAll(String deviceOS, MyClient client) {
		this.deviceOS = deviceOS;
		this.client = client;
	};
		

		  

		  public void RunTheTest(){
			  String testName = "MakePayment";
			  //client = new MyClient(host, port, true);  
			  //client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
			 // client.mySetReporter(deviceOS, testName, "xml", "reports", "MakePaymentAndroid");
		
		
			  //create text file
	     
	      String pathForCreatingText = "temp";
			String pathToFile = "temp";

	      //create CSV file
		  if(deviceOS.contains("android")) {
			  pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
			  if (!(MyRunnable.isRunAllTests)) {
				  new File(pathToFile).mkdirs();
				  
				}		      pathForCreatingText = pathToFile + File.separator  + "MakePaymentAndroid.txt";
		      testName = "MakePaymentAndroid";
		  }
		  else {
		      pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
		      if (!(MyRunnable.isRunAllTests)) {
				  new File(pathToFile).mkdirs();
				  
				}		      pathForCreatingText = pathToFile + File.separator  + "MakePaymentiPhone.txt";
		      testName = "MakePaymentiPhone";
		  }
		    String pathForCreatingMonitorData =  pathToFile + File.separator  + "MonitorData_MakePayment.csv";

		//getting device name
		  //String deviceName = client.getDiviceName(testName);
	      //client.mySetDevice(deviceName, testName);
		  String deviceName = MyRunnable.deviceName;
		  if(deviceName.isEmpty()) {
				return;
			}

			client.myStartMonitor(deviceName, testName, "com.experitest.ExperiBank");

	      //enter to app
	      client.myDeviceAction(deviceName, testName, "Home");

	      if(client.myUninstall(deviceName, testName, "com.experitest.ExperiBank")){
	            // If statement
	        }
	        //install new version
	        if(client.myInstall(deviceName, testName, "com.experitest.ExperiBank", true, false)){
	        	client.myLaunch(deviceName, testName, "com.experitest.ExperiBank", true, true);
	        	client.myElementSendText(deviceName, testName, "NATIVE", "xpath=//*[@accessibilityLabel='Username']", 0, "company");
	        	client.myElementSendText(deviceName, testName , "NATIVE", "xpath=//*[@accessibilityLabel='Password']", 0, "company");
	        	client.myClick(deviceName, testName, "NATIVE", "text=Login", 0, 1);

	        	client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Make Payment']", 0, 1);
		        if(client.waitForElement("NATIVE", "xpath=//*[@accessibilityLabel='Phone']", 0, 30000)){
		            // If statement
		        }
		        int amountOfMouny = 1;
		        client.myElementSendText(deviceName, testName, "NATIVE", "xpath=//*[@accessibilityLabel='Phone']", 0, "1");
		        client.myElementSendText(deviceName, testName, "NATIVE", "xpath=//*[@accessibilityLabel='Name']", 0, "1");
		        client.myElementSendText(deviceName, testName, "NATIVE", "xpath=//*[@accessibilityLabel='Amount']", 0, String.valueOf(amountOfMouny));
		        
		        String[] screenSizeString = client.myGetDeviceProperty(deviceName, testName,"device.screensize").split("x");
		        int screenSize = Integer.parseInt(screenSizeString[1]);
		        if(client.waitForElement("NATIVE", "partial_text=Select", 0, 30000)){
		            // If statement
		        }
		        client.myLongClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Select']", 0, 1, 0, 0);
		        if(client.mySwipeWhileNotFound(deviceName, testName, "Down", screenSize/2, 2000, "NATIVE", "xpath=//*[@accessibilityLabel='Ukraine']", 0, 1000, 5, false)){ //set to dont click just to let me use next command
		        	
		        }
		        if(client.myElementListVisible(deviceName, testName, "accessibilityLabel=conutryView", "text=Ukraine", 0)) {
			        client.myLongClick(deviceName, testName, "NATIVE", "xpath=//*[@accessibilityLabel='Ukraine']", 0, 1, 0, 0);
		        }
		        if(client.waitForElement("NATIVE", "partial_text=Send Payment", 0, 10000)){
		            // If statement
		        }
		        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Send Payment']", 0, 1);
		        if(client.waitForElement("NATIVE", "partial_text=Yes", 0, 30000)){
		            // If statement
		        }
		        client.myClick(deviceName, testName, "NATIVE", "text=Yes", 0, 1);
		        //else couldn't find the element
		        String howMuchWePaid = client.myGetText(deviceName, testName, "TEXT");
		        String calcLeftover = String.valueOf(100-amountOfMouny) + ".00$"; //don't like the hard 100...
		        boolean didWeWon = (howMuchWePaid.contains(String.valueOf(calcLeftover)));
			    try {
					MyClient.saveResultToFile(testName, pathForCreatingText, didWeWon);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		        
	        }
	        else {
	        	try {
					MyClient.saveResultToFile(testName, pathForCreatingText, false);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);

	        /*if (!(MyRunnable.isRunAllTests)) {
                client.releaseDeviceIfNotSuite(deviceName, testName, deviceOS);
            }*/
	      
	  }
}
