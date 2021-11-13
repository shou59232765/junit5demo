package com.my.junit5test;

import com.stu.auto.AddCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Junit5Demo_1_1_AssertAll {

    @Test
    void addTest() {
        AddCode a1 = new AddCode();
        Assertions.assertEquals(a1.add(5, 6), 11, "失败");
        AddCode a2 = new AddCode();
        Assertions.assertEquals(a2.add(5, 6), 12, "失败");

    }

}

