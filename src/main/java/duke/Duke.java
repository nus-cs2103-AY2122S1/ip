package duke;

import java.io.IOException;

import duke.logic.Storage;
import duke.task.TaskList;
import duke.ui.TextCliUi;

/**
 * Duke is a personal assistant that allows users to keep track of events, deadlines and things to do.
 * The main method will start the personal assistant in the console.
 */
public class Duke {
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Creates a new instance of a duke chat-bot.
     *
     * @param filePath the path where the data of the tasks will be saved
     */
    public Duke(String filePath) throws IOException {
        taskList = new TaskList();
        storage = new Storage(filePath, taskList);
    }

    /**
     * Creates a new instance of a duke chat-bot, with default filepath and limit.
     */
    public Duke() throws IOException {
        taskList = new TaskList(100);
        storage = new Storage("./dukedata.txt", taskList);
    }

    /**
     * Runs the duke chat-bot on the command line with default filepath and limit.
     *
     * @param args irrelevant for now
     */
    public static void main(String[] args) {
        try {
            new Duke().run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public Storage getStorage() {
        return storage;
    }

    /**
     * Runs the duke chat-bot.
     */
    public void run() {
        TextCliUi ui = new TextCliUi();
        while (!ui.willExit()) {
            ui.checkInput(taskList, storage);
        }
    }
}
