package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class InvalidTest {
    private PaymentPage paymentPage;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void setUp() {
        paymentPage = open("http://localhost:8080", PaymentPage.class);
    }

    @Test
    @DisplayName("Should show error with short CVC")
    void shouldShowErrorWithShortCvc() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getCardWithShortCvc())
                .submitForm();

        debitPage.checkCvcError();
    }

    @Test
    @DisplayName("Should show error with numeric holder")
    void shouldShowErrorWithNumericHolder() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getCardWithNumericHolder())
                .submitForm();

        debitPage.checkHolderError();
    }

    @Test
    @DisplayName("Should show error with zero month")
    void shouldShowErrorWithZeroMonth() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getCardWithZeroMonth())
                .submitForm();

        debitPage.checkMonthError();
    }

    @Test
    @DisplayName("Should show error with future year")
    void shouldShowErrorWithFutureYear() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.fillForm(DataHelper.getCardWithFutureYear())
                .submitForm();

        debitPage.checkYearError();
    }

    @Test
    @DisplayName("Should show error with empty holder")
    void shouldShowErrorWithEmptyHolder() {
        var debitPage = paymentPage.selectDebitPayment();
        debitPage.enterCardNumber(DataHelper.getApprovedCardNumber())
                .enterMonth(DataHelper.getValidMonth())
                .enterYear(DataHelper.getValidYear())
                .enterCvc(DataHelper.getValidCvc())
                .submitForm();

        debitPage.checkHolderError();
    }
}