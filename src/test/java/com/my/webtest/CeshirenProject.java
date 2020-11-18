package com.my.frametest.webtest;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
* @Description:    测试人页面自动化项目
* @Author:         soso.he
* @CreateDate:     2020-11-14  16:34
* @UpdateUser:     soso.he
* @UpdateDate:     2020-11-14  16:34
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class CeshirenProject {
    private final static String url = "https://ceshiren.com/";
    private static WebDriver driver;
    private static WebDriverWait wait;
    private final static String username="";
    private final static String password="";

    @BeforeAll
    static void init() {
        driver = new ChromeDriver();
        //隐式等待
//        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);
    }

    @Disabled
    @Test
    void login() throws InterruptedException {
        driver.get(url);
        driver.findElement(By.xpath("//span[contains(text(),\"登录\")]")).click();
        driver.findElement(By.id("login-account-name")).clear();
        driver.findElement(By.id("login-account-name")).sendKeys(username);

        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    @Test
    void testTimeOut(){
        driver.get(url);
//        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        WebElement logEle = wait.until(new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver webDriver) {
                return webDriver.findElement(By.id("//span[contains(text(),'登录')]"));
            }
        });
        logEle.click();

//        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//span[contains(text(),\"登录\")]"))
//
//        );
//        element.click();

    }

    @AfterAll
    static void closed(){
        driver.quit();
    }

}
