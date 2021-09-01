package duke.core;

import java.util.Scanner;

import duke.command.Commandable;
import duke.exception.DukeException;

/**
 * Encapsulates the Main Program where Duke is run.
 *
 * @author Clifford
 */
public class Duke {
    private static final String TASKS_FILE_PATH = "bin/data/tasks.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Initialises Duke for whole application to run
     */
    public Duke() {
        ui = new Ui();
        try {
            storage = new Storage(TASKS_FILE_PATH);
            taskList = new TaskList(storage);
            taskList.retrieveTasks();
        } catch (DukeException e) {
            ui.formatDisplay(e.getMessage());
        }
    }

    /**
     * Sends a greeting message when the user first starts up Duke
     * @return the greeting message when user starts up Duke
     */
    public String greetUser() {
        return "Hello! I'm Duke!\nHow may I be of service to you?";
    }

    /**
     * Runs the whole application with only Text
     */
    public void run() {
        ui.greetUser();
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = sc.nextLine();
                Commandable c = Parser.identifyCommand(userInput);
                ui.formatDisplay(c.execute(taskList, ui, storage));
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.formatDisplay(e.getMessage());
            }
        }
        sc.close();
    }

    /**
     * Initialises and runs Duke in text-based form
     * @param args the user input
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String userInput) {
        try {
            Commandable c = Parser.identifyCommand(userInput);
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
