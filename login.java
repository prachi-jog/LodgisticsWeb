package HotelLog;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class login {

	WebDriver driver;

	@BeforeSuite
	public void setUp() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ue\\eclipse-workspace\\LodgisticsWeb\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://stagingapp.lodgistics.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test(priority = 0)
	public void Login() throws InterruptedException {

		driver.findElement(By.id("user_login")).click();
		driver.findElement(By.id("user_login")).clear();
		driver.findElement(By.id("user_login")).sendKeys("prachi.j");
		driver.findElement(By.id("user_password")).click();
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys("lodgistics123");
		driver.findElement(By.name("commit")).click();
		// driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Thread.sleep(15000L);
		driver.findElement(By.xpath("//*[@id='wootric-x']")).click();
	}

	@Test(priority = 1)
	public void CreatePost() throws InterruptedException, IOException {

		System.out.println("Creating First Post");

		driver.findElement(By.xpath("//ul[@class='topmenu topmenu-responsive']/li[7]/a[1]/span[2]")).click();
		// driver.findElement(By.linkText("Hotel Log")).click();
		Thread.sleep(10000L);
		driver.findElement(By.xpath("//button[@class='btn cta-button']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Title (optional)']"))
				.sendKeys("Test Post created by Prachi through Selenium");
		Thread.sleep(10000L);
		driver.findElement(By.xpath("//*[@class='form-control text-uppercase']")).sendKeys(Keys.TAB);

		driver.findElement(By.xpath("/html/body/div[5]/div/div/div[2]/div[1]/div/div[1]/div/textarea"))
				.sendKeys("Description One through automation. Please ignore");
		driver.findElement(By.xpath("//button[@class= 'btn cta-button right ']")).click();
		System.out.println("Test post created");

		// driver.navigate().refresh();

		// Getting screenshot of post created

		Thread.sleep(30000L);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver
				.findElement(By.xpath("//*[contains(text(),'TEST POST CREATED BY PRACHI THROUGH SELENIUM')]"));
		js.executeScript("arguments[0].scrollIntoView();", element);

		Thread.sleep(20000L);
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src,
				new File("C:\\Users\\ue\\eclipse-workspace\\LodgisticsWeb\\Screenshots\\screenshot1.png"));
		Thread.sleep(30000L);

	}

	@Test(priority = 2)
	public void DeletePost() throws InterruptedException {

		WebElement element = driver
				.findElement(By.xpath("//*[contains(text(),'TEST POST CREATED BY PRACHI THROUGH SELENIUM')]"));
		String string = element.getText();
		Assert.assertEquals("TEST POST CREATED BY PRACHI THROUGH SELENIUM", string);
		System.out.println("Deleting post:  " + string);

		driver.findElement(By.xpath("//button[@class= 'btn cta-button small cancel']")).click();
		Thread.sleep(20000L);
		driver.findElement(By.xpath("//button[.='Confirm']")).click();

	}

	@AfterSuite

	public void success() {
		System.out.println("First class fle executed");
	}
}
