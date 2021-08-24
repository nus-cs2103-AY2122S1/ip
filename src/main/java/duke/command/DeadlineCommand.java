package duke.command;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    public String arguments;

    public DeadlineCommand(String arguments) {
        super("deadline");
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (arguments.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be left empty. "
                    + "Please try again.", command));
        }
        String[] argArr = arguments.split("/by");
        if (argArr.length == 1 || argArr[1].isEmpty()) {
            throw new DukeException("Arguments do not follow proper format. Don't forget the /by");
        }
        LocalDate newTaskDate = Parser.convertDate(argArr[1].trim());
        Deadline newTask = new Deadline(argArr[0].trim(), newTaskDate);
        tasks.add(newTask);
        ui.printToUser("Got it. I've added this task:");
        ui.printToUser("  " + newTask);
        ui.printToUser("Now you have " + tasks.size() +
                (tasks.size() == 1 ? " task" : " tasks")
                + " in your list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
