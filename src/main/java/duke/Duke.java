package duke;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.format.DateTimeFormatter;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a runner for the Duke program.
 */
public class Duke {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private UserInterface ui;
    private DateTimeFormatter dtformatter;
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor.
     */
    public Duke() {
        ui = new UserInterface();
        dtformatter = DateTimeFormatter.ISO_DATE;
        String home = System.getProperty("user.home");
        Path dukePath = Paths.get(home, "Documents", "duke", "data.csv");
        storage = new Storage(dukePath);
        taskList = storage.load(ui);
    }

    /**
     * Runs this Duke program.
     */
    public void run() {
        ui.displayGreeting();
        Command command;
        do {
            command = Parser.parse(ui.getResponse());
            command.execute(taskList, ui);
        } while(command.shouldExecuteNext());
        storage.save(ui, taskList);
    }
}
