package seetestTutorial;
import java.io.File;
import java.io.IOException;

import org.junit.*;
import seetestTutorial.MyRunnable;

/**
 *
*/
public class CNNTextIPhone {
	
	private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
    protected MyClient client =null;
    protected String testName = "CNNTextIPhone";
    protected String deviceOS = MyRunnable.deviceOS;


    @Before
    public void setUp(){
    	client = MyRunnable.client;  
	    client.mySetProjectBaseDirectory(deviceOS, "CNNTextIPhone", projectBaseDirectory);
	    client.mySetReporter(deviceOS, "CNNTextIPhone", "xml", "reports", "CNNTextIPhone");
    }

    @Test
    public void testcnnTextIPhone(){
    	CNNTextForAll runTheTest = new CNNTextForAll(deviceOS, client);
		runTheTest.runTheTest();
    	/*deviceName = client.getDiviceName(testName);
        client.mySetDevice(deviceName, testName);
        client.launch("safari:edition.cnn.com", true, false);
        try{client.hybridWaitForPageLoad(20000);} catch(Exception e) {};
    	client.click("WEB", "xpath=//div[@class=\"nav-menu__hamburger\"]", 0, 1);

        
        clickNVarify("WEB", "text=Regions", "Regions");
        clickNVarify("WEB",  "text=U.S. Politics", "U.S. Politics");
        clickNVarifySpecial("css=IMG.nav-menu__img-money", "Money", "css=IMG.nav-menu__img-money" , "Inside", 0); //?
        clickNVarifySpecial("css=IMG.nav-menu__img-entertainment", "entertainment", "css=IMG.nav-menu__img-entertainment", "Inside", 0); //?
        clickNVarifySpecial("css=IMG.nav-menu__img-tech", "tech", "text=Sport", "Up", 150); //?
        clickNVarify("WEB", "text=Sport", "Sport");
        clickNVarifySpecial("css=A.nav-flyout__section-title[href='/travel']", "travel", "text=Sport", "Down", 150);
        clickNVarifySpecial("xpath=(//*[@id='nav-expanded-menu']/*//*[@nodeName='IMG' and ./parent::*[@nodeName='A' and @width>0 and ./parent::*[@nodeName='DIV']]])[5]", "STYLE", "css=A.nav-flyout__section-title[href='/travel']", "Down", 150);
        clickNVarify("WEB", "text=Health", "Health");
        clickNVarify("WEB", "text=Features", "Features");
        clickNVarify("WEB", "text=Video", "Video");
        clickNVarifySpecial("css=IMG.nav-menu__img-vr360", "VR", "text=More…", "Up", 150); //?
        clickNVarify("WEB", "text=More…", "More");
        
        String pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
        new File(pathToFile).mkdirs();
        String pathForSavingLogs = pathToFile + File.separator  + "CNNTextiPhone.TXT";
        
        try {
			MyClient.saveArrayToFile(pathForSavingLogs, resultsOfCNNTextTestIPhone);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
    }
    
    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
     //   client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
    }
    
    /*protected void clickNVarify(String zoneForMenu, String elementForMenu, String toCompare) {
        if(client.myIsElementFound(deviceName, testName, zoneForMenu, elementForMenu, 0)){
    		resultsOfCNNTextTestIPhone[0][whereToSave] = 1;
    		String str0 = client.myelementGetText(deviceName, testName, "WEB", elementForMenu, 0);
      	  int i = 0;
      	  while(!str0.contains(toCompare)  && i < 10) {
          	  str0 = client.myelementGetText(deviceName, testName, zoneForMenu, elementForMenu, 0);
          	  i++;
      	  }
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
  		  String str4 = client.myGetTextIn(deviceName, testName, "WEB", fromWhereToSearch, 0, "TEXT", direction, 0, howMuch);
      	  int i = 0;
      	  while(!str4.contains(toCompare) && i < 10) {
      		  str4 = client.myGetTextIn(deviceName, testName, "WEB", fromWhereToSearch, 0, "TEXT", direction, 0, 150);   
      		  i++;
      	  }
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
    
    */
}
