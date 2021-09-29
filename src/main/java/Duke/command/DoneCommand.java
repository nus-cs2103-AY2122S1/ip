package Duke.command;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Duke.Storage;
import Duke.TaskList;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Task;
import Duke.task.Todo;

public class DoneCommand extends Command {

    private int task;

    public DoneCommand(String input) {
        this.task = Integer.parseInt(input.split(" ")[1]);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.markAsDone(this.task);
    }
}
