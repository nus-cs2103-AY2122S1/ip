package duke.command;

import duke.Duke;
import duke.task.ToDo;
import duke.exception.DukeException;

public class ToDoCommand extends Command {
    public ToDoCommand() {
        setCommandString("todo");
    }

    /**
     * Parses the user input for a name,
     * then creates the ToDo and adds it into the taskList
     *
     * @param input Full user input
     * @throws DukeException Any exception caught when executing this command
     */
    @Override
    public void parse(String input) throws DukeException {
        if (input.length() <= getCommandLength()) {
            throw new DukeException("Please input the todo's name!");
        }

        String name = input.substring(getCommandLength()).strip();

        if (name.equals("")) {
            throw new DukeException("Please input the todo's name!");
        }

        ToDo toDo = new ToDo(name);
        Duke.taskList.addTask(toDo);
    }
}
