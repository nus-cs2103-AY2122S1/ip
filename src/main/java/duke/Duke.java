package duke;

import duke.command.Command;
import duke.constant.MessageType;
import duke.listener.Message;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Parser;

/**
 * The project aims to build a product named Duke,
 * a Personal Assistant Chatbot that helps a person
 * to keep track of various things.
 *
 * This is the Main class that will contain the main method
 * to be executed at run-time.
 */
public class Duke implements Message {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructs a Duke object.
     */
    public Duke() {
        ui = new Ui();
        taskList = new TaskList();
        storage = new Storage(this);
    }

    /**
     * Runs Duke program.
     */
    public void run() {
        storage.loadTasks(taskList);
        ui.logo();
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            String command = ui.readCommand();
            Command c = Parser.parse(command, this);
            if (c == null) {
                continue;
            }
            c.execute();
            c.execute(taskList);
            storage.saveTasksToFile(taskList);
            isExit = c.isExit();
        }
    }

    /**
     * Runs entry point for the Duke program.
     *
     * @param args an array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Displays messages.
     */
    @Override
    public void show(MessageType messageType, String... messages) {
        ui.show(messages);
    }
}
