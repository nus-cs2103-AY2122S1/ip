package retriever;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Retriever retriever = new Retriever();

    /**
     * Starts the GUI interface.
     *
     * @param stage The window shown.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Can modify this line for changing font
            scene.getRoot().setStyle("-fx-font-family: 'Courier New'");

            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRetriever(retriever);
            stage.show();

            // To show a welcome splash screen
            Stage welcomeStage = new Stage();
            Ui ui = new Ui();
            ui.printWelcomeMessage();
            Label welcomeLabel = new Label(ui.getRetrieverResponse());
            welcomeLabel.setFont(new Font("Courier New", 20));
            welcomeLabel.setPadding(new Insets(25, 25, 25, 25));
            Scene welcomeScene = new Scene(welcomeLabel);
            welcomeStage.setScene(welcomeScene);
            welcomeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
