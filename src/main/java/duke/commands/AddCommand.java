package duke.commands;

import java.time.format.DateTimeParseException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.exceptions.InvalidInputException;
import duke.tasks.*;

/**
 * Adds a task to the task list
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String taskDescription;

    /**
     * Constructor to create an AddCommand
     */
    public AddCommand(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    public String getTaskType() {
        return taskType;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Adds the specified task to the tasklist and reflects the result via the Ui.
     * @throws InvalidInputException if date is not indicated or it is in the incorrect format.
     */
    public String execute(TaskList task, Storage storage) throws InvalidInputException {
        if (taskDescription.equals("")) {
            throw new InvalidInputException(printEmptyDescription(taskType));
        } else {
            Task t;
            if (taskType.equals("todo")) {
                t = new Todo(taskDescription);
            } else if (taskType.equals("deadline")) {
                try {
                    String[] info = Parser.parseDeadline(taskDescription);
                    t = new Deadline(info[0], info[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("Please specify the date for the deadline");
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Please specify date in 'YYYY-MM-DD TIME' format");
                }
            } else if (taskType.equals("dowithin")){
                try {
                    String[] info = Parser.parseDoWithin(taskDescription);
                    t = new DoWithinPeriod(info[0], info[1], info[2]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("Please specify both start/end dates for the task");
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Please specify date in 'YYYY-MM-DD TIME' format");
                }
            } else {
                try {
                    String[] info = Parser.parseEvent(taskDescription);
                    t = new Event(info[0], info[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("Please specify the date for the event");
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("Please specify date in YYYY-MM-DD TIME' format");
                }
            }
            task.addTask(t);
            return showAddTaskMessage(t.toString(), task.getNumTasks());
        }
    }

    /**
     * Prints the message with information on the task type if the user input enters an empty description
     */
    public String printEmptyDescription(String taskType) {
        return String.format("OOPS!!! The description of a %s cannot be empty.", taskType);
    }

    /**
     * Displays the description of the newly added task.
     */
    public String showAddTaskMessage(String message, int numTotalTasks) {
        return String.format("Got it. I've added this task: \n   %s\n"
                + "Now you have %d tasks in the list.", message, numTotalTasks);
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
