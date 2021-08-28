package duke.command;

import java.util.ArrayList;

import duke.task.Task;
import duke.util.ToDoList;
import duke.util.Ui;

/**
 * This class encapsulates the command dealing with filtering the tasks list using a date/time specified.
 *
 * @author Tan Yi Guan
 * @version CS2103T AY21/22 Semester 1
 */
public class FilterCommand extends Command {
    private final ToDoList list;

    public FilterCommand(ToDoList list) {
        this.list = list;
    }

    @Override
    public String getResponse(String input) {
        if (input.split(" ").length < 2) {
            return "Please include a date/time for me to search after the filter command!";
        } else if (input.split(" ").length > 2) {
            return "Sorry, I can only search using one date/time.";
        }

        String[] extracted = input.split(" ", 2);
        ArrayList<Task> extractedTask = list.filterList(extracted[1]);

        if (extractedTask.size() == 0) {
            return "There are no tasks on this day.";
        } else {
            String output = String.format("Here are your tasks for this day:%s", Ui.LINE_SEPARATOR);

            int count = 1;
            for (Task t : extractedTask) {
                output = output.concat(String.format("[%d]. %s", count++, t + Ui.LINE_SEPARATOR + "\t\t"));
            }

            return output;
        }
    }
}
