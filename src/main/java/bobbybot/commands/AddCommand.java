package bobbybot.commands;

import bobbybot.exceptions.InvalidArgumentException;
import bobbybot.tasks.Deadline;
import bobbybot.tasks.Event;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
    private Task toAdd;

    /**
     * Prepares to add todo task with only a description as argument
     * @param description description of task
     */
    public AddCommand(String description) {
        this.toAdd = new ToDo(description);
    }

    /**
     * Prepares to add task with time argument
     * @param description description of task
     * @param timeArg time argument string
     * @param type event or deadline
     */
    public AddCommand(String description, String timeArg, String type) throws InvalidArgumentException {
        type = type.toLowerCase();
        switch(type) {
        case "todo":
            this.toAdd = new ToDo(description);
            break;
        case "event":
            this.toAdd = new Event(description, timeArg);
            break;
        case "deadline":
            try {
                LocalDateTime dateBy = LocalDateTime.parse(timeArg, DT_FORMATTER);
                this.toAdd = new Deadline(description, dateBy);
            } catch (DateTimeParseException e) {
                this.toAdd = null;
                System.out.println("Please input deadline date in the following format: [dd-mm-yyyy hh:mm]");
            }
            break;
        default:
            throw new InvalidArgumentException("Unexpected value: " + type);
        }
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(toAdd);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.out.println("Could not save tasks to database!\n");
            e.printStackTrace();
        }

        return "Got it. I've added this task:\n  " + toAdd + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}
