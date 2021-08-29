package duke.command;

import java.time.format.DateTimeParseException;

import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * The type Add command that adds a user-specified task to a given list of tasks.
 */
public class AddCommand extends Command {

    /** Enum that specifies the type of task being added */
    private final AddCommandType addType;
    /** User inputted string */
    private final String userInput;
    /** List of tasks to run the command on */
    private final TaskList tasks;

    /**
     * Instantiates a new Add command.
     *
     * @param addType   task type that is being added using AddCommand
     * @param userInput user-inputted string
     * @param tasks     list tasks to add the new task to
     */
    public AddCommand(AddCommandType addType, String userInput, TaskList tasks) {
        this.addType = addType;
        this.userInput = userInput;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        Task newTask = null;
        String[] inputs = this.userInput.split(" ", 2);
        try {
            switch (this.addType) {
            case todo: {
                newTask = new Todo(inputs[1]);
                break;
            }
            case event: {
                String[] args = inputs[1].split(" /at ", 2);
                String[] datetimeArgs = args[1].split(" ", 2);
                if (datetimeArgs.length == 1) {
                    newTask = new Event(args[0], args[1]);
                } else {
                    newTask = new Event(args[0], datetimeArgs[0], datetimeArgs[1]);
                }
                break;
            }
            case deadline: {
                String[] args = inputs[1].split(" /by ", 2);
                String[] datetimeArgs = args[1].split(" ", 2);
                if (datetimeArgs.length == 1) {
                    newTask = new Deadline(args[0], args[1]);
                } else {
                    newTask = new Deadline(args[0], datetimeArgs[0], datetimeArgs[1]);
                }
                break;
            }
            default:
                break;
            }
        } catch (DateTimeParseException e) {
            System.out.println(
                    Ui.tabAndFormat(
                            "☹ OOPS!!! Please enter an appropriate date (and optionally, 24-hour time)\n"
                                + "Format: YYYY-MM-DD HH:MM"
                    )
            );
            return;
        }
        this.tasks.add(newTask);
        String returnString = "Got it. I've added this task:\n  "
                + newTask.toString() + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.";
        System.out.println(Ui.tabAndFormat(returnString));
    }


}
