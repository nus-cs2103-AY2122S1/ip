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
    private final String directory;

    // The file
    private final String file;

    // The contents of the file as a List of Strings
    private ArrayList<String> fileTasks;

    /**
     * Instantiates a storage object.
     *
     * @param fileDir The path to the directory.
     * @param file The file name.
     */
    public Storage(String fileDir, String file) {
        this.directory = fileDir;
        this.file = file;
        this.fileTasks = new ArrayList<>();
    }

    /**
     * Reads file. If file does not exist, creates a file.
     *
     * @return List containing all tasks in file originally.
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

        // list to contain all tasks ass understood by bot.
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
        Duke.state.add(fileTaskList);
    }

    /**
     * Adds a task to the saved file.
     *
     * @param task The task as a String.
     * @throws DukeException When saving the file fails.
     */
    public void addToFile(String task) throws DukeException {
        fileTasks.add(task);
        saveToFile();
    }

    /**
     * Adds a task to the saved file at given index.
     * Used with undo functionality.
     *
     * @param index at which task should be added
     * @param task The task as a String.
     * @throws DukeException When saving the file fails.
     */
    public void addToFile(int index, String task) throws DukeException {
        fileTasks.add(index, task);
        saveToFile();
    }

    /**
     * Removes a task from the saved file.
     *
     * @param id The line to be removed.
     * @throws DukeException When saving the file fails.
     */
    public void deleteFromFile(int id) throws DukeException {
        fileTasks.remove(id);
        saveToFile();
    }

    /**
     * Marks the task at given index as done.
     *
     * @param id index at which task is to be marked done.
     * @throws DukeException when a task is not found
     */
    public void markTaskDone(int id) throws DukeException {
        int doneIndex = 4;
        String task = this.getFileLine(id);
        String newTask = task.substring(0, doneIndex) + "1" + task.substring(doneIndex + 1);
        this.updateListTask(id, newTask);
    }

    /**
     * Marks the task at given index as undone.
     * Used to implement the undo functionality.
     *
     * @param id index at which task is to be marked done.
     * @throws DukeException when a task is not found
     */
    public void markTaskUndone(int id) throws DukeException {
        int doneIndex = 4;
        String task = this.getFileLine(id);
        String newTask = task.substring(0, doneIndex) + "0" + task.substring(doneIndex + 1);
        this.updateListTask(id, newTask);
    }

    /**
     * Updates a line in the file at the given index.
     *
     * @param id index at which task is to be updated.
     * @param task new task to be updated.
     * @throws DukeException when saving file fails.
     */
    public void updateListTask(int id, String task) throws DukeException {
        fileTasks.set(id, task);
        saveToFile();
    }

    /**
     * Returns the String of the queried line.
     *
     * @param index The line number
     * @return The String of the queried line
     * @throws DukeException When the line is not found
     */
    public String getFileLine(int index) throws DukeException {
        try {
            ArrayList<String> fileList = TaskList.getStringList();
            return fileList.get(index);
        } catch (Exception e) {
            throw new DukeException("Task doesn't exist"
                    + "\nAre you sure you have entered the correct index?");
        }
    }


    /**
     * Saves the file content to the hard drive.
     *
     * @throws DukeException when saving the file fails.
     */
    private void saveToFile() throws DukeException {
        try {
            ArrayList<String> fileList = TaskList.getStringList();
            Files.write(Paths.get(directory + "/" + file), fileList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("Error: could not save to file.");
        }
    }

}
