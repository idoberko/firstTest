package seetestTutorial;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.experitest.client.Client;
public class MyClient extends Client{
      String workingDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
	  String filename = "newFile.txt";
	  String pathForFile = workingDirectory + File.separator ;
	  public MyClient(String host, int port, boolean useSessionID) {
              super(host, port, true);

       }  
	  
public String getDiviceName(String testName) {
	String alldevices = myGetConnectedDevices(testName);
	  String[] alldevicesInLines = alldevices.split(System.getProperty("line.separator"));
	  alldevicesInLines = alldevicesInLines[0].split("\n");
	  String deviceName =alldevicesInLines[0];
	  deviceName = fixDeviceName(deviceName);
	  return deviceName;
}
public void myClick(String deviceName, String testName, String zone, String element, int index, int clickCount)
  {
       try{
              super.click(zone, element, index, clickCount);
          } catch(Exception e)
          {
        super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"click error", "expected click to work", "click has failed");   
         }
 }

public void myLaunch(String deviceName, String testName, String activityURL, boolean instrument, boolean stopIfRunning) {
	try{
		super.launch(activityURL, instrument, stopIfRunning);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"launching error", "expected web site to load", "launching website has failed");
	}
}


public boolean myIsElementFound(String deviceName, String testName, String Zone, String Element, int Index) {
	try{
		return super.isElementFound(Zone, Element, Index);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"finding the button error", "expected to find the following button:", "finding the menu button has failed");
	}
	return false;
}

public String myelementGetText(String deviceName, String testName, String Zone, String Element, int Index) {
	try{
		return super.elementGetText(Zone, Element, Index);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't get text error", "expected to get text", "getting text as failed");
	}
	return "";
}

public void WhatWasTheText(String deviceName, String testName, String whatWentWrong) {
	
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't get text error", "expected to get text", "getting text as failed");
	
}

public String myGetTextIn(String deviceName, String testName, String Zone, String Element, int Index, String TextZone, String Direction, int Width, int Height) {
	try{
		return super.getTextIn(Zone, Element, Index, TextZone, Direction, Width, Height);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't get text with direction error", "expected to get text", "getting text has failed");
	}
	return "";
}

public String myGetConnectedDevices(String testName) {
	try{
		return super.getConnectedDevices();
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave("", testName), pathForFile ,"","coudln't get connected devices", "expected to get list of devices", "getting connected devices has failed");
	}
	return "";
}

public void mySetDevice(String deviceName, String testName) {
	try{
		 super.setDevice(deviceName);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't set device:" + deviceName, "expected to set device", "setting device has failed");
	}
}

public void myElementSendText(String deviceName, String testName, String zone, String element, int index, String text) {
	try{
		 super.elementSendText(zone, element, index, text); //ElementSendText(Zone, Element, Index, Text);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't set device:" + deviceName, "expected to set device", "setting device has failed");
	}
}

public boolean myUninstall(String deviceName, String testName, String whatToUninstall) {
	try{
		return super.uninstall(whatToUninstall); 
		 
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't uninstall the app", "expected to uninstall an app", "uninstalling app has failed");
	}
	return false;
}

public boolean myInstall(String deviceName, String testName, String whatToInstall, boolean instrument, boolean keepData) {
	try{
		return super.install(whatToInstall, instrument, keepData);
		 
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't install an app", "expected to install an app", "installing app has failed");
	}
	return false;
}

public static void saveArrayToFile(String fileNAmeForLogs, int[][] arrayForPrint, String[] whatWeWhereLookingFor, String[] whatWeFound)throws IOException{
	 PrintWriter outputWriter = new PrintWriter(fileNAmeForLogs, "UTF-8");
	 int howMuchToPrint = arrayForPrint[0].length;
	  for (int i = 0; i < 2; i++) {
		  if (i == 0) {
			  outputWriter.write("was element found? \t");
		  }
		  else
			  outputWriter.write("was it succesful? \t");

		  for (int j = 0; j <howMuchToPrint; j++) {
			  outputWriter.write(Integer.toString(arrayForPrint[i][j]));
			  
		  }
		  outputWriter.write("\r\n");
		  
	  }
	  outputWriter.write("what we where looking for \t what we found \r\n");
	  //print details
	  for(int i = 0; i<howMuchToPrint; i++) {
		  outputWriter.write(whatWeWhereLookingFor[i] + "\t \t \t \t \t" + whatWeFound[i]);
		  outputWriter.write("\r\n");
	  }
	  outputWriter.flush();  
	  outputWriter.close();  
	}

