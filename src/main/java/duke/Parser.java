package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.ToDo;


/**
 * Main function to break down the input of the user.
 */
public class Parser {

    private static final String TIME_PARSE_ERROR =
            "Hmm.. Seems like the time format is foreign to me. \n"
            + "Please use the following format:\n"
            + "yyyy-MM-dd HH:MM (e.g 2020-05-19 15:30)";

    private enum CommandType { Bye, List, Delete, Done, Event, ToDo, Deadline, Find }

    /**
     * Interprets user input and returns the appropriate command
     *
     * @param userInput The string which the user typed in.
     * @return Appropriate command the input represents for the main function to execute.
     * @throws DukeExceptions if the input is lacking argument.
     * @throws DukeExceptions if the input has too much argument.
     * @throws DukeExceptions if the input is not properly formatted.
     * @throws DukeExceptions if the input is empty.
     * @throws DukeExceptions if the first word of the input is not a recognisable command.
     */

    public Command parse(String userInput) throws DukeExceptions {
        String[] splitUserInput = userInput.split(" ", 2);
        CommandType command = changeToEnum(splitUserInput[0]);
        String[] splitCommand = checkArguments(splitUserInput, command);

        switch (command) {
        case Bye: {
            return new ByeCommand();
        }

        case List: {
            return new ListCommand();
        }

        case Delete: {
            return new DeleteCommand(castIndex(splitCommand));
        }

        case Done: {
            return new DoneCommand(castIndex(splitCommand));
        }

        case Event: {
            return new EventCommand(Event.create(splitCommand[1], parseDateTime(splitCommand[2])));
        }

        case Deadline: {
            return new DeadlineCommand(Deadline.create(splitCommand[1], parseDateTime(splitCommand[2])));
        }

        case ToDo: {
            return new ToDoCommand(ToDo.create(splitCommand[1]));
        }

        case Find: {
            return new FindCommand(splitCommand[1]);
        }

        default:
            throw new DukeExceptions("Unknown error!");
        }
    }

    private CommandType changeToEnum(String command) throws DukeExceptions {
        switch (command) {
        case "bye": {
            return CommandType.Bye;
        }

        case "list": {
            return CommandType.List;
        }

        case "delete": {
            return CommandType.Delete;
        }

        case "done": {
            return CommandType.Done;
        }

        case "event": {
            return CommandType.Event;
        }

        case "deadline": {
            return CommandType.Deadline;
        }

        case "todo": {
            return CommandType.ToDo;
        }

        case "find": {
            return CommandType.Find;
        }

        case "": {
            throw new DukeExceptions("Can you type louder.. or type anything? I got nothing..");
        }

        default:
            throw new DukeExceptions("Sorry! I don't recognise your command!");
        }
    }

    private String[] checkArguments(String[] splitUserInput, CommandType command) throws DukeExceptions {

        switch (command) {
        case Bye:
            // Fallthrough
        case List:
            checkForExtraInput(splitUserInput);
            break;
        case Delete:
            // Fallthrough
        case Done:
            checkIndex(splitUserInput);
            break;
        case ToDo:
            //Fallthrough
        case Event:
            //Fallthrough
        case Deadline:
            splitUserInput = checkForDescriptionTask(splitUserInput, command);
            break;
        case Find:
            checkForKeyword(splitUserInput);
            break;
        default:
            throw new DukeExceptions("Unknown error!");
        }
        return splitUserInput;
    }

    private LocalDateTime parseDateTime(String dateString) throws DukeExceptions {
        try {
            return LocalDateTime.parse(dateString.strip(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeExceptions(TIME_PARSE_ERROR);
        }
    }

    private void checkForExtraInput(String[] splitUserInput) throws DukeExceptions {
        if (splitUserInput.length > 1 && !(splitUserInput[1].equals(""))) {
            String message = String.format ("Hey! %s command cannot"
                    + "take any additional parameter!", splitUserInput[0]);
            throw new DukeExceptions(message);
        }
    }

    private void checkIndex(String[] splitUserInput) throws DukeExceptions {
        if (splitUserInput.length == 1) {
            throw new DukeExceptions("You have to tell me the index of the task to process!");
        }
    }

    private int castIndex(String[] splitUserInput) throws DukeExceptions {
        try {
            return Integer.parseInt(splitUserInput[1]);
        } catch (NumberFormatException e) {
            String message = String.format ("Oops! %s command can "
                            + "only take a single integer as the argument!",
                    splitUserInput[0]
            );
            throw new DukeExceptions(message);
        }
    }

    private String[] checkForDescriptionTask(String[] splitUserInput, CommandType commandType)
            throws DukeExceptions {
        String[] output = new String[3];
        output[0] = splitUserInput[0];
        output[1] = splitUserInput[1];
        String[] temp;
        if (splitUserInput.length == 1 || splitUserInput[1].equals("")) {
            String message;
            switch (commandType) {
            case ToDo:
                message = "Oops you need to tell me the description of the task";
                break;

            case Event:
                message = "Oops, you need to tell me the description "
                        + "and the time of the event";
                break;

            case Deadline:
                message = "Oops, you need to tell me the description "
                        + "and the time of the deadline";
                break;

            default:
                message = "Hmm.. there is an unknown error.. contact developer please";
                break;
            }
            throw new DukeExceptions(message);
        }
        if (commandType.equals(CommandType.Deadline) || commandType.equals(CommandType.Event)) {
            temp = checkForDescriptionAndTime(output, commandType);
            output[1] = temp[0];
            output[2] = temp[1];
        }
        return output;
    }

    private String[] checkForDescriptionAndTime(String[] body, CommandType commandType)
            throws DukeExceptions {
        String splitter;
        String task;

        switch (commandType) {
        case Event:
            splitter = "/at";
            task = "event";
            break;

        case Deadline:
            splitter = "/by";
            task = "deadline";
            break;

        default:
            throw new DukeExceptions ("Error: check desc error. Please contact developer");
        }

        String[] splitBody = body[1].strip().split(splitter, 2);
        if (splitBody[0].equals("")) {
            String message = String.format("Oops, you need to tell me the description "
                    + "and the time of the %s", task);
            throw new DukeExceptions(message);

        } else if (splitBody.length == 1 || splitBody[1].strip().equals("")) {
            String message = String.format("Oops! I need to know when the %s is. \n"
                    + "Use the %s argument followed by yyyy-MM-dd HH:mm please",
                    task, splitter);
            throw new DukeExceptions(message);
        }
        splitBody[0].strip();

        return splitBody;
    }

    private void checkForKeyword(String[] splitUserInput) throws DukeExceptions {
        if (splitUserInput.length == 1 || splitUserInput[1].equals("")) {
            throw new DukeExceptions("Oops, "
                    + "you need to tell me the keyword you are looking for");
        }
    }
}
