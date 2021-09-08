import exceptions.MorganException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String STYLE_SCROLL_PANE = "-fx-background: FEF7FC;";
    private static final String STYLE_INPUT_BOX = "-fx-background-color: FDE7FE;"
            + "-fx-border-color: A1A191";
    private Morgan morgan = new Morgan();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image morganImage = new Image(this.getClass().getResourceAsStream("/images/Morgan.png"));

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        scrollPane.setStyle(STYLE_SCROLL_PANE);
        userInput.setStyle(STYLE_INPUT_BOX);
        userInput.setOpacity(0.8);
        sendButton.setStyle(STYLE_INPUT_BOX);
        sendButton.setOpacity(0.8);

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Morgan");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 5.0);
        AnchorPane.setRightAnchor(sendButton, 5.0);
        AnchorPane.setLeftAnchor(userInput, 5.0);
        AnchorPane.setBottomAnchor(userInput, 5.0);

        dialogContainer.getChildren().add(DialogBox.getMorganDialog(morgan.getGreeting(), morganImage));

        try {
            this.morgan.loadData();
        } catch (MorganException e) {
            dialogContainer.getChildren().add(DialogBox.getMorganDialog(e.getMessage(), morganImage));
        }

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String morganText = morgan.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getMorganDialog(morganText, morganImage)
        );
        userInput.clear();

        if (morgan.isExit(userText)) {
            Platform.exit();
        }
    }
}
