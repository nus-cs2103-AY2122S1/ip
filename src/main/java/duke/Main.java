package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/mainScene.fxml"));
        Parent root = fxmlLoader.load();
        stage.setTitle("Duke Task Manager");
        stage.setScene(new Scene(root));
        SceneController controller = fxmlLoader.getController();
        controller.intialiseChat(duke);
        stage.show();
    }
}
