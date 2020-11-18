package com.my.frametest.webtest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;

import java.util.concurrent.TimeUnit;

/**
* @Description:    Action的使用
* @Author:         soso.he
* @CreateDate:     2020-11-15  22:47
* @UpdateUser:     soso.he
* @UpdateDate:     2020-11-15  22:47
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ActionTest {
    private final static String url = "http://sahitest.com/demo/clicks.htm";
    private static WebDriver driver;
    private static Actions actions;

    @BeforeAll
    static void init() {
        driver = new ChromeDriver();
        actions = new Actions(driver);
        //隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
    }

    /**
     * 单击事件
     * @throws InterruptedException
     */
    @Test
    @Order(1)
    void clickMeTest() throws InterruptedException {
//        driver.get(url);
        actions.click(driver.findElement(By.xpath("//*[@value=\"click me\"]")));
        actions.perform();
        Thread.sleep(1000);
    }

    /**
     * 双击事件
     * @throws InterruptedException
     */
    @Test
    @Order(2)
    void doubleClickMeTest() throws InterruptedException {
        actions.doubleClick(driver.findElement(By.xpath("//input[@value=\"dbl click me\"]")));
        actions.perform();
        Thread.sleep(1000);
    }

    @Test
    @Order(3)
    void clearTest(){
        actions.click(driver.findElement(By.xpath("//input[@value=\"Clear\"]")));
        actions.perform();
    }


    /**
     * 移动鼠标到元素上
     * @throws InterruptedException
     */
    @Test
    void baiduTest() throws InterruptedException {
        driver.get("https://www.baidu.com");
        actions.moveToElement(driver.findElement(By.cssSelector("#u1 #s-usersetting-top"))).perform();
        Thread.sleep(3000);
        actions.click(driver.findElement(By.xpath("//*[@id=\"u1\"]//a[@class=\"setpref\"]"))).perform();
        Thread.sleep(3000);
    }

    /**
     * 拖动控件
     * @throws InterruptedException
     */
    @Test
    void DropToElementTest() throws InterruptedException {
        driver.get("http://sahitest.com/demo/dragDropMooTools.htm");
        WebElement sourceElement = driver.findElement(By.cssSelector("div[id=\"dragger\"]"));
        WebElement targetElement = driver.findElement(By.xpath("//div[@class=\"item\"][1]"));

        actions.dragAndDrop(sourceElement,targetElement).perform();
        Thread.sleep(3000);
    }


    /**
     * 模拟键盘事件 复制粘贴
     */
    @Test
    void keyBroadTest() throws InterruptedException {

        driver.get("http://sahitest.com/demo/label.htm");
        driver.findElements(By.xpath("//input[@type=\"textbox\"]")).get(0).sendKeys("soso");
        //control+a
        actions.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).perform();
//        control+c
        actions.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();
        actions.keyDown(driver.findElements(By.xpath("//input[@type=\"textbox\"]")).get(1),Keys.COMMAND)
                .sendKeys("v")
                .keyUp(Keys.COMMAND).perform();
        Thread.sleep(3000);

    }

    /**
     * 滑动事件
     */
    @Test
    void slideTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("霍格沃兹测试学院");
        TouchActions touchActions = new TouchActions(driver);
        touchActions.click(driver.findElement(By.id("su")));
        //通过鼠标滑动到页面底部 js操作
        //强转
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(5000);

        driver.findElement(By.xpath("//*[@class=\"page-inner\"]//a[last()]")).click();
        Thread.sleep(5000);

    }


    @AfterAll
    static void closed(){
        driver.quit();
    }
}
