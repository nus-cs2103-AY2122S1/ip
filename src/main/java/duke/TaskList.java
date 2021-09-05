package duke;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contains the task list and all relevant list methods
 */
public class TaskList {
    private static ArrayList<Task> taskList;
    private static int counter;

    /**
     * Creates the taskList and a counter to keep track of the number of tasks saved in the user's
     * hard disk at any point in time
     */
    public TaskList() {
        taskList = new ArrayList<Task>(100);
        counter = 0;
    }

    /**
     * Returns the taskList
     *
     * @return the taskList
     */
    public static ArrayList<Task> getTaskList() {
        return TaskList.taskList;
    }

    /**
     * Returns the number of tasks currently save in user's hard disk
     *
     * @return the number of tasks currently save in user's hard disk (counter member)
     */
    public static int getCounter() {
        return TaskList.counter;
    }

    /**
     * Adds a task to the taskList
     *
     * @param currTask the task that is to be added to the taskList
     */
    public static void addTask(Task currTask) {
        TaskList.taskList.add(currTask);
        TaskList.counter++;
    }

    /**
     * Adds a task to the taskList and update the list of tasks in user's hard disk
     *
     * @param currTask the task that is to be added
     * @throws IOException if there is an error in appending the task to the list of tasks
     * in user's hard disk
     */
    public static void addTaskAndUpdate(Task currTask) throws IOException {
        TaskList.taskList.add(currTask);
        TaskList.counter++;
        appendToFile("data/jarvis.txt", (counter) + "." +
                TaskList.getTaskList().get(counter - 1).toPrintToFile()
                + System.lineSeparator());
    }

    /**
     * Deletes a task to the taskList and update the list of tasks in user's hard disk
     *
     * @param currTask the task that is to be deleted
     * @throws IOException if there is an error in re-writing the list of tasks without the
     * deleted task
     */
    public static void deleteTaskAndUpdate(Task currTask) throws IOException{
        TaskList.taskList.remove(currTask);
        counter--;
        if (TaskList.getTaskList().size() == 0) {
            writeToFile("data/jarvis.txt", "");
        } else {
            for (int i = 0; i < TaskList.getTaskList().size(); i++) {
                if (i == 0) {
                    writeToFile("data/jarvis.txt", (i + 1) + "." +
                            TaskList.getTaskList().get(i).toPrintToFile()
                            + System.lineSeparator());
                } else {
                    appendToFile("data/jarvis.txt", (i + 1) + "." +
                            TaskList.getTaskList().get(i).toPrintToFile()
                            + System.lineSeparator());
                }
            }
        }
    }

    /**
     * Writes to the file in user's hard disk that stores a list of tasks or to overwrite
     * the contents of this file
     *
     * @param filePath the relative path to the file
     * @param textToAdd the content that is to be written
     * @throws IOException if there is an error in writing to/overwriting the file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Appends content to the file in user's hard disk that stores a list of tasks
     *
     * @param filePath the relative path to the file
     * @param textToAppend the content that is to be appended
     * @throws IOException if there is an error in appending to existing content of the file
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
}
