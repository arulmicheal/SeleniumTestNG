package com.framework.reUsableComponents;

import java.net.MalformedURLException;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public interface Selenium_Interface {
		
	
		/**
		 * This method will launch the chrome/firefox browser and 
		 * maximise the browser and set the wait for 10 seconds 
		 * and load the url
		 * @throws MalformedURLException 
		 * @throws Exception 
		 */
		public void launchApplication() throws MalformedURLException, Exception ;

		/**
		 * This method will locate the element using any given locator
		 * @param locator  - The locator by which the element to be found
		 * @param locValue - The locator value by which the element to be found
		 
		 * @throws NoSuchElementException
		 */
		public WebElement locateElement(String locator, String locValue) ;	
		
		/**
		 * This method will enter the value in the given text field 
		 * @param element   - The Webelement (text field) in which the data to be entered
		 * @param data  - The data to be sent to the webelement
		 
		 * @throws ElementNotVisibleException		 * 
		 */
		public void setText(WebElement element, String data) ;
		
		/**
		 * This method will click the element and take snap
		 * @param element   - The Webelement (button/link/element) to be clicked
		 
		 */
		public void click(WebElement element);


		/**
		 * This method will select the drop down visible text
		 * @param ele   - The Webelement (dropdown) to be selected
		 * @param value The value to be selected (visibletext) from the dropdown 
		 
		 */
		public void selectDropDownUsingText(WebElement element, String value) ;
		
		/**
		 * This method will verify if the element (Radio button, Checkbox)  is selected
		 * @param element   - The Webelement (Radio button, Checkbox) to be verified
		
		 */
		public void verifySelected(WebElement element);
		
		/**
		 * This method will verify if the element is visible in the DOM
		 * @param element   - The Webelement to be checked
		 
		 */
		public void verifyDisplayed(WebElement ele);
		
		/**
		 * This method will take snapshot of the browser
		
		 */
		public long takeScreenshot();
			
		/**
		 * This method will close the active browser
		 
		 */
		public void closeBrowser();		
		
		/**
		 * This method will close all the browsers
		
		 */
		public void closeAllBrowsers();

}



