package virushade;

import java.util.Timer;
import java.util.TimerTask;

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

    private final Image USER = new Image(this.getClass().getResourceAsStream("/images/Telescope.png"));
    private final Image VIRUSHADE = new Image(this.getClass().getResourceAsStream("/images/Virushade.png"));

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
        initializeGuiFields();
        setTheStage(stage);
        tweakGuiDisplay();
        sayHello();
        listenForUserInput();
    }

    private void initializeGuiFields() {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
    }

    private void setTheStage(Stage stage) {
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Virushade");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
    }

    private void tweakGuiDisplay() {
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
    }

    private void listenForUserInput() {
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
     * Gets Virushade to greet the user. Always ran when Virushade is launched.
     */
    private void sayHello() {
        Label virushadeText = new Label(ResponseProcessor.greet());
        dialogContainer.getChildren().addAll(DialogBox.getVirushadeDialog(virushadeText, new ImageView(VIRUSHADE)));
        userInput.clear();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Virushade's reply and then
     * appends them to the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        String output = getResponse(input);

        reply(input, output);

        if (input.equals("bye")) {
            shutdownVirushade();
        }

        userInput.clear();
    }

    private void reply(String input, String output) {
        Label userText = new Label(input);
        Label virushadeText = new Label(output);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(USER)),
                DialogBox.getVirushadeDialog(virushadeText, new ImageView(VIRUSHADE))
        );
    }

    private void shutdownVirushade() {
        // Disable all mode of input
        userInput.setDisable(true);
        sendButton.setDisable(true);

        // Exit after 1s for the user to view the exit message.
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        }, 1000);
    }

    /**
     * The function that gives a response to the user actions.
     * @param input The user input.
     * @return A string that represents the response by Virushade.
     */
    private String getResponse(String input) {
        return ResponseProcessor.handleMessage(input);
    }
}
