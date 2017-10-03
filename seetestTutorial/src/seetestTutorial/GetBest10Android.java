package seetestTutorial;
import com.experitest.client.*;
import org.junit.*;
/**
 *
*/
public class GetBest10Android {
	private String host = "localhost";
	  private int port = 8889;
	  private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
	  protected MyClient client =null;
	  String testName = "GetBest10Android";
	  protected String deviceOS = "@os='android'";


	    
	    @Before
	    public void setUp(){
	  	  client = new MyClient(host, port, true);  
	  	  client.mySetProjectBaseDirectory(deviceOS, testName, projectBaseDirectory);
	  	  client.mySetReporter(deviceOS , testName, "xml", "reports", testName);
	    }

	    @Test
	    public void testGetBest10Android(){
	    	GetBest10ForAll runTheTest = new GetBest10ForAll(deviceOS, client);
	    	runTheTest.runTheTest();

	        
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
