package ui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Prompt a Box for user to confirm about his/her action.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.00
 */
public class AlertBox {

    /**
     * Display a prompt window to the user to interact with by either clicking yes or no
     * to commence with the passed in event handling or not.
     *
     * @param title        the message at the top.
     * @param eventHandler eventHandler to execute when user click yes or press enter.
     */
    public static void display(String title, EventHandler<ActionEvent> eventHandler) {
        Stage stage = new Stage();
        setUpStage(title, eventHandler, stage);
        stage.showAndWait();
    }

    private static void setUpStage(String title, EventHandler<ActionEvent> eventHandler, Stage stage) {
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(250);
        VBox vBoxLayout = new VBox(10);
        vBoxLayout.setAlignment(Pos.CENTER);
        addTitle(title, vBoxLayout);
        addButtons(eventHandler, stage, vBoxLayout);
        Scene scene = new Scene(vBoxLayout);
        stage.setScene(scene);
    }

    private static void addTitle(String title, VBox vBoxLayout) {
        Label label = new Label();
        label.setText(title);
        label.setStyle("-fx-font-family: Helvetica");
        vBoxLayout.getChildren().add(label);
    }

    private static void addButtons(EventHandler<ActionEvent> eventHandler, Stage stage, VBox vBoxLayout) {
        HBox horizontal = new HBox(10);
        Button yesButton = new Button("Yes");
        yesButton.setStyle("-fx-font-family: Helvetica");
        yesButton.setDefaultButton(true);
        Button noButton = new Button("No");
        noButton.setStyle("-fx-font-family: Helvetica");
        yesButton.setOnAction(e -> {
            eventHandler.handle(e);
            stage.close();
        });
        noButton.setOnAction(e -> stage.close());
        horizontal.setAlignment(Pos.CENTER);
        horizontal.getChildren().addAll(yesButton, noButton);
        vBoxLayout.getChildren().add(horizontal);
    }

}
