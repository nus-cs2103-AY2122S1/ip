import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class deadlineCommand extends Command {
    private String command;
    
    public deadlineCommand(String command) {
        super(command);
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage) {
        if (command.length() <= 9) {
            DukeException exp = new EmptyDescriptionException("OOPS!!! The description of a deadline cannot be empty.");
            System.out.println(exp);
        } else {
            String[] parts = command.split("/", 2);
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].substring(3).trim(), dtf);
                Task task = new Deadline(parts[0].substring(9), dateTime);
                taskList.addTask(task);
                Ui.taskResponse(task);
                storage.writeToFile("./duke.txt", taskList);
            } catch (DateTimeParseException e) {
                DukeException exp = new InvalidDateTimeException("The format of your command is incorrect! It should be deadline/by " +
                        "<yyyy-mm-dd HHmm>");
                System.out.println(exp);
            }
        }
    }
}