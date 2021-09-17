package aoi.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import aoi.commands.Command;
import aoi.commands.Keyword;
import aoi.exceptions.AoiException;
import aoi.task.Deadline;
import aoi.task.Event;
import aoi.task.Task;
import aoi.task.Todo;

/**
 * Encapsulates a Parser object that reads in and executes commands based on user's input.
 *
 * @author Owen Tan
 * @version aoi.Aoi Level-9
 */
public class Parser {
    public static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static final int MAX_LENGTH_T = 4;
    private static final int MAX_LENGTH_D = 5;
    private static final int MAX_LENGTH_E = 5;
    private static final int taskPos = 0;
    private static final int isDonePos = 1;
    private static final int descriptionPos = 2;
    private static final int addInfoPos = 3;
    private static final int notesPos = 4;

    /**
     * Reads in and executes user's input based on string given.
     *
     * @param cmd String from user's input.
     * @throws AoiException
     */
    public static Command parse(String cmd) throws AoiException {
        String[] tokens = cmd.split(" ");
        Keyword keyword = validateCommand(tokens);
        return Command.of(keyword, tokens);
    }

    private static Keyword validateCommand(String[] cmd) throws AoiException {
        Keyword keyword;
        try {
            keyword = Keyword.getKeyword(cmd[0].toUpperCase());
            if (keyword.equals(Keyword.ADD) && cmd.length < 2) {
                throw new AoiException(String.format("☹ OOPS!!! The description cannot be empty.", keyword));
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

    /**
     * Parses string and creates a Task associated to the string.
     *
     * @param task String from text file.
     * @return Task associated to the string.
     */
    public static Task parseTask(String task) {
        // Format to Parse: T | 0 | description | addInfo | notes (optional)
        String[] tokens = task.split(" \\| ");
        boolean isDone = tokens[isDonePos].equals("1");
        LocalDateTime timestamp = null;
        assert tokens[0].equals("T") || tokens[0].equals("D") || tokens[0].equals("E");
        String notes;
        Task taskCreated = null;
        try {
            switch (tokens[taskPos]) {
            case "T":
                notes = tokens.length == MAX_LENGTH_T ? tokens[MAX_LENGTH_T - 1] : "";
                taskCreated = new Todo(tokens[descriptionPos], isDone, notes);
                break;
            case "D":
                timestamp = LocalDateTime.parse(tokens[addInfoPos], FORMAT);
                notes = tokens.length == MAX_LENGTH_D ? tokens[notesPos] : "";
                taskCreated = new Deadline(tokens[descriptionPos], isDone, timestamp, notes);
                break;
            case "E":
                timestamp = LocalDateTime.parse(tokens[3], FORMAT);
                notes = tokens.length == MAX_LENGTH_E ? tokens[notesPos] : "";
                taskCreated = new Event(tokens[descriptionPos], isDone, timestamp, notes);
                break;
            default:
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing task from saved file");
        }
        return taskCreated;
    }
}
