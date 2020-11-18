package com.my.frametest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {


    public static WebDriver driver;
    public static Actions actions;

    @BeforeAll
    static void init(){
        //执行以下maven命令可以执行
        //browser="safari" mvn -Dtest=BrowserTest test
//        String browseName = System.getenv("browser");
        String browseName = "chrome";
        if("chrome".equals(browseName)){
            System.setProperty("webdriver.gecko.driver","/Users/helishou/chromedriver");
            driver = new ChromeDriver();
        }else if("firfox".equals(browseName)){
            System.setProperty("webdriver.gecko.driver", "/Users/helishou/chromedriver");
            driver = new FirefoxDriver();
        }else if("safari".equals(browseName)){
            driver = new SafariDriver();
        }
        //隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actions = new Actions(driver);

    }

    public void dragAndDrop(WebElement source,WebElement target){
        actions.dragAndDrop(source,target).perform();
    }

    @AfterAll
    static void closed(){
        driver.quit();
    }

}
