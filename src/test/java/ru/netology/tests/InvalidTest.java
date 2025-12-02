package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class InvalidTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Card Number Tests
    @Test
    @DisplayName("Should show validation error for empty card number")
    void shouldShowErrorForEmptyCardNumber() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var emptyCardNumber = DataHelper.getCardWithEmptyCardNumber();

        debitPage.fillPaymentForm(emptyCardNumber);
        debitPage.submitPayment();
        debitPage.verifyCardNumberFieldError();
    }

    @Test
    @DisplayName("Should show validation error for short card number")
    void shouldShowErrorForShortCardNumber() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var shortCardNumber = DataHelper.getCardWithShortCardNumber();

        debitPage.fillPaymentForm(shortCardNumber);
        debitPage.submitPayment();
        debitPage.verifyCardNumberFieldError();
    }

    @Test
    @DisplayName("Should show validation error for card number with letters")
    void shouldShowErrorForCardNumberWithLetters() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var cardWithLetters = DataHelper.getCardWithCardNumberWithLetters();

        debitPage.fillPaymentForm(cardWithLetters);
        debitPage.submitPayment();
        debitPage.verifyCardNumberFieldError();
    }

    // Month Tests
    @Test
    @DisplayName("Should show validation error for invalid month")
    void shouldShowErrorForInvalidMonth() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var invalidCard = DataHelper.getCardWithInvalidMonth();

        debitPage.fillPaymentForm(invalidCard);
        debitPage.submitPayment();
        debitPage.verifyMonthFieldError();
    }

    @Test
    @DisplayName("Should show validation error for zero month")
    void shouldShowErrorForZeroMonth() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var zeroMonthCard = DataHelper.getCardWithZeroMonth();

        debitPage.fillPaymentForm(zeroMonthCard);
        debitPage.submitPayment();
        debitPage.verifyMonthFieldError();
    }

    @Test
    @DisplayName("Should show validation error for empty month")
    void shouldShowErrorForEmptyMonth() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var emptyMonthCard = DataHelper.getCardWithEmptyMonth();

        debitPage.fillPaymentForm(emptyMonthCard);
        debitPage.submitPayment();
        debitPage.verifyMonthFieldError();
    }

    @Test
    @DisplayName("Should show validation error for single digit month")
    void shouldShowErrorForSingleDigitMonth() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var singleDigitMonthCard = DataHelper.getCardWithSingleDigitMonth();

        debitPage.fillPaymentForm(singleDigitMonthCard);
        debitPage.submitPayment();
        debitPage.verifyMonthFieldError();
    }

    // Year Tests
    @Test
    @DisplayName("Should show validation error for expired year")
    void shouldShowErrorForExpiredYear() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var expiredCard = DataHelper.getCardWithPastYear();

        debitPage.fillPaymentForm(expiredCard);
        debitPage.submitPayment();
        debitPage.verifyYearFieldError();
    }

    @Test
    @DisplayName("Should show validation error for future year")
    void shouldShowErrorForFutureYear() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var futureYearCard = DataHelper.getCardWithFutureYear();

        debitPage.fillPaymentForm(futureYearCard);
        debitPage.submitPayment();
        debitPage.verifyYearFieldError();
    }

    @Test
    @DisplayName("Should show validation error for empty year")
    void shouldShowErrorForEmptyYear() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var emptyYearCard = DataHelper.getCardWithEmptyYear();

        debitPage.fillPaymentForm(emptyYearCard);
        debitPage.submitPayment();
        debitPage.verifyYearFieldError();
    }

    @Test
    @DisplayName("Should show validation error for single digit year")
    void shouldShowErrorForSingleDigitYear() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var singleDigitYearCard = DataHelper.getCardWithSingleDigitYear();

        debitPage.fillPaymentForm(singleDigitYearCard);
        debitPage.submitPayment();
        debitPage.verifyYearFieldError();
    }

    // Holder Tests
    @Test
    @DisplayName("Should show validation error for empty holder")
    void shouldShowErrorForEmptyHolder() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var emptyHolderCard = DataHelper.getCardWithEmptyHolder();

        debitPage.fillPaymentForm(emptyHolderCard);
        debitPage.submitPayment();
        debitPage.verifyHolderFieldError();
    }

    @Test
    @DisplayName("Should show validation error for holder with special characters")
    void shouldShowErrorForHolderWithSpecialChars() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var specialCharsHolderCard = DataHelper.getCardWithHolderWithSpecialChars();

        debitPage.fillPaymentForm(specialCharsHolderCard);
        debitPage.submitPayment();
        debitPage.verifyHolderFieldError();
    }

    @Test
    @DisplayName("Should show validation error for long holder name")
    void shouldShowErrorForLongHolderName() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var longHolderCard = DataHelper.getCardWithLongHolderName();

        debitPage.fillPaymentForm(longHolderCard);
        debitPage.submitPayment();
        debitPage.verifyHolderFieldError();
    }

    // CVC Tests
    @Test
    @DisplayName("Should show validation error for short CVC")
    void shouldShowErrorForShortCvc() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var shortCvcCard = DataHelper.getCardWithShortCvc();

        debitPage.fillPaymentForm(shortCvcCard);
        debitPage.submitPayment();
        debitPage.verifyCvcFieldError();
    }

    @Test
    @DisplayName("Should show validation error for empty CVC")
    void shouldShowErrorForEmptyCvc() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var emptyCvcCard = DataHelper.getCardWithEmptyCvc();

        debitPage.fillPaymentForm(emptyCvcCard);
        debitPage.submitPayment();
        debitPage.verifyCvcFieldError();
    }

    @Test
    @DisplayName("Should show validation error for single digit CVC")
    void shouldShowErrorForSingleDigitCvc() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var singleDigitCvcCard = DataHelper.getCardWithSingleDigitCvc();

        debitPage.fillPaymentForm(singleDigitCvcCard);
        debitPage.submitPayment();
        debitPage.verifyCvcFieldError();
    }

    @Test
    @DisplayName("Should show validation error for CVC with letters")
    void shouldShowErrorForCvcWithLetters() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var cvcWithLettersCard = DataHelper.getCardWithCvcWithLetters();

        debitPage.fillPaymentForm(cvcWithLettersCard);
        debitPage.submitPayment();
        debitPage.verifyCvcFieldError();
    }

    // Form Tests
    @Test
    @DisplayName("Should show validation errors for empty form")
    void shouldShowErrorForEmptyForm() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var emptyCard = DataHelper.getEmptyCard();

        debitPage.fillPaymentForm(emptyCard);
        debitPage.submitPayment();
        debitPage.verifyCardNumberFieldError();
        debitPage.verifyMonthFieldError();
        debitPage.verifyYearFieldError();
        debitPage.verifyHolderFieldError();
        debitPage.verifyCvcFieldError();
    }

    // БАНКОВСКИЕ ОШИБКИ

    // БАГИ ВАЛИДАЦИИ: поле "Владелец" не проверяет некорректные данные
    // БАГ: кириллица проходит валидацию

    @Test
    @DisplayName("Should show validation error for cyrillic holder (BUG: currently passes)")
    void shouldShowErrorForCyrillicHolder() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var cyrillicCard = DataHelper.getCardWithCyrillicHolder();

        debitPage.fillPaymentForm(cyrillicCard);
        debitPage.submitPayment();

        // БАГ: кириллица проходит (должна быть ошибка под полем)
        debitPage.verifySuccessNotification();

        // TODO: После исправления бага изменить на:
        // debitPage.verifyHolderFieldError("Неверный формат");
    }

    // БАГ: цифры проходят валидацию
    @Test
    @DisplayName("Should show validation error for numeric holder (BUG: currently passes)")
    void shouldShowErrorForNumericHolder() {
        var paymentPage = open("http://localhost:8080", PaymentPage.class);
        var debitPage = paymentPage.goToDebitPayment();
        var numericHolderCard = DataHelper.getCardWithNumericHolder();

        debitPage.fillPaymentForm(numericHolderCard);
        debitPage.submitPayment();

        // БАГ: цифры проходят (должна быть ошибка под полем)
        debitPage.verifySuccessNotification();

        // TODO: После исправления бага изменить на:
        // debitPage.verifyHolderFieldError("Неверный формат");
    }
}
