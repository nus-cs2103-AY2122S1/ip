package duke;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the initialization of Duke application.
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    // JavaFX GUI Elements
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Fields for chatbot functionality
    public static final String FILEPATH = "data/tasks.txt";
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * @param filePath to read/write task list data
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public Duke() {
        ui = new Ui();
        storage = new Storage(FILEPATH);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (IOException e) {
            System.out.println("Critical failure while reading io");
        }
    }

    public String getResponse(String input) {
        try {
            //String fullCommand = ui.readCommand();
            ui.showLine(); // show the divider line ("_______")
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
            return c.getCommandOutput();
        } catch (DukeException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return "Please enter the date and time "
                    + "in dd/mm/yyyy hh:mma format!";
        }
    }
}
