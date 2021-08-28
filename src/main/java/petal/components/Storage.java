package petal.components;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Scanner;

import petal.task.Deadline;
import petal.task.Event;
import petal.task.Task;
import petal.task.ToDo;

/**
 * The Storage that handles all the operation dealing with the creation of directories,
 * files, saving of tasks, and the retrieval of previously saved tasks.
 */
public class Storage {

    //True if folder and file for saving could be created properly
    private boolean isLocationPresent;

    //Paths of the location of storage
    private final String folderPath;
    private final String filePath;

    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor for the Storage class
     *
     * @param taskList Instance of TaskList being used
     * @param ui The Ui instance being used
     */
    public Storage(TaskList taskList, Ui ui) {
        this.ui = ui;
        this.taskList = taskList;
        this.folderPath = System.getProperty("user.dir") + "/PetalData";
        this.filePath = folderPath + "/Tasks.txt";
        isLocationPresent = true;
    }

    /**
     * Creates the main PetalData folder, containing Tasks.txt
     */
    public void createDirectory() {
        try {
            if (retrieveTasks()) {
                return;
            }
            Path path = Paths.get(folderPath);
            Files.createDirectories(path);
            File petalData = new File(filePath);
            boolean done = petalData.createNewFile();
        } catch (IOException e) {
            isLocationPresent = false;
            ui.output(Responses.FILE_ERROR);
        }
        ui.output(Responses.START_MESSAGE);
    }

    /**
     * Parses the text from Tasks.txt in tasks and retrieves it as tasks
     *
     * @return True if tasks were retrieved, false if no tasks (new user) or exception occurred
     */
    public boolean retrieveTasks() {
        try {
            File tasks = new File(filePath);
            Scanner scanner = new Scanner(tasks);
            ArrayList<Task> toBeAdded = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                String[] components = taskLine.split("\\|");
                boolean isDone = Objects.equals(components[1], "X");
                switch (components[0]) {
                case "T":
                    toBeAdded.add(new ToDo(components[2], isDone));
                    break;
                case "D":
                    toBeAdded.add(new Deadline(components[2], components[3], isDone));
                    break;
                case "E":
                    toBeAdded.add(new Event(components[2], components[3], isDone));
                    break;
                default:
                    break;
                }
            }
            taskList.addTask(toBeAdded);
            scanner.close();
            return true;
        } catch (FileNotFoundException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Saves the tasks in the task list.
     * If the folder/file couldn't be created properly, the bot doesn't save the tasks.
     *
     * @throws IOException Thrown if tasks are not saved properly
     */
    public void saveTasks() throws IOException {
        if (!isLocationPresent) {
            return;
        }
        FileWriter fileWriter = new FileWriter(filePath);
        fileWriter.write(taskList.formatForSaving());
        fileWriter.close();
    }



}
