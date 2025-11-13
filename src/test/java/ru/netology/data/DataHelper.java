package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getValidMonth() {
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        return String.format("%02d", month);
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getValidYear() {
        return LocalDate.now().plusYears(2).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getFutureYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getValidHolder() {
        return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
    }

    public static String getInvalidHolderCyrillic() {
        Faker russianFaker = new Faker(new Locale("ru"));
        return russianFaker.name().firstName() + " " + russianFaker.name().lastName();
    }

    public static String getInvalidHolderNumbers() {
        return faker.number().digits(10);
    }

    public static String getValidCvc() {
        return faker.number().digits(3);
    }

    public static String getInvalidCvc() {
        return faker.number().digits(2);
    }

    // Доменные методы для получения тестовых данных
    public static CardInfo getApprovedCard() {
        return new CardInfo(
                getApprovedCardNumber(),
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(
                getDeclinedCardNumber(),
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getCardWithInvalidMonth() {
        return new CardInfo(
                getApprovedCardNumber(),
                getInvalidMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getCardWithZeroMonth() {
        return new CardInfo(
                getApprovedCardNumber(),
                getZeroMonth(),
                getValidYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getCardWithPastYear() {
        return new CardInfo(
                getApprovedCardNumber(),
                getValidMonth(),
                getPastYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getCardWithFutureYear() {
        return new CardInfo(
                getApprovedCardNumber(),
                getValidMonth(),
                getFutureYear(),
                getValidHolder(),
                getValidCvc()
        );
    }

    public static CardInfo getCardWithCyrillicHolder() {
        return new CardInfo(
                getApprovedCardNumber(),
                getValidMonth(),
                getValidYear(),
                getInvalidHolderCyrillic(),
                getValidCvc()
        );
    }

    public static CardInfo getCardWithNumericHolder() {
        return new CardInfo(
                getApprovedCardNumber(),
                getValidMonth(),
                getValidYear(),
                getInvalidHolderNumbers(),
                getValidCvc()
        );
    }

    public static CardInfo getCardWithShortCvc() {
        return new CardInfo(
                getApprovedCardNumber(),
                getValidMonth(),
                getValidYear(),
                getValidHolder(),
                getInvalidCvc()
        );
    }

    @Value
    public static class CardInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }
}