package duke;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

import java.util.Scanner;

/**
 * duke.Duke class that initialises the duke.Duke chat bot.
 * The Duke class supports operators including
 * (i) run: runs the chat bot
 * (ii) greet: prints out a greeting when the chat bot runs
 * (iii) exit: ends the execution of the chat bot
 * (iv) addToList: adds a Task to the list of Tasks
 * (v) displayList: prints out the current list of Tasks
 * (vi) markDone: marks a Task as done
 * (vii) addDeadline: adds a Deadline to the list of Tasks
 * (viii) addTodo: adds a Todo to the list of Tasks
 * (ix) addEvent: adds an duke.tasks.Event to the list of Tasks
 * (x) handleCommands: main logic for processing and executing various commands
 * like "list", "done", "deadline", "todo", "event" and other invalid commands
 */

public class Duke {

    /** Handles loading and saving of Tasks. */
    private final Storage storage;

    /** Stores all tasks */
    private TaskList tasks;

    /** Handles interactions with users. */
    private final Ui ui;

    /**
     * Constructor for the DUke chatbot.
     * Loads any pre-existing data from the provided filePath.
     *
     * @param filePath path to the data storage file
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DukeException e) {
            tasks = new TaskList();
            Ui.printMessage(e.getMessage());
        }
    }

    /**
     * Runs the Duke chat bot.
     */

    private void run() {

        Parser parser = new Parser(tasks);

        ui.greet();

        String input;
        Scanner sc = new Scanner(System.in);

        /* user input trimmed to remove unwanted spaces at the front and back of user input
        allows for greater margin of error when typing in commands */
        while(!(input = sc.nextLine().trim()).equals("bye")) {
            // continuously runs the bot as long as the "bye" command is not issued
            parser.handleCommands(input);
        }

        sc.close();

        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            Ui.printMessage(e.getMessage());
        }

        ui.exit();
    }

    /**
     * Runs the Duke chatbot.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
