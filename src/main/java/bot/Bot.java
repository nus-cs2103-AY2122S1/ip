package main.java.bot;

import main.java.command.Command;

/**
 * A class that encapsulates the main runtime operations of Duke.
 * Has two main methods: start and stop.
 */
public class Bot {

    private TaskList tasks;
    private UserInterface ui;
    private static boolean isRunning;

    /**
     * Constructor for the Bot class.
     *
     * Takes no parameters.
     */
    public Bot() {

        ui = new UserInterface();

        try {
           tasks = Storage.load();
        } catch (DukeException e) {
           ui.showLoadingError();
           tasks = new TaskList();
        }
    }

    /**
     * Starts the main Duke bot and executes the main runtime operations of Duke.
     *
     * Takes no parameters.
     */
    public void start() {

        ui.showWelcomeMessage();
        isRunning = false;

        while (!isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui);
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Stops the main Duke bot.
     *
     * Takes no parameters.
     */
    public static void stop() {
         isRunning = true;
     }
}