package duke;

import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkAsDoneCommand;
import duke.exception.ParserException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The class that Parses the command line from user.
 *
 * @author Luo Zhijie
 */
public class Parser {

    /**
     * Parses the input string from user to meaning command.
     *
     * @param input input string from user.
     * @return Command got from input string.
     * @throws ParserException Exception that generated during parsing.
     */
    public Command parse(String input) throws ParserException {
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("done")) {
            return parseMarkAsDoneCommand(input);
        } else if (input.startsWith("delete")) {
            return parseDeleteCommand(input);
        } else if (input.startsWith("find")) {
            return parseFindCommand(input);
        } else if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.startsWith("todo")) {
            return parseToDo(input);
        } else if (input.startsWith("deadline")) {
            return parseDeadline(input);
        } else if (input.startsWith("event")) {
            return parseEvent(input);
        } else {
            throw new ParserException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parse the user input to a mark-as-done command.
     *
     * @param input Input String from the user.
     * @return Mark task a done command.
     * @throws ParserException Exception when user input doesn't
     *                         contain task index or the task index is not a integer.
     */
    private Command parseMarkAsDoneCommand(String input) throws ParserException {
        try {
            int contentStartIndex = 5;
            int taskIndex = Integer.parseInt(input.substring(contentStartIndex));
            return new MarkAsDoneCommand(taskIndex - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new ParserException("Please enter a number for task index");
        }
    }

    /**
     * Parse the user input to a delete command.
     *
     * @param input Input String from the user.
     * @return The delete command.
     * @throws ParserException Exception when user input doesn't
     *                         contain task index or the task index is not a integer.
     */
    private Command parseDeleteCommand(String input) throws ParserException {
        try {
            int contentStartIndex = 7;
            int deleteIndex = Integer.parseInt(input.substring(contentStartIndex));
            return new DeleteCommand(deleteIndex - 1);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new ParserException("Please enter a number for task index");
        }
    }

    /**
     * Parse the user input to a find command.
     *
     * @param input Input String from the user.
     * @return Find command.
     * @throws ParserException Exception when there's no keyword in the user input.
     */
    private Command parseFindCommand(String input) throws ParserException {
        if (input.equals("find")) {
            throw new ParserException("Please enter some keywords.");
        }
        int keywordStartIndex = 5;
        String keywords = input.substring(keywordStartIndex);
        return new FindCommand(keywords);
    }

    /**
     * Gets the task description from deadline task or event task.
     *
     * @param input    User input.
     * @param taskType String of "deadline" or "event".
     * @return The task description.
     * @throws ParserException Exception generated if user input does not match the format.
     */
    private String getDeadlineEventDescription(String input, String taskType) throws ParserException {
        String regex = taskType + " ";
        String descriptionTaskTimeDivider = taskType.equals("deadline") ? "/by" : "/at";
        if (input.split(regex).length < 2) {
            throw new ParserException("The description of a " + taskType + " cannot be empty.");
        }
        String content = input.split(regex)[1];
        if (!content.contains(descriptionTaskTimeDivider)) {
            throw new ParserException("Please enter "
                    + descriptionTaskTimeDivider
                    + " followed by a deadline.");
        }
        String description = content.substring(0, content.indexOf(descriptionTaskTimeDivider));
        if (description.equals("")) {
            throw new ParserException("The description of a " + taskType + " cannot be empty.");
        }
        return description;
    }

    /**
     * Gets the deadline or event task time.
     *
     * @param input    User input.
     * @param taskType String of "deadline" or "event".
     * @return The task happening time.
     * @throws ParserException Exception generated if user input does not match the format.
     */
    private String getTaskTime(String input, String taskType) throws ParserException {
        String regex = taskType + " ";
        String content = input.split(regex)[1];
        String descriptionTaskTimeDivider = taskType.equals("deadline") ? "/by" : "/at";
        int timeStartIndex = content.indexOf(descriptionTaskTimeDivider) + 4;
        int timeEndIndex = taskType.equals("deadline")
                ? content.indexOf(descriptionTaskTimeDivider) + 19
                : content.indexOf(descriptionTaskTimeDivider) + 37;
        if (timeEndIndex >= content.length()) {
            String errorMessage = "Invalid date, "
                    + "please enter a valid date and time in the format: ";
            errorMessage += taskType.equals("deadline")
                    ? "yyyy/MM/dd HH:mm"
                    : "yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm";
            throw new ParserException(errorMessage);
        }
        String taskTime = content.substring(timeStartIndex, timeEndIndex + 1);
        return taskTime;
    }

    /**
     * Gets reminder time from input string.
     *
     * @param input User input.
     * @return Reminder time.
     * @throws ParserException Exception generated if user input does not match the format.
     */
    private String getReminderTime(String input) throws ParserException {
        String reminderTime = "";
        if (input.contains("/reminder")) {
            int reminderTimeStartIndex = input.indexOf("/reminder ") + 10;
            int reminderTimeEndIndex = input.indexOf("/reminder ") + 25;
            if (reminderTimeEndIndex >= input.length()) {
                throw new ParserException("Invalid date, "
                        + "please enter a valid reminder time in the format: "
                        + "yyyy/MM/dd HH:mm");
            }
            reminderTime = input.substring(reminderTimeStartIndex, reminderTimeEndIndex + 1);
        }
        return reminderTime;
    }

    /**
     * Parses the user input to an add ToDo command.
     *
     * @param input Input string from the user.
     * @return Add ToDo command.
     * @throws ParserException Exception generated if user input does not match the format.
     */
    private Command parseToDo(String input) throws ParserException {
        if (input.split("todo ").length < 2) {
            throw new ParserException("The description of a todo cannot be empty.");
        }
        String content = input.split("todo ")[1];
        if (content.contains("/reminder ")) {
            if (content.split("/reminder ").length < 2 || content.split("/reminder ")[0].length() == 0) {
                throw new ParserException("Please enter the reminder time");
            }
            String description = content.split("/reminder ")[0];
            String reminderTime = content.split("/reminder ")[1];
            try {
                Task task = new ToDo(description, reminderTime);
                return new AddCommand(task);
            } catch (DateTimeParseException e) {
                throw new ParserException("Invalid date, "
                        + "please enter a valid reminder time in the format: "
                        + "yyyy/MM/dd HH:mm");
            }
        } else {
            Task task = new ToDo(content);
            return new AddCommand(task);
        }
    }

    /**
     * Parses the user input to an add Deadline command.
     *
     * @param input Input string from user.
     * @return Add Deadline command.
     * @throws ParserException Exception generated if user input does not match the format.
     */
    private Command parseDeadline(String input) throws ParserException {
        String description = getDeadlineEventDescription(input, "deadline");
        String deadline = getTaskTime(input, "deadline");
        String reminderTime = getReminderTime(input);

        try {
            Task task;
            if (reminderTime.equals("")) {
                task = new Deadline(description, deadline);
                return new AddCommand(task);
            }
            task = new Deadline(description, deadline, reminderTime);
            return new AddCommand(task);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid date and time in the format: "
                    + "yyyy/MM/dd HH:mm");
        }
    }

    /**
     * Parses the user input to an add Event command.
     *
     * @param input Input string from user.
     * @return Add Event command.
     * @throws ParserException Exception generated if user input does not match the format.
     */
    private Command parseEvent(String input) throws ParserException {
        String description = getDeadlineEventDescription(input, "event");
        String eventPeriod = getTaskTime(input, "event");
        String reminderTime = getReminderTime(input);

        try {
            Task task;
            String[] startingEndingTime = eventPeriod.split("--");
            String from = startingEndingTime[0];
            String to = startingEndingTime[1];
            if (reminderTime.equals("")) {
                task = new Event(description, from, to);
                return new AddCommand(task);
            }
            task = new Event(description, from, to, reminderTime);
            return new AddCommand(task);
        } catch (DateTimeParseException e) {
            throw new ParserException("Invalid date, "
                    + "please enter a valid time period in the format: "
                    + "yyyy/MM/dd HH:mm--yyyy/MM/dd HH:mm");
        }
    }
}
