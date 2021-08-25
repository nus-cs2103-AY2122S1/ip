package bot;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import commands.Command;
import exceptions.InvalidTaskException;

/**
 * Bot handles the bot's REPL cycle
 */
public class Bot {
    public TaskList taskList;
    private Boolean isRunning;


    public Bot() {
        this.taskList = new TaskList(new ArrayList<>());
    }

    /**
     * Starts the task bot
     */
    public void start() {
        Ui.printWelcome();
        Storage.load(this);
        this.isRunning = true;
        while (this.isRunning) {
            Command cmd = Parser.getCommand();
            try {
                cmd.run(this, cmd.getArgs());
                Storage.save(this);
            } catch (InvalidTaskException | DateTimeParseException e) {
                Ui.print(new String[]{
                        e.getMessage()
                });
            }
        }
        Ui.printGoodbye();
        Parser.closeScanner();
    }

    /**
     * Stops the task bot
     */
    public void stop() {
        this.isRunning = false;
    }

}
