package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage extends BasePage {

    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement cardHolderField = $("fieldset > div:nth-child(3) input");
    private final SelenideElement cvvField = $("input[placeholder='999']");
    private final SelenideElement continueButton = $$("button").find(Condition.exactText("Продолжить"));

    // Универсальный селектор для ошибок
    private final SelenideElement errorElement = $(".input__sub");

    public PaymentPage openPage() {
        com.codeborne.selenide.Selenide.open("http://localhost:8080");
        $$("button").find(Condition.exactText("Купить")).click();
        return this;
    }

    // Основной метод заполнения формы
    public void fillForm(DataHelper.CardInfo card) {
        setValue(cardNumberField, card.getNumber());
        setValue(monthField, card.getMonth());
        setValue(yearField, card.getYear());
        setValue(cardHolderField, card.getHolder());
        setValue(cvvField, card.getCvc());
        clickContinueButton(continueButton);
    }

    // Заполнение формы без номера карты
    public void fillFormWithoutCardNumber(DataHelper.CardInfo card) {
        cardNumberField.clear();
        setValue(monthField, card.getMonth());
        setValue(yearField, card.getYear());
        setValue(cardHolderField, card.getHolder());
        setValue(cvvField, card.getCvc());
        clickContinueButton(continueButton);
    }

    // Заполнение формы без владельца
    public void fillFormWithoutCardHolder(DataHelper.CardInfo card) {
        setValue(cardNumberField, card.getNumber());
        setValue(monthField, card.getMonth());
        setValue(yearField, card.getYear());
        cardHolderField.clear();
        setValue(cvvField, card.getCvc());
        clickContinueButton(continueButton);
    }

    // Проверка уведомлений
    public void verifySuccessNotification() {
        checkSuccessNotification();
    }

    public void verifyErrorNotification() {
        checkErrorNotification();
    }

    // Универсальные методы проверки ошибок
    public void verifyCardNumberError(String expectedError) {
        checkFieldError(expectedError);
    }

    public void verifyMonthError(String expectedError) {
        checkFieldError(expectedError);
    }

    public void verifyYearError(String expectedError) {
        checkFieldError(expectedError);
    }

    public void verifyCardHolderError(String expectedError) {
        checkFieldError(expectedError);
    }

    public void verifyCvcError(String expectedError) {
        checkFieldError(expectedError);
    }

    // Универсальный метод проверки ошибки
    private void checkFieldError(String expectedError) {
        errorElement.shouldHave(Condition.exactText(expectedError))
                .shouldBe(Condition.visible);
    }
}