import java.io.File;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Contains main and supports the whole bot system.
 */
public class Duke extends Application {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));



    /**
     * constructor for starting Duke.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser();
        this.tasks = new TaskList();
        File file = new File("./data/Duke.txt");
        storage.loadTextFile(tasks.getDoneCheck(),
                tasks.getList(),
                file);
    }

    public void run() {
        String nextLine = ui.scanNextLine();
        ui.emptyDescriptionException(nextLine);
        storage.checkFileCreation();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld, Color.CYAN); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        scene.getRoot().setStyle("-fx-font-family: 'Courier New'");
        stage.show(); // Render the stage.

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox(5);

        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Grace");
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
        dialogContainer.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);
        sendButton.setTextFill(Color.BLACK);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        Label greetings = new Label("Hello! I'm Grace" + "\n" + "How can I help you?");
        greetings.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, new CornerRadii(0), Insets.EMPTY)));
        greetings.setTextFill(Color.BLACK);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(greetings, new ImageView(duke)));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add.
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
        userText.setTextFill(Color.BLACK);
        userText.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, new CornerRadii(0), Insets.EMPTY)));
        String response = this.getResponse(userInput.getText());
        assert response.length() > 0 : "response cannot be empty";
        Label dukeText = new Label(response);
        dukeText.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, new CornerRadii(0), Insets.EMPTY)));
        dukeText.setTextFill(Color.BLACK);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return parser.readCommand(ui, input, tasks);
    }


    public static void main(String[] args) {
        new Duke().run();
    }




}
