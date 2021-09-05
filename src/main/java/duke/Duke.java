package duke;

import duke.command.Command;
import javafx.application.Application;

/**
 * Interactive bot that manages one's tasks.
 */
public class Duke {
    private FileManager filemanager;
    private Tasklist tasks;
    private Ui ui;
    private static final String defaultFilePath = "taskList.txt";

    /**
     * Constructs Duke which stores the tasks in a file.
     */
    public Duke() {
        ui = new Ui();
        filemanager = new FileManager(defaultFilePath);
        tasks = new Tasklist(filemanager.getTaskList());
    }

    /**
     * Returns string of Duke's welcome message.
     *
     * @return Duke's welcome messsage.
     */
    public String sayHi() {
        return this.ui.showWelcome();
    }

    /**
     * Returns Duke response to user's commmand.
     *
     * @param input user's command.
     * @return Duke response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            String uiStr = c.execute(tasks, ui, filemanager);
            return uiStr;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}

