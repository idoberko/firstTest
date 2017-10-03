package seetestTutorial;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;

import org.junit.*;
/**
 *
*/
public class GetBest10IPhone {
	private String host = "localhost";
	private int port = 8889;
	private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
	protected MyClient client =null;
	String testName = "GetBest10IPhone";
	protected String deviceOS = MyRunnable.deviceOS;
	protected String deviceName = MyRunnable.deviceName;
	protected String whatWeFound[] = new String[10];


	    
    @Before
    public void setUp(){
    	client = MyRunnable.client;  
    	client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
    	client.mySetReporter(deviceOS , testName, "xml", "reports", testName);
    }

    @Test
    public void testGetBest10Android(){
    	//GetBest10ForAll runTheTest = new GetBest10ForAll(deviceOS, client);
    	getToTopFreeGamesiPhone();
        
    }
    
    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
	       // client.releaseClient();
    }
    
    public void getToTopFreeGamesiPhone() {
    	String pathToFile = "temp";
	    String pathForCreatingText = "temp";
    	pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
	      if (!(MyRunnable.isRunAllTests)) {
			  new File(pathToFile).mkdirs();
			  
			}
	      pathForCreatingText = pathToFile + File.separator  + "GetBest10iPhone.txt";
			
		  String pathForCreatingMonitorData =  pathToFile + File.separator  + "MonitorData_GetBest10.csv";

	      client.setMonitorPollingInterval(2000);

    	 client.launch("com.apple.AppStore", true, true);
    	 client.launch("com.apple.AppStore", true, true);
    	 while(!(client.waitForElement("NATIVE", "xpath=//*[@text='Top Charts' and @class='UIAButton']", 0, 10000))) {
         	
         }
    	 client.click("NATIVE", "xpath=//*[@text='Top Charts' and @class='UIAButton']", 0, 1);
        if(client.waitForElement("NATIVE", "xpath=//*[@class='UIACollectionView']", 0, 30000)){
            // If statement
        }
        client.click("NATIVE", "xpath=//*[@text='Categories' and ./parent::*[@text='Top Charts']]", 0, 1);
        if(client.waitForElement("NATIVE", "xpath=//*[@text='Games']", 0, 10000)){
            // If statement
        }
        client.click("NATIVE", "xpath=//*[@text='Games']", 0, 1);
        client.click("NATIVE", "xpath=//*[@text='All Games']", 0, 1);
        while(!(client.waitForElement("NATIVE", "xpath=//*[@text='Free']", 0, 10000))) {
         	
        }
        String textWeGot_current = "temp";
        String textWeGot_next = "temp";
        String currentXPath = "temp";
        String nextXPath = "temp";
        String[] screenSizeString = client.myGetDeviceProperty(deviceName, testName, "device.screensize").split("x");
        int screenSize = Integer.parseInt(screenSizeString[1]);
        int i = 0;
        client.myClick(deviceName, testName, "NATIVE", "xpath=//*[@text='Free']", 0, 1);
        int doWeNeedToStop = 0;
        while (doWeNeedToStop < 9) {
        	currentXPath =  "xpath=(//*[@class='UIACollectionView']/*/*/*/*[@class='UIAView' and @height>0])["+ Integer.toString(i+1) +"]";
        	nextXPath =  "xpath=(//*[@class='UIACollectionView']/*/*/*/*[@class='UIAView' and @height>0])["+ Integer.toString(i+2) +"]";
        	if(client.isElementFound("NATIVE", currentXPath, 0)){
        		 textWeGot_current = client.myGetTextIn(deviceName, testName, "NATIVE", currentXPath, 0, "NATIVE", "Left", 0, 0, 0, 0);
             }

        	
        	String temptext[] = textWeGot_current.split(",");
        	doWeNeedToStop = Integer.parseInt(temptext[0])-1;
            whatWeFound[doWeNeedToStop] = temptext[1];
            if(client.isElementFound("NATIVE", nextXPath, 0)){
            	textWeGot_next = client.myGetTextIn(deviceName, testName, "NATIVE", nextXPath, 0, "NATIVE", "Left", 0, 0, 0, 0);
            }
            
            if(textWeGot_next.contains("Categories")){
                client.mySwipe(deviceName, testName, "Down", screenSize/2, 20);
                if(client.isElementFound("NATIVE", currentXPath, 0)){
           		 textWeGot_current = client.myGetTextIn(deviceName, testName, "NATIVE", currentXPath, 0, "NATIVE", "Left", 0, 0, 0, 0);
                }
                if(textWeGot_current.contains("SIM") || textWeGot_current.contains("Categories")) {
                	textWeGot_current = client.myGetTextIn(deviceName, testName, "NATIVE", "xpath=(//*[@class='UIACollectionView']/*/*/*/*[@class='UIAView' and @height>0])[2]", 0, "NATIVE", "Left", 0, 0, 0, 0);
                }
            	temptext = textWeGot_current.split(",");
                i = i-Integer.parseInt(temptext[0]);
                if(i<=0) {
                	i=1;
                }
            }
            i++;

        }
        
        try {
			MyClient.saveBest10ToFile(pathForCreatingText, whatWeFound);
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
        client.myGetMonitorsData(deviceName, testName, pathForCreatingMonitorData);

        
        
        

    }
}
