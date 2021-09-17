package aoi.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.task.Deadline;
import aoi.task.Event;
import aoi.task.Task;
import aoi.task.Todo;
import aoi.ui.Ui;

/**
 * Encapsulates a Parser object that reads in and executes commands based on user's input.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
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
     * @param ui Ui for aoi.Aoi.
     */
    public Parser(TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Reads in and executes user's input based on string given.
     *
     * @param cmd String from user's input.
     * @throws AoiException
     */
    public String parse(String cmd) throws AoiException {
        String[] tokens = cmd.split(" ");
        Keyword command = validateCommand(tokens);

        int argIndex;
        int notesIndex;
        int index;
        String description;
        String dateString;
        String notes = "";
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
            notesIndex = Arrays.asList(tokens).indexOf("/notes");
            if (notesIndex == -1) {
                task = new Todo(cmd.substring(5));
            } else {
                description = String.join(" ", Arrays.copyOfRange(tokens, 1, notesIndex));
                notes = String.join(" ", Arrays.copyOfRange(tokens, notesIndex + 1, tokens.length));
                task = new Todo(description, notes);
            }
            this.tasks.add(task);
            message.append(ui.showAddTaskMsg(task));
            break;
        case DEADLINE:
            argIndex = Arrays.asList(tokens).indexOf("/by");
            notesIndex = Arrays.asList(tokens).indexOf("/notes");
            if (argIndex == -1) {
                throw new AoiException("Missing deadline.");
            }
            description = String.join(" ", Arrays.copyOfRange(tokens, 1, argIndex));
            if (notesIndex == -1) {
                dateString = String.join(" ", Arrays.copyOfRange(tokens, argIndex + 1, tokens.length));
            } else {
                dateString = String.join(" ", Arrays.copyOfRange(tokens, argIndex + 1, notesIndex));
                notes = String.join(" ", Arrays.copyOfRange(tokens, notesIndex + 1, tokens.length));
            }
            try {
                by = LocalDateTime.parse(dateString, format);
                task = new Deadline(description, by, notes);
                this.tasks.add(task);
                message.append(ui.showAddTaskMsg(task));
            } catch (DateTimeParseException e) {
                throw new AoiException("Please enter a date in the following format: dd/MM/yyyy HHmm");
            }
            break;
        case EVENT:
            argIndex = Arrays.asList(tokens).indexOf("/at");
            notesIndex = Arrays.asList(tokens).indexOf("/notes");
            if (argIndex == -1) {
                throw new AoiException("Missing time of event.");
            }
            description = String.join(" ", Arrays.copyOfRange(tokens, 1, argIndex));
            if (notesIndex == -1) {
                dateString = String.join(" ", Arrays.copyOfRange(tokens, argIndex + 1, tokens.length));
            } else {
                dateString = String.join(" ", Arrays.copyOfRange(tokens, argIndex + 1, notesIndex));
                notes = String.join(" ", Arrays.copyOfRange(tokens, notesIndex + 1, tokens.length));
            }
            try {
                at = LocalDateTime.parse(dateString, format);
                task = new Event(description, at, notes);
                this.tasks.add(task);
                message.append(ui.showAddTaskMsg(task));
            } catch (DateTimeParseException e) {
                throw new AoiException("Please enter a date in the following format: dd/MM/yyyy HHmm");
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
            throw new AoiException("Unknown command passed.");
        }
        return message.toString();
    }

    private static Keyword validateCommand(String[] cmd) throws AoiException {
        Keyword keyword;
        try {
            keyword = Keyword.valueOf(cmd[0].toUpperCase());
            if ((keyword.equals(Keyword.TODO) || keyword.equals(Keyword.DEADLINE) || keyword.equals(Keyword.EVENT))
                    && cmd.length < 2) {
                throw new AoiException(String.format("☹ OOPS!!! The description of a %s cannot be empty.", keyword));
            }
            if ((keyword.equals(Keyword.DONE) || keyword.equals(Keyword.DELETE) || keyword.equals(Keyword.FIND))
                    && (cmd.length < 2)) {
                throw new AoiException("☹ OOPS!!! Missing arguments.");
            }
        } catch (IllegalArgumentException e) {
            throw new AoiException("Brother! What are you saying?");
        }
        return keyword;
    }
}
