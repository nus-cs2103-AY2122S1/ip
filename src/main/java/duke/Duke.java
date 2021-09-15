package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

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
    public static LinkedList<TaskList> state;

    /**
     * Duke Constructor
     */
    public Duke(String directory, String file) throws DukeException {
        ui = new Ui();
        storage = new Storage(directory, file);
        DukeConstants.isRunning = true;
        items = new TaskList(storage);
        parser = new Parser(this.items);
        undo = new Undo(this.items);
        state = new LinkedList<>();
    }

    /**
     * Runs the duke chatbot.
     */
    public void run() {
        String rawInput;
        String output = "";
        ui.greet();
        while (DukeConstants.isRunning) {
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
        return DukeConstants.isRunning;
    }

    private void exitChat(String message) {
        DukeConstants.isRunning = !message.equals(Ui.printGoodBye());
    }

    public static void addToState(TaskList taskList) {
        state.add(taskList);
    }

    public static TaskList getLastestState() {
        int lastIndex = state.size() - 1;
        return state.get(lastIndex);
    }

    /**
     * The main function of Bhutu.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) throws DukeException {
        new Duke("./data", "duke.txt").run();
    }
}
