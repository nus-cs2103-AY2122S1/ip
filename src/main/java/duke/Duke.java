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

    public String getResponse(String input) throws DukeException {
        return Parser.parse(input, taskList, this);
    }

    public void save(TaskList taskList) {
        storage.write(taskList);
    }

    public String greet() {
        return ui.greet();
    }
}
