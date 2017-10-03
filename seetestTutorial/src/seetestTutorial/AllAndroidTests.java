package seetestTutorial;
import java.io.File;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({ CNNTextAndroid.class, CNNSecondTestAndroid.class, EriBankCSVAndroid.class,
				MakePaymentAndroid.class, DownloadAppAndroid.class, GetBest10Android.class})

public class AllAndroidTests {

}
