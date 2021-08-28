import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class eventCommand extends Command {
    private String command;
    
    public eventCommand(String command) {
        super(command);
        this.command = command;
    }

    public void execute(TaskList taskList, Storage storage) {
        if (command.length() <= 6) {
            DukeException exp = new EmptyDescriptionException("OOPS!!! The description of an event cannot be empty.");
            System.out.println(exp);
        } else {
            String[] parts = command.split("/");
            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].substring(3).trim(), dtf);
                Task task = new Event(parts[0].substring(6), dateTime);
                taskList.addTask(task);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                storage.writeToFile("./duke.txt", taskList);
            } catch (DateTimeParseException e) {
                DukeException exp = new InvalidDateTimeException("The format of your command is incorrect! It should be event/at " +
                        "<yyyy-mm-dd HHmm>");
                System.out.println(exp);
            }
        } 
    }
}