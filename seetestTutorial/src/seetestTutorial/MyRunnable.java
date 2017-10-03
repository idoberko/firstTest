package seetestTutorial;

import java.io.File;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

public class MyRunnable implements Runnable {

	protected int whichTestToRun;
	public static String getTime =  String.valueOf(System.currentTimeMillis());
	public String deviceType;
	//android test class:
	private static Class CNNTextAndroid = CNNTextAndroid.class;
	private static Class CNNSecondTestAndroid = CNNSecondTestAndroid.class;
	private static Class EriBankCSVAndroid = EriBankCSVAndroid.class;
	private static Class MakePaymentAndroid = MakePaymentAndroid.class;
	private static Class DownloadAppAndroid = DownloadAppAndroid.class;
	private static Class GetBest10Android = GetBest10Android.class;
	private static Class AllAndroidTests = AllAndroidTests.class;

	private static Class CNNTextIPhone = CNNTextIPhone.class;
	private static Class CNNSecondTestIPhone = CNNSecondTestIPhone.class;
	private static Class EriBankCSVIPhone = EriBankCSVIPhone.class;
	private static Class MakePaymentIPhone = MakePaymentIPhone.class;
	private static Class DownloadAppIPhone = DownloadAppIPhone.class;
	private static Class GetBest10IPhone = GetBest10IPhone.class;
	private static Class AlliPhoneTests = AlliPhoneTests.class;

	public static boolean isRunAllTests = false;
	String workingDirectory = "C:\\Users\\ido.berkovitz\\workspace\\project2";
	String filename = "newFile.txt";
	private static String host = "localhost";
    private static int port = 8889;
    
	protected static String pathToFile = "temp";
    protected static MyClient client = new MyClient(host, port, true);
	protected static String deviceOS = "temp";
	protected static String deviceName = "temp";
	
	
	
	
	//public static String fileForSavedLogs;
	 
	
	public MyRunnable(int whichTestToRun, String deviceType) {
		this.whichTestToRun = whichTestToRun;
		this.deviceType = deviceType;
	}
	
	public void run() {
		if(deviceType.equals("android")) {
			setDeviceOS("@os='android'");
			pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
			new File(pathToFile).mkdirs();

			switch (whichTestToRun) {
			case 1: {
				chooseDevice("CNNTextAndroid");
				Result result1 = JUnitCore.runClasses(CNNTextAndroid);
				client.releaseDeviceIfNotSuite(deviceName, "CNNTextAndroid", deviceOS);
                break;
			}
			case 2:{
				chooseDevice("CNNSecondTestAndroid");
				Result result2 = JUnitCore.runClasses(CNNSecondTestAndroid);
				client.releaseDeviceIfNotSuite(deviceName, "CNNSecondTestAndroid", deviceOS);
                break;
			}
			case 3:{
				chooseDevice("EriBankCSVAndroid");
				Result result3 = JUnitCore.runClasses(EriBankCSVAndroid);
				client.releaseDeviceIfNotSuite(deviceName, "EriBankCSVAndroid", deviceOS);
                break;

			}
			case 4:{
				chooseDevice("MakePaymentAndroid");
				Result result4 = JUnitCore.runClasses(MakePaymentAndroid);
				client.releaseDeviceIfNotSuite(deviceName, "MakePaymentAndroid", deviceOS);
                break;

			}
			case 5:{
				chooseDevice("DownloadAppAndroid");
				Result result5 = JUnitCore.runClasses(DownloadAppAndroid);
                client.releaseDeviceIfNotSuite(deviceName, "DownloadAppAndroid", deviceOS);
                break;

			}
			case 6:{
				chooseDevice("GetBest10Android");
				Result result6 = JUnitCore.runClasses(GetBest10Android);
                client.releaseDeviceIfNotSuite(deviceName, "GetBest10Android", deviceOS);
				break;
			}
			case 7:{
				chooseDevice("AllAndroidTest");
				MyRunnable.setVariable(true);
				Result result7 = JUnitCore.runClasses(AllAndroidTests);
                client.releaseDeviceIfNotSuite(deviceName, "AllAndroidTest", deviceOS);
				break;

			}
			default:
				
			}
		}
		else { //iPhone
			deviceOS = "@os='ios'";
			pathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
		    new File(pathToFile).mkdirs();
			switch (whichTestToRun) {
			case 1: {
				chooseDevice("CNNTextIPhone");
				Result result1 = JUnitCore.runClasses(CNNTextIPhone);
				client.releaseDeviceIfNotSuite(deviceName, "CNNTextIPhone", deviceOS);
                break;
			}
			case 2:{
				chooseDevice("CNNSecondTestIPhone");
				Result result2 = JUnitCore.runClasses(CNNSecondTestIPhone);
				client.releaseDeviceIfNotSuite(deviceName, "CNNSecondTestIPhone", deviceOS);
                break;
			}
			case 3:{
				chooseDevice("EriBankCSVIPhone");
				Result result3 = JUnitCore.runClasses(EriBankCSVIPhone);
				client.releaseDeviceIfNotSuite(deviceName, "EriBankCSVIPhone", deviceOS);
                break;

			}
			case 4:{
				chooseDevice("MakePaymentIPhone");
				Result result4 = JUnitCore.runClasses(MakePaymentIPhone);
				client.releaseDeviceIfNotSuite(deviceName, "MakePaymentIPhone", deviceOS);
                break;

			}
			case 5:{
				chooseDevice("DownloadAppIPhone");
				Result result5 = JUnitCore.runClasses(DownloadAppIPhone);
                client.releaseDeviceIfNotSuite(deviceName, "DownloadAppIPhone", deviceOS);
                break;

			}
			case 6:{
				chooseDevice("GetBest10IPhone");
				Result result6 = JUnitCore.runClasses(GetBest10IPhone);
                client.releaseDeviceIfNotSuite(deviceName, "GetBest10IPhone", deviceOS);
				break;
			}
			case 7:{
				chooseDevice("AlliPhoneTests");
				MyRunnable.setVariable(true);
				Result result7 = JUnitCore.runClasses(AlliPhoneTests);
				client.releaseDeviceIfNotSuite(deviceName, "AlliPhoneTests", deviceOS);
				break;

			}
			default:
				
			}
		}
		
	}
	
	public void creatingPathForFile(String deviceOS) {
		String tempPathToFile = "failed";
		if(deviceOS.contains("android")) {
			tempPathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "android folders";
				  new File(pathToFile).mkdirs();
		}
		else {
			tempPathToFile = "C:\\Users\\ido.berkovitz\\workspace\\project2\\Run_" + MyRunnable.getTime + File.separator + "iPhone folders";
				  new File(pathToFile).mkdirs();
			}
		      
		setPathToFile(tempPathToFile);
	}

	public void chooseDevice(String testName) {
		String tempDeviceName = client.myWaitForDevice(testName, deviceOS, 30000);
		if(tempDeviceName.isEmpty()) {
			setDeviceName("failed");
		}
		else {
			setDeviceName(tempDeviceName);
		}
	}
	
	public static void setVariable(boolean s)
	{
		isRunAllTests = s;
	}
	
	public static void setDeviceName(String s)
	{
		deviceName = s;
	}
	
	public static void setDeviceOS(String s)
	{
		deviceOS = s;
	}
	
	public static void setPathToFile(String s)
	{
		pathToFile = s;
	}
}
