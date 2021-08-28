package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The storage class is in charge of recording the user's input and loading saved user record.
 * Saving and loading tasks are done automatically by default, but a user can save and load the tasks to a specified
 * directory by using the save/load command.
 */
public class Storage {
    private ArrayList<Task> userInputRecords;
    private StorageUi ui;

    /**
     * The constructor for a Storage Object.
     */
    public Storage() {
        this.userInputRecords = new ArrayList<>();
        this.ui = new StorageUi();
    }

    /**
     * Automatically(without any user command) loads the users inputs from ../data/record when the program is restarted.
     */
    public void autoLoad() {
        try {
            System.out.println(Paths.get("data", "record"));
            Scanner scanner = new Scanner(Paths.get("data", "record"));
            while (scanner.hasNextLine()) {
                String itemInfo = scanner.nextLine();
                if (itemInfo.startsWith("[T]")) {
                    Task task = new ToDo(itemInfo.substring(7));
                    if (itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecords.add(task);
                } else if (itemInfo.startsWith("[D]")) {
                    Task task = new Deadline(itemInfo.substring(7, itemInfo.indexOf("(by")),
                            ui.convertToLocalTime(itemInfo.substring(itemInfo.indexOf("(by") + 5,
                                    itemInfo.length() - 1)));
                    if (itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecords.add(task);
                } else if (itemInfo.startsWith("[E]")) {
                    Task task = new Event(itemInfo.substring(7, itemInfo.indexOf("(at")),
                            ui.convertToLocalTime(itemInfo.substring(itemInfo.indexOf("(at") + 5,
                                    itemInfo.length() - 1)));
                    if (itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecords.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            ui.printSaveNotFoundMessage();
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
                ui.printDirectoryAlreadyExistMessage();
            }
        } else if (!fileExists) {
            try {
                Files.createFile(Path.of("data", "record"));
            } catch (IOException e) {
                ui.printFileAlreadyExistMessage();
            }
        }
        try {
            FileWriter writer = new FileWriter(Paths.get("data", "record").toString());
            for (Task task : userInputRecords) {
                writer.write(task.toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
        } catch (IOException e) {
            ui.printUnexpectedErrorMessage();
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
     */
    public void load(String filePath) {
        try {
            filePath = filePath.replace("load ", "");
            Scanner scanner = new Scanner(Paths.get(filePath));
            while (scanner.hasNextLine()) {
                String itemInfo = scanner.nextLine();
                if (itemInfo.startsWith("[T]")) {
                    Task task = new ToDo(itemInfo.substring(7));
                    if (itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecords.add(task);
                } else if (itemInfo.startsWith("[D]")) {
                    Task task = new Deadline(itemInfo.substring(7, itemInfo.indexOf("(by")),
                            ui.convertToLocalTime(itemInfo.substring(itemInfo.indexOf("(by") + 5,
                                    itemInfo.length() - 1)));
                    if (itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecords.add(task);
                } else if (itemInfo.startsWith("[E]")) {
                    Task task = new Event(itemInfo.substring(7, itemInfo.indexOf("(at")),
                            ui.convertToLocalTime(itemInfo.substring(itemInfo.indexOf("(at") + 5,
                                    itemInfo.length() - 1)));
                    if (itemInfo.contains("[X]")) {
                        task.setDone(true);
                    }
                    userInputRecords.add(task);
                }
            }
            scanner.close();
            ui.printLoadSuccessfulMessage();
        } catch (IOException e) {
            ui.printInvalidFilePathMessage();
        }
    }

    /**
     * Saves the current task list to a user-specified file, upon receiving a save command.
     *
     * @param filePath the filepath indicated by the user.
     */
    public void save(String filePath) {
        try {
            filePath = filePath.replace("save ", "");
            Files.deleteIfExists(Path.of(filePath));
            Files.createFile(Path.of(filePath));
            FileWriter writer = new FileWriter(filePath);
            for (Task task : userInputRecords) {
                writer.write(task.toString());
                writer.write(System.getProperty("line.separator"));
            }
            writer.close();
            autoSave();
            ui.printSaveSuccessfulMessage();
        } catch (IOException | InvalidPathException e) {
            ui.printInvalidFilePathMessage();
        }
    }
}
