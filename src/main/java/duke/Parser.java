package main.java.duke;
import main.java.duke.commands.AddCommand;
import main.java.duke.commands.AddContactCommand;
import main.java.duke.commands.Command;
import main.java.duke.commands.DeleteCommand;
import main.java.duke.commands.DeleteContactCommand;
import main.java.duke.commands.DoneCommand;
import main.java.duke.commands.ExitCommand;
import main.java.duke.commands.FindCommand;
import main.java.duke.commands.FindContactCommand;
import main.java.duke.commands.ListCommand;
import main.java.duke.commands.ListContactsCommand;
import main.java.duke.commands.OnDateCommand;
import main.java.duke.commands.UnknownCommand;
import main.java.duke.extensions.Contact;
import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Event;
import main.java.duke.tasks.Todo;

/**
 * A parser that parses user input.
 */
public class Parser {

    /**
     * Parses user input and responds with corresponding commands.
     *
     * @param userInput input by user
     * @return corresponding command of the input
     * @throws DukeException
     */
    public static Command parse(String userInput) throws DukeException {
        if (userInput == "") {
            throw new DukeException("A command cannot be empty!");
        }
        int firstBlankIndex = userInput.indexOf(" ");
        String commandWord; if (firstBlankIndex == -1) {
            commandWord = userInput;
        } else {
            commandWord = userInput.substring(0, firstBlankIndex);
        }
        switch (commandWord) {
        case "bye" : {
            return new ExitCommand();
        }
        case "list" : {
            return new ListCommand();
        }
        case "on" : {
            String dateString = userInput.substring(3);
            return new OnDateCommand(dateString);
        }
        case "find" : {
            String keyword = userInput.substring(5);
            return new FindCommand(keyword);
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
            assert userInput.length() > 5 : "User length should be greater than 5";
            String name = userInput.substring(6).split("/at")[0];
            String time = userInput.substring(6).split("/at")[1];
            Event event = new Event(name, time);
            return new AddCommand(event);
        }
        case "friend" : {
            if (userInput.length() == 6) {
                throw new DukeException("OOPS!!! The name of a contact cannot be empty.");
            } else if (!userInput.contains(":")) {
                throw new DukeException("Please follow the format for adding a contact!");
            }
            String friendName = userInput.substring(7).split(":")[0];
            String contactNumber = userInput.substring(7).split(":")[1];
            Contact contact = new Contact(friendName, Integer.parseInt(contactNumber));
            return new AddContactCommand(contact);
        }
        case "deleteC" : {
            int contactIndex = Integer.parseInt(userInput.substring(8));
            return new DeleteContactCommand(contactIndex);
        }
        case "searchC" : {
            String keyword = userInput.substring(8);
            return new FindContactCommand(keyword);
        }
        case "listC" : {
            return new ListContactsCommand();
        }
        default : {
            return new UnknownCommand();
        }
        }
    }
}
