package duke;


import duke.command.Command;
import duke.task.TaskList;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * Duke program implements a basic task bot application. He serves like an efficient butler.
 *
 * @author Aiken Wong
 */
public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private boolean isExit = false;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Storage storage;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises a new Duke object with storage path data/taskList.txt
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("data/taskList.txt");
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showException(e);
        }
    }

    /**
     * Initialises a new Duke object by taking in a filePath for storage.
     *
     * @param filePath This will be the location where user tasks are in the hard disk.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException e) {
            ui.showException(e);
        }
    }

    /**
     * This method runs the app.
     */
    private void run() {
        ui.greet();
        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input, ui, tasks, storage);
                command.execute();
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showException(e);
            }
        }

    }

    /**
     * This is the main method which makes use of the run method.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/taskList.txt");
        duke.run();
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
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

        // more code to be added here later

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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));

        userText.setPadding(new Insets(0, 10, 0, 0));
        dukeText.setPadding(new Insets(0, 0, 0, 10));

        System.out.println(user.getWidth());

        ImageView userImage = new ImageView(user);
        ImageView dukeImage = new ImageView(duke);

        setClipViewport(userImage, 100);
        setClipViewport(dukeImage, 100);

        DialogBox userDialogBox = DialogBox.getUserDialog(userText, userImage);
        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeText, dukeImage);

        userDialogBox.setPadding(new Insets(0, 0, 30, 0));
        dukeDialogBox.setPadding(new Insets(0, 0, 30, 0));

        setDialogBoxBackgroundColor(userDialogBox, "#1FDA12");
        setDialogBoxBackgroundColor(dukeDialogBox, "#12B1DA");


        dialogContainer.getChildren().addAll(
            userDialogBox, dukeDialogBox
        );
        userInput.clear();
    }

    private void setClipViewport(ImageView imageView, int length) {
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);

        Circle circle = new Circle(length / 2, length / 2, Math.min(length, length) / 2);


        imageView.setClip(circle);
    }

    private void setDialogBoxBackgroundColor(DialogBox dialogBox, String hexValue) {
        dialogBox.setBackground(new Background(new BackgroundFill(Paint.valueOf(hexValue), null, null)));
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input) {
        return "Duke heard: " + input;
    }

}
