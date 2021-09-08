package bobbybot.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import bobbybot.enums.BotCommand;
import bobbybot.exceptions.BobbyException;
import bobbybot.exceptions.InvalidArgumentException;
import bobbybot.tasks.Deadline;
import bobbybot.tasks.Event;
import bobbybot.tasks.Task;
import bobbybot.tasks.ToDo;
import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class AddCommand extends Command {
    public static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-uuuu HH:mm");
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
    public AddCommand(String description, String timeArg, BotCommand type) throws InvalidArgumentException {
        switch(type) {
        case TODO:
            this.toAdd = new ToDo(description);
            break;
        case EVENT:
            this.toAdd = new Event(description, timeArg);
            break;
        case DEADLINE:
            try {
                LocalDateTime dateBy = LocalDateTime.parse(timeArg, DT_FORMATTER);
                this.toAdd = new Deadline(description, dateBy);
            } catch (DateTimeParseException e) {
                System.out.println("Please input deadline date in the following format: [dd-mm-yyyy hh:mm]");
                throw new InvalidArgumentException("Please input deadline date in the following format:"
                        + " [dd-mm-yyyy hh:mm]");
            }
            break;
        default:
            throw new InvalidArgumentException("Unexpected value: " + type);
        }
    }

    /**
     * Executes Add command
     *  @param tasks task list
     * @param ui ui
     * @param storage storage
     * @param contacts
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        tasks.addTask(toAdd);
        try {
            storage.save(tasks);
        } catch (BobbyException e) {
            System.out.println(e.getMessage());
        }

        response = "Got it. I've added this task:\n  " + toAdd + "\n"
                + "Now you have " + tasks.getTasks().size() + " tasks in the list.";
    }
}
