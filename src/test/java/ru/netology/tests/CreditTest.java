package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class CreditTest {
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
    @DisplayName("Should successfully get credit with approved card")
    void shouldSuccessfullyGetCreditWithApprovedCard() {
        var creditPage = paymentPage.selectCreditPayment();
        creditPage.fillForm(DataHelper.getApprovedCard())
                .submitForm();

        creditPage.checkSuccessNotification();
    }

    @Test
    @DisplayName("Should show error when getting credit with declined card")
    void shouldShowErrorWhenGettingCreditWithDeclinedCard() {
        var creditPage = paymentPage.selectCreditPayment();
        creditPage.fillForm(DataHelper.getDeclinedCard())
                .submitForm();

        creditPage.checkErrorNotification();
    }

    @Test
    @DisplayName("Should show error with empty card number for credit")
    void shouldShowErrorWithEmptyCardNumberForCredit() {
        var creditPage = paymentPage.selectCreditPayment();
        creditPage.enterMonth(DataHelper.getValidMonth())
                .enterYear(DataHelper.getValidYear())
                .enterHolder(DataHelper.getValidHolder())
                .enterCvc(DataHelper.getValidCvc())
                .submitForm();

        creditPage.checkCardNumberError();
    }

    @Test
    @DisplayName("Should show error with invalid month for credit")
    void shouldShowErrorWithInvalidMonthForCredit() {
        var creditPage = paymentPage.selectCreditPayment();
        creditPage.fillForm(DataHelper.getCardWithInvalidMonth())
                .submitForm();

        creditPage.checkMonthError();
    }

    @Test
    @DisplayName("Should show error with past year for credit")
    void shouldShowErrorWithPastYearForCredit() {
        var creditPage = paymentPage.selectCreditPayment();
        creditPage.fillForm(DataHelper.getCardWithPastYear())
                .submitForm();

        creditPage.checkYearError();
    }

    @Test
    @DisplayName("Should show error with cyrillic holder name for credit")
    void shouldShowErrorWithCyrillicHolderNameForCredit() {
        var creditPage = paymentPage.selectCreditPayment();
        creditPage.fillForm(DataHelper.getCardWithCyrillicHolder())
                .submitForm();

        creditPage.checkHolderError();
    }
}