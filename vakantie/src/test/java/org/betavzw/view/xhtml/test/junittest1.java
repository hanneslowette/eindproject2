package org.betavzw.view.xhtml.test;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class junittest1 {

	WebDriver driver = new FirefoxDriver();

	@Test
	public void test() throws InterruptedException {
		//driver.manage().window().maximize();
		driver.get("http://localhost:8080/vakantie/faces/login.xhtml");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//input[@name='loginform:txtUsername']")).sendKeys(
				"Hannes");		
		driver.findElement(By.xpath("//input[@name='loginform:txtPassword']")).sendKeys(
				"Hannes");
		driver.findElement(By.xpath("//input[@name='loginform:submit']")).click();
		Thread.sleep(2000);
		System.out.print("junittest1 class is executed");
		driver.quit();
	}
}