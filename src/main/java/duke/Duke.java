package duke;

import javafx.application.Application;
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

import task.Tasklist;
import ui.DialogBox;
import ui.Ui;

/**
 * Allows for the main initialization of the Duke Program
 *
 * @author: Wei Yangken
 */
public class Duke extends Application {

    private static final String DEFAULT_ADDRESS = "src/main/java/data/tasklist.txt";
    private Storage storage;
    private Tasklist tasklist;
    private String exitCmd = "bye";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Constructs Duke Object that stores information in filepath
     * @param filepath Location of stored data
     */
    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        this.tasklist = storage.load();
    }

    /**
     * Overloaded constructor of Duke Object with default address
     */
    public Duke() {
        this.storage = new Storage(DEFAULT_ADDRESS);
        this.tasklist = storage.load();
    }

    @Override
    public void start(Stage stage) {

        // Formatting window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        //Container for the content to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //Styling
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToHeight(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Adding functionality
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) ->
                scrollPane.setVvalue(1.0));

        // Creating scene
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getUserDialog(dukeText, duke)
        );
        userInput.clear();
    }

    public String getResponse(String input) {
        Ui user = new Ui(storage, tasklist);
        return "Duke: " + "ono";
    }



    /**
     * Run main program
     */
    public void run() {
        Ui.start();
        boolean isExit = false;
        Ui user = new Ui(storage, tasklist);
        while (!isExit) {
            if (user.readCommand().equals(exitCmd)) {
                break;
            }
        }
    }

    /**
     * Main function to run Duke Program
     * @param args
     */
    public static void main(String[] args) {
        String filepath = DEFAULT_ADDRESS;
        new Duke(filepath).run();
    }
}

