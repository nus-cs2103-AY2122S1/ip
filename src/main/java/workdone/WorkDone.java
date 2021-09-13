package workdone;

import java.nio.file.Path;
import java.nio.file.Paths;

import workdone.command.Command;
import workdone.data.Storage;
import workdone.data.TaskList;
import workdone.exception.WorkDoneException;
import workdone.ui.Parser;
import workdone.ui.Ui;

/**
 * Represents the WorkDone program. Manages tasks based on commands received.
 */
public class WorkDone {
    /** Storage that can write to or retrieve data from a file on hard disk */
    private final Storage storage;
    /** List of tasks added */
    private final TaskList tasks;
    /** UI of the program */
    private final Ui ui;

    /**
     * Constructor of the class 'WorkDone'.
     *
     * @param filePath Path of the file to retrieve data.
     */
    public WorkDone(Path filePath) {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(filePath, this.tasks);
    }

    /**
     * Runs the WorkDone program.
     */
    public void run() {
        // Print welcome message, start running
        this.ui.showWelcome();
        boolean isRunning = true;

        // Read in commands while the program is running
        while (isRunning) {
            String commandString = this.ui.getCommand();
            Command command;
            try {
                command = Parser.parse(commandString.strip());
                command.execute(this.tasks, this.storage);
                this.ui.showCommandOutput(command);
                isRunning = command.isRunning();
            } catch (WorkDoneException workDoneException) {
                this.ui.showError(workDoneException);
            }
        }
    }

    /**
     * Runs the WorkDone program, prints out messages based on commands received.
     *
     * @param args The command line parameters.
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir");
        new WorkDone(Paths.get(filePath, "data", "tasks.txt")).run();
    }

    public String getResponse(String input) {
        Command command;
        try {
            command = Parser.parse(input.strip());
            command.execute(this.tasks, this.storage);
            return command.toString();
        } catch (WorkDoneException workDoneException) {
            return workDoneException.toString();
        }
    }

    public String getGreetingMessage() {
        return this.ui.getGreetingMessage();
    }
}
