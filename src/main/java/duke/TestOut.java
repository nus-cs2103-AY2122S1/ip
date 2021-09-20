package duke;
import java.io.BufferedWriter;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.FileWriter;

public class TestOut {

    public static void main(String[] args){
        String currentDir = System.getProperty("user.dir");
        java.nio.file.Path dirpath = Paths.get("data");
        Path taskListPath = Paths.get("data", "DukeTask.txt");
        boolean directoryExists = Files.exists(dirpath);
        boolean fileExist = Files.exists(taskListPath);
        if(!directoryExists) {
            try{
                Files.createDirectory(dirpath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        if(!fileExist) {
            try{
                Files.createFile(taskListPath);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try {
//            FileWriter fw = new FileWriter(taskListPath.normalize().toString());
//            BufferedWriter out = new BufferedWriter(fw);
//            out.write("Hello, It's Me");
            Files.write(taskListPath, "Hello, Its me".getBytes());
            Files.lines(taskListPath).forEach(System.out::println);
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
