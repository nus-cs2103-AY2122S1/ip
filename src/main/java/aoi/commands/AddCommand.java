package aoi.commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import aoi.data.TaskList;
import aoi.exceptions.AoiException;
import aoi.parser.Parser;
import aoi.storage.Storage;
import aoi.task.Deadline;
import aoi.task.Event;
import aoi.task.Task;
import aoi.task.Todo;
import aoi.ui.Ui;

/**
 * Encapsulates a Command for Adding Tasks to Aoi.
 *
 * @author Owen Tan
 * @version aoi.Aoi v1.2
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for AddCommand.
     *
     * @param tokens User input.
     * @throws AoiException
     */
    public AddCommand(String[] tokens) throws AoiException {
        String description;
        String notes = getNotes(tokens);
        LocalDateTime dateTime;
        switch(tokens[0]) {
        case "todo":
            description = getDescription(tokens);
            task = new Todo(description, notes);
            break;
        case "deadline":
            description = getDescription(tokens, "/by");
            dateTime = getDateTime(tokens, "/by");
            task = new Deadline(description, dateTime, notes);
            break;
        case "event":
            description = getDescription(tokens, "/at");
            dateTime = getDateTime(tokens, "/at");
            task = new Event(description, dateTime, notes);
            break;
        default:
            throw new AoiException("Unknown AddCommand");
        }
    }

    /**
     * Adds the associated Task to TaskList.
     *
     * @param tasks TaskList associated to Aoi.
     * @param storage Storage associated to Aoi.
     * @return A string that shows the Task has been added.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        tasks.add(task);
        return Ui.showAddTaskMsg(task);
    }

    private String getDescription(String[] tokens, String... args) {
        List list = Arrays.asList(args);
        List tokensList = Arrays.asList(tokens);
        String description;
        int notesIndex = Arrays.asList(tokens).indexOf("/notes");
        if (list.contains("/by") || list.contains("/at")) {
            int argIndex = Arrays.asList(tokens).indexOf(args[0]);
            description = String.join(" ", Arrays.copyOfRange(tokens, 1, argIndex));
        } else {
            if (notesIndex == -1) {
                description = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
            } else {
                description = String.join(" ", Arrays.copyOfRange(tokens, 1, notesIndex));
            }
        }
        return description;
    }

    private LocalDateTime getDateTime(String[] tokens, String... args) throws AoiException {
        int notesIndex = Arrays.asList(tokens).indexOf("/notes");
        String dateString;
        int argIndex = Arrays.asList(tokens).indexOf(args[0]);
        if (notesIndex == -1) {
            dateString = String.join(" ", Arrays.copyOfRange(tokens, argIndex + 1, tokens.length));
        } else {
            dateString = String.join(" ", Arrays.copyOfRange(tokens, argIndex + 1, notesIndex));
        }

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(dateString, Parser.FORMAT);
        } catch (DateTimeParseException e) {
            throw new AoiException("Please enter a date in the following format: dd/MM/yyyy HHmm");
        }
        return dateTime;
    }

    private String getNotes(String[] tokens) {
        int notesIndex = Arrays.asList(tokens).indexOf("/notes");
        if (notesIndex == -1) {
            return "";
        }
        String notes = String.join(" ", Arrays.copyOfRange(tokens, notesIndex + 1, tokens.length));
        return notes;
    }
}
