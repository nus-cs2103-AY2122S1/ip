package commands;

import java.time.LocalDate;
import java.util.*;
import tasks.*;
import utils.*;

public class GetAtCommand extends Command{
    LocalDate date;

    public GetAtCommand(String datestring) throws DukeException{
        datestring = datestring.split("getat ")[1];
        if (!datestring.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DukeException("☹ Yo bro pls give the time in yyyy-mm-dd format thx.");
        }
        date = LocalDate.parse(datestring);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ArrayList<Task> userInputs = tasks.getTasks();
        for (int i = 0; i < userInputs.size(); i++) {
            Task task = userInputs.get(i);
            if (task instanceof TaskWithDate) {
                // We know that the incoming task is a TaskWithDate, so its safe to type cast it
                TaskWithDate datedTask = (TaskWithDate) task;
                if (datedTask.date.equals(date)) {
                    // Print out only if its equals to the date of interest
                    System.out.println((i + 1) + ". " + task);
                }
            }
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}

