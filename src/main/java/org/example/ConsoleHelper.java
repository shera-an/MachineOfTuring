package org.example;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

@UtilityClass
public class ConsoleHelper {

    private final BufferedReader CONSOLE = new BufferedReader(new InputStreamReader(System.in));

    public void writeMessage(String message){
        System.out.println(message);
    }

    @SneakyThrows
    public String readString(){
        return CONSOLE.readLine();
    }

    public int readInt(){
        return Integer.parseInt(readString().trim());
    }

    public Path buildFileName(String path, String suffix){


//    int dotIndex = path.lastIndexOf('.');
//
//    int separatorIndex = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));
//
//    Path paths = Paths.get(path);
//    if (dotIndex == -1 || dotIndex < separatorIndex) {
//
//        return Path.of(path + suffix);
//    }else {
//        String fileName = paths.getFileName().toString();
//        return paths.resolveSibling(fileName.substring(0, dotIndex) + suffix + fileName.substring(dotIndex));
//    }

        Path paths = Paths.get(path);
        String fileName = paths.getFileName().toString();
        int dotIndex = fileName.lastIndexOf('.');
        String newFileName;
        if(dotIndex == -1){
            newFileName = path + suffix;
        }else {
            newFileName = fileName.substring(0, dotIndex) + suffix + fileName.substring(dotIndex);
        }

        return  paths.resolveSibling(newFileName);

    }
}