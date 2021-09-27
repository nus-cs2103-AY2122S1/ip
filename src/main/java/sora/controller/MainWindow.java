package sora.controller;

import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sora.Sora;
import sora.util.Message;

/**
 * Controller for Main Window.
 * Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image soraImage = new Image(this.getClass().getResourceAsStream("/images/Sora.jpg"));
    @FXML
    private HBox titleBar;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;
    private Sora sora;
    private Stage stage;
    private double x, y;

    /** Initializes components of the Main Window. */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getSoraDialog(Message.WELCOME.toString(), soraImage));

        titleBar.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        titleBar.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
    }

    public void setData(Sora sora, Stage stage) {
        this.sora = sora;
        this.stage = stage;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Sora's reply and then appends
     * them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        // Obtain user input and clear the input
        String input = userInput.getText();
        userInput.clear();

        // Shutdown upon receiving exit message
        if (input.equals("bye")) {
            close();
        }

        // Obtain and display response to user input
        String response = sora.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSoraDialog(response, soraImage)
        );
    }

    @FXML
    private void btnClose() {
        // Obtain and display exit message
        String response = sora.getResponse("bye");
        dialogContainer.getChildren().add(DialogBox.getSoraDialog(response, soraImage));

        // Remove any input
        userInput.clear();

        close();
    }

    private void close() {
        // Disable all mode of input
        userInput.setDisable(true);
        sendButton.setDisable(true);

        // Display the exit message and then exit after 1s
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1500);
    }

    @FXML
    private void btnMinimize() {
        stage.setIconified(true);
    }
}
