package duke;

import java.lang.reflect.Proxy;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * A terminal based chat bot to track tasks.
 */
public class Duke {

    private static final String WELCOME_MESSAGE = "\n\tHi! I'm Herbert, you can call me Herb  ٩(˘◡˘)۶\n"
            + "\tHow can I help you?\n\n"
            + "\tYou can type:\n"
            + "\t\t `list` to get a list of tasks\n"
            + "\t\t `todo ${item}` to add a todo\n"
            + "\t\t `deadline ${item} /by ${time}` to add a deadline\n"
            + "\t\t `event ${item} /at ${time}` to add an event\n"
            + "\t\t `done ${i}` to mark task i as completed\n"
            + "\t\t `delete ${i}` to delete task i\n"
            + "\t\t `find ${keyword}` to find tasks by keyword\n"
            + "\t\t `sort ${flag}` to sort tasks. [Opt] Flags; -T, -E, -D and -d for descending\n"
            + "\t\t `bye` to end this chat\n";

    private final Storage storage;
    private final UiInterface uiInterface;
    // Invocation handler that acts as a wrapper around methods in the Ui class
    // so that Ui messages can be sent to the GUI prior to display.
    private final DynamicInvocationHandler interceptor;
    private TaskList taskList;
    private boolean isExit = false;

    /**
     * Duke constructor.
     *
     * @param filePath The filepath of the task list from the project root
     */
    public Duke(String filePath) {
        Ui ui = new Ui();
        interceptor = new DynamicInvocationHandler(ui);
        uiInterface = (UiInterface) Proxy.newProxyInstance(
                UiInterface.class.getClassLoader(),
                new Class[] { UiInterface.class },
                interceptor);
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            uiInterface.showLoadingError(e.getMessage());
            taskList = new TaskList();
        }
    }

    /**
     * Main method that starts and runs the bot.
     */
    public void run() {
        uiInterface.showWelcome(WELCOME_MESSAGE);
        while (!this.isExit) {
            try {
                String fullCommand = uiInterface.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, uiInterface, storage);
                this.isExit = c.isExit();
                storage.save(taskList);
            } catch (DukeException e) {
                uiInterface.showError(e.getMessage());
            }
        }
    }

    /**
     * Returns Herb's response based on a given input.
     *
     * @param input user input
     * @return response
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, uiInterface, storage);
            this.isExit = c.isExit();
            storage.save(taskList);
        } catch (DukeException e) {
            uiInterface.showError(e.getMessage());
        }
        // Assert that the latest response has been updated at this stage
        assert this.interceptor.getLatestResponse() != null;
        return this.interceptor.getLatestResponse();
    }

    /**
     * Returns true if the latest command was for exit.
     *
     * @return boolean indicating if to exit or not
     */
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * Returns welcome message.
     *
     * @return welcome message
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public static void main(String[] args) {
        new Duke("data/tasklist.txt").run();
    }
}
