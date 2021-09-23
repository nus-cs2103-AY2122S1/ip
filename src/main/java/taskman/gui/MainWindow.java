package taskman.gui;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import taskman.TaskMan;

/**
 * Main Window
 */
// Solution adapted from https://se-education.org/guides/tutorials/javaFx.html
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
    private BorderPane borderPane;
    @FXML
    private AnchorPane anchorPane;

    private TaskMan taskMan;

    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));


    /**
     * Settiing the TaskMan within main window
     *
     * @param taskMan TaskMan object
     */
    public void setDuke(TaskMan taskMan) {
        this.taskMan = taskMan;
    }

    /**
     * Set the auto scroll functionality
     */
    public void setScrollPane () {
        dialogContainer.heightProperty()
                .addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.autosize();
        userInput.setPromptText("Say Something to TaskMan");
    }

    public void setTaskManSize() {
        borderPane.prefHeightProperty().bind(anchorPane.heightProperty());
        borderPane.prefWidthProperty().bind(anchorPane.widthProperty());
        dialogContainer.prefHeightProperty().bind(scrollPane.heightProperty());
        dialogContainer.prefWidthProperty().bind(scrollPane.widthProperty());
    }

    /**
     * Startup message
     */
    @FXML
    public void initialize() {
        String welcomeMessage = "Hello! I am TaskMan.\nWhat can I do for you?";
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Handles when user either press enter or enter bay
     *
     * @throws InterruptedException
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String userStringInput = this.userInput.getText();
        String dukeStringResponse = taskMan.guiProcess(userStringInput);
        addUserDukeResponseIntoContainer(userStringInput, dukeStringResponse);
        checkExit();
    }

    /**
     * Add user and taskMan response into dialog container
     *
     * @param userStringInput user input
     * @param dukeStringInput  taskMan response to user input
     * @throws InterruptedException exception when error with delay
     */
    private void addUserDukeResponseIntoContainer(String userStringInput,
                                                  String dukeStringInput) throws InterruptedException {
        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(userStringInput, userImage));
        Thread.sleep(200);
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeStringInput, dukeImage));
        this.userInput.clear();
    }

    /**
     * Handles when User pressed the + button
     *
     * @throws InterruptedException
     */
    @FXML
    private void handleAddEvent() throws InterruptedException {
        String userStringInput = popupDialogAddNewTask();
        String dukeStringResponse = taskMan.guiProcess(userStringInput);
        addUserDukeResponseIntoContainer(userStringInput, dukeStringResponse);
    }

    /**
     * Creates a popup to add a new task
     *
     * @return String containing task details from the popup
     */
    private String popupDialogAddNewTask() {
        Dialog<String> dialog = new Dialog<>();
        setupDialogTaskTypeDescription(dialog);
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            return result.get();
        }
        return "";
    }

    /**
     * Settings of the popup to add new task
     *
     * @param dialog user input
     */
    private void setupDialogTaskTypeDescription (Dialog dialog) {
        dialog.setTitle("Adding New Event");
        dialog.setHeaderText("Please enter details of new task \n"
                + "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);
        Label taskTypeLabel = new Label("TaskType: ");
        Label descriptionLabel = new Label("Description: ");
        ComboBox taskTypeDropDownOption = new ComboBox();
        taskTypeDropDownOption.getItems().addAll("event", "deadline", "todo");
        TextField descriptionText = new TextField();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(taskTypeLabel, 1, 1);
        grid.add(taskTypeDropDownOption, 2, 1);
        grid.add(descriptionLabel, 1, 2);
        grid.add(descriptionText, 2, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Add Task", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.setResultConverter(dialogButtonPressTaskTypeDescription(buttonTypeOk,
                taskTypeDropDownOption, descriptionText));
    }

    /**
     * Creates a popup to get additional date and time details for new task
     *
     * @param taskType type of task
     * @return String containing task details from the popup
     */
    private String popupDialogDateTime(String taskType) {

        Dialog<String> dialog = new Dialog<>();
        setupDialogDateTime(dialog, taskType);
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            return result.get();
        }
        return "";

    }

    /**
     * Settings of the popup to get additional date and time task details
     *
     * @param dialog
     * @param taskType
     */
    private void setupDialogDateTime(Dialog dialog, String taskType) {
        dialog.setTitle("Add time");
        dialog.setHeaderText("This is a custom dialog. Enter info and \n"
                + "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);
        TextField date = new TextField();
        TextField timee = new TextField();
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setHgap(10);
        grid.setVgap(10);
        Label label1 = new Label("Date: ");
        Label label2 = new Label("YYYY-MM-DD format");
        Label label3 = new Label("Time: ");
        Label label4 = new Label("24 hour format");
        grid.add(label1, 1, 1);
        grid.add(date, 2, 1);
        grid.add(label2, 3, 1);
        grid.add(label3, 1, 2);
        grid.add(timee, 2, 2);
        grid.add(label4, 3, 2);
        dialog.getDialogPane().setContent(grid);
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.setResultConverter(
                dialogButtonPressDateTime(buttonTypeOk, date, timee , taskType)
        );
    }

    /**
     * Allow dialog to return the task details inside the dialog box
     *
     * @param buttonTypeOk button
     * @param taskType task type to set
     * @param description task description to set
     * @return
     */
    //Solution below adapted from https://examples.javacodegeeks
    // .com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
    public Callback<ButtonType, String> dialogButtonPressTaskTypeDescription(
            ButtonType buttonTypeOk,
            ComboBox taskType, TextField description) {
        return new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType b) {
                if (b == buttonTypeOk) {
                    boolean ifNeedDate = checkIfNeedDate((String) taskType.getValue());
                    if (ifNeedDate) {
                        String dateTime = popupDialogDateTime((String) taskType.getValue());
                        return taskType.getValue() + " " + description.getText() + dateTime;
                    }
                    return taskType.getValue() + " " + description.getText();
                }
                return "";
            }
        };

    }

    /**
     * Allow dialog to return the date and time details inside the dialog box
     *
     * @param buttonTypeOk button
     * @param date date to set
     * @param time time to set
     * @param taskType task type
     * @return
     */
    //Solution below adapted from
    // https://examples.javacodegeeks.com/desktop-java/javafx/dialog-javafx/javafx-dialog-example/
    public Callback<ButtonType, String> dialogButtonPressDateTime(
            ButtonType buttonTypeOk,
            TextField date, TextField time, String taskType) {
        return new Callback<ButtonType, String>() {
            @Override
            public String call(ButtonType b) {
                if (b == buttonTypeOk && taskType.equals("event")) {
                    return " /at " + date.getText() + " " + time.getText();
                }
                if (b == buttonTypeOk && taskType.equals("deadline")) {
                    return " /by " + date.getText() + " " + time.getText();
                }
                return "";
            }
        };
    }

    private boolean checkIfNeedDate (String taskType) {
        return (taskType.equals("event") || taskType.equals("deadline"));
    }

    /**
     * Check whether to continue taskMan operations
     */
    private void checkExit() {
        if (taskMan.shouldAbort()) {
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(Platform::exit);
        }
    }


    /**
     * Handles when tasklist button pressed
     */
    @FXML
    private void handleGetList() throws InterruptedException {
        String getList = "list";
        String response = taskMan.guiProcess(getList);
        HBox list = DialogBox.getDukeDialog(response, dukeImage);
        list.setMinWidth(scrollPane.getWidth());
        addUserDukeResponseIntoContainer(getList, response);
    }

    /**
     * Handles when help button pressed
     */
    @FXML
    private void handleGetHelp() throws InterruptedException {
        String getHelp = "help";
        String response = taskMan.guiProcess(getHelp);
        addUserDukeResponseIntoContainer(getHelp, response);
    }


}
