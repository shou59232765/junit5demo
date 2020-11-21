package com.my.po.bate1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;


public class ContactPage extends BasePage{

    //po原则2 不要暴露页面内部实现细节
    private By partInfo = By.cssSelector(".js_party_info");


    public ContactPage(WebDriver driver) {
        super(driver);
    }

    //po原则6 添加成功的时候与添加失败返回的页面是不同的，需要封装为不同的方法
    public ContactPage memberAdd(Map<String,Object> data){
        click(By.linkText("添加成员"));
        sendKeys(By.id("username"), data.get("username").toString());
        sendKeys(By.id("memberAdd_english_name"),data.get("englishName").toString());
        sendKeys(By.id("memberAdd_acctid"),data.get("acctid").toString());
        sendKeys(By.id("memberAdd_phone"),data.get("phone").toString());
        click(By.linkText("保存"));
        return this;
    }

    //po原则6 添加失败返回的页面是不同的，需要封装为不同的方法
    public ContactPage memberAddNew(Map<String,Object> data){
        click(By.cssSelector(".ww_icon_AddMember"));
        click(By.linkText("添加成员"));
        sendKeys(By.id("username"), data.get("username").toString());
        sendKeys(By.id("memberAdd_english_name"),data.get("englishName").toString());
        sendKeys(By.id("memberAdd_acctid"),data.get("acctid").toString());
        sendKeys(By.id("memberAdd_phone"),data.get("phone").toString());
        click(By.linkText("保存"));
        return this;
    }


    public ContactPage addDepartment(String deptName){
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendKeys(By.name("name"),deptName);
        click(By.linkText("选择所属部门"));
        driver.findElements(By.linkText("斗虫科技有限公司")).get(1).click();
        click(By.linkText("确定"));
        return this;
    }

    public ContactPage updateDepartment(String deptName){
        click(By.xpath("//a[contains(text(),\"研发部999\")]//span[@class=\"icon jstree-contextmenu-hover\"]"));
        click(By.linkText("修改名称"));
        sendKeys(By.name("name"),deptName);
        click(By.linkText("保存"));
        return this;
    }


    public ContactPage updateMemeber(String name){
        click(By.xpath("//*[@id=\"member_list\"]/tr[1]/td[1]/input"));

        click(By.cssSelector("#member_list > tr:nth-child(1) > td:nth-child(7) > a"));

        click(By.xpath("//a[contains(text(),\"编辑\")]"));
        sendKeys(By.name("username"),name);
        click(By.linkText("保存"));
        return this;
    }



    public ContactPage departmentSearch(String deptName){
        sendKeys(By.id("memberSearchInput"),deptName);
        String content = driver.findElement(partInfo).getText();
        return this;
    }

    public String getPartyInfo(){
        click(By.cssSelector(".ww_icon_AddMember"));
        return driver.findElement(partInfo).getText();
    }

}
