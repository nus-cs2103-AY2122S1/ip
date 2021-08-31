package duke.main;

import duke.commands.Find;
import duke.data.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public class Duke {
    /** Storage component for Duke */
    private Storage storage;
    /** UI component for Duke */
    private Ui ui;
    /** TaskList component for Duke */
    private TaskList taskList;
    /** Parser component for Duke */
    private Parser parser;
    /** Find component for Duke */
    private Find finder;

    /**
     * Constructor for a duke.main.Duke class.
     */
    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList(storage.Load());
        this.parser = new Parser();
        this.finder = new Find();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
