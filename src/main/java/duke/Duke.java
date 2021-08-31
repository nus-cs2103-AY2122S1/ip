package duke;
import duke.exceptions.DukeException;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *  Duke class that contains run and main method to run the program
 *
 * @author Chua Sue-Ann
 */
public class Duke extends Application {

    private Storage storage;
    private TaskList taskList;

    /**
     * Public constructor for Duke class. Creates instance of Duke class.
     *
     * @param file File that data will be stored in later.
     * @return Instance of Duke class.
     */
    public Duke(File file) {
        this.storage = new Storage(file);
        try {
            this.taskList = new TaskList();
        } catch (Exception e) {

        }
    }

    public Duke() {}

    /**
     * Run method triggers file to be created and welcomes user with welcome message.
     *
     * @throws DukeException
     */
    public void run() throws DukeException {
        try {
            File dukeFile = new File("data/duke.txt");
            PrintWriter writer  = this.storage.load();

            Ui.showWelcomeMessage();

            Parser.parseCommand(taskList, dukeFile, writer);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!");
        Scene scene = new Scene(helloWorld);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method for program. Creates file path and creates file.
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Files.createDirectories(Paths.get("data/"));
        File dukeFile = new File("data/duke.txt");
        new Duke(dukeFile).run();
    }
}

