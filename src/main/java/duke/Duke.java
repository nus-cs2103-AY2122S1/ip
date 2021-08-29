package duke;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *  This class is the entry point to all other classes,
 *  also includes GUI.
 *
 * @author Ryan Tian Jun.
 */
public class Duke extends Application {
    private Storage storage;
    private Intro intro;
    private Farewell farewell;

    public Duke() {
        this.intro = new Intro();
        this.farewell = new Farewell();
    }


    /**
     * Loads list (if any) stored locally.
     */
    private void loadStoredList() {
        String filePath = "data/duke.txt";
        this.storage = new Storage(filePath);
    }

    /**
     * Driver method for Java GUI.
     *
     * @param stage Takes in a stage to display the object.
     */
    @Override
    public void start(Stage stage) {
        loadStoredList();
        Label label = new Label(this.intro.printIntro() + "\n" + Storage.getCommandResult());

        TextField userInput = new TextField();

        // create a tile pane, and sets the sizes of all nodes
        GridPane gridPane = new GridPane();
        ScrollPane sp = new ScrollPane(label);
        gridPane.add(sp, 1 , 2);
        gridPane.setPrefWidth(400);
        gridPane.setPrefHeight(400);
        gridPane.setAlignment(Pos.TOP_CENTER);
        GridPane.setRowIndex(userInput, 0);
        GridPane.setColumnIndex(userInput, 1);
        userInput.setPrefWidth(375);
        sp.setPrefWidth(375);
        sp.setMinHeight(20);
        label.prefWidthProperty().bind(sp.widthProperty().subtract(2));
        label.setWrapText(true);
        userInput.setPromptText("Input Commands here!");

        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String command = userInput.getText();
                if (command.toLowerCase().equals("bye")) {
                    TaskList.saveList();
                    label.setText(farewell.printFarewell() + "\n" + " ");
                } else {
                    Ui feature = new Feature(command);
                    String commandResult = feature.getCommandResult();
                    label.setText(commandResult);
                }
                userInput.clear();
            }
        };

        userInput.setOnAction(event);
        gridPane.getChildren().add(userInput);
        gridPane.getChildren().add(label);

        //styles
        gridPane.setStyle("-fx-background-color: E5CBB1;"
                + "-fx-background-radius: 0.0;"
                + "-fx-padding: 10;"
                + "-fx-hgap: 10;"
                + "-fx-vgap: 10;");
        userInput.setStyle("-fx-background-color: E5CBB1,-fx-text-box-border, -fx-background ;"
                + "-fx-background-insets: 0, 0 0 0 0 ;"
                + "-fx-background-radius: 10 ;"
                + "-fx-font: normal 20px Verdana");
        label.setStyle("-fx-background-color: E5CBB1,-fx-text-box-border ;"
                + "-fx-background-insets: 0, 0 0 0 0 ;"
                + "-fx-background-radius: 0 ;"
                + "-fx-font: normal 12px Helvetica");


        Scene scene = new Scene(gridPane);
        stage.setScene(scene);
        stage.show();
    }
}
