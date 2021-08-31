package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A chatbot based on Project Duke
 *
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class Duke extends Application{

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    /**
     * A constructor to initialize a new chatbot.
     *
     * @param filePath Filepath of a saved chatbot or to save a chatbot
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printStringInErrorBox(e.getMessage());
            taskList = new TaskList();
        }
    }

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printStringInErrorBox(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Start a new chatbot session
     */
    private void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.getCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.printStringInErrorBox(e.getMessage());
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // more code to be added here later
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
