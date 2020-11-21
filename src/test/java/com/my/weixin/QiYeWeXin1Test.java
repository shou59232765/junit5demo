package com.my.weixin;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QiYeWeXin1Test extends BaseWeXinTest {


    @Test
    void addContact() throws InterruptedException, IOException {

        driver.navigate().refresh();
        click(By.linkText("添加成员"));
        setContent(By.id("username"),RandomString.make(9));
        setContent(By.id("memberAdd_english_name"),RandomString.make(9));
        setContent(By.id("memberAdd_acctid"),RandomString.make(9));
        setContent(By.id("memberAdd_phone"),getMobile());
        click(By.linkText("保存"));

    }

    @Test
    void departmentSearch(){
        click(By.id("menu_contacts"));
        setContent(By.id("memberSearchInput"),"测试部");
        String content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(content);

        click(By.cssSelector(".ww_icon_AddMember"));
        content = driver.findElement(By.cssSelector(".js_party_info")).getText();
        assertTrue(content.contains("无任何成员"));


    }




}
