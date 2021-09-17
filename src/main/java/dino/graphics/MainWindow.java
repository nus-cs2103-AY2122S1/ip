package dino.graphics;

import java.io.IOException;

import dino.Dino;
import dino.user.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;



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
    @FXML
    private Button helpButton;
    @FXML
    private Popup helpPopup;


    private Dino dino;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Lunch-min.jpg"));
    private Image dinoImage = new Image(this.getClass().getResourceAsStream("/images/Dinner.jpg"));

    /**
     * Runs when the MainWindow is initialized.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendIntroMessage();

    }

    public void setDino(Dino d) {
        dino = d;
    }

    @FXML
    private void sendIntroMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDinoDialog("Hello! I'm Dino!\nYour friendly task manager!", dinoImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dino's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = dino.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDinoDialog(response, dinoImage)
        );

        // @@author CheyanneSim-reused
        if (response.equals(Ui.displayByeMessage())) {
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();

        }
        userInput.clear();
    }

    @FXML
    private void openHelpPopup() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/HelpWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Command List");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
