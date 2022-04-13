package duke;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import duke.command.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.DialogBox;
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
import javafx.stage.Stage;



/**
 * This program is a chatbot that helps keep track of various tasks.
 *
 * @author Lethicia Renissa Santoso (G12)
 */
public class Duke extends Application {
    private String filePath = "data/duke.txt";
    private Storage storage;
    private TaskList tasks;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/elsa.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/olaf.png"));

    /**
     * constructor for Duke object.
     */
    public Duke() {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showErrorMessage(e);
            tasks = new TaskList(new ArrayList<Task>());
        }

        File f = new File(filePath);
        assert f.exists() : "filePath should have been initiated.";
    }

    @Override
    public void start(Stage stage) {

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();

        Label dukeWelcome = new Label("Hello from Duke! \nWhat do you need to do today?");
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeWelcome, new ImageView(duke))
        );

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //styles
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 600.0);

        scrollPane.setPrefSize(485, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        //dialogContainer styles
        dialogContainer.setSpacing(10.0);
        dialogContainer.setPadding(new Insets(10));
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(65.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));


        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }

    private void handleUserInput() {
        String input = userInput.getText();
        Label userText = new Label(input);
        Label dukeText = new Label(getResponse(input));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
        assert (userInput.getText().equals("")) : "userInput should be cleared.";
        if (input.equals("bye")) {
            new Timer().schedule(new TimerTask() {
                public void run () { System.exit(0); }
            }, 10000);
        }
    }

    private String getResponse(String input) {
        String output;
        try {
            Command cmd = Parser.getCommand(input);
            output = cmd.execute(tasks);
        } catch (DukeException e) {
            output = Ui.showErrorMessage(e);
        }
        this.storage.write(tasks);

        assert !output.equals("") : "response output should not be empty.";

        return output;
    }
}
