package addon;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {

    private static final Insets INSETS = new Insets(10, 10, 10, 10);
    /**
     * Creates a popup window with the given message and an acknowledgement button.
     *
     * @param message Message to be displayed.
     */
    public static void errorPopup(String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Error");
        window.setMinWidth(250);

        Label errorMessage = new Label();
        errorMessage.setText(message);
        Button back = new Button("Ok");
        back.setOnAction(a -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(errorMessage, back);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }

    static boolean returnBoolean;

    /**
     * Creates a popup window with the given message and an confirm and cancel button.
     *
     * @param message String representation of the task to be deleted.
     * @return boolean True if user clicks confirm.
     */
    public static boolean confirmationPopup(String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm action");
        window.setMinWidth(250);

        Label confirmationMessage = new Label();
        confirmationMessage.setText("Are you sure you want to delete the following task:\n"+ message);
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.setPadding(INSETS);
        Button confirm = new Button("Yep");
        confirm.setOnAction(a -> {
            returnBoolean = true;
            window.close();
        });
        Button back = new Button("Nep");
        back.setOnAction(a -> window.close());
        buttons.getChildren().addAll(confirm, back);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(10);
        layout.setPadding(INSETS);
        layout.getChildren().addAll(confirmationMessage, buttons);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
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
        Stage window = new Stage();

        ObservableList<String> filteredTasks = FXCollections.observableArrayList();
        ListView<String> listView = new ListView<>(filteredTasks);
        listView.getItems().addAll(list);

        window.setTitle("Filter results for " + str);
        window.setMinWidth(250);

        Label explain = new Label();
        explain.setText("Here are your filtered results for \"" + str + "\"");
        explain.setAlignment(Pos.CENTER);
        BorderPane pain = new BorderPane();
        pain.setTop(explain);
        pain.setPadding(INSETS);
        pain.setCenter(listView);

        Scene scene = new Scene(pain);
        window.setScene(scene);
        window.show();
    }
}
