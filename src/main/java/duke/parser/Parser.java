package duke.parser;

import duke.command.Command;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Class providing static parse method for parsing user input
 */
public class Parser {

    /**
     * parse takes in user input and returns the corresponding Command enum
     *
     * @param input String[] of the user input split once by " "
     *
     * @return Command enum corresponding to the input provided
     */
    public static Command parse(String[] input)  {
        if (input[0].equals("bye") || input[0].equals("exit")) {
            return Command.EXIT;
        } else if (input[0].equals("list")) {
            return Command.LIST;
        } else  if (input[0].equals("done") || input[0].equals("delete")) {
            return Command.INDEXCOMMAND;
        } else if (input[0].equals("todo") || input[0].equals("deadline") || input[0].equals("event")) {
            return Command.ADDCOMMAND;
        } else if (input[0].equals("find")) {
            return Command.FIND;
        } else if (input[0].equals("update")) {
            return Command.UPDATE;
        } else {
            return Command.UNKNOWN;
        }
    }

    public static Task parseStorage(String line) {
        String[] taskArray = line.split(" : ");
        Task task;
        if (taskArray[0].equals("T")) {
            task = new Todo(taskArray[2], taskArray[1].equals("1"));
        } else  if (taskArray[0].equals("D")) {
            task = new Deadline(taskArray[2], taskArray[1].equals("1"), taskArray[3]);
        } else {
            task = new Event(taskArray[2], taskArray[1].equals("1"), taskArray[3]);
        }
        return task;
    }
}
