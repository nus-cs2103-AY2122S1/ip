package duke.command;

import java.time.LocalDate;

import duke.IncompleteCommandException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

/**
 * DeadlineCommand instructions to add task into the program.
 */
public class DeadlineCommand extends Command {

    /**
     * constructor for DeadlineCommand.
     *
     * @param input String command.
     */
    public DeadlineCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws IncompleteCommandException {
        if (input.length() <= 8) {
            throw new IncompleteCommandException("OOPS!!! The description of a deadline cannot be empty.");
        }
        Task task = null;

        if (input.contains("/by")) {
            String[] stringArr = input.substring(9).split("/by");
            LocalDate date;
            try {
                date = LocalDate.parse(stringArr[1].strip());
                task = new Deadline(stringArr[0], date);
                taskList.addTask(task);
            } catch (Exception e) {
                System.out.println("Incorrect date format! Please follow YYYY-MM-DD for the date");
            }

        } else {
            System.out.println("Your deadline is missing a /by (date)");
        }

        return ui.taskWithDateAddedMessage(task, taskList.getTotalNumberOfTask());
    }


}
