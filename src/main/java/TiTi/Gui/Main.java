package titi.gui;

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
import titi.util.SavedHistory;
import titi.util.TaskList;
import titi.util.Ui;


/**
 * A GUI for TiTI using javaFX.
 * Adapted from https://se-education.org/guides/index.html
 */
public class Main extends Application {

    private SavedHistory savedHistory;
    private TaskList taskList;
    private Ui ui;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/Setsu.PNG"));
    private Image tiTi = new Image(this.getClass().getResourceAsStream("/images/TiTi.PNG"));


    /**
     * Sets up user interface and display.
     *
     * @param stage stage for display
     */
    @Override
    public void start(Stage stage) {
        savedHistory = new SavedHistory();
        taskList = new TaskList(savedHistory.readHistory());
        ui = new Ui(savedHistory, taskList);

        // Setting up required components
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);

        stage.setTitle("TiTi Catbot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        // Setting up dimensions
        mainLayout.setPrefSize(300.0, 600.0);

        scrollPane.setPrefSize(430, 556);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(354.0);
        userInput.setPrefHeight(41);

        sendButton.setPrefWidth(75.0);
        sendButton.setPrefHeight(41);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // Setting up user interactions
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        stage.show();

        // Display welcome message
        Label tiTiText = new Label("\n" + ui.getWelcomeMessage());
        dialogContainer.getChildren().addAll(
            DialogBox.getTiTiDialog(tiTiText, new ImageView(tiTi))
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing TiTi's reply
     * then appends them to the dialog container.
     * Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label("\n" + userInput.getText() + "\n");
        Label tiTiText = new Label("\n" + getResponse(userInput.getText()) + "\n");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getTiTiDialog(tiTiText, new ImageView(tiTi)));
        userInput.clear();
    }


    /**
     * Generates a response to user input.
     *
     * @param input user input
     * @return String representing TiTi's response
     */
    public String getResponse(String input) {
        return ui.getResponse(input);
    }

}
