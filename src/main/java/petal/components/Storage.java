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

    private boolean isFolderAndFilePresent;

    private final String folderPath;
    private final String saveFilePath;
    private final String archiveFilePath;

    private final TaskList taskList;

    /**
     * Constructs a Storage instance
     *
     * @param taskList Instance of TaskList being used
     */
    public Storage(TaskList taskList) {
        this.taskList = taskList;
        folderPath = System.getProperty("user.home") + File.separator + "PetalData";
        saveFilePath = folderPath + File.separator + "Tasks.txt";
        archiveFilePath = folderPath + File.separator + "Archive.txt";
        isFolderAndFilePresent = true;
    }

    /**
     * Creates the main PetalData folder, containing Tasks.txt and Archive.txt
     *
     * @return String greeting the user
     */
    public String createDirectory() {
        if (hasUsedPetalBefore()) {
            return Responses.WELCOME_BACK.toString();
        }
        try {
            Path path = Paths.get(folderPath);
            File petalData = new File(saveFilePath);
            File archiveData = new File(archiveFilePath);

            Files.createDirectories(path);
            petalData.createNewFile();
            archiveData.createNewFile();
        } catch (IOException e) {
            isFolderAndFilePresent = false;
        }
        return Responses.START_MESSAGE.toString();
    }

    /**
     * Parses the text from Tasks.txt in tasks and retrieves it as tasks. If it is
     * able to retrieve the tasks, this means that Petal was opened by the user at least
     * once before.
     *
     * @return True if tasks were retrieved, false if no tasks (new user) or exception occurred
     */
    public boolean hasUsedPetalBefore() {
        try {
            ArrayList<Task> savedTasks = readFile(saveFilePath);
            ArrayList<Task> archive = readFile(archiveFilePath);
            taskList.addTasks(savedTasks, archive);
            return true;
        } catch (FileNotFoundException | NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Reads the saved tasks from the PetalData.txt file.
     * Parses the text and converts them into the relevant Task objects
     *
     * @param filePath The address of the file to be read
     * @return ArrayList of the saved tasks
     */
    private ArrayList<Task> readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String taskLine = sc.nextLine();

            String[] components = taskLine.split("\\|");
            String typeOfTask = components[0];
            String descOfTask = components[2];
            String dateTime = (typeOfTask.equals("D") || typeOfTask.equals("E")) ? components[3] : "";
            boolean isDone = Objects.equals(components[1], "X");

            if (typeOfTask.equals("T")) {
                tasks.add(new ToDo(descOfTask, isDone));
            } else if (typeOfTask.equals("D")) {
                tasks.add(new Deadline(descOfTask, dateTime, isDone));
            } else {
                tasks.add(new Event(descOfTask, dateTime, isDone));
            }
        }
        return tasks;
    }

    /**
     * Saves the tasks in the task list.
     * If the folder/file couldn't be created properly, the bot doesn't save the tasks.
     *
     * @throws IOException Thrown if tasks are not saved properly
     */
    public void saveTasks() throws IOException {
        if (!isFolderAndFilePresent) {
            return;
        }

        FileWriter currFileWriter = new FileWriter(saveFilePath);
        FileWriter archiveFileWriter = new FileWriter(archiveFilePath);

        currFileWriter.write(taskList.formatForCurrSaving());
        archiveFileWriter.write(taskList.formatForArchivesSaving());

        currFileWriter.close();
        archiveFileWriter.close();
    }



}
