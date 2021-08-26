package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A class to represent the command of adding
 * a task into TaskList.
 */

public class AddCommand extends Command {

    /** String to represent the command */
    private String command;

    /** The type of the task */
    private String taskType;


    /**
     * A public constructor to intialize the command
     * and task type to the given one.
     *
     * @param command A string from the input of the user.
     * @param taskType The type of the task inputted by the user.
     */
    public AddCommand(String command, String taskType) {
        super(command);
        this.command = command;
        this.taskType = taskType;
    }

    /**
     * Checks if the command is written correctly and executes
     * the command. If the date and time is not indicated wrongly, a
     * DukeException is thrown.
     *
     * @param tasks The list of tasks stored so far.
     * @param ui A Ui which deals with interactions with user.
     * @param storage The storage which saves and edits the file.
     * @throws DukeException DukeException thrown when format of date/time
     * is incorrect.
     */

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task;
        if (taskType.equals("todo")) {
            String[] addTask = command.split(" +", 2);
            task = new Todo(addTask[1]);
        } else if (taskType.equals("deadline")) {
            String[] addTask = command.split("/by", 2);
            try {
                LocalDate date = LocalDate.parse(addTask[1].trim());
                task = new Deadline(addTask[0].split("deadline")[1].trim(), date);
            } catch (DateTimeParseException e) {
                throw new DukeException(ui.dateError());
            }
        } else {
            try {
                String[] addTask = command.split("/at", 2);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(addTask[1].trim(), dtf);
                task = new Event(addTask[0].split("event")[1].trim(), dateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException(ui.dateTimeError());
            }
        }

        tasks.add(task);
        ui.addTask(task);
        ui.numberOfTasks(tasks);
        storage.appendToFile(storage.fileString(task));
    }
}
