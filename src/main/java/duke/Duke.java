package duke;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



public class Duke extends Application {
    private static Parser parser;
    private static Storage storage;


    /**
     * Creates Duke object
     *
     */
    public Duke(String fileName) {
        storage = new Storage(fileName);
        try {
            storage.loadTasks();
        } catch (IOException e) {
            Ui.showLoadingError();
        }
        parser = new Parser(storage.getTasks(), storage);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
    /**
     * Runs the main logic of program
     *
     */
    public void run() {
        Ui.welcome();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            if (parser.isExit(input)) {
                Ui.bye();
                break;
            } else {
                try {
                    String[] results = parser.parseInput(input);
                    Ui.printAll(results);
                } catch (Exception e) {
                    continue;
                }
            }
        }
    }


    /**
     * Runs program
     *
     */
    public static void main(String[] args) {
        new Duke("duke.txt").run();

    }

    public String[] getResponse(String input) {
        String[] results = parser.parseInput(input);
        return results;
    }
}
