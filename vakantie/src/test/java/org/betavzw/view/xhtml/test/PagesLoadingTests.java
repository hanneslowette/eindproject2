package org.betavzw.view.xhtml.test;

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
				.sendKeys("Hannes");
		driver.findElement(By.xpath("//input[@name='loginform:txtPassword']"))
				.sendKeys("Hannes");
		driver.findElement(By.xpath("//input[@name='loginform:loginButton']"))
				.click();
		
		Thread.sleep(2000);

	}
	
	@Test
	public void test() {
		driver.findElement(By.xpath(""));
	}

}
