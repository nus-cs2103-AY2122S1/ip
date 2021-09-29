package Duke.command;

import Duke.Storage;
import Duke.TaskList;
import Duke.exception.DukeException;
import Duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TodoCommand extends Command {
    private Todo todo;

    public TodoCommand(String input) {
        this.todo = todo;

        try {
            if (input.split(" ").length == 1) {
                throw new DukeException("____________________________________________________________\n" +
                        "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                        "____________________________________________________________");
            }
            String task = input.split(" ")[1];
            this.todo = new Todo(task);
        } catch (DukeException e) {
            e.getMessage();
        }
    }

    @Override
    public String execute(TaskList tasks) {
        return tasks.addTask(this.todo);
    }
}
