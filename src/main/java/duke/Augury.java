package duke;

import duke.exceptions.*;
import duke.tasks.Task;
import duke.tasks.TaskFactory;
import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.io.Parser;
import duke.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Augury} class contains the entry point of the entire Task Management app.
 * Create an instance of {@code Augury}, initialize with {@code init()}, and start the program
 * with {@code loop()}.
 *
 * <p>{@code Augury} takes in a {@code String path} as argument in its constructor.
 * This file stores data created by the app. If no file exists at the specified path,
 * {@code Augury} will create a new file.</p>
 *
 * @author Jefferson (@qreoct)
 */
public class Augury {
    static String VER     = "v0.8.0"; // Level-9 Find
    static String WELCOME =
            "\t+-------------------------------+\n" +
            "\t| *                 *         * |\n" +
            "\t|   (`<       augury     *      |\n" +
            "\t| __/_)_______________________  |\n" +
            "\t|   ||                      *   |\n" +
            "\t|   ||   a task manager         |\n" +
            "\t|      *             *          |\n" +
            "\t|             *         "+VER+"  |\n" +
            "\t+-------------------------------+";
    private TaskList taskList = new TaskList();
    private Storage storage;
    private Ui ui;
    private Parser parser;

    /**
     * Initialises a new {@code Augury} object which uses the
     * provided {@code String path} as location of the .txt save file.
     *
     * @param path A {@code String} containing the location of the .txt save file.
     */
    public Augury(String path) {
        ui = new Ui();
        this.storage = new Storage(path);
        this.parser = new Parser();
    }

    /**
     * Initialises the private {@code TaskList} using the data from the
     * save file provided.
     *
     * @throws FileIOException If file cannot be read or created
     */
    public void init() throws AuguryException {
        try {
            this.storage.initializeTaskList(this.taskList);
        } catch (IOException e) {
            throw new FileIOException(e.getMessage());
        }
    }

    /**
     * Prints a welcome message to the user.
     */
    public void greet() {
        System.out.println(WELCOME);
    }

    /**
     * Main loop of {@code Augury}. Parses and execute commands in a loop.
     *
     * @throws InvalidActionException If action commands were malformed.
     * @throws InvalidTaskCreationException If invalid parameters were provided in task creation.
     * @throws UnknownCommandException If an unrecognized command was provided.
     */
    public void loop() throws AuguryException {
        Scanner scan = new Scanner(System.in);
        ui.speak("Hello! How may I help you?");

        boolean isRunning = true;

        while (isRunning) {
            String input = scan.nextLine().trim().toLowerCase();
            try {
                String command = parser.parse(input);
                switch(command){
                case "COMMAND_QUIT":
                    ui.speak("The readiness is all.");
                    isRunning = false;
                    break;
                case "COMMAND_LIST_TASKS":
                    handleListTasks();
                    break;
                case "COMMAND_MARK_TASK_STATUS":
                    handleMarkAsDone(input);
                    break;
                case "COMMAND_DELETE_TASK":
                    handleDeleteTasks(input);
                    break;
                case "COMMAND_MAKE_TASK":
                    handleAddTask(input);
                    break;
                case "COMMAND_FIND_TASKS":
                    handleFindTasks(input);
                    break;
                case "COMMAND_UNKNOWN":
                    throw new UnknownCommandException("Unknown command.");
                default:
                    throw new AuguryException("Something went wrong.");
                }
            } catch (AuguryException e) {
                ui.speak(e.getMessage() + "\n\t Please try again.");
            }
        }

    }

    private void handleAddTask(String arg) throws AuguryException {
        String type = arg.split(" ")[0];

        try {
            TaskFactory tf = new TaskFactory();
            Task newTask = tf.createTask(type, arg);

            if (newTask == null) {
                throw new UnknownCommandException("Invalid command entered when creating task.");
            }
            ui.speak(taskList.addTaskAndAnnounce(type, newTask));
            storage.saveTaskListToStorage(taskList);
        } catch (AuguryException e) {
            throw new AuguryException(e.getMessage());
        }
    }

    private void handleListTasks() {
        ui.speak(taskList.toString());
    }

    private void handleMarkAsDone(String args) throws AuguryException {
        // check if args exist
        if (args.length() <= 5) {
            throw new InvalidActionException("Please enter the task number which you want to mark as done.");
        }

        args = args.substring(5);
        ArrayList<Integer> listOfTasks = convertCommaSeparatedStringToIntegerArrayList(args);

        for (Integer i : listOfTasks){
            if (i > taskList.size()) {
                throw new InvalidActionException("Task " + i + " does not exist, please try again");
            }
        }
        ui.speak(taskList.markAsDoneAndAnnounce(listOfTasks));
        storage.saveTaskListToStorage(taskList);
    }

    private void handleDeleteTasks(String args) throws AuguryException {
        // check if args exist
        if (args.length() <= 7) {
            throw new InvalidActionException("Please enter the task number which you want to delete.");
        }

        args = args.substring(7);
        ArrayList<Integer> listOfTasks = convertCommaSeparatedStringToIntegerArrayList(args);

        for (Integer i : listOfTasks){
            if (i > taskList.size()) {
                throw new InvalidActionException("Task " + i + " does not exist, please try again.");
            }
        }
        ui.speak(taskList.deleteTasksAndAnnounce(listOfTasks));
        storage.saveTaskListToStorage(taskList);
    }

    private void handleFindTasks(String args) throws AuguryException {
        if (args.length() <= 5) {
            throw new InvalidActionException("Please enter the search string which you want to find.");
        }

        args = args.substring(5);
        ui.speak(taskList.findAndAnnounce(args));

    }

    private ArrayList<Integer> convertCommaSeparatedStringToIntegerArrayList (String s) {
        // split comma-separated string to String[]
        String[] s_String = s.split(",");
        for (int i = 0; i < s_String.length; i++) {
            // trim whitespace on each item
            s_String[i] = s_String[i].trim();
        }
        // convert String[] to ArrayList<Integer>
        ArrayList<Integer> s_Integer = new ArrayList<>();
        for (String ss : s_String) {
            s_Integer.add(Integer.parseInt(ss));
        }
        return s_Integer;
    }
}
