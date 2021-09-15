package whobot.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Gui extends Application {

    private static final long SHORT_TEXT_DELAY = 50;

    private static final long LONG_TEXT_DELAY = 20;

    private static final long SHORT_TEXT_LIMIT = 150;

    public static long getShortTextDelay() {
        return SHORT_TEXT_DELAY;
    }

    public static long getLongTextDelay() {
        return LONG_TEXT_DELAY;
    }

    public static long getShortTextLimit() {
        return SHORT_TEXT_LIMIT;
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
