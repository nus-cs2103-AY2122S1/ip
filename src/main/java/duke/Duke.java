package duke;

import java.io.IOException;

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
    private Items items;
    // checking if user still wants to input
    public static boolean isRunning;

    /**
     * Duke Constructor
     */
    public Duke(String directory, String file) {
        ui = new Ui(directory, file);
        storage = new Storage(directory, file);
        parser = new Parser();
        isRunning = true;
        try {
            items = new Items(storage.loadData());
        } catch (DukeException e) {
            items = new Items();
        }
    }

    /**
     * Runs the duke chatbot.
     *
     * @throws DukeException in case any unexpected input is passed.
     * @throws IOException in case of file issues.
     */
    public void run() throws DukeException, IOException {
        ui.greet();
        while (isRunning) {
            ui.getResponse(ui.getInput());
        }
        ui.printGoodBye();
    }

    /**
     * interacts with the user.
     *
     * @param input input entered by the user.
     * @return output based on the input entered by the user.
     */
    public String getResponse(String input) {
        return ui.getResponse(input);
    }

    /**
     * Returns true when duke is awake and false otherwise.
     * @return True when duke is awake and false otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * The main function of Bhutu.
     *
     * @param args The command line arguments
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("./data", "duke.txt").run();
    }
}
