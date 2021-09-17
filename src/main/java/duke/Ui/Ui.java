package duke.Ui;
import duke.Duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.image.Image;

/**
 * Ui bridges the user and the software, providing output, input and error channels
 */
public class Ui extends Application {
    private ScrollPane scrollPane = new ScrollPane();
    private VBox dialogContainer = new VBox();
    private TextField userInputField = new TextField();
    private Button sendButton = new Button("Send");
    private Image userImg = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImg = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Scene scene;
    private Duke duke;

    @Override
    public void start(Stage stage) throws Exception {
        this.duke = new Duke();
        this.scrollPane.setContent(this.dialogContainer);
        this.duke.init();
        this.dialogContainer.
                getChildren().add(
                    DialogBox.DukeDialogBox("hi i'm Duke, what do you want?",
                            this.dukeImg));

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(this.scrollPane, this.userInputField, this.sendButton);

        this.scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);
        this.scrollPane.setPrefSize(385, 535);
        this.scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.scrollPane.setVvalue(1.0);
        this.scrollPane.setFitToWidth(true);
        this.dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        this.userInputField.setPrefWidth(325.0);
        this.sendButton.setPrefWidth(55.0);
        AnchorPane.setTopAnchor(this.scrollPane, 1.0);
        AnchorPane.setBottomAnchor(this.sendButton, 1.0);
        AnchorPane.setRightAnchor(this.sendButton, 1.0);
        AnchorPane.setLeftAnchor(this.userInputField , 1.0);
        AnchorPane.setBottomAnchor(this.userInputField, 1.0);

        this.sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        this.userInputField.setOnAction((event) -> {
            handleUserInput();
        });

        this.dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String userInput = this.userInputField.getText();
        String dukeResponse = this.duke.getResponse(userInput);
        this.dialogContainer.getChildren().addAll(
                DialogBox.UserDialogBox(userInput, userImg),
                DialogBox.DukeDialogBox(dukeResponse, dukeImg)
        );
        this.userInputField.clear();
    }

    /*
    public static void main(String[] args) {
        launch();
    }
     */
}