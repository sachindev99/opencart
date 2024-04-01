package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager; //log4j
import org.apache.logging.log4j.Logger; //log4j

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;
	public Properties p;  //to access properties file
	@BeforeClass(groups= {"sanity","regression","master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws InterruptedException, IOException {
		
		//loading properties file
		FileReader file = new FileReader(".//src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		
		//Loading log4j2.xml file
		//this -->current testCases class 
		//getClass gives the name of the testCases class at run time
		logger= LogManager.getLogger(this.getClass());
		if(br.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(br.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"sanity","regression","master"})
	public void tearDown() {
		driver.close();
	}
	
	//we need to add a dependencies in pom.xml ---> commons-lang3 in order to access RandomStringUtils class
		//it's not directly available in java
		public String randomString() {
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;
		}
		
		public String randomNumber() {
			String generatedString = RandomStringUtils.randomNumeric(10);
			return generatedString;
		}
		
		public String randomAlphaNUmeric() {
			String str = RandomStringUtils.randomAlphabetic(3);
			String num= RandomStringUtils.randomNumeric(3);
			return (str+"@"+num);
		}

		public String captureScreen(String tname) throws IOException{
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			
			TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
			File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+"\\screenshots" + tname+ "_"+timeStamp+".png";
			File targetFile = new File(targetFilePath);
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
			
		}
		
		

}
