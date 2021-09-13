package duke;


import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.TaskList;


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
     * Used to run Duke using the CLI.
     */
    private void run() {
        //Welcome message
        ui.printWelcomeMessage();

        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(tasks);
        boolean hasUserEnd = false;

        //Main functionality of Duke
        while (!hasUserEnd) {
            String command = "";
            if (sc.hasNextLine()) {
                command = sc.nextLine().trim();
            }
            if (command.equalsIgnoreCase("bye")) {
                hasUserEnd = true;
                ui.printGoodbyeMessage();
                storage.save(tasks);
            } else {
                try {
                    Command commandObject = parser.parse(command);
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
     * Used by JavaFX to process the user's commands and execute accordingly.
     *
     * @param command The command input by the user.
     * @return A String containing the message to be shown to the user in the GUI.
     */
    public String getResponse(String command) {
        Parser parser = new Parser(tasks);

        try {
            Command commandObject = parser.parse(command);
            return commandObject.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
