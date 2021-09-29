package Duke.command;

import Duke.Storage;
import Duke.TaskList;
import Duke.task.Deadline;
import Duke.task.Event;
import Duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventCommand extends Command {
    private Event event;

    public EventCommand(String input) {
        String[] s = input.split(" ", 2)[1].split(" /at ");
        String task = s[0];
        LocalDate time = LocalDate.parse(s[1]);
        this.event = new Event(task, time);
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.event);
    }
}
