package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class DebitTest {
    private PaymentPage paymentPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        SQLHelper.testConnection();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SQLHelper.cleanDatabase();
    }

    @BeforeEach
    public void setUp() {
        SQLHelper.cleanDatabase();
        paymentPage = open("http://localhost:8080", PaymentPage.class);
    }

    @Test
    @DisplayName("Should successfully pay with approved card")
    void shouldSuccessfullyPayWithApprovedCard() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getApprovedCard())
                .submitForm();

        debitPage.checkSuccessNotification();
    }

    @Test
    @DisplayName("Should show error with declined card")
    void shouldShowErrorWithDeclinedCard() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getDeclinedCard())
                .submitForm();

        debitPage.checkErrorNotification();
    }

    @Test
    @DisplayName("Should show error with empty card number")
    void shouldShowErrorWithEmptyCardNumber() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.enterMonth(DataHelper.getValidMonth())
                .enterYear(DataHelper.getValidYear())
                .enterHolder(DataHelper.getValidHolder())
                .enterCvc(DataHelper.getValidCvc())
                .submitForm();

        debitPage.checkCardNumberError();
    }

    @Test
    @DisplayName("Should show error with invalid month")
    void shouldShowErrorWithInvalidMonth() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getCardWithInvalidMonth())
                .submitForm();

        debitPage.checkMonthError();
    }

    @Test
    @DisplayName("Should show error with past year")
    void shouldShowErrorWithPastYear() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getCardWithPastYear())
                .submitForm();

        debitPage.checkYearError();
    }

    @Test
    @DisplayName("Should show error with cyrillic holder name")
    void shouldShowErrorWithCyrillicHolderName() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getCardWithCyrillicHolder())
                .submitForm();

        debitPage.checkHolderError();
    }
}