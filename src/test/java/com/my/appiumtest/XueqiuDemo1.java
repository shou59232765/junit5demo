package com.my.appiumtest;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class XueqiuDemo1 {

    private static AndroidDriver driver;

    private WebDriverWait wait;

    @BeforeAll
    static void init(){
        DesiredCapabilities caps = new DesiredCapabilities();
        //启动appium或者模拟器 需要的要数
        caps.setCapability("platformName","android");
        caps.setCapability("deviceName","xxxx");
        caps.setCapability("appActivity",".view.WelcomeActivityAlias");
        caps.setCapability("appPackage","com.xueqiu.android");
        caps.setCapability("udid","192.168.56.102:5555");
        //不重置环境
        caps.setCapability("noReset","true");

        try {
//            通过http接口与模拟器或者手机建立链接通信
            driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"),caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
//        瘾式等待（全局）
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    @AfterAll
    static void closed(){
        driver.quit();
    }


    @Test
    void testScroll() throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"过苑博\").instance(0))");
        Thread.sleep(3000);

    }


    @Test
    void childBychild() throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/tab_name\").fromParent(text(\"我的\")").click();
        Thread.sleep(3000);
    }


    @Test
    void fatherBychild() throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/scroll_view\").childSelector(text(\"推荐\"))").click();
        Thread.sleep(3000);
    }


    @Test
    void classTextUseTest() throws InterruptedException {
        driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.TextView\").text(\"行情\")").click();
        Thread.sleep(3000);
    }


    @Test
    void uiAutoMatorSelectorTest() throws InterruptedException {
//        AndroidDriver<MobileElement> driver = (AndroidDriver<MobileElement>)this.driver;
        driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.xueqiu.android:id/tab_name\").text(\"交易\")").click();
        Thread.sleep(3000);
    }


    /**
     * 屏幕滑动
     */
    @Test
    void swipTest(){
        try {
//            获取到手机屏幕的宽度和高度
            int x = driver.manage().window().getSize().getWidth();
            int y = driver.manage().window().getSize().getHeight();

            Thread.sleep(7000);
            TouchAction touchAction = new TouchAction(driver);
            touchAction.press(PointOption.point((int)(x*0.5),(int)(y*0.8)))
                    .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point((int)(x*0.5),(int)(y*0.2)))
                    .release().perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    @Test
    void snowBallTest(){
        wait = new WebDriverWait(driver,10,1);
        WebElement search = driver.findElementById("com.xueqiu.android:id/home_search");

        String isEnable = search.getAttribute("enabled");
        if("true".equals(isEnable)){
            System.out.println(search.getLocation());
            search.click();
            WebElement search_input_text = driver.findElementById("com.xueqiu.android:id/search_input_text");

            if(search_input_text.getAttribute("displayed").equals("true")){
                search_input_text.sendKeys("阿里巴巴");
                System.out.println("搜索成功");
            }else {
                System.out.println("搜索失败");
            }

            driver.findElement(By.xpath("//*[@text='BABA']")).click();

            String price = driver.findElementById("com.xueqiu.android:id/current_price").getText();
            System.out.println(price);
        }
    }


    @Test
    void test_xueqiu_search(){
//        显式等待 针对控件
        wait = new WebDriverWait(driver,10,1);
        driver.findElementById("com.xueqiu.android:id/home_search").click();
        WebElement el3 = driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.sendKeys("阿里巴巴");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();

        String price = driver.findElementById("com.xueqiu.android:id/current_price").getText();
        System.out.println(price);

    }

    @Test
    void price_test(){
        wait = new WebDriverWait(driver,10,1);
        driver.findElementById("com.xueqiu.android:id/home_search").click();
        WebElement el3 = driver.findElementById("com.xueqiu.android:id/search_input_text");
        el3.sendKeys("阿里巴巴");
        driver.findElement(By.xpath("//*[@text='BABA']")).click();

//        定位到09988阿里股票的的价格
        String price = driver.findElement(By.xpath("//*[@text='09988']/../../..//*[@resource-id='com.xueqiu.android:id/current_price']")).getText();
        System.out.println(price);


    }
}