public static void saveResultToFile(String testName, String fileNAmeForLogs, boolean didWeWon)throws IOException{
	 PrintWriter outputWriter = new PrintWriter(fileNAmeForLogs, "UTF-8");
	 
	 if(didWeWon) {
		 outputWriter.write(testName + ":" + " worked");
	 }
	 else {
		 outputWriter.write(testName + ":" + " failed");
	 }
	  outputWriter.flush();  
	  outputWriter.close();  
	}

public String whereToSave(String deviceName, String testName) {
	long pathForZipInLong = System.currentTimeMillis();
	  String pathForZip = String.valueOf(pathForZipInLong);
	  pathForZip = workingDirectory + File.separator + "RUN_" + pathForZip + File.separator + deviceName + File.separator + testName +".ZIP";
	  return pathForZip;
}

protected String fixDeviceName(String deviceName) {
	if (deviceName.contains(":")){
		deviceName.replaceAll(":", "_");
	}
	if (deviceName.contains("*")){
		deviceName.replaceAll("*", "_");
	}
	if (deviceName.contains("?")){
		deviceName.replaceAll("?", "_");
	}
	if (deviceName.contains("<")){
		deviceName.replaceAll("<", "_");
	}
	if (deviceName.contains(">")){
		deviceName.replaceAll(">", "_");
	}
	if (deviceName.contains("|")){
		deviceName.replaceAll("|", "_");
	}
	
	return deviceName;
}

public static int[] printToCSVFile(String csvFileName) throws IOException{
	int howManyLines = 0;
	int howMuchDataInLine = 0;
	int[] arrayForReturn = new int[2];
	
	 PrintWriter outputWriter = new PrintWriter(csvFileName);
	 StringBuilder sb = new StringBuilder();
     sb.append("id");
     sb.append(',');
     sb.append("Password");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;

     sb.append("one");
     sb.append(',');
     sb.append("company");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;
     
     sb.append("Company");
     sb.append(',');
     sb.append("one");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;

     sb.append(" ");
     sb.append(',');
     sb.append("one");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;

     sb.append("one");
     sb.append(',');
     sb.append(" ");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;

     sb.append("one");
     sb.append(',');
     sb.append("one");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;

     sb.append("compa");
     sb.append(',');
     sb.append("company");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;

     sb.append("company");
     sb.append(',');
     sb.append("compa");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;

     sb.append("company");
     sb.append(',');
     sb.append("company");
     sb.append('\n');
     howManyLines++;
 	 howMuchDataInLine++;
          
     outputWriter.write(sb.toString());
     outputWriter.close();
     System.out.println("done!");
	  
	  outputWriter.flush();  
	  outputWriter.close();
	  arrayForReturn[0] = howManyLines;
	  arrayForReturn[1] = howMuchDataInLine;
	return arrayForReturn;  
	}
	
