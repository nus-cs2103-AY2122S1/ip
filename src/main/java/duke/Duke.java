package duke;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class to encapsulate Duke Chatbot.
 */
public class Duke extends Application {

    private final Ui dukeUi;
    private final Parser dukeParser;
    private final Storage dukeStorage;
    private EntryList entries;

    private final Image USER = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image DUKE = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

    public static final String TERMINATION_COMMAND = "bye";
    public static final String LIST_ENTRIES_COMMAND = "list";
    public static final String MARK_ENTRY_DONE_COMMAND = "done";
    public static final String DELETE_ENTRY_COMMAND = "delete";
    public static final String TODO_COMMAND = "todo";
    public static final String EVENT_COMMAND = "event";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String FIND_COMMAND = "find";
    public static final String HELP_COMMAND = "help";
    public static final List<String> commands =
            List.of(TERMINATION_COMMAND, LIST_ENTRIES_COMMAND,
                    MARK_ENTRY_DONE_COMMAND, DELETE_ENTRY_COMMAND,
                    TODO_COMMAND, EVENT_COMMAND, DEADLINE_COMMAND,
                    FIND_COMMAND, HELP_COMMAND);

    /**
     * Constructor for Duke Chatbot.
     */
    public Duke() {
        this.dukeUi = new Ui();
        this.dukeParser = new Parser();
        this.dukeStorage = new Storage();
        try {
            this.entries = this.dukeStorage.readData();
        } catch (DukeException e) {
            //specific to loading error dukeUi
            this.dukeUi.handleLoadingError(e);
            this.entries = new EntryList();
        }
    }

    @Override
    public void start(Stage stage) {
        VBox dukeContainer = new VBox();
        TextField dukeInput = new TextField();
        ScrollPane scrollPane = configureScrollPane(dukeContainer);
        Button sendButton = configureSendButton(dukeContainer, dukeInput, stage);
        Label sideLabel = configureSideLabel();
        HBox topRow = configureTopRow(scrollPane, sideLabel);
        AnchorPane container = configureAnchorPane(scrollPane, sendButton, dukeInput, topRow);
        Scene scene = new Scene(container);
        configureDukeContainerAndInput(dukeContainer, dukeInput, scrollPane, stage);
        configureStage(scene, stage);
        stage.show();
    }

    private void configureStage(Scene scene, Stage stage) {
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(500.0);
        stage.setMinWidth(700.0);
        stage.setScene(scene);
    }

    private Button configureSendButton(VBox dukeContainer, TextField dukeInput, Stage stage) {
        Button sendButton = new Button();
        sendButton.setPrefWidth(200.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(dukeContainer, dukeInput, stage);
        });
        return sendButton;
    }

    private AnchorPane configureAnchorPane(ScrollPane scrollPane, Button sendButton, TextField dukeInput, HBox topRow) {
        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(dukeInput , 1.0);
        AnchorPane.setBottomAnchor(dukeInput, 1.0);
        anchor.getChildren().addAll(topRow, dukeInput, sendButton);
        anchor.setPrefSize(700.0, 500.0);
        anchor.setStyle("-fx-background-color: #13223b");
        return anchor;
    }

    private void configureDukeContainerAndInput(VBox dukeContainer, TextField dukeInput,
                                                ScrollPane scrollPane, Stage stage) {
        dukeContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dukeContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        dukeInput.setPrefWidth(496.0);
        dukeInput.setOnAction((event) -> {
            handleUserInput(dukeContainer, dukeInput, stage);
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing USER input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the USER input after processing.
     */
    private void handleUserInput(VBox dialogContainer, TextField userInput, Stage stage) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText(), stage));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(USER)).padDialog(10),
                DialogBox.getDukeDialog(dukeText, new ImageView(DUKE)).padDialog(10)
        );
        userInput.clear();
    }

    /**
     * Function to get response from Duke.
     */
    private String getResponse(String input, Stage stage) {
        if (input.equals(TERMINATION_COMMAND)) {
            stage.close();
            return "";
        } else {
            return run(input);
        }
    }

    private HBox configureTopRow(ScrollPane scrollPane, Label sideLabel) {
        HBox topRow = new HBox();
        topRow.getChildren().addAll(scrollPane, sideLabel);
        return topRow;
    }

    private ScrollPane configureScrollPane(VBox dukeContainer) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(dukeContainer);
        scrollPane.setPrefSize(500.0, 473.3);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    private Label configureSideLabel() {
        Label sideLabel = new Label("DUKE");
        sideLabel.setTextFill(Color.color(1,1,1));
        sideLabel.setFont(new Font("Arial", 45));
        sideLabel.setStyle("-fx-start-margin: 100");
        return sideLabel;
    }

    /**
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
     * Method containing Logic for processed Input.
     *
     * @param parsedTerms ArrayList containing the command, entry and timing (in that order).
     * @throws DukeException If ProcessedInput is invalid/incoherent.
     */
    private String processInput(ArrayList<String> parsedTerms) throws DukeException {
        if (parsedTerms.size() < 3) {
            throw new DukeException("Duke Cannot Understand Your Entry!");
        }
        String command = parsedTerms.get(0);
        String entry = parsedTerms.get(1);
        String timing = parsedTerms.get(2);
        String output = "";

        //Process Command
        switch(command) {
            case LIST_ENTRIES_COMMAND:
                output = entries.getEntries();
                break;

            case MARK_ENTRY_DONE_COMMAND:
                output = entries.getEntryAsDone(Integer.parseInt(entry));
                break;

            case TODO_COMMAND:
                output = entries.getAddEntry(new Todo(entry), command, this.dukeUi);
                break;

            case EVENT_COMMAND:
                output = entries.getAddEntry(new Event(entry, timing), command, this.dukeUi);
                break;

            case DEADLINE_COMMAND:
                output = entries.getAddEntry(new Deadline(entry, timing), command, this.dukeUi);
                break;

            case DELETE_ENTRY_COMMAND:
                output = entries.getDeleteEntry(Integer.parseInt(entry), this.dukeUi);
                break;

            case FIND_COMMAND:
                output = entries.getFindEntry(entry, this.dukeUi);
                break;

            case HELP_COMMAND:
                output = this.dukeUi.getCommands();
                break;

            default:
                throw new DukeException("Sorry! Duke can't understand what that means");
        }
        return output;
    }


    public String run(String input) {
        String output = "";
        if (input.equals("bye")) {
            return this.dukeUi.getGoodByeUser();
        }
        try {
            ArrayList<String> parsedTerms = this.dukeParser.parseInput(input);
            output = this.processInput(parsedTerms);
            this.dukeStorage.saveEntries(this.entries);
        } catch (DukeException e) {
            output = this.dukeUi.getParsingError(e);
        } catch (AssertionError e) {
            output = this.dukeUi.getAssertingError(e);
        }
        return output;
    }

    /**
     * Overrides the Object's toString method.
     *
     * @return String description of Duke.
     */
    @Override
    public String toString() {
        return "I'm Duke, a simple chatbot to help you remember tasks!";
    }
}
