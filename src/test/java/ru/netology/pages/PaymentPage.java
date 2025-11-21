package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    public DebitPage goToDebitPayment() {
        getBuyButton().click();
        return new DebitPage();
    }

    public CreditPage goToCreditPayment() {
        getCreditButton().click();
        return new CreditPage();
    }

    private SelenideElement getBuyButton() {
        return $$("button").findBy(text("Купить"));
    }

    private SelenideElement getCreditButton() {
        return $$("button").findBy(text("Купить в кредит"));
    }
}