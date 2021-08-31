package duke;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Class that implements methods to present the Duke.
 */
public class Ui {

//    private AnchorPane container;
//    private ScrollPane scrollPane;
//    private VBox dukeContainer;
//    private Button sendButton;
//    private Label sideLabel;
//    private Scene dukeScene;
//    private TextField dukeInput;

    /**
     * Constructor to create a UI object.
     */
    Ui() {
//        container = new AnchorPane();
//        scrollPane = new ScrollPane();
//        dukeContainer = new VBox();
//        sendButton = new Button("Send");
//        sideLabel = new Label("DUKE");
//        dukeScene = new Scene(dukeContainer);
//        dukeInput = new TextField();
    }

    private final static String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    private final static String HORIZONTAL_DIVIDE =
        "___________________________________________________________________";

    /**
     * Prints out a welcome message.
     */
    public void welcomeUser() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("What can Duke do for you today?");
        System.out.println(HORIZONTAL_DIVIDE);
    }

//    public void startUi(Stage stage) {
//        sideLabel.setStyle("-fx-start-margin: 150");
//        sideLabel.setTextFill(Color.color(1,1,1));
//        sideLabel.setFont(new Font("Helvetica", 45));
//        container.setPrefSize(700.0, 500.0);
//        container.setStyle("-fx-background-color: #13223b");
//
//        System.out.println("HERE");
//        scrollPane.setContent(dukeContainer);
//        scrollPane.setPrefSize(500.0, 473.3);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//        HBox topRow = new HBox();
//        topRow.getChildren().addAll(scrollPane, sideLabel);
//
//        container.getChildren().addAll(topRow, dukeInput, sendButton);
//        dukeContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        dukeInput.setPrefWidth(496.0);
//
//        sendButton.setPrefWidth(200.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//        AnchorPane.setLeftAnchor(dukeInput , 1.0);
//        AnchorPane.setBottomAnchor(dukeInput, 1.0);
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(500.0);
//        stage.setMinWidth(700.0);
//        stage.setScene(dukeScene);
//        stage.show();
//    }

    /**
     * Prints out the added entry.
     *
     * @param entry THe newly-added entry.
     * @param id The #id of the newly-added entry.
     */
    public void addEntry(Entry entry, int id) {
        System.out.println("I've added entry to your list!");
        this.printEntry(entry, id);
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out the added entry.
     *
     * @param entry THe newly-added entry.
     * @param id The #id of the newly-added entry.
     */
    public String getAddEntry(Entry entry, int id) {
        return "I've added entry to your list!" +
        this.getPrintEntry(entry, id);
    }

    /**
     * Prints out a horizontal line at the end of some operation.
     */
    public void endCommand() {
        System.out.println(HORIZONTAL_DIVIDE);
    }

    public void listMatches() {
        System.out.println("Looking for matching tasks in your list..");
    }

    public String getListMatches() {
        return "Looking for matching tasks in your list..";
    }

    public void foundMatches() {
        System.out.println("Here are the matching tasks in your list..");
    }

    public String getFoundMatches() {
        return "Here are the matching tasks in your list..";
    }

    /**
     * Prints out given entry.
     *
     * @param entry Entry to be printed.
     * @param id ID of entry to be printed.
     */
    public void printEntry(Entry entry, int id) {
        System.out.println("\t" + id + "." + entry);
    }

    /**
     * Returns given entry.
     *
     * @param entry Entry to be printed.
     * @param id ID of entry to be printed.
     * @return String for given entry.
     */
    public String getPrintEntry(Entry entry, int id) {
        return "\t" + id + "." + entry;
    }

    /**
     * Prints out farewell message.
     */
    public void printGoodByeUser() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out farewell message.
     *
     * @return String to say farewell to user.
     */
    public String getGoodByeUser() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Prints out the Error Message received when Parsing.
     *
     * @param parsingError Error encountered while parsing input.
     */
    public void handleParsingError(DukeException parsingError) {
        System.out.println(parsingError.getMessage());
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out the Error Message received when Parsing.
     *
     * @param parsingError Error encountered while parsing input.
     */
    public String getParsingError(DukeException parsingError) {
        return parsingError.getMessage();
    }

    /**
     * Prints out the Error Message received when loading data from memory.
     *
     * @param loadingError Error encountered while loading data from memory.
     */
    public void handleLoadingError(DukeException loadingError) {
        System.out.println(loadingError.getMessage());
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out entry just after deletion.
     *
     * @param deletedEntry Entry that has just been deleted.
     */
    public void showDeletedEntry(Entry deletedEntry) {
        System.out.println("Removed entry\n" + deletedEntry);
        System.out.println(HORIZONTAL_DIVIDE);
    }

    /**
     * Prints out entry just after deletion.
     *
     * @param deletedEntry Entry that has just been deleted.
     */
    public String getDeletedEntry(Entry deletedEntry) {
        return "Removed entry\n" + deletedEntry;
    }
}
