package duke.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class EventCommand extends Command {
    private Event event;

    public EventCommand(String description, String at) {
        event = new Event(description, at);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(event);

        String response = "Got it. I've added this task:\n"
                + "       " + event + "\n"
                + "     Now you have "
                + tasks.getSize() + (tasks.getSize() > 1 ? " tasks" : " task")
                + " in the list.";
        ui.showResponse(response);

        storage.save(tasks.getTaskList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
