package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String command;
    private String taskType;


    public AddCommand(String command, String taskType) {
        super(command);
        this.command = command;
        this.taskType = taskType;
    }

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
