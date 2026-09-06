//package main;
//
//import java.io.*;
//import java.util.Scanner;
//
//public class Encrypt {
//    public static void encrypted()throws IOException {
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("Введите путь к  файлу для шифрования:");
//        String src = scanner.nextLine();
//        System.out.println("Введите ключ");
//        int key = Integer.parseInt(scanner.nextLine());
//        System.out.println("Введите путь куда записать результат");
//        String dst = scanner.nextLine();
//
//        CaesarCipher caesarCipher = new CaesarCipher();
//        try(BufferedReader reader = new BufferedReader(new FileReader(src));
//            BufferedWriter writer = new BufferedWriter(new FileWriter(dst))) {
//            while (reader.ready()){
//                String encryptedString = caesarCipher.encrypt(reader.readLine(),key);
//                writer.write(encryptedString);
//                writer.newLine();
//            }
//        }
//    }
//}
