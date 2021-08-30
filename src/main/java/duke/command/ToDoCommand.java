package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Command to add a todo into the task list
 */
public class ToDoCommand extends Command {
    public ToDoCommand() {
        setCommandString("todo");
    }

    /**
     * Parses the user input for a name,
     * then creates the ToDo and adds it into the taskList
     *
     * @param input Full user input
     * @param taskList The list of tasks
     * @return The response
     * @throws DukeException Any exception caught when executing this command
     */
    @Override
    public String parse(String input, TaskList taskList) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input the todo's name!");
        }

        String name = input.substring(getCommandLength()).strip();

        if (name.equals("")) {
            throw new DukeException("Please input the todo's name!");
        }

        ToDo toDo = new ToDo(name);
        return taskList.addTask(toDo);
    }
}
