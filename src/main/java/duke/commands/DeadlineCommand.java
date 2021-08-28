package duke.commands;

import duke.DateTimeHandler;
import duke.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.UI;

import java.time.LocalDateTime;

/**
 * Encapsulates the deadline command, used to create a deadline task
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new DeadlineCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            return "Please enter the name of the task after deadline";

        }
        if (!args.contains("/by")) {
            return "Please enter the deadline of the task after /by";
        }
        String[] parts = args.split(" /by ");
        LocalDateTime deadlineDate = dth.parseDate(parts[1]);
        if (deadlineDate == null) {
            return dth.invalidFormat();
        }
        Deadline d = new Deadline(parts[0], false, deadlineDate);
        tl.addToList(d);
        return ui.formatMessage(tl.taskAddedMessage(d));
    }

    @Override
    public String startsWith() {
        return "deadline";
    }
}
