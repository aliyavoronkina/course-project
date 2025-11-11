package ru.netology.data;

import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    // Валидные тестовые данные
    public static CardInfo getApprovedCard() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "IVAN IVANOV", "123");
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo("5555 6666 7777 8888", getValidMonth(), getValidYear(), "IVAN IVANOV", "123");
    }

    // Невалидные данные для поля "Номер карты"
    public static CardInfo getEmptyCardNumber() {
        return new CardInfo("", getValidMonth(), getValidYear(), "IVAN IVANOV", "123");
    }

    public static CardInfo getShortCardNumber() {
        return new CardInfo("1111 2222 3333 444", getValidMonth(), getValidYear(), "IVAN IVANOV", "123");
    }

    public static CardInfo getRandomCardNumber() {
        return new CardInfo("1111 2222 3333 4445", getValidMonth(), getValidYear(), "IVAN IVANOV", "123");
    }

    // Невалидные данные для поля "Месяц"
    public static CardInfo getEmptyMonth() {
        return new CardInfo("1111 2222 3333 4444", "", getValidYear(), "IVAN IVANOV", "123");
    }

    public static CardInfo getInvalidMonth() {
        return new CardInfo("1111 2222 3333 4444", "13", getValidYear(), "IVAN IVANOV", "123");
    }

    public static CardInfo getSingleDigitMonth() {
        return new CardInfo("1111 2222 3333 4444", "5", getValidYear(), "IVAN IVANOV", "123");
    }

    public static CardInfo getPastMonth() {
        LocalDate now = LocalDate.now();
        String pastMonth = now.minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("1111 2222 3333 4444", pastMonth, getCurrentYear(), "IVAN IVANOV", "123");
    }

    // Невалидные данные для поля "Год"
    public static CardInfo getEmptyYear() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), "", "IVAN IVANOV", "123");
    }

    public static CardInfo getPastYear() {
        LocalDate now = LocalDate.now();
        String pastYear = now.minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), pastYear, "IVAN IVANOV", "123");
    }

    public static CardInfo getFutureYear() {
        LocalDate now = LocalDate.now();
        String futureYear = now.plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), futureYear, "IVAN IVANOV", "123");
    }

    // Невалидные данные для поля "Владелец"
    public static CardInfo getEmptyHolder() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "", "123");
    }

    public static CardInfo getCyrillicHolder() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "ИВАН ИВАНОВ", "123");
    }

    public static CardInfo getHolderWithNumbers() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "IVAN123", "123");
    }

    public static CardInfo getHolderWithSpecialChars() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "IVAN@!", "123");
    }

    // Невалидные данные для поля "CVC"
    public static CardInfo getEmptyCvc() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "IVAN IVANOV", "");
    }

    public static CardInfo getTwoDigitCvc() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "IVAN IVANOV", "12");
    }

    public static CardInfo getSingleDigitCvc() {
        return new CardInfo("1111 2222 3333 4444", getValidMonth(), getValidYear(), "IVAN IVANOV", "1");
    }

    // Вспомогательные методы для генерации валидных данных
    public static String getValidMonth() {
        LocalDate now = LocalDate.now();
        return now.plusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getValidYear() {
        LocalDate now = LocalDate.now();
        return now.plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCurrentYear() {
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern("yy"));
    }
}