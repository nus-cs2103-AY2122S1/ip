package duke;

import duke.command.Command;

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
     *
     * @param filePath filepath for the file which the tasks will be solved in.
     */
    public Duke(String filePath) {
        ui = new Ui();
        filemanager = new FileManager(filePath);
        tasks = new Tasklist(filemanager.getTaskList());
    }

    public Duke() {
        ui = new Ui();
        filemanager = new FileManager(defaultFilePath);
        tasks = new Tasklist(filemanager.getTaskList());
    }

    public String sayHi() {
        return this.ui.showWelcome();
    }

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

