/**
 * This function simulates a chat bot who interacts with a user to keep track of a todo list.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */

package duke;

import java.io.IOException;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;

import duke.command.CommandExecutor;
import duke.exceptions.DukeExceptions;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke extends AnchorPane {

    private final CommandExecutor commandExecutor;

    /**
     * Constructs a Duke object.
     *
     * @throws IOException If the file saved fails to load.
     */
    public Duke() throws IOException {
        Ui ui = new Ui(); // Performs the self introduction upon successful initialization.
        Storage storage = new Storage("data/duke.txt");
        TaskList taskList = new TaskList(storage.load());
        this.commandExecutor = new CommandExecutor(storage, ui, taskList);
    }

    /**
     * Generates output and performs actions based on the input provided by the user.
     *
     * @param input The user input received.
     * @return Output corresponding to the input received.
     */
    public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                commandExecutor.execute(input);
                Platform.exit();
            }
            return commandExecutor.execute(input);
        } catch (DukeExceptions | IOException exception) {
            return "Ohno! You were so exceptional, an error has occurred!\n" + exception.getMessage();
        }
    }
}
