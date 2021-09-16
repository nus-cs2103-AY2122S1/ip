package jwbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import jwbot.data.exception.JwBotException;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private JwBot jwBot = new JwBot("jwbot.txt");

    @Override
    public void start(Stage stage) throws JwBotException {
        assert stage != null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJwBot(jwBot);
            stage.show();
        } catch (IOException e) {
            throw new JwBotException("Bro, there was an error with starting!");
        }
    }
}
