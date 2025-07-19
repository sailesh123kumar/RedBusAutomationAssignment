package com.redbus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomationMain2 {
	
	
	
	public static void main(String[] args)  {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  //Synchronization
		driver.get("https://www.redbus.in/");
		
		clickSourceLocation(wait);
		waitTillSuggestionsReady(wait);
		selectLocation(driver, wait, "Chennai");
		selectLocation(driver, wait, "Bangalore");
		searchBus(wait);
		
		By liveTrackingButtonLocator = By.xpath("//div[contains(text(),'Live Tracking')]");
		WebElement liveTrackingButton = wait.until(ExpectedConditions.elementToBeClickable(liveTrackingButtonLocator));
		liveTrackingButton.click();
		
		By tuppleWrapperLocator = By.xpath("//li[contains(@class,'tupleWrapper')]");
		By busesNameLocator = By.xpath(".//div[contains(@class,\"travelsName\")]");
		wait.until(ExpectedConditions.visibilityOfElementLocated(tuppleWrapperLocator));
		
		By acButtonLocator = By.xpath("//div[starts-with(text(),'AC')]");
		WebElement acButton = wait.until(ExpectedConditions.elementToBeClickable(acButtonLocator));
		acButton.click();

		By subTitleLocator = By.xpath("//span[contains(@class,\"subtitle\")]");
		
		Boolean isBuses = wait.until(ExpectedConditions.textToBePresentInElementLocated(subTitleLocator,"buses"));
		WebElement subTitle = null;
		if (isBuses) {
			subTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(subTitleLocator));
		}
	
		System.out.println(subTitle.getText());
		
		JavascriptExecutor js = (JavascriptExecutor) driver ;
		
		while(true) { //Lazy Loading
			List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
			List<WebElement> enOflist = driver.findElements(By.xpath("//span[text()='End of list']"));
			
			if(enOflist.size()==1) {
				break;  //exit condition from the while loop
			}
			
			js.executeScript("arguments[0].scrollIntoView({behavior:'smooth'})", rowList.get(rowList.size()-3));
			
		}
		
		List<WebElement> rowList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tuppleWrapperLocator));
		System.out.println("Available buse for the given filters : "+rowList.size());
		for (WebElement row : rowList) {
			System.out.println(row.findElement(busesNameLocator).getText());
		}
	}
	
	
	
	//=======================================================//
	//=======================-METHODS-=======================//
	//=======================================================//
	

	private static void searchBus(WebDriverWait wait) {
		By searchBusesLocator = By.xpath("//button[.='Search buses']");
		WebElement searchBuses = wait.until(ExpectedConditions.elementToBeClickable(searchBusesLocator));
		searchBuses.click();
	}

	private static void waitTillSuggestionsReady(WebDriverWait wait) {
		By searchSuggestionLocator = By.xpath("//div[contains(@class,\"searchSuggestionWrapper\")]");
		WebElement searchSoureSuggestionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionLocator));
	}

	private static void clickSourceLocation(WebDriverWait wait) {
		By sourceButtonLocator = By.xpath("//div[contains(@class,\"srcDestWrapper\") and @role=\"button\"]");
		WebElement sourceButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceButtonLocator));
		sourceButtonElement.click();
	}

	private static By selectLocation(WebDriver driver, WebDriverWait wait,String locationData) {
		WebElement searchTextBoxElement = driver.switchTo().activeElement();
		searchTextBoxElement.sendKeys(locationData);
		
		By searchcategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> searchcategoryList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchcategoryLocator, 2));
		
		WebElement locationSearchResult = searchcategoryList.get(0);
		//chianing of weblement
		By locationNamelocator = By.xpath(".//div[contains(@class,\"listHeader\")]");
		
		List<WebElement> locationNameList = locationSearchResult.findElements(locationNamelocator);
		
		for(WebElement ele:locationNameList) {
			String locationName = ele.getText();
			
			if(locationName.equals(locationData)) {
				ele.click();
				break;
			}
		}
		return searchcategoryLocator;
	}

}
