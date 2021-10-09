package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.LoadingException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the class to store the data.
 *
 * @author QIN GUORUI
 */
public class Storage {
    /** The recommended working directory path. */
    private static String dir = System.getProperty("user.dir");

    /** The content stored in the file. */
    private String fileContent;

    /** Relative file path. */
    private String relativeFilePath;

    /** The actual file name. */
    private String file;

    /**
     * Sets up the store for data.
     *
     * @param relativeFilePath Where to store data.
     */
    public Storage(String relativeFilePath) {
        this.relativeFilePath = relativeFilePath;
        this.file = relativeFilePath.substring(4);
    }

    /**
     * Returns the task list stored in data file.
     *
     * @return The task list.
     * @throws LoadingException The exception related to loading.
     */
    public ArrayList<Task> load() throws LoadingException {
        Scanner s = buildFileAndScanner();
        ArrayList<Task> taskList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (s.hasNextLine()) {
            String task = s.nextLine();
            int lens = task.length();
            String oneLine = task + System.lineSeparator();
            sb.append(oneLine);
            addRelatedTasks(task, taskList, lens); // Judge data input to add tasks.
        }
        upDateContent(sb); //Update the content.
        return taskList;
    }

    /**
     * Builds the file and scanner required to read input.
     *
     * @return The scanner that takes in the file content input.
     * @throws LoadingException The exception happens during creating the file.
     */
    public Scanner buildFileAndScanner() throws LoadingException {
        java.nio.file.Path path = java.nio.file.Paths.get(dir, "data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
            new File(dir + "/data").mkdir();
        }
        File f = new File(path + file);
        Scanner s;
        try {
            f.createNewFile();
            s = new Scanner(f);
            return s;
        } catch (IOException e) {
            throw new LoadingException();
        }
    }

    /**
     * Adds the tasks to the tasklist.
     *
     * @param task The task input.
     * @param taskList The list store tasks.
     * @param lens The length of task string.
     */
    public void addRelatedTasks(String task, ArrayList<Task> taskList, int lens) {
        char type = task.charAt(0);
        char done = task.charAt(4);
        if (type == 'T') {
            taskList.add(addTodo(task, lens, done));
        } else {
            taskList.add(addEventOrDeadline(task, lens, type, done));
        }
    }

    /**
     * Returns the deadline or event task added.
     *
     * @param task The string form of task.
     * @param lens The length of task.
     * @param type The type representing event or deadline.
     * @param done The char representing done or not.
     * @return The added deadline or event task.
     */
    public Task addEventOrDeadline(String task, int lens, int type, char done) {
        String splitString = " ~ ";
        int splitIndex = 8;
        String[] parts = task.substring(splitIndex, lens).split(splitString);
        String content = parts[0];
        String time = parts[1];
        Task deadlineOrEvent;
        if (type == 'D') {
            deadlineOrEvent = new Deadline(content, time);
        } else {
            deadlineOrEvent = new Event(content, time);
        }
        if (done == '1') {
            deadlineOrEvent.markAsDone();
        }
        return deadlineOrEvent;
    }

    /**
     * Returns the todo task added.
     *
     * @param task The task in string form.
     * @param lens The length of task.
     * @param done The char representing done or not.
     * @return The added todo task.
     */
    public Task addTodo(String task, int lens, char done) {
        Task todo;
        int splitIndex = 8;
        if (done == '1') {
            todo = new Todo(task.substring(splitIndex, lens));
            todo.markAsDone();
        } else {
            todo = new Todo(task.substring(splitIndex, lens));
        }
        return todo;
    }

    /**
     * Writes the text to data file, which overwrites initial storage.
     *
     * @param filePath The path to data file.
     * @param textToAdd The overwritten text.
     * @throws IOException When write to file fails.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Adds the task to data file.
     *
     * @param task The string format of task representation.
     * @throws IOException The exception happened during write to file.
     */
    public void store(String task) throws IOException {
        String dataFilePath = dir + "/" + relativeFilePath;
        String data = transformToData(task);
        StringBuilder sb = new StringBuilder();
        sb.append(fileContent);
        sb.append(data);
        sb.append(System.lineSeparator());
        appendToFile(dataFilePath, data + System.lineSeparator());
        upDateContent(sb);
    }

    /**
     * Transforms the task representation to storage form in data file.
     *
     * @param task The task string representation.
     * @return The string format stored in the data file.
     */
    public static String transformToData(String task) {
        int lens = task.length();
        char type = task.charAt(1); //char at index 1 represents type.
        char done = task.charAt(4); //char at index 4 represents status of done.
        int splitIndex = 7;
        String splitString = "by: ";
        String prefix = "0";
        String dataForm;
        //Transform to different data form according to the type of task.
        if (type == 'E') {
            splitString = "at: ";
        }
        if (done == 'X') {
            prefix = "1";
        }
        if (type == 'T') {
            dataForm = "T | " + prefix + " | " + task.substring(splitIndex, lens);
        } else {
            String[] parts = task.substring(splitIndex, lens).split(splitString);
            String contentPart = parts[0];
            int lensContentPart = contentPart.length();
            String actualContent = contentPart.substring(0, lensContentPart - 2); //Just extract the content.
            String timePart = parts[1];
            int lensTimePart = timePart.length();
            String actualTime = timePart.substring(0, lensTimePart - 1); //extract out the time representation.
            dataForm = type + " | " + prefix + " | " + actualContent + " ~ " + actualTime;
        }
        return dataForm;
    }

    /**
     * Appends the text to existing data file.
     *
     * @param filePath The path to data file.
     * @param textToAppend The text tend to add.
     * @throws IOException When the append operation fails.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Replaces the unmarked task as marked, or just removes the task.
     *
     * @param place The index of task in task list.
     * @param tasks The task list.
     */
    public void replace(int place, TaskList tasks) {
        String dataFilePath = dir + "/" + relativeFilePath;
        String[] parts = fileContent.split(System.lineSeparator());
        int lens = parts.length;
        String newData = "";
        if (tasks != null) {
            newData = transformToData(tasks.elementToString(place));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lens; i++) {
            String temp = parts[i];
            if (i == place && tasks == null) {
                continue;
            } else if (i == place) {
                temp = newData;
            }
            temp = temp + System.lineSeparator();
            sb.append(temp);
        }
        upDateContent(sb);
        try {
            writeToFile(dataFilePath, fileContent);
        } catch (IOException e) {
            System.out.println("Delete failed.");
        }
    }

    /**
     * Updates the data stored in content variable.
     *
     * @param sb The string builder that holds the content to be updated.
     */
    public void upDateContent(StringBuilder sb) {
        fileContent = sb.toString();
    }
}
