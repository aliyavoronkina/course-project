package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {
    private SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement cardMonthField = $("input[placeholder='08']");
    private SelenideElement cardYearField = $("input[placeholder='22']");
    private SelenideElement cardHolderField = $("fieldset > div:nth-child(3) input");
    private SelenideElement cardCvcField = $("input[placeholder='999']");
    private SelenideElement continueButton = $$("button").findBy(text("Продолжить"));

    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");
    private SelenideElement formHeading = $$(".heading").findBy(text("Оплата по карте"));

    public DebitPage() {
        formHeading.shouldBe(visible, Duration.ofSeconds(10));
    }

    // Публичные методы для заполнения полей
    public DebitPage enterCardNumber(String cardNumber) {
        cardNumberField.setValue(cardNumber);
        return this;
    }

    public DebitPage enterMonth(String month) {
        cardMonthField.setValue(month);
        return this;
    }

    public DebitPage enterYear(String year) {
        cardYearField.setValue(year);
        return this;
    }

    public DebitPage enterHolder(String holder) {
        cardHolderField.setValue(holder);
        return this;
    }

    public DebitPage enterCvc(String cvc) {
        cardCvcField.setValue(cvc);
        return this;
    }

    // Основной метод заполнения формы
    public DebitPage fillForm(CardInfo card) {
        enterCardNumber(card.getNumber());
        enterMonth(card.getMonth());
        enterYear(card.getYear());
        enterHolder(card.getHolder());
        enterCvc(card.getCvc());
        return this;
    }

    public void submitForm() {
        continueButton.click();
    }

    // Публичные методы проверки уведомлений
    public void checkSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Успешно"));
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Ошибка"));
    }

    // Публичные методы проверки ошибок полей
    public void checkCardNumberError() {
        cardNumberField.closest(".input").$(".input__sub")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Неверный формат"));
    }

    public void checkMonthError() {
        cardMonthField.closest(".input").$(".input__sub")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Неверно указан срок действия карты"));
    }

    public void checkYearError() {
        cardYearField.closest(".input").$(".input__sub")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Истёк срок действия карты"));
    }

    public void checkHolderError() {
        cardHolderField.closest(".input").$(".input__sub")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Поле обязательно для заполнения"));
    }

    public void checkCvcError() {
        cardCvcField.closest(".input").$(".input__sub")
                .shouldBe(visible, Duration.ofSeconds(5))
                .shouldHave(text("Неверный формат"));
    }
}