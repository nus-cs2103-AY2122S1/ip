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

    /**
     * Constructs a Storage instance
     *
     * @param taskList Instance of TaskList being used
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
        this.folderPath = System.getProperty("user.dir") + "/PetalData";
        this.filePath = folderPath + "/Tasks.txt";
        isLocationPresent = true;
    }

    /**
     * Creates the main PetalData folder, containing Tasks.txt
     * @return String greeting the user
     */
    public String createDirectory() {
        if (hasUsedPetalBefore()) {
            return Responses.WELCOME_BACK.toString();
        }
        try {
            Path path = Paths.get(folderPath);
            Files.createDirectories(path);
            File petalData = new File(filePath);
            petalData.createNewFile();
        } catch (IOException e) {
            isLocationPresent = false;
            return Responses.FILE_ERROR.toString();
        }
        return Responses.START_MESSAGE.toString();
    }

    /**
     * Parses the text from Tasks.txt in tasks and retrieves it as tasks. If it is
     * able to retrieve the tasks, this means that Petal was opened by the user at least
     * once before
     *
     * @return True if tasks were retrieved, false if no tasks (new user) or exception occurred
     */
    public boolean hasUsedPetalBefore() {
        try {
            File tasks = new File(filePath);
            Scanner scanner = new Scanner(tasks);
            ArrayList<Task> addPasts = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                String[] components = taskLine.split("\\|");
                boolean isDone = Objects.equals(components[1], "X");
                switch (components[0]) {
                case "T":
                    addPasts.add(new ToDo(components[2], isDone));
                    break;
                case "D":
                    assert components[3] != null;
                    addPasts.add(new Deadline(components[2], components[3], isDone));
                    break;
                case "E":
                    assert components[3] != null;
                    addPasts.add(new Event(components[2], components[3], isDone));
                    break;
                default:
                    break;
                }
            }
            taskList.addSavedTasks(addPasts);
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
