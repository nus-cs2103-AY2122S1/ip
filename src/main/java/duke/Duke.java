package duke;

import duke.command.Command;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents a {@code Duke} object. {@code Duke} responds to
 * predefined user inputs and outputs various messages in response to the user
 * input.
 *
 * @author Elizabeth Chow
 */
public class Duke {
    private final Ui ui;
    private final Storage storage;
    private TaskList tasks;

    /**
     * Constructs a {@code Duke} object.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Shows a welcome message when {@code Duke} starts running. Continuously takes
     * in user inputs until "bye" is entered.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);

        while (!isExit) {
            try {
                Command c = Parser.parse(scanner.nextLine().trim());
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
        scanner.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
