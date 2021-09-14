package duke.controller;

import java.io.IOException;

import duke.command.Command;
import duke.constant.Constants;
import duke.constant.MessageType;
import duke.controller.dialog.DukeDialogController;
import duke.controller.dialog.UserDialogController;
import duke.listener.Message;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.thread.CustomThreadPool;
import duke.util.Parser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Controller for DukeUi. Provides the layout for the other controls.
 */
public class DukeController extends AnchorPane implements Message {
    private static final String GREET = "Hello! I'm Duke.\nWhat can I do for you?";
    private static final int ONE_SECOND = 1000;

    private static final CustomThreadPool customThreadPool = new CustomThreadPool(
            Thread.NORM_PRIORITY
    );

    @FXML
    private ScrollPane chatPane;
    @FXML
    private Label comingTasks;
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
     * Passes Stage object to DukeController.
     *
     * @param stage Stage is created after FXMLLoader is loaded.
     */
    public void setStageListener(Stage stage) {
        if (stage == null) {
            return;
        }
        stage.setOnCloseRequest(event -> {
            exit();
        });
    }

    /**
     * Adds DukeDialog with content.
     *
     * @param messageType Message type to identify the content color.
     * @param content Content that will be shown in the dialog.
     */
    public void addDukeDialog(MessageType messageType, String content) {
        if (dialogContainer == null) {
            return;
        }
        try {
            Color color = messageType == MessageType.NORMAL ? Color.BLACK : Color.RED;
            // The reason of following approach is because the size of the dukeDialogController
            // will be returned only after it is added by the dialogContainer.
            DukeDialogController preDukeDialogController = new DukeDialogController(
                    content, color);
            preDukeDialogController.heightProperty()
                    .addListener(observable -> replaceDukeDialog(
                            content, color, preDukeDialogController));
            dialogContainer.getChildren().add(preDukeDialogController);
        } catch (IOException e) {
            System.out.println("Duke dialog fxml file not found: " + e.getMessage());
        }
    }

    private void replaceDukeDialog(
            String content, Color color, DukeDialogController preDukeDialogController) {
        try {
            DukeDialogController dukeDialogController = new DukeDialogController(
                    content, color, preDukeDialogController.getHeight());
            dialogContainer.getChildren().add(dukeDialogController);
        } catch (IOException e) {
            System.out.println("Duke dialog fxml file not found: "
                    + e.getMessage());
        } finally {
            dialogContainer.getChildren().remove(preDukeDialogController);
        }
    }

    /**
     * Adds UserDialog with content.
     *
     * @param content Content that will be shown in the dialog.
     */
    public void addUserDialog(String content) {
        if (dialogContainer == null) {
            return;
        }
        try {
            // The reason of following approach is because the size of the userDialogController
            // will be returned only after it is added by the dialogContainer.
            UserDialogController preUserDialogController = new UserDialogController(content);
            preUserDialogController.heightProperty()
                    .addListener(observable -> replaceUserDialog(content, preUserDialogController));
            dialogContainer.getChildren().add(preUserDialogController);
        } catch (IOException e) {
            System.out.println("User dialog fxml file not found: " + e.getMessage());
        }
    }

    private void replaceUserDialog(String content, UserDialogController preUserDialogController) {
        if (dialogContainer == null || preUserDialogController == null) {
            return;
        }
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
        addDukeDialog(MessageType.NORMAL, GREET);
        tfInput.setDisable(false);
        customThreadPool.execute(() -> {
            while (true) {
                handleComingTasks();
                try {
                    Thread.sleep(ONE_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void handleComingTasks() {
        Platform.runLater(() -> {
                int tasks = taskList.findComingTasks(Constants.COMING_TASK_HOUR_RANGE).length;
                comingTasks.setText(tasks + " coming "
                        + (taskList.getSize() <= 1 ? "task" : "tasks") + " in "
                        + Constants.COMING_TASK_HOUR_RANGE + " hours!");
            }
        );
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
        customThreadPool.release();
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void show(MessageType messageType, String... messages) {
        addDukeDialog(messageType, String.join("\n", messages));
    }
}
