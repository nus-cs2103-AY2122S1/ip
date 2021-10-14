package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidEntryException;
import duke.exception.MissingPreException;
import duke.exception.NotATaskNumberException;
import duke.exception.TaskNoDateTimeException;
import duke.exception.TaskNoNameException;
import duke.exception.TaskNotFoundException;

/**
 * Deals with making sense of user command
 */
public class Parser {

    private TaskList tasks;

    /**
     * Constructs Parser object
     *  @param tasks   list of tasks
     *
     */

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Deals with interpreting user commands
     *
     * @return command to execute
     */
    public Command parse(String s) throws DukeException {
        String commandWord = getCommandWord(s);
        switch (commandWord) {
        case "todo":
            String todoName = getTaskName(commandWord, s);
            return new TodoCommand(todoName);
        case "deadline":
            checkDescIsValid(s, "/by", commandWord);
            String deadlineName =  getTaskName(commandWord, s);
            LocalDateTime deadlineDateTime = getDateTime("/by", s);
            return new DeadlineCommand(deadlineName, deadlineDateTime);
        case "event":
            checkDescIsValid(s, "/at", commandWord);
            String eventName = getTaskName(commandWord, s);
            LocalDateTime eventDateTime = getDateTime("/at", s);;
            return new EventCommand(eventName, eventDateTime);
        case "done":
            int doneTaskNum = getTaskNum(s);
            return new DoneCommand(doneTaskNum);
        case "delete":
            int deleteTaskNum = getTaskNum(s);
            return new DeleteCommand(deleteTaskNum);
        case "find":
            String keyword = getDesc(s);
            return new FindCommand(keyword);
        case "list":
            return new ListCommand();
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new InvalidEntryException();
        }
    }

    private String getCommandWord(String input) {
        String trimmed = input.trim();
        if (trimmed.contains(" ")) {
            return trimmed.substring(0, input.indexOf(" "));
        }
        return trimmed;
    }

    private String getTaskName(String commandWord, String s) throws DukeException {
        switch (commandWord) {
        case "todo":
            return getDesc(s);
        case "deadline":
            String[] deadlineNameAndDateTimeArr = getNameAndDateTimeArr("/by", s);
            assert deadlineNameAndDateTimeArr.length == 2;
            return deadlineNameAndDateTimeArr[0];
        case "event":
            String[] eventNameAndDateTimeArr = getNameAndDateTimeArr("/at", s);
            assert eventNameAndDateTimeArr.length == 2;
            return eventNameAndDateTimeArr[0];
        default:
            throw new DukeException();
        }
    }

    private String[] getNameAndDateTimeArr(String pre, String s) throws EmptyDescriptionException {
        String desc = getDesc(s);
        return desc.split(pre);
    }


    private String getDesc(String s) throws EmptyDescriptionException {
        if (s.contains(" ")) {
            String[] parts = s.split(" ", 2);
            if (!parts[1].isEmpty()) {
                return parts[1];
            }
        }
        throw new EmptyDescriptionException(s);
    }

    private LocalDateTime getDateTime(String pre,String s) throws InvalidDateTimeException, EmptyDescriptionException {
        try {
            String dt = getNameAndDateTimeArr(pre, s)[1];
            String dtSpaceRemoved = dt.trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dtSpaceRemoved, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException();
        }
    }

    private int getTaskNum(String s) throws TaskNotFoundException, NotATaskNumberException, EmptyDescriptionException {
        try {
            String desc = getDesc(s);
            int num = Integer.parseInt(desc);
            if (num <= 0 || num > this.tasks.getSize()) {
                throw new TaskNotFoundException();
            }
            return num - 1;
        } catch (NumberFormatException exception) {
            throw new NotATaskNumberException();
        }
    }

    private void checkDescIsValid(String input, String pre, String taskType) throws DukeException {
        String desc = getDesc(input);
        // check if description has required preposition
        if (!desc.contains(pre)) {
            throw new MissingPreException(pre);
        }
        // check if description has missing name and date/time
        if (desc.equals("") || desc.equals(pre)) {
            throw new InvalidDescriptionException(taskType);
        }
        // check if description has missing task name
        if (desc.startsWith(pre)) {
            throw new TaskNoNameException(taskType);
        }
        // check if description has missing date/time
        if (desc.endsWith(pre)) {
            throw new TaskNoDateTimeException(taskType);
        }
    }
}
