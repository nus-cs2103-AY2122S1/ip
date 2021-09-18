package duke;


import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;
import javafx.application.Platform;


/**
 * The class for the chat bot, Duke
 */
public class Duke {

    /** Storage object to save to and load from the data file */
    private Storage storage;
    /** List of all the Tasks */
    private TaskList tasks;
    /** Ui object to handle the UI output to the user */
    private Ui ui;

    /**
     * Constructor for Duke class.
     *
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        tasks = storage.load();
    }

    /**
     * Runs Duke using the CLI.
     */
    private void run() {
        ui.printWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        boolean hasUserEnd = false;

        while (!hasUserEnd) {
            String input = "";
            if (sc.hasNextLine()) {
                input = sc.nextLine().trim();
            }
            if (input.equalsIgnoreCase("bye")) {
                hasUserEnd = true;
                ui.printGoodbyeMessage();
                storage.save(tasks);
            } else {
                try {
                    Command commandObject = parser.parse(input);
                    String message = commandObject.execute(tasks, storage);
                    ui.printMessage(message);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        sc.close();
    }

    /**
     * Gets user's commands and is used by JavaFX to process the user's commands and execute accordingly.
     *
     * @param input The user's input to Duke.
     * @return A String containing the message to be shown to the user in the GUI.
     */
    public String getResponse(String input) throws DukeException {
        Parser parser = new Parser(tasks);

        Command command = parser.parse(input);
        if (command.isBye()) {
            Platform.exit();
        }
        return command.execute(tasks, storage);
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
