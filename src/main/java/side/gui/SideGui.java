package side.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import side.util.TaskList;

public class SideGui extends Application {

    @Override
    public void start(Stage stage) {
        TaskList taskList = new TaskList();

        taskList.retrieve();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SideGui.class.getResource("/view/GuiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<GuiWindow>getController().setTaskList(taskList);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

