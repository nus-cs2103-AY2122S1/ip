package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a chat-bot that stores list of tasks/events that the user
 * can add/delete/complete.
 */
public class Duke {
    private static TaskList taskList;
    private static Storage storage;
    private static Ui ui;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        try {
            taskList = storage.load();
        } catch (IOException e) {
            e.printStackTrace();
            taskList = new TaskList(new ArrayList<>());
        }
    }

    /**
     * Gets the response from the bot after parsing the user's input.
     *
     * @param userInput the user's input to be parsed.
     * @return returns the response from the bot after parsing the input.
     * @throws DukeException when there's an invalid input from the user.
     */
    public String getResponse(String userInput) throws DukeException {
        return Parser.parse(userInput, taskList, this);
    }

    public void save(TaskList taskList) {
        storage.write(taskList);
    }

    public String greet() {
        return ui.greet();
    }
}
