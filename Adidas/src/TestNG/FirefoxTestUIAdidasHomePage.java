package TestNG;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.Test;
//import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class FirefoxTestUIAdidasHomePage {
	
	
	@Test
	public void FirefoxTestUIAdidas0001() throws InterruptedException {
	   System.setProperty("webdriver.gecko.driver", "/Users/atul/Downloads/geckodriver"); 
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.adidas.fi/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//remove the pop-up asking for select website based on loaction
		driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div/div[2]/header/div/a/i")).click();

		Actions act = new Actions(driver);
		Thread.sleep(5000);
		// ---------------------TEST #1-------------------------------
		driver.findElement(By.xpath("//div[@data-auto-id='glass-navigation-flyout']/a[contains(text(),'Brands')]"))
				.click();
		Thread.sleep(5000);
		act.moveToElement(driver.findElement(By.xpath("//a[@class='image-banner']/img[contains(@alt,'athletics')]")))
				.click().perform();
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@href,'SHOP-MEN')]")).isDisplayed());
		System.out.println("-----------The shop ZNE MEN is displayed----------");
		System.out.println("-----------Test #1 Firefox UI PASS----------");
		driver.quit();

	}

	@Test
	public void FirefoxTestUIAdidas0002() throws InterruptedException {

		
		System.setProperty("webdriver.gecko.driver", "/Users/atul/Downloads/geckodriver"); 
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.adidas.fi/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//remove the pop-up asking for select website based on loaction
		driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div/div[2]/header/div/a/i")).click();

		Actions act = new Actions(driver);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@data-auto-id='glass-navigation-flyout']/a[contains(text(),'Brands')]"))
				.click();
		Thread.sleep(5000);
		act.moveToElement(driver.findElement(By.xpath("//a[@class='image-banner']/img[contains(@alt,'athletics')]")))
				.click().perform();
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@href,'SHOP-MEN')]")).isDisplayed());
		System.out.println("-----------The shop ZNE MEN is displayed----------");
		// ---------------------TEST #2-------------------------------
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(@href,'SHOP-MEN')]")).click();

		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='product-CG0253']")).isDisplayed());
		System.out.println("-----------The corresponding men's items are displayed----------");
		System.out.println("-----------Test #2 Firefox UI PASS----------");
		driver.quit();

	}

	@Test
	public void FirefoxTestUIAdidas0003() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "/Users/atul/Downloads/geckodriver"); 
		WebDriver driver = new FirefoxDriver();
		driver.get("https://www.adidas.fi/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//remove the pop-up asking for select website based on loaction
		driver.findElement(By.xpath("//*[@id='app']/div/div[2]/div/div[2]/header/div/a/i")).click();

		Actions act = new Actions(driver);
		Thread.sleep(5000);
		// act.moveToElement(driver.findElement(By.xpath("//div[@data-auto-id=\"glass-navigation-flyout\"]/a[contains(text(),'Brands')]"))).click().perform();
		driver.findElement(By.xpath("//div[@data-auto-id='glass-navigation-flyout']/a[contains(text(),'Brands')]"))
				.click();
		// driver.findElement(By.xpath("//a[contains(text(),'Brands')]")).sendKeys("sports");

		Thread.sleep(5000);
		act.moveToElement(driver.findElement(By.xpath("//a[@class='image-banner']/img[contains(@alt,'athletics')]")))
				.click().perform();
		Thread.sleep(5000);
		Assert.assertTrue(driver.findElement(By.xpath("//a[contains(@href,'SHOP-MEN')]")).isDisplayed());
		System.out.println("-----------The shop ZNE MEN is displayed----------");

		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[contains(@href,'SHOP-MEN')]")).click();

		Thread.sleep(4000);
		Assert.assertTrue(driver.findElement(By.xpath("//*[@id='product-CG0253']")).isDisplayed());
		System.out.println("-----------The corresponding men's items are displayed----------");
		// ---------------------TEST #3-------------------------------
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id='product-CG0253']")).click();

		Thread.sleep(6000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//button[@data-auto-id='add-to-bag' and @type='submit']")).isDisplayed());

		System.out.println("-----------The corresponding button for buying a good is displayed----------");
		System.out.println("-----------Test #3 Firefox UI PASS----------");
		driver.quit();
	}
}
