package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class eventCommand extends Command {
    private String command;
    
    public eventCommand(String command) {
        super(command);
        this.command = command;
    }
    
    public String toString() {
        return "This is an event command";
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
                Ui.taskResponse(task);
                storage.writeToFile("./duke.txt", taskList);
            } catch (DateTimeParseException e) {
                DukeException exp = new InvalidDateTimeException("The format of your command is incorrect! It should be event/at " +
                        "<yyyy-mm-dd HHmm>");
                System.out.println(exp);
            }
        } 
    }
}