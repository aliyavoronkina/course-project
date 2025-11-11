package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper.CardInfo;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PaymentPage extends BasePage {

    public PaymentPage openPage() {
        open(System.getProperty("app.url", "http://localhost:8080"));
        $(byText("Купить")).click();
        return this;
    }

    private SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("input[placeholder='08']");
    private SelenideElement yearField = $("input[placeholder='22']");
    private SelenideElement cardHolderField = $(byText("Владелец")).parent().$("input");
    private SelenideElement cvcField = $("input[placeholder='999']");
    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement successNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    private SelenideElement getCardNumberError() {
        return cardNumberField.parent().parent().$(".input__sub");
    }

    private SelenideElement getMonthError() {
        return monthField.parent().parent().$(".input__sub");
    }

    private SelenideElement getYearError() {
        return yearField.parent().parent().$(".input__sub");
    }

    private SelenideElement getCardHolderError() {
        return cardHolderField.parent().parent().$(".input__sub");
    }

    private SelenideElement getCvcError() {
        return cvcField.parent().parent().$(".input__sub");
    }

    public void enterCardData(CardInfo cardInfo) {
        setValue(cardNumberField, cardInfo.getNumber());
        setValue(monthField, cardInfo.getMonth());
        setValue(yearField, cardInfo.getYear());
        setValue(cardHolderField, cardInfo.getHolder());
        setValue(cvcField, cardInfo.getCvc());
    }

    public void submitForm() {
        continueButton.click();
    }

    public void fillForm(CardInfo cardInfo) {
        enterCardData(cardInfo);
        submitForm();
    }

    public void verifySuccessNotification() {
        checkNotification(successNotification, true);
    }

    public void verifyErrorNotification() {
        checkNotification(errorNotification, true);
    }

    public void verifyCardNumberError(String expectedError) {
        checkFieldError(getCardNumberError(), expectedError);
    }

    public void verifyMonthError(String expectedError) {
        checkFieldError(getMonthError(), expectedError);
    }

    public void verifyYearError(String expectedError) {
        checkFieldError(getYearError(), expectedError);
    }

    public void verifyCardHolderError(String expectedError) {
        checkFieldError(getCardHolderError(), expectedError);
    }

    public void verifyCvcError(String expectedError) {
        checkFieldError(getCvcError(), expectedError);
    }

    public void fillFormWithoutCardNumber(CardInfo cardInfo) {
        setValue(monthField, cardInfo.getMonth());
        setValue(yearField, cardInfo.getYear());
        setValue(cardHolderField, cardInfo.getHolder());
        setValue(cvcField, cardInfo.getCvc());
        submitForm();
    }

    public void fillFormWithoutCardHolder(CardInfo cardInfo) {
        setValue(cardNumberField, cardInfo.getNumber());
        setValue(monthField, cardInfo.getMonth());
        setValue(yearField, cardInfo.getYear());
        setValue(cvcField, cardInfo.getCvc());
        submitForm();
    }
}