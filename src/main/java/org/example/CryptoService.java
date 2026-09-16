package org.example;

import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class CryptoService {

    @SneakyThrows
    public static void stringProcessing(boolean flag) throws IOException {
        CaesarCipher caesarCipher = new CaesarCipher();

        ConsoleHelper.writeMessage("Введите путь к файлу для его " + (flag ? "зашифровки" : "расшифровки"));
        String src = ConsoleHelper.readString();

        Path dst = ConsoleHelper.buildFileName(src, flag ? "_e" : "_d");

        ConsoleHelper.writeMessage("Введите ключ");
        int key = ConsoleHelper.readInt();

        try (BufferedReader bufferedReader = Files.newBufferedReader(Path.of(src));
             BufferedWriter bufferedWriter = Files.newBufferedWriter(dst)) {

            while (bufferedReader.ready()) {
                String string = bufferedReader.readLine();
                bufferedWriter.write(flag ? caesarCipher.encrypt(string, key) : caesarCipher.decrypt(string, key));
                bufferedWriter.newLine();
            }
        }
    }
}