package com.my.weixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.my.frametest.BaseTest;
import org.junit.jupiter.api.Test;
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

public class TestWeb extends BaseTest {


    @Test
    void testGetCookies() throws IOException, InterruptedException {
//        String url = "https://work.weixin.qq.com/wework_admin/loginpage_wx?redirect_uri=https://work.weixin.qq.com/wework_admin/loginpage_wx";
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(url);

        Thread.sleep(15000);
        Set<Cookie> cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);

    }

    @Test
    void testLogin() throws IOException, InterruptedException {
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver.get(url);

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
        Thread.sleep(15000);
    }

    @Test
    void addEmploee() throws InterruptedException, IOException {
        String url = "https://work.weixin.qq.com/wework_admin/frame";
        driver.get(url);

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

        driver.findElement(By.xpath("//span[contains(text(),\"添加成员\")]")).click();
        driver.findElement(By.id("username")).sendKeys("sosossdfsdf");
        driver.findElement(By.id("memberAdd_english_name")).sendKeys("yyyy");
        driver.findElement(By.id("memberAdd_acctid")).sendKeys("soso_auto_test");
        driver.findElement(By.id("memberAdd_phone")).sendKeys("13762626251");
        driver.findElement(By.xpath("//a[@class=\"qui_btn ww_btn js_btn_save\"][1]")).click();
        Thread.sleep(10000);

    }



}
