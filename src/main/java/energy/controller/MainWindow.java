package energy.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import energy.Energy;


/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends Stage {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Energy energy;

    private final Image userImage =
            new Image(this.getClass().getResourceAsStream("/images/TakeNRG.png"));
    private final Image energyImage =
            new Image(this.getClass().getResourceAsStream("/images/GivePLZ.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setEnergy(Energy energy) {
        this.energy = energy;
        dialogContainer.getChildren().addAll(
                DialogBox.getEnergyDialog(energy.getInitMessage(), energyImage, energy.hasException())
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Energy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = energy.getResponse(input);
        // If a terminating command has been executed (e.g. ByeCommand)
        if (energy.isTerminated()) {
            Platform.exit();
            System.exit(0);
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEnergyDialog(response, energyImage, energy.hasException())
        );
        userInput.clear();
    }
}
