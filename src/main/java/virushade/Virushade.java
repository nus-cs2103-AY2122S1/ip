package virushade;

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
import virushade.tasks.TaskList;

import java.util.Timer;
import java.util.TimerTask;

/**
 * The Main class of the application.
 */
public class Virushade extends Application {
    private Storage storageFile;
    private TaskList taskList;
    private ResponseProcessor responseProcessor;

    private ScrollPane scrollPane;
    private TextField userInput;
    private VBox dialogContainer;
    private Button sendButton;
    private Scene scene;
    private AnchorPane mainLayout;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image virushade = new Image(this.getClass().getResourceAsStream("/images/Virushade.png"));

    /**
     * The constructor for Virushade.
     * @param fileName The file path of the file where data is written to.
     */
    public Virushade(String fileName) {
        storageFile = new Storage(fileName);
        try {
            TaskList taskList = new TaskList(storageFile);
        } catch (VirushadeException e) {
            ResponseProcessor.handleVirushadeException(e);
        }
    }

    /**
     * Starts the Virushade application.
     * @param stage The stage.
     */
    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Virushade");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

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

        sayHello();

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     *
     */
    private void sayHello() {
        Label virushadeText = new Label(ResponseProcessor.greet());
        dialogContainer.getChildren().addAll(DialogBox.getVirushadeDialog(virushadeText, new ImageView(virushade)));
        userInput.clear();
    }

    /**
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Virushade's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String output = getResponse(input);

        Label userText = new Label(input);
        Label virushadeText = new Label(output);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getVirushadeDialog(virushadeText, new ImageView(virushade))
        );

        // Shutdown upon receiving exit message. Inspired by Zhang Shi Chen.
        if (input.equals("bye")) {
            // Disable all mode of input
            userInput.setDisable(true);
            sendButton.setDisable(true);

            // Display exit message then exit after 1s.
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.exit(0);
                }
            }, 1000);
        }

        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return ResponseProcessor.handleMessage(input);
    }
}
