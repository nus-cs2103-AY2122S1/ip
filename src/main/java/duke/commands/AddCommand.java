package duke.commands;

import duke.exceptions.CommandParamException;
import duke.exceptions.DukeFileException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.UnknownCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.TaskList;
import duke.Storage;
import duke.Ui;
import java.io.IOException;

/**
 * This is a duke.commands.AddCommand class that extends duke.commands.Command.
 */
public class AddCommand extends Command {


    public AddCommand(String taskType, String description) {
        super(taskType, description);
    }


    @Override
    public void execute(TaskList tasklist, Storage store, Ui ui)
            throws CommandParamException, DukeFileException,
            EmptyDescriptionException, UnknownCommandException {
        try {
            Task t;
            if (this.command.equals("todo")) {
                if (description.trim().equals("")) {
                    throw new EmptyDescriptionException("todo");
                }
                t = new ToDo(this.description);

            } else if (this.command.equals("deadline")) {
                if (description.equals("")) {
                    throw new EmptyDescriptionException("deadline");
                }
                if (description.contains("/by")) {
                    String[] parts = description.split("/by");
                    if (parts.length < 2 || parts[0].trim().equals("") || parts[1].trim().equals("")) {
                        throw new CommandParamException("deadline");
                    }
                    t = new Deadline(parts[0].trim(), parts[1].trim());
                } else {
                    throw new CommandParamException("deadline");
                }

            } else if (this.command.equals("event")) {
                if (description.equals("")) {
                    throw new EmptyDescriptionException("event");
                }
                if (description.contains("/at")) {
                    String[] parts = description.split("/at");
                    if (parts.length < 2 || parts[0].trim().equals("") || parts[1].trim().equals("")) {
                        throw new CommandParamException("event");
                    }
                    t = new Event(parts[0].trim(), parts[1].trim());
                } else {
                    throw new CommandParamException("event");
                }
            } else {
                throw new UnknownCommandException();
            }
            tasklist.addToList(t);
            store.appendCommand(t.fullCommand());
            ui.printAddTask(t, tasklist);
        } catch (IOException e) {
            throw new DukeFileException();
        }
    }
}
