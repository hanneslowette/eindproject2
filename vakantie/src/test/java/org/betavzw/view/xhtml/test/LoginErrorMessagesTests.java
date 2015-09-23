package org.betavzw.view.xhtml.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginErrorMessagesTests {

	WebDriver driver = new FirefoxDriver();

	@Before
	public void setup() throws InterruptedException {
		driver.get("http://localhost:8080/vakantie/faces/login.xhtml");
		Thread.sleep(2000);
	}

	@After
	public void teardown() {
		driver.quit();
	}

	@Test
	public void noPassword_Test() throws InterruptedException {

		driver.findElement(By.xpath("//input[@name='loginform:txtUsername']"))
				.sendKeys("Hannes");
		driver.findElement(By.xpath("//input[@name='loginform:loginButton']"))
				.click();

		Thread.sleep(2000);

		String message = driver.findElement(
				By.xpath("//div[@class='messages']")).getText();

		assertTrue(
				"failure - doesn't return correct message when no password is entered",
				message.contains("Gelieve username en password in te voeren"));

	}

	@Test
	public void noUsername_Test() throws InterruptedException {

		driver.findElement(By.xpath("//input[@name='loginform:txtPassword']"))
				.sendKeys("Hannes");
		driver.findElement(By.xpath("//input[@name='loginform:loginButton']"))
				.click();

		Thread.sleep(2000);

		String message = driver.findElement(
				By.xpath("//div[@class='messages']")).getText();

		assertTrue(
				"failure - doesn't return correct message when no username is entered",
				message.contains("Gelieve username en password in te voeren"));

	}

	@Test
	public void wrongPassword_Test() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@name='loginform:txtUsername']"))
				.sendKeys("Hannes");
		driver.findElement(By.xpath("//input[@name='loginform:txtPassword']"))
				.sendKeys("sdfsdfsdfqsdfsdq");
		driver.findElement(By.xpath("//input[@name='loginform:loginButton']"))
				.click();

		Thread.sleep(2000);

		String message = driver.findElement(
				By.xpath("//div[@class='messages']")).getText();

		assertTrue(
				"failure - doesn't return correct message when password is wrong",
				message.contains("Username of password niet gevonden"));
	}
	
	@Test
	public void wrongUsername_Test() throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@name='loginform:txtUsername']"))
				.sendKeys("sdfgsqdfgqsdfqsdfqsdf");
		driver.findElement(By.xpath("//input[@name='loginform:txtPassword']"))
				.sendKeys("Hannes");
		driver.findElement(By.xpath("//input[@name='loginform:loginButton']"))
				.click();

		Thread.sleep(2000);

		String message = driver.findElement(
				By.xpath("//div[@class='messages']")).getText();

		assertTrue(
				"failure - doesn't return correct message when username is unknown",
				message.contains("Username of password niet gevonden"));
	}
}