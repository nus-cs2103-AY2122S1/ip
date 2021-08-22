package duke.command;

import duke.exception.DukeException;
import duke.exception.MultipleDateTimeException;
import duke.task.Task;
import duke.util.ToDoList;
import duke.util.Ui;

import java.util.ArrayList;

public class FilterCommand extends Command {
    private final String input;
    private final ToDoList list;

    public FilterCommand(String input, ToDoList list) {
        this.input = input;
        this.list = list;
    }

    @Override
    public void execute() throws DukeException {
        handleFilter(input);
    }

    public void handleFilter(String input) throws DukeException {
        if (input.split(" ").length < 2) {
            throw new DukeException("MissingDateTimeException: Enter a date/time after your command!");
        } else if (input.split(" ").length > 2) {
            throw new MultipleDateTimeException();
        }

        String[] extracted = input.split(" ", 2);
        ArrayList<Task> extractedTask = list.filterList(extracted[1]);

        if (extractedTask.size() == 0) {
            Ui.prettyPrint("There are no tasks on this day.");
        } else {
            String output = String.format("Here are your tasks for this day:%s", Ui.LINE_SEPARATOR + "\t\t");

            int count = 1;
            for (Task t : extractedTask) {
                output = output.concat(String.format("[%d]. %s", count++, t + Ui.LINE_SEPARATOR + "\t\t"));
            }

            Ui.prettyPrint(output);
        }
    }
}
