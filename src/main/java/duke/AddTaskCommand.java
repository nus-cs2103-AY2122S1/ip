package duke;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidTaskException;

public class AddTaskCommand extends UndoableCommand {
    public AddTaskCommand(TaskList tasks, String input) {
        super(tasks, input);
    }

    /**
     * A method to add a Task inside a TaskList
     *
     * @return String representation of a task after it is added.
     * @throws EmptyDescriptionException Thrown when users do not give a task description.
     * @throws InvalidTaskException Thrown when users give incorrect input for task.
     * @throws InvalidDeadlineException Thrown when users give an incorrect deadline.
     */
    @Override
    public String run() throws DukeException {
        //if the task only contain 1 word
        if (input.split(" ").length == 1) {
            if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                throw new EmptyDescriptionException(input);
            }
            throw new InvalidTaskException();
        }

        //if task contains more than 1 word, but keyword is wrong
        if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
            throw new InvalidTaskException();
        }

        Task task;
        if (input.startsWith("todo")) {
            task = new ToDos(input.substring(5));
        } else if (input.startsWith("deadline")) {
            String[] message = input.split("/by ");
            if (message.length != 2) {
                throw new InvalidTaskException();
            }
            task = new Deadline(message[0].substring(9), message[1]);
        } else {
            String[] message = input.split("/at ");
            if (message.length != 2) {
                throw new InvalidTaskException();
            }
            task = new Event(message[0].substring(6), message[1]);
        }
        return tasks.addTask(task);
    }

    @Override
    public String undo() {
        return tasks.deleteTask(tasks.getList().size());
    }
}
