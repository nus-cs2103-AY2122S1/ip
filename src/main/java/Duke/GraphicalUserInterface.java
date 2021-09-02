package Duke;

import Duke.task.TaskList;
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

public class GraphicalUserInterface {
    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private AnchorPane mainLayout;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/resources/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/resources/images/DaDuke.png"));
    private TaskList tasks;
    private Storage storage;


    public GraphicalUserInterface(Stage stage, TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);
        userInput = new TextField();
        sendButton = new Button("Send");
        mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        System.out.println(mainLayout);
        scene = new Scene(mainLayout);

        this.stage = stage;
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Duke");
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


    }

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        ImageView userImage = new ImageView(user);
        DialogBox userDialogBox = DialogBox.getUserDialog(userText, userImage);
        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeText, new ImageView(duke));
        dialogContainer.getChildren().addAll(
                userDialogBox, dukeDialogBox
        );
        userInput.clear();
    }

    private String getResponse(String input) {
        Duke duke = new Duke();
        return duke.getResponse(input);
    }

}

