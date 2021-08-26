package duke;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the interactions with the data file.
 */
public class Storage {

    /** The relative file path of the data file. */
    private String filepath;

    /** Constructs a Storage object to handle the interactions with the data file. */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Saves the user's TaskList to the data file when the user exits the program.
     * If the data file does not exist the the file path, a new data file is created
     * for the user.
     *
     * @param tasks The user's TaskList to be saved.
     */
    public void save(TaskList tasks) {
        try {
            // Initialize a File object using the data file path.
            File file = new File(filepath);

            // If data file does not exist, create a new data file.
            if (!file.exists()) {
                file.createNewFile();
            }

            // Initialize new writer for the data file.
            FileWriter writer = new FileWriter(filepath);

            // Initialize the String that will be used to save to the data file.
            String toSave = "";

            // Format the String to how the task list should be saved in the data file.
            for (int i = 0; i < tasks.size(); i++) {
                Task currentTask = tasks.getTask(i);
                toSave = toSave.concat(currentTask.taskType()
                        + " | "
                        + currentTask.isDoneToInt()
                        + " | "
                        + currentTask.getTaskDetails() + "\n");
            }
            // Write to the data file.
            writer.write(toSave);

            // Close the writer when done writing.
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads the data from the data file and returns the TaskList from when the user last ran Duke.
     * If the data file does not exist in the file path, an empty TaskList will be returned.
     *
     * @return If the data files exists in the file path, returns the TaskList that is loaded.
     * If not, returns an empty TaskList.
     */
    public TaskList load() {
        // ArrayList that will be used to initialize the returned TaskList.
        ArrayList<Task> loadedTaskList = new ArrayList<>();

        try {
            // Initialize a File object using the data file path.
            File file = new File(filepath);

            // If data file does not exist, create a new data file
            // and return empty TaskList
            if (file.createNewFile()) {
                return new TaskList();
            }

            // Initialize a scanner object for the data file
            Scanner s = new Scanner(file);

            // Scan the data file and add each of the different tasks.
            while (s.hasNext()) {
                String[] lineSplit = s.nextLine().split(" \\| ",5);

                switch (lineSplit[0]) {
                case "T":
                    loadedTaskList.add(new ToDo(lineSplit[2]));
                    break;
                case "D":
                    loadedTaskList.add(new Deadline(lineSplit[2], lineSplit[3], lineSplit[4]));
                    break;
                case "E":
                    loadedTaskList.add(new Event(lineSplit[2], lineSplit[3]));
                    break;
                }

                if (lineSplit[1].equals("0")) {
                    loadedTaskList.get(loadedTaskList.size() - 1).markDone();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new TaskList(loadedTaskList);
    }
}
