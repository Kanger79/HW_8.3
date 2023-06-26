package ru.netology.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CallBackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
    }

    @BeforeEach
    void setUp() {driver=new ChromeDriver();
    }
    @AfterEach
    void tearDown() {
        driver.quit();
        driver=null;
    }

    @Test
    void shouldTestForm() {
        // Загрузить страницу
        // Поиск элементов
        // Взаимодействие с элементами


//        throw new UnsupportedOperationException();

    }
}
