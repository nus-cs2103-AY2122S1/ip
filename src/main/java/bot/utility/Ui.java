package bot.utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bot.constants.GlobalStringFormats;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents a UI that the user interacts with.
 */
public class Ui {
    private static final String TAB_SPACES = GlobalStringFormats.TAB_SPACES;
    private Stage stage;

    protected static String greet() {
        Date localDate = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String localTime = timeFormat.format(localDate);
        String msg = "I'm IntelliBot. What can I do for you today?";

        if ("05:00:00".compareTo(localTime) <= 0 && localTime.compareTo("12:00:00") < 0) {
            return "\nGood morning!\n" + msg;
        } else if ("12:00:00".compareTo(localTime) <= 0 && localTime.compareTo("18:00:00") < 0) {
            return "\nGood afternoon!\n" + msg;
        } else {
            return "\nGood evening!\n" + msg;
        }
    }

    /**
     * Starts the Ui that the user interacts with.
     *
     * @param stage A stage used for interaction with the user.
     * @param parser A parser that is used to parse user inputs for the controller of the AnchorPane.
     */
    public void start(Stage stage, Parser parser) {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(IntelliBot.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<MainWindow>getController().setUp(this, parser);
            assert ap != null;

            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("IntelliBot");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void close() {
        stage.close();
    }
}
