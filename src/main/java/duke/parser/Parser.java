package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.Todo;
import duke.data.TaskList;
import duke.exceptions.DukeException;
import duke.ui.Ui;

/**
 * Encapsulates a Parser object that reads in and executes commands based on user's input.
 *
 * @author Owen Tan
 * @version duke.Duke Level-9
 */
public class Parser {
    private TaskList tasks;
    private Ui ui;

    /**
     * Commands from user's input
     */
    public enum Keyword {
        TODO, LIST, DEADLINE, EVENT, DONE, DELETE, FIND
    }

    /**
     * Public constructor for Parser.
     *
     * @param tasks A list of tasks.
     * @param ui Ui for duke.Duke.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Reads in and executes user's input based on string given.
     *
     * @param cmd String from user's input.
     * @throws DukeException
     */
    public String parse(String cmd) throws DukeException {
        String[] tokens = cmd.split(" ");
        Keyword command = validateCommand(tokens);

        int middle;
        int index;
        String description;
        String dateString;
        LocalDateTime at;
        LocalDateTime by;
        Task task;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        StringBuilder message = new StringBuilder();

        switch (command) {
        case LIST:
            message.append(this.tasks.printTaskList());
            break;
        case TODO:
            task = new Todo(cmd.substring(5));
            this.tasks.add(task);
            message.append(ui.showAddTaskMsg(task));
            break;
        case DEADLINE:
            middle = Arrays.asList(tokens).indexOf("/by");
            if (middle == -1) {
                throw new DukeException("Missing deadline.");
            }
            description = String.join(" ", Arrays.copyOfRange(tokens, 1, middle));
            dateString = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
            try {
                by = LocalDateTime.parse(dateString, format);
                task = new Deadline(description, by);
                this.tasks.add(task);
                message.append(ui.showAddTaskMsg(task));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter a date in the following format: dd/MM/yyyy HHmm");
            }
            break;
        case EVENT:
            middle = Arrays.asList(tokens).indexOf("/at");
            if (middle == -1) {
                throw new DukeException("Missing time of event.");
            }
            description = String.join(" ", Arrays.copyOfRange(tokens, 1, middle));
            dateString = String.join(" ", Arrays.copyOfRange(tokens, middle + 1, tokens.length));
            try {
                at = LocalDateTime.parse(dateString, format);
                task = new Event(description, at);
                this.tasks.add(task);
                message.append(ui.showAddTaskMsg(task));
            } catch (DateTimeParseException e) {
                throw new DukeException("Please enter a date in the following format: dd/MM/yyyy HHmm");
            }
            break;
        case DONE:
            index = Integer.parseInt(tokens[1]) - 1;
            task = this.tasks.get(index);
            this.tasks.complete(index);
            message.append(ui.showCompleteTaskMsg(task));
            break;
        case DELETE:
            index = Integer.parseInt(tokens[1]) - 1;
            task = this.tasks.get(index);
            this.tasks.delete(index);
            message.append(ui.showDeleteTaskMsg(task));
            message.append(ui.showListCountMsg());
            break;
        case FIND:
            String keyword = cmd.substring(5);
            message.append(tasks.printMatchingTasks(keyword));
            break;
        default:
            throw new DukeException("Unknown command passed.");
        }
        return message.toString();
    }

    private static Keyword validateCommand(String[] cmd) throws DukeException {
        Keyword keyword;
        try {
            keyword = Keyword.valueOf(cmd[0].toUpperCase());
            if ((keyword.equals(Keyword.TODO) || keyword.equals(Keyword.DEADLINE) || keyword.equals(Keyword.EVENT))
                    && cmd.length < 2) {
                throw new DukeException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", keyword));
            }
            if ((keyword.equals(Keyword.DONE) || keyword.equals(Keyword.DELETE) || keyword.equals(Keyword.FIND))
                    && (cmd.length < 2)) {
                throw new DukeException("☹ OOPS!!! Missing arguments.");
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("Brother! What are you saying?");
        }
        return keyword;
    }
}
