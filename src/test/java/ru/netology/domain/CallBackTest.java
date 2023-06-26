package ru.netology.domain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class CallBackTest {
    private static WebDriver driver;

    static ChromeOptions options = new ChromeOptions();

    @BeforeAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    void setUp() {driver=new ChromeDriver();
        ChromeOptions options = new ChromeOptions();

    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver=null;
    }

    @Test
    void shouldTestForm() {
        // Загрузить страницу
        driver.get ("http://localhost:9999");
        // Поиск элементов
        // Взаимодействие с элементами


//        throw new UnsupportedOperationException();

    }
}
