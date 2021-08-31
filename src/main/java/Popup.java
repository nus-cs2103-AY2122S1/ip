import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {

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
        pain.setCenter(listView);

        Scene scene = new Scene(pain);
        window.setScene(scene);
        window.show();
    }
}
