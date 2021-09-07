package lania;

import java.time.format.DateTimeParseException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lania.command.Command;
import lania.command.ExitCommand;
import lania.exception.LaniaException;
import lania.task.TaskList;

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

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private Log log;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image laniaImage = new Image(this.getClass().getResourceAsStream("/images/Lania.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setLania(TaskList tasks, Ui ui, Storage storage, Parser parser, Log log) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
        this.parser = parser;
        this.log = log;
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
            String laniaText = c.execute(tasks, storage, ui, log);
            if (c instanceof ExitCommand) {
                Platform.exit();
            }
            generateDialog(input, laniaText, userImage, laniaImage);
        } catch (LaniaException e) {
            generateDialog(input, ui.showLaniaException(e), userImage, laniaImage);
        } catch (DateTimeParseException e) {
            generateDialog(input, ui.showDateTimeException(), userImage, laniaImage);
        }
        userInput.clear();
    }

    private void generateDialog(String input, String reply, Image userImage, Image laniaImage) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLaniaDialog(reply, laniaImage)
        );
    }
}
//@@author
