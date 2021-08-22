package duke;

import java.io.IOException;

public class AddEventCommand extends Command {
    private final Event event;

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.event);
        try {
            storage.updateFile(tasks.getTasks());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ui.showAddedMessage(this.event, tasks);
    }
}
