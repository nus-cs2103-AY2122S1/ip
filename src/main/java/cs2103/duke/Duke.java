package cs2103.duke;

import java.io.IOException;

/**
 * This class encapsulates a Duke chat-bot.
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        Storage storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            System.out.println(ui.showLoadingError());
            tasks = new TaskList();
        }
        ui = new Ui(storage, tasks);
    }

    /**
     * Generates a response given an input from user.
     *
     * @param input The user input received.
     * @return A string representing the response to the user.
     */
    public String getResponse(String input) {
        assert input != null;
        String result;
        try {
            return ui.handleInput(input, tasks);
        } catch (DukeException | IOException e) {
            e.printStackTrace();
            result = e.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        DukeLauncher.main(null);
    }
}