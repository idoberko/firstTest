package seetestTutorial;

//package <set your test package>;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;

public class EriBankCSVAndroid {
	private String host = "localhost";
	  private int port = 8889;
	  private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
	  protected MyClient client =null;
	  protected String deviceOS = "@os='android'";

	  

	  @Before
	  public void setUp(){
		  client = new MyClient(host, port, true);  
	      client.mySetProjectBaseDirectory("@os='android'", "EriBankCSVAndroid", projectBaseDirectory);
	      client.mySetReporter("@os='android", "EriBankCSVAndroid", "xml", "reports", "EriBankCSVAndroid");
	      
	  }

	  @Test
	  public void testEriBankCSVAndroid(){
		  EriBankCSVForAll runTheTest = new EriBankCSVForAll(deviceOS, client);
		  runTheTest.RunTheTest();
		  
	  }
	  
	  @After
	  public void tearDown(){
	      // Generates a report of the test case.
	      // For more information - https://docs.experitest.com/display/public/SA/Report+Of+Executed+Test
	      client.generateReport(false);
	      // Releases the client so that other clients can approach the agent in the near future. 
	      client.releaseClient();
	      
	  }
	  
	  
}
