package com.my.appiumtest;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTest extends AppBase {


    @Test
    void fun3() throws InterruptedException {
        wait = new WebDriverWait(driver,10,100);
        driver.findElementById("com.xueqiu.android:id/home_search").click();
        driver.findElementById("com.xueqiu.android:id/search_input_text").sendKeys("阿里巴巴");
        wait = new WebDriverWait(driver,1,1);
        WebElement until = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='BABA']")));
        String enabled = until.getAttribute("enabled");
        System.out.println("元素是否可见："+enabled);
        if("true".equals(enabled)){
            driver.findElement(By.xpath("//*[@text='BABA']")).click();
        }else{
            System.out.println("找不到元素");
        }

        Thread.sleep(2000);
    }


}
