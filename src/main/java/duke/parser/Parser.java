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
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidDateTimeException;
import duke.exception.InvalidDescriptionException;
import duke.exception.MissingPreException;
import duke.exception.NoNumberException;
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
     * @return
     */
    public Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        }

        if (input.equals("help")) {
            return new HelpCommand();
        }

        if (input.equals("list")) {
            return new ListCommand();
        }

        if (input.contains("done")) {
            int taskNum = getNum(input);
            return new DoneCommand(taskNum);
        }

        if (input.contains("delete")) {
            int taskNum = getNum(input);
            return new DeleteCommand(taskNum);
        }


        if (input.contains("todo")) {
            String desc = getDesc(input);
            return new TodoCommand(desc);
        }

        if (input.contains("deadline")) {
            String desc = getDesc(input);
            if (checkDescIsValid(desc, "/by")) {
                String[] parts = desc.split("/by");
                String name = parts[0];
                LocalDateTime by = getDateTime(parts[1]);
                return new DeadlineCommand(name, by);
            }
        }

        if (input.contains("event")) {
            String desc = getDesc(input);
            if (checkDescIsValid(desc, "/at")) {
                String[] parts = desc.split("/at");
                String name = parts[0];
                LocalDateTime at = getDateTime(parts[1]);
                return new EventCommand(name, at);
            }
        }

        if (input.contains("find")) {
            String keyword = getDesc(input);
            if (!keyword.equals("")) {
                return new FindCommand(keyword);
            }
        }

        return new InvalidCommand();
    }

    private int getNum(String s) throws DukeException {
        try {
            if (s.contains(" ")) {
                String[] parts = s.split(" ", 2);

                assert parts[0].equals("done") || parts[0].equals("delete")
                        : "Invalid Keyword";

                int num = Integer.parseInt(parts[1]);

                if (num <= 0 || num > this.tasks.getSize()) {
                    throw new TaskNotFoundException("error");
                }
                return num - 1;
            } else {
                throw new NoNumberException("error");
            }
        } catch (NumberFormatException exception) {
            throw new NoNumberException("error");
        }
    }

    private String getDesc(String s) throws DukeException {
        if (s.contains(" ")) {
            String[] parts = s.split(" ", 2);

            boolean isKeyword = (parts[0].equals("todo") || parts[0].equals("deadline")
                    || parts[0].equals("event") || parts[0].equals("find"));
            assert isKeyword : "Invalid Keyword!";

            if (!parts[1].equals("")) {
                return parts[1];
            }
        }
        throw new EmptyDescriptionException("error", s);
    }

    private LocalDateTime getDateTime(String s) throws InvalidDateTimeException {
        try {
            String dt = s.replaceFirst(" ", "");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("error");
        }
    }

    private boolean checkDescIsValid(String description, String pre) throws DukeException {
        String task = pre.equals("/by") ? "deadline" : "event";
        boolean hasPreposition = description.contains(pre);
        boolean hasNameOrDateTime = !description.equals("") && !description.equals(pre);
        boolean isMissingName = description.startsWith(pre);
        boolean isMissingDateTime = description.endsWith(pre);

        if (hasPreposition) {
            if (hasNameOrDateTime) {
                if (isMissingName) {
                    throw new TaskNoNameException("error", task);
                } else if (isMissingDateTime) {
                    throw new TaskNoDateTimeException("error", task);
                }
                return true;
            }
            throw new InvalidDescriptionException("error", task);
        }
        throw new MissingPreException("error", pre);
    }
}
