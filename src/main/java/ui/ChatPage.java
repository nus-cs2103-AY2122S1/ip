package ui;

import alice.Alice;
import dialog.DialogException;
import javafx.scene.Node;
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
import storage.Storage;

import java.io.IOException;

/**
 * Page for interacting with Alice
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.02
 * @since 0.01
 */
public class ChatPage extends Page {

    private Alice alice;
    private String fileName;
    private Stage stage;

    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox();
    private TextField userInput = new TextField();
    private Button sendButton = new Button("Send");


    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpeg"));
    private final Image aliceImage = new Image(this.getClass().getResourceAsStream("/images/alice.png"));

    /**
     * Constructor for the chat page.
     *
     * @param fileName the filename that the user has chosen
     * @param stage    the stage passed from the Start Page to navigate back
     * @throws IOException     if there is anything wrong with the IO
     * @throws DialogException Dialog with the sameId cannot exist at the same time while
     *                         the app is running
     */
    ChatPage(String fileName, Stage stage) throws IOException, DialogException {
        this.fileName = fileName;
        this.stage = stage;
        this.alice = new Alice(fileName);
        alice.getUi().getTaskDialog().setChatPage(this);
    }

    /**
     * Return Alice currently embedded in this chat page
     *
     * @return alice, the virtual assistant
     */
    public Alice getAlice() {
        return this.alice;
    }

    /**
     * Return the layout of the chat page for interacting with Alice as Scene
     *
     * @return the layout of the chat page for interacting with Alice
     */
    @Override
    public Scene layout() {
        AnchorPane mainLayout = new AnchorPane();
        // Set container into the scrolling pane
        scrollPane.setContent(dialogContainer);

        // add all components into the main layout
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        mainLayout.setPrefSize(600.0, 600.0);

        scrollPane.setPrefSize(600, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
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


        userInput.setPrefWidth(545.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        return new Scene(mainLayout);
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
    private void handleUserInput() {
        Label userText = getDialogLabel(userInput.getText());
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(userText, new ImageView(userImage))
        );
        this.alice.execute(userInput.getText());
        userInput.clear();
    }

    /**
     * Print the list of tasks in this chat page
     */
    public void showCurrentList() {
        Label currentList = getDialogLabel(alice.getUi().getCurrentList());
        dialogContainer.getChildren().add(DialogBox.getAliceDialog(currentList, new ImageView(aliceImage)));
    }

    /**
     * Print error to this chat page
     *
     * @param e exception to be print
     */
    public void printError(Exception e) {
        Label errorText = getDialogLabel(Ui.getErrorText(e));
        dialogContainer.getChildren().add(new DialogBox(errorText, new ImageView(aliceImage)));
    }

    /**
     * Explicitly ask Alice to print something into this chat page
     *
     * @param input string input to be printed
     */
    public void printAlicely(String input) {
        Label textToPrint = getDialogLabel(input);
        dialogContainer.getChildren().add(DialogBox.getAliceDialog(textToPrint, new ImageView(aliceImage)));
    }

    /**
     * Exit the chat page and go back to the start page
     */
    public void exit() {
        stage.setTitle("Alice");
        // Add the scene to the stage
        stage.setScene(new StartPage().layout());
        // Display
        stage.show();
    }

}
