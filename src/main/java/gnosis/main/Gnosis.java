package gnosis.main;

import java.io.IOException;

import gnosis.controller.GnosisController;
import gnosis.ui.GnosisUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;



/**
 *
 * Gnosis class is the main programme to execute chat-bot assistant.
 *
 * @author Pawandeep Singh

 * */
public class Gnosis extends Application {

    /**
     * Starts creating the Gnosis UI.
     *
     * @param stage Stage to set for GnosisUI.
     */
    @Override
    public void start(Stage stage) {
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(GnosisUI.class.getResource("/gnosis/ui/GnosisMainWindow.fxml"));
            GridPane gridPane = fxmlLoader.load();
            Scene scene = new Scene(gridPane);
            stage.setScene(scene);
            stage.setResizable(false);

            // Set up connection between Gnosis UI and Gnosis Logic Controller
            GnosisUI gnosisView = fxmlLoader.getController();
            GnosisController gnosisController = new GnosisController(gnosisView);
            gnosisView.setUpGnosis(gnosisController);


            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        //When platform.exit() is called
        //stop() will be called before closing application
        System.out.println("Application is closed");
    }
}
