package mvnProjerct;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GitTest {
	WebDriver driver;
	String url;
	String username;
	String password="";
	By USERNAME_FIELD = By.xpath("//*[@id=\"username\"]");
	By PASSWORD_FIELD = By.xpath("//*[@id=\"password\"]");
	By LOGIN_BUTTON_FIELD = By.xpath("/html/body/div/div/div/form/div[3]/button");
	By DASHBOARD_HEADER_FIELD = By.xpath("//*[@id=\"page-wrapper\"]/div[2]/div/h2");
	
	@BeforeClass
	public void getConfig() throws Exception {
		Properties prop = new Properties();
		FileInputStream fi = new FileInputStream("data\\config.properties");
		prop.load(fi);
		url = prop.getProperty("url");
		username=prop.getProperty("username");
		password=prop.getProperty("password");
		
	}
	
	@BeforeMethod
	public void init () {
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}
	
	@Test
	public void loginTest() {
		driver.findElement(USERNAME_FIELD).sendKeys(username);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(LOGIN_BUTTON_FIELD).click();
		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), "Dashboard", "wrong//failed ");
		
	}
	@Test
	public void negLoginTest() {
		driver.findElement(USERNAME_FIELD).sendKeys(username);
		driver.findElement(PASSWORD_FIELD).sendKeys("abc1234");
		driver.findElement(LOGIN_BUTTON_FIELD).click();
		
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
	}

}
