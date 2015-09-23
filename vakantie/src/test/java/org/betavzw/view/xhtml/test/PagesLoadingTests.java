package org.betavzw.view.xhtml.test;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PagesLoadingTests {

	static WebDriver driver = new FirefoxDriver();

	@BeforeClass
	public static void setup() throws InterruptedException {
		driver.get("http://localhost:8080/vakantie/faces/login.xhtml");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='loginform:txtUsername']"))
				.sendKeys("Yannick");
		driver.findElement(By.xpath("//input[@name='loginform:txtPassword']"))
				.sendKeys("Yannick");
		driver.findElement(By.xpath("//input[@name='loginform:loginButton']"))
				.click();

		Thread.sleep(2000);

	}

	@AfterClass
	public static void teardown() {
		driver.quit();
	}

	@Test
	public void login_Test() {
		String page = driver.getPageSource();
		assertTrue("failure - cannot log in",
				page.contains("U bent ingelogd als Yannick"));
	}

	@Test
	public void test() {
		driver.findElement(By.xpath("//ipnut[@name='homeform:werknemerToevoegen']"));
		driver.findElement(By.xpath("//ipnut[@name='homeform:werknemerOpvragen']"));
		driver.findElement(By.xpath("//ipnut[@name='homeform:homeform:team_toevoegen']"));
		driver.findElement(By.xpath("//ipnut[@name='homeform:werknemerToevoegen']"));
		driver.findElement(By.xpath("//ipnut[@name='homeform:werknemerToevoegen']"));
				
		
		
	}

}
