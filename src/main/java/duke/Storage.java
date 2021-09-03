package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The storage class is in charge of recording the user's input and loading saved user record.
 * Saving and loading tasks are done automatically by default, but a user can save and load the tasks to a specified
 * directory by using the save/load command.
 */
public class Storage {

    private ArrayList<Task> userInputRecords;

    /**
     * The constructor for a Storage Object.
     */
    public Storage() {
        this.userInputRecords = new ArrayList<>();
    }

    /**
     * Automatically(without any user command) loads the users inputs from ../data/record when the program is restarted.
     */
    public void autoLoad() {
        try {
            Scanner scanner = new Scanner(Paths.get("data", "record"));
            while (scanner.hasNextLine()) {
                String itemInfo = scanner.nextLine();
                addTask(itemInfo);
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Saved data not found, a new data file created.\n");
        }
    }

    /**
     * Automatically(without any user command) saves the users inputs to ../data/record.
     */
    public void autoSave() {
        boolean directoryExists = Files.exists(Paths.get("data"));
        boolean fileExists = Files.exists(Paths.get("data", "record"));
        if (!directoryExists) {
            try {
                Files.createDirectory(Path.of("data"));
            } catch (IOException e) {
                System.out.println("This directory already exists!\n");
            }
        } else if (!fileExists) {
            try {
                Files.createFile(Path.of("data", "record"));
            } catch (IOException e) {
                System.out.println("This file already exists!\n");
            }
        }
        try {
            FileWriter writer = new FileWriter(Paths.get("data", "record").toString());
            for (Task task : userInputRecords) {
                writer.write(task.toStringRecord());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An unknown error has occurred.\n");
        }
    }

    public ArrayList<Task> getUserInputRecords() {
        return userInputRecords;
    }

    /**
     * Loads the current task list from a user-specified file, upon receiving a load command.
     * Texts not recorded in the standard format wll be ignored.
     *
     * @param filePath the filepath indicated by the user.
     * @return the response on whether a file is successfully loaded.
     */
    public String load(String filePath) {
        try {
            filePath = filePath.replace("load ", "");
            Scanner scanner = new Scanner(Paths.get(filePath));
            while (scanner.hasNextLine()) {
                String itemInfo = scanner.nextLine();
                addTask(itemInfo);
            }
            scanner.close();
            return "Load successfully.\n";
        } catch (IOException e) {
            return "Saved data not found.\n";
        }
    }

    /**
     * Saves the current task list to a user-specified file, upon receiving a save command.
     *
     * @param filePath the filepath indicated by the user.
     * @return the response on whether a file is successfully saved.
     */
    public String save(String filePath) {
        try {
            filePath = filePath.replace("save ", "");
            Files.deleteIfExists(Path.of(filePath));
            Files.createFile(Path.of(filePath));
            FileWriter writer = new FileWriter(filePath);
            for (Task task : userInputRecords) {
                writer.write(task.toStringRecord());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
            autoSave();
            return "Save successfully.\n";
        } catch (IOException | InvalidPathException e) {
            return "Invalid file path detected, please try again.\n";
        }
    }

    private void addTask(String taskRecord) {
        int indexForDescriptionStart = 7;
        int indexForTimeStart = 5;
        Task task;
        if (taskRecord.startsWith("[T]")) {
            task = new ToDo(taskRecord.substring(indexForDescriptionStart));
        } else if (taskRecord.startsWith("[D]")) {
            task = new Deadline(taskRecord.substring(indexForDescriptionStart, taskRecord.indexOf("(by")),
                    LocalDate.parse(taskRecord.substring(taskRecord.indexOf("(by") + indexForTimeStart,
                            taskRecord.length() - 1)));
        } else if (taskRecord.startsWith("[E]")) {
            task = new Event(taskRecord.substring(indexForDescriptionStart, taskRecord.indexOf("(at")),
                    LocalDate.parse(taskRecord.substring(taskRecord.indexOf("(at") + indexForTimeStart,
                            taskRecord.length() - 1)));
        } else {
            //Should not reach here unless the file is corrupted; skip the line that is not properly formatted.
            return;
        }
        setDone(taskRecord, task);
        userInputRecords.add(task);
    }

    private void setDone(String taskRecord, Task task) {
        if (taskRecord.contains("[X]")) {
            task.setDone(true);
        }
    }
}
