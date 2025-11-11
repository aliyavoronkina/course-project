package ru.netology.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataHelper {

    private DataHelper() {
    }

    public static class CardInfo {
        private final String number;
        private final String month;
        private final String year;
        private final String holder;
        private final String cvc;

        public CardInfo(String number, String month, String year, String holder, String cvc) {
            this.number = number;
            this.month = month;
            this.year = year;
            this.holder = holder;
            this.cvc = cvc;
        }

        public String getNumber() { return number; }
        public String getMonth() { return month; }
        public String getYear() { return year; }
        public String getHolder() { return holder; }
        public String getCvc() { return cvc; }
    }

    // Одобренная карта
    public static CardInfo getApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "123");
    }

    // Отклоненная карта
    public static CardInfo getDeclinedCard() {
        return new CardInfo("4444 4444 4444 4442", "12", "25", "IVAN IVANOV", "123");
    }

    // Короткий номер карты
    public static CardInfo getShortCardNumber() {
        return new CardInfo("4444 4444 4444", "12", "25", "IVAN IVANOV", "123");
    }

    // Неверный месяц
    public static CardInfo getInvalidMonth() {
        return new CardInfo("4444 4444 4444 4441", "15", "25", "IVAN IVANOV", "123");
    }

    // Прошедший месяц
    public static CardInfo getPastMonth() {
        String pastMonth = LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("MM"));
        return new CardInfo("4444 4444 4444 4441", pastMonth, "25", "IVAN IVANOV", "123");
    }

    // Прошедший год
    public static CardInfo getPastYear() {
        String pastYear = LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
        return new CardInfo("4444 4444 4444 4441", "12", pastYear, "IVAN IVANOV", "123");
    }

    // Кириллический владелец
    public static CardInfo getCyrillicHolder() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "ИВАН ИВАНОВ", "123");
    }

    // Двухзначный CVC
    public static CardInfo getTwoDigitCvc() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "IVAN IVANOV", "12");
    }

    // Пустой владелец
    public static CardInfo getEmptyHolder() {
        return new CardInfo("4444 4444 4444 4441", "12", "25", "", "123");
    }
}