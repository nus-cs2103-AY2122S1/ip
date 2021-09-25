package iris.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import iris.task.Task;
import iris.task.TaskList;

/**
 * Storage saves and loads tasks
 * from the local directory.
 *
 * @author Cheong Yee Ming
 * @version Iris Level-10
 */
public class Storage {

    private static final String FOLDER = "data";
    private static final String FILE = "iris.txt";

    /**
     * Creates a folder in local directory.
     */
    public void createFolder() {
        File folder = new File(FOLDER);
        boolean folderCreated = folder.mkdir();
        if (!folderCreated) {
            System.out.println("Folder could not be created.");
        }
    }

    /**
     * Creates a file in local directory.
     */
    public void createFile() {
        File file = new File(FOLDER + "/" + FILE);
        boolean isCreated = false;
        try {
            isCreated = file.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (isCreated) {
                System.out.println("New file has been created");
            } else {
                System.out.println("File already exist.");
            }
        }
    }

    /**
     * Loads tasks from local directory only if
     * data is present in local directory.
     *
     * @return An ArrayList of Task saved in local directory.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(FOLDER + "/" + FILE);
        try {
            Scanner sc = new Scanner(file);
            System.out.println("Tasks have been loaded into the list");
            while (sc.hasNextLine()) {
                String curr = sc.nextLine();
                Task task = TaskList.stringToTask(curr);
                taskList.add(task);
            }
            assert !sc.hasNextLine() : "There are tasks not loaded.";
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No data found, initializing empty list.");
            createFolder();
            createFile();
        }
        return taskList;
    }

    /**
     * Saves tasks in TaskList to local directory.
     * Folder and file will be created if not already present.
     *
     * @param taskList List of task saved in local directory.
     */
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter fileWriter = new FileWriter(FOLDER + "/" + FILE);
            for (Task task: taskList) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Save Error: " + e.getMessage());
        }
    }
}
