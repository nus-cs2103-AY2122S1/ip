package duke;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores tasks locally on the hard disk.
 */
public class Storage {

    /** Stores the path of the text file */
    String file = "data/duke.txt";

    /** Stores the path of the data directory in the project folder */
    Path dirPath = Paths.get("data");

    /** Stores the path of the data file in the project folder */
    Path filePath = Paths.get("data/duke.txt");

    /** Stores if the path of the data directory in the project folder exists */
    boolean existsDirPath = Files.exists(dirPath);

    /** Stores if the path of the data file in the data directory in the project folder exists */
    boolean existsFilePath = Files.exists(filePath);


    /**
     * Appends tasks to text file.
     * @param filePath path of the text file that stores the tasks.
     * @param textToAppend the text to be appended.
     * @throws IOException
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Overwrites tasks to text file.
     * @param filePath path of the text file that stores the tasks.
     * @param textToAdd the text to be added.
     * @throws IOException
     */
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }


    public ArrayList<Task> load(String filePath) {
        ArrayList<Task> tasks = new ArrayList<>();
        if (existsDirPath) {
            if (existsFilePath) {
                try {
                    List<String> lines = Files.readAllLines(Path.of(filePath));
                    for(int i=0;i<lines.size();i++){
                        String line = lines.get(i);
                        if (line.charAt(1) == 'T') {
                            Task t = new Task(line, Duke.Category.TODO);
                            t.setPreExisting();
                            tasks.add(t);
                        } else if (line.charAt(1) == 'D') {
                            Task t = new Task(line, Duke.Category.DEADLINE);
                            t.setPreExisting();
                            tasks.add(t);
                        } else if (line.charAt(1) == 'E') {
                            Task t = new Task(line, Duke.Category.EVENT);
                            t.setPreExisting();
                            tasks.add(t);
                        }
                    }
                    return tasks;
                } catch (IOException e) {
                    e.printStackTrace();
                    return tasks;
                }


            } else {
                try {
                    Files.createFile(Path.of(filePath));
                } catch (IOException e) {
                    System.out.println("Sorry! Data storage file couldn't be created.");
                }
            }
        } else {

            try {
                Files.createDirectories(dirPath);
                Files.createFile(Path.of(filePath));
            } catch (IOException e) {
                System.out.println("Sorry! Data directory couldn't be created.");
            }

        }
        return tasks;
    }

}
