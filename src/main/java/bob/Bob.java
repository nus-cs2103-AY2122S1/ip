package bob;

import java.io.File;

import bob.exception.DirectoryNotFoundException;
import bob.exception.FileNotFoundException;
import bob.gui.MainWindow;
import bob.gui.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A chatbot that stores, displays and alters the user's task list based on the user input.
 */
public class Bob extends Application {
    /** Storage object that deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;

    /** List of user tasks */
    private TaskList tasks;

    /** Ui object that deals with interactions with the user */
    private Ui ui;

    /** Parser object that deals with making sense of the user commands */
    private Parser parser;

    /** Whether the data directory is already present in the user computer */
    private boolean isDirectoryPresent = true;

    /** Whether the bob.txt file is already present within the data directory in the user computer */
    private boolean isBobFilePresent = true;

    /** UI element that contains all the nodes to be shown in the GUI */
    private Scene scene;

    /**
     * Constructor for a new Bob instance.
     */
    public Bob() {
        ui = new Ui();
        storage = new Storage(new File("").getAbsolutePath() + "/data");
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (DirectoryNotFoundException e) {
            isDirectoryPresent = false;
            tasks = new TaskList();
            storage.makeDataDirectory();
            storage.makeBobFile();
        } catch (FileNotFoundException e) {
            isBobFilePresent = false;
            tasks = new TaskList();
            storage.makeBobFile();
        }
    }

    /**
     * Does not do anything.
     *
     * @param args String array that acts as the argument to the main method.
     */
    public static void main(String[] args) {
    }

    /**
     * Sets up the GUI for Bob.
     *
     * @param stage The primary stage provided by JavaFX for the GUI.
     */
    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = new MainWindow(storage, tasks, ui, parser);
        mainWindow.setDarkerBackground();
        scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
        mainWindow.formatWindow(stage);
        mainWindow.setLighterBackground();

        mainWindow.displayGreeting();
        mainWindow.handleMissingDirectoryOrFile(isDirectoryPresent, isBobFilePresent);
        mainWindow.displayReadyMessage();

        mainWindow.handleInput();
        mainWindow.scrollDown();
    }
}
