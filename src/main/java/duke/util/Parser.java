package duke.util;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ScheduleCommand;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    /**
     * Returns a Task recovered from the record.
     * @param record contain the information of a task
     * @return the task recovered from the record.
     */
    static Task convertRecordToTask(String record) {
        String[] strings = record.split("&&");
        assert strings.length > 0 : "Number of words in a record should > 0";
        switch (strings[0]) {
        case "T":
            return new Todo(strings[2], strings[1].equals("Done"));
        case "D":
            return new Deadline(strings[2], strings[3], strings[1].equals("Done"));
        case "E":
            return new Event(strings[2], strings[3], strings[1].equals("Done"));
        default:
            return null;
        }
    }


    /**
     * Parses the input
     * @param input
     * @return Command object
     */
    public static Command parseInput(String input) throws DukeException {
        String[] infos = input.split(" ", 2);
        assert infos.length > 0 : "Number of words in input should > 0";
        String type = infos[0];
        switch (type) {
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "list":
            return new ListCommand(infos);
        case "schedule":
            return new ScheduleCommand(infos);
        case "done":
            return new DoneCommand(infos);
        case "find":
            return new FindCommand(infos);
        case "delete":
            return new DeleteCommand(infos);
        case "deadline":
            //fall through
        case "todo":
            //fall through
        case "event":
            return new AddCommand(infos);
        default:
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }
    }

}


