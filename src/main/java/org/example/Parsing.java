package org.example;

import com.sun.source.tree.TryTree;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

public class Parsing {

    @SneakyThrows
    public static Map<Character, Integer> createMap(String path){
        Map<Character, Integer> map = new HashMap<>();
        try(BufferedReader bufferedReader = Files.newBufferedReader(Path.of(path))){
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                builder.append(line);
            }
            for (char aChar : builder.toString().toCharArray()) {
                map.merge(aChar,1, Integer::sum);
//                   map.put(aChar,map.getOrDefault(aChar, 0) + 1);
                //(oldValue, newValue ) -> oldValue + newValue
            }
        }
        return map;
    }

    public static List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map){
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a,b) -> b.getValue() - a.getValue());
        return list;
    }

    @SneakyThrows
    public static void parse() {
        ConsoleHelper.writeMessage("Введите путь к файлу для его расшифровки");
        String pathEncrypted = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Введите путь к файлу с текстом для набора статистики");
        String pathStatistic = ConsoleHelper.readString();
        Path dst = ConsoleHelper.buildFileName(pathEncrypted, "_parce");

        Map<Character, Integer> characterEncrypted = createMap(pathEncrypted);
        Map<Character, Integer> characterStatistic = createMap(pathStatistic);

        List<Map.Entry<Character, Integer>> listEncrypted = mapToList(characterEncrypted);
        List<Map.Entry<Character, Integer>> listStatistic = mapToList(characterStatistic);

        HashMap<Character, Character> decryptionMap = new HashMap<>();

        if(listEncrypted.size() <= listStatistic.size()){
            for (int i = 0; i < listStatistic.size(); i++) {
                char encryptedChar = listEncrypted.get(i).getKey();
                char statisticChar = listStatistic.get(i).getKey();
                decryptionMap.put(encryptedChar, statisticChar);
            }
        }else {
            ConsoleHelper.writeMessage("Размер файла статистики недостаточен для расшифровки, нужно больше информации");
            return;
        }


        try(BufferedReader bufferedReader = Files.newBufferedReader(Path.of(pathEncrypted));
            BufferedWriter bufferedWriter = Files.newBufferedWriter(dst)) {

            String line;
            while ((line = bufferedReader.readLine()) != null){
                for (int i = 0; i < line.length(); i++) {
                    bufferedWriter.write(decryptionMap.get(line.charAt(i)));
                }
                bufferedWriter.newLine();
            }
            ConsoleHelper.writeMessage("Расшифровка успешно завершена!");

        }
//        try (FileReader reader = new FileReader(pathEncrypted);
//             FileWriter writer = new FileWriter(dst)) {
//
//            int code;
//            while ((code = reader.read()) != -1) {
//                char ch = (char) code;
//
//                char decryptedChar = decryptionMap.get(ch);
//                writer.write(decryptedChar);
//            }
//            ConsoleHelper.writeMessage("Расшифровка успешно завершена!");
//
//        }
    }
}
