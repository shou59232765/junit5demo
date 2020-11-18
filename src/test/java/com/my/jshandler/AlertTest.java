package com.my.jshandler;

import com.my.frametest.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertTest extends BaseTest {


    @Test
    void alertTest() throws InterruptedException {

        driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");
        //切frame
        driver.switchTo().frame("iframeResult");
        WebElement source = driver.findElement(By.xpath("//div[contains(text(),\"请拖拽我！\")]"));
        WebElement target = driver.findElement(By.id("droppable"));
        //拖动控件
        dragAndDrop(source,target);
        Thread.sleep(4000);

        //弹框处理
        driver.switchTo().alert().accept();

        Thread.sleep(3000);

        driver.switchTo().parentFrame();
        System.out.println(driver.findElement(By.id("submitBTN")).getText());

    }
}
