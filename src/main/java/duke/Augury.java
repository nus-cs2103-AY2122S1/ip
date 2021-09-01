package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.commands.Command;
import duke.exceptions.AuguryException;
import duke.exceptions.FileIoException;
import duke.exceptions.InvalidActionException;
import duke.exceptions.InvalidTaskCreationException;
import duke.exceptions.UnknownCommandException;
import duke.io.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The {@code Augury} class contains the entry point of the entire Task Management app.
 * Create an instance of {@code Augury}, initialize with {@code init()}, and start the program
 * with {@code loop()}.
 *
 * <p>{@code Augury} takes in a {@code String path} as argument in its constructor.
 * This file stores data created by the app. If no file exists at the specified path,
 * {@code Augury} will create a new file.</p>
 *
 * @author Jefferson (@qreoct)
 */
public class Augury {
    private final String VER     = "v1.0.0"; // Level-10 GUI
    private final String WELCOME =
            "\t+-------------------------------+\n" +
            "\t| *                 *         * |\n" +
            "\t|   (`<       augury     *      |\n" +
            "\t| __/_)_______________________  |\n" +
            "\t|   ||                      *   |\n" +
            "\t|   ||   a task manager         |\n" +
            "\t|      *             *          |\n" +
            "\t|             *         "+VER+"  |\n" +
            "\t+-------------------------------+";
    private final TaskList taskList = new TaskList();
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initialises a new {@code Augury} object which uses the
     * provided {@code String path} as location of the .txt save file.
     *
     * @param path A {@code String} containing the location of the .txt save file.
     */
    public Augury(String path) {
        ui = new Ui();
        this.storage = new Storage(path);
        this.parser = new Parser();
    }

    /**
     * Initialises the private {@code TaskList} using the data from the
     * save file provided.
     *
     * @throws FileIoException If file cannot be read or created
     */
    public void init() throws AuguryException {
        try {
            this.storage.initializeTaskList(this.taskList);
        } catch (IOException e) {
            throw new FileIoException(e.getMessage());
        }
    }

    /**
     * Prints a welcome message to the user.
     */
    public void greet() {
        System.out.println(WELCOME);
    }

    /**
     * Runs main loop of {@code Augury}. Parses and execute commands in a loop.
     *
     * @throws InvalidActionException If action commands were malformed.
     * @throws InvalidTaskCreationException If invalid parameters were provided in task creation.
     * @throws UnknownCommandException If an unrecognized command was provided.
     */
    public void loop() throws AuguryException {
        Scanner scan = new Scanner(System.in);
        ui.speak("Hello! How may I help you?");

        boolean isRunning = true;

        while (isRunning) {
            String input = scan.nextLine().trim().toLowerCase();
            try {
                Command command = parser.parse(input);
                String result = command.execute(taskList, storage);
                if (result.equals("ExitCommand")) {
                    ui.speak("The readiness is all.");
                    isRunning = false;
                } else {
                    ui.speak(result);
                }
            } catch (AuguryException e) {
                ui.speak(e.getMessage() + "\n\t Please try again.");
            }
        }
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            String result = command.execute(taskList, storage);
            if (result.equals("ExitCommand")) {
                return "The readiness is all.";
            } else {
                return result;
            }
        } catch (AuguryException e) {
            return e.getMessage() + "\n\t Please try again.";
        }
    }

}
