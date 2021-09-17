package duke;

import java.util.ArrayList;
import java.util.Objects;
import java.util.List;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class to encapsulate Duke Chatbot.
 */
public class Duke extends Application {

    private final Ui dukeUi;
    private final Parser dukeParser;
    private final Storage dukeStorage;
    private EntryList entries;

    private final Image USER =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaUser.png")));
    private final Image DUKE =
            new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/DaDuke.png")));

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
        configureSendButton(dukeContainer, dukeInput, stage);
    }

    private void configureSendButton(VBox dukeContainer, TextField dukeInput, Stage stage) {
        Button sendButton = new Button();
        sendButton.setPrefWidth(200.0);
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput(dukeContainer, dukeInput, stage);
        });
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing USER input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the USER input after processing.
     */
    private void handleUserInput(VBox dialogContainer, TextField userInput, Stage stage) {
        String response = getResponse(userInput.getText());
        if (response.isEmpty()) {
            stage.close();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), USER),
                DialogBox.getDukeDialog(response, DUKE)
        );
        userInput.clear();
    }

    /**
     * Function to get response from Duke.
     */
    public String getResponse(String input) {
        if (input.equals(TERMINATION_COMMAND)) {
            return "";
        } else {
            return run(input);
        }
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
                output = entries.getEntryAsDone(Integer.parseInt(entry), this.dukeUi);
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

    /**
     * Runs Duke and returns output String.
     *
     * @param input String containing user input.
     * @return output String with
     */
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
