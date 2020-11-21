package com.my.weixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.my.frametest.BaseTest;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.IOException;

public class QiYeWeXinTest extends BaseWeXinTest {


    @Test
    void addContact() throws InterruptedException, IOException {

        driver.navigate().refresh();

//        driver.findElement(By.xpath("//span[contains(text(),\"添加成员\")]")).click();
        driver.findElement(By.linkText("添加成员")).click();
        driver.findElement(By.id("username")).sendKeys(RandomString.make(9));
        driver.findElement(By.id("memberAdd_english_name")).sendKeys(RandomString.make(9));
        driver.findElement(By.id("memberAdd_acctid")).sendKeys(RandomString.make(9));
        driver.findElement(By.id("memberAdd_phone")).sendKeys(getMobile());
//        driver.findElement(By.xpath("//a[@class=\"qui_btn ww_btn js_btn_save\"][1]")).click();
        driver.findElement(By.linkText("保存")).click();
        Thread.sleep(10000);

    }


    @Test
    void testRandom() {
        System.out.println(RandomString.make(9));
        System.out.println(System.currentTimeMillis());
        System.out.println(getMobile());

    }


}
