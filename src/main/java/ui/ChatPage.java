package ui;

import alice.Alice;
import dialog.exceptions.DialogException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Page for interacting with Alice
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class ChatPage extends AnchorPane {

    private Alice alice;
    private String fileName;

    @FXML
    private ScrollPane scrollPane = new ScrollPane();
    @FXML
    private VBox dialogContainer = new VBox();
    @FXML
    private TextField userInput = new TextField();
    @FXML
    private Button sendButton = new Button("Send");
    @FXML
    private AnchorPane anchorPaneReference;


    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private final Image aliceImage = new Image(this.getClass().getResourceAsStream("/images/alice.png"));

    /**
     * Constructor for the chat Page.
     * Create an empty chat page with no alice.
     */
    public ChatPage() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
        printWelcomeText();
    }

    /**
     * Constructor for the chat page.
     *
     * @param fileName the filename that the user has chosen
     * @throws IOException     if there is anything wrong with the IO
     * @throws DialogException Dialog with the sameId cannot exist at the same time while
     *                         the app is running
     */
    ChatPage(String fileName) throws IOException {
        this.fileName = fileName;
        this.alice = new Alice(fileName);
        alice.getUi().getTaskDialog().setChatPage(this);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        printWelcomeText();
    }

    /**
     * Return Alice currently embedded in this chat page
     *
     * @return alice, the virtual assistant
     */
    public Alice getAlice() {
        return this.alice;
    }

    /** initialise when building chat page */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Setter for alice
     *
     * @param a Alice to be set to this chat page
     */
    public void setAlice(Alice a) {
        alice = a;
    }

    /**
     * Setter for fileName of the chat page
     *
     * @param fileName name of file
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Setter for alice using a file name to automatically link alice
     * to the specified file.
     *
     * @param fileName name of file
     */
    public void setAliceByFilename(String fileName) {
        try {
            this.alice = new Alice(fileName);
            alice.getTaskDialog().setChatPage(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Print initial welcoming message
     */
    public void printWelcomeText() {
        try {
            Label logo = getDialogLabel("Alice");
            logo.setStyle("-fx-font: normal italic 72px 'verdana' ");

            Label welcome = getDialogLabel(Ui.getWelcomeText());

            dialogContainer.getChildren().addAll(
                    logo,
                    welcome
            );
            showCurrentList();
        } catch (Exception e) {
            printError(e);
        }
    }


    /**
     * Create label from String
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /** for executing the command */
    @FXML
    private void handleUserInput() {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userInput.getText(), userImage)
        );
        this.alice.execute(userInput.getText());
        userInput.clear();
    }

    /**
     * Print the list of tasks in this chat page
     */
    public void showCurrentList() {
        dialogContainer.getChildren().add(DialogBox.getAliceDialog(alice.getUi().getCurrentList(), aliceImage));
    }

    /**
     * Print error to this chat page
     *
     * @param e exception to be print
     */
    public void printError(Exception e) {
        dialogContainer.getChildren().add(DialogBox.getAliceDialog(Ui.getErrorText(e), aliceImage));
    }

    /**
     * Explicitly ask Alice to print something into this chat page
     *
     * @param input string input to be printed
     */
    public void printAlicely(String input) {
        dialogContainer.getChildren().add(DialogBox.getAliceDialog(input, aliceImage));
    }

    /**
     * Exit the chat page and go back to the start page
     */
    public void exit() {
        try {
            Stage stage = (Stage) anchorPaneReference.getScene().getWindow();
            stage.setTitle("Alice");
            FXMLLoader fxmlLoader = new FXMLLoader(StartPage.class.getResource("/view/StartPage.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<StartPage>getController().fetchSaveFiles();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
