package org.betavzw.view.xhtml.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class VerlofAanvragenTest {

	public static void main(String[] args) {

		// Create a new instance of Firefox Browser
		WebDriver driver = new FirefoxDriver();

		// Open the URL in firefox browser
		driver.get("http://localhost:8080/vakantie/faces/verlofaanvragen.xhtml");

		// Maximize the Browser window
		driver.manage().window().maximize();

		// Get the current page URL and store the value in variable 'str'
		String str = driver.getCurrentUrl();

		// Print the value of variable in the console
		System.out.println("The current URL is " + str);
	}
}