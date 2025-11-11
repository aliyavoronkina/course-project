package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage extends BasePage {

    private final SelenideElement cardNumberField = $("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("input[placeholder='08']");
    private final SelenideElement yearField = $("input[placeholder='22']");
    private final SelenideElement cardHolderField = $("fieldset > div:nth-child(3) input");
    private final SelenideElement cvvField = $("input[placeholder='999']");
    private final SelenideElement continueButton = $$("button").find(Condition.exactText("Продолжить"));

    public CreditPage openPage() {
        com.codeborne.selenide.Selenide.open("http://localhost:8080");
        $$("button").find(Condition.exactText("Купить в кредит")).click();
        return this;
    }

    public void fillForm(DataHelper.CardInfo card) {
        setValue(cardNumberField, card.getNumber());
        setValue(monthField, card.getMonth());
        setValue(yearField, card.getYear());
        setValue(cardHolderField, card.getHolder());
        setValue(cvvField, card.getCvc());
        clickContinueButton(continueButton);
    }

    public void verifySuccessNotification() {
        checkSuccessNotification();
    }

    public void verifyErrorNotification() {
        checkErrorNotification();
    }
}