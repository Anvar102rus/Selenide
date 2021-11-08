package ru.netology;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.Condition.visible;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrder {
    public String getDate(int days, String formattedDate) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(formattedDate));
    }

    @Test
    void shouldCheckRegistrationForm() {
        String DateOfMeeting = getDate(3, "dd.MM.yyyy");

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Уфа");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(DateOfMeeting);
        $("[data-test-id=name] input").setValue("Анвар");
        $("[data-test-id=phone] input").setValue("+79999999999");
        $("[data-test-id=agreement]").click();
        $(By.className("button")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(20));
    }
}
