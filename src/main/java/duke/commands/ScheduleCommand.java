package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import java.util.ArrayList;
import java.util.Optional;

import duke.tasks.Task;
import duke.date.Date;
import duke.exceptions.WrongDateFormatException;
import duke.exceptions.WrongScheduleFormatException;

/**
 * Class that specifies the properties
 * of a schedule command.
 */
public class ScheduleCommand extends Command {

    /**
     * Calls parent class to initialise the
     * bye command with description "schedule"
     * followed by a date input.
     * @param desc String description of
     * schedule.
     */
    public ScheduleCommand(String desc) {
        super(desc);
    }

    /**
     * Indicates whether the program should
     * stop running, and in this is case no.
     * @return boolean that this command
     * should exit the application or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Search through all the tasks to find
     * event or deadline task with matching dates.
     * @param tasks TaskList to update change given by user.
     * @param ui Ui class to display messages to user.
     * @param storage Storage updates each time a command make changes
     * to the existing stored tasks.
     * @return String output of all matching tasks found.
     * @throws WrongScheduleFormatException if schedule command
     * input given by user is not of "schedule dd/mm/yyyy" format.
     * @throws WrongDateFormatException if the date format is inherently
     * wrong.
     */
    @Override
    public String execute(
            TaskList tasks,
            Ui ui,
            Storage storage) throws WrongScheduleFormatException,
            WrongDateFormatException {
        String[] instructions = this.
                commandDescription.
                split(" ");
        if (instructions.length != 2) {
            throw new WrongScheduleFormatException();
        }
        Date userInputDate = new Date(instructions);
        ArrayList<Task> allTasksItems = tasks.getTaskList();
        String allTasksFound = this.findMatchingDates(
                userInputDate, allTasksItems, tasks);
        return ui.displayTaskMatchingDate(allTasksFound);
    }

    private String findMatchingDates(
            Date userInputDate,
            ArrayList<Task> taskItems,
            TaskList tasks) {
        StringBuilder sb = new StringBuilder("");
        int counter = 1;
        for (int i = 1; i <= tasks.getTaskListLength(); i++) {
            Task task = taskItems.get(i - 1);
            Optional<Date> date = task.getOptionalDate();
           if (counter == 1
                   && hasDateAndIsEquivalent(date, userInputDate)) {
               sb.append(counter + ". " + task.toString());
               counter++;
            } else if (counter != 1
                   && hasDateAndIsEquivalent(date, userInputDate)) {
                sb.append("\n" + counter + ". " + task.toString());
                counter++;
            }
        }
        return sb.toString();
    }

    private boolean hasDateAndIsEquivalent(
            Optional<Date> date,
            Date userInputDate) {
        boolean hasDate = date.isPresent();
        boolean isEquivalentDates = false;
        if (hasDate) {
            isEquivalentDates = userInputDate.
                    isEquivalentDate(date.get());
        }
        return hasDate && isEquivalentDates;
    }
}
