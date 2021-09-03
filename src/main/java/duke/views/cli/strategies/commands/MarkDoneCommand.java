package duke.views.cli.strategies.commands;

import duke.domain.Task;
import duke.domain.TaskList;
import duke.shared.DukeException;

public class MarkDoneCommand extends TaskCommand {
    private static Command singleInstance;

    public MarkDoneCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new MarkDoneCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String query) throws DukeException {
        assert query != null;
        try {
            int index = Integer.parseInt(query.substring(done.length()).strip()) - 1;
            boolean isWithinBounds = index >= userTasks.size() || index < 0;
            if (!isWithinBounds) {
                throw DukeException.createIndexOutOfBoundsException(userTasks.size(), index + 1);
            }
            Task task = userTasks.get(index);
            task.finish();
            return String.format("Nice! I've marked this task as done:%s\t%s%s", System.lineSeparator(), task,
                    System.lineSeparator());
        } catch (NumberFormatException e) {
            throw new DukeException(DukeException.ExceptionCode.INCORRECT_ARGS, "Please give a number");
        }
    }
}
