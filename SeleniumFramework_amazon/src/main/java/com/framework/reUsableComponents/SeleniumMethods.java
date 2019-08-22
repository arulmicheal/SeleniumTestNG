package com.framework.reUsableComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.utils.Reporter;

public class SeleniumMethods extends Reporter implements Selenium_Interface{

	public static WebDriver driver;
	public String sUrl,strPlatform,strBrowser;
	public Properties prop;
	
	public SeleniumMethods() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File("./src/main/resources/config.properties")));
			sUrl = prop.getProperty("URL");
			strPlatform=prop.getProperty("platform");
			strBrowser=prop.getProperty("browser");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void launchApplication() throws Exception {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(strBrowser);
		
		//Sets the platform based on the input in properties file
		if (strPlatform.equalsIgnoreCase("windows")) {
			dc.setPlatform(Platform.WINDOWS);
		}else if(strPlatform.equalsIgnoreCase("mac")) {
			dc.setPlatform(Platform.MAC);
		}else {
			reportStep("Please provide valid platform!","Fail");
			throw new Exception("Please provide valid platform!");
		}
		try {
				//The driver will be assigned based on the input in properties file
				if(strBrowser.equalsIgnoreCase("chrome")){
					System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
					driver = new ChromeDriver();
				}else if(strBrowser.equalsIgnoreCase("firefox")) {
					System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
					driver = new FirefoxDriver();
				}else {
					reportStep("Please provide valid browser name!","Fail");
					throw new Exception("Please provide valid browser name!");
				}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.get(sUrl);
			reportStep("The browser: "+strBrowser+" launched successfully", "PASS");
		} catch (WebDriverException e) {			
			reportStep("The browser: "+strBrowser+" could not be launched", "FAIL");
		}
	}

	public WebElement locateElement(String locator, String locValue) {
		try {
			switch(locator) {
			case("id"): return driver.findElement(By.id(locValue));
			case("link"): return driver.findElement(By.linkText(locValue));
			case("xpath"):return driver.findElement(By.xpath(locValue));
			case("name"): return driver.findElement(By.name(locValue));
			case("class"): return driver.findElement(By.className(locValue));
			case("tag"):return driver.findElement(By.tagName(locValue));
			}
		} catch (NoSuchElementException e) {
			reportStep("The element with locator "+locator+" not found.","FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while finding "+locator+" with the value "+locValue, "FAIL");
		}
		return null;
	}

	public void setText(WebElement element, String data) {
		try {
			element.clear();
			element.sendKeys(data);
			reportStep("The data: "+data+" entered successfully in the field :"+element, "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The data: "+data+" could not be entered in the field :"+element,"FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while entering "+data+" in the field :"+element, "FAIL");
		}
	}

	public void click(WebElement element) {
		String text = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(element));			
			text = element.getText();
			element.click();
			reportStep("The element "+text+" is clicked", "PASS");
		} catch (InvalidElementStateException e) {
			reportStep("The element: "+text+" could not be clicked", "FAIL");
		} catch (WebDriverException e) {
			reportStep("Unknown exception occured while clicking in the field :", "FAIL");
		} 
	}

	public void selectDropDownUsingText(WebElement element, String value) {
		try {
			new Select(element).selectByVisibleText(value);
			reportStep("The dropdown is selected with text "+value,"PASS");
		} catch (WebDriverException e) {
			reportStep("The element: "+element+" could not be found.", "FAIL");
		}

	}

	public void verifySelected(WebElement element) {
		try {
			if(element.isSelected()) {
				reportStep("The element "+element+" is selected","PASS");
			} else {
				reportStep("The element "+element+" is not selected","FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		}
	}

	public void verifyDisplayed(WebElement element) {
		try {
			if(element.isDisplayed()) {
				reportStep("The element "+element+" is visible","PASS");
			} else {
				reportStep("The element "+element+" is not visible","FAIL");
			}
		} catch (WebDriverException e) {
			reportStep("WebDriverException : "+e.getMessage(), "FAIL");
		} 
	}

	public long takeScreenshot(){
		long number = (long) Math.floor(Math.random() * 900000000L) + 10000000L; 
		try {
			FileUtils.copyFile(((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE) , new File("./reports/images/"+number+".jpg"));
		} catch (WebDriverException e) {
			System.out.println("The browser has been closed.");
		} catch (IOException e) {
			System.out.println("The snapshot could not be taken");
		}
		return number;
	}

	public void closeBrowser() {
		try {
			driver.close();
			reportStep("The browser is closed","PASS", false);
		} catch (Exception e) {
			reportStep("The browser could not be closed","FAIL", false);
		}
	}

	public void closeAllBrowsers() {
		try {
			driver.quit();
			reportStep("The opened browsers are closed","PASS", false);
		} catch (Exception e) {
			reportStep("Unexpected error occured in Browser","FAIL", false);
		}
	}





}
