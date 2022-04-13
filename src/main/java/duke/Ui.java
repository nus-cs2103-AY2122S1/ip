package duke;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class will handle the UI via which the user will interact with Duke.
 */
public class Ui extends Application {
    private static final String LOGO = "Hello from\n"
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String DIVIDER =
            "    ____________________________________________________________";
    private static final String GREETING = "     Hello! I'm Duke\n"
            + "     What can I do for you?\n";

    @Override
    public void start(Stage stage) {
        // stage design
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(500.0);
        stage.setMinWidth(500.0);
        // window design
        AnchorPane window = new AnchorPane();
        window.setPrefSize(500.0, 500.0);
        // set scene
        Scene scene = new Scene(window);
        stage.setScene(scene);
        // scroll design
        ScrollPane scroll = new ScrollPane();
        scroll.setPrefSize(500, 485);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.setVvalue(1.0);
        scroll.setFitToWidth(true);
        AnchorPane.setTopAnchor(scroll, 1.0);
        // textField design
        TextField textField = new TextField();
        textField.setPrefWidth(500.0);
        AnchorPane.setLeftAnchor(textField, 1.0);
        AnchorPane.setBottomAnchor(textField, 1.0);
        // set scroll and textField as part of window
        window.getChildren().addAll(scroll, textField);
        // chatRecords design
        VBox chatRecords = new VBox();
        chatRecords.setPrefHeight(Region.USE_COMPUTED_SIZE);
        chatRecords.heightProperty().addListener((observable) -> scroll.setVvalue(1.0));
        // set chatRecords as part of scroll
        scroll.setContent(chatRecords);
        // shows the stage
        stage.show();
        // handles user input
        Parser parser = new Parser("./data/duke.txt");
        // print out welcome message
        chatRecords.getChildren().add(new Label(LOGO + wrapReply(GREETING)));
        //event listener for the text being entered
        textField.setOnAction((event) -> {
            String input = textField.getText();
            chatRecords.getChildren().add(sendToDuke(input));
            textField.clear();
            String message = parser.parse(input);
            if (message == null) {
                Platform.exit();
            } else {
                chatRecords.getChildren().add(sendToDuke(wrapReply(message)));
            }
        });
    }

    private Label sendToDuke(String text) {
        Label reply = new Label(text);
        reply.setWrapText(true);
        return reply;
    }

    /**
     * Wraps any message within a formatted box.
     *
     * @param content The message to be shown to the user.
     */
    public static String wrapReply(String content) {
        return DIVIDER + "\n" + content + DIVIDER;
    }
}