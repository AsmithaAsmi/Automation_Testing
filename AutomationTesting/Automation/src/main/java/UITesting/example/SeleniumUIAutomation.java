package UITesting.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;


public class SeleniumUIAutomation {

    WebDriver driver = new ChromeDriver();
    public static void main(String[] args) throws IOException, InterruptedException {
        SeleniumUIAutomation seleniumUIAutomation = new SeleniumUIAutomation();
        System.setProperty("webdriver.chrome.driver", "E:\\Selenium Automation\\SeleniumAutomation\\src\\test\\resources\\chromedriver.exe");
        seleniumUIAutomation.driver.manage().window().maximize();
        seleniumUIAutomation.driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        seleniumUIAutomation.loginToOrangeHrm(readFromPropertyFile("UserName"), readFromPropertyFile("Password"));
        seleniumUIAutomation.quitDriver();
    }
    public void quitDriver(){
        System.out.println("Quiting the driver");
        driver.quit();
    }

    public void loginToOrangeHrm(String userName, String password) throws InterruptedException {
        System.out.println("Login to the website");
        Thread.sleep(5000);
//        driver.findElement(By.xpath("//input[@name='username']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(userName);
//        driver.findElement(By.xpath("//input[@name='password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public static String readFromPropertyFile(String readText) throws IOException {
        System.out.println("Reading from the file");
        FileReader fileReader = new FileReader("E:\\Selenium Automation\\SeleniumAutomation\\src\\main\\resources\\config.properties");
        Properties properties = new Properties();
        properties.load(fileReader);
        readText = properties.getProperty(readText);
        return readText;
    }
}