package duke;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.util.ArrayList;

/**
 * Deals with the interaction of a user.
 */
public class Ui {
    private final String OUTRO = "Bye. Hope to see you again soon!";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Returns a goodbye message in a String.
     *
     * @return Goodbye message.
     */
    public String goodbyeMessageToString() {
        return OUTRO;
    }

    /**
     * Returns a done message in a String.
     *
     * @param currentTask The current task.
     * @return Done message.
     */
    public String doneMessageToString(Task currentTask, boolean isDone) {
        String message = String.format("Nice! I've marked this task as %s:", isDone ? "done" : "undone") + "\n" + currentTask.toString();
        return message;
    }

    /**
     * Returns the list message in a String.
     *
     * @param tasks Tasks to be listed.
     * @return List message.
     */
    public String listMessageToString(ArrayList<Task> tasks) {
        String message = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            message += "    " + ((i + 1) + "." + currentTask.toString() + "\n");
        }
        message += "You have a total of " + tasks.size() + " tasks";
        return message;
    }

    /**
     * Returns the list message in a String.
     *
     * @param description Description to the end of the String.
     * @param tasks Tasks to be listed.
     * @return List message.
     */
    public String listMessageToString(String description, ArrayList<Task> tasks) {
        String message = String.format("Here are the %s tasks in your list:\n", description);
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            message += "    " + (i + 1) + "." + currentTask.toString() + "\n";
        }
        message += String.format("You have a total of " + tasks.size() + " %s tasks.\n", description);
        return message;
    }

    /**
     * Returns the added task in a String.
     *
     * @param currentTask The current task added.
     * @param tasks The tasks to be listed.
     * @return Task message.
     */
    public String taskMessageToString(Task currentTask, ArrayList<Task> tasks) {
        String message = String.format("Got it. I've added this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in this list.\n", tasks.size());
        return message;
    }

    /**
     * Returns the deleted task in a String.
     *
     * @param currentTask The current task deleted.
     * @param tasks The tasks left.
     * @return List message.
     */
    public String deleteMessageToString(Task currentTask, ArrayList<Task> tasks) {
        String message = String.format("Noted. I've removed this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in the list\n", tasks.size());
        return message;
    }

    public String undoMessageToString(String message) {
        return message;
    }

    /**
     * Returns a String when unknown command is parsed.
     *
     * @return Unknown String.
     */
    public String unknownMessageToString() {
        return "Sorry, unknown command!";
    }

    /**
     * Returns a String when there is nothing parsed.
     *
     * @return Nothing found String.
     */
    public String nothingFoundMessageToString() {
        return "Sorry, there are no matching tasks";
    }

    /**
     * String to be printed on GUI upon opening Duke.
     *
     * @return String to be printed on GUI.
     */
    public String greetingMessageToString() {
        return "    Hello, welcome to Duke! Type help to see your available commands.";
    }

    /**
     * Initialises the Gui.
     *
     * @param stage The stage to initialise Gui.
     */
    public void initialiseGui(Stage stage, Duke duke) {
        setUpUi(stage);
        setupEventHandlers();

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(duke);
        });

        userInputField.setOnAction((event) -> {
            handleUserInput(duke);
        });
    }

    /**
     * Sets up the UI used by Duke.
     *
     * @param stage Stage by which the UI is set up.
     */
    public void setUpUi(Stage stage) {
        assert userImage != null : "userImage cannot be null";
        assert dukeImage != null : "userImage cannot be null";
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInputField = new TextField();
        sendButton = new Button("Enter");
        AnchorPane mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        setUpScrollPane(scrollPane);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(25.0);
        userInputField.setPrefWidth(435.0);
        sendButton.setPrefWidth(55.0);

        setUpAnchorPane(scrollPane, sendButton, userInputField);

        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInputField, sendButton);
    }

    /**
     * Sets up ScrollPane.
     *
     * @param scrollPane Current ScrollPane to be set up.
     */
    public void setUpScrollPane(ScrollPane scrollPane) {
        scrollPane.setPrefSize(485.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    /**
     * Sets up AnchorPane.
     *
     * @param scrollPane Sets current ScrollPane.
     * @param sendButton Sets current Button.
     * @param userInputField Sets current TextField.
     */
    public void setUpAnchorPane(ScrollPane scrollPane, Button sendButton, TextField userInputField) {
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInputField, 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);
    }

    /**
     * Sets up the events when buttons are clicked.
     */
    private void setupEventHandlers() {
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInputField.getText()));
            userInputField.clear();
        });

        userInputField.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInputField.getText()));
            userInputField.clear();
        });

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Formats the Label
     *
     * @param text The text in Label.
     * @return The formatted Label.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Handles the user input and returns it on Gui.
     *
     * @param duke Duke to run the program and generate an output.
     */
    private void handleUserInput(Duke duke) {
        Label userText = new Label(userInputField.getText());
        Label dukeText = new Label(duke.run(userInputField.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(userImage)),
                DialogBox.getDukeDialog(dukeText, new ImageView(dukeImage))
        );
        userInputField.clear();
    }
}
