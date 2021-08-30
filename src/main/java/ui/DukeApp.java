package ui;

import commands.Command;
import duke.DukeException;
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
import parser.Parser;
import storage.Storage;
import tasks.TaskList;

//@@author wanyu-l-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart3.html
// with minor modifications
public final class DukeApp extends Application{
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private TaskList lst;
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private long startTime;

    public DukeApp() {
        this.ui = new Ui();
        this.storage = new Storage();
        try {
            storage.createFiles();
        } catch (DukeException e) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(new Label("something went wrong: "
                            + e.getMessage() + "\n" + "     exiting program...")));
            System.exit(0);
        }
        lst = new TaskList(storage.loadSaves());
        parser = new Parser();
    }

    @Override
    public void start(Stage stage) {
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

        stage.setTitle("Memo-Assistant Duke");
        stage.setResizable(false);
        stage.setMinHeight(800.0);
        stage.setMinWidth(1000.0);

        mainLayout.setPrefSize(1000.0, 800.0);

        scrollPane.setPrefSize(985, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(false);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(925.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> handleUserInput());

        userInput.setOnAction((event) -> handleUserInput());

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(Ui.getWelcomeMessage())));
    }

    private void handleUserInput() {
        String inputText = userInput.getText();
        Command c = new Parser().parse(inputText);
        if (c!= null) {
            Label userText = new Label(inputText);
            String executeResult = c.execute(lst, ui, storage);
            Label dukeText = new Label(getResponse(executeResult));
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(userText),
                    DialogBox.getDukeDialog(dukeText)
            );
            if (c.isExit()) {
                System.exit(0);
            }
        } else {
            dialogContainer.getChildren().addAll(DialogBox.getUserDialog(new Label(inputText)),
                    DialogBox.getDukeDialog(new Label(Ui.getHelperMessage())));
        }
        userInput.clear();
    }

    private String getResponse(String input) {
        return ui.getSeparator() + "\n"
                + input + "\n"
                + ui.getSeparator();
    }
}
//@@author
