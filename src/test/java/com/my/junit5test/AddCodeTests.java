package com.my.junit5test;

import com.stu.auto.AddCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddCodeTests {


    @Test
    void test_add(){
        AddCode a1 = new AddCode();
        Assertions.assertEquals(a1.add(5,6),11,"失败");
    }

    @Test
    void addFailtest(){
        AddCode a1 = new AddCode();
        Assertions.assertEquals(a1.add(5,6),12,"失败");
    }
}
