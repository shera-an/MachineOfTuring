package main;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    static void main(String[] args) throws IOException {
        while (true){
            System.out.println("Выберете действие введя его номер");
            System.out.println("1. Зашифровать текст с помощью ключа");
            System.out.println("2. Расшифровать текст с помощью ключа");
            System.out.println("3. Подобрать ключь к защифрованному тексту в файле");
            System.out.println("4. Расшифравать текст с помощью статистического анализа");
            System.out.println("5. Выйти из программы");

            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            switch (answer){
                case "1" -> CryptoService.stringProcessing(true);
                case "2" -> CryptoService.stringProcessing(false);
                case "3" -> Bruteforce.bruteforce();
                case "4" -> System.out.println(4);
                case "5" ->{return;}


            }
        }

    }
}
