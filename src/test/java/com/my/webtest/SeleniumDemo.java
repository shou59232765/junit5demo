package com.my.frametest.webtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @Description: 环境搭建
 * @Author: soso.he
 * @CreateDate: 2020-11-14  16:00
 * @UpdateUser: soso.he
 * @UpdateDate: 2020-11-14  16:00
 * @UpdateRemark: 修改内容
 * @Version: 1.0
 */
public class SeleniumDemo {

    private final static String url = "https://ceshiren.com/";
    private static WebDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    static void init() {
//        System.setProperty("webdriver.chrome.driver", "/Users/helishou/chromedriver");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    void demo1() throws InterruptedException {
        driver.get(url);
        Thread.sleep(3000);
    }

    @AfterAll
    static void closed(){
        driver.quit();
    }


}
