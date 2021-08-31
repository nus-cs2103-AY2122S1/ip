package duke;

import java.io.IOException;

import duke.commands.Command;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * Container for Duke program.
 */
public class Duke extends Application {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    /**
     * Instantiates a Duke object.
     *
     * @param filePath String of file path of the previously saved task.txt file (if any).
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }

    /**
     * Executes the Duke program.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();

            } catch (DukeException e) {
                ui.printError(e.toString());
            }
        }
    }

    /**
     * Drives the Duke program.
     *
     * @param args Arguments that user inputs.
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    /**
     * The main entry point for all JavaFX applications. The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param stage the primary stage for this application, onto which the application scene can be set.
     * Applications may create other stages, if needed, but they will not be primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage stage) throws Exception {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}
