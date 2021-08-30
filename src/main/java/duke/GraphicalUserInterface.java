package duke;

import duke.task.TaskList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * Class responsible for setting up GUI for Duke.
 *
 * @author Aiken Wong
 */
public class GraphicalUserInterface {

    private Stage stage;
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private AnchorPane mainLayout;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Instantiates a new Graphical User Interface for Duke.
     *
     * @param stage
     * @param ui
     * @param tasks
     * @param storage
     */
    public GraphicalUserInterface(Stage stage, Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;

        ui.addGui(this);


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

    /**
     * Display GUI and greet user.
     */
    public void showGui() {
        stage.show();
        ui.greet();
    }


    private void handleUserInput() {

        String userInputText = userInput.getText();
        Label userTextLabel = new Label(userInputText);
        userTextLabel.setPadding(new Insets(0, 10, 0, 0));
        ImageView userImage = new ImageView(user);
        clipImageViewToCircle(userImage, 100);
        DialogBox userDialogBox = DialogBox.getUserDialog(userTextLabel, userImage);
        userDialogBox.setPadding(new Insets(0, 0, 30, 0));
        setDialogBoxBackgroundColor(userDialogBox, "#1FDA12");

        dialogContainer.getChildren().add(
            userDialogBox
        );

        try {
            Parser.parse(userInputText, ui, tasks, storage).execute();
        } catch (DukeException e) {
            ui.showException(e);
        }


        userInput.clear();
    }

    /**
     * Displays a given string as a duke reply in the GUI.
     *
     * @param dukeReply The given string for duke to reply with.
     */
    public void displayDukeReply(String dukeReply) {
        Label dukeTextLabel = new Label(dukeReply);
        dukeTextLabel.setPadding(new Insets(0, 0, 0, 10));

        ImageView dukeImage = new ImageView(duke);
        clipImageViewToCircle(dukeImage, 100);

        DialogBox dukeDialogBox = DialogBox.getDukeDialog(dukeTextLabel, dukeImage);
        dukeDialogBox.setPadding(new Insets(0, 0, 30, 0));

        setDialogBoxBackgroundColor(dukeDialogBox, "#12B1DA");

        dialogContainer.getChildren().add(dukeDialogBox);
    }


    private void clipImageViewToCircle(ImageView imageView, int length) {
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        Circle circle = new Circle(length / 2, length / 2, Math.min(length, length) / 2);
        imageView.setClip(circle);
    }

    private void setDialogBoxBackgroundColor(DialogBox dialogBox, String hexValue) {
        dialogBox.setBackground(new Background(new BackgroundFill(Paint.valueOf(hexValue), null, null)));
    }


}