public boolean checkLogin(String deviceName, String testName, String fileNameCSV, int[] howMuchDataWasCreated) {
	String csvUserName = null;
    String csvPassword = null;
    try (Scanner inputStream = new Scanner(new File(fileNameCSV))){
    inputStream.nextLine(); //ignore the first line - Headlines
 
            while(inputStream.hasNext())
            {
                String data = inputStream.nextLine(); // Read line
                String[] values =data.split(","); // Split the line to an array
                csvUserName=values[0];
                csvPassword=values[1];
                myElementSendText(deviceName, testName, "NATIVE",  "xpath=//*[@accessibilityLabel='Username']", 0, csvUserName); //send the text from the csv file to "user name"
                myElementSendText(deviceName, testName, "NATIVE", "xpath=//*[@accessibilityLabel='Password']", 0, csvPassword); //send the text from the csv file to "password"
                myClick(deviceName, testName, "NATIVE", "text=Login", 0, 1);
                if(myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Invalid username or password!']", 0)) // check if user name or password are not correct.
                	myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Dismiss']", 0, 1);  
                else {
                	//break; //if we don't know when the true username is writen - this break has to go 
	                if(csvUserName.contains("company")) {
	                	if(csvPassword.contains("company")) {
	                		return true;
	                	}
	                	else
	                		return false;
	                }
	                else
	                	return false;
                }
                myLaunch(deviceName, testName, "com.experitest.ExperiBank", true, true);
                while(!(myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Login']", 0))){
                	
                }

            }
                         
            myIsElementFound(deviceName, testName, "NATIVE", "xpath=//*[@text='Make Payment']", 0); // check if the user is log in
            inputStream.close(); //close the connection to the csv file
    }catch (FileNotFoundException e) {
        e.printStackTrace();
    }
	return false; 
    
}

public String myWaitForDevice(String testName, String deviceName, int i) {
	try{
		 return super.waitForDevice(deviceName, i);
	} catch(Exception e)
	{
		if(deviceName.contains("android")) {
			super.collectSupportData(whereToSave("android", testName), pathForFile ,deviceName,"coudln't set device:" + deviceName, "expected to set device", "setting device has failed");
		}
		else {
			super.collectSupportData(whereToSave("iPhone", testName), pathForFile ,deviceName,"coudln't set device:" + deviceName, "expected to set device", "setting device has failed");
		}

	}
	return "";
}

public void mySetProjectBaseDirectory(String deviceOS, String testName, String projectBaseDirectory) {
	// TODO Auto-generated method stub
	
	try{
		super.setProjectBaseDirectory(projectBaseDirectory);
		} catch(Exception e)
	{
		if(deviceOS.contains("android")) {
			super.collectSupportData(whereToSave("android", testName), pathForFile ,"no device" ,"coudln't set device: no device" , "expected to set project folder", "setting project folder has failed");
		}
		else {
			super.collectSupportData(whereToSave("iPhone", testName), pathForFile ,"no device" ,"coudln't set device: no device", "expected to set project folder", "setting project folder has failed");
		}

	}
}

public void mySetReporter(String deviceOS, String testName, String xml, String reports, String whichTest) {
	try{
		super.setReporter(xml, reports, whichTest);
		} catch(Exception e)
	{
		if(deviceOS.contains("android")) {
			super.collectSupportData(whereToSave("android", testName), pathForFile ,"no device" ,"coudln't set reporter: no device" , "expected to set reporter", "setting reporter has failed");
		}
		else {
			super.collectSupportData(whereToSave("iPhone", testName), pathForFile ,"no device" ,"coudln't set reporter: no device", "expected to set reporter", "setting reporter has failed");
		}

	}
	
}

public void myReleaseDevice(String deviceName, String testName, boolean releaseAgent, boolean removeFromDeviceList, boolean releaseFromCloud) {
	try{
		super.releaseDevice(deviceName, releaseAgent, removeFromDeviceList, releaseFromCloud);
		 
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't release device", "expected to release device", "releasing the device has failed");
	}
}

public String myGetDeviceProperty(String deviceName, String testName, String string) {
	try{
		 return super.getDeviceProperty(string);
	} catch(Exception e)
	{
		if(testName.contains("android")) {
			super.collectSupportData(whereToSave("android", testName), pathForFile ,deviceName,"coudln't get device property:" + deviceName, "expected to get screen size", "getting screen size has failed");
		}
		else {
			super.collectSupportData(whereToSave("iPhone", testName), pathForFile ,deviceName,"coudln't set device:" + deviceName, "expected to get screen size", "getting screen size has failed");
		}

	}
	return "";
}

public static void saveBest10ToFile(String pathForCreatingText, String[] whatWeFound)throws IOException {
	PrintWriter outputWriter = new PrintWriter(pathForCreatingText, "UTF-8");
	 int howMuchToPrint = whatWeFound.length;
	  for (int i = 0; i < howMuchToPrint; i++) {
		 if(i<9) {
			  outputWriter.write(Integer.toString(i+1));
			  outputWriter.write(".  \t");
			  outputWriter.write(whatWeFound[i]);
		 }
		 else {
			 outputWriter.write(Integer.toString(i+1));
			  outputWriter.write(".  \t");
			  outputWriter.write(whatWeFound[i]);
		 }
		  outputWriter.write("\r\n");
		  
	  }
	  
	  outputWriter.flush();  
	  outputWriter.close();  
	
}

public String myGetTextIn(String deviceName, String testName, String Zone, String Element, int Index, String TextZone,
		String Direction, int Width, int Height, int offsetX, int offsetY) {
	try{
		return super.getTextIn(Zone, Element, Index, TextZone, Direction, Width, Height, offsetX, offsetY);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't get text with direction error", "expected to get text", "getting text has failed");
	}
	return "";
}

public void myDeviceAction(String deviceName, String testName, String whatActionToRun) {
try {
	super.deviceAction(whatActionToRun);
}catch(Exception e)
{
	String whatFailed = "running: " + whatActionToRun + " has failed";
	super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't run the action", "expected to run device action", whatFailed);
}
}

public boolean mySwipeWhileNotFound(String deviceName, String testName, String direction, int offset, int swipeTime,
		String zone, String elementToFind, int elementToFindIndex, int delay, int rounds, boolean toClick) {
	try{
		return super.swipeWhileNotFound(direction, offset, swipeTime, zone, elementToFind, elementToFindIndex, delay, rounds, toClick);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't swipe while not found error", "expected to swipe till found", "swiping till found has failed");
	}
	return false;
}

public void releaseDeviceIfNotSuite(String deviceName, String testName, String deviceOS) {
	if(deviceOS.contains("remote")) {
    	myReleaseDevice(deviceName, testName, true, true, true);
    }
    else {
    	myReleaseDevice(deviceName, testName, false, false, false);

    }
}

public String myGetInstalledApplications(String deviceName, String testName) {
	try{
		return super.getInstalledApplications();
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't get installed apps", "expected to get a list of installed apps", "getting a list of all installed apps has failed");
	}
	return "";
}

public boolean myGetNetworkConnection(String deviceName, String testName, String string) {
	try{
		return super.getNetworkConnection(string);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't get network settings", "expected to get network settings", "getting network setting has failed");
	}
	return false;
}

public void mySetNetworkConnection(String deviceName, String testName, String string, boolean b) {
	try{
		super.setNetworkConnection(string, b);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't set network to WiFi", "expected to set the network to WiFi", "setting network to WiFi has failed");
	}
	
}

public boolean myReboot(String deviceName, String testName, int i) {
	try{
		return super.reboot(i);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't reboot the device", "expected to restart the device", "restarting the device has failed");
	}
	return false;
}

public void mySwipe(String deviceName, String testName, String string, int i, int j) {
	try{
		super.swipe(string, i, j);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't swipe " + string, "expected to swipe " + string , "swiping " + string + " has failed");
	}
}

public boolean myWaitForElement(String deviceName, String testName, String string, String string2, int i, int j) {
	try{
		return super.waitForElement(string, string2, i, j);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"element didn't show", "expected to wait until object showwed", "waiting for element has failed");
	}
	return false;
}

public boolean myWaitForElementToVanish(String deviceName, String testName, String zone, String element, int index,
		int timeOut) {
	try{
		return super.waitForElementToVanish(zone, element, index, timeOut);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"waited for element to vanish", "expected to wait until object vanished", "waiting for element to vanish has failed");
	}
	return false;
}

