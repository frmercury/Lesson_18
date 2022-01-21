package com.ex.HW_19;

import com.ex.BaseTest;
import jdk.management.resource.ResourceRequestDeniedException;
import org.apache.hc.client5.http.HttpHostConnectException;
import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class HomeWork_19 extends BaseTest {

    @Test
    public void fileUpload() {
        webDriver.get("https://theautomationzone.blogspot.com/");
        WebElement chooseFile = webDriver.findElement(By.id("photo"));
        String filename = "src/main/resources/photo.txt";
        File file = new File(filename);
        String path = file.getAbsolutePath();
        chooseFile.sendKeys(path);
        webDriver.findElement(By.id("submit")).click();
    }

    @Test
    public void randomCheckBox() {
        webDriver.get("https://theautomationzone.blogspot.com/");
        List<WebElement> elementList = webDriver.findElements(By.xpath("//div[@class='divTableCell'][3]"));

        By checkBoxRelative = By.xpath(".//preceding-sibling::div/input");
        Random random = new Random();
        WebElement randomElement = null;

        for (int i = 0; i <= 7; i++) {
            randomElement = elementList.get(random.nextInt(elementList.size()));

            if (randomElement.findElement(checkBoxRelative).isSelected()) {
                continue;
            } else {
                randomElement.findElement(checkBoxRelative).click();
            }
        }

        List<WebElement> inputList = webDriver.findElements(By.xpath("//div[@class='divTableCell']/input"));

        int checkboxCounter = 0;

        for (WebElement webElement: inputList) {
            if (webElement.isDisplayed()) {
                 checkboxCounter++;
             } else {
                continue;
            }
        }
        assertTrue(checkboxCounter > 4, "Количество отмеченных чекбосов не меньше " + checkboxCounter);
    }

    @Test
    public void randomTab() {
        try {
            webDriver.get("http://automationpractice.com/");
            String checkedUrl = webDriver.getCurrentUrl();
            List<WebElement> tabList = webDriver.findElements(By.xpath("//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li"));
            Random random = new Random();
            WebElement randomTab = tabList.get(random.nextInt(tabList.size()));
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
            randomTab.click();

            assertTrue(!webDriver.getCurrentUrl().equals(checkedUrl));
        } catch (Exception e){
            throw new ResourceRequestDeniedException("Site unreachable, HTTP ERROR 500");
        }
    }

    @Test
    public void arithmeticOperation() {
        webDriver.get("https://theautomationzone.blogspot.com/");
        webDriver.findElement(By.xpath("//input[@value='5']")).click();
        webDriver.findElement(By.xpath("//input[@value='*']")).click();
        webDriver.findElement(By.xpath("//input[@value='5']")).click();
        webDriver.findElement(By.xpath("//input[@value='=']")).click();
        assertEquals(webDriver.findElement(By.xpath("//input[@id='result']")).getAttribute("value"), "25");
    }
}