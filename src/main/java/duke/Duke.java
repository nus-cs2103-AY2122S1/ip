package duke;

import duke.command.Command;

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
        try {
            // Get stored data.
            taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            Ui.showMessage(e.getMessage());
            taskList = new TaskList(storage);
        }
        parser = new Parser(taskList);
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

        try {
            // Parse user input and execute the command
            Command command = parser.parseInput(input);
            String output = command.execute();

            quitIfBye(output);
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

    private void quitIfBye(String message) {
        isRunning = !message.equals(Ui.getGoodByeMessage());
    }
}
