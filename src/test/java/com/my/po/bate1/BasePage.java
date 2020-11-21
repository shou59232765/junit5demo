package com.my.po.bate1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public BasePage() {
    }

    protected void click(By by){
        driver.findElement(by).click();
    }

    protected void sendKeys(By by,String content){
        driver.findElement(by).sendKeys(content);
    }

    protected void close(){
        driver.quit();
    }


}
