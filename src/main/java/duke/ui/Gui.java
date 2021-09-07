package duke.ui;

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

public class Gui {

    private final String CHATBOT_NAME = "Barry";

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/Barry.jpg"));

    private final double BOX_HEIGHT =  600;
    private final double BOX_WIDTH =  400;
    private final double SCROLL_HEIGHT =  535;
    private final double SCROLL_WIDTH =  385;
    private final double INPUT_BOX_WIDTH =  325;
    private final double BUTTON_WIDTH =  55;

    public Gui() {

    }

    /**
     * Set up Gui environment.
     * @param stage the stage.
     */
    public void start(Stage stage) {

        //Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(BOX_HEIGHT);
        stage.setMinWidth(BOX_WIDTH);

        mainLayout.setPrefSize(BOX_WIDTH, BOX_HEIGHT);

        scrollPane.setPrefSize(SCROLL_WIDTH, SCROLL_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(INPUT_BOX_WIDTH);

        sendButton.setPrefWidth(BUTTON_WIDTH);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        greetUser();
    }

    public Button getButton() {
        return sendButton;
    }

    public TextField getUserTextField() {
        return userInput;
    }

    /**
     * Get user command.
     */
    public String getCommand() {
        String command = userInput.getText();
        Label userText = new Label(command);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user))
        );
        userInput.clear();
        return command;
    }

    /**
     * Show chatbot response.
     * @param response the response to show.
     */
    public void showResponse(String response) {
        Label dukeReply = new Label(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(dukeReply, new ImageView(duke))
        );
    }

    /**
     * Show greet message.
     */
    public void greetUser() {
        showResponse(String.format("Hello! I'm %s \nWhat can I do for you?", CHATBOT_NAME));
    }

    /**
     * Show exit message.
     */
    public void exit() {
        showResponse("Bye");
    }
}
