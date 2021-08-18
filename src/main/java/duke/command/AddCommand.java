package duke.command;

import duke.enums.Tasks;
import duke.exception.BadInputFormatException;
import duke.exception.EmptyDescriptionException;
import duke.exception.UnknownTaskTypeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class AddCommand extends Command {
    private Task task;

    private AddCommand(Task task) {
        this.task = task;
    }

    public static AddCommand of(String content)
            throws BadInputFormatException,
            UnknownTaskTypeException,
            EmptyDescriptionException {
        AddCommand command;
        switch (taskTypeParser(content)) {
        case TODO:
            command = new AddCommand(Todo.of(taskDescriptionParser(content)));
            break;
        case DEADLINE:
            command = new AddCommand(Deadline.of(taskDescriptionParser(content)));
            break;
        case EVENT:
            command = new AddCommand(Event.of(taskDescriptionParser(content)));
            break;
        default:
            throw new UnknownTaskTypeException(content);
        }
        return command;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        ui.print("Got it. I've added this task:",
                task.toString(),
                String.format("Now you have %d %s in the list.", tasks.size(), tasks.size() == 1 ? "task" : "tasks"));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    private static Tasks taskTypeParser(String content) {
        return Tasks.valueOfLabel(content.trim().split(" ")[0]);
    }

    private static String taskDescriptionParser(String content) throws EmptyDescriptionException {
        try {
            return content.trim().split(" ", 2)[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new EmptyDescriptionException();
        }
    }
}
