import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a task in a task list.
 */
public class Task {

    public final static String BYE_MSG = "Bye mate!";
    private final static ArrayList<Task> taskList = new ArrayList<>();
    private final static String CACHE_PATH = "/data/cache.txt";
    private final static String CACHE_DIR = "/data/";


    protected enum TaskType {
        todo, deadline, event
    }
    protected TaskType category;
    private final String description;
    private boolean completed;
    
    /**
     * Initializes the cache for the task list.
     * 
     * @throws DukeException Thrown if cannot ensure the cache exists.
     */
    public static void initializeCache() throws DukeException {
        File cache = new File(CACHE_PATH);
        File dir = new File(CACHE_DIR);

        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new DukeException("Failed to crete required directory.");
            }
        }

        if (!cache.exists()) {
            try {
                if (!cache.createNewFile()) {
                    throw new DukeException("Failed to crete required cache.");
                }
            } catch (IOException e) {
                throw new DukeException("Failed to crete required cache.");
            }
        }
    }

    /**
     * Stores a line of input into the cache.
     * 
     * @param input Input to be stored, given as a string.
     * @throws DukeException Thrown if cannot write to cache.
     */
    public static void cacheInput(String input) throws DukeException {
        try {
            FileWriter fw = new FileWriter(CACHE_PATH);
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to cache.");
        }
    }

    /**
     * Reads the cache to initialize the task list.
     * 
     * @throws DukeException Thrown if cannot scan the cache.
     */
    public static void readCache() throws DukeException {
        File cache = new File(CACHE_PATH);

        if (cache.exists()) {
            try {
                Scanner sc = new Scanner(cache);
                boolean exit = false;
        
                while(!exit) {
                    try {
                        if (sc.hasNextLine()) {
                            String nextInput = sc.nextLine();
                            Task.cacheInput(nextInput);
                            String[] resultMsg = processInput(nextInput);
                            if (isByeMsg(resultMsg)) {
                                exit = true;
                            }
                        } else {
                            exit = true;
                        }
                    } catch(DukeException e) {
                        // Ignore previous invalid statements and continue adding tasks.
                    }
                }

                sc.close();
            } catch (FileNotFoundException e1) {
                throw new DukeException("Could not scan from cache.");
            }
        }
    }

    /**
     * Processes a command as input and alters the task list.
     * 
     * @param command A command read in as input.
     * @return A response as a string array.
     * @throws DukeException Thrown if input is not a valid command.
     */
    public static String[] processInput(String command) throws DukeException {
        if (command.equals("bye")) {
            // Rmbr to add exit condition to parent function
            return new String[] {BYE_MSG};
        } else if (command.equals("list")){
            return getTaskStrings();
        } else if (command.startsWith("done ")) {
            return markTask(Integer.parseInt(command.substring(5)) - 1);
        } else if (command.startsWith("delete ")) {
            return deleteTask(Integer.parseInt(command.substring(7)) - 1);
        } else if (command.startsWith("todo")) {
            if (command.length() < 6) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return addTask(new Todo(command.substring(5)));
        } else if (command.startsWith("deadline")) {
            if (command.length() < 10) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            int byIndex = command.indexOf("/by");
            if (byIndex == -1) {
                throw new DukeException("/by not found.");
            }
            return addTask(new Deadline(
                command.substring(9, byIndex - 1),
                command.substring(byIndex + 4)));
        } else if (command.startsWith("event")) {
            if (command.length() < 7) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            int atIndex = command.indexOf("/at");
            if (atIndex == -1) {
                throw new DukeException("/at not found.");
            }
            return addTask(new Event(
                command.substring(6, atIndex - 1),
                command.substring(atIndex + 4)));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if a message is a terminating message.
     * 
     * @param msg Message string to be checked.
     * @return Boolean describing if the message is terminal.
     */
    public static boolean isByeMsg(String[] msg) {
        return msg.length == 0 && msg[0].equals(BYE_MSG);
    }

    /**
     * Add a task to the task list
     *
     * @param t Task to be added
     * @return String notifying the task added.
     */
    public static String[] addTask(Task t) {
        taskList.add(t);
        return new String[] {
                "Got it. I've added this task:",
                t.toString(),
                "Now you have "
                        + taskList.size()
                        + " tasks in the list."
        };
    }

    /**
     * Marks a task as completed.
     *
     * @param index Numerical index of task completed.
     * @return String reporting that task is marked done.
     * @throws DukeException Thrown if index out of range.
     */
    public static String[] markTask(int index) throws DukeException {
        if (invalidID(index)) {
            throw new DukeException("Task ID out of range!");
        }

        Task t = taskList.get(index);
        t.completed = true;
        return new String[] {
                "Nice! I've marked this task as done:",
                t.toString()
        };
    }

    /**
     * Delete a task from the list.
     *
     * @param index Numerical index of task to be removed
     * @return String reporting that task is removed.
     * @throws DukeException Thrown if index out of range.
     */
    public static String[] deleteTask(int index) throws DukeException {
        if (invalidID(index)) {
            throw new DukeException("Task ID out of range!");
        }

        Task t = taskList.remove(index);
        return new String[] {
                "Noted. I've removed this task:",
                t.toString(),
                "Now you have "
                        + taskList.size()
                        + " tasks in the list."
        };
    }

    /**
     * Get an array of strings with tasks numbered.
     *
     * @return A String array with numbered tasks.
     */
    public static String[] getTaskStrings() {
        String[] taskStrings = new String[taskList.size() + 1];
        taskStrings[0] = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.size(); i++) {
            taskStrings[i + 1] = (i + 1) + "." + taskList.get(i).toString();
        }
        return taskStrings;
    }

    /**
     * Checks if an ID is an invalid index for the task list.
     *
     * @param ID Integer index of the task.
     * @return Boolean representing validity of ID.
     */
    private static boolean invalidID(int ID) {
        return ID < 0 || ID >= taskList.size();
    }

    /**
     * Constructor for a Task
     *
     * @param description String description of task.
     */
    protected Task(String description) {
        this.description = description;
        this.completed = false;
    }

    /**
     * Gets the status of a task as a checkbox.
     *
     * @return A string representing status of task.
     */
    private String getStatus() {
        return "[" + (completed ? "X" : " ") + "]";
    }

    /**
     * String representation of task.
     *
     * @return String representing task.
     */
    @Override
    public String toString() {
        return getStatus() + " " + description;
    }
}
