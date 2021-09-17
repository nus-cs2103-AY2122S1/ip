package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Ui {
    public static final String USER_IMAGE_FILEPATH = "/images/DaUser.png";
    public static final String DUKE_IMAGE_FILEPATH = "/images/DaDuke.png";
    public static final String APP_NAME = "DukeMaster";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user;
    private Image duke;

    /**
     * Constructor for UI.
     * @param stage FX Stage for UI.
     */
    public Ui(Stage stage) {
        AnchorPane mainLayout = createComponents();
        createScene(mainLayout);
        displayStage(stage);
        setProperties(stage, mainLayout);
    }

    private AnchorPane createComponents() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");

        user = new Image(this.getClass().getResourceAsStream(USER_IMAGE_FILEPATH));
        duke = new Image(this.getClass().getResourceAsStream(DUKE_IMAGE_FILEPATH));

        scrollPane.setContent(dialogContainer);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        return mainLayout;
    }

    private void createScene(AnchorPane mainLayout) {
        scene = new Scene(mainLayout);
    }

    private void displayStage(Stage stage) {
        stage.setScene(scene);
        stage.show();
    }

    private void setProperties(Stage stage, AnchorPane mainLayout) {
        stage.setTitle(APP_NAME);
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(10);
        dialogContainer.setPadding(new Insets(10, 10, 10, 10));
        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void showResponse(boolean isError, String response) {
        Label dukeText = new Label(response);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke), isError)
        );
    }

    private void showResponse(boolean isError, String... response) {
        Label dukeText = new Label(String.join("\n", response));
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke), isError)
        );
    }

    /**
     * Displays the user's command in the UI.
     * @param command The command to display.
     */
    public void showFullCommand(String command) {
        Label userText = new Label(command);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );
    }

    /**
     * Displays that undo command is successfully completed.
     * @param lastHistoryState Previous History State.
     */
    public void showUndone(HistoryState lastHistoryState) {
        Command previousCommand = lastHistoryState.getCommand();
        TaskList previousTaskList = lastHistoryState.getTaskList();
        showResponse(false, "Successfully undone previous command: " + previousCommand);
        showResponse(false, "Task List after undoing: ");
        showTaskList(previousTaskList);
    }

    /**
     * Clears user's input box.
     */
    public void clearInput() {
        userInput.clear();
        assert readCommand().equals("") : "Should have cleared input field";
    }

    public Button getSendButton() {
        return sendButton;
    }

    public TextField getUserInput() {
        return userInput;
    }

    public String readCommand() {
        return userInput.getText();
    }

    public void showLoadingError(String errorMessage) {
        showResponse(false, "Error loading data file: " + errorMessage, "Starting with empty TaskList.");
    }

    public void showError(String errorMessage) {
        showResponse(true, "Oops! " + errorMessage);
    }

    public void showWelcome() {
        showResponse(false, "Welcome to " + APP_NAME, "What can I do for you?");
    }

    /**
     * Displays new task added.
     * @param task Task added.
     * @param tasks TaskList after addition of task.
     */
    public void showAddedNewTask(Task task, TaskList tasks) {
        assert tasks.doesContain(task) : "Task should be in the TaskList";
        showResponse(false, "Got it. I've added this task:", task.toString(), numberOfTasksToString(tasks));
    }

    /**
     * Displays task removed.
     * @param task Task removed.
     * @param tasks TaskList after removal of task.
     */
    public void showRemovedTask(Task task, TaskList tasks) {
        assert !tasks.doesContain(task) : "Task should have been removed from the TaskList";
        showResponse(false, "Noted. I've removed this task:", task.toString(), numberOfTasksToString(tasks));
    }

    /**
     * Displays task marked as done.
     * @param task Task marked as done.
     */
    public void showMarkedAsDone(Task task) {
        assert task.getDoneStatus() : "Task should have been done";
        showResponse(false, "Nice! I've marked this task as done:", task.toString());
    }

    /**
     * Displays task list.
     * @param tasks Task list to display.
     */
    public void showTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.getLength(); i++) {
            response.append((i + 1) + "." + tasks.getTask(i).toString() + "\n");
        }
        boolean isTaskListEmpty = tasks.getLength() == 0;
        String responseMessage = isTaskListEmpty ? "Your task list is empty." : response.toString();
        showResponse(false, responseMessage);
    }

    /**
     * Displays the tasks occurring on the date.
     * @param date Query date.
     * @param tasks Entire task list.
     */
    public void showOccurringTasks(LocalDate date, TaskList tasks) {
        ArrayList<Task> relevantTasks = tasks.getTasksOccurringOn(date);
        showResponse(false, "Tasks occurring on "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ":", tasksToString(relevantTasks));
    }

    public void showGoodbye() {
        showResponse(false, "Bye. Hope to see you again soon!");
    }

    /**
     * Displays matching tasks based on query.
     * @param query The query.
     * @param tasks Entire task list.
     */
    public void showMatchingTasks(String query, TaskList tasks) {
        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(query);
        showResponse(false, "Here are the matching tasks in your list:", tasksToString(matchingTasks));
    }

    private String numberOfTasksToString(TaskList tasks) {
        return "Now you have " + tasks.getLength() + " tasks in the list.";
    }

    private String tasksToString(ArrayList<Task> tasks) {
        int count = 0;
        StringBuilder response = new StringBuilder();
        for (Task task: tasks) {
            response.append((count + 1) + ". " + task + "\n");
            count++;
        }
        if (tasks.size() == 0) {
            response.append("No tasks.");
        }
        return response.toString();
    }
}
