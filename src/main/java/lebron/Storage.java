package lebron;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lebron.exception.LebronException;
import lebron.task.*;

/**
 * Represents a storage class to handle writing and loading from a file.
 *
 * @author Nigel Tan
 */
public class Storage {
    private File file;
    private final String filePath;

    /**
     * Constructor.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            //if file does not exist
            if (!file.isFile()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            this.file = file;
        } catch (IOException e) {
            System.out.println("Something went wrong when creating/finding directory: "
                    + e.getMessage());
        }
    }

    /**
     * Loads the file contents and saves it in an arraylist.
     *
     * @param filePath the file path.
     * @return an ArrayList containing tasks.
     */
    public ArrayList<Task> loadFileContents(String filePath) {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source

            while (s.hasNext()) {
                String line = s.nextLine();
                String[] splitWords = line.split(" \\| ", 4);
                String taskName = splitWords[0];
                String isDoneValue = splitWords[1];
                Task task;
                switch (taskName) {
                case "T":
                    this.handleCaseToDo(splitWords, isDoneValue, taskList);
                    break;
                case "D":
                    this.handleCaseDeadline(splitWords, isDoneValue, taskList);
                    break;
                case "E":
                    this.handleCaseEvent(splitWords, isDoneValue, taskList);
                    break;
                default:
                    System.out.println(":( OOPS! There is a task that I don't recognise.\n");
                    break;
                }

            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when loading file contents "
                    + e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Saves the current list of tasks into the specified file.
     *
     * @param taskList the list of tasks.
     */
    public void saveToFile(TaskList taskList) {
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < taskList.getLst().size(); i++) {
                String textToAppend = taskList.getItem(i).getStringForFile();
                builder.append(textToAppend).append("\n");
            }
            FileWriter fw = new FileWriter(filePath);
            fw.write(builder.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when saving file contents "
                    + e.getMessage());
        }
    }

    /**
     * Handles the case where the task is a ToDo.
     *
     * @param splitWords the parsed input from text file.
     * @param isDoneValue "1" if task is done, "0" if task is not done.
     * @param taskList the tasklist.
     */
    private void handleCaseToDo(String[] splitWords, String isDoneValue, ArrayList<Task> taskList) {
        Task task = new ToDo(splitWords[2]);
        if (isDoneValue.equals("1")) {
            task.markAsDone();
        }
        taskList.add(task);
    }

    /**
     * Handles the case where the task is a Deadline.
     *
     * @param splitWords the parsed input from text file.
     * @param isDoneValue "1" if task is done, "0" if task is not done.
     * @param taskList the tasklist.
     */
    private void handleCaseDeadline(String[] splitWords, String isDoneValue, ArrayList<Task> taskList) {
        try {
            String deadline = splitWords[3];
            String[] dateTimeDeadline = deadline.split(" ", 2);
            Task task = new Deadline(splitWords[2], dateTimeDeadline[0], dateTimeDeadline[1]);
            if (isDoneValue.equals("1")) {
                task.markAsDone();
            }
            taskList.add(task);
        } catch (LebronException e) {
            System.err.println(e);
        }
    }

    /**
     * Handles the case where the task is a Event.
     *
     * @param splitWords the parsed input from text file.
     * @param isDoneValue "1" if task is done, "0" if task is not done.
     * @param taskList the tasklist.
     */
    private void handleCaseEvent(String[] splitWords, String isDoneValue, ArrayList<Task> taskList) {
        try {
            String at = splitWords[3];
            String[] dateTimeEvent = at.split(" ", 2);
            Task task = new Event(splitWords[2], dateTimeEvent[0], dateTimeEvent[1]);
            if (isDoneValue.equals("1")) {
                task.markAsDone();
            }
            taskList.add(task);
        } catch (LebronException e) {
            System.err.println(e);
        }
    }
}
