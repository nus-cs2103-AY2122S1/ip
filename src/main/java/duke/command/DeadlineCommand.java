package duke.command;

import duke.task.Deadline;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;

public class DeadlineCommand extends Command {
    protected Deadline deadline;
    public static final String INSTRUCTION_DEADLINE = "deadline";

    public DeadlineCommand(String parameter_1, String parameter_2) throws DukeException {
        if (parameter_1.equals("") || parameter_2.equals("")) {
            throw new DukeException("☹ OOPS!!! The description or the date a deadline cannot be empty.");
        }
        deadline = new Deadline(parameter_1, parameter_2);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(this.deadline);
        tasks.add(this.deadline);
        ui.formatPrint("Got it. I've added this task:", "  " + this.deadline.toString(), tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "[" + INSTRUCTION_DEADLINE + "] - " + deadline.toString();
    }
}
