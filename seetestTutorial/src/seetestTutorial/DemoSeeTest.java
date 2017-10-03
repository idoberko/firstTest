package seetestTutorial;

//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
*
*/
public class DemoSeeTest {
  private String host = "localhost";
  private int port = 8889;
  private String projectBaseDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
  protected Client client = null;

  @Before
  public void setUp(){
      client = new Client(host, port, true);
      client.setProjectBaseDirectory(projectBaseDirectory);
      client.setReporter("xml", "reports", "Untitled");
  }

  @Test
  public void testUntitled(){
      client.setDevice("adb:m2 note");
      client.launch("com.experitest.ExperiBank/.LoginActivity", true, true);
      client.sendText("company");
      client.elementSendText("NATIVE", "hint=Password", 0, "company");
      if(client.waitForElement("NATIVE", "partial_text=Login", 0, 10000)){
          // If statement
      }
      client.click("NATIVE", "text=Login", 0, 1);
      if(client.waitForElement("NATIVE", "partial_text=Logout", 0, 10000)){
          // If statement
      }
      String str0 = client.getTextIn("default", "Make Payment", 0, "TEXT", "Up", 0, 125);
      client.click("NATIVE", "text=Logout", 0, 1);
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
