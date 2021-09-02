package duke.controller;

import java.io.IOException;

import duke.command.Command;
import duke.controller.dialog.DukeDialogController;
import duke.controller.dialog.UserDialogController;
import duke.listener.Message;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.util.Parser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for DukeUi. Provides the layout for the other controls.
 */
public class DukeController extends AnchorPane implements Message {
    public static final String GREET = "Hello! I'm Duke.\nWhat can I do for you?";

    @FXML
    private ScrollPane chatPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField tfInput;
    @FXML
    private ImageView ivSend;

    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a DukeController object.
     */
    public DukeController() {
        taskList = new TaskList();
        storage = new Storage(this);
    }

    /**
     * Adds DukeDialog with content.
     *
     * @param content Content.
     */
    public void addDukeDialog(String content) {
        if (dialogContainer == null) {
            return;
        }
        try {
            // The reason of following approach is because the size of the dukeDialogController
            // will be returned only after it is added by the dialogContainer.
            DukeDialogController preDukeDialogController = new DukeDialogController(content);
            preDukeDialogController.heightProperty()
                    .addListener(observable -> {
                        try {
                            DukeDialogController dukeDialogController = new DukeDialogController(
                                    content, preDukeDialogController.getHeight());
                            dialogContainer.getChildren().add(dukeDialogController);
                        } catch (IOException e) {
                            System.out.println("Duke dialog fxml file not found: "
                                    + e.getMessage());
                        } finally {
                            dialogContainer.getChildren().remove(preDukeDialogController);
                        }
                    });
            dialogContainer.getChildren().add(preDukeDialogController);
        } catch (IOException e) {
            System.out.println("Duke dialog fxml file not found: " + e.getMessage());
        }
    }

    /**
     * Adds UserDialog with content.
     *
     * @param content Content.
     */
    public void addUserDialog(String content) {
        if (dialogContainer == null) {
            return;
        }
        try {
            // The reason of following approach is because the size of the userDialogController
            // will be returned only after it is added by the dialogContainer.
            UserDialogController preUserDialogController = new UserDialogController(
                    content);
            preUserDialogController.heightProperty()
                    .addListener(observable -> {
                        try {
                            UserDialogController userDialogController = new UserDialogController(
                                    content, preUserDialogController.getHeight());
                            dialogContainer.getChildren().add(userDialogController);
                        } catch (IOException e) {
                            System.out.println("User dialog fxml file not found: "
                                    + e.getMessage());
                        } finally {
                            dialogContainer.getChildren().remove(preUserDialogController);
                        }
                    });
            dialogContainer.getChildren().add(preUserDialogController);
        } catch (IOException e) {
            System.out.println("User dialog fxml file not found: " + e.getMessage());
        }
    }

    /**
     * Initializes components.
     */
    @FXML
    public void initialize() {
        dialogContainer.heightProperty()
                .addListener(observable -> chatPane.setVvalue(chatPane.getVmax()));
        ivSend.setPickOnBounds(true);
        ivSend.setOnMouseClicked((MouseEvent e) -> {
            handleInput();
        });
        tfInput.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
                handleInput();
            }
        });
        init();
    }

    private void init() {
        storage.loadTasks(taskList);
        addDukeDialog(GREET);
        tfInput.setDisable(false);
    }

    private void handleInput() {
        String input = tfInput.getText();
        tfInput.clear();
        addUserDialog(input);
        Command c = Parser.parse(input, this);
        if (c == null) {
            return;
        }
        c.execute(taskList);
        if (c.isExit()) {
            exit();
        }
        storage.saveTasksToFile(taskList);
    }

    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void show(String... messages) {
        addDukeDialog(String.join("\n", messages));
    }
}
