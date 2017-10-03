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
public class CNNTextForAll {

	protected MyClient client =null;
	private String deviceOS;
	protected int whereToSave = 0;
	protected String deviceName = "temp";
	protected int resultsOfCNNTextTestIPhone[][] = new int[2][13];
	protected String whatWeWhereLookingFor[] = new String[13];
	protected String whatWeFound[] = new String[13];
	protected String testName = "CNNText";
	public CNNTextForAll(String deviceOS, MyClient client) {
		this.deviceOS = deviceOS;
		this.client = client;
	};
	
	public void runTheTest() {
		client.startTransaction("CNNNTextTry_1");
		
		
		  //client = new MyClient(host, port, true);  
		  //client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
		 // client.mySetReporter(deviceOS, testName, "xml", "reports", "MakePaymentAndroid");
	
	
	//create text file
		String pathToFile = "temp";
	    String pathForCreatingText = "temp";
	    String textForLaunch = "temp";
	    String textForMenu = "temp";
	    if(deviceOS.contains("android")) {
		  pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
	      new File(pathToFile).mkdirs();
	      pathForCreatingText = pathToFile + File.separator  + "CNNTextAndroid.txt";
	      testName = "CNNTextAndroid";
	      textForLaunch = "chrome:http://edition.cnn.com";
	      textForMenu = "id=menu";
		}
		else {
	      pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
	      new File(pathToFile).mkdirs();
	      pathForCreatingText = pathToFile + File.separator  + "CNNTextiPhone.txt";
	      testName = "CNNTextiPhone";
	      textForLaunch = "safari:edition.cnn.com";
	      textForMenu = "xpath=//div[@class=\"nav-menu__hamburger\"]";
		}
	    String pathForCreatingMonitorData =  pathToFile + File.separator  + "MonitorData_CNNTextiPhone.csv";

		
		deviceName = MyRunnable.deviceName;
		if(deviceName.isEmpty()) {
			return;
		}
		client.myStartMonitor(deviceName, testName, "com.apple.mobilesafari");
		client.setMonitorPollingInterval(10000);
		
	    //client.mySetDevice(deviceName, testName);
	    //client.myLaunch(deviceName, testName, textForLaunch, true, false);
		client.launch("safari:edition.cnn.com", true, true);

	    try{client.hybridWaitForPageLoad(20000);} catch(Exception e) {};
		client.myClick(deviceName, testName, "WEB",textForMenu , 0, 1);

	    
	    clickNVarify("WEB", "text=Regions", "Regions");
	    clickNVarify("WEB",  "text=U.S. Politics", "U.S. Politics");
	    clickNVarifySpecial("css=IMG.nav-menu__img-money", "Money", "css=IMG.nav-menu__img-money" , "Inside", 0); //?
	    clickNVarifySpecial("css=IMG.nav-menu__img-entertainment", "entertainment", "css=IMG.nav-menu__img-entertainment", "Inside", 0); //?
	    clickNVarifySpecial("css=IMG.nav-menu__img-tech", "tech", "text=Sport", "Up", 150); //?
	    clickNVarify("WEB", "text=Sport", "Sport");
	    clickNVarifySpecial("css=A.nav-flyout__section-title[href='/travel']", "travel", "text=Sport", "Down", 150);
	    clickNVarifySpecial("xpath=(//*[@id='nav-expanded-menu']/*/*/*[@nodeName='IMG' and ./parent::*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV']]])[5]", "style", "css=A.nav-flyout__section-title[href='/travel']", "Down", 150);
	    clickNVarify("WEB", "text=Health", "Health");
	    clickNVarify("WEB", "text=Features", "Features");
	    clickNVarify("WEB", "text=Video", "Video");
	    clickNVarifySpecial("css=IMG.nav-menu__img-vr360", "VR", "text=More…", "Up", 150); //?
	    clickNVarify("WEB", "text=More…", "More");
	    
	    
	    
	    try {
			MyClient.saveArrayToFile(pathForCreatingText, resultsOfCNNTextTestIPhone, whatWeWhereLookingFor, whatWeFound);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	   /* if (!(MyRunnable.isRunAllTests)) {
            client.releaseDeviceIfNotSuite(deviceName, testName, deviceOS);
        }*/
       client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);
		client.endTransaction("CNNNTextTry_1");


	}
	
	protected void clickNVarify(String zoneForMenu, String elementForMenu, String toCompare) {
        if(client.myIsElementFound(deviceName, testName, zoneForMenu, elementForMenu, 0)){
    		resultsOfCNNTextTestIPhone[0][whereToSave] = 1;
    		client.textFilter("0xFFFFFF", 50);
    		String str0 = client.myelementGetText(deviceName, testName, "WEB", elementForMenu, 0);
      	  int i = 0;
      	  while(!str0.contains(toCompare)  && i < 10) {
      		client.textFilter("0xFFFFFF", 50);
          	  str0 = client.myelementGetText(deviceName, testName, zoneForMenu, elementForMenu, 0);
          	  i++;
      	  }
      	whatWeWhereLookingFor[whereToSave] = toCompare;
      	whatWeFound[whereToSave] = str0;
      	  if(i == 10) {
      		resultsOfCNNTextTestIPhone[1][whereToSave] = 0;
      	  }
      	  else
      		resultsOfCNNTextTestIPhone[1][whereToSave] = 1;
        }
        else {
        	resultsOfCNNTextTestIPhone[0][whereToSave] = 0;
        }
        whereToSave++;
    
    }
    
    protected void clickNVarifySpecial(String elementForMenu, String toCompare, String fromWhereToSearch, String direction, int howMuch) {
    	if(client.myIsElementFound(deviceName, testName,"WEB", elementForMenu, 0)){
            // If statement
    		resultsOfCNNTextTestIPhone[0][whereToSave] = 1;
    		client.textFilter("0xFFFFFF", 50);
  		  String str4 = client.myGetTextIn(deviceName, testName, "WEB", fromWhereToSearch, 0, "TEXT", direction, 0, howMuch);
      	  int i = 0;
      	  while(!str4.contains(toCompare) && i < 10) {
      		client.textFilter("0xFFFFFF", 50);
      		  str4 = client.myGetTextIn(deviceName, testName, "WEB", fromWhereToSearch, 0, "TEXT", direction, 0, 150);   
      		  i++;
      	  }
      	whatWeWhereLookingFor[whereToSave] = toCompare;
      	whatWeFound[whereToSave] = str4;
      	  if(i == 10) {
      		resultsOfCNNTextTestIPhone[1][whereToSave] = 0;
      	  }
      	  else
      		resultsOfCNNTextTestIPhone[1][whereToSave] = 1;
        }
        else {
        	resultsOfCNNTextTestIPhone[0][whereToSave] = 0;
        }
        whereToSave++;
    
    }
    
}
