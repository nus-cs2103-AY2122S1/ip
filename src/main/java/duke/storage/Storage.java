package duke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.task.TaskList;

/**
 * Deals with loading tasks from file and saving tasks in file.
 */
public class Storage {
    /** String representation of the path to the file */
    private String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath String representation of the path to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file.
     * Returns task as String representation.
     *
     * @return String representation of tasks.
     */
    public String load() {
        String str = "";

        File dukeData = new File(filePath);

        try {
            if (!dukeData.exists()) {
                dukeData.getParentFile().mkdirs();
                dukeData.createNewFile();
            } else {
                Scanner sc = new Scanner(dukeData);

                while (sc.hasNext()) {
                    str += sc.nextLine() + "\n";
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return str;
    }

    /**
     * Saves the tasks into a file.
     *
     * @param list Contain the tasks that needs to be saved.
     */
    public void write(TaskList list) {
        String savedData = list.checkOut();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filePath)));
            writer.write(savedData);

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
