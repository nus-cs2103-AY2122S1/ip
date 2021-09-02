package duke;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Stores tasks locally on the hard disk.
 */
public class Storage {

    /** Stores the path of the text file */
    String file = "data/duke.txt";

    public Storage() {

    }

    /**
     * Overwrites content to the data file.
     * @param textToAdd text to be written.
     * @throws IOException exception to be thrown.
     */
    public void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Loads tasks from the disk when the bot starts.
     * @return ArrayList of tasks that were loaded.
     */
    public ArrayList<Task> load() {
        Path dirPath = Paths.get("data");
        Path path = Paths.get("data/duke.txt");
        boolean existsDirPath = Files.exists(dirPath);
        boolean existsFilePath = Files.exists(path);
        ArrayList<Task> tasks = new ArrayList<>();
        if (existsDirPath) {
            if (existsFilePath) {
                try {
                    List<String> lines = Files.readAllLines(path);
                    for (int i = 0; i < lines.size(); i++) {
                        String line = lines.get(i);
                        if (line.charAt(1) == 'T') {
                            Task t = new Task(line, Duke.Category.TODO);
                            t.setPreExisting();
                            tasks.add(t);
                            if (line.charAt(4) =='X') {
                                t.markAsDone(i);
                            }
                        } else if (line.charAt(1) == 'D') {
                            Task t = new Task(line, Duke.Category.DEADLINE);
                            t.setPreExisting();
                            tasks.add(t);
                            if (line.charAt(4) == 'X') {
                                t.markAsDone(i);
                            }
                        } else if (line.charAt(1) == 'E') {
                            Task t = new Task(line, Duke.Category.EVENT);
                            t.setPreExisting();
                            tasks.add(t);
                            if (line.charAt(4) =='X') {
                                t.markAsDone(i);
                            }
                        }
                    }
                    return tasks;
                } catch (IOException e) {
                    e.printStackTrace();
                    return tasks;
                }
            } else {
                try {
                    Files.createFile(path);
                } catch (IOException e) {
                    System.out.println("Sorry! Data storage file couldn't be created.");
                }
            }
        } else {
            try {
                Files.createDirectories(dirPath);
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("Sorry! Data directory couldn't be created.");
            }
        }
        return tasks;
    }
}
