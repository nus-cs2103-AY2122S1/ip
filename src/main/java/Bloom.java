import bloom.app.Storage;
import bloom.app.TaskList;
import bloom.app.Ui;
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
 * Represents the task manager-bot Bloom.
 */
public class Bloom extends Application {

    /** The storage. */
    private Storage storage = new Storage();

    /** The list of tasks. */
    private TaskList tasks = new TaskList();

    /** The user interface. */
    private Ui ui = new Ui();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(
            this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image bloom = new Image(
            this.getClass().getResourceAsStream("/images/bloom.jpg"));

    @Override
    public void start(Stage stage) {
        // Set up required components

        // The container for the content of the chat to scroll
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        // Format the window to look as expected
        formatScrollPane(scrollPane);
        formatDialogContainer(dialogContainer);
        formatUserInput(userInput);
        formatSendButton(sendButton);
        formatStage(stage);
        formatWindow(mainLayout);

        // Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer
                    .getChildren()
                    .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer
                    .getChildren()
                    .add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        // Scroll down to the end every time dialogContainer's height changes
        dialogContainer.heightProperty()
                .addListener((observable) -> scrollPane.setVvalue(1.0));

        // Add functionality to handle user input
        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());
    }

    private void formatStage(Stage stage) {
        stage.setTitle("Bloom");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void formatWindow(AnchorPane mainLayout) {
        mainLayout.setPrefSize(400.0, 600.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void formatScrollPane(ScrollPane scrollPane) {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
    }

    private void formatDialogContainer(VBox dialogContainer) {
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
    }

    private void formatUserInput(TextField userInput) {
        userInput.setPrefWidth(325.0);
    }

    private void formatSendButton(Button sendButton) {
        sendButton.setPrefWidth(55.0);
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return     a label with the specified text that has word wrap enabled
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Bloom's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getBloomDialog(dukeText, bloom)
        );
        userInput.clear();
    }

    /**
     * Outputs response according to user inputs.
     *
     * @param input the user input
     * @return      the string response
     */
    protected String getResponse(String input) {
        return new Ui().run(input);
    }
}
