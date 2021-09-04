package duke.gui;

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

public class DukeGui extends Application {
    protected ScrollPane scrollPane;
    protected VBox dialogContainer;
    protected TextField userInput;
    protected Button sendButton;
    protected Scene scene;
    protected Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    protected Image duke = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Creates components of the GUI.
     * @param stage The stage on which to render the scene.
     */
    protected void setUpGuiComponents(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Duke ChatBot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        adjustComponentSizes();
    }

    /**
     * Sets sizes and scroll settings for components.
     */
    private void adjustComponentSizes() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Starts the JavaFX GUI.
     * @param stage The primary stage that JavaFX provides
     */
    @Override
    public void start(Stage stage) {
        setUpGuiComponents(stage);

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            echo();
        });

        userInput.setOnAction((event) -> {
            echo();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Sends the user a copy of what they have input in the text input field.
     */
    private void echo() {
        Label userText = new Label(userInput.getText());
        dialogContainer.getChildren().addAll(
                new UserDialogBox(userText, new ImageView(user)),
                new DukeDialogBox(userText, new ImageView(duke))
        );
        userInput.clear();
    }
}
