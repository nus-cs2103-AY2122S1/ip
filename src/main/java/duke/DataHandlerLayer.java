package duke;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Handles all things related to data. This includes the in-memory storage represented by an arrayList or
 * the persistent storage handled by the .txt files.
 */
public class DataHandlerLayer {

    public static void main(String[] args) {
        Function<Task, Boolean> tempFunc = a -> !a.getCompleteStatus();
        addToLog(new Todo("smth"));
        Task tempTask = new Todo("another");
        tempTask.completeTask();
        addToLog(tempTask);
        addToLog(new Todo("smth"));
        addToLog(new Todo("smth"));
        System.out.println("=====================");
        System.out.println(getFilteredLog(tempFunc));
        System.out.println("=====================");
        System.out.println("smth");
    }

    /**
     * In memory storage for log, for history, refer to the history variable
     */
    private static ArrayList<Task> log = new ArrayList<>();

    /**
     * History file that will be written to
     */
    private static String fileName = "src/main/Text/History.txt";

    /**
     * Init data storage handler for history.
     */
    private static PersistentStorageHandler history = new PersistentStorageHandler(fileName);


    /**
     * Adds the command to the log.
     *
     * @param task duke.Command to be added to the log
     */
    public static void addToLog(Task task) {
        log.add(task);
    }

    /**
     * Returns the task at the specific log postion
     *
     * @param position
     * @return duke.Task
     */
    public static Task getTask(int position) {
        return log.get(position);
    }

    /**
     * Prints a log of tasks that are filtered. True for completed and False for not completed
     */
    public static String getFilteredLog(Function<Task , Boolean> function) {
        int taskNumber = 1;
        StringBuilder sb = new StringBuilder();
        System.out.println("getFilteredLog caled");
        for (Task temp : log) {
            if (function.apply(temp)) {
                sb.append(taskNumber + ". " + temp.toString() + "\n");
                taskNumber++;
            }
        }
        return sb.toString();
    }

    /**
     * Used for the filter keyword. Searches the log for any strings
     * and prints any task that contains it.
     * @param keyword keyword that is being searched
     */
    public static String filterLog(String keyword) {
        assert keyword != "";
        String acc = "";
        for (int i = 0; i < log.size(); i++) {
            Task temp = log.get(i);
            if (temp.toString().contains(keyword)) {
                Task currentTask = log.get(i);
                int taskNumber = i + 1;
                acc += taskNumber + ". " + currentTask.toString();
            }
        }
        return acc;
    }


    /**
     * Deletes any task in the specified postion of log
     * @param position in the log which is based on index.
     * @throws IndexOutOfBoundsException If the player specifies a postion outside the length of the log
     * this error is thrown
     */
    public static void delete(int position) throws IndexOutOfBoundsException {
        assert position >= 0;
        if (position == 0) {
            throw new IndexOutOfBoundsException();
        }
        ;
        log.remove(position - 1);
    }

    /**
     * Prints all the lines in the history
     */
    public static void printHistory() {
        history.printAllLines();
    }

    /**
     * Closes the printer
     */
    public static void stopWriting() {
        history.stopWriting();
    }

    /**
     * Packages the data(in string) into Commands in the persistent storage using
     * this commands. The data is packaged as
     * Command classes. To be passed to Logic for processing
     * @return An arraylist of packaged commands
     * @throws InvalidCommandException thrown when any of the commands in string cannot be packaged as a command class.
     */
    public static ArrayList<Command> loadPreset() throws InvalidCommandException {
        return Parser.parsePreloadedTasks(history.getAllLines());
    }

    /**
     * Appends any task written to history.
     * @param task
     */
    public static void appendToHistory(Task task) {
        history.write(task.toString());
    }

    /**
     * Updates history and writes stuff down. Will auto clear all the tasks that is not done.
     */
    public static void updateHistory() {
        history.clearHistory();
        for (Task task : log) {
            if (!task.getCompleteStatus()) {
                appendToHistory(task);
            }
        }
    }

    public static String getLogAsString() {
        String acc = "";
        for (int i = 0; i < log.size(); i++) {
            Task currTask = log.get(i);
            String index = String.format("%d. ", i + 1);
            acc += index + currTask.toString();
            acc += "\n";
        }
        return acc;
    }
}
