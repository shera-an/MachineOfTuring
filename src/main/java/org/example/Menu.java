package org.example;

import lombok.SneakyThrows;

public class Menu {

    @SneakyThrows
    static void main(String[] args){
        while (true){
            ConsoleHelper.writeMessage("""
                    Выберете действие введя его номер
                    1. Зашифровать текст с помощью ключа
                    2. Расшифровать текст с помощью ключа
                    3. Подобрать ключь к защифрованному тексту в файле
                    4. Расшифравать текст с помощью статистического анализа
                    5. Выйти из программы""");

            switch (ConsoleHelper.readString()){
                case "1" -> CryptoService.stringProcessing(true);
                case "2" -> CryptoService.stringProcessing(false);
                case "3" -> Bruteforce.bruteforce();
                case "4" -> Parsing.parse();
                case "5" ->{return;}
            }
        }
    }
}