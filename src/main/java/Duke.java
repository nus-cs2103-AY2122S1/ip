import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import duke.Chatbot;
import duke.DukeArgumentException;
import duke.DukeDateParseException;
import duke.DukeIoException;
import duke.DukeTaskException;
import javafx.application.Application;
import javafx.application.Platform;
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
import javafx.util.Duration;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    private Chatbot chatbot;
    private boolean isChatting;
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        // initialize chatbot
        // get initial stream
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        this.chatbot = new Chatbot();
        this.isChatting = true;

        // reset stream
        System.out.flush();
        System.setOut(old);

        // initialize stage
        createScene(stage);

        // display stage
        stage.setScene(scene);
        stage.show();

        // setup other items
        setAnchors();
        setListeners();

        // when need to scroll, shows the latest comments
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Chatbot's welcome message
        displaySplashScreenMessage(baos);
    }

    private void displaySplashScreenMessage(ByteArrayOutputStream baos) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label(baos.toString()), new ImageView((duke))));
    }

    private void setListeners() {
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    /**
     * Creates scene for other JavaFX nodes.
     *
     * @param stage the stage of Application.
     */
    private void createScene(Stage stage) {
        // The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        // styling the stage
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(700.0);
        stage.setMinWidth(500.0);

        mainLayout.setPrefSize(500.0, 700.0);

        scrollPane.setPrefSize(500, 700);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(425.0);

        sendButton.setPrefWidth(75.0);
    }

    private void setAnchors() {
        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        assert isChatting == true : "should not chat anymore";
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();

        // closes app when user decides to leave
        if (!isChatting) {
            PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Chats with chatbot.
     *
     * @param input user's input.
     * @return chatbot response.
     */
    private String getResponse(String input) {
        // Captures original output
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream old = System.out;
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);
        String errorMsg = null;
        try {
            isChatting = this.chatbot.guiInterpret(input) == Chatbot.ChatContinue.CHAT_CONTINUE;
        } catch (DukeArgumentException e) {
            errorMsg = e.getMessage();
        } catch (DukeTaskException e) {
            errorMsg = e.getMessage();
        } catch (DukeIoException e) {
            errorMsg = e.toString();
        } catch (DukeDateParseException e) {
            errorMsg = e.toString();
        }
        System.out.flush();
        System.setOut(old);
        assert System.out != ps : "output stream not reset";
        if (errorMsg != null) {
            return errorMsg;
        } else {
            return baos.toString();
        }
    }
}
