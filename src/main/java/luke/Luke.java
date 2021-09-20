package luke;

import java.util.ArrayList;
import java.util.Objects;
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
 * Class to encapsulate Luke Chatbot.
 */
public class Luke extends Application {

    private final Ui lukeUi;
    private final Parser lukeParser;
    private final Storage lukeStorage;
    private EntryList entries;

    private final Image USER = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/UserImage.jpg")));
    private final Image LUKE = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/LukeImage.jpg")));

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
     * Constructor for Luke Chatbot.
     */

    public Luke() {
        this.lukeUi = new Ui();
        this.lukeParser = new Parser();
        this.lukeStorage = new Storage();
        try {
            this.entries = this.lukeStorage.readData();
        } catch (LukeException e) {
            this.lukeUi.handleLoadingError(e);
            this.entries = new EntryList();
        }
    }

    @Override
    public void start(Stage stage) {
        VBox lukeContainer = new VBox();
        TextField lukeInput = new TextField();
        ScrollPane scrollPane = configureScrollPane(lukeContainer);
        Button sendButton = configureSendButton(lukeContainer, lukeInput, stage);
        Label sideLabel = configureSideLabel();
        HBox topRow = configureTopRow(scrollPane, sideLabel);
        AnchorPane container = configureAnchorPane(scrollPane, sendButton, lukeInput, topRow);
        Scene scene = new Scene(container);
        configureLukeContainerAndLukeInput(lukeContainer, lukeInput, scrollPane, stage);
        configureStage(scene, stage);
        stage.show();
    }

    private void configureStage(Scene scene, Stage stage) {
        stage.setTitle("Luke");
        stage.setResizable(false);
        stage.setMinHeight(500.0);
        stage.setMinWidth(700.0);
        stage.setScene(scene);
    }

    private Button configureSendButton(VBox lukeContainer, TextField lukeInput, Stage stage) {
        Button sendButton = new Button();
        sendButton.setPrefWidth(200.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(lukeContainer, lukeInput, stage);
        });
        return sendButton;
    }

    private AnchorPane configureAnchorPane(ScrollPane scrollPane, Button sendButton, TextField lukeInput, HBox topRow) {
        AnchorPane anchor = new AnchorPane();
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(lukeInput , 1.0);
        AnchorPane.setBottomAnchor(lukeInput, 1.0);
        anchor.getChildren().addAll(topRow, lukeInput, sendButton);
        anchor.setPrefSize(700.0, 500.0);
        anchor.setStyle("-fx-background-color: #13223b");
        return anchor;
    }

    private void configureLukeContainerAndLukeInput(
            VBox lukeContainer, TextField lukeInput, ScrollPane scrollPane, Stage stage) {
        lukeContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        lukeContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        lukeInput.setPrefWidth(496.0);
        lukeInput.setOnAction((event) -> {
            handleUserInput(lukeContainer, lukeInput, stage);
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing USER input and the other containing Luke's reply and then appends them to
     * the dialog container. Clears the USER input after processing.
     */
    private void handleUserInput(VBox dialogContainer, TextField userInput, Stage stage) {
        Label userText = new Label(userInput.getText());
        Label lukeText = new Label(getResponse(userInput.getText(), stage));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(USER)).padDialog(10),
                DialogBox.getLukeDialog(lukeText, new ImageView(LUKE)).padDialog(10)
        );
        userInput.clear();
    }

    /**
     * Function to get response from Luke.
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

    private ScrollPane configureScrollPane(VBox lukeContainer) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(lukeContainer);
        scrollPane.setPrefSize(500.0, 473.3);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }

    private Label configureSideLabel() {
        Label sideLabel = new Label("LUKE");
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
     * @throws LukeException If ProcessedInput is invalid/incoherent.
     */
    private String processInput(ArrayList<String> parsedTerms) throws LukeException {
        if (parsedTerms.size() < 3) {
            throw new LukeException("Luke cannot understand your entry :/\nType 'help' for assistance");
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
                output = entries.getAddEntry(new Todo(entry), command, this.lukeUi);
                break;

            case EVENT_COMMAND:
                output = entries.getAddEntry(new Event(entry, timing), command, this.lukeUi);
                break;

            case DEADLINE_COMMAND:
                output = entries.getAddEntry(new Deadline(entry, timing), command, this.lukeUi);
                break;

            case DELETE_ENTRY_COMMAND:
                output = entries.getDeleteEntry(Integer.parseInt(entry), this.lukeUi);
                break;

            case FIND_COMMAND:
                output = entries.getFindEntry(entry, this.lukeUi);
                break;

            case HELP_COMMAND:
                output = this.lukeUi.getCommands();
                break;

            default:
                throw new LukeException("Sorry! Luke can't understand that\nType 'help' for assistance");
        }
        return output;
    }


    public String run(String input) {
        String output = "";
        if (input.equals("bye")) {
            return this.lukeUi.getGoodByeUser();
        }
        try {
            ArrayList<String> parsedTerms = this.lukeParser.parseInput(input);
            output = this.processInput(parsedTerms);
            this.lukeStorage.saveEntries(this.entries);
        } catch (LukeException e) {
            output = this.lukeUi.getParsingError(e);
        } catch (AssertionError e) {
            output = this.lukeUi.getAssertingError(e);
        }
        return output;
    }

    /**
     * Overrides the Object's toString method.
     *
     * @return String description of Luke.
     */
    @Override
    public String toString() {
        return "Hello! I'm Luke, your slightly useful personal assistant!\n"
                + "I can help you remember tasks and other things :D";
    }
}


