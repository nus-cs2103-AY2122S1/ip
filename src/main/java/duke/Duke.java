package duke;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.command.Command;
import duke.command.Parser;
import duke.task.Task;
import duke.util.DukeException;
import duke.util.Message;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Main logic class for duke.
 * Run this class to run Duke without GUI.
 *
 * @author marcuspeh
 * @version A-Assertions
 * @since 6 Sep 2021
 */
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
            return ui.formatLoadTaskSuccessMessage();
        } catch (FileNotFoundException e) {
            taskList = new TaskList(new ArrayList<Task>(), ui, storage);
            return ui.formatHelpMessage();
        }
    }

    /**
     * Starts the chatbot and get it to chat with the user.
     */
    private void chat() {
        ui.formatGreetMessage().printMessage();
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
                    Message response = command.execute(taskList, ui);
                    assert response != null : " Message from command missing";
                    response.printMessage();
                }
            } catch (DukeException e) {
                ui.formatChatErrorMessage().printMessage();
            }
        }
        ui.formatExitMessage().printMessage();
    }

    /**
     * Deals with the GUI aspect of Duke in javaFx.
     *
     * @param message Message passed in by the user.
     * @return Return the response of Duke
     */
    public String getResponse(String message) {
        message = message.strip();
        try {
            Command command = Parser.parseChat(message);
            if (command == null) {
                return ui.formatExitMessage().toString();
            } else {
                Message response = command.execute(taskList, ui);
                assert response != null : " Message from command missing";
                return response.toString();
            }
        } catch (DukeException e) {
            return ui.formatChatErrorMessage().toString();
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
