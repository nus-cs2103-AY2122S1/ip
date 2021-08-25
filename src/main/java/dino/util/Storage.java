package dino.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import dino.task.*;
import dino.exception.*;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a Storage object
     *
     * @param filePath path to the local storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Opens the local storage file and loads data from it
     * Creates a new empty file at the specified path if there is no file under that path
     *
     * @throws IOException if the file cannot be loaded from the path specified, or the file
     * does not exist but also fails to created
     */
    public List<Task> loadStorage() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                createEmptyFile(file);
            } else {
                return getDataFromStorage(file);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Loads the task list data from the storage file
     *
     * @param file the file that is loaded which contains information about the task list
     * @return the task list that is loaded from the file
     */
    public List<Task> getDataFromStorage(File file)  {
        List<Task> taskList = new ArrayList<>();
        try {
        Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String currTask = sc.nextLine();
                String[] taskDetails = currTask.split(" \\| ");
                Task task;
                String type = taskDetails[0];
                String status = taskDetails[1];
                String description = taskDetails[2];
                switch (type) {
                case "T": {
                    task = new ToDo(description);
                    break;
                }
                case "D": {
                    LocalDate time = LocalDate.parse(taskDetails[3]);
                    task = new Deadline(description, time);
                    break;
                }
                case "E": {
                    LocalDate time = LocalDate.parse(taskDetails[3]);
                    task = new Event(description, time);
                    break;
                }
                default: {
                    throw new InvalidFormatException("check your storage file and make sure each task",
                            "Event | Status | Description | Time");
                }
                }
                if (status.equals("1")) task.setDone();
                taskList.add(task);
            }
        } catch (FileNotFoundException | DinoException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    /**
     * Creates an empty file at the directory of the input file
     *
     * @param file the file whose path is used to create a new empty file
     * @throws IOException if the file cannot be created for some reasons
     */
    public void createEmptyFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    /**
     * Saves the current task list to the local storage file
     *
     * @param tasks the current task list
     */
    public void saveToStorage(List<Task> tasks){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task: tasks) fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}