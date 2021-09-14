package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class is used to simulate an automatic list creator which saves its data and changes it
 * when the user interacts with it.
 */
public class Duke extends Application {
    /** The storage class used to store files for Duke. **/
    private static Storage storage;

    /** The list of tasks given to Duke (if any). **/
    private static TaskList tasks;

    /** Boolean to check if first time using Duke. **/
    private static boolean isFirstTime = false;

    /** Fields for GUI **/
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Baba.jpg"));

    /**
     * Constructor for Duke.
     *
     * @param filePath Path of the file to be used when starting Duke.
     */
    public Duke(String filePath) {

        storage = new Storage(filePath);
        try {
            Path storagePath = Paths.get(".", filePath);

            // Creating new parent directory if does not exist
            File dukeFile = new File(filePath);
            File parentDir = dukeFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!Files.exists(storagePath)) {
                isFirstTime = true;
                Files.createFile(storagePath);
            }

            tasks = new TaskList(Storage.load());
        } catch (IOException e) {
            System.out.println("Failed to create storage file: "
                    + e.getMessage());
        }

    }

    /**
     * Returns true if user runs Duke for the first time, false otherwise.
     *
     * @return True if user runs Duke for the first time, false otherwise.
     */
    public static boolean isFirstTimeCreation() {
        return isFirstTime;
    }

    /**
     * Returns the response on Duke application after user has given some input.
     *
     * @param input user input that is typed into Duke
     * @return message displaying what Duke has done with the user input
     */
    public static String getResponse(String input) {
        assert input != null : "Input cannot be null";
        return "Baba the Duke says: \n" + Parser.evaluateUserInput(input);
    }

    /**
     * Sets up the 'stage' for the GUI of Duke.
     *
     * @param stage the stage used to setup the GUI.
     */
    @Override
    public void start(Stage stage) {
        // Setting up stage and formatting window to look as expected
        setUpStage(stage);

        // Handles user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // Allows scrolling to the end if text interactions exceed window height.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Functionality for handling user input.
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Sets up a stage using JavaFX tools.
     *
     * @param stage the stage we are setting up and using for Duke.
     */
    private void setUpStage(Stage stage) {
        // Container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setTitle("Duke");
        stage.setResizable(true);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        stage.show();

        formatStage(mainLayout);
    }

    /**
     * Formats the stage for better look and sizing.
     *
     * @param mainLayout the AnchorPane from the stage used in Duke.
     */
    private void formatStage(AnchorPane mainLayout) {
        mainLayout.setPrefSize(400.0, 700.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Method used to begin processing of Duke.
     */
    public void run() {
        Scanner newScan = new Scanner(System.in);
        Parser.evaluateUserInput(Duke.getResponse(newScan.toString()));
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }
}
