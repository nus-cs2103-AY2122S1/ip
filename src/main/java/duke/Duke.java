package duke;

import java.util.HashMap;

/**
 * The Duke chat-bot app.
 */
public class Duke {

    /** Hard disk storage */
    private final Storage storage;

    /** User input parser */
    private final Parser parser;

    /** The list of tasks */
    private TaskList taskList;

    /** True if Duke is still running */
    private boolean isRunning;


    /**
     * Duke class constructor.
     * @param directory Relative path to directory of the saved data.
     * @param file Name of the saved data file.
     */
    public Duke(String directory, String file) {
        assert directory != null : "[duke.Duke.Duke]: directory parameter should not be null.";
        assert file != null : "[duke.Duke.Duke]: file parameter should not be null.";

        storage = new Storage(directory, file);
        parser = new Parser();
        try {
            // Get stored data.
            taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            Ui.showMessage(e.getMessage());
            taskList = new TaskList(storage);
        }
        isRunning = true;
    }

    /**
     * Entry point of the Duke program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("./data", "duke.txt").run();
    }


    /**
     * Displays the Duke UI and process user input commands.
     */
    public void run() {

        // Show Greetings.
        Ui.showGreetings();

        // Get and process input.
        String rawInput = "";
        String output = "Jak się masz? My name-a Borat. I like you.\nWhat I do for you?";
        while (isRunning) {
            // Gets user input
            Ui.showMessage(output);
            rawInput = Ui.getInput();
            output = getResponse(rawInput);
        }
        // Goodbye message
        Ui.showMessage(Ui.getGoodByeMessage());
    }

    /**
     * Returns a response by Duke given a user input.
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        assert input != null : "[duke.Duke.getResponse]: input parameter is null";
        String output = "";
        try {
            // Parses user input.
            HashMap<String, String> inputs = parser.parseInput(input);
            Constant.Command command = Constant.Command.valueOf(inputs.get("command"));
            String task;

            // Process user input.
            switch (command) {
            case HELP:
                // Gets user manual.
                output = Ui.getHelpMenu();

                break;
            case BYE:
                // Quits program.
                isRunning = false;

                break;
            case LIST:
                // Gets the string represented tasks in the task list.
                output = taskList.getAllTask();

                break;
            case DONE:
                // Marks a task as being completed.
                int index = parser.convertToInt(inputs.get("index")) - 1;
                output = taskList.markDone(index);

                break;
            case TODO:
                // Adds a todo-typed task to the task list.
                Todo todo = new Todo(inputs.get("description"));
                output = taskList.addItem(todo);

                break;
            case DEADLINE:
                // Adds a deadline-typed task in the task list.
                Deadline deadline = new Deadline(inputs.get("description"), inputs.get("date"));
                output = taskList.addItem(deadline);

                break;
            case EVENT:
                // Adds an event-typed task in the task list.
                Event event = new Event(inputs.get("description"), inputs.get("date"));
                output = taskList.addItem(event);

                break;
            case DELETE:
                // Deletes a task from the task list.
                int id = parser.convertToInt(inputs.get("index")) - 1;
                output = taskList.removeItem(id);
                break;
            case DATES:
                // Gets the accepted date types.
                output = Ui.getAllAcceptedDates();

                break;
            case FIND:
                // Gets the task matching the queried keyword.
                output = taskList.find(inputs.get("keyword"));

                break;
            default:
                output = "Invalid Message";
            }

            // Return the output message.
            return output;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns true when duke is awake and false otherwise.
     * @return True when duke is awake and false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }
}
