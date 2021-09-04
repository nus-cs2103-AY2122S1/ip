package duke;

import duke.controllers.AppWindow;
import duke.exceptions.AuguryException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The {@code App} class starts the GUI for Augury.
 */
public class App extends Application {

    private final Augury augury = new Augury("data/tasks.txt");

    /**
     * Creates the GUI for {@code Augury}.
     */
    @Override
    public void start(Stage stage) throws AuguryException {
        augury.init();
        AppWindow app = new AppWindow(augury);
        Scene scene = new Scene(app);
        scene.getStylesheets().add("/styles/augury.css");
        stage.getIcons().add(new Image(App.class.getResourceAsStream("/images/icon.png")));
        stage.setScene(scene);
        stage.setTitle("Augury");
        stage.show();
    }
}