public void myCloseKeyboard(String deviceName, String testName) {
	try{
		super.closeKeyboard();
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"coudln't close the keyboard", "expected to close the keyboard", "closing the keyboard has failed");
	}	
}

public void myLongClick(String deviceName, String testName, String zone, String element, int index, int clickCount, int x, int y) {
	try{
		super.longClick(zone, element, index, clickCount, x, y);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"tried to long click", "expected to long click the button", "long clicking has failed");
	} 
	
}

public boolean myElementListVisible(String deviceName, String testName, String listLocator, String elementLocator, int index) {
	try{
		return super.elementListVisible(listLocator, elementLocator, index);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"tried to check if an object is part of the list", "expected to find the object in the list", "finding the object in the list has failed");
	}
	return false;
}

public String myGetText(String deviceName, String testName, String zone) {
	try{
		return super.getText(zone);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"tried to get all the text from a zone", "expected to get text from zone", "getting the text from the given zone has failed");
	}
	return null;
}

public void myStartMonitor(String deviceName, String testName, String packageNAme) {
	try{
		super.startMonitor(packageNAme);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"tried to start collecting data from monitor", "expected to start monitor", "starting monitor has failed");
	} 
}

public void myGetMonitorsData(String deviceName, String testName, String pathForCreatingMonitorData) {
	try{
		super.getMonitorsData(pathForCreatingMonitorData);
	} catch(Exception e)
	{
		super.collectSupportData(whereToSave(deviceName, testName), pathForFile ,deviceName,"tried to create csv file with data from monitor", "expected to create csv file", "getting data monitor has failed");
	} 
}






}

