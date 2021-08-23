package duke.command;

import duke.task.ToDo;
import duke.exception.DukeException;
import duke.Duke;

public class ToDoCommand extends Command {
    public ToDoCommand() {
        setCommandString("todo");
    }

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
