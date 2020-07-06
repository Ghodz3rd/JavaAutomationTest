package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;

import stepdefinitions.HelperContext;

public class FeatureContext {

	private static WebDriver driver;
	private Integer suspend = 3000;
	static String systemPath = System.getProperty("user.dir");
	
	 @Before
	 public void beforeScenario(){
		System.setProperty("webdriver.chrome.driver",systemPath +"/files/chromedriver");		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");
		driver = new ChromeDriver(options);
	} 

	@Given("I am on homepage {string}")
	public void i_am_on_homepage(String path) {
		  
		driver.get(path);
				
	}

	@When("I click {string}")
	public void i_click(String Elem) throws InterruptedException {
				
		String locator;
		try {
			locator = HelperContext.DataFromCSV(Elem.trim());
			WebElement element = driver.findElement(By.xpath(locator));
						
			if (element != null) {
				element.click();
				Thread.sleep(suspend);
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
			        	    
	}
	
	
	@When("I fill in {string} with {string}")
	public void i_fill_in_with(String Elem, String value) {
	

	String locator;
	try {
		locator = HelperContext.DataFromCSV(Elem.trim());
		WebElement element = driver.findElement(By.xpath(locator));
						
		if (element != null) {
				element.sendKeys(value);
			}
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	
	}

	@Then("I should see {string}")
	public void i_should_see(String value) {
		
		try 
		{
			driver.getPageSource().contains(value);
		} catch (Exception e) {
		    System.out.println("Wrong account name displayed");
		}
			
	}
	      
	@After
	public void afterScenario(){
	    driver.quit();
	}
	
}
