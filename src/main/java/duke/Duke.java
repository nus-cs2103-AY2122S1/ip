package duke;

import java.util.ArrayList;
import java.util.Scanner;
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

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private final String TERMINATION_COMMAND = "bye";
    private final String LIST_ENTRIES_COMMAND = "list";
    private final String MARK_ENTRY_DONE_COMMAND = "done";
    private final String DELETE_ENTRY_COMMAND = "delete";
    private final String TODO_COMMAND = "todo";
    private final String EVENT_COMMAND = "event";
    private final String DEADLINE_COMMAND = "deadline";
    private final String FIND_COMMAND = "find";

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
        AnchorPane container = new AnchorPane();
        ScrollPane scrollPane = new ScrollPane();
        VBox dukeContainer = new VBox();
        TextField dukeInput = new TextField();
        Button sendButton = new Button("Send");
        Scene scene = new Scene(container);

        Label sideLabel = new Label("DUKE");
        sideLabel.setTextFill(Color.color(1,1,1));
        sideLabel.setFont(new Font("Arial", 45));
        sideLabel.setStyle("-fx-start-margin: 100");
        container.setPrefSize(700.0, 500.0);
        container.setStyle("-fx-background-color: #13223b");

        scrollPane.setContent(dukeContainer);
        scrollPane.setPrefSize(500.0, 473.3);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        HBox topRow = new HBox();
        topRow.getChildren().addAll(scrollPane, sideLabel);

        container.getChildren().addAll(topRow, dukeInput, sendButton);
        dukeContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        dukeInput.setPrefWidth(496.0);

        sendButton.setPrefWidth(200.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(dukeContainer, dukeInput, stage);
        });

        dukeInput.setOnAction((event) -> {
            handleUserInput(dukeContainer, dukeInput, stage);
        });

        dukeContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setLeftAnchor(dukeInput , 1.0);
        AnchorPane.setBottomAnchor(dukeInput, 1.0);

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(500.0);
        stage.setMinWidth(700.0);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput(VBox dialogContainer, TextField userInput, Stage stage) {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText(), stage));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)).padUserDialog(10),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke)).padDukeDialog(10)
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    private String getResponse(String input, Stage stage) {
        if (input.equals("bye")) {
            stage.close();
            return "";
        } else {
            return run(input);
        }
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

            default:
                throw new DukeException("Sorry! Duke can't understand what that means");
        }
//        switch(command) {
//        case LIST_ENTRIES_COMMAND:
//            entries.displayEntries(this.dukeUi);
//            break;
//
//        case MARK_ENTRY_DONE_COMMAND:
//            entries.markEntryAsDone(Integer.parseInt(entry));
//            break;
//
//        case TODO_COMMAND:
//            entries.addEntry(new Todo(entry), command, this.dukeUi);
//            break;
//
//        case EVENT_COMMAND:
//            entries.addEntry(new Event(entry, timing), command, this.dukeUi);
//            break;
//
//        case DEADLINE_COMMAND:
//            entries.addEntry(new Deadline(entry, timing), command, this.dukeUi);
//            break;
//
//        case DELETE_ENTRY_COMMAND:
//            entries.deleteEntry(Integer.parseInt(entry), this.dukeUi);
//            break;
//
//        case FIND_COMMAND:
//            entries.findEntry(entry, this.dukeUi);
//            break;
//
//            default:
//                throw new DukeException("Sorry! Duke can't understand what that means");
//        }
        return output;
    }

//    /**
//     * Method to perform initialise Duke operations.
//     */
//    private void run() {
//        this.dukeUi.welcomeUser();
//        Scanner inputScanner = new Scanner(System.in);
//        String input = inputScanner.nextLine();
//        while (!(input.equals(TERMINATION_COMMAND))) {
//            try {
//                ArrayList<String> parsedTerms = this.dukeParser.parseInput(input);
//                this.processInput(parsedTerms);
//                this.dukeStorage.saveEntries(this.entries);
//            } catch (DukeException e) {
//                this.dukeUi.handleParsingError(e);
//            }
//            input = inputScanner.nextLine();
//        }
//        this.dukeUi.printGoodByeUser();
//    }

    private String run(String input) {
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
        }
        return output;
    }

//    /**
//     * Main method that starts Duke.
//     *
//     * @param args Arguments passed when Duke is started.
//     */
//    public static void main(String[] args) {
//        new Duke().run();
//    }

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
