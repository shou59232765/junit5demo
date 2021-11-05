package com.my.appiumtest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;


public class AssertTest extends AppBase {


    private static Stream<Arguments> testData(){
        return Stream.of(
                Arguments.of("阿里巴巴","BABA",200d),
                Arguments.of("wangyi","NTES",80d),
                Arguments.of("baidu","BIDU",140d),
                Arguments.of("谷歌","GOOG",1800d)
        );
    }



    @ParameterizedTest
    @MethodSource("testData")
    void priceAssert(String name,String code,Double expPrice){
        wait = new WebDriverWait(driver,10,1);
        driver.findElementById("com.xueqiu.android:id/home_search").click();
        WebElement el3 = driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.sendKeys(name);
        driver.findElement(By.xpath("//*[@text='"+code+"']")).click();
//        driver.findElement(By.xpath("//*[@resource-id='com.xueqiu.android:id/title_text'][@text='股票']")).click();
        driver.findElement(By.xpath("//*[@text='股票']")).click();
        String price = driver.findElement(By.xpath("//*[@text='"+code+"']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();

        driver.findElement(By.xpath("//*[@text='取消']")).click();
//        assertThat("股票价格对比",Double.parseDouble(price),equalTo(256.6d));
        // 判断股票价格大于200
        assertThat("股票价格对比",Double.parseDouble(price),greaterThan(expPrice));
    }


    @Test
    void priceAssert1(){

        wait = new WebDriverWait(driver,10,1);
        driver.findElementById("com.xueqiu.android:id/home_search").click();
        WebElement el3 = driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.sendKeys("阿里巴巴");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();
//        driver.findElement(By.xpath("//*[@resource-id='com.xueqiu.android:id/title_text'][@text='股票']")).click();
        driver.findElement(By.xpath("//*[@text='股票']")).click();
        String price = driver.findElement(By.xpath("//*[@text='09988']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();

//        assertThat("股票价格对比",Double.parseDouble(price),equalTo(256.6d));
        // 判断股票价格大于200
        assertThat("股票价格对比",Double.parseDouble(price),greaterThan(200d));

    }






}
