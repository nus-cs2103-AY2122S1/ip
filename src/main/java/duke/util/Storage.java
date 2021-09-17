package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that deals with loading tasks from save file and saving tasks in the file.
 *
 * @author Benedict Chua
 */
public class Storage {
    private static final String DIRECTORY_PATH = "./data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/duke.txt";

    private boolean isDirectoryCreated;
    private boolean isFileCreated;

    /**
     * Constructor for Storage.
     * Checks if directory and file exists when created.
     */
    public Storage() {
        File directory = new File(DIRECTORY_PATH);
        File file = new File(FILE_PATH);

        this.isDirectoryCreated = directory.exists();
        this.isFileCreated = file.exists();
    }

    /**
     * Loads tasks from the save file.
     *
     * @return Tasks from the saved file in an ArrayList of String
     */
    public ArrayList<String> retrieveData() {
        if (isFileCreated) {
            try {
                File f = new File(FILE_PATH);
                Scanner sc = new Scanner(f);
                ArrayList<String> tasks = new ArrayList<>();

                while (sc.hasNextLine()) {
                    tasks.add(sc.nextLine());
                }

                return tasks;

            } catch (FileNotFoundException e) {
                System.out.println("Something went wrong:  " + e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Saves tasks into the save file from a given String of Tasks.
     *
     * @param tasks String containing all the tasks to save.
     */
    public void writeToFile(String tasks) {
        // Checks if directory exists, creates if it doesn't
        if (!this.isDirectoryCreated) {
            File directory = new File(DIRECTORY_PATH);
            directory.mkdir();
            this.isDirectoryCreated = directory.exists();
        }

        // Checks if file exists, creates if it doesn't
        if (!this.isFileCreated) {
            File file = new File(FILE_PATH);
            boolean isCreationSuccess;
            try {
                isCreationSuccess = file.createNewFile();
                this.isFileCreated = isCreationSuccess;
            } catch (IOException e) {
                System.out.println("Something went wrong:  " + e.getMessage());
            }
        }

        // Write to file
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong:  " + e.getMessage());
        }
    }
}
