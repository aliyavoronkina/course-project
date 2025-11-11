package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

public class BasePage {

    protected void setValue(SelenideElement element, String value) {
        if (value != null && !value.isEmpty()) {
            element.setValue(value);
        }
    }

    protected void checkNotification(SelenideElement notification, boolean shouldBeVisible) {
        if (shouldBeVisible) {
            notification.shouldBe(Condition.visible, Duration.ofSeconds(15));
        } else {
            notification.shouldNotBe(Condition.visible);
        }
    }

    protected void checkFieldError(SelenideElement field, String expectedError) {
        if (expectedError != null && !expectedError.isEmpty()) {
            field.shouldHave(Condition.exactText(expectedError));
        }
    }
}
