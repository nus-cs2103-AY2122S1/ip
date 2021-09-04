package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFX extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Label label;

    @Override
    //Most of these are from the CS2103 JavaFX tutorial
    public void start(Stage stage) {
        //step 1: Setting up required components.
        Label label = new Label("Hello World!"); // Creating a new Label control
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(label, scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); //no horizontal scroll
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); //vertical scroll

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

        //step 3: add functionality to handle user input:
        //set an event for clicking send button
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText())); //define an event function
            //that adds the label created from user input to the VBox
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText())); //same as the code above
            userInput.clear();
        });

        //scroll down to the end everytime dialogContainer changes height
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        //more code here later
    }

    /**
     * Iteration 1: Create label with specified text and add it to dialog container
     * @param text
     * @return label with specified text that has word wrap enabled
     * (if word is too long setting this to true wraps it around)
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);
        return textToAdd;
    }
}
