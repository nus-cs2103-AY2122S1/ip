package duke;

import java.util.ArrayList;

import duke.command.Command;

/**
 * Duke class that starts and runs the Duke bot.
 */
public class Duke {

    static final String START_MESSAGE = "Hello! I'm Duke\n" + "What can I do for you?";
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs the Duke object.
     */
    public Duke() {
        storage = new Storage("data/duke.txt");
        try {
            taskList = new TaskList(storage.getFile());
        } catch (DukeException e) {
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public String start() {
        return START_MESSAGE;
    }

    /**
     * Gets the response from the Duke bot for the input.
     *
     * @param input String input message.
     * @return Response string.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(taskList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
