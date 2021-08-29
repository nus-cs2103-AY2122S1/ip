package duke;

import duke.commands.*;
import duke.exceptions.NoSuchCommandException;
import duke.tasks.DeadlinesTask;
import duke.tasks.EventsTask;
import duke.tasks.Task;
import duke.tasks.ToDosTask;


public class Parser {
    Parser(){

    }

    public static Command parse(String input) throws NoSuchCommandException {
        switch(input.split(" ")[0]) {
            case "list":
                return new ListCommand();
            case "done":
                try {
                    Integer index = Integer.parseInt(input.split(" ")[1]);
                    return new DoneCommand(index);
                } catch (NullPointerException ex){
                    throw new NoSuchCommandException("Invalid Command");
                }
            case "delete":
                try {
                    Integer index = Integer.parseInt(input.split(" ")[1]);
                    return new DeleteCommand(index);
                } catch (NullPointerException ex){
                    throw new NoSuchCommandException("Invalid Command");
                }

            case "todo":
                try {
                    ToDosTask.isLegitInput(input);
                    return new AddCommand(new ToDosTask(ToDosTask.getNameInput(input), false));
                } catch (NoSuchCommandException ex) {
                    throw new NoSuchCommandException("Invalid Command");
                }

            case "deadline":
                try {
                    DeadlinesTask.isLegitInput(input);
                    return new AddCommand(new DeadlinesTask(DeadlinesTask.getNameInput(input), false, Task.parseDateTime(DeadlinesTask.getDeadlineInput(input))));
                } catch (NoSuchCommandException ex) {
                    throw new NoSuchCommandException("Invalid Command");
                }

            case "event":
                try {
                    EventsTask.isLegitInput(input);
                    return new AddCommand(new EventsTask(EventsTask.getNameInput(input), false, Task.parseDateTime(EventsTask.getDeadlineInput(input))));
                } catch (NoSuchCommandException ex) {
                    throw new NoSuchCommandException("Invalid Command");
                }
            default:
                throw new NoSuchCommandException("Invalid Command");
        }
    }

}
