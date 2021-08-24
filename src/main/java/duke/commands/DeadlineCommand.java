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
    public void execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            ui.print("Please enter the name of the task after deadline");
            return;
        }
        if (!args.contains("/by")) {
            ui.print("Please enter the deadline of the task after /by");
            return;
        }
        String[] parts = args.split(" /by ");
        LocalDateTime deadlineDate = dth.parseDate(parts[1]);
        if (deadlineDate == null) {
            ui.print(dth.invalidFormat());
            return;
        }
        Deadline d = new Deadline(parts[0], false, deadlineDate);
        tl.addToList(d);
        ui.print(tl.taskAddedMessage(d));
    }

    @Override
    public String startsWith() {
        return "deadline";
    }
}
