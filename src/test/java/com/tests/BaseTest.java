package com.tests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
protected WebDriver driver;
	//Changing the below code to adapt for Selenium Grid
	/*
	@BeforeTest
	public void setupDriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\Selenium drivers\\Chrome\\chromedriver.exe");
		this.driver = new ChromeDriver();
	}
	*/

	@BeforeTest
	public void setupDriver() throws MalformedURLException {
		//Environment Variables - BROWSER and HUB_HOST
		//BROWSER => chrome/firefox
		//HUB_HOST => localhost/ <any ip adresss>/ hostname
		
		//Declaring default values if no values are given to the environment variables
		String host = "localhost";
		DesiredCapabilities dc;
		
		//Checking for environment variables, if values are provided override them
		if(System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("firefox")) {
			dc = DesiredCapabilities.firefox();
		}else {
			dc = DesiredCapabilities.chrome();
		}
		
		if(System.getProperty("HUB_HOST") != null) {
			host = System.getProperty("HUB_HOST");
		}
		
		//Hub URL
		String completeURL ="http://" +host+ ":4444/wd/hub";
		//driver refers to instance of RemoteWebDriver now, which takes 2 params URL and dc
		
		this.driver = new RemoteWebDriver(new URL(completeURL), dc);
	}


	@AfterTest
	public void quitBrowser() {
		this.driver.quit();
	}
}
