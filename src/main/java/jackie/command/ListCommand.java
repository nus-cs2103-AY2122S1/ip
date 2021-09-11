package jackie.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import jackie.JackieException;
import jackie.Storage;
import jackie.TaskList;
import jackie.Ui;

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
     * @throws jackie.JackieException Will be thrown if information provided are insufficient/incorrect.
     */
    public ListCommand(String... command) throws JackieException {
        if (isListOps(command)) {
            // first element is the time
            this.localDate = LocalDate.parse(command[1]);
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
    public static boolean isListOps(String... input) throws JackieException {
        int length = input.length;
        if (length < 2) {
            // guard clause
            return false;
        }
        try {
            // first element is the time
            LocalDate holder = LocalDate.parse(input[1]);
            return true;
        } catch (DateTimeParseException e) {
            throw new JackieException(" D: SORZ but I only understand date in yyyy-MM-dd format!");
        }
    }

    /**
     * Implements the execute method from Command superclass.
     * Executes the given listing command accordingly by updating taskList and storage, interacting with ui.
     * Returns a String of system reply when given certain input under execution.
     *
     * @param taskList A jackie.TaskList object that contains an ArrayList of jackie.task.task object to be updated.
     * @param ui A jackie.Ui object that helps to perform interaction when the command is executed.
     * @param storage A jackie.Storage object that helps to update the storage after the execution is done.
     * @return a String of system reply when given certain input under execution.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            System.out.println(ui.emptyList());
            return ui.emptyList();
        } else {
            if (isOps) {
                System.out.println(ui.showScheduleList(taskList, localDate));
                return ui.showScheduleList(taskList, localDate);
            } else {
                System.out.println(ui.showFullList(taskList));
                return ui.showFullList(taskList);
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
