package com.my.jshandler;

import com.my.frametest.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

/**
* @Description:    处理文件上传
* @Author:         soso.he
* @CreateDate:     2020-11-16  22:44
* @UpdateUser:     soso.he
* @UpdateDate:     2020-11-16  22:44
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class UploadTest extends BaseTest {


    @Test
    void uploadTest() throws InterruptedException {

        driver.get("https://www.baidu.com/");

        driver.findElement(By.xpath("//span[@class=\"soutu-btn\"]")).click();
        driver.findElement(By.xpath("//input[@class=\"upload-pic\"]"))
                .sendKeys("/Users/helishou/Downloads/手持照片.png");

        Thread.sleep(5000);

    }


}
