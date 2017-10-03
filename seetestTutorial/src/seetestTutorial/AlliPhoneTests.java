package seetestTutorial;

import java.io.File;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CNNTextIPhone.class, CNNSecondTestIPhone.class, EriBankCSVIPhone.class,
				MakePaymentIPhone.class, DownloadAppIPhone.class, GetBest10IPhone.class })
public class AlliPhoneTests {
	public AlliPhoneTests() {
		String pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
		  if (!(MyRunnable.isRunAllTests)) {
			  new File(pathToFile).mkdirs();
			  
			}
	}
	
 }
