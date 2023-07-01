package ru.netology.domain;

import com.codeborne.selenide.Condition;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertFalse;

public class SelenideCardTest {

    @BeforeEach
    void openPage() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldTestInChromeSelenide() { //валидный тест

        $("[data-test-id=name] input").setValue("Алешин Сергей");
        $("[data-test-id=phone] input").setValue("+79181234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    void shouldTestEmptyName() {    //Пустое поле Имя

        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79181234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestEmptyPhone() {   // Пустое поле Телефон

        $("[data-test-id=name] input").setValue("Юрий Деточкин");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldTestNOCheckBox() {   // Не установлен чек-бокс

        $("[data-test-id=name] input").setValue("Иванов-Петров Максим");
        $("[data-test-id=phone] input").setValue("+79181234567");
        $("button").click();
        boolean actual = $("[data-test-id=agreement] span.checkbox__box").shouldBe(visible, Duration.ofSeconds(10)).isSelected();
        assertFalse(actual);
    }

    @Test
    void shouldTestInvalidName() {  // невалидное Имя (?!) (буква Ё)

        $("[data-test-id=name] input").setValue("Алёна Медведева");
        $("[data-test-id=phone] input").setValue("+79181234567");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(Condition.exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldTestInvalidPhone() {     // Невалидный номер телефона

        $("[data-test-id=name] input").setValue("Сергей Есенин");
        $("[data-test-id=phone] input").setValue("тел: +791878543");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(Condition.exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

}
