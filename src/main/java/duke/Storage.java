package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private final List<Task> tasks = new ArrayList<>();
    private final Parser parser = new Parser();

    /**
     * Loads the task list information from the hard disk.
     *
     * @return A list of tasks retrieved from the hard disk.
     */
    public List<Task> loadFromDisk() {
        File folder = new File("data");
        if (folder.isDirectory()) {
            try {
                File file = new File("data/duke.txt");
                Scanner scanner = new Scanner(file);
                while (scanner.hasNext()) {
                    String taskString = scanner.nextLine();
                    Task task = parser.taskStringToTask(taskString);
                    this.tasks.add(task);
                }
            } catch (FileNotFoundException e) {
                //create the file if it does not exist.
                createFile();
            }
        } else {
            //create both directory and file if they do not exist.
            createDataFolder();
            createFile();
        }
        return tasks;
    }

    /**
     * Saves the list changes on the hard disk.
     */
    public void saveProgress() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : tasks) {
                fw.write(task + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("No File Found");
            System.exit(-1);
        }
    }

    private void createFile() {
        try {
            File dukeFile = new File("data/duke.txt");
            dukeFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(-1);
        }
    }

    private void createDataFolder() {
        File dataFolder = new File("data");
        boolean isFolderCreated = dataFolder.mkdir();
        if (!isFolderCreated) {
            System.out.println("Could not create data directory");
            System.exit(-1);
        }
    }
}
