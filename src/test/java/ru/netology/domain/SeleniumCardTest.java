package ru.netology.domain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SeleniumCardTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
        //       WebDriverManager.chromedriver().setup();    // для Chrome
        WebDriverManager.edgedriver().setup();    // для MS Edge
    }

    @BeforeEach
    void setUp() {
//        driver = new ChromeDriver();    // для Chrome
//        driver = new EdgeDriver();    // для Edge
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new EdgeDriver(options);
        driver.get("http://localhost:9999");

    }


    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestInChromeSelenium() { //Валидный тест

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78002008002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actual.trim());
    }

    @Test
    void shouldTestEmptyName() {    // Пустое поле Имя
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78002008002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", actual.trim());
    }

    @Test
    void shouldTestEmptyPhone() {   // Пустое поле Телефон

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов-Петров Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        assertEquals("Поле обязательно для заполнения", actual.trim());
    }

    @Test
    void shouldTestNOCheckBox() {   // Не установлен чек-бокс

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов-Петров Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("");
        driver.findElement(By.className("button")).click();
        boolean actual = driver.findElement(By.cssSelector("[data-test-id=agreement] span.checkbox__box")).isSelected();
        assertEquals(false, actual);
    }

    @Test
    void shouldTestInvalidName() {  // Не валидное Имя
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("ПетрOFF Maxim");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+78002008002");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id=name].input_invalid .input__sub")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", actual.trim());
    }

    @Test
    void shouldTestInvalidPhone() {     // Не валидный Телефон

        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Иванов-Петров Максим");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+7928723");
        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
        driver.findElement(By.className("button")).click();
        String actual = driver.findElement(By.cssSelector("[data-test-id=phone].input_invalid .input__sub")).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", actual.trim());
    }

}
