package com.my.po.bate1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{

    public MainPage() throws IOException, InterruptedException {
        this.loginWeXin();
    }

    public ContactPage intoContactPage(){
//        click(By.linkText("添加成员"));
        click(By.id("menu_contacts"));
        return new ContactPage(this.driver);
    }



    public void loginWeXin() throws IOException, InterruptedException {
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver = new ChromeDriver();
        //隐式等待
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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
            writeCookies();
        }


    }

    private void writeCookies() throws IOException, InterruptedException {
//        String url = "https://work.weixin.qq.com/wework_admin/frame";
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//        driver.get(url);

        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);

    }
}
