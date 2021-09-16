package ponyo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import ponyo.common.Messages;
import ponyo.common.Prefixes;
import ponyo.data.exceptions.PonyoException;
import ponyo.data.task.Deadline;
import ponyo.data.task.Event;
import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.data.task.Todo;

/**
 * Handles loading and saving of tasks into the file created.
 */
public class Storage {
    private static final String PATH = "data";
    protected String filePath;

    /**
     * Constructor for a Storage object
     *
     * @param filePath file path of the stored file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the list of tasks in hard disk.
     *
     * @return the list of tasks stored in hard disk.
     */
    public ArrayList<Task> load() {
        try {
            return readTasksFromFile(filePath);
        } catch (IOException e) {
            throw new PonyoException(Messages.MESSAGE_ERROR_LOADING_FILE);
        }
    }

    /**
     * Checks if the file and folder exists.
     *
     * @param filePath path to check
     * @throws IOException if file creation fails
     */
    public void checkIfFileFolderExists(String filePath) throws IOException {
        File file = new File(filePath);
        File directory = new File(PATH);

        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }

        assert directory.exists() : "Directory does not exist";
        assert file.exists() : "File does not exist";
    }

    /**
     * Formats the text to be written in the file.
     *
     * @param t task to be written into file
     */
    public void writeToFile(Task t) {
        try {
            appendToFile(filePath, t.toStringInFile() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Retrieves all the contents in the task list.
     * Used when re-writing the contents in the file.
     *
     * @param tasks the task list to be parsed
     */
    public void getFullContents(TaskList tasks) {
        try {
            String allContent = "";
            for (int i = 0; i < tasks.size(); i++) {
                allContent += tasks.retrieveTask(i).toStringInFile() + "\n";
            }
            overwriteFile(filePath, allContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Returns a list of tasks stored in the file.
     *
     * @param filePath path of file
     * @throws IOException if error reading file
     */
    private ArrayList<Task> readTasksFromFile(String filePath) throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        checkIfFileFolderExists(filePath);

        Scanner fileScanner = new Scanner(new File(filePath));
        while (fileScanner.hasNext()) {
            tasks.add(readEachTask(fileScanner));
        }
        fileScanner.close();
        return tasks;
    }

    /**
     * Reads each task and creates a new task instance per line.
     *
     * @param fileScanner Scanner object to parse the file
     * @return a Task to be added into the global TaskList
     */
    private Task readEachTask(Scanner fileScanner) {
        Scanner lineScanner = new Scanner(fileScanner.nextLine());
        lineScanner.useDelimiter(Pattern.compile("(\\n)| - "));

        String taskCode = lineScanner.next();

        String[] taskDetails = parseTaskDetails(lineScanner);
        boolean isMarkedDone = taskDetails[0].equals("1");
        String description = taskDetails[1];
        String date = taskDetails.length > 2 ? taskDetails[2] : null;

        switch (taskCode) {
        case Prefixes.PREFIX_TODO:
            Task task = new Todo(description);
            return markTaskIfMarkedDone(isMarkedDone, task);
        case Prefixes.PREFIX_DEADLINE:
            task = new Deadline(description, date);
            return markTaskIfMarkedDone(isMarkedDone, task);
        case Prefixes.PREFIX_EVENT:
            task = new Event(description, date);
            return markTaskIfMarkedDone(isMarkedDone, task);
        default:
            break;
        }

        throw new PonyoException(Messages.MESSAGE_ERROR_LOADING_FILE);
    }

    /**
     * Marks a task as done if it is marked done in file
     *
     * @param isMarkedDone true if task is marked done, false otherwise
     * @param task the task to be marked
     * @return the same task to be checked
     */
    private Task markTaskIfMarkedDone(boolean isMarkedDone, Task task) {
        if (isMarkedDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Parses the task details in each line of file.
     *
     * @param lineScanner a Scanner object to parse a line
     * @return a String array of task details: marked, description, date.
     */
    private String[] parseTaskDetails(Scanner lineScanner) {
        int size = 0;
        String[] taskDetails = new String[3];
        while (lineScanner.hasNext()) {
            taskDetails[size] = lineScanner.next();
            size++;
        }
        lineScanner.close();
        return taskDetails;
    }

    /**
     * Appends a given text to the end of the file.
     *
     * @param filePath the path of file to be written
     * @param textToAppend the text to be appended in the file
     * @throws IOException if text appending fails
     */
    private void appendToFile(String filePath, String textToAppend) throws IOException {
        checkIfFileFolderExists(filePath);
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Overwrites the file with the new file content.
     *
     * @param filePath the path of file to be written
     * @param fileContent the text content to be written in the file
     * @throws IOException if overwriting file fails
     */
    private void overwriteFile(String filePath, String fileContent) throws IOException {
        checkIfFileFolderExists(filePath);
        FileWriter fw = new FileWriter(filePath);
        fw.write(fileContent);
        fw.close();
    }
}
