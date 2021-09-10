package ui;

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
 * Prompt Box for user to confirm about his/her action
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.00
 */
public class AlertBox {

    /**
     * Display a prompt window to the user to interact with
     *
     * @param title        the message at the top
     * @param eventHandler eventHandler to execute when user click yes or press enter
     */
    public static void display(String title, EventHandler<ActionEvent> eventHandler) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(title);
        Button yesButton = new Button("Yes");
        yesButton.setDefaultButton(true);
        Button noButton = new Button("No");
        yesButton.setOnAction(e -> {
            eventHandler.handle(e);
            window.close();
        });
        noButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        HBox horizontal = new HBox(10);
        horizontal.setAlignment(Pos.CENTER);
        horizontal.getChildren().addAll(yesButton, noButton);
        layout.getChildren().addAll(label, horizontal);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
