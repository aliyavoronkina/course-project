package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitTest {

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
    @DisplayName("Should successfully process payment with approved card")
    void shouldSuccessfullyPayWithApprovedCard() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var card = DataHelper.getApprovedCard();

        debitPage.fillPaymentForm(card);
        debitPage.submitPayment();
        debitPage.verifySuccessNotification();

        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Should decline payment with declined card")
    void shouldDeclinePaymentWithDeclinedCard() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var card = DataHelper.getDeclinedCard();

        debitPage.fillPaymentForm(card);
        debitPage.submitPayment();
        debitPage.verifyErrorNotification();

        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
    }
}