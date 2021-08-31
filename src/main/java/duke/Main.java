package duke;
/*
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    private static final double GUI_WIDTH = 400.0;
    private static final double GUI_HEIGHT = 600.0;
    private static final double BOTTOM_BAR_HEIGHT = 40.0;
    private static final double TEXT_INPUT_WIDTH = 325.0;
    private static final double SEND_BUTTON_WIDTH = GUI_WIDTH - TEXT_INPUT_WIDTH;
    private static final double SCROLL_PLANE_HEIGHT = GUI_HEIGHT - BOTTOM_BAR_HEIGHT;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    @Override
    public void start(Stage stage) {
        // Set the size of the window
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(GUI_HEIGHT);
        stage.setMinWidth(GUI_WIDTH);

        // Set up dialog container
        dialogContainer = new VBox();
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        // Set up scroll view
        scrollPane = new ScrollPane();
        scrollPane.setPrefSize(GUI_WIDTH, SCROLL_PLANE_HEIGHT);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(dialogContainer);

        // Set up user input
        userInput = new TextField();
        userInput.setFont(new Font(12));
        userInput.setPrefWidth(TEXT_INPUT_WIDTH);
        userInput.setPrefHeight(BOTTOM_BAR_HEIGHT);

        // Set up send button
        sendButton = new Button("Send");
        sendButton.setFont(new Font(16));
        sendButton.setPrefWidth(SEND_BUTTON_WIDTH);
        sendButton.setPrefHeight(BOTTOM_BAR_HEIGHT);


        // Setup anchor panel
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        mainLayout.setPrefSize(GUI_WIDTH, GUI_HEIGHT);

        // Set anchor
        AnchorPane.setTopAnchor(dialogContainer, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setLeftAnchor(userInput , 1.0);

        // set up scene
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        // Set up interactions
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
    }

    private Label getDialogLabel(String text) {
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, new ImageView(user)),
                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
*/

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}