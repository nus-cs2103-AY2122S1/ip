import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Storage {
    private final String pathname;

    public Storage(String pathname) {
        this.pathname = pathname;
        makeFile();
    }

    public void makeFile() {
        File textFile = new File("data/duke.txt");
        Path path = Paths.get("data");
        if (!Files.isDirectory(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.err.println("Failed to create directory!" + e.getMessage());
            }
        }
        if (!textFile.exists()) {
            try {
                textFile.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create file!" + e.getMessage());
            }
        }
    }

    public String[] load() {
        File textFile = new File("data/duke.txt");
        Integer counter = 0;
        String[] temp = new String[50];
        try {
            Scanner s = new Scanner(textFile);
            while (s.hasNext()) {
                String command = s.nextLine();
                temp[counter] = command;
                counter++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist" + e.getMessage());
        }
        return temp;
    }




}
