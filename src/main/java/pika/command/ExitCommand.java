package pika.command;

import pika.exception.PikaException;
import pika.task.TaskList;
import pika.ui.Storage;
import pika.ui.Ui;

/**
 * ExitCommand class to handle the closing of the bot.
 */
public class ExitCommand extends Command { //ExitCommand to handle the shutting down of bots

    /**
     * Constructor for ExitCommand.
     *
     * @param details any other input by the user apart from the exit command.
     * @throws PikaException if the user inputs anything extra.
     */
    public ExitCommand(String details) throws PikaException {
        super(false);
        if (details != null) {
            throw new PikaException("Pika pi!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Executes the exitCommand for the bot to say the goodbye Message
     * and to inform the Duke Class that isRunning is false.
     *
     * @param taskList The current list of tasks.
     * @param storage  The current storage class to handle the txt file.
     * @return the string that the bot will say once the exit command is executed.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Ui.goodByeMessage();
    }
}
