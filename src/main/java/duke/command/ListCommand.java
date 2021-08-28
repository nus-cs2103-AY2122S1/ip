package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A Class that extends the Command class.
 * It is specifically designed for a Command for listing.
 *
 * @author Gu Geng
 */
public class ListCommand extends Command {
    private LocalDate localDate;
    private boolean isOps;

    /**
     * Returns a ListCommand object with the information provided.
     *
     * @param command A String containing information that can possibility be used to create an ListCommand object.
     * @throws duke.DukeException Will be thrown if information provided are insufficient/incorrect.
     */
    public ListCommand(String command) throws DukeException {
        if (isListOps(command)) {
            this.localDate = LocalDate.parse(command.substring(5).trim());
            isOps = true;
        } else {
            isOps = false;
        }
    }

    /**
     * Returns true if a valid listing operation is entered.
     * False otherwise.
     *
     * @param input User input of the commands.
     * @return If the input contains a valid listing operations.
     */
    public static boolean isListOps(String input) throws DukeException {
        int length = input.length();
        if (length < 6) {
            return false;
        }
        try {
            LocalDate holder = LocalDate.parse(input.substring(5).trim());
            return true;
        } catch (DateTimeParseException e) {
            throw new DukeException(" ☹ SORZ but I only understand date in yyyy-MM-dd format!");
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given listing command accordingly by updating taskList and storage, interacting with ui.
     *
     * @param taskList A duke.TaskList object that contains an ArrayList of duke.task.task object to be updated.
     * @param ui A duke.Ui object that helps to perform interaction when the command is executed.
     * @param storage A duke.Storage object that helps to update the storage after the execution is done.
     * @throws duke.DukeException Will be thrown if unable to locate/update the storage file.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskList.size() == 0) {
            ui.emptyList();
        } else {
            if (isOps) {
                ui.showScheduleList(taskList, localDate);
            } else {
                ui.showFullList(taskList);
            }
        }
    }

    /**
     * Implements the isExit method from Command superclass.
     * Returns a boolean indicating if the programme terminates after the listing execution.
     *
     * @return A boolean indicating if the programme terminates after the listing execution.
     */
    public boolean isExit() {
        return false;
    }
}
