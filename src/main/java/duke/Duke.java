package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class Duke {
    /** For the chatboard to read the user input. */
    private Scanner sc;
    /** Stores all the task. */
    private TaskList taskList;
    /** Get the ui to interact with the user. */
    private Ui ui;
    /** deals with loading and saving tasks. */
    private Storage storage;

    /**
     * Constructor for duke.Duke.
     */
    Duke() {
        sc = new Scanner(System.in);
        ui = new Ui();
        storage = new Storage(ui);
        taskList = new TaskList(storage.importTask(), ui, storage);
    }

    /**
     * Starts the chatbot and get it to chat with the user.
     */
    private void chat() {
        ui.greetMessage();
        String message;
        Command command;
        boolean isRunning = true;
        while (isRunning) {
            message = sc.nextLine().strip();
            try {
                command = Parser.parseChat(message);
                if (command == null) {
                    isRunning = false;
                } else {
                    command.execute(taskList, ui);
                }
            } catch (DukeException e) {
                ui.chatErrorMessage();
            }
        }
        ui.exitMessage();
    }

    /**
     * Runs the chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
