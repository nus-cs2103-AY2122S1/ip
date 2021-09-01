package duke;

import duke.command.Command;
import duke.exception.DukeException;

import java.lang.reflect.Proxy;

/**
 * A terminal based chat bot to track tasks.
 */
public class Duke {

    private final Storage storage;
    private final UiInterface uiInterface;
    private final DynamicInvocationHandler interceptor;
    private final Ui ui;
    private TaskList taskList;
    private boolean isExit = false;

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
            + "\t\t `bye` to end this chat\n";

    /**
     * Duke constructor.
     *
     * @param filePath The filepath of the task list from the project root
     */
    public Duke(String filePath) {
        ui = new Ui();
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
    
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(taskList, uiInterface, storage);
            this.isExit = c.isExit();
            storage.save(taskList);
        } catch (DukeException e) { 
            uiInterface.showError(e.getMessage());
        }    
        return this.interceptor.getLatestResponse();
    }
    
    public boolean isExit() {
        return this.isExit;
    }

    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    public static void main(String[] args) {
        new Duke("data/tasklist.txt").run();
    }
}
