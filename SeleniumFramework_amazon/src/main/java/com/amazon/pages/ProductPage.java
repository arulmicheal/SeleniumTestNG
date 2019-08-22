package com.amazon.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.framework.reUsableComponents.TestNgMethods;

public class ProductPage extends TestNgMethods{
	
	public ProductPage() {
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(how=How.ID,using="ebooksProductTitle")
	private WebElement eleProductTitle;
	
	public ProductPage printProductTitle() {
		reportStep("Product name is \""+eleProductTitle.getText().trim()+"\"", "INFO");
		return this;		
		}
	
	@FindBy(how=How.XPATH,using="//div[@id='formats']//ul/li//a[@role='button']/span[1]")
	private List<WebElement> listOfNames;
	@FindBy(how=How.XPATH,using="//div[@id='formats']//ul/li//a[@role='button']//span[contains(@class,'color')]/span")
	private List<WebElement> listOfPrice;
	
	public ProductPage printProductDetails() {
		printProductTitle();
		int iCount=0;
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='formats']//ul/li//a[@role='button']/span[1]")));	
		for(WebElement eachelement:listOfNames)
		{
			reportStep("Product edition \""+eachelement.getText().trim()+"\" price is : "+listOfPrice.get(iCount).getText().trim(), "INFO");
			iCount++;
		}
		return this;		
		}
	

}
