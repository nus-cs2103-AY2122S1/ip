package duke.views.strategies.commands;

import java.time.LocalDateTime;

import duke.constants.Constants;
import duke.domain.Deadline;
import duke.domain.Event;
import duke.domain.Task;
import duke.domain.TaskList;
import duke.shared.DukeException;

/**
 * Encapsulates a command to filter out deadline and event objects by datetime from the task list.
 */
public class OccurringOnCommand extends TaskCommand {
    private static Command singleInstance;

    public OccurringOnCommand(TaskList taskList) {
        super(taskList);
    }

    public static Command getInstance(TaskList taskList) {
        if (singleInstance == null) {
            singleInstance = new OccurringOnCommand(taskList);
        }
        return singleInstance;
    }

    @Override
    public String produce(String query) throws DukeException {
        assert query != null;
        String dateQuery = query.substring(ON.length()).strip();
        if (dateQuery.length() == 0) {
            dateQuery = Constants.Input.DATETIME_FORMATTER.format(LocalDateTime.now());
        }

        TaskList relevantTasks = new TaskList();
        for (Task task : userTasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.isDueOn(dateQuery)) {
                    relevantTasks.add(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.isOccurringOnDay(dateQuery)) {
                    relevantTasks.add(event);
                }
            }
        }

        return listTasksWithMessage("Tasks occurring on that day:",
                relevantTasks, "No tasks on that day");
    }
}
