package com.my.jshandler;

import com.my.frametest.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
* @Description:    js操作日期
* @Author:         soso.he
* @CreateDate:     2020-11-16  22:22
* @UpdateUser:     soso.he
* @UpdateDate:     2020-11-16  22:22
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class JsTest extends BaseTest {


    @Test
    void jsDateTest() throws InterruptedException {

        driver.get("https://www.12306.cn/index/");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById(\"train_date\").value='2020-12-30'");
        Thread.sleep(3000);
        System.out.println(js.executeScript("return document.getElementById('train_date').value"));
        Thread.sleep(3000);

    }


}