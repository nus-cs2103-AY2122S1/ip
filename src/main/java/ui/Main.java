package ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public final class Main extends Application {
    private Image icon;

    @Override
    public void start(Stage stage) {
        initializeIcon();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            FileInputStream inputStream = new FileInputStream("src/main/resources/view/MainWindow.fxml");
            AnchorPane ap = fxmlLoader.load(inputStream);
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Memo-Assistant Duke");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeIcon() {
        FileInputStream dukeMascot;
        try {
            dukeMascot = new FileInputStream("src/main/resources/images/DukeMascot.png");
            icon = new Image(dukeMascot);
        } catch (FileNotFoundException e) {
            System.out.println(getClass() + ": dukeMascot image not found");
        }
    }
}
