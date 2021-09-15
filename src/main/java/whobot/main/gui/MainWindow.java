package whobot.main.gui;

import java.io.File;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import whobot.main.UI;
import whobot.main.WhoBotException;
import whobot.utils.Parser;
import whobot.utils.Storage;
import whobot.utils.TaskList;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    /** UI to output to */
    private UI ui;

    /** Storage to store Task list in */
    private Storage storage;

    /** Parser to parse user input */
    private Parser parser;

    /** Task List to store all tasks */
    private TaskList taskList;

    /***
     * Initializes the Main Window
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        DisplayBuffer.setParent(dialogContainer);
        DisplayBuffer.setUserInput(userInput);
        DisplayBuffer.setSendButton(sendButton);
        this.parser = new Parser();
        this.ui = new UI();
        try {
            String filename = "." + File.separator + "data" + File.separator + "WhoBotData.txt";
            this.storage = new Storage(filename);
            this.taskList = new TaskList(storage);
        } catch (WhoBotException ex) {
            ui.echo(ex.getMessage(), UI.Type.ERROR);
            System.exit(0);
        }

        scrollPane.setOnMouseEntered(e -> {
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        });
        scrollPane.setOnMouseExited(e -> {
            scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        });

        ui.echo("Hello! I'm the WhoBot.", UI.Type.START);
        ui.echo("What can I do for you?", UI.Type.END);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String command = userInput.getText();
        final Node node = UserDialogBox.getDialog(command);
        FadeTransition transition = new FadeTransition(Duration.millis(400), node);
        transition.setFromValue(0);
        transition.setToValue(1);
        dialogContainer.getChildren().add(node);
        transition.play();
        try {
            if (parser.parse(command, ui, storage, taskList) == -1) {
                String message = "GoodBye, I hope to see you again soon :)";
                long delay = message.length() * 50 + 10500;
                ui.echo("GoodBye, I hope to see you again soon :)", UI.Type.COMPLETE);
                Thread delayThread = new Thread(() -> {
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                });
                delayThread.start();
            }
        } catch (WhoBotException e) {
            ui.echo(e.getMessage(), UI.Type.ERROR);
        }
        userInput.clear();
    }

}
