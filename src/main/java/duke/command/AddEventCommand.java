package duke.command;

import java.time.format.DateTimeParseException;
import duke.task.Event;
import duke.task.Task;

public class AddEventCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "event";

    public static Command create(String userInput) throws MalformedCommandException {
        try {
            String userParams = userInput.split(" ", 2)[1];
            String[] userParamsSplit = userParams.split(" /", 2);
            String description = userParamsSplit[0];
            String at = "";
            if(userParamsSplit[1].startsWith("at")) {
                at = userParamsSplit[1].replaceFirst("at", "").stripLeading();
            }
            Task task = new Event(description, at);
            return new AddEventCommand(task);
        } catch(ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new MalformedCommandException("Creating an event needs to follow the following format: " +
                "event [description] /at [d/MM/yyyy HHmm]");
        }
    }

    private AddEventCommand(Task task) {
        this.task = task;
    }
}
