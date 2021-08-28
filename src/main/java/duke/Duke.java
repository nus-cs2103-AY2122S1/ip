package duke;
import duke.ui.Ui;
import javafx.application.Application;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Duke class used to run the duke.Duke chat-bot.
 * Contains methods that
 * (i)    initialises the Ui class and runs the chat-bot.
 */
public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));
    /**
     * Initialises the chatBot and runs the Duke chatBot.
     * @param args arguments for main method.
     */
    public static void main(String[] args) {
        Ui chatBot = new Ui();
        chatBot.run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Formatting the window to look as expected.

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();

        dialogContainer.setStyle("-fx-background-color: #F6E9D7;");
        dialogContainer.setMinHeight(600.0);
        dialogContainer.setSpacing(10.0);
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        //userInput.setStyle("-fx-background-color: #D2FDFF;");
        sendButton = new Button("Send");
        sendButton.setStyle("-fx-text-fill:WHITE; -fx-background-color: #52AB98;");

        AnchorPane mainLayout = new AnchorPane();

        //mainLayout.setStyle("-fx-background-color: #303C6C;");

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
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

        //sets padding between dialogContainer and the window
        dialogContainer.setPadding(new Insets(10));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        // more code to be added here later
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
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        Circle userCir = new Circle(50, 48, 45);
        ImageView u = new ImageView(user);
        u.setClip(userCir);
        u.setSmooth(true);

        Circle dukeCir = new Circle(50, 48, 45);
        ImageView d = new ImageView(duke);
        d.setClip(dukeCir);
        d.setSmooth(true);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, u),
                DialogBox.getDukeDialog(dukeText, d)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
