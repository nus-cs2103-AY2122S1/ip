package lania;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lania.command.Command;
import lania.exception.LaniaException;
import lania.task.TaskList;

/**
 * Represents the chatbot Lania that helps manage your tasks.
 */
public class Lania extends Application {

    /** Contains the task list */
    private TaskList tasks;
    /**  Deals with interactions with the user */
    private Ui ui;
    /** Deals with loading tasks from the file and saving tasks in the file */
    private Storage storage;
    /** Deals with making sense of the user command */
    private Parser parser;

    public Lania() {
        ui = new Ui();
        storage = new Storage("data/lania.txt");
        parser = new Parser();
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError();
            e.printStackTrace();
        }
    }

    /**
     * Constructor for the Lania object.
     *
     * @param filePath The location of the file in which data is stored
     */
    public Lania(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
    }

    /**
     * Main part of the program. First, it tries to load the file
     * if it exists and display its contents. It then copies the
     * contents over into a TaskList.
     *
     * After greeting the user, the
     * program continuously waits for input unless the command 'bye'
     * is given which closes exits the program.
     *
     */
    public void run() {
        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showError();
            e.printStackTrace();
        }
        ui.showListMessage(tasks);
        ui.showGreetingMessage();
        boolean isExit = false;
        Scanner s = new Scanner(System.in);
        while (!isExit) {
            try {
                String input = s.nextLine();
                Command c = parser.parse(input);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (LaniaException e) {
                ui.showLaniaException(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeException();
            }
        }
        s.close();
    }

    /**
     * Lania object is created and starts running.
     *
     * @param args The command line arguments. Not required here.
     **/
    public static void main(String[] args) {
        Lania lania = new Lania("data/lania.txt");
        lania.run();
    }

    @Override
    public void start(Stage stage) {
        Gui gui = new Gui(stage, tasks, ui, storage, parser);
        gui.runGui();
    }
}
