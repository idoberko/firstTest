package seetestTutorial;
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.*;
import java.io.File;
import java.io.IOException;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;
/**
 *
*/
public class CNNSecondTestIPhone {
    private String host = "localhost";
    private int port = 8889;
    private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
    protected MyClient client =null;
    protected String testName = "CNNSecondTestIPhone";
    protected String deviceOS = MyRunnable.deviceOS;



    @Before
    public void setUp(){
    	client =  MyRunnable.client;  
  	  	client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
  	  	client.mySetReporter(deviceOS , testName, "xml", "reports", testName);
    }

    @Test
    public void testcnnSecondIPhone(){
        // it is recommended to start your script with SetDevice command:
        // client.setDevice( <Device Name>);
        
    	CNNSecondTestForAll runTheTest = new CNNSecondTestForAll(deviceOS, client);
    	runTheTest.runTheTest();
   	  	
    	
    }

    @After
    public void tearDown(){
        // Generates a report of the test case.
        // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
        client.generateReport(false);
        // Releases the client so that other clients can approach the agent in the near future. 
      //  client.releaseClient();
    }
    
    
}
