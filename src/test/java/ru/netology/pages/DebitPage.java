package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {

    // Объявляем элементы как поля класса
    private SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("input[placeholder='08']");
    private SelenideElement yearField = $("input[placeholder='22']");
    private SelenideElement holderField = $("fieldset > div:nth-child(3) input");
    private SelenideElement cvcField = $("input[placeholder='999']");
    private SelenideElement continueButton = $$("button").findBy(text("Продолжить"));
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");


    // Добавьте эти элементы для проверки ошибок полей
    private SelenideElement cardNumberError = $(".input__sub[data-test-id='number'] .input__sub");
    private SelenideElement monthError = $(".input__sub[data-test-id='month'] .input__sub");
    private SelenideElement yearError = $(".input__sub[data-test-id='year'] .input__sub");
    private SelenideElement holderError = $(".input__sub[data-test-id='holder'] .input__sub");
    private SelenideElement cvcError = $(".input__sub[data-test-id='cvc'] .input__sub");

    // Добавьте методы проверки ошибок
    public void verifyCardNumberFieldError() {
        cardNumberError.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void verifyMonthFieldError() {
        monthError.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void verifyYearFieldError() {
        yearError.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void verifyHolderFieldError() {
        holderError.shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
    }

    public void verifyCvcFieldError() {
        cvcError.shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    // Доменный метод для заполнения формы
    public void fillPaymentForm(CardInfo card) {
        cardNumberField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        holderField.setValue(card.getHolder());
        cvcField.setValue(card.getCvc());
    }

    // Доменный метод для отправки формы
    public void submitPayment() {
        continueButton.click();
    }

    // Доменные методы для проверок
    public void verifySuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(15));
        successNotification.shouldHave(text("Операция одобрена банком"));
    }

    public void verifyErrorNotification() {
        // Ждем появления элемента с увеличенным временем
        errorNotification.shouldBe(visible, Duration.ofSeconds(30));
        errorNotification.shouldHave(text("Ошибка! Банк отказал в проведении операции"));
    }
}