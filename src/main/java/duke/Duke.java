package duke;

import command.Command;
import exception.DukeException;
import exception.DukeExceptionType;

import java.io.InputStream;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the main Duke program. Running this class's main function executes the program.
 */
public class Duke {

    private final Ui ui;
    private final TaskList taskList;

    private ScrollPane scrollPane;
    private TextField userInput;

    /**
     * Default constructor for the Duke program.
     * Uses filepath data/duke.txt by default.
     */
    public Duke() {
        ui = new Ui();
        Storage storage = new Storage("data", "duke.txt");
        taskList = new TaskList(storage);
    }

    /**
     * Constructor for the Duke program with custom filepath.
     *
     * @param fileDirectory Directory of the hard disk.
     * @param fileName The hard disk.
     */
    public Duke(String fileDirectory, String fileName) {
        ui = new Ui();
        Storage storage = new Storage(fileDirectory, fileName);
        taskList = new TaskList(storage);
    }

    /**
     * Runs the main logic of the Duke program.
     *
     * @param stream The desired stream for the program's input.
     */
    public void run(InputStream stream) {
        // Set up Scanner for input and isBye boolean for while loop
        Scanner input = new Scanner(stream);
        boolean isBye = false;

        // Welcome message
        ui.showWelcome();

        // Continuously takes user input until "bye" command is given
        while (!isBye) {
            try {
                // performing action based on user command
                ui.showInputPrompt();
                ui.readCommand(input);
                String fullCommand = userInput.getText();
                ui.showLine();
                Command c = Parser.parse(fullCommand, taskList);
                c.execute(taskList);
                isBye = c.isBye();

            } catch (DukeException e) {
                ui.showException(e);

            } catch (NumberFormatException e) {
                ui.showException(new DukeException(DukeExceptionType.INVALID_TASK_INDEX));

            } catch (DateTimeParseException e) {
                ui.showException(new DukeException(DukeExceptionType.INVALID_DATETIME));

            } finally {
                ui.showLine();
            }
        }

        input.close();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Command c = Parser.parse(input, taskList);
            return c.execute(taskList);

        } catch (DukeException e) {
            return ui.showException(e);

        } catch (NumberFormatException e) {
            return ui.showException(new DukeException(DukeExceptionType.INVALID_TASK_INDEX));

        } catch (DateTimeParseException e) {
            return ui.showException(new DukeException(DukeExceptionType.INVALID_DATETIME));
        }
    }

    public static void main(String[] args) {
        new Duke().run(System.in);
    }
}
