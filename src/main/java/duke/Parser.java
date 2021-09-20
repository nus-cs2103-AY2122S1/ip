package duke;

import duke.commands.AddCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.NoSuchCommandException;
import duke.tasks.DeadlinesTask;
import duke.tasks.EventsTask;
import duke.tasks.FixedDurationTask;
import duke.tasks.Task;
import duke.tasks.ToDosTask;


/**
 * Parser that takes in the user input and decides what command to throw
 */

public class Parser {
    Parser(){

    }

    /**
     * Method that takes in user input and decides what command to return
     *
     * @param input User Input
     * @throws NoSuchCommandException If the input is invalid
     */

    @SuppressWarnings("checkstyle:WhitespaceAround")
    public static Command parse(String input) throws NoSuchCommandException {
        Integer index;
        try {
            switch(input.split(" ")[0]) {
            case "list":
                return new ListCommand();
            case "done":
                index = Integer.parseInt(input.split(" ")[1]);
                return new DoneCommand(index);
            case "delete":
                index = Integer.parseInt(input.split(" ")[1]);
                return new DeleteCommand(index);
            case "todo":
                ToDosTask.isLegitInput(input);
                return new AddCommand(new ToDosTask(ToDosTask.getNameInput(input), false));
            case "deadline":
                DeadlinesTask.isLegitInput(input);
                return new AddCommand(new DeadlinesTask(DeadlinesTask.getNameInput(input),
                        false, Task.parseDateTime(DeadlinesTask.getDeadlineInput(input))));
            case "event":
                EventsTask.isLegitInput(input);
                return new AddCommand(new EventsTask(EventsTask.getNameInput(input),
                        false, Task.parseDateTime(EventsTask.getDeadlineInput(input))));
            case "fixed":
                FixedDurationTask.isLegitInput(input);
                return new AddCommand(new FixedDurationTask(FixedDurationTask.getNameInput(input),
                        false, FixedDurationTask.getDurationInput(input)));
            case "find":
                Task.isLegitFindInput(input);
                return new FindCommand(input.split(" ")[1]);
            case "bye":
                return new ByeCommand();
            default:
                throw new NoSuchCommandException("Invalid Command");
            }
        } catch (NullPointerException | NoSuchCommandException ex) {
            throw new NoSuchCommandException("Invalid Command");
        }
    }

}
