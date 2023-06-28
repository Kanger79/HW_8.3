package ru.netology.domain;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardTest {
//    private static WebDriver driver;
//
//    static ChromeOptions options = new ChromeOptions();
//
//    @BeforeAll
//    static void setUpAll() {
//        WebDriverManager.chromedriver().setup();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
//        driver = new ChromeDriver(options);
//    }
//
//    @BeforeEach
//    void setUp() {
//        ChromeDriver driver = new ChromeDriver();
//        //  EdgeDriver edgeDriver = new EdgeDriver();
//        ChromeOptions options = new ChromeOptions();
//
//    }
//
//
//    @AfterEach
//    void tearDown() {
//        driver.quit();
//        driver = null;
//    }
//
//    @Test
//    void shouldTestForm() throws InterruptedException {
//        // Загрузить страницу
//        driver.get("http://localhost:9999");
//        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Петров Петр");
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78002008002");
//        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//        driver.findElement(By.className("button")).click();
//        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
//        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actual.trim());
//        Thread.sleep(5000);
//    }

    @Test
    void shouldTestInChromeSelenide() throws InterruptedException {
        open("http://localhost:9999/");
        $("[data-test-id=name] input").setValue("Алешин Сергей");
        $("[data-test-id=phone] input").setValue("+79181234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

 //       Thread.sleep(3000);
    }



}
