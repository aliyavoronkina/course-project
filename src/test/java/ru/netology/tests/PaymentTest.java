package ru.netology.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.PaymentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentTest {

    @AfterEach
    void tearDown() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Успешная покупка тура по дебетовой карте")
    void shouldSuccessfullyPayWithApprovedCard() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getApprovedCard());
        paymentPage.verifySuccessNotification();

        String status = SQLHelper.getPaymentStatus();
        if (status != null) {
            assertEquals("APPROVED", status, "Статус платежа в БД должен быть APPROVED");
        }
    }

    @Test
    @DisplayName("Покупка тура с отклоненной картой должна показывать ошибку")
    void shouldShowErrorWithDeclinedCard() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getDeclinedCard());
        paymentPage.verifyErrorNotification();

        String status = SQLHelper.getPaymentStatus();
        if (status != null) {
            assertEquals("DECLINED", status, "Статус платежа в БД должен быть DECLINED");
        }
    }

    @Test
    @DisplayName("Пустой номер карты должен показывать ошибку валидации")
    void shouldShowValidationErrorForEmptyCardNumber() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillFormWithoutCardNumber(DataHelper.getApprovedCard());
        paymentPage.verifyCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("Неполный номер карты должен показывать ошибку валидации")
    void shouldShowValidationErrorForShortCardNumber() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getShortCardNumber());
        paymentPage.verifyCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("Месяц больше 12 должен показывать ошибку валидации")
    void shouldShowValidationErrorForInvalidMonth() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getInvalidMonth());
        paymentPage.verifyMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Прошедший месяц должен показывать ошибку валидации")
    void shouldShowValidationErrorForPastMonth() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getPastMonth());
        paymentPage.verifyMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Прошедший год должен показывать ошибку валидации")
    void shouldShowValidationErrorForPastYear() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getPastYear());
        paymentPage.verifyYearError("Истёк срок действия карты");
    }

    @Test
    @DisplayName("Владелец на кириллице должен показывать ошибку валидации")
    void shouldShowValidationErrorForCyrillicHolder() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getCyrillicHolder());
        paymentPage.verifyCardHolderError("Неверный формат");
    }

    @Test
    @DisplayName("CVC из двух цифр должен показывать ошибку валидации")
    void shouldShowValidationErrorForTwoDigitCvc() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillForm(DataHelper.getTwoDigitCvc());
        paymentPage.verifyCvcError("Неверный формат");
    }

    @Test
    @DisplayName("Пустое поле владельца должно показывать ошибку валидации")
    void shouldShowValidationErrorForEmptyHolder() {
        PaymentPage paymentPage = new PaymentPage().openPage();

        paymentPage.fillFormWithoutCardHolder(DataHelper.getApprovedCard());
        paymentPage.verifyCardHolderError("Поле обязательно для заполнения");
    }
}