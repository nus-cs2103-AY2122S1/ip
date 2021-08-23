package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JWBotException;
import jwbot.data.task.Event;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

public class AddEventCommand extends Command {

    public AddEventCommand(String input) {
        super(input);
    }

    /**
     * The method that executes the command
     *
     * @param tasks list of the tasks
     * @param ui the ui object responsible for user interaction
     * @param storage the storage object responsible for writing and reading txt file
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JWBotException {
        try {
            String content = input.split(" ", 2)[1];
            String[] separated = content.split(" /at ");
            Event event = new Event(separated[0], separated[1]);
            tasks.addTask(event);
            storage.write(tasks);
            ui.showAddTaskSuccessMessage(event);
        } catch (Exception e) {
            throw new JWBotException("Sorry bro, I think you made an error with the event format!");
        }
    }

    /**
     * The method that checks if the bot should be stopped.
     *
     * @return the boolean false to not stop the bot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
