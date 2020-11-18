package com.my.frametest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FrameSwitchTest {

    private final static String url = "https://www.baidu.com/";
    private static WebDriver driver;
    private static Actions actions;

    @BeforeAll
    static void init(){
        driver = new ChromeDriver();
        actions = new Actions(driver);
        //隐式等待
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);
    }

    @AfterAll
    static void closed(){
        driver.quit();
    }


    /**
     * 切换frame 操作
     * @throws InterruptedException
     */
    @Test
    void frameTest() throws InterruptedException {
        driver.get("https://www.runoob.com/try/try.php?filename=jqueryui-api-droppable");
        driver.manage().window().maximize();

//        切换frame
        driver.switchTo().frame("iframeResult");
        WebElement source = driver.findElement(By.xpath("//div[@id=\"draggable\"]"));
        WebElement target = driver.findElement(By.xpath("//div[@id=\"droppable\"]"));

        actions.dragAndDrop(source,target).perform();
        Thread.sleep(4000);

        //跳出到父frame
        driver.switchTo().parentFrame();
//        driver.findElement(By.xpath("//button[@id=\"submitBTN\"]")).click();
//        Thread.sleep(4000);

    }

    /**
     * 先注册，再切换窗口登录
     */
    @Test
    void regAndLogin() throws InterruptedException {
        driver.get(url);
        driver.manage().window().maximize();
        //点击登录按钮
        driver.findElement(By.xpath("//div[@id=\"u1\"]//a[@name=\"tj_login\"]")).click();

        //百度首页的登录窗口句柄
        String baiduWin = driver.getWindowHandle();
        System.out.println(baiduWin);

        //点击立即注册按钮
        driver.findElement(By.xpath("//div[@class=\"tang-pass-footerBar\"]//a[@target=\"_blank\"]")).click();

        //获取浏览器所有窗口
        Set<String> windowHandles = driver.getWindowHandles();
        System.out.println(windowHandles);
        for (String windowHandle : windowHandles) {
            if(!baiduWin.equals(windowHandle)){
                //切到新的窗口
                driver.switchTo().window(windowHandle);
                driver.findElement(By.id("TANGRAM__PSP_4__userName")).sendKeys("soso");

                copyData();
                driver.switchTo().window(baiduWin);
                driver.findElement(By.id("TANGRAM__PSP_11__footerULoginBtn")).click();
                parasData(driver.findElement(By.xpath("//input[@id=\"TANGRAM__PSP_11__userName\"]")));


                driver.switchTo().window(windowHandle);
                driver.findElement(By.id("TANGRAM__PSP_4__phone")).sendKeys("13480644811");
                copyData();
                driver.switchTo().window(baiduWin);
                parasData(driver.findElement(By.id("TANGRAM__PSP_11__password")));
            }
        }

        Thread.sleep(5000);


    }

    void copyData(){
        actions.keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).perform();
        actions.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).perform();
    }
    void parasData(WebElement element){
        actions.keyDown(element,Keys.COMMAND)
                .sendKeys("v")
                .keyUp(Keys.COMMAND).perform();
    }


}
