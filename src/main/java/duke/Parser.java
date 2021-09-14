package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ToDoCommand;
import duke.commands.UpdateCommand;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;


/**
 * Main function to break down the input of the user.
 */
public class Parser {

    private static final String TIME_PARSE_ERROR =
            "Hmm.. Seems like the time format is foreign to me. \n"
            + "Please use the following format:\n"
            + "yyyy-MM-dd HH:MM (e.g 2020-05-19 15:30)";

    private enum CommandType { Bye, List, Delete, Done, Event, ToDo, Deadline, Find, Update }

    private Storage storage;

    public Parser(Storage storage) {
        this.storage = storage;
    }
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
        List<String> splitUserInput = new ArrayList<>();
        Collections.addAll(splitUserInput, userInput.split(" ", 2));
        CommandType command = changeToEnum(splitUserInput.get(0));
        checkArguments(splitUserInput, command);

        switch (command) {
        case Bye: {
            return new ByeCommand();
        }

        case List: {
            return new ListCommand();
        }

        case Delete: {
            return new DeleteCommand(castIndex(splitUserInput));
        }

        case Done: {
            return new DoneCommand(castIndex(splitUserInput));
        }

        case Event: {
            return new EventCommand(Event.create(splitUserInput.get(1), parseDateTime(splitUserInput.get(2))));
        }

        case Deadline: {
            return new DeadlineCommand(Deadline.create(splitUserInput.get(1), parseDateTime(splitUserInput.get(2))));
        }

        case ToDo: {
            return new ToDoCommand(ToDo.create(splitUserInput.get(1)));
        }

        case Find: {
            return new FindCommand(splitUserInput.get(1));
        }

        case Update: {
            if (splitUserInput.size() > 3) {
                return new UpdateCommand(castIndex(splitUserInput),
                        splitUserInput.get(2),
                        parseDateTime(splitUserInput.get(3)));
            } else {
                return new UpdateCommand(castIndex(splitUserInput), splitUserInput.get(2));
            }
        }

        default:
            throw new DukeExceptions("Unknown error!");
        }
    }

    /**
     * Convert a String command to an Enum.
     * @param command String representation of the command.
     * @return A CommandType representation of the command.
     * @throws DukeExceptions if the command is unknown.
     */
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

        case "update": {
            return CommandType.Update;
        }

        case "": {
            throw new DukeExceptions("Can you type louder.. or type anything? I got nothing..");
        }

        default:
            throw new DukeExceptions("Sorry! I don't recognise your command!");
        }
    }

    private void checkArguments(List<String> splitUserInput, CommandType command) throws DukeExceptions {

        switch (command) {
        case Bye:
            // Fallthrough
        case List:
            checkForExtraInput(splitUserInput);
            break;
        case Delete:
            // Fallthrough
        case Done:
            checkForIndex(splitUserInput);
            break;
        case ToDo:
            //Fallthrough
        case Event:
            //Fallthrough
        case Deadline:
            checkAndProcessDescription(splitUserInput, command);
            break;
        case Find:
            checkForKeyword(splitUserInput);
            break;
        case Update:
            processUpdate(splitUserInput);
            break;
        default:
            throw new DukeExceptions("Unknown error!");
        }
    }

    private LocalDateTime parseDateTime(String dateString) throws DukeExceptions {
        try {
            return LocalDateTime.parse(dateString.strip(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new DukeExceptions(TIME_PARSE_ERROR);
        }
    }

    private void checkForExtraInput(List<String> splitUserInput) throws DukeExceptions {
        if (splitUserInput.size() > 1 && !(splitUserInput.get(1).equals(""))) {
            String message = String.format ("Hey! %s command cannot"
                    + "take any additional parameter!", splitUserInput.get(0));
            throw new DukeExceptions(message);
        }
    }

    private void checkForIndex(List<String> splitUserInput) throws DukeExceptions {
        if (splitUserInput.size() == 1) {
            throw new DukeExceptions("You have to tell me the index of the task to process!");
        }
    }

    private int castIndex(List<String> splitUserInput) throws DukeExceptions {
        try {
            return Integer.parseInt(splitUserInput.get(1));
        } catch (NumberFormatException e) {
            String message = String.format ("Oops! %s command can "
                            + "only take a single integer as the argument!",
                    splitUserInput.get(0)
            );
            throw new DukeExceptions(message);
        }
    }

    /**
     * Checks and separates the splitUserInput body to description and time if applicable.
     * @param splitUserInput List of string representing the user input.
     * @param commandType The type of command.
     * @throws DukeExceptions if there is no description.
     */
    private void checkAndProcessDescription(List<String> splitUserInput, CommandType commandType)
            throws DukeExceptions {
        if (splitUserInput.size() == 1 || splitUserInput.get(1).equals("")) {
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
            splitDescriptionAndTime(splitUserInput, commandType, 1);
        }
    }

    /**
     * Separates the splitUserInput body to description and time.
     * @param splitUserInput List of string representing the user input.
     * @param commandType The type of command.
     * @throws DukeExceptions if there is no description and/or time parameter.
     */
    private void splitDescriptionAndTime(List<String> splitUserInput, CommandType commandType, int index)
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

        String[] splitBody = splitUserInput.get(index).strip().split(splitter, 2);
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
        splitBody[0] = splitBody[0].strip();
        splitUserInput.remove(index);
        Collections.addAll(splitUserInput, splitBody);
    }

    private void checkForKeyword(List<String> splitUserInput) throws DukeExceptions {
        if (splitUserInput.size() == 1 || splitUserInput.get(1).equals("")) {
            throw new DukeExceptions("Oops, "
                    + "you need to tell me the keyword you are looking for");
        }
    }

    /**
     * Process the user input for Update command.
     * @param splitUserInput List of string representing the user input.
     * @throws DukeExceptions if there is no description and/or index in the input.
     */
    private void processUpdate(List<String> splitUserInput) throws DukeExceptions {
        String[] body = splitUserInput.get(1).strip().split(" ", 2);
        if (body.length == 1) {
            throw new DukeExceptions("Please tell me which task to update and the new description");
        }
        splitUserInput.remove(1);
        splitUserInput.add(body[0]);
        int index = castIndex(splitUserInput);
        splitUserInput.add(body[1]);
        Task taskToUpdate = storage.getTask(index - 1);
        CommandType taskType = taskToEnum(taskToUpdate);
        if (!(taskType.equals(CommandType.ToDo))) {
            splitDescriptionAndTime(splitUserInput, taskType, 2);
        }
    }

    private CommandType taskToEnum(Task task) throws DukeExceptions {
        CommandType taskType;
        switch(task.getTaskType()) {
        case "T":
            taskType = CommandType.ToDo;
            break;
        case "E":
            taskType = CommandType.Event;
            break;
        case "D":
            taskType = CommandType.Deadline;
            break;
        default:
            throw new DukeExceptions("Unknown Error in taskToEnum!");
        }
        return taskType;
    }
}
