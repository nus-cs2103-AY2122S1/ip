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

/**
 * Deals with all file operations.
 */
public class Storage {

    private String filePath;

    /**
     * Initialises Storage object with file path.
     *
     * @param filePath represents absolute file path of where user's tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads list of strings representing Task objects from the file.
     *
     * @return ArrayList of task strings.
     */
    public ArrayList<String> load() {
        ArrayList<String> taskStrings = new ArrayList<>();
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                //@@author kaushikkrdy-reused
                //Reused from https://stackoverflow.com/a/27058578
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
                //@@author
            } else {
                Scanner s = new Scanner(file);
                while (s.hasNext()) {
                    taskStrings.add(s.nextLine());
                }
                return taskStrings;
            }
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        return taskStrings;
    }

    /**
     * Appends a line on the file.
     *
     * @param textToAdd new text that will be added to the file.
     */
    public void appendToFile(String textToAdd) {
        assert !textToAdd.isEmpty() : "Text to add cannot be blank!";
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAdd);
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Replaces specific line on the file.
     *
     * @param textToReplaceWith new text that will replace the old text.
     * @param lineNumber denotes the line number of the line that needs to be replaced.
     */
    public void replaceFileLine(String textToReplaceWith, int lineNumber) {
        assert !textToReplaceWith.isEmpty() : "Text to replace with cannot be blank!";
        try {
            //@@author kaushikkrdy-reused
            //Reused from https://stackoverflow.com/a/31376173
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            lines.set(lineNumber, textToReplaceWith);
            Files.write(path, lines, StandardCharsets.UTF_8);
            //@@author
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Deletes specific line from the file.
     *
     * @param lineNumber denotes the line number of the line that needs to be deleted.
     */
    public void deleteFileLine(int lineNumber) {
        try {
            //Solution below adapted from https://stackoverflow.com/a/31376173
            Path path = Paths.get(filePath);
            List<String> lines = Files.readAllLines(path);
            lines.remove(lineNumber);
            Files.write(path, lines, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
}
