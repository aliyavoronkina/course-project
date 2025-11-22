package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class DebitPage {

    // Поля ввода
    private SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("input[placeholder='08']");
    private SelenideElement yearField = $("input[placeholder='22']");
    private SelenideElement holderField = $("fieldset > div:nth-child(3) input");
    private SelenideElement cvcField = $("input[placeholder='999']");
    private SelenideElement continueButton = $$("button").findBy(text("Продолжить"));
    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    // Селекторы для ошибок полей
    private SelenideElement cardNumberError = $x("//span[contains(text(),'Номер карты')]/following-sibling::span[@class='input__sub']");
    private SelenideElement monthError = $x("//span[contains(text(),'Месяц')]/following-sibling::span[@class='input__sub']");
    private SelenideElement yearError = $x("//span[contains(text(),'Год')]/following-sibling::span[@class='input__sub']");
    private SelenideElement holderError = $x("//span[contains(text(),'Владелец')]/following-sibling::span[@class='input__sub']");
    private SelenideElement cvcError = $x("//span[contains(text(),'CVC/CVV')]/following-sibling::span[@class='input__sub']");

    // Методы проверки ошибок
    public void verifyCardNumberFieldError() {
        cardNumberError.shouldBe(visible, Duration.ofSeconds(5));
    }

    public void verifyMonthFieldError() {
        monthError.shouldBe(visible, Duration.ofSeconds(5));
    }

    public void verifyYearFieldError() {
        yearError.shouldBe(visible, Duration.ofSeconds(5));
    }

    public void verifyHolderFieldError() {
        holderError.shouldBe(visible, Duration.ofSeconds(5));
    }

    public void verifyCvcFieldError() {
        cvcError.shouldBe(visible, Duration.ofSeconds(5));
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
        errorNotification.shouldBe(visible, Duration.ofSeconds(45));
        errorNotification.shouldHave(text("Ошибка! Банк отказал в проведении операции"));
    }
}