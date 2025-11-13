package ru.netology.data;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardInfo {
    private String number;
    private String month;
    private String year;
    private String holder;
    private String cvc;
}