package side.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import side.util.TaskList;

/**
 * Encapsulates a SideGui that is used to load in GUI on launching the application.
 *
 * @author Lua Yi Da
 */

public class SideGui extends Application {

    /**
     * Loads in elements to be shown upon launching the application.
     *
     * @param stage Stage to be shown to user.
     */
    @Override
    public void start(Stage stage) {
        TaskList taskList = new TaskList();

        taskList.retrieve();

        try {
            Image sideImage = new Image(this.getClass().getResourceAsStream("/images/side.png"));
            FXMLLoader fxmlLoader = new FXMLLoader(SideGui.class.getResource("/view/GuiWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            stage.setTitle("Side");
            stage.getIcons().add(sideImage);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<GuiWindow>getController().setTaskList(taskList);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

