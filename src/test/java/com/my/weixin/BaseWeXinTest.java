package com.my.weixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BaseWeXinTest {


    public static WebDriver driver;
    public static Actions actions;

    @BeforeAll
    static void init() throws IOException, InterruptedException {
        //执行以下maven命令可以执行
        //browser="safari" mvn -Dtest=BrowserTest test
//        String browseName = System.getenv("browser");
        String browseName = "chrome";
        if("chrome".equals(browseName)){
            System.setProperty("webdriver.gecko.driver","/Users/helishou/chromedriver");
            driver = new ChromeDriver();
        }else if("firfox".equals(browseName)){
            System.setProperty("webdriver.gecko.driver", "/Users/helishou/chromedriver");
            driver = new FirefoxDriver();
        }else if("safari".equals(browseName)){
            driver = new SafariDriver();
        }
        //隐式等待
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);

        loginWeXin();
    }

    public static void loginWeXin() throws IOException, InterruptedException {
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver.get(url);
        File file = new File("cookies.yaml");
        if(file.exists()){

            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference<List<HashMap<String,Object>>> typeReference = new TypeReference<List<HashMap<String,Object>>>() {
            };
            //读cookies
            List<HashMap<String, Object>> cookies = mapper.readValue(new File("cookies.yaml"), typeReference);
            System.out.println(cookies);

            //给浏览器设置cookies
            cookies.forEach(x->{
                driver.manage().addCookie(new Cookie(x.get("name").toString(),x.get("value").toString()));
            });

            //刷新
            driver.navigate().refresh();
        }else{
            GetCookies();
        }


    }

    static void GetCookies() throws IOException, InterruptedException {
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);

        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);
        System.exit(0);

    }

    public void dragAndDrop(WebElement source,WebElement target){
        actions.dragAndDrop(source,target).perform();
    }

    @AfterAll
    static void closed(){
        driver.quit();
    }

}
