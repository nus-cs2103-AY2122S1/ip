package duke.storage;

import java.util.HashMap;
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
    private static final String NAME_KEY = "\tName";
    private static final String DONE_KEY = "\tDone";
    private static final String AT_KEY = "\tAt";
    private static final String BY_KEY = "\tBy";

    private Scanner scanner;

    /**
     * Constructs a Save parser.
     *
     * @param scanner scanner object that is reading the save file
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
     * @return new task from save file
     * @throws DukeReadSaveException if there is an invalid task
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

    /**
     * Test if next line of savefile is the start of a new task block.
     *
     * @return true if next line of savefile is the start of a new task block
     */
    private boolean nextLineIsANewTask() {
        return this.scanner.hasNext("(Task:).*");
    }

    /**
     * Parse the next line of the savefile and extract Key:Value as a 2 member string array.
     *
     * @return Pair of [Key,Value]
     */
    private String[] getNextKeyValuePair() {
        return this.scanner.nextLine().split(":", 2);
    }

    /**
     * Create a checklist of arguments to mark found.
     *
     * @param args String vargs of the keys to add to checklist
     * @return HashMap checklist of (String Key, Boolean found)
     */
    private HashMap<String, Boolean> createArgumentCheckList(String... args) {
        HashMap<String, Boolean> checkList = new HashMap<String, Boolean>(args.length);
        for (String arg : args) {
            checkList.put(arg, false);
        }
        return checkList;
    }

    private Todo parseTodo() throws DukeReadSaveException {
        HashMap<String, Boolean> argsFound = createArgumentCheckList(NAME_KEY, DONE_KEY);
        String name = "";
        boolean isDone = false;

        while (this.hasNextLine()) {
            if (nextLineIsANewTask()) {
                //break if next line is the start of a new task
                break;
            }
            String[] keyValuePair = this.getNextKeyValuePair();
            switch (keyValuePair[0]) {
            case (NAME_KEY):
                name = keyValuePair[1];
                break;
            case (DONE_KEY):
                isDone = Boolean.parseBoolean(keyValuePair[1]);
                break;
            default:
                // invalid line
            }
            if (argsFound.containsKey(keyValuePair[0])) {
                assert keyValuePair.length == 2;
                argsFound.put(keyValuePair[0], true);
            }
        }
        if (argsFound.containsValue(false)) {
            throw MISSING_ARG_EXCEPTION;
        }
        return new Todo(name, isDone);
    }

    private Deadline parseDeadline() throws DukeReadSaveException {
        HashMap<String, Boolean> argsFound = createArgumentCheckList(NAME_KEY, DONE_KEY, BY_KEY);
        String name = "";
        boolean isDone = false;
        String by = "";

        while (this.hasNextLine()) {
            if (nextLineIsANewTask()) {
                //break if next line is the start of a new task
                break;
            }
            String[] keyValuePair = this.getNextKeyValuePair();
            switch (keyValuePair[0]) {
            case (NAME_KEY):
                name = keyValuePair[1];
                break;
            case (DONE_KEY):
                isDone = Boolean.parseBoolean(keyValuePair[1]);
                break;
            case (BY_KEY):
                by = keyValuePair[1];
                break;
            default:
                // invalid line
            }
            if (argsFound.containsKey(keyValuePair[0])) {
                assert keyValuePair.length == 2;
                argsFound.put(keyValuePair[0], true);
            }
        }
        if (argsFound.containsValue(false)) {
            throw MISSING_ARG_EXCEPTION;
        }
        return new Deadline(name, isDone, by);

    }

    private Event parseEvent() throws DukeReadSaveException {
        HashMap<String, Boolean> argsFound = createArgumentCheckList(NAME_KEY, DONE_KEY, AT_KEY);
        String name = "";
        boolean isDone = false;
        String at = "";

        while (this.hasNextLine()) {
            if (nextLineIsANewTask()) {
                //break if next line is the start of a new task
                break;
            }
            String[] keyValuePair = this.getNextKeyValuePair();
            switch (keyValuePair[0]) {
            case (NAME_KEY):
                name = keyValuePair[1];
                break;
            case (DONE_KEY):
                isDone = Boolean.parseBoolean(keyValuePair[1]);
                break;
            case (AT_KEY):
                at = keyValuePair[1];
                break;
            default:
                // invalid line
            }
            if (argsFound.containsKey(keyValuePair[0])) {
                assert keyValuePair.length == 2;
                argsFound.put(keyValuePair[0], true);
            }
        }
        if (argsFound.containsValue(false)) {
            throw MISSING_ARG_EXCEPTION;
        }
        return new Event(name, isDone, at);
    }
}
