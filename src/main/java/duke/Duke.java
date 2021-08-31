package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.gui.Main;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import javafx.application.Application;

/**
 * Represents a chat bot called Duke.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Duke {
    /** An instance of a the Storage class that saves and loads Duke's data. */
    private Storage storage;

    /** The taskList where all tasks are stored. */
    private TaskList taskList;

    /** An instance of the Ui class that is responsible for Duke's user interactions. */
    private final Ui ui;

    /**
     * Constructor for the Duke class.
     */
    public Duke(String filepath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filepath);
            this.taskList = new TaskList(this.storage.load());
        } catch (IOException exception) {
            System.out.println(ui.showLoadingError());
        } catch (DukeException exception) {
            System.out.println(ui.showError(exception.getMessage()));
        }
    }

    /**
     * Returns Duke's response to a command as a string.
     *
     * @param userInput The command that the user typed to Duke.
     * @return A string representing Duke's reply after executing a command.
     */
    public String getResponse(String userInput) {
        Command c = Parser.parse(userInput);
        return c.execute(this.taskList, this.ui, this.storage);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
