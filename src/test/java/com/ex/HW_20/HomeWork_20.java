package com.ex.HW_20;

import com.ex.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeWork_20 extends BaseTest {
    By tryTestRailButton = By.xpath("//ul['gk-header-sticky-main-menu']/a['btn btn-small btn-green']");

    @Test
    public void threadSleep() {
        webDriver.get("https://secure.gurock.com/customers/auth/login/");

        Assert.assertTrue(findElement(webDriver, tryTestRailButton).isDisplayed());
        webDriver.manage().window().setSize(new Dimension(375, 667));
        assertTrue(checkIsInvisible(webDriver, tryTestRailButton));
    }

    @Test
    public void testScrolling() {
        webDriver.get("https://www.gurock.com/testrail/");

        WebElement we = webDriver.findElement(By.xpath("//a[contains(., 'Legal')]"));
        scrollToElement(webDriver, we);
        we.click();
    }

    @Test
    public void testSectionLinkAboutGurockLink () {
        webDriver.get("https://www.gurock.com/testrail/");

        findElement(webDriver, By.xpath("//li[@id='menu-item-704']/a")).click();
        assertTrue(checkIsVisible(webDriver, By.xpath("//a[contains(text(), 'Learn more about')]")));
    }

    @Test
    public void testSectionLinkJobsLink () {
        webDriver.get("https://www.gurock.com/testrail/");

        WebElement we = findElement(webDriver, By.xpath("//li[@id='menu-item-705']/a"));
        scrollToElement(webDriver, we);
        we.click();
        assertTrue(checkIsVisible(webDriver, By.partialLinkText("Browse open positions")));
    }

    @Test
    public void testSectionLinkContactLink () {
        webDriver.get("https://www.gurock.com/testrail/");

        WebElement we = findElement(webDriver, By.xpath("//li[@id='menu-item-706']/a"));
        scrollToElement(webDriver, we);
        we.click();
        assertTrue(checkIsVisible(webDriver, By.partialLinkText("Support Request Form")));
    }

    @Test
    public void testSectionLinkLegalLink () {
        webDriver.get("https://www.gurock.com/testrail/");

        WebElement we = findElement(webDriver, By.xpath("//li[@id='menu-item-2896']/a"));
        scrollToElement(webDriver, we);
        we.click();
        List<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
        findElement(webDriver,By.id("ui-id-2")).click();
        assertTrue(checkIsVisible(webDriver, By.xpath("//a[contains(text(), 'TestRail Cloud Terms of Service')]")));
        webDriver.switchTo().window(tabs.get(0));
        findElement(webDriver,By.xpath("//div[@class='col-6 col-sm-6 col-md-4 col-lg-3 gk-footer-menu-item-first']/a")).click();
        assertTrue(checkIsVisible(webDriver, By.xpath("//h1[contains(text(), 'Subscribe to TestRail News')]")));
    }

    @Test
    public void testSectionLinkComplianceLink () {
        webDriver.get("https://www.gurock.com/testrail/");

        WebElement we = findElement(webDriver, By.xpath("//li[@id='menu-item-9627']/a"));
        scrollToElement(webDriver, we);
        we.click();
        assertTrue(checkIsVisible(webDriver, By.xpath("//h1/span[contains(text(), 'TestRail SOC 2 Compliance & Certification')]")));
    }

    @Test
    public void testResponseFeaturesLink() throws IOException {
        webDriver.get("https://www.gurock.com/testrail/");
        WebElement scrollToElement = findElement(webDriver, By.xpath("//li[@id='menu-item-2474']/a"));
        scrollToElement(webDriver, scrollToElement);
        scrollToElement.click();
        String we = webDriver.getCurrentUrl();

        HttpURLConnection responseCode = (HttpURLConnection) new URL(we).openConnection();
        assertEquals(responseCode.getResponseCode(), 200, "Wrong response code");
    }
}