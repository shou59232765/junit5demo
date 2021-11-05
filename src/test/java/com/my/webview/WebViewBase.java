package com.my.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebViewBase {
    protected static AndroidDriver driver;

    protected WebDriverWait wait;

    @BeforeAll
    static void init(){
        DesiredCapabilities caps = new DesiredCapabilities();
        //启动appium或者模拟器 需要的要数
//        caps.setCapability(MobileCapabilityType.BROWSER_NAME,"chrome");
        caps.setCapability("platformName","android");
        caps.setCapability("deviceName","xxxx");
        caps.setCapability("appActivity",".view.WelcomeActivityAlias");
        caps.setCapability("appPackage","com.xueqiu.android");
        caps.setCapability("udid","192.168.56.102:5555");
//        caps.setCapability("udid","emulator-5554");
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
    static void closed() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

}
