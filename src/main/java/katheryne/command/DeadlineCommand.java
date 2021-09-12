package katheryne.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import katheryne.KatheryneException;
import katheryne.Storage;
import katheryne.TaskList;
import katheryne.Ui;
import katheryne.task.Deadline;

public class DeadlineCommand extends Command {
    /**
     * The constant name to refer to this command by
     */
    public static final String COMMAND = "DEADLINE";
    private final String description;
    private final LocalDate atByDate;

    /**
     * Constructs the DeadlineCommand by putting the processedRemainingText into appropriate variables.
     *
     * @param processedRemainingText The first item is the description; the second is the time it is due by
     * @throws KatheryneException If the date is in the wrong format
     */
    DeadlineCommand(String[] processedRemainingText) throws KatheryneException {
        this.description = processedRemainingText[0];
        System.out.println(processedRemainingText[1]);
        try {
            this.atByDate = LocalDate.parse(processedRemainingText[1]);
        } catch (DateTimeParseException e) {
            throw new KatheryneException(
                    "The by time is in the wrong format. It must be in the format YYYY-MM-DD");
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws KatheryneException {
        Deadline deadline = new Deadline(description, atByDate);
        taskList.add(deadline);
        ui.say("Deadline '" + description + "' added to your list, scheduled for " + deadline.getStringDueBy());
        ui.countTasksInList(taskList);
    }
}
