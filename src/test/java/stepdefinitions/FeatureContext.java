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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
		  
		driverConfig(path);
				
	}

	@When("I click {string}")
	public void i_click(String Elem) throws InterruptedException {
				
		String locator;
		try {
			locator = DataFromCSV(Elem.trim());
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
		locator = DataFromCSV(Elem.trim());
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
	
	static void driverConfig(String path) 
	{

		driver.get(path);
		
	}
	
	static String DataFromCSV(String Param) throws IOException
    {
		
       String dataResult = "";
       String path = systemPath + "/files/data/Element.csv";
       String row = "";
       
       BufferedReader csvReader;
		try {
			csvReader = new BufferedReader(new FileReader(path));
					while ((row = csvReader.readLine()) != null) {
			       String[] data = row.split(";");
			       
			       if(data[0].substring(0, 1) != "#")
			       {  			    	
			    	   if (data[0].equals(Param)) {
			    		   dataResult = data[1];
			    		   csvReader.close();
			    		   break; 
			    	   	}
	           
			       }
			   }
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 return dataResult;
	}
      
	 
	@After
	public void afterScenario(){
	    driver.quit();
	}
	
}
