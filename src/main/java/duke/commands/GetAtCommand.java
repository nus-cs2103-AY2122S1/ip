package duke.commands;

import duke.tasks.Task;
import duke.tasks.TaskWithDate;
import duke.utils.DukeException;
import duke.utils.Storage;
import duke.utils.TaskList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.time.DateTimeException;

/**
 * Represent a retrieval action to be executed.
 */
public class GetAtCommand extends Command{
    LocalDate date;

    public GetAtCommand(String datestring) throws DukeException {
        try {
            datestring = datestring.split("getat ")[1];
            if (!datestring.matches("\\d{4}-\\d{2}-\\d{2}")) {
                throw new DukeException("☹ Yo bro pls give the time in yyyy-mm-dd format thx.");
            }
            date = LocalDate.parse(datestring);
        }  catch (IndexOutOfBoundsException e) {
            throw new DukeException("Hey this command is invalid! Check out the proper format please!");
        } catch (DateTimeException e) {
            throw new DukeException("Invalid date!!");
        }
    }
    /**
     * prints out the tasks at with a specific deadline/timing
     *
     * @param tasks    the tasklist
     * @param storage Persistent storage for data
     */
    @Override
    public String execute(TaskList tasks,Storage storage){
        ArrayList<Task> userInputs = tasks.getTasks();
        String tasksToPrint = "";
        assert (tasksToPrint == "");
        for (int i = 0; i < userInputs.size(); i++) {
            Task task = userInputs.get(i);
            if (task instanceof TaskWithDate) {
                // We know that the incoming task is a TaskWithDate, so its safe to type cast it
                TaskWithDate datedTask = (TaskWithDate) task;
                if (datedTask.date.equals(date)) {
                    // Print out only if its equals to the date of interest
                    tasksToPrint += (task + " ");
                }
            }
        }
        return tasksToPrint;
    }

    @Override
    public boolean isExit(){
        return false;
    }
}

