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
    private final String INTRO = "Hello! I'm Duke\n"
            + "What can I do for you today?";

    private final String OUTRO = "Bye. Hope to see you again soon!";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInputField;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    /**
     * Prints intro message.
     */
    public void showIntroMessage() {
        System.out.println(INTRO);
    }

    /**
     * Prints outro message.
     */
    public void showGoodbyeMessage() {
        System.out.println(OUTRO);
    }

    /**
     * Prints done message.
     *
     * @param currentTask Task completed.
     */
    public void showDoneMessage(Task currentTask) {
        System.out.println("Nice! I've marked this task as done:" + "\n" + currentTask.toString());
    }

    /**
     * Prints the tasks.
     *
     * @param list Tasks provided.
     */
    public void showListMessage(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            Task currentTask = list.get(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
        System.out.println("You have a total of " + list.size() + " tasks");
    }

    /**
     * Prints the task added.
     *
     * @param description The task added.
     * @param list The list of tasks.
     */
    public void showListMessage(String description, ArrayList<Task> list) {
        System.out.format("Here are the %s tasks in your list:\n", description);
        for (int i = 0; i < list.size(); i++) {
            Task currentTask = list.get(i);
            System.out.println((i + 1) + "." + currentTask.toString());
        }
        System.out.format("You have a total of " + list.size() + " %s tasks.\n", description);
    }

    public void showTaskMessage(Task currentTask, ArrayList<Task> list) {
        System.out.format("Got it. I've added this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in this list.\n", list.size());
    }

    /**
     * Prints the deleted task.
     *
     * @param currentTask The task deleted.
     * @param list The list of tasks.
     */
    public void showDeleteMessage(Task currentTask, ArrayList<Task> list) {
        System.out.format("Noted. I've removed this task:\n" + currentTask.toString() + "\n"
                + "Now you have %d tasks in the list\n", list.size());
    }

    /**
     * Prints message if unknown command given.
     */
    public void showUnknownMessage() {
        System.out.println("Sorry, unknown command!");
    }

    /**
     * Prints message if there are problems loading the list.
     */
    public void showLoadingErrorMessage() {
        System.out.println("Your file seems to have issues loading.");
    }

    /**
     * Prints message if there are no matches in find.
     */
    public void showNothingFoundMessage() {
        System.out.println("Sorry, there are no matching tasks");
    }

    /**
     * Initialises the Gui.
     * @param stage The stage to initialise Gui
     */
    public void initialiseGui(Stage stage) {
        setUpUi(stage);
        setupEventHandlers();

        stage.setScene(scene);
        stage.show();

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInputField.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Sets up the UI used by Duke.
     *
     * @param stage Stage by which the UI is set up.
     */
    public void setUpUi(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInputField = new TextField();
        sendButton = new Button("Send");
        AnchorPane mainLayout = new AnchorPane();
        scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485.0, 535.0);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(25.0);

        userInputField.setPrefWidth(435.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInputField, 1.0);
        AnchorPane.setBottomAnchor(userInputField, 1.0);

        scrollPane.setContent(dialogContainer);
        mainLayout.getChildren().addAll(scrollPane, userInputField, sendButton);

        stage.setScene(scene);
        stage.show();
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
     */
    private void handleUserInput() {
        Label userText = new Label(userInputField.getText());
        Label dukeText = new Label(getResponse(userInputField.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInputField.clear();
    }

    /**
     * Prints message when user inputs text.
     *
     * @param input The user's input.
     * @return String of input.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
