package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Class includes methods required for running this project.
 */
public class Duke extends Application {
    private Scanner in = new Scanner(System.in);
    private CompilationOfFiles storage;
    private ListOfTasks tasks;
    private Ui ui;

    /**
     * Constructor for creating duke.
     *
     * @param filepath refers to path of the file
     */
    public Duke(String filepath) {
        ui = new Ui();
        storage = new CompilationOfFiles(filepath);
        tasks = new ListOfTasks();
        storage.loadAndSaveFile(tasks);
    }
    /**
     * This method is required for running the file.
     */
    public void run() {
        ui.start();
        boolean isEnd = false;
        while (!isEnd) {
            String command = in.nextLine();
            isEnd = Parser.handleCommand(command, tasks);
        }
        ui.end();
    }
    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * This is the main method.
     *
     * @param args required for main method
     */
    public static void main(String[]args) {
        new Duke("data/duke.txt").run();
    }

}

