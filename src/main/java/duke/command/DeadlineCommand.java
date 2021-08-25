package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.DukeIllegalFormatException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand extends AddCommand {

    public DeadlineCommand(String description) {
        super(description);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] line = description.split(" /by ");
        if (line.length != 2) {
            throw new DukeIllegalFormatException(
                    "☹ OOPS!!! Seems like you have entered a wrong format for a deadline task. " +
                            "Try this instead: deadline <description> /by <date>"
            );
        }
        Task task = new Deadline(line[0], line[1]);
        tasks.add(task, storage);
        System.out.println("Got it. I've added this task:\n  " +
                task +
                "\nNow you have " + tasks.toArray().length + " task(s) in the list.");
    }
}
