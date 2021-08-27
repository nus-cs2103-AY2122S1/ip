package kayu;

import java.util.List;
import java.util.Scanner;

import kayu.commands.Command;
import kayu.exception.DukeException;
import kayu.exception.StorageException;
import kayu.parser.Parser;
import kayu.service.ChatBot;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Task;

/**
 * Holds the main logic for Kayu project (CS2103T's iP).
 * Kayu is a task manager for users that love the command line interface.
 * Code structure inspiration from <code>se-edu</code>'s addressbook-level2 code on GitHub
 * <a href="https://github.com/se-edu/addressbook-level2">addressbook-level2</a>.
 * @author muhammad-khair, damithc, j-lum
 */
public class Kayu {

    private final Parser parser = new Parser();
    private final TaskList taskList = new TaskList();
    private final Storage storage = new Storage();
    private final ChatBot chatBot = new ChatBot();
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Runs the whole program process. Greets user, loads data,
     * reads commands, and terminates upon {@link kayu.commands.ByeCommand#COMMAND_WORD}.
     */
    public void runProgram() {
        chatBot.printLogo();
        initializeData();
        chatBot.printGreetingMessage();
        readCommandsAndExecute();
        chatBot.printExitMessage();
    }

    /**
     * Initializes the data stored in file to taskList.
     */
    public void initializeData() {
        try {
            List<Task> tasks = storage.load();
            taskList.initializeTasks(tasks);
        } catch (StorageException exception) {
            chatBot.printError(exception.getMessage());
            terminate();
        }
    }

    /**
     * Reads the commands inputted by user, parses them into {@link kayu.commands.Command}
     * and executes them. {@link kayu.service.ChatBot} helps output the responses from such
     * executions.
     */
    public void readCommandsAndExecute() {
        Command command;
        do {
            String userInput = scanner.nextLine().trim();
            command = parser.parseToCommand(userInput);
            try {
                String feedback = command.execute(taskList);
                List<Task> tasks = taskList.getTasks();
                storage.saveTasks(tasks);
                chatBot.printMessage(feedback);

            } catch (DukeException exception) {
                chatBot.printError(exception.getMessage());

            } catch (StorageException exception) {
                chatBot.printErrorOnSave();
                terminate();
            }
        } while (!command.isBye());
        scanner.close();
    }

    /**
     * Terminates the program, based on {@link kayu.exception.StorageException}.
     */
    public void terminate() {
        chatBot.printTerminateMessage();
        scanner.close();
        System.exit(1); // exit with error status
    }
}
