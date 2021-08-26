package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private final String FILEPATH;

    public Storage(String filePath) {
        this.FILEPATH = filePath;
    }

    public ArrayList<String> load() {
        ArrayList<String> taskStrings = new ArrayList<>();
        File file = new File(FILEPATH);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    taskStrings.add(s.nextLine());
                }
                return taskStrings;
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return taskStrings;
    }

    public void appendToFile(String textToAdd) {
        try {
            FileWriter fw = new FileWriter(FILEPATH, true);
            fw.write(textToAdd);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void replaceFileLine(String textToReplaceWith, int lineNumber) {
        try {
            Path path = Paths.get(FILEPATH);
            List<String> lines = Files.readAllLines(path);
            lines.set(lineNumber, textToReplaceWith);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void deleteFileLine(int lineNumber) {
        try {
            Path path = Paths.get(FILEPATH);
            List<String> lines = Files.readAllLines(path);
            lines.remove(lineNumber);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}