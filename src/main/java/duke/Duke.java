package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.io.IOException;

import java.util.Scanner;

/**
 * duke.Duke class that initialises the duke.Duke chat bot.
 * The duke.Duke class supports operators including
 * (i) run: runs the chat bot
 * (ii) greet: prints out a greeting when the chat bot runs
 * (iii) exit: ends the execution of the chat bot
 * (iv) addToList: adds a duke.tasks.Task to the list of Tasks
 * (v) displayList: prints out the current list of Tasks
 * (vi) markDone: marks a duke.tasks.Task as done
 * (vii) addDeadline: adds a duke.tasks.Deadline to the list of Tasks
 * (viii) addTodo: adds a duke.tasks.Todo to the list of Tasks
 * (ix) addEvent: adds an duke.tasks.Event to the list of Tasks
 * (x) handleCommands: main logic for processing and executing various commands
 * like "list", "done", "deadline", "todo", "event" and other invalid commands
 */

public class Duke {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the duke.Duke chat bot.
     * It takes in user inputs and responds accordingly.
     * If a command is issued, the bot will execute the command if the appropriate message
     * follows the command.
     * Any invalid inputs are caught via custom Exceptions, thrown and printed for the user to see.
     * Invalid inputs include empty inputs, incorrect formats, invalid index, empty messages and
     * attempting to amend the list of Tasks when it is currently empty.
     */

    private void run() {

        Parser parser = new Parser(tasks);

        ui.greet();

        String input;
        Scanner sc = new Scanner(System.in);

        // user input trimmed to remove unwanted spaces at the front and back of user input
        // allows for greater margin of error when typing in commands
        while(!(input = sc.nextLine().trim()).equals("bye")) {
            // continuously runs the bot as long as the "bye" command is not issued
            parser.handleCommands(input);
        }

        sc.close(); // closes the Scanner

        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        ui.exit();
    }




    public static void main(String[] args) {
        new Duke("data.txt").run();
    }
}
