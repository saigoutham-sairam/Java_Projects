package com.example.multithreading;

public
class NumberConverter {

    // Word mappings for numbers
    private static final String[] belowTwenty = {
            "", "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen",
            "Eighteen", "Nineteen"
    };

    private static final String[] tens = {
            "", "", "Twenty", "Thirty", "Forty", "Fifty",
            "Sixty", "Seventy", "Eighty", "Ninety"
    };

    private static final String[] thousands = {
            "", "Thousand", "Million", "Billion"
    };


    public
    String convertThousands(int num) {

        int i = 0;
        String words = "";
//31430
        while (num > 0) {
            if (num % 1000 != 0) {
                // call function to convert less than 1000s - 430
                words = helper(num % 1000) + thousands[i] + words;
            }
            num /= 1000;
            i++;
        }
        return words;
    }

    private
    String helper(int num) {
        if (num == 0)
            return "";
        if (num < 20) {
            return belowTwenty[num / 10];
        } else if (num < 100) {
            return tens[num / 10] + helper(num % 10);
        }else
            return belowTwenty[num/100] + " Hundred " + tens[num % 100] ;
    }
}
