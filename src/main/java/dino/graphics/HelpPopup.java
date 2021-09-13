package dino.graphics;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HelpPopup {

    // credit http://www.learningaboutelectronics.com/Articles/How-to-create-a-pop-up-window-in-JavaFX.php
    public static void display() {
        Stage popupWindow = new Stage();

        popupWindow.initModality(Modality.APPLICATION_MODAL);
        popupWindow.setTitle("List of commands");

        Label label1 = new Label("Pop up window now displayed Pop up window now displayed Pop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayedPop up window now displayed");
        label1.setWrapText(true);

        Button button1 = new Button("Close the help menu");
        button1.setOnAction(e -> popupWindow.close());

        VBox layout = new VBox(10);

        layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);

        //HBox.setHgrow(label1, Priority.ALWAYS);
        //VBox.setVgrow(label1, Priority.ALWAYS);


        Scene scene1 = new Scene(layout, 300, 250);

        popupWindow.setScene(scene1);
        popupWindow.showAndWait();

    }


}