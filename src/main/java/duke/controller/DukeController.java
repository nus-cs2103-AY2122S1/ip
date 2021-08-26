package duke.controller;

import java.io.IOException;

import duke.controller.dialog.DukeDialogController;
import duke.controller.dialog.UserDialogController;
import duke.listener.Message;
import duke.storage.Storage;
import duke.task.Operation;
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

    private final Storage storage;

    /**
     * Constructs a DukeController object.
     */
    public DukeController() {
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
                        e.printStackTrace();
                    } finally {
                        dialogContainer.getChildren().remove(preDukeDialogController);
                    }
                });
            dialogContainer.getChildren().add(preDukeDialogController);
        } catch (IOException e) {
            e.fillInStackTrace();
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
                        e.printStackTrace();
                    } finally {
                        dialogContainer.getChildren().remove(preUserDialogController);
                    }
                });
            dialogContainer.getChildren().add(preUserDialogController);
        } catch (IOException e) {
            e.fillInStackTrace();
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
        storage.loadTasks();
        addDukeDialog(GREET);
        tfInput.setDisable(false);
    }

    private void handleInput() {
        String input = tfInput.getText();
        addUserDialog(input);
        Operation operation = storage.getOperation(input);
        if (operation == Operation.BYE) {
            exit();
        } else if (operation == Operation.LIST) {
            storage.listTasks();
        } else if (operation == Operation.DONE) {
            storage.completeTask(input);
        } else if (operation == Operation.DELETE) {
            storage.deleteTask(input);
        } else if (operation == Operation.CLEAR) {
            storage.clearTasks();
        } else if (operation == Operation.FIND) {
            storage.findTasks(input);
        } else if (operation == Operation.TODO
            || operation == Operation.DEADLINE
            || operation == Operation.EVENT) {
            storage.addTask(input);
        }
        storage.saveTasksToFile();
        tfInput.clear();
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
