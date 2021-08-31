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
    private Storage storage;
    private TaskList taskList;
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
            ui.showLoadingError();
        } catch (DukeException exception) {
            ui.showError(exception.getMessage());
        }
    }

    /**
     * Runs Duke.
     * Duke starts reading inputs from the user and executes the user's commands accordingly.
     */
    public String getResponse(String userInput) {
        Command c = Parser.parse(userInput);
        return c.execute(this.taskList, this.ui, this.storage);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
