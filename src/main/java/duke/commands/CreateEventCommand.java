package duke.commands;

import duke.data.TaskList;
import duke.data.tasks.Events;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Creates and adds an Event task.
 */
public class CreateEventCommand extends Command {
    private final String name;
    private final String date;

    /**
     * @param userInput The raw input string from the user
     */
    public CreateEventCommand(String userInput) {
        assert userInput.split(" /at ").length > 1;
        this.name = userInput.split(" /at ", 2)[0].substring(6);
        this.date = userInput.split(" /at ", 2)[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMsg(tasks.addToList(new Events(this.name, this.date)));
        storage.write(tasks.getSaveData());
    }
}
