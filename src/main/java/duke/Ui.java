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
    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox();
    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    public Ui(Stage stage) {
        scrollPane.setContent(dialogContainer);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
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

    private void showResponse(String response) {
        Label dukeText = new Label(response);
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    private void showResponse(String ... response) {
        Label dukeText = new Label(String.join("\n", response));
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
    }

    public void showFullCommand(String command) {
        Label userText = new Label(command);
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );
    }

    public void showUndone(HistoryState lastHistoryState) {
        Command previousCommand = lastHistoryState.getCommand();
        TaskList previousTaskList = lastHistoryState.getTaskList();
        showResponse("Successfully undone previous command: " + previousCommand);
        showResponse("Task List after undoing: ");
        showTaskList(previousTaskList);
    }

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

    public void showLoadingError() {
        showResponse("Error loading data file, starting with empty duke.TaskList.");
    }

    public void showError(String errorMessage) {
        showResponse("Oops! " + errorMessage);
    }

    public void showWelcome() {
        showResponse("Welcome to DukeMaster", "What can I do for you?");
    }

    public void showAddedNewTask(Task task, TaskList tasks) {
        assert tasks.doesContain(task) : "Task should be in the TaskList";
        showResponse("Got it. I've added this task:", task.toString(), numberOfTasksToString(tasks));
    }

    public void showRemovedTask(Task task, TaskList tasks) {
        assert !tasks.doesContain(task) : "Task should have been removed from the TaskList";
        showResponse("Noted. I've removed this task:", task.toString(), numberOfTasksToString(tasks));
    }

    public void showMarkedAsDone(Task task) {
        assert task.getDoneStatus() : "Task should have been done";
        showResponse("Nice! I've marked this task as done:", task.toString());
    }

    public void showTaskList(TaskList tasks) {
        StringBuilder response = new StringBuilder();
        for (int i = 0; i < tasks.getLength(); i++) {
            response.append((i + 1) + "." + tasks.getTask(i).toString() + "\n");
        }
        showResponse(response.toString());
    }

    public void showOccurringTasks(LocalDate date, TaskList tasks) {
        ArrayList<Task> relevantTasks = tasks.getTasksOccurringOn(date);
        showResponse("Tasks occurring on "
                + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ":", tasksToString(relevantTasks));
    }

    public void showGoodbye() {
        showResponse("Bye. Hope to see you again soon!");
    }

    public void showMatchingTasks(String query, TaskList tasks) {
        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(query);
        showResponse("Here are the matching tasks in your list:", tasksToString(matchingTasks));
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
