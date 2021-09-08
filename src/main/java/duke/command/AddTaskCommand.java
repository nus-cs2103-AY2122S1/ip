package duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Class to add different types of tasks.
 */
public class AddTaskCommand implements Command {
    private String type;
    private String detail;
    private Task task;

    /**
     * Constructor to initialize AddTaskCommand.
     *
     * @param arr of length 2 which contains the task type and description.
     */
    public AddTaskCommand(String... arr) {
        assert arr.length == 2;
        this.type = arr[0];
        this.detail = arr[1];
    }
    /**
     * Constructor to initialize AddTaskCommand.
     */
    public AddTaskCommand() { }

    /**
     * Execute command.
     *
     * @param taskList TaskList that manages all current tasks.
     * @param ui Ui used to print messages.
     * @param storage Loads and saves the tasks to a txt file.
     * @throws DukeException Thrown if there are input/parsing errors.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (type.equals("todo")) {
            task = new ToDo(detail);
        } else if (type.equals("deadline")) {
            String[] description = detail.split("/by ", 2);
            if (description.length == 1) {
                throw new DukeException("Invalid Deadline entry. "
                        + "Try something like: deadline HW due /by 19/8/2021 14:00");
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    task = new Deadline(description[0].trim(), LocalDateTime.parse(description[1].trim(), formatter));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Unable to parse time. "
                           + "Valid deadline format: deadline do HW /by 19/08/2021 23:59");
                }
            }

        } else {
            String[] description = detail.split("/at ", 2);
            if (description.length == 1) {
                throw new DukeException("Invalid Event entry. "
                        + "Try something like: event meeting /at 19/08/2021 14:00");
            } else {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                    task = new Event(description[0].trim(), LocalDateTime.parse(description[1].trim(), formatter));
                } catch (DateTimeParseException e) {
                    throw new DukeException("Unable to parse time."
                            + "Valid event format: event meeting /at 19/08/2021 23:59");
                }
            }
        }

        if (task != null) {
            taskList.addTask(task);
            ui.echo("Got it! I added this task: " + task);
        }
    }

    /**
     * Returns a boolean to determine if Duke should stop running.
     *
     * @return A boolean false as this is not an exit command.
     */
    @Override
    public boolean isRunning() {
        return false;
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return "todo <detail of task e.g read book>  [add todo] \n"
                + "       deadline <detail of deadline> /by <dd/MM/yyyy HH:mm>  [add deadlines] \n"
                + "       event <detail of event> /at <dd/MM/yyyy HH:mm>  [add events] ";
    }
}
