package duke.command;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * A class to represent the command of adding
 * a task into TaskList.
 */

public class AddCommand extends Command {

    /**
     * String to represent the command
     */
    private String command;

    /**
     * The type of the task
     */
    private String taskType;


    /**
     * A public constructor to initialize the command
     * and task type to the given one.
     *
     * @param command  A string from the input of the user.
     * @param taskType The type of the task inputted by the user.
     */
    public AddCommand(String command, String taskType) {
        super(command);
        this.command = command;
        this.taskType = taskType;
    }


    public Task addDeadline(String inputDate, String description) throws DukeException {
        try {
            LocalDate date = LocalDate.parse(inputDate.trim());
            return new Deadline(description.trim().split("deadline")[1].trim(), date);
        } catch (DateTimeParseException e) {
            throw new DukeException("OH NO :( I can't seem to understand "
                    + "the date you have entered.\n" + "I can only understand if it "
                            + "is in  the yyyy-mm-dd format..");
        }
    }

    public Task addEvent(String inputDateTime, String description) throws DukeException {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(inputDateTime.trim(), dtf);
            return new Event(description.trim().split("event")[1].trim(), dateTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("OH NO :( I can't seem "
                    + "to understand the date and time you have entered.\n"
                            + "I can only understand if it is in "
                                    + "yyyy-MM-dd HH:mm format..");
        }
    }

    /**
     * Checks if the command is written correctly and executes
     * the command. If the date and time is not indicated wrongly, a
     * DukeException is thrown.
     *
     * @param tasks   The list of tasks stored so far.
     * @param ui      A Ui which deals with interactions with user.
     * @param storage The storage which saves and edits the file.
     * @return The string indicating the command has been executed.
     * @throws DukeException DukeException thrown when format of date/time
     *                       is incorrect.
     */

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        String[] addTask;

        switch (taskType) {
        case "todo":
            addTask = command.split(" +", 2);
            task = new Todo(addTask[1]);
            break;
        case "deadline":
            addTask = command.split("/by", 2);
            task = addDeadline(addTask[1], addTask[0]);
            break;
        case "event":
            addTask = command.split("/at", 2);
            task = addEvent(addTask[1], addTask[0]);
            break;
        default:
            task= null;
            assert false: "invalid task command";
            break;
        }

        tasks.add(task);
        storage.appendToFile(storage.getFileString(task));
        return ui.addTask(task) + System.lineSeparator() + ui.numberOfTasks(tasks);
    }
}
