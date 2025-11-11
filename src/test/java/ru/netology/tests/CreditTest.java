package ru.netology.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.helpers.SQLHelper;
import ru.netology.pages.CreditPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditTest {

    @AfterEach
    void tearDown() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Успешное оформление кредита с одобренной картой")
    void shouldSuccessfullyGetCreditWithApprovedCard() {
        CreditPage creditPage = new CreditPage().openPage();

        creditPage.fillForm(DataHelper.getApprovedCard());
        creditPage.verifySuccessNotification();

        String status = SQLHelper.getCreditStatus();
        if (status != null) {
            assertEquals("APPROVED", status, "Статус кредита в БД должен быть APPROVED");
        }
    }

    @Test
    @DisplayName("Отклонение кредита с отклоненной картой")
    void shouldShowErrorWithDeclinedCardForCredit() {
        CreditPage creditPage = new CreditPage().openPage();

        creditPage.fillForm(DataHelper.getDeclinedCard());
        creditPage.verifyErrorNotification();

        String status = SQLHelper.getCreditStatus();
        if (status != null) {
            assertEquals("DECLINED", status, "Статус кредита в БД должен быть DECLINED");
        }
    }
}