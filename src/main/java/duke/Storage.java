package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Reads and writes valid user inputs into the task list to be saved to the hard disk.
 */
public class Storage {

    // The relative path to the directory
    private static String directory = "";

    // The file
    private static String file = "";

    // The contents of the file as a List of Strings
    private ArrayList<String> fileTasks;

    /**
     * Instantiates a storage object.
     *
     * @param fileDir The path to the directory.
     * @param fileName The file name.
     */
    public Storage(String fileDir, String fileName) {
        directory = fileDir;
        file = fileName;
        this.fileTasks = new ArrayList<>();
    }

    /**
     * Reads file. If file does not exist, creates a file.
     * Stores the contents of the file in a tasklist.
     */
    public void loadData() throws DukeException {
        // Make directory and/or file if they don't exist
        File dataFolder = new File(directory);
        dataFolder.mkdirs();
        File dataFile = new File(directory + "/" + file);
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
        }

        // list to contain all tasks as understood by bot.
        ArrayList<Task> fileList = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            fileTasks.clear();
            while (fileReader.hasNextLine()) {
                String rawData = fileReader.nextLine();
                fileTasks.add(rawData);
                String[] data = rawData.split(" \\| ");
                String taskType = data[0];
                boolean isDone = data[1].equals("1");
                Task task = null;

                assert (taskType.equals("D") || taskType.equals("E") || taskType.equals("T")) : "Data Corrupt in File";
                switch (taskType) {
                case "T":
                    // Add a todo task.
                    task = new Todo(data[2]);
                    break;
                case "D":
                    // Add a deadline task.
                    task = new Deadline(data[2], data[3]);
                    break;
                case "E":
                    // Add an event task.
                    task = new Event(data[2], data[3]);
                    break;
                default: break;
                }
                if (isDone) {
                    task.doneTask();
                }
                fileList.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("No saved data found");
        }
        TaskList fileTaskList = TaskList.of(fileList);
        Duke.addToState(fileTaskList);
    }

    /**
     * Saves the file content to the hard drive.
     *
     * @throws DukeException when saving the file fails.
     */
    public static void saveToFile() throws DukeException {
        try {
            ArrayList<String> fileList = TaskList.getStringList();
            Files.write(Paths.get(directory + "/" + file), fileList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error: could not save to file.");
        }
    }

}
