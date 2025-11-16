package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.CardInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {

    // Доменный метод для заполнения формы
    public void fillPaymentForm(CardInfo card) {
        enterCardNumber(card.getNumber());
        enterMonth(card.getMonth());
        enterYear(card.getYear());
        enterHolder(card.getHolder());
        enterCvc(card.getCvc());
    }

    // Доменный метод для отправки формы
    public void submitPayment() {
        getContinueButton().click();
    }

    // Доменные методы для проверок
    public void verifySuccessNotification() {
        getSuccessNotification().shouldBe(visible, Duration.ofSeconds(15));
    }

    public void verifyErrorNotification() {
        getErrorNotification().shouldBe(visible, Duration.ofSeconds(15));
    }

    // Приватные методы для взаимодействия с элементами
    private void enterCardNumber(String value) {
        getCardNumberField().setValue(value);
    }

    private void enterMonth(String value) {
        getMonthField().setValue(value);
    }

    private void enterYear(String value) {
        getYearField().setValue(value);
    }

    private void enterHolder(String value) {
        getHolderField().setValue(value);
    }

    private void enterCvc(String value) {
        getCvcField().setValue(value);
    }

    private SelenideElement getCardNumberField() {
        return $("input[placeholder='0000 0000 0000 0000']");
    }

    private SelenideElement getMonthField() {
        return $("input[placeholder='08']");
    }

    private SelenideElement getYearField() {
        return $("input[placeholder='22']");
    }

    private SelenideElement getHolderField() {
        return $("fieldset > div:nth-child(3) input");
    }

    private SelenideElement getCvcField() {
        return $("input[placeholder='999']");
    }

    private SelenideElement getContinueButton() {
        return $$("button").findBy(text("Продолжить"));
    }

    private SelenideElement getSuccessNotification() {
        return $(".notification_status_ok");
    }

    private SelenideElement getErrorNotification() {
        return $(".notification_status_error");
    }
}