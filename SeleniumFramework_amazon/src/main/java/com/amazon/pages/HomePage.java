package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.reUsableComponents.TestNgMethods;

public class HomePage extends TestNgMethods{
	
	public HomePage() {		
		PageFactory.initElements(driver,this);
	}	

	@FindBy(how=How.XPATH,using="//a[@aria-label='Amazon']")
	private WebElement eleHeaderName;
	
	public HomePage verifyHeaderName() {
	eleHeaderName.isDisplayed();
	return this;		
	}
	
	@FindBy(how=How.ID,using="searchDropdownBox")
	private WebElement eleSearchDropdown;
	
	public HomePage selectSearchDropdown(String strData) {
		selectDropDownUsingText(eleSearchDropdown,strData);
		return this;		
		}
	@FindBy(how=How.ID,using="twotabsearchtextbox")
	private WebElement eleSearchTextbox;
	
	public HomePage setSearchText(String strData) {
		setText(eleSearchTextbox,strData);
		return this;		
		}
	@FindBy(how=How.XPATH,using="//input[@value='Go'][@type='submit']")
	private WebElement eleSearchLens;
	
	public SearchResultPage clickSearchLens() {
		click(eleSearchLens);
		return new SearchResultPage();
		}
}
