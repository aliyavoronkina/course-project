package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {
    private SelenideElement payWithDebitCardButton = $$("button").findBy(text("Купить"));
    private SelenideElement payWithCreditCardButton = $$("button").findBy(text("Купить в кредит"));
    private SelenideElement pageHeading = $$(".heading").findBy(text("Путешествие дня"));

    public PaymentPage() {
        pageHeading.shouldBe(visible, Duration.ofSeconds(10));
    }

    public DebitPage selectDebitPayment() {
        payWithDebitCardButton.click();
        return new DebitPage();
    }

    public CreditPage selectCreditPayment() {
        payWithCreditCardButton.click();
        return new CreditPage();
    }
}