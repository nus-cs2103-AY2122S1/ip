package front;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {

    private static final Insets INSETS = new Insets(10, 10, 10, 10);
    private static boolean returnBoolean;
    private static Image backIcon = new Image(Popup.class.getResourceAsStream("/images/back.png"), 16,
            16, true, true);
    private static Image yesIcon = new Image(Popup.class.getResourceAsStream("/images/yes.png"), 16,
            16, true, true);
    private static Image cancelIcon = new Image(Popup.class.getResourceAsStream("/images/cancel.png"),
            16, 16, true, true);

    /**
     * Creates a popup window with the given message and an acknowledgement button.
     *
     * @param message Message to be displayed.
     */
    public static void errorPopup(String message) {
        Stage errorStage = new Stage();
        errorStage.getIcons().add(cancelIcon);

        errorStage.initModality(Modality.APPLICATION_MODAL); //Have to be dealt with.
        errorStage.setTitle("Error");
        errorStage.setMinWidth(250);

        Label errorMessage = new Label();
        errorMessage.setText(message);
        Button back = new Button("Ok");
        back.setGraphic(new ImageView(yesIcon));
        back.setOnAction(a -> errorStage.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(errorMessage, back);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        errorStage.setScene(scene);
        errorStage.show();
    }

    /**
     * Creates a popup window with the given message and an confirm and cancel button.
     *
     * @param message String representation of the task to be deleted.
     * @return boolean True if user clicks confirm.
     */
    public static boolean confirmationPopup(String message) {
        Stage confirmationStage = new Stage();
        Image confirmationAppIcon = new Image(Popup.class.getResourceAsStream("/images/confirmation.png"));
        confirmationStage.getIcons().add(confirmationAppIcon);

        confirmationStage.initModality(Modality.APPLICATION_MODAL); //Have to be dealt with.
        confirmationStage.setTitle("Confirm action");
        confirmationStage.setMinWidth(250);

        Label confirmationMessage = new Label();
        confirmationMessage.setText(message);
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setPadding(INSETS);
        Button confirm = new Button("Yep");
        confirm.setGraphic(new ImageView(yesIcon));

        confirm.setOnAction(a -> {
            returnBoolean = true;
            confirmationStage.close();
        });
        Button back = new Button("Nep");
        back.setGraphic(new ImageView(cancelIcon));
        back.setOnAction(a -> confirmationStage.close());
        buttons.getChildren().addAll(confirm, back);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.setPadding(INSETS);
        layout.getChildren().addAll(confirmationMessage, buttons);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        confirmationStage.setScene(scene);
        confirmationStage.showAndWait();
        boolean returnBooleanTrue = returnBoolean;
        returnBoolean = false;
        return returnBooleanTrue;
    }

    /**
     * Creates a popup window with the given message and an acknowledgement button.
     *
     * @param list List of items to be displayed.
     * @param str String representation of the terms used for the filtering.
     */
    public static void filterPopup(ObservableList<String> list, String str) {
        Stage filterStage = new Stage();
        Image filterAppIcon = new Image(Popup.class.getResourceAsStream("/images/filter.png"));
        filterStage.getIcons().add(filterAppIcon);

        ObservableList<String> filteredTasks = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(filteredTasks);
        listView.getItems().addAll(list);

        filterStage.setTitle("Filter results for " + str);
        filterStage.setMinWidth(250);

        Label explain = new Label();
        explain.setText("Here are your filtered results for \"" + str + "\"");
        explain.setAlignment(Pos.CENTER);

        Button exit = new Button("Exit");
        exit.setGraphic(new ImageView(backIcon));
        exit.setOnAction(e -> filterStage.close());
        BorderPane pain = new BorderPane();
        pain.setTop(explain);
        pain.setPadding(INSETS);
        pain.setCenter(listView);
        pain.setBottom(exit);

        Scene scene = new Scene(pain, 450, 150);
        scene.getRoot().setStyle("-fx-font-family: 'Helvetica'");
        filterStage.setScene(scene);
        filterStage.show();
    }
}
