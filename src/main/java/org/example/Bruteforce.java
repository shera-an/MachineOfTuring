package org.example;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Bruteforce {
    @SneakyThrows
    public static void bruteforce() {
        ConsoleHelper.writeMessage("Введите путь к файлу для расшифровки");
        String src = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(src,"_brute");

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(src));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(dst)) {
            List<String> list = bufferedReader.readAllLines();
            StringBuilder stringBuilder = new StringBuilder();
            for (String element : list) {
                stringBuilder.append(element);
            }
            String encryptedString = stringBuilder.toString();

            CaesarCipher caesarCipher = new CaesarCipher();

            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String decrypt = caesarCipher.decrypt(encryptedString, i);
                if (isValidateText(decrypt)) {
                    for (String str : list){
                        bufferedWriter.write(caesarCipher.decrypt(str,i));
                        bufferedWriter.newLine();
                    }
                    ConsoleHelper.writeMessage("Содержимое файла расшифровано");
                    ConsoleHelper.writeMessage("Ключ валидации: " + i);
                    break;
                }
            }
        }
    }

    private static boolean isValidateText(String text) {
        String[] strings = text.split(" ");
        int maxLengthWord = 28;
        for (String word : strings){
            if(word.length() > maxLengthWord) return false;
        }
        boolean isValidate = false;
        if(text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")){
            isValidate = true;
        }
        int showSim = 500;

        ConsoleHelper.writeMessage(text.length() < showSim ?  text : text.substring(0, showSim));
        ConsoleHelper.writeMessage("Понятен ли этот текст?: Y/N");
        String answer = ConsoleHelper.readString();

        while (isValidate){
            if(answer.equalsIgnoreCase("y")){
               return true;
            } else if (answer.equalsIgnoreCase("n")) {
                isValidate = false;
            }else {
                ConsoleHelper.writeMessage("Введено некорректное значенение, попробуйте снова!");
            }
        }
        return false;
    }
}
