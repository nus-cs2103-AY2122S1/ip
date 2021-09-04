package duke;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import static java.lang.Thread.sleep;
import static javafx.application.Platform.exit;


public class Duke{
//    Duke dukeObject = new Duke("data/duke.txt");
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
//    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
//    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//
//    @Override
//    public void start(Stage stage) {
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        AnchorPane mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//        dialogContainer.getChildren().add(DialogBox.getDukeDialog(new Label("hihi"), new ImageView(this.duke)));
//
//
//        stage.setTitle("Duke");
//        stage.setResizable(false);
//        stage.setMinHeight(600.0);
//        stage.setMinWidth(400.0);
//
//        mainLayout.setPrefSize(400.0, 600.0);
//
//        scrollPane.setPrefSize(385, 535);
//        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        scrollPane.setVvalue(1.0);
//        scrollPane.setFitToWidth(true);
//
//        // You will need to import `javafx.scene.layout.Region` for this.
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 1.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 1.0);
//        AnchorPane.setRightAnchor(sendButton, 1.0);
//
//        AnchorPane.setLeftAnchor(userInput , 1.0);
//        AnchorPane.setBottomAnchor(userInput, 1.0);
//
//        scene = new Scene(mainLayout);
//
//        stage.setScene(scene);
//        stage.show();
//
//        sendButton.setOnMouseClicked((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//
//        userInput.setOnAction((event) -> {
//            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
//            userInput.clear();
//        });
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//
//
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//
//        });
//
//    }
//
//    /**
//     * Iteration 2:
//     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
//     * the dialog container. Clears the user input after processing.
//     */
//    private void handleUserInput() {
//        Label userText = new Label(userInput.getText());
//        Label dukeText = new Label(getResponse(userInput.getText()));
//        dialogContainer.getChildren().addAll(
//                DialogBox.getUserDialog(userText, new ImageView(user)),
//                DialogBox.getDukeDialog(dukeText, new ImageView(duke))
//        );
//        userInput.clear();
//    }
//
//    private void greet(Duke dukeObject) {
//        Label greeting = new Label(dukeObject.turnOn());
//        dialogContainer.getChildren().addAll(
//                DialogBox.getDukeDialog(greeting, new ImageView(duke))
//        );
//    }
//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    private String getResponse(String input) {
//        System.out.println("hihi");
//        return dukeObject.returnOutput(input);
////        return "hi";
//    }
//
//    /**
//     * Iteration 1:
//     * Creates a label with the specified text and adds it to the dialog container.
//     * @param text String containing text to add
//     * @return a label with the specified text that has word wrap enabled.
//     */
//    private Label getDialogLabel(String text) {
//        // You will need to import `javafx.scene.control.Label`.
//        Label textToAdd = new Label(text);
//        textToAdd.setWrapText(true);
//
//        return textToAdd;
//    }
//
//
//    public static class DialogBox extends HBox {
//
//        private Label text;
//        private ImageView displayPicture;
//
//        public DialogBox(Label l, ImageView iv) {
//            text = l;
//            displayPicture = iv;
//
//            text.setWrapText(true);
//            displayPicture.setFitWidth(100.0);
//            displayPicture.setFitHeight(100.0);
//
//            this.setAlignment(Pos.TOP_RIGHT);
//            this.getChildren().addAll(text, displayPicture);
//
//        }
//        /**
//         * Flips the dialog box such that the ImageView is on the left and text on the right.
//         */
//        private void flip() {
//            this.setAlignment(Pos.TOP_LEFT);
//            ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
//            FXCollections.reverse(tmp);
//            this.getChildren().setAll(tmp);
//        }
//
//        public static DialogBox getUserDialog(Label l, ImageView iv) {
//            return new DialogBox(l, iv);
//        }
//
//        public static DialogBox getDukeDialog(Label l, ImageView iv) {
//            var db = new DialogBox(l, iv);
//            db.flip();
//            return db;
//        }
//    }



    /**
     * Constructor for a new Duke object.
     *
     * @param filePath File path for the storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        taskList = storage.loadTask();
        ui = new Ui(storage, taskList);
    }

    public Duke() {
        new Duke("data/duke.txt");
    }
    /**
     * Executes the Duke object.
     */
    public void run() {
        System.out.println(returnOutput("hehehe"));
        boolean isExit = false;
        ui.greet();
        taskList.printTask();
        while (!isExit) {
            String input = ui.readLine();
            ui.handleInput(input);
            ui.showLine();
            isExit = ui.handleExit();
        }
    }
    public String turnOn() {
        return ui.greet() + taskList.printTask();
    }
    public String returnOutput(String input) {
//        System.out.println(ui.handleInput(input));

        return ui.handleInput(input);
    }

    /**
     * Creates new Dukc object and runs it.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }


}