package duke.command;

import duke.exception.DukeException;
import duke.util.Parser;
import duke.util.ToDoList;

public class DoneCommand extends Command {
    private final ToDoList list;
    private final String input;

    public DoneCommand(ToDoList list, String input) {
        this.list = list;
        this.input = input;
    }

    @Override
    public void execute() throws DukeException {
        int index = Parser.extractIndex(input);
        list.markTaskAsDone(index);
        list.updateData();
    }
}
