package duke.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class Gui extends Application {
//    private Image user = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/image/user.jpeg")));
//    private Image duke = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/image/duke.jpeg")));
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;
//    private AnchorPane mainLayout;

    @Override
    public void start(Stage stage) {
//        scrollPane = new ScrollPane();
//        dialogContainer = new VBox();
//        scrollPane.setContent(dialogContainer);
//
//
//        userInput = new TextField();
//        sendButton = new Button("Send");
//
//        mainLayout = new AnchorPane();
//        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
//
//        scene = new Scene(mainLayout);
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
//
//        scrollPane.setFitToWidth(true);
//
//        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
//
//        userInput.setPrefWidth(325.0);
//
//        sendButton.setPrefWidth(55.0);
//
//        AnchorPane.setTopAnchor(scrollPane, 13.0);
//        AnchorPane.setLeftAnchor(scrollPane, 8.0);
//
//        AnchorPane.setBottomAnchor(sendButton, 12.0);
//        AnchorPane.setRightAnchor(sendButton, 8.0);
//
//        AnchorPane.setLeftAnchor(userInput , 8.0);
//        AnchorPane.setBottomAnchor(userInput, 12.0);
//
//        sendButton.setOnMouseClicked((event) -> {
//            handleUserInput();
//        });
//
//        userInput.setOnAction((event) -> {
//            handleUserInput();
//        });
//        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
//        stage.setScene(scene);
//        stage.show();
    }

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
//    /**
//     * You should have your own function to generate a response to user input.
//     * Replace this stub with your completed method.
//     */
//    private String getResponse(String input) {
//        return "Duke heard: " + input;
//    }
}
