package duke;

import java.util.ArrayList;

/**
 * Represents a chat bot.
 *
 * @author Willy Angga Prawira.
 */
public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * A constructor to create a Duke object.
     *
     * @param filepath Location of the file that keeps track of the list.
     */
    public Duke(String filepath) {
        taskList = new TaskList(new ArrayList<Task>());
        storage = new Storage(filepath);
        ui = new Ui();
    }

    /**
     * A constructor to create a Duke object.
     */
    public Duke() {
        taskList = new TaskList(new ArrayList<Task>());
        storage = new Storage("./duke.txt");
        ui = new Ui();
        storage.readFile(taskList);
    }

    /**
     * Receives an input from the user and outputs the appropriate response.
     */
    public String getResponse(String input) {
        String parsed = new Parser(taskList, ui).parse(input);
        storage.saveFile(taskList);
        return "Duke says: " + parsed;
    }
}
