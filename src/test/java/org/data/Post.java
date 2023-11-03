package org.data;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class Post {

	public static void main(String[] args) throws JsonSyntaxException, JsonIOException, FileNotFoundException{
		WebDriver driver = new FirefoxDriver();
		driver.get("https://testpages.herokuapp.com/styled/tag/dynamic-table.html");
		driver.findElement(By.xpath("//summary[text()='Table Data']")).click();
	    WebElement findElement = driver.findElement(By.id("jsondata"));
	    findElement.clear();
	    Gson gson = new Gson();
	    JsonArray expecteddata = gson.fromJson(new FileReader("D:\\Assignment_for_SDET\\src\\test\\resources\\TestData.json"), JsonArray.class);
	    findElement.sendKeys(expecteddata.toString());
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	    driver.findElement(By.xpath("//button[text()='Refresh Table']")).click();
	    String actualdata = findElement.getAttribute("[{\"name\" : \"Bob\", \"age\" : 20, \"gender\": \"male\"}, " +
                "{\"name\": \"George\", \"age\" : 42, \"gender\": \"male\"}, " +
                "{\"name\": \"Sara\", \"age\" : 42, \"gender\": \"female\"}, " +
                "{\"name\": \"Conor\", \"age\" : 40, \"gender\": \"male\"}, " +
                "{\"name\": \"Jennifer\", \"age\" : 42, \"gender\": \"female\"}]");
	    Assert.assertEquals(actualdata, expecteddata,"testdata added successfully");
	  
	    driver.quit();
	    
	}
}
