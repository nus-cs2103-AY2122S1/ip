package duke;

import gui.DukeGui;
import javafx.application.Application;

import java.nio.file.Path;

/**
 * Main class of Duke chatbot. Contains the main method that initialises Duke
 * and starts the chatbot.
 */
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    private Parser parser;


    /**
     * Constructor for Duke.
     *
     * @param filepath The filepath of the save file.
     */
    public Duke(Path filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        try {
            tasklist = new TaskList(storage.load());
        } catch (DukeException e) {
            tasklist = new TaskList();
        }
        parser = new Parser(ui, storage, tasklist);
    }

    /**
     * Reads the input from the GUI and returns the output.
     *
     * @param input The user input.
     * @return The response string.
     */
    public String readInput(String input) {
        return parser.process(input);
    }

    public static void main(String[] args) {
        Application.launch(DukeGui.class,  args);
    }
}
