package duke.command;
import duke.Ui;
import duke.Storage;
import duke.DukeException;
import duke.task.TaskList;
import duke.task.Event;

public class EventCommand extends Command {
    private String input;
    private String taskDesc;
    private String eventDate;

    public EventCommand(String input) {
        this.input = input;
        this.taskDesc = input.replaceFirst("^event", "").split(" /")[0];
        if (input.contains("/at")) {
            this.eventDate = input.substring(input.indexOf("/at") + 4);
        }
    }

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Event eTask = new Event(taskDesc, eventDate);
        ls.addTask(eTask);
        storage.rewriteFile(ls);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
