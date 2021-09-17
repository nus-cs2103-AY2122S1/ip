package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Command to add a ToDo into the task list.
 */
public class ToDoCommand extends Command {
    public ToDoCommand() {
        setMainCommand("todo");
    }

    /**
     * Parses the user input for a name.
     * Then creates the ToDo and adds it into the taskList.
     *
     * @param input Full user input.
     * @param taskList The list of tasks.
     * @return The response telling the user that a ToDo has been created.
     * @throws DukeException Any exception caught when executing this command.
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        assert taskList != null : "taskList should not be null";

        int firstSpace = input.indexOf(' ');

        // No space after the command
        if (firstSpace == -1) {
            throw new DukeException("Please input the ToDo's name.");
        }

        String name = input.substring(firstSpace).strip();

        if (name.equals("")) {
            throw new DukeException("Please input the ToDo's name.");
        }

        ToDo toDo = new ToDo(name);
        return taskList.addTask(toDo);
    }
}
