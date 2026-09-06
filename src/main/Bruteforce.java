package main;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bruteforce {
    public static void bruteforce() throws IOException {
        System.out.println("Введите путь к файлу для расшифровки");
        Scanner scanner = new Scanner(System.in);
        String src = scanner.nextLine();
        System.out.println("Введите путь куда записать результат");
        String dst = scanner.nextLine();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dst))) {
            List<String> list = bufferedReader.readAllLines();
            StringBuilder stringBuilder = new StringBuilder();
            for (String element : list) {
                stringBuilder.append(element);
            }
            String encryptedString = stringBuilder.toString();

            CaesarCipher caesarCipher = new CaesarCipher();

            //ПРОБЛЕМА ТУТ ДУМАЙ ИЩИ !!
            int key = Integer.MAX_VALUE;
            for (int i = 0; i < caesarCipher.alphabetLength(); i++) {
                String decrypt = caesarCipher.decrypt(encryptedString, i); // ПОДУМАЙ НАД СТРОЧКОЙ
                key = i;
                if (!isValidateText(decrypt)) {
                   break;
                }
            }
            for (String str : list){
                bufferedWriter.write(caesarCipher.decrypt(str,key));
                bufferedWriter.newLine();
            }

        }


    }

    private static boolean isValidateText(String text) {
        if(text.contains(". ") || text.contains(", ") || text.contains("! ") || text.contains("? ")){
            String[] strings = text.split(" ");
            for (String s : strings){
                if(s.length() > 28) return false;
            }
        }
        return true;
    }
}
