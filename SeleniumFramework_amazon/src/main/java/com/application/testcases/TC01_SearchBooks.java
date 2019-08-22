package com.application.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.amazon.pages.HomePage;
import com.framework.reUsableComponents.TestNgMethods;


public class TC01_SearchBooks extends TestNgMethods{
	@BeforeTest
	public void setData() {
		testCaseName="TC01_SearchBooks";
		testDescription="Search Books in Amazon";
		testNodes="Search";
		category="Smoke";
		authors="Arul";
		dataSheetName="TC01";
	}
    @Test(dataProvider="fetchData")
	public void searchBooksAndPrintDetails(String strCategory,String strSearchData) {
		
		new HomePage().verifyHeaderName().selectSearchDropdown(strCategory).setSearchText(strSearchData).clickSearchLens().clickFirstProduct().printProductDetails();
		takeScreenshot();
				
	}

}
