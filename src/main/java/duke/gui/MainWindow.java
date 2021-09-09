package duke.gui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Main Window
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private final Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));

    /**
     * Settiing the Duke within main window
     *
     * @param d Duke
     */
    public void setDuke(Duke d) {
        this.duke = d;
    }

    /**
     * Set the auto scroll functionality
     */
    public void setScrollPane () {
        dialogContainer.heightProperty()
                .addListener((observable) -> scrollPane.setVvalue(1.0));
        dialogContainer.autosize();

    }

    /**
     * Startup message
     */
    @FXML
    public void initialize() {
        String welcomeMessage = "Hello! I am Duke.\nWhat can I do for you?";
        this.dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Handles when user either press enter or enter bay
     *
     * @throws InterruptedException
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String userStringInput = this.userInput.getText();
        String dukeStringResponse = duke.guiProcess(userStringInput);
        addUserDukeResponseIntoContainer(userStringInput, dukeStringResponse);
        checkExit();
    }

    /**
     * Add user and duke response into dialog container
     *
     * @param userStringInput user input
     * @param dukeStringInput  duke response to user input
     * @throws InterruptedException exception when error with delay
     */
    private void addUserDukeResponseIntoContainer(String userStringInput, String dukeStringInput) throws InterruptedException {

        this.dialogContainer.getChildren().add(DialogBox.getUserDialog(userStringInput, userImage));
        Thread.sleep(200);
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(dukeStringInput, dukeImage));
        this.userInput.clear();
    }

    /**
     * Check whether to continue duke operations
     */
    private void checkExit() {
        if (duke.checkIsBye()) {
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(Platform::exit);
        }
    }


    /**
     * Handles when tasklist button pressed
     */
    @FXML
    private void handleGetList()  {
        String getList = "list";
        String response = duke.guiProcess(getList);
        HBox list = DialogBox.getDukeDialog(response, dukeImage);
        list.setMinWidth(scrollPane.getWidth());

        this.dialogContainer.getChildren().add(list);
    }

    /**
     * Handles when help button pressed
     */
    @FXML
    private void handleGetHelp() {
        String getList = "help";
        String response = duke.guiProcess(getList);
        this.dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, dukeImage));
    }


}
