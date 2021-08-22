package duke.command;

import duke.exception.DukeException;
import duke.util.ToDoList;

public class ListCommand extends Command {
    private ToDoList list;

    public ListCommand(ToDoList list) {
        this.list = list;
    }

    @Override
    public void execute() throws DukeException {
        list.printList();
    }
}
