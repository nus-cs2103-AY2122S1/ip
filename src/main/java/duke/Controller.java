package duke;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Class that handles user inputs into the GUI.
 */
public class Controller {
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private ScrollPane scrollPane;

    private String command;
    private MyList list;
    private Storage storage;
    private Image dukeIcon;
    private Image userIcon;
    private Image backgroundImage;
    /**
     * Constructor for the controller.
     */
    public Controller() {
        try {
            dukeIcon = new Image(this.getClass().getResourceAsStream("/dukeIcon.png"));
            userIcon = new Image(this.getClass().getResourceAsStream("/userIcon.png"));
        } catch (Exception ee) {
            System.out.println("rip welcome message");
        }
        this.list = new MyList();
        this.storage = new Storage(this.list, "./Data.txt");
        storage.load();
    }

    /**
     * Adds the welcome message to the GUI as soon as duke is started.
     */
    public void initialize() {
        addWelcomeMessage();
    }

    /**
     * Handles the user input when pressing enter or clicking the send button.
     * @param e
     */
    public void handleUserInput(ActionEvent e) {
        this.command = this.userInput.getText();
        this.userInput.setText("");

        if (this.command.equals("bye")) {
            closeGui();
        }

        if (!this.command.equals("")) {
            dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
            this.dialogContainer.getChildren().addAll(
                    new DialogBox(getUserDialog(), new ImageView(userIcon), true),
                    new DialogBox(getDukeDialog(), new ImageView(dukeIcon), false)
            );
        }

    }

    /**
     * Returns the label containing the user's input.
     * @return The label containing the user's input.
     */
    public Label getUserDialog() {
        Label textToAdd = new Label(this.command);
        return textToAdd;
    }

    /**
     * Returns the label containing duke's response.
     * @return The label containing duke's response.
     */
    public Label getDukeDialog() {
        Parser p = new Parser(this.list, this.storage);
        String response = p.getDukeResponse(this.command);
        Label textToAdd = new Label(response);
        return textToAdd;

    }

    /**
     * Creates the welcome message to be displayed on the GUI everytime duke is started.
     * @return The label to be added to the scene.
     */
    public Label getWelcomeMessage() {
        Label textToAdd = new Label(Ui.getWelcomeMessage());
        return textToAdd;
    }

    /**
     * Adds the welcome message dialog box into the gui.
     */
    public void addWelcomeMessage() {
        this.dialogContainer.getChildren().addAll(
                new DialogBox(getWelcomeMessage(), new ImageView(dukeIcon), false)
        );
    }

    /**
     * Closes the GUI.
     */
    public void closeGui() {
        Stage stage = (Stage) this.scrollPane.getScene().getWindow();
        stage.close();
    }

}
