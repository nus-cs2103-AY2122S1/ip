import java.util.ArrayList;

/**
 * Handles all things related to data. This includes the in-memory storage represented by an arrayList or
 * the persistent storage handled by the .txt files.
 */
public class DataHandlerLayer {


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
     * @param task Command to be added to the log
     */
    public static void addToLog(Task task) {
        log.add(task);
    }

    /**
     * Returns the task at the specific log postion
     *
     * @param position
     * @return Task
     */
    public static Task getTask(int position) {
        return log.get(position);
    }

    /**
     * Prints log of tasks. No parameters needed. To change task representation, see Task.
     */
    public static void printLog() {
        System.out.println("Here are your tasks summoner. Please do complete them as fast as possible. I have" +
                "been waiting for so many others for countless of centuries. Perhaps I am just an npc");
        for (int i = 0; i < log.size(); i++) {
            Task currentTask = log.get(i);
            int taskNumber = i + 1;
            System.out.println(taskNumber + ". " + currentTask.toString());
        }
    }

    /**
     * Prints a log of tasks that are filtered. True for completed and False for not completed
     */
    public static void print_filtered_log(boolean cond) {
        int taskNumber = 1;
        for (Task temp : log) {
            if (temp.getCompletedStatus() == cond) {
                System.out.println(taskNumber + ". " + temp.toString());
                taskNumber++;
            }
        }
    }

    public static void delete(int position) throws IndexOutOfBoundsException {
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

    public static void stopWriting() {
        history.stopWriting();
    }

    public static ArrayList<Command> loadPreset() throws InvalidCommandException {
        return Parser.parsePreloadedTasks(history.getAll_lines());
    }

    public static void appendToHistory(Task task) {
        history.write(task.toString());
    }

    public static void updateHistory() {
        history.clear_history();
        for (Task task : log) {
            if (!task.getCompletedStatus()) {
                appendToHistory(task);
            }
        }
    }
}