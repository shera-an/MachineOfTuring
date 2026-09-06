package main;

public class CaesarCipher {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" + "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            ".,\":!? +-*/\\@#$%^&(){}[];'|`~=_©«»—" + "0123456789";
    public String encrypt(String message, int key){


        StringBuilder result = new StringBuilder();
        for(char oldChar : message.toCharArray()){

            int index = ALPHABET.indexOf(oldChar);

            if(index >= 0){
                int newIndex = (index + key) % ALPHABET.length();
//                char newChar = newIndex < 0 ? ALPHABET.charAt(newIndex + ALPHABET.length()) : ALPHABET.charAt(newIndex);
                char newChar =  ALPHABET.charAt(newIndex + (newIndex < 0 ? ALPHABET.length() : 0));
                result.append(newChar);
            }
        }
        return result.toString();
    }
    public String decrypt(String message, int key){

        return encrypt(message, key * -1);
    }
    public int alphabetLength(){
        return ALPHABET.length();
    }
}
