package duke.commands;

import java.io.IOException;

import duke.exceptions.CommandParamException;
import duke.exceptions.DukeFileException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is an AddCommand class that extends Command.
 */
public class AddCommand extends Command {

    /**
     * This is the constructor for AddCommand.
     *
     * @param taskType A String representing the task to be created.
     * @param description A String containing the details of the task to be created.
     */
    public AddCommand(String taskType, String description) {
        super(taskType, description);
    }

    @Override
    public String execute(TaskList taskList, Storage store, Ui ui)
            throws CommandParamException, DukeFileException, EmptyDescriptionException, UnknownCommandException {
        try {
            Task task;
            if (this.command.equals("todo")) {
                if (description.trim().equals("")) {
                    throw new EmptyDescriptionException("todo");
                }
                task = new ToDo(this.description);

            } else if (this.command.equals("deadline")) {
                if (description.equals("")) {
                    throw new EmptyDescriptionException("deadline");
                }
                if (description.contains("/by")) {
                    String[] deadlineDateTimeParts = description.split("/by");
                    String deadlineDescription = deadlineDateTimeParts[0].trim();
                    String deadlineBy = deadlineDateTimeParts[1].trim();
                    if (deadlineDescription.equals("") || deadlineBy.equals("")) {
                        throw new CommandParamException("deadline");
                    }
                    task = new Deadline(deadlineDescription, deadlineBy);
                } else {
                    throw new CommandParamException("deadline");
                }

            } else if (this.command.equals("event")) {
                if (description.equals("")) {
                    throw new EmptyDescriptionException("event");
                }
                if (description.contains("/at")) {
                    String[] eventDateTimeParts = description.split("/at");
                    String eventDescription = eventDateTimeParts[0].trim();
                    String eventAt = eventDateTimeParts[1].trim();
                    if (eventDescription.equals("") || eventAt.equals("")) {
                        throw new CommandParamException("event");
                    }
                    task = new Event(eventDescription, eventAt);
                } else {
                    throw new CommandParamException("event");
                }

            } else {
                throw new UnknownCommandException();
            }

            taskList.addToList(task);
            store.appendCommand(task.fullCommand());
            return ui.showAddTaskMessage(task, taskList.getSize());

        } catch (IOException e) {
            throw new DukeFileException();
        }
    }

    @Override
    public String toString() {
        return this.command + " " + this.description;
    }
}
