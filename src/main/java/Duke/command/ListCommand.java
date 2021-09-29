package Duke.command;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Duke.TaskList;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks) {
        String reply = "Here are the tasks in your list:\n";
        ArrayList<Task> taskList = tasks.getTasks();
        int count = 0;
        for (Task task : taskList) {
            count++;
            String type = task.getType();
            reply += "  " + count + "." + task.toString() + "\n";
        }
        return reply;
    }
}
