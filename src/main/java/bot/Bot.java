package bot;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import commands.Command;
import exceptions.InvalidArgumentsException;
import exceptions.InvalidTaskException;

/**
 * Bot handles the Bot's REPL cycle
 */
public class Bot {
    private TaskList taskList;
    private Boolean isRunning;

    /**
     * Constructor for Bot
     */
    public Bot() {
        this.taskList = new TaskList(new ArrayList<>());
        Storage.load(this);
        this.isRunning = true;
    }

    /**
     * Starts the task bot
     */
    public void start() {
        Ui.printWelcome();
        while (this.isRunning) {
            String cmdString = Parser.getInput();
            Ui.print(getOutput(cmdString));
        }
        Parser.closeScanner();
    }

    /**
     * Gets the lines for the Bot's output
     * given an input string for a command
     *
     * @param input command string
     * @return Bot output lines
     */
    public String[] getOutput(String input) {
        try {
            Command cmd = Parser.commandFromString(input);
            String[] resp = cmd.run(this, cmd.getArgs());
            Storage.save(this);
            return resp;
        } catch (InvalidTaskException | InvalidArgumentsException | DateTimeParseException e) {
            return new String[]{ e.getMessage() };
        }
    }

    /**
     * Stops the task bot
     */
    public void stop() {
        this.isRunning = false;
    }

    /**
     * Getter for taskList
     *
     * @return Bot's taskList
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}
