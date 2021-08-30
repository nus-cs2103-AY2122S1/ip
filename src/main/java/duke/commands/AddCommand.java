package duke.commands;

import java.time.format.DateTimeParseException;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.InvalidInputException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

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
    public void execute(TaskList task, Ui ui, Storage storage) throws InvalidInputException {
        if (taskDescription.equals("")) {
            throw new InvalidInputException(ui.printEmptyDescription(taskType));
        } else {
            Task t;
            if (taskType.equals("todo")) {
                t = new Todo(taskDescription);
            } else if (taskType.equals("deadline")) {
                try {
                    String[] info = Parser.parseDeadline(taskDescription);
                    t = new Deadline(info[0], info[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("   INVALID INPUT: Please specify the date for the deadline");
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("   INVALID INPUT: Please specify date in "
                            + "'YYYY-MM-DD TIME' format");
                }
            } else {
                try {
                    String[] info = Parser.parseEvent(taskDescription);
                    t = new Event(info[0], info[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new InvalidInputException("   INVALID INPUT: Please specify the date for the event");
                } catch (DateTimeParseException e) {
                    throw new InvalidInputException("   INVALID INPUT: Please specify date in "
                            + "'YYYY-MM-DD TIME' format");
                }
            }
            task.addTask(t);
            ui.showAddTaskMessage(t.toString(), task.getNumTasks());
        }
    }

    /**
     * Helper function to tell Duke to continue reading inputs
     */
    public boolean isExit() {
        return false;
    }
}
