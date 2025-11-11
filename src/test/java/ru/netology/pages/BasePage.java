package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    protected void setValue(SelenideElement element, String value) {
        if (value != null && !value.isEmpty()) {
            element.setValue(value);
        }
    }

    protected void checkSuccessNotification() {
        $(".notification_status_ok")
                .shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    protected void checkErrorNotification() {
        $(".notification_status_error")
                .shouldBe(Condition.visible, Duration.ofSeconds(20));
    }

    protected void clickContinueButton(SelenideElement button) {
        button.click();
    }
}