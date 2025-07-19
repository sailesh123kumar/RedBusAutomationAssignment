package com.redbus;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RedBusAutomationMain {
	
	
	
	public static void main(String[] args) throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));  //Synchronization
		driver.get("https://www.redbus.in/");
		
		By sourceButtonLocator = By.xpath("//div[contains(@class,\"srcDestWrapper\") and @role=\"button\"]");
		WebElement sourceButtonElement = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceButtonLocator));
		sourceButtonElement.click();
		
		By searchSuggestionLocator = By.xpath("//div[contains(@class,\"searchSuggestionWrapper\")]");
		WebElement searchSoureSuggestionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(searchSuggestionLocator));
		
		WebElement searchTextBoxElement = driver.switchTo().activeElement();
		searchTextBoxElement.sendKeys("Chennai");
		
		By searchcategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> searchcategoryList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchcategoryLocator, 2));
		System.out.println("Search Suggesion category Size : "+searchcategoryList.size());
		
		WebElement locationSearchResult = searchcategoryList.get(0);
		//chianing of weblement
		By locationNamelocator = By.xpath(".//div[contains(@class,\"listHeader\")]");
		
		
		List<WebElement> locationNameList = locationSearchResult.findElements(locationNamelocator);
		System.out.println("Location List Size : "+locationNameList.size());
		
		for(WebElement ele:locationNameList) {
			String locationName = ele.getText();
			
			if(locationName.equals("Chennai")) {
				ele.click();
				break;
			}
		}
		
		
		WebElement searchDestinationTextBoxElement = driver.switchTo().activeElement();
		searchDestinationTextBoxElement.sendKeys("Bangalore");
		
		By toSearchcategoryLocator = By.xpath("//div[contains(@class,'searchCategory')]");
		List<WebElement> toSearchcategoryList = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchcategoryLocator, 2));
		System.out.println("Search Suggesion category Size : "+searchcategoryList.size());
		
		WebElement toLocation = toSearchcategoryList.get(0);
		By toLocationNamelocator = By.xpath(".//div[contains(@class,\"listHeader\")]");
		List<WebElement> destlocationsList = toLocation.findElements(toLocationNamelocator);
		


		for (WebElement ele : destlocationsList) {
			
			String destname = ele.getText();
			
			if(destname.equals("Bangalore")) {
				ele.click();
				break;
			}
			
		}
		
		
		By searchBusesLocator = By.xpath("//button[.='Search buses']");
		WebElement searchBuses = driver.findElement(searchBusesLocator);
		searchBuses.click();
		
		
	}

}
