package duke.ui;

import duke.command.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Ui {
    private static final String WINDOW_TITLE = "DukePro";

    private static final String MESSAGE_INTRO = "\nHello! I'm Duke\nWhat can I do for you?";
    private static final String MESSAGE_OUTRO = "Bye. Come back soon!";
    private static final String MESSAGE_LINE = "\n_____________________________________\n";

    private static final int APP_HEIGHT = 700;
    private static final int APP_WIDTH = 500;
    private static final Color APP_BACKGROUND_COLOR = Color.WHITE;
    private static final Pos APP_DUKE_TEXT_ALIGNMENT = Pos.TOP_LEFT;
    private static final boolean APP_WRAP_TEXT = true;

    private final Storage storage;
    private final TaskList tasks;

    private final Stage stage;
    private final ScrollPane scrollPane;
    private final VBox vBox;
    private final TextField textField;

    private final StringBuilder chat;
    private final Label label;

    // TextField onAction Event
    private final EventHandler<ActionEvent> event = new EventHandler<>() {
        public void handle(ActionEvent e) {
            Ui ui = Ui.this;
            String fullCommand = textField.getText();

            displayMessage(fullCommand);

            try {
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
            } catch (DukeException dukeException) {
                ui.showError(dukeException.getMessage());
            } finally {
                ui.showLine();
            }

            // Clear TextField
            textField.clear();
        }
    };

    public Ui(Stage stage, Storage storage, TaskList tasks) {
        this.stage = stage;
        this.storage = storage;
        this.tasks = tasks;

        this.chat = new StringBuilder();
        this.label = new Label();
        label.setWrapText(APP_WRAP_TEXT);

        this.scrollPane = new ScrollPane();

        this.vBox = new VBox();
        this.vBox.setAlignment(APP_DUKE_TEXT_ALIGNMENT);

        this.textField = new TextField();
        textField.setOnAction(event);

        vBox.getChildren().addAll(textField, label);
        vBox.setMinSize(APP_WIDTH, APP_HEIGHT);

        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setContent(vBox);
    }

    private void displayMessage(String message) {
        chat.append(message);
        label.setText(chat.toString());
    }

    /**
     * Initialize the UI of the application.
     */
    public void init() {
        // Initial setup
        stage.setTitle(WINDOW_TITLE);
        stage.setResizable(false);

        Scene scene = new Scene(scrollPane, APP_WIDTH, APP_HEIGHT, APP_BACKGROUND_COLOR);
        stage.setScene(scene);
        stage.show();

        showWelcome();
    }

    public void showWelcome() {
        displayMessage(MESSAGE_INTRO);
        showLine();
    }

    public void showGoodbye() {
        displayMessage(MESSAGE_OUTRO);
        stage.close();
    }

    public void print(String message) {
        displayMessage(message);
    }

    public void showLine() {
        displayMessage(MESSAGE_LINE);
    }

    public void showError(String message) {
        displayMessage(message);
    }
}
