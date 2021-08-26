import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String command;
    private String taskType;

    public AddCommand(String command, String taskType) {
        super(command);
        this.taskType = taskType;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] addTask = command.split(" +", 2);
        Task task;
        if (taskType.equals("todo")) {
            task = new Todo(addTask[1]);
        } else if (taskType.equals("deadline")) {
            try {
                LocalDate date = LocalDate.parse(addTask[3].trim());
                task = new Deadline(addTask[1], date);
            } catch (DateTimeParseException e) {
                throw new DukeException(ui.dateError());
            }
        } else {
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(addTask[3].trim(), dtf);
                task = new Event(addTask[1], dateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException(ui.dateTimeError());
            }
        }

        tasks.add(task);
        ui.showLine();
        ui.addTask(task);
        ui.showLine();
    }
}
