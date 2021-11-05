package com.my.webview;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class WebViewDemo1 extends WebViewBase{

//    webview原生定位
   @Test
    void webview_native() {
       driver.findElement(By.xpath("//*[@text='交易']")).click();
       driver.findElement(By.xpath("//*[@text='基金开户']")).click();
   }




}
