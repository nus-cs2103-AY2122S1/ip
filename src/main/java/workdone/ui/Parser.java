package workdone.ui;

import java.time.format.DateTimeParseException;
import java.util.Stack;

import workdone.command.AddTaskCommand;
import workdone.command.ClearCommand;
import workdone.command.Command;
import workdone.command.DeleteTaskCommand;
import workdone.command.ExitCommand;
import workdone.command.FindTaskCommand;
import workdone.command.GetListCommand;
import workdone.command.HelpCommand;
import workdone.command.TaskDoneCommand;
import workdone.command.TaskUndoneCommand;
import workdone.command.UndoCommand;
import workdone.exception.InvalidCommandException;
import workdone.exception.InvalidTaskNoException;
import workdone.exception.InvalidTimeException;
import workdone.exception.MissingCommandDetailException;
import workdone.exception.MultipleTimeSlotsException;
import workdone.exception.UnableToUndoException;
import workdone.exception.WorkDoneException;
import workdone.task.Deadline;
import workdone.task.Event;
import workdone.task.Task;
import workdone.task.ToDo;

/**
 * Represents a parser that deals with making sense of the user command.
 */
public class Parser {

    private static Stack<Command> commands = new Stack<>();

    /**
     * Returns parsed command which involves time.
     *
     * @param words An array of words in the command.
     * @param isEvent Whether the command is an `event` command.
     * @return Parsed command.
     * @throws WorkDoneException If command is invalid.
     */
    private static Command parseCommandWithTime(String[] words, boolean isEvent) throws WorkDoneException {
        // Determine key information based on type of the task
        String timeFormat = isEvent ? "yyyy-MM-dd HH:mm to yyyy-MM-dd HH:mm" : "yyyy-MM-dd HH:mm";
        String regex = isEvent ? "/at" : "/by";
        String taskType = isEvent ? "event" : "deadline";

        if (words.length < 2) {
            // If there is only one word in the command, the description is missing.
            throw new MissingCommandDetailException("description", taskType,
                    String.format("%s %s", regex, timeFormat));
        }
        String[] information = words[1].split(regex);
        if (information.length < 2) {
            // time is missing
            throw new MissingCommandDetailException("time", taskType, String.format("%s %s", regex, timeFormat));
        }
        if (information.length > 2) {
            // more than one time slots are given
            throw new MultipleTimeSlotsException(taskType);
        }
        try {
            Task task = isEvent
                    ? new Event(information[0], information[1])
                    : new Deadline(information[0], information[1]);
            AddTaskCommand addTaskCommand = new AddTaskCommand(task);
            Parser.commands.push(addTaskCommand);
            return addTaskCommand;
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new InvalidTimeException(timeFormat);
        }
    }

    /**
     * Returns parsed command which involves a task number.
     *
     * @param words An array of words in the command.
     * @return Parsed command.
     * @throws InvalidTaskNoException If task number is invalid.
     */
    private static Command parseCommandWithTaskNo(
            String[] words) throws InvalidTaskNoException {
        String leadingWord = words[0];
        int index;
        try {
            index = Integer.parseInt(words[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new InvalidTaskNoException();
        }
        Command command = leadingWord.equals("done")
                ? new TaskDoneCommand(index)
                : leadingWord.equals("undone")
                ? new TaskUndoneCommand(index)
                : new DeleteTaskCommand(index);
        Parser.commands.push(command);
        return command;
    }

    private static AddTaskCommand parseTodo(String[] words) throws MissingCommandDetailException {
        if (words.length < 2) {
            throw new MissingCommandDetailException("description", "todo", "");
        }
        AddTaskCommand addTaskCommand = new AddTaskCommand(new ToDo(words[1]));
        Parser.commands.push(addTaskCommand);
        return addTaskCommand;
    }

    private static FindTaskCommand parseFind(String[] words) throws MissingCommandDetailException {
        if (words.length < 2) {
            throw new MissingCommandDetailException("keyword", "find", "");
        }
        // Cannot undo a find command
        return new FindTaskCommand(words[1].trim());
    }

    private static UndoCommand parseUndo() throws UnableToUndoException {
        if (Parser.commands.empty()) {
            throw new UnableToUndoException();
        }
        // Cannot undo an undo command
        return new UndoCommand(Parser.commands.pop());
    }

    private static Command parseCommandWithTwoOrMoreWords(String[] words) throws WorkDoneException {
        String leadingWord = words[0];
        if (leadingWord.equals("done")) {
            return Parser.parseCommandWithTaskNo(words);
        } else if (leadingWord.equals("undone")) {
            return Parser.parseCommandWithTaskNo(words);
        } else if (leadingWord.equals("delete")) {
            return Parser.parseCommandWithTaskNo(words);
        } else if (leadingWord.equals("todo")) {
            return Parser.parseTodo(words);
        } else if (leadingWord.equals("deadline")) {
            return Parser.parseCommandWithTime(words, false);
        } else if (leadingWord.equals("event")) {
            return Parser.parseCommandWithTime(words, true);
        } else if (leadingWord.equals("find")) {
            return Parser.parseFind(words);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses and returns a command from a string to a Command object.
     *
     * @param command Command received from keyboard.
     * @return Parsed command.
     * @throws WorkDoneException If command is invalid.
     */
    public static Command parse(String command) throws WorkDoneException {
        // Determine type of the command and return corresponding command instance
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new GetListCommand();
        } else if (command.equals("undo")) {
            return Parser.parseUndo();
        } else if (command.equals("help")) {
            return new HelpCommand();
        } else if (command.equals("clear")) {
            return new ClearCommand();
        } else {
            // Split the command into two phrases
            String[] words = command.split(" ", 2);
            return Parser.parseCommandWithTwoOrMoreWords(words);
        }
    }

    /**
     * Assumes the last command in the history is invalid and pops it.
     */
    public static void popInvalidCommand() {
        Parser.commands.pop();
    }
}
