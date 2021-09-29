package Duke.command;

import Duke.Storage;
import Duke.TaskList;
import Duke.task.Deadline;
import Duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(String input) {
        String[] data = input.split(" ", 2)[1].split(" /by ");
        String task = data[0];
        LocalDate time = LocalDate.parse(data[1]);
        this.deadline = new Deadline(task, time);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.deadline);
    }
}
