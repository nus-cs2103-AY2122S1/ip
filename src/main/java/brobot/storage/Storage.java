package brobot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import brobot.UI;
import brobot.task.Deadline;
import brobot.task.Event;
import brobot.task.Task;
import brobot.task.TaskList;
import brobot.task.Todo;


/**
 * Represents the brobot.storage of the Brobot Program. Provides methods to handle the brobot.storage.
 */
public class Storage {
    private String defaultDataFilePath;
    private File dataFile;
    private final File savedDataFilePath = new File("./saveFilePath.txt");

    /**
     * Constructor for the Storage Class.
     *
     * @param defaultDataFilePath The default data file location in the system.
     */
    public Storage(String defaultDataFilePath) {
        //initialize the filePath and file object with the specified filePath
        this.defaultDataFilePath = defaultDataFilePath;
        this.dataFile = new File(defaultDataFilePath);

        if (!savedDataFilePath.exists()) {
            initializeSaveFile();
        }
        loadSaveFile();
        if (!dataFile.exists()) {
            initializeDataFile();
        }
    }

    /**
     * Creates the save file only if it does not exist(for first-time user)
     */
    public void initializeSaveFile() {
        //create save file
        try {
            savedDataFilePath.createNewFile();
            FileWriter fw = new FileWriter(savedDataFilePath);
            fw.write(defaultDataFilePath);
            fw.close();
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
    }

    /**
     * Loads the saved File path into the actual data File path
     */
    public void loadSaveFile() {
        //check for save file
        try {
            Scanner s = new Scanner(savedDataFilePath);
            defaultDataFilePath = s.nextLine();
            dataFile = new File(defaultDataFilePath);
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
    }

    /**
     * Creates the data file only if it does not exist(for first-time user)
     */
    public void initializeDataFile() {
        //create data file
        try {
            dataFile.getParentFile().mkdirs();
            dataFile.createNewFile();
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
    }

    /**
     * Getter method for the current brobot.storage file for when brobot.storage change fails.
     * @return the current brobot.storage file
     */
    public File getCurrentFile() {
        return dataFile;
    }
    /**
     * change file path to the specified file path and move the current list over
     */
    public void changeFilePath(File newFile) {
        String newFileAbsolutePath = newFile.getAbsolutePath();
        try {
            FileWriter fw = new FileWriter(savedDataFilePath);
            fw.write(newFileAbsolutePath + "/list1.txt");
            fw.close();
        } catch (Exception e) {
            UI.printToTerm(UI.getErrorText(e));
        }
        this.defaultDataFilePath = newFileAbsolutePath + "/list1.txt";
        dataFile.renameTo(new File(defaultDataFilePath));
    }
    /**
     * Reads the list from the brobot.storage file and translates it into a TaskList object.
     *
     * @return A TaskList.
     */
    public TaskList readList() {
        TaskList list = new TaskList();
        try {
            Scanner s = new Scanner(dataFile);
            while (s.hasNext()) {
                String[] singleTask = s.nextLine().split(" \\| ", 4);
                processTaskInStorageFormat(singleTask, list);

            }
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
        return list;
    }

    /**
     * Parse a single task in brobot.storage format and adds it into the task list.
     * @param singleTask The task to be parsed
     * @param list The list to add the task into
     */
    private void processTaskInStorageFormat(String[] singleTask, TaskList list) {
        String taskType = singleTask[0];
        boolean isTaskDone = singleTask[1].equals("1");
        String taskContent = singleTask[2];
        switch (taskType) {
        case "T":
            Task t = new Todo(taskContent);
            if (isTaskDone) {
                t.markComplete();
            }
            list.addTask(t);
            break;
        case "D":
            String deadlineDate = singleTask[3];
            Task d = new Deadline(taskContent, LocalDateTime.parse(deadlineDate,
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            if (isTaskDone) {
                d.markComplete();
            }
            list.addTask(d);
            break;
        case "E":
            String eventDate = singleTask[3];
            Task e = new Event(taskContent, LocalDateTime.parse(eventDate,
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            if (isTaskDone) {
                e.markComplete();
            }
            list.addTask(e);
            break;
        default:
            break;
        }
    }

    /**
     * Writes a TaskList object into the brobot.storage file.
     *
     * @param list The TaskList to be written into the file.
     */
    public void writeList(TaskList list) {
        try {
            assert(new File(defaultDataFilePath).exists());
            assert(new File(defaultDataFilePath).canWrite());
            FileWriter fw = new FileWriter(defaultDataFilePath);
            fw.write(list.toStorageString());
            fw.close();
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
    }
}
