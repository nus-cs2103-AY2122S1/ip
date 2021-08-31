package lania;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lania.command.Command;
import lania.exception.LaniaException;
import lania.task.TaskList;

import java.time.format.DateTimeParseException;

//@@author nguyiyang-reused
//Reused from https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications
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

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image laniaImage = new Image(this.getClass().getResourceAsStream("/images/Lania.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLania(TaskList tasks, Ui ui, Storage storage, Parser parser) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.parser = parser;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Lania's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        try {
            Command c = parser.parse(userInput.getText());
            String laniaText = c.execute(tasks, storage, ui);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLaniaDialog(laniaText, laniaImage)
            );
        } catch (LaniaException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLaniaDialog(ui.showLaniaException(e), laniaImage)
            );
        } catch (DateTimeParseException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getLaniaDialog(ui.showDateTimeException(), laniaImage)
            );
        }
        userInput.clear();
    }
}
//@@author
