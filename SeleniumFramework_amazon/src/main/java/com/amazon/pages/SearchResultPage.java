package com.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.framework.reUsableComponents.TestNgMethods;

public class SearchResultPage extends TestNgMethods{
	
	public SearchResultPage() {		
		PageFactory.initElements(driver,this);
	}	

	@FindBy(how=How.XPATH,using="//div[contains(@class,'search-results')]/div[1]//h2/a")
	private WebElement eleFirstProduct;
	
	public ProductPage clickFirstProduct() {
		click(eleFirstProduct);
		return new ProductPage();
		}
}
