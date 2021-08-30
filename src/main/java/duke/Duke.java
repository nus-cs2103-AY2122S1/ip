package duke;

import java.util.Scanner;

import java.lang.String;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Duke extends Application {
    private static Ui ui = new Ui();
    private static Storage storage = new Storage();
    private static TaskList taskList = new TaskList();
    public static File file = new File("data/list.txt");

    public static void main(String[] args) {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        storage.checkExistence();

        ui.showFileLocation(file.getAbsolutePath());

        taskList.initialise(file, storage);

        Parser.parse();
    }

    @Override
    public void start(Stage stage) {
        Label allo = new Label("allo"); // Creating a new Label control
        allo.setFont(new Font("Arial",50));
        Scene scene = new Scene(allo); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }


}
