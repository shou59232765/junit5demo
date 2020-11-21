package com.my.po.bate1;

import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
* @Description:    使用pageObject模式  初步使用（未优化）
* @Author:         soso.he
* @CreateDate:     2020-11-20  01:17
* @UpdateUser:     soso.he
* @UpdateDate:     2020-11-20  01:17
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
public class ContactPoTest {

    private static MainPage mainPage;

    private String getMobile(){
        long time = System.currentTimeMillis();
        String s = String.valueOf(time);
        String substring = s.substring(5);
        String mobile = "137" + substring;
        return mobile;
    }

    private Map<String,Object> getData(){
        Map<String,Object> data = new HashMap<>();
        data.put("username", RandomString.make(9));
        data.put("englishName", RandomString.make(9));
        data.put("acctid", RandomString.make(9));
        data.put("phone", getMobile());
        return data;
    }

    @BeforeAll
    static void init() throws IOException, InterruptedException {
        mainPage = new MainPage();
        //数据清理
    }


    @Test
    void testAddMember() throws IOException, InterruptedException {
        Map<String,Object> data = new HashMap<>();
        data.put("username", RandomString.make(9));
        data.put("englishName", RandomString.make(9));
        data.put("acctid", RandomString.make(9));
        data.put("phone", getMobile());

        mainPage.intoContactPage().memberAdd(data);
        mainPage.close();

    }

    @ParameterizedTest
    @ValueSource(strings={"测试部"})
    void testSearchDepartment(String value) throws IOException, InterruptedException {
        String content = mainPage.intoContactPage().departmentSearch(value).getPartyInfo();
        mainPage.close();
        assertTrue(content.contains("无任何成员"));

    }



    @ParameterizedTest
    @ValueSource(strings={"研发部"})
    void testAddDepartment(String value) throws IOException, InterruptedException {
        String content = mainPage.intoContactPage()
                .addDepartment(value)
                .departmentSearch(value).getPartyInfo();
        assertTrue(content.contains("无任何成员"));
        assertTrue(content.contains(value));

    }

    @ParameterizedTest
    @ValueSource(strings={"研发部999"})
    void testUpdateDepartment(String value) throws IOException, InterruptedException {
        String content = mainPage.intoContactPage().addDepartment(value)
                .updateDepartment("研发部1111111")
                .departmentSearch("研发部1111111").getPartyInfo();
        assertTrue(content.contains("无任何成员"));
        assertTrue(content.contains(value));
    }


    //    新建部门 搜索部门 更新部门 更新成员信息 参数化
    @ParameterizedTest
    @ValueSource(strings={"研发部999"})
    void testUpdateDeptMemeber(String value) throws IOException, InterruptedException {
        mainPage.intoContactPage().addDepartment(value)
                .memberAddNew(getData()).updateMemeber("soso123");


    }


}
