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

    @Override
    public boolean isExit() {
        return false;
    }
}
