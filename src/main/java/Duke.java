import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class runs a personal assistant chatbot that helps a person keep track of various tasks.
 * Commands for the bot are: list, mark, delete, bye, event, deadline, and todo.
 * Unrecognised command will be met with a prompt to enter a recognised one instead.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initiates the chat bot by loading the given file.
     *
     * @param filepath The location of the file to load.
     */
    public Duke(String filepath) {
        ui = new Ui("Elsa");
        storage = new Storage(filepath);

        try {
            tasks = new TaskList(storage.loadListData());
        } catch (FileNotFoundException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Starts the chat bot's interactions with the user.
     */
    public void run() {
        ui.printWelcomeMessage();

        while (true) {
            try {
                String input = ui.getInput();
                String command = ui.receiveUserCommand(input);

                if (command.equals("bye")) {
                    break;
                }

                ui.printSeparator();
                tasks.performCommand(command, input);
                ui.printSeparator();

                storage.saveTasksToFile(tasks.getTasks());

            } catch (InvalidCommandException e) {
                ui.printException("InvalidCommand");
            } catch (IOException e) {
                ui.printException("IOException");
            } catch (InvalidTaskException e) {
                ui.printException("InvalidTask");
            } catch (MissingTaskException e) {
                ui.printException("MissingTask");
            } catch (MissingTimeException e) {
                ui.printException("MissingTime");
            } catch (DateTimeParseException e) {
                ui.printException("DateTimeParse");
            }
        }

        ui.printBye();
    }


    /**
     * Initializes the chatbot.
     */
    public static void main(String[] args) {
        new Duke("data/duke_list_data.txt").run();
    }
}
