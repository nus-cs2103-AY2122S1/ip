package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.ToDoList;

public class DeleteCommand extends Command {
    private String input;
    private final ToDoList list;

    public DeleteCommand(String input, ToDoList list) {
        this.input = input;
        this.list = list;
    }

    @Override
    public void execute() throws DukeException {
        int index = Parser.extractIndex(input);
        list.removeFromList(index);
        list.updateData();
    }
}
