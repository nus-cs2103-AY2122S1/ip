package duke;

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
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * The main class for Duke.
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Button helpButton;
    private Scene scene;

    private Duke duke;
    private ResponseManager responseManager;
    private TaskManager taskManager;
    private Storage storage;
    private boolean isRunning = true;
    private Parser parser = new Parser();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Jesse.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Constructor for Duke.
     *
     * @param saveFileLocation The path to where the save file is to be stored.
     */
    public Duke(String saveFileLocation) {
        responseManager = new ResponseManager();
        storage = new Storage(saveFileLocation);
        taskManager = new TaskManager(storage.loadSave());
    }

    public Duke() {

    }

    public static void main(String[] args) {
        //new Duke("docs\\save.txt");
    }

    /**
     * Starts running the program.
     *
     * @param stage The UI stage to display the chat.
     */
    public void start(Stage stage) {
        //Step 1. Setting up required components
        duke = new Duke("docs\\save.txt");


        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        helpButton = new Button("Help");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton, helpButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("duke");
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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        helpButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        helpButton.setOnMouseClicked((event) -> {
            displayHelpPopup();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));



    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        System.out.println(react(userText));
        String dukeText = react(userText);

        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(userText, userImage),
                UserDialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    private void displayHelpPopup() {
//        dialogContainer.getChildren().addAll(
//                UserDialogBox.getDukeDialog("wefwe", userImage)
//        );
        String userText = userInput.getText();
        System.out.println(react(userText));
        String dukeText = react(userText);

        dialogContainer.getChildren().addAll(
                UserDialogBox.getUserDialog(userText, userImage),
                UserDialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        ICommand c = parser.getInput(input);
        c.execute(taskManager, responseManager, storage);
        return c.getReply();
    }

    /**
     * Runs the program until the bye command is used to end it.
     */
    public String react(String input) {
        ICommand c = parser.getInput(input);
        assert(c != null);
        c.execute(taskManager, responseManager, storage);
        return c.getReply();
    }
}
