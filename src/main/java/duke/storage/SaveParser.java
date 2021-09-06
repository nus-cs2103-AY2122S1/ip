package duke.storage;

import java.util.Scanner;

import duke.exceptions.DukeReadSaveException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;



/**
 * Parser class to create Task objects from string commands.
 */
public class SaveParser {
    private static final DukeReadSaveException MISSING_ARG_EXCEPTION =
            new DukeReadSaveException("missing argument in save");

    private Scanner scanner;

    /**
     * Constructs a Save parser.
     *
     * @param scanner scanner object that is reading the save file.
     */
    SaveParser(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Tests if file has next line.
     *
     * @return true if next line present.
     */
    public boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }

    /**
     * Extracts next task from save file and return it as object.
     *
     * @return new task from save file.
     * @throws DukeReadSaveException if there is an invalid task.
     */
    public Task getNextTask() throws DukeReadSaveException {
        String[] taskType = this.scanner.nextLine().split(":", 2);
        if (!taskType[0].equals("Task")) {
            throw new DukeReadSaveException("Invalid Task");
        }
        switch (taskType[1]) {
        case ("todo"):
            return parseTodo();
        case ("deadline"):
            return parseDeadline();
        case ("event"):
            return parseEvent();
        default:
            throw new DukeReadSaveException("Invalid Task name");
        }
    }

    private Todo parseTodo() throws DukeReadSaveException {
        String name = "";
        boolean isDone = false;
        boolean[] argsFound = new boolean[2];

        String nl;
        while (this.scanner.hasNextLine()) {
            if (this.scanner.hasNext("(Task:).*")) {
                //break if next line is the start of a new task
                break;
            }
            nl = this.scanner.nextLine();
            if (nl.equals("")) {
                break;
            }
            String[] key_value = nl.split(":", 2);
            assert key_value.length == 2;
            switch (key_value[0]) {
            case ("\tName"):
                name = key_value[1];
                argsFound[0] = true;
                break;
            case ("\tDone"):
                isDone = Boolean.parseBoolean(key_value[1]);
                argsFound[1] = true;
                break;
            default:
                // invalid line
            }
        }
        if (argsFound[0] && argsFound[1]) {
            return new Todo(name, isDone);
        }
        throw MISSING_ARG_EXCEPTION;
    }

    private Deadline parseDeadline() throws DukeReadSaveException {
        String name = "";
        boolean isDone = false;
        String by = "";
        boolean[] argsFound = new boolean[3];

        String nl;
        while (this.scanner.hasNextLine()) {
            if (this.scanner.hasNext("(Task:).*")) {
                //break if next line is the start of a new task
                break;
            }
            nl = this.scanner.nextLine();
            if (nl.equals("")) {
                break;
            }
            String[] key_value = nl.split(":", 2);
            assert key_value.length == 2;
            switch (key_value[0]) {
            case ("\tName"):
                name = key_value[1];
                argsFound[0] = true;
                break;
            case ("\tDone"):
                isDone = Boolean.parseBoolean(key_value[1]);
                argsFound[1] = true;
                break;
            case ("\tBy"):
                by = key_value[1];
                argsFound[2] = true;
                break;
            default:
                // invalid line
            }
        }
        if (argsFound[0] && argsFound[1] && argsFound[2]) {
            return new Deadline(name, isDone, by);
        }
        throw MISSING_ARG_EXCEPTION;
    }

    private Event parseEvent() throws DukeReadSaveException {
        String name = "";
        boolean isDone = false;
        String at = "";
        boolean[] argsFound = new boolean[3];

        String nl;
        while (this.scanner.hasNextLine()) {
            if (this.scanner.hasNext("(Task:).*")) {
                //break if next line is the start of a new task
                break;
            }
            nl = this.scanner.nextLine();
            if (nl.equals("")) {
                //break if we hit an empty line
                break;
            }
            String[] key_value = nl.split(":", 2);
            assert key_value.length == 2;
            switch (key_value[0]) {
            case ("\tName"):
                name = key_value[1];
                argsFound[0] = true;
                break;
            case ("\tDone"):
                isDone = Boolean.parseBoolean(key_value[1]);
                argsFound[1] = true;
                break;
            case ("\tAt"):
                at = key_value[1];
                argsFound[2] = true;
                break;
            default:
                // invalid line
            }
        }
        if (argsFound[0] && argsFound[1] && argsFound[2]) {
            return new Event(name, isDone, at);
        }
        throw MISSING_ARG_EXCEPTION;
    }
}
