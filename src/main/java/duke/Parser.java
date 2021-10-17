package duke;

import java.io.IOException;

/**
 * This class parses the user input into a command understood by Duke.
 */
public class Parser {
    private final Storage storage;
    private TaskList tasks;

    /**
     * Initializes Storage and TaskList classes.
     *
     * @param filePath file URL where task information will be stored.
     */
    public Parser(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the first word of raw user input.
     *
     * @param input Raw user input.
     * @return The first word which is the command for Duke to understand.
     */
    public String parse(String input) {
        String command = input.split(" ")[0];
        return handleQuery(command, input);
    }

    /**
     * Allows Duke to handle the query by the user.
     * Error controls to tackle EmptyDescriptionError and UnknownCommandError.
     * Command parsed by parse() method will be used to identify the query type.
     * "bye" command will store taskList as a duke.txt file, then return null
     * which is picked up by the Ui class to shut down the window.
     *
     * @param command used to classify the query type from the user.
     * @param input contains the overall user input.
     */
    private String handleQuery(String command, String input) {
        switch (command) {
            case "bye":
                try {
                    storage.store(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            case "list":
                return tasks.list();
            case "done":
                Task t = tasks.done(Integer.parseInt(input.substring(5)) - 1);
                return done(t);
            case "delete":
                t = tasks.delete(Integer.parseInt(input.substring(7)) - 1);
                return delete(t, tasks.getSize());
            case "find":
                String keyword = input.substring(5);
                return tasks.find(keyword);
            default:
                try {
                    return create(command, input.trim());
                } catch (UnknownCommandError e) {
                    return e.getMessage();
                } catch (IndexOutOfBoundsException e) {
                    return new EmptyDescriptionError(command).getMessage();
                }
        }
    }

    /**
     * Show users that the task has been added successfully to taskList.
     *
     * @param t The task being added.
     * @param size The current size of taskList.
     */
    public String add(Task t, int size) {
        return "     Got it. I've added this task: \n"
                + String.format("       %s\n", t)
                + String.format("     Now you have %d tasks in the list.\n", size);
    }

    /**
     * Show users that the task has been successfully marked as completed.
     *
     * @param t The taks that is marked as completed.
     */
    public String done(Task t) {
        return "     Nice! I've marked this task as done: \n"
                + String.format("       %s\n", t);
    }

    /**
     * Show users that the task has been deleted successfully from the taskList.
     *
     * @param t The task being deleted.
     * @param size The current size of the taskList.
     */
    public String delete(Task t, int size) {
        return "     Noted. I've removed this task: \n"
                + String.format("       %s\n", t)
                + String.format("     Now you have %d tasks in the list.\n", size);
    }

    /**
     * Handles queries if the user wishes to create a new task
     *
     * @param command used to classify the query type from the user.
     * @param input contains the overall user input.
     * @throws IndexOutOfBoundsException used to check if input specifies description
     * of the task to be created.
     * @throws UnknownCommandError used to check if the command is unknown to Duke.
     */
    public String create(String command, String input)
            throws IndexOutOfBoundsException, UnknownCommandError {
        Task t;
        switch (command) {
            case "todo":
                t = new Todo(input);
                break;
            case "event":
                t = new Event(input);
                break;
            case "deadline":
                t = new Deadline(input);
                break;
            case "fixed":
                t = new FixedTask(input);
                break;
            default:
                throw new UnknownCommandError("");
        }
        tasks.add(t);
        return add(t, tasks.getSize());
    }
}