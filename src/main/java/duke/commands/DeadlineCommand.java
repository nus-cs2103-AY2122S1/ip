package duke.commands;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import duke.DateTimeHandler;
import duke.Deadline;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) throws DukeException {
        String args = super.getArguments();
        if (args.length() == 0) {
            throw new DukeException("Please enter the name of the task after deadline");

        }
        if (!args.contains("/by")) {
            throw new DukeException("Please enter the deadline of the task after /by");
        }

        String description;
        String dateString;
        ArrayList<String> tags = new ArrayList<>();
        if (args.contains("/t")) {
            ArrayList<String> parts = Parser.parseCommandArguments(args, "by", "t");
            description = parts.get(0);
            dateString = parts.get(1);
            tags = new ArrayList<>(Arrays.asList(parts.get(2).split(" ")));
            System.out.print(description + " " + dateString);
        } else {
            ArrayList<String> parts = Parser.parseCommandArguments(args, "by");
            description = parts.get(0);
            dateString = parts.get(1);
        }
        LocalDateTime deadlineDate = dth.parseDate(dateString);
        if (deadlineDate == null) {
            throw new DukeException(dth.invalidFormat());
        }
        Deadline d = new Deadline(description, false, deadlineDate, tags);
        tl.addToList(d);
        return ui.formatMessage(tl.taskAddedMessage(d));
    }

    @Override
    public String getCommandPrefix() {
        return "deadline";
    }
}
