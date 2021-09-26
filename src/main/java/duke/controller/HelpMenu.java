package duke.controller;

import java.io.IOException;

import duke.Duke;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * The HelpMenu class is a controller class that represents a small menu of buttons consisting of
 * all commands supported by Duke. The user can click on each of these buttons to find out what
 * each command does, and the proper syntax required to use each command.
 */
public class HelpMenu extends VBox {
    @FXML
    private Button byeButton;
    @FXML
    private Button listButton;
    @FXML
    private Button todoButton;
    @FXML
    private Button deadlineButton;
    @FXML
    private Button eventButton;
    @FXML
    private Button doneButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button findButton;

    private Duke duke;

    private VBox dialogContainer;

    /**
     * Constructs a help menu GUI element.
     *
     * @param duke The object representing the Duke program.
     * @param dialogContainer The container containing all messages between the user and Duke.
     */
    public HelpMenu(Duke duke, VBox dialogContainer) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelpMenu.class.getResource("/view/HelpMenu.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.duke = duke;
        this.dialogContainer = dialogContainer;
    }

    @FXML
    private void handlePress(ActionEvent event) {
        Button button = (Button) event.getSource();
        String input = "help " + button.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response));
    }
}
