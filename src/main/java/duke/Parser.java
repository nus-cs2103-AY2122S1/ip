package duke;
import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.commands.OnDateCommand;
import duke.commands.UnknownCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

public class Parser {

    /**
     * Parses user input and responds with corresponding commands.
     * 
     * @param userInput input by user
     * @return corresponding command of the input
     * @throws DukeException
     */
    public static Command parse(String userInput) throws DukeException {
        int firstBlankIndex = userInput.indexOf(" ");
        String commandWord = firstBlankIndex == -1 ? userInput : userInput.substring(0, firstBlankIndex);
        switch (commandWord) {
        case "bye" : return new ExitCommand();
        case "list" : return new ListCommand();
        case "on" : {
            String dateString = userInput.substring(3);
            return new OnDateCommand(dateString);
        }
        case "done" : {
            int taskNum = Integer.parseInt(userInput.substring(5));
            return new DoneCommand(taskNum);
        } 
        case "delete": {
            int taskNum = Integer.parseInt(userInput.substring(7));
            return new DeleteCommand(taskNum);
        }
        case "todo": {
            if (userInput.length() == 4) {
                throw new DukeException("OOPS!!! The task name of a todo cannot be empty.");
            }
            Todo todo = new Todo(userInput.substring(5));
            return new AddCommand(todo);
        }
        case "deadline": {
            if (userInput.length() == 8) {
                throw new DukeException("OOPS!!! The task name of a deadline cannot be empty.");
            } else if (!userInput.contains("/by")) {
                throw new DukeException("Please input date for your deadline!");
            }
            String name = userInput.substring(9).split("/by")[0];
            String date = userInput.substring(9).split("/by")[1];
            Deadline deadline = new Deadline(name, date);
            return new AddCommand(deadline);
        }
        case "event" : {
            if (userInput.length() == 5) {
                throw new DukeException("OOPS!!! The task name of an event cannot be empty.");
            } else if (!userInput.contains("/at")) {
                throw new DukeException("Please input time for your event!");
            }
            String name = userInput.substring(6).split("/at")[0];
            String time = userInput.substring(6).split("/at")[1];
            //System.out.println(time);
            Event event = new Event(name, time);
            return new AddCommand(event);
        }
        default : return new UnknownCommand();
        }
    }
}
