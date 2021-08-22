package duke.command;

import duke.exception.DukeException;
import duke.task.Task;
import duke.util.Parser;
import duke.util.ToDoList;
import duke.util.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final ToDoList list;
    private final String input;

    public FindCommand(ToDoList list, String input) {
        this.list = list;
        this.input = input;
    }

    @Override
    public void execute() throws DukeException {
        String keyword = Parser.extractKeyword(input);
        ArrayList<Task> matchingTasks = list.searchList(keyword);
        String output = "Here are the matching tasks in your list:" + System.lineSeparator();

        for (int i = 1; i <= matchingTasks.size(); i++) {
            output = output.concat(String.format("\t%d. %s", i, matchingTasks.get(i - 1)));
            output = output.concat(System.lineSeparator());
        }

        Ui.prettyPrint(output);
    }
}
