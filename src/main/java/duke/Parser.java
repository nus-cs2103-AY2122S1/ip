package duke;

import duke.commands.*;
import duke.exceptions.NoSuchCommandException;
import duke.tasks.DeadlinesTask;
import duke.tasks.EventsTask;
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

    public static Command parse(String input) throws NoSuchCommandException {
        switch(input.split(" ")[0]) {
<<<<<<< HEAD
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
<<<<<<< HEAD
            case "find":
                try {
                    Task.isLegitFindInput(input);
                    return new FindCommand(input.split(" ")[1]);
                } catch (NoSuchCommandException ex) {
                    throw new NoSuchCommandException("Invalid Command");
                }
=======
            case "bye":
                return new ByeCommand();
>>>>>>> branch-A-JavaDoc
            default:
=======
        case "list":
            return new ListCommand();
        case "done":
            try {
                Integer index = Integer.parseInt(input.split(" ")[1]);
                return new DoneCommand(index);
            } catch (NullPointerException ex){
>>>>>>> branch-A-CodingStandard
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
