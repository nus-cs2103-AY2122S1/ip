package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;
import duke.util.DukeException;
import duke.util.Message;
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
    }

    /**
     * Loads the saved task list for duke.
     *
     * @return Message to be printed out by UI.
     */
    public Message loadTaskList() {
        try {
            taskList = new TaskList(storage.importTask(), ui, storage);
            return ui.loadTaskSuccessMessage();
        } catch (FileNotFoundException e) {
            return ui.importTaskErrorMessage();
        }
    }

    /**
     * Starts the chatbot and get it to chat with the user.
     */
    private void chat() {
        ui.greetMessage().printMessage();
        loadTaskList().printMessage();
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
                    command.execute(taskList, ui).printMessage();
                }
            } catch (DukeException e) {
                ui.chatErrorMessage().printMessage();
            }
        }
        ui.exitMessage().printMessage();
    }

    public String getResponse(String message) {
        message = message.strip();
        try {
            Command command = Parser.parseChat(message);
            if (command == null) {
                return ui.exitMessage().toString();
            } else {
                return command.execute(taskList, ui).toString();
            }
        } catch (DukeException e) {
            return ui.chatErrorMessage().toString();
        }
    }

    /**
     * Runs the chatbot.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.chat();
    }
}
