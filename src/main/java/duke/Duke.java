package duke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

import duke.task.command.Command;


/**
 * Class to handle all duke logic.
 */
public class Duke {

    // All UI functionality
    private final Ui ui;
    // All saved data related functionality
    private final Storage storage;
    // All user input related functionality
    private final Parser parser;
    // the list of items
    private TaskList items;

    private Undo undo;
    public static Stack<TaskList> state = new Stack<>();
    public static boolean isRunning;

    /**
     * Duke Constructor
     */
    public Duke(String directory, String file) throws DukeException {
        ui = new Ui();
        storage = new Storage(directory, file);
        isRunning = true;
        items = new TaskList(storage);
        parser = new Parser();
    }

    /**
     * Runs the duke chatbot.
     */
    public void run() {
        String rawInput;
        String output = "";
        ui.greet();
        while (isRunning) {
            rawInput = Ui.getInput();
            output = getResponse(rawInput);
        }
        Ui.printMessage(Ui.printGoodBye());
    }

    /**
     * Interacts with the user.
     */
    public String getResponse(String input) {
        assert input != null : "Command cannot be NULL";
        try {
            Command command = parser.compileInput(input);
            String output = command.execute();
            exitChat(output);

            assert !output.equals(""): "Unable to generate response. Please try again.";
            return output;
        } catch (Exception dukeException) {
            return dukeException.getMessage();
        }

    }

    /**
     * Returns true when duke is awake and false otherwise.
     *
     * @return True when duke is awake and false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

    private void exitChat(String message) throws DukeException {
        isRunning = !message.equals(Ui.printGoodBye());
        if (!isRunning()) {
            Storage.saveToFile();
        }
    }

    public static void addToState(TaskList taskList) {
        Duke.state.push(taskList);
    }

    public static TaskList getLatestState() {
        if (Duke.state.isEmpty()) {
            return new TaskList();
        }
        return Duke.state.peek();
    }

    public static void deleteLastState() {
        Duke.state.pop();
    }

    public static int stateSize() {
        return Duke.state.size();
    }

    /**
     * Starts the bot.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data", "duke.txt").run();
    }
}
