package duke;

import tasklist.TaskList;
import ui.Ui;

/**
 * The Duke app implements tracks tasks that the user requires to be tracked.
 *
 * @author  Hoon Darren
 * @version 1.0
 * @since   2021-08-21
 */
public class Duke {
    private TaskList engineGui = new TaskList();

    /**
     * Initialises app.
     *
     * @param args empty args.
     */
    public static void main(String[] args) {

        Ui messages = new Ui();
        TaskList engine = new TaskList();

        messages.welcomeMessage();
        engine.runProgram();
        messages.goodbyeMessage();
    }

    /**
     * Returns the string based on a user's input.
     *
     * @param input user input string in the application.
     * @return string response based on what the user input.
     */
    public String getResponse(String input) {
        assert(input != null);
        return engineGui.readGuiInput(input);
    }
}
