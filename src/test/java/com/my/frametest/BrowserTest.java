package com.my.frametest;

import org.junit.jupiter.api.Test;

public class BrowserTest extends BaseTest {

    @Test
    void testlogin() throws InterruptedException {
        driver.get("https://ceshiren.com/");
        Thread.sleep(3000);
    }


}
