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
 * @author Zhao Peiduo
 */
public class Storage {

    private final ArrayList<Task> userInputRecords;

    /**
     * The constructor for a Storage Object.
     */
    public Storage() {
        this.userInputRecords = new ArrayList<>();
    }

    /**
     * Loads the users inputs from ../data/record when the program is restarted automatically(without any user command).
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
            boolean directoryExists = Files.exists(Paths.get("data"));
            boolean fileExists = Files.exists(Paths.get("data", "record"));
            if (!directoryExists) {
                try {
                    Files.createDirectory(Path.of("data"));
                } catch (IOException error) {
                    System.out.println("This directory already exists!\n");
                }
            } else if (!fileExists) {
                try {
                    Files.createFile(Path.of("data", "record"));
                } catch (IOException error) {
                    System.out.println("This file already exists!\n");
                }
            }
            System.out.println("Saved data not found, a new data file created.\n");
        }
    }

    /**
     * Saves the users inputs to ../data/record automatically(without any user command)
     */
    public void autoSave() {
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
     * @param userInput the user input containing a filepath.
     * @return the response on whether a file is successfully loaded.
     */
    public String load(String userInput) {
        try {
            String path = userInput.replace("load ", "");
            Scanner scanner = new Scanner(Paths.get(path));
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
     * @param userInput the user input containing a filepath.
     * @return the response on whether a file is successfully saved.
     */
    public String save(String userInput) {
        try {
            String path = userInput.replace("save ", "");
            Files.deleteIfExists(Path.of(path));
            Files.createFile(Path.of(path));
            FileWriter writer = new FileWriter(path);
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
        String description;
        if (taskRecord.startsWith("[T]")) {
            description = taskRecord.substring(indexForDescriptionStart);
            task = new ToDo(description);
        } else if (taskRecord.startsWith("[D]")) {
            description = taskRecord.substring(indexForDescriptionStart, taskRecord.indexOf("(by"));
            LocalDate deadline = LocalDate.parse(taskRecord.substring(taskRecord.indexOf("(by") + indexForTimeStart,
                    taskRecord.length() - 1));
            task = new Deadline(description, deadline);
        } else {
            assert taskRecord.startsWith("[E]");
            description = taskRecord.substring(indexForDescriptionStart, taskRecord.indexOf("(at"));
            LocalDate date = LocalDate.parse(taskRecord.substring(taskRecord.indexOf("(at") + indexForTimeStart,
                    taskRecord.length() - 1));
            task = new Event(description, date);
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
