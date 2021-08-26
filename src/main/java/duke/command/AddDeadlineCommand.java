package duke.command;

import java.time.format.DateTimeParseException;
import duke.task.Deadline;
import duke.task.Task;

public class AddDeadlineCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "deadline";

    public static Command create(String userInput) throws MalformedCommandException {
        try {
            String userParams = userInput.split(" ", 2)[1];
            String[] userParamsSplit = userParams.split(" /", 2);
            String description = userParamsSplit[0];
            String by = "";
            if(userParamsSplit[1].startsWith("by")) {
                by = userParamsSplit[1].replaceFirst("by", "").stripLeading();
            }
            Task task = new Deadline(description, by);
            return new AddDeadlineCommand(task);
        } catch(ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new MalformedCommandException("Creating an deadline needs to follow the following format: " +
                "deadline [description] /by [d/MM/yyyy HHmm]");
        }
    }

    private AddDeadlineCommand(Task task) {
        this.task = task;
    }
}
