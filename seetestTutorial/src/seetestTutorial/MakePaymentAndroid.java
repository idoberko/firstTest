package seetestTutorial;

//package <set your test package>;
import com.experitest.client.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.*;
import seetestTutorial.Main;
import com.experitest.client.Client;

public class MakePaymentAndroid {
	private String host = "localhost";
	  private int port = 8889;
	  private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
	  protected MyClient client =null;
	  
	  

	  

	  @Before
	  public void setUp(){
		  client = new MyClient(host, port, true);  
	      client.mySetProjectBaseDirectory("@os='android'", "MakePaymentAndroid", projectBaseDirectory);
	      client.mySetReporter("@os='android", "MakePaymentAndroid", "xml", "reports", "MakePaymentAndroid");
	      
	  }
	  
	  
	  @Test
	  public void testMakePaymentAndroid(){
		  MakePaymentForAll runTheTest = new MakePaymentForAll("@os='android'", client);
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
