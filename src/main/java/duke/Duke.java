/**
 * This function simulates a chat bot who interacts with a user to keep track of a todo list.
 *
 * @author Megan Wee Rui En
 * @version CS2103T AY21/22 Semester 1
 */
package duke;

import java.util.Scanner;
import java.io.IOException;

import duke.command.CommandExecutor;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import duke.command.Command;
import duke.command.CommandExecutor;
import duke.exceptions.CommandDoesNotExist;
import duke.exceptions.DukeExceptions;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke extends AnchorPane {

    private final Storage storage;
    private final TaskList taskList;
    private final Ui ui;
    private final CommandExecutor commandExecutor;

    /**
     * Constructs a Duke object.
     *
     * @throws IOException
     */
    public Duke() throws IOException {
        this.ui = new Ui(); // Performs the self introduction upon successful initialization.
        this.storage = new Storage("data/duke.txt"); //TODO
        this.taskList = new TaskList(storage.load());
        this.commandExecutor = new CommandExecutor(storage, ui, taskList);
    }

    public String getResponse(String input) throws IOException, DukeExceptions {
        return commandExecutor.execute(input);
    }

}
