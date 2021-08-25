package duke.commands;

import java.time.LocalDate;
import java.util.*;

import duke.tasks.*;
import duke.utils.*;

public class AddCommand extends Command {
    public enum Type {
        todo, deadline, event
    }

    Type type;
    String input;

    public AddCommand(String input) {
        this.input = input;
        if (input.startsWith("todo")) {
            this.type = Type.todo;
        } else if (input.startsWith("deadline")) {
            this.type = Type.deadline;
        } else {
            this.type = Type.event;
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String description;
        String time;
        Task task;
        try {
            if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
                throw new DukeException("☹ OOPS!!! The description of a " + input + " cannot be empty.");
            }

            if (this.type == Type.todo) {
                description = input.split("todo ")[1];
                task = new Todo(description);
                Storage.saveToDisc("todo" + "," + "0"  + "," + description);
            } else if (this.type == Type.deadline) {
                description = input.split("deadline ")[1].split(" /")[0];
                time = input.split("/by ")[1];
                if (!time.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    throw new DukeException("☹ Yo bro pls give the time in yyyy-mm-dd format thx.");
                }
                task = new Deadline(description, time);
                Storage.saveToDisc("deadline" + "," + "0" + "," + description + "," + time);
            } else {
                description = input.split("event ")[1].split(" /")[0];
                time = input.split("/at ")[1];
                if (!time.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    throw new DukeException("☹ Yo bro pls give the time in yyyy-mm-dd format thx.");
                }
                task = new Event(description, time);
                Storage.saveToDisc("event" + "," + "0"  + "," + description + "," + time);
            }
            tasks.addTask(task);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
