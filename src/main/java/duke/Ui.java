package duke;

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
 * Ui handles all of the text interface functionality of the program
 *
 * @author Loh Wen Hao Aaron
 *
 */
public class Ui {
    private static final String WELCOME_MESSAGE = "Hello, I'm Dobie!\nWhat can I do for you?";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Duke duke;

    public Ui(Duke duke) {
        this.duke = duke;
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @param isDobie Boolean toggling the dialog portraits
     * @return A label with the specified text that has word wrap enabled.
     */
    public void addDialog(String text, boolean isDobie) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        if (isDobie) {
            DialogBox dobieDialogBox = DialogBox.getDukeDialog(textToAdd, dukeImage);
            dialogContainer.getChildren().add(dobieDialogBox);
        } else {
            DialogBox userDialogBox = DialogBox.getUserDialog(textToAdd, userImage);
            dialogContainer.getChildren().add(userDialogBox);
        }

    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        //assert duke object is not empty
        assert this.duke != null;
        String text = userInput.getText();
        this.duke.runCommand(text);
        userInput.clear();
    }

    /**
     * Initialises the scene and the UI for the program.
     *
     * @param stage The specified stage to be used.
     */
    public Scene initialiseScene(Stage stage) {

        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("DobieBot");
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

        AnchorPane.setTopAnchor(scrollPane, 10.0);
        AnchorPane.setLeftAnchor(scrollPane, 5.0);
        AnchorPane.setRightAnchor(scrollPane, 5.0);

        AnchorPane.setBottomAnchor(sendButton, 20.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);

        AnchorPane.setLeftAnchor(userInput , 5.0);
        AnchorPane.setBottomAnchor(userInput, 20.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        //Show welcome message
        Label uiMessage = new Label(this.getWelcomeMessage());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(uiMessage, dukeImage));

        scene = new Scene(mainLayout);

        return this.scene;
    }
}
