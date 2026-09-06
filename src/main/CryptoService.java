package main;

import java.io.*;
import java.util.Scanner;

public class CryptoService {


    public static void stringProcessing(boolean flag) throws IOException{
        Scanner scanner = new Scanner(System.in);
        CaesarCipher caesarCipher = new CaesarCipher();

        System.out.println("Введите путь к файлу для его " + (flag == true ? "зашифровки" : "расшифровки"));
        String src = scanner.nextLine();
        System.out.println("Введите путь куда записать результат");
        String dst = scanner.nextLine();
        System.out.println("Введите ключ");
        int key = Integer.parseInt(scanner.nextLine());
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(src));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(dst))) {

            while (bufferedReader.ready()){
                String proceesedString = flag == true ? caesarCipher.encrypt(bufferedReader.readLine(),key) : caesarCipher.decrypt(bufferedReader.readLine(),key);
                 bufferedWriter.write(proceesedString);
                bufferedWriter.newLine();
            }
        }
    }

}
