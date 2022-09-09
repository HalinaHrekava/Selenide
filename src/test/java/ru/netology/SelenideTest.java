package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;


import java.time.Duration;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

import java.time.LocalDate;


public class SelenideTest {
    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void shouldAll() {
        String planningDate = generateDate(4);
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Уфа");
        $x("//input [@placeholder='Дата встречи']").doubleClick();
        $x("//input [@placeholder='Дата встречи']").sendKeys(Keys.DELETE);
        $x("//input [@placeholder='Дата встречи']").setValue(planningDate);
        $x("//input[@name='name']").setValue("Иван Петров-Сидоров");
        $x("//input[@name='phone']").setValue("+37529000000");
        $x("//span[@class='checkbox__box']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@class='notification notification_visible notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']").
                should(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }


}
