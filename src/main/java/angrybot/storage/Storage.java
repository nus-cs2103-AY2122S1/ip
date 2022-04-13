package angrybot.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import angrybot.task.Task;
import angrybot.task.TaskList;

/**
 * Encapsulate the handling of loading and storing files on local directory for AngryBot.
 *
 * @author Zhi Bin
 * @version AngryBot Level 10
 */
public class Storage {
    private static final String FOLDER_NAME = "data";
    private static final String FILE_NAME = "AngryBot.txt";

    /**
     * Creates a data file named 'AngryBot.txt' to store the task list on local directory under the folder data.
     * Prints different message when it is created successfully or failed due to an error or file existed.
     */
    public void createFile() {
        File file = new File(String.format("%s/%s", FOLDER_NAME, FILE_NAME));
        boolean isCreated = false;
        try {
            isCreated = file.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } finally {
            if (isCreated) {
                System.out.println("New data file has been created.");
            } else {
                System.out.println("Data file already exist.");
            }
        }
    }

    /**
     * Creates a folder named 'data' to contain the data file storing task list on local directory.
     * Prints message if the folder could not be created.
     */
    public void createFolder() {
        File folder = new File(FOLDER_NAME);
        boolean created = folder.mkdir();
        if (!created) {
            System.out.println("Folder could not be created.");
        }
    }

    /**
     * Loads the data file stored on the local directory into AngryBot to get the previously stored task list.
     * If the data file does not exist, means it is the first time user use AngryBot, proceed to create folder and file.
     * Else, load the data from the file to an ArrayList to be used by TaskList class.
     *
     * @return The ArrayList of Task to be used by TaskList class to recreate the stored task list.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        File file = new File(String.format("%s/%s", FOLDER_NAME, FILE_NAME));
        try {
            Scanner sc = new Scanner(file);
            System.out.println("I have found past data in your local storage, "
                    + "type 'list' to view the previous tasks.");
            while (sc.hasNextLine()) {
                String curr = sc.nextLine();
                Task task = TaskList.stringToTask(curr);
                taskList.add(task);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("No past data found in your local storage, initializing from blank state.");
            createFolder();
            createFile();
        }
        return taskList;
    }

    /**
     * Saves the current task list to a data file on local directory.
     * Prints error message if an IOException is thrown.
     *
     * @param taskList The current task list to be saved to the data file.
     */
    public void save(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(String.format("%s/%s", FOLDER_NAME, FILE_NAME));
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
