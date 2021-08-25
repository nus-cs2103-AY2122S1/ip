package duke;

import duke.command.Command;

/**
 * Interactive bot that manages one's tasks.
 */
public class Duke {
    private FileManager filemanager;
    private Tasklist tasks;
    private UI ui;

    /**
     * Constructs Duke which stores the tasks in a file.
     *
     * @param filePath filepath for the file which the tasks will be solved in.
     */
    public Duke(String filePath) {
        ui = new UI();
        filemanager = new FileManager(filePath);
        tasks = new Tasklist(filemanager.getTaskList());
    }

    /**
     * Runs Duke to manage user's task.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, filemanager);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    /**
     * To run Duke.
     * @param args no arguments
     */
    public static void main(String[] args) {
        new Duke("taskList.txt").run();
    }
}