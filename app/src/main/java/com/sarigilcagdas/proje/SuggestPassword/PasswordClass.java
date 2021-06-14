package com.sarigilcagdas.proje.SuggestPassword;

import java.util.Random;

public class PasswordClass {
    Random random = new Random();
    public String suggestPassword6(){
        String[] passwordArray = new String[6];
        String password_6 = "";
        for (int i = 0; i<passwordArray.length; i++){

            int digit = (int)(Math.random()*10);
            passwordArray[i] = String.valueOf(digit);
            password_6 = password_6 + passwordArray[i];

        }
        return password_6;
    }

    public String suggestPassword8() {
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
        };
        String[] passwordArray = new String[8];
        String password_8 = "";

        for (int i = 0;i < passwordArray.length;i++){
            int random = this.random.nextInt(digits.length);
            passwordArray[i] = digits[random];
            password_8 = password_8 + passwordArray[i];
        }
        return password_8;
    }

    public String suggestPassword12(){
        String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
                "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
                "!","'",".","*"
        };

        String[] passwordArray = new String[12];
        String password_12 = "";

        for (int i = 0;i<passwordArray.length;i++){
            int random = this.random.nextInt(digits.length);
            passwordArray[i] = digits[random];
            password_12 = password_12 + passwordArray[i];
        }
        return password_12;
    }

}
