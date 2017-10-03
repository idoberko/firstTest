package seetestTutorial;
//package <set your test package>;
import com.experitest.client.*;
import org.junit.*;
/**
*
*/
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
public class Main {
	public static void main(String args[]) {
        MyRunnable myRunnable2 = new MyRunnable(1, "iphone"); //android or iphone
        Thread t2 = new Thread(myRunnable2);
        t2.start();
        /*MyRunnable myRunnable1 = new MyRunnable(1);
        Thread t1 = new Thread(myRunnable1);
        t1.start();*/
        
    }  
	/*private static Class CNNSecondTest = CNNSecondTest.class;
	private static Class CNNTextAndroid = CNNTextAndroid.class;

	//first line resultsOfFirstTestAndroid_ButtonNotFound[] = new int[13];
	//second line resultsOfFirstTestAndroid_TextNotEqual[] = new int[13];
	public static int resultsOfFirstTestAndroid[][] = new int[2][13];
	public static int resultsOfSecondTestAndroid[][] = new int[2][13];
	
	public static void main(String[] args) 
	{
		
//		JUnitCore junit = new JUnitCore();
//		Result result = junit.run(CNNSecondTest);
		Result result1 = JUnitCore.runClasses(CNNTextAndroid);
		System.out.println(resultsOfFirstTestAndroid.toString());
		Result result2 = JUnitCore.runClasses(CNNSecondTest);
		System.out.println(resultsOfSecondTestAndroid.toString());
	}
	
	
	//	CNNSecondTest cnnSecondTest = new CNNSecondTest();
//	CNNSecondTest cnnSecondTest = new CNNSecondTest();
//	resultsOfSecondTest = CNNSecondTest.testCNNSecondTest(resultsOfSecondTest);
	
	*/
	
	
}
