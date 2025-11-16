package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    public static String getApprovedCardNumber() {
        return "1111 2222 3333 4444";
    }

    public static String getDeclinedCardNumber() {
        return "5555 6666 7777 8888";
    }

    public static String getValidMonth() {
        int currentMonth = LocalDate.now().getMonthValue();
        return String.format("%02d", faker.number().numberBetween(currentMonth, 12));
    }

    public static String getInvalidMonth() {
        return "13";
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getValidYear() {
        return LocalDate.now().plusYears(faker.number().numberBetween(1, 3))
                .format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getPastYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getFutureYear() {
        return LocalDate.now().plusYears(6).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String generateHolderName() {
        return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
    }

    public static String generateCVC() {
        return faker.number().digits(3);
    }

    public static String generateInvalidCVC() {
        return faker.number().digits(2);
    }

    // Additional test data methods
    public static String getEmptyField() {
        return "";
    }

    public static String getSingleDigit() {
        return faker.number().digit();
    }

    public static String getCardNumberWithLetters() {
        return "1111 2222 3333 abcd";
    }

    public static String getShortCardNumber() {
        return "1111 2222 3333 444";
    }

    public static String getHolderWithSpecialChars() {
        return "IVAN@#$%";
    }

    public static String getLongHolderName() {
        return "A".repeat(100);
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getEmptyCard() {
        return new CardInfo("", "", "", "", "");
    }

    public static CardInfo getCardWithInvalidNumber() {
        return new CardInfo("4444 4444 4444 4443", getValidMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithInvalidMonth() {
        return new CardInfo(getApprovedCardNumber(), getInvalidMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithZeroMonth() {
        return new CardInfo(getApprovedCardNumber(), getZeroMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithPastYear() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getPastYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithFutureYear() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getFutureYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithCyrillicHolder() {
        Faker russianFaker = new Faker(new Locale("ru"));
        String cyrillicName = russianFaker.name().firstName() + " " + russianFaker.name().lastName();
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), cyrillicName, generateCVC());
    }

    public static CardInfo getCardWithNumericHolder() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), faker.number().digits(10), generateCVC());
    }

    public static CardInfo getCardWithShortCvc() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateHolderName(), generateInvalidCVC());
    }

    // New test data methods
    public static CardInfo getCardWithEmptyCardNumber() {
        return new CardInfo(getEmptyField(), getValidMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithShortCardNumber() {
        return new CardInfo(getShortCardNumber(), getValidMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithCardNumberWithLetters() {
        return new CardInfo(getCardNumberWithLetters(), getValidMonth(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithEmptyMonth() {
        return new CardInfo(getApprovedCardNumber(), getEmptyField(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithSingleDigitMonth() {
        return new CardInfo(getApprovedCardNumber(), getSingleDigit(), getValidYear(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithEmptyYear() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getEmptyField(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithSingleDigitYear() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getSingleDigit(), generateHolderName(), generateCVC());
    }

    public static CardInfo getCardWithEmptyHolder() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getEmptyField(), generateCVC());
    }

    public static CardInfo getCardWithHolderWithSpecialChars() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getHolderWithSpecialChars(), generateCVC());
    }

    public static CardInfo getCardWithLongHolderName() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getLongHolderName(), generateCVC());
    }

    public static CardInfo getCardWithEmptyCvc() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateHolderName(), getEmptyField());
    }

    public static CardInfo getCardWithSingleDigitCvc() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateHolderName(), getSingleDigit());
    }

    public static CardInfo getCardWithCvcWithLetters() {
        return new CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), generateHolderName(), "abc");
    }
}