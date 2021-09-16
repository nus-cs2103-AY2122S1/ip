package duke.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import duke.Duke;
import duke.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    private Stage stage;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/winnie.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/baymax.jpg"));

    /**
     * Constructs a MainWindow object
     *
     * @param stage stage
     */
    public MainWindow(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        this.stage = stage;
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Shows list of tasks and welcomes user
     */
    public void start() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getUi().showTaskList(duke.getTaskList(), "past"), dukeImage),
                DialogBox.getDukeDialog(duke.getUi().showWelcome(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        {
            if (input.equals("help")) {
                help();
            }
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
            if (input.equals("bye")) {
                exit();
            }
        }
    }

    private void help() {
        userInput.clear();
        Scene prev = this.getScene();
        HelpPage helpPage = new HelpPage(stage, prev);
        helpPage.start();
    }

    private void exit() {
        Timer timer = new Timer();
        TimerTask exit = new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        };
        userInput.setDisable(true);
        sendButton.setDisable(true);
        timer.schedule(exit, new Date(System.currentTimeMillis() + 3 * 1000));
    }
}
