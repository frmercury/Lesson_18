package com.ex.ui;

import com.ex.BaseTest;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeUiSuite extends BaseTest {
    public static String randomEmailGeneration () {
        return RandomStringUtils.randomAlphabetic(8) + "@gmail.com";
    }

    @Test
    public void checkRegisterFormIsDisplayed() {
        webDriver.get("http://automationpractice.com/");
        By signIn = By.xpath("//a[@class='login']");
        webDriver.findElement(signIn).click();

        webDriver.findElement(By.xpath("//input[@id='email_create']")).sendKeys(randomEmailGeneration());
        webDriver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();

        //Очень долго грузит, увеличил время, чтобы элемент успел отобразиться
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(webDriver.findElement(By.id("account-creation_form")).isDisplayed());
    }
    @Test
    public void checkMailAlreadyExistIsDisplayed() {
        webDriver.get("http://automationpractice.com/");
        By signIn = By.xpath("//a[@class='login']");
        webDriver.findElement(signIn).click();

        webDriver.findElement(By.xpath("//input[@id='email_create']")).sendKeys("example@email.com");
        webDriver.findElement(By.xpath("//button[@id='SubmitCreate']")).click();


        //Очень долго грузит, увеличил время, чтобы элемент успел отобразиться
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(webDriver.findElement(By.id("create_account_error")).isDisplayed());
    }
}
