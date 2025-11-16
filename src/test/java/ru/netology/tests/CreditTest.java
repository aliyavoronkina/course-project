package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should successfully process credit with approved card")
    void shouldSuccessfullyGetCreditWithApprovedCard() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var creditPage = paymentPage.goToCreditPayment();
        var card = DataHelper.getApprovedCard();

        creditPage.fillCreditForm(card);
        creditPage.submitCreditRequest();
        creditPage.verifySuccessNotification();

        assertEquals("APPROVED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("Should decline credit with declined card")
    void shouldDeclineCreditWithDeclinedCard() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var creditPage = paymentPage.goToCreditPayment();
        var card = DataHelper.getDeclinedCard();

        creditPage.fillCreditForm(card);
        creditPage.submitCreditRequest();
        creditPage.verifyErrorNotification();

        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }
}