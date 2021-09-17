package lebron.controller;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import lebron.Lebron;

//Solution below adapted from https://se-education.org/guides/tutorials/javaFx.html
//Credit to Jeffry Lum
/**
 * A GUI for Duke using FXML.
 */
public class Bridge extends Application {

    private Lebron lebron;

    @Override
    public void start(Stage stage) {
        lebron = new Lebron();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Bridge.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setLebron(lebron);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
