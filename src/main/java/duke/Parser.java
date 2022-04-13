package duke;

import duke.task.DukeList;

/**
 * Encapsulates the parser of user input to duke.
 */
public class Parser {

    /** List of duke. */
    private final DukeList list;

    /** Storage of duke. */
    private final Storage storage;

    /** State of whether duke's storage has been loaded. */
    private boolean isStorageLoaded;


    /**
     * Constructs a Parser object.
     *
     * @param list The list of duke.
     * @param storage The storage of duke.
     */
    public Parser(DukeList list, Storage storage) {
        this.list = list;
        this.storage = storage;
        this.isStorageLoaded = false;
    }

    /**
     * Parses user input to duke and runs the appropriate function.
     *
     * @param input User input.
     * @return The response to the user's input.
     */
    public String parse(String input) {
        String[] segment = input.split(" ", 2);

        int parameters = segment.length;

        boolean hasBody = parameters == 2;

        String command = segment[0];
        String body = hasBody ? segment[1] : "";
        String response;

        try {

            if (!isStorageLoaded) {
                storage.load();
                isStorageLoaded = true;
            }

            if (isValidList(input)) {
                response = list.list();
            } else if (isValidDone(command, parameters)) {
                response = list.done(Integer.parseInt(body));
                storage.save();
            } else if (isValidDelete(command, parameters)) {
                response = list.delete(Integer.parseInt(body));
                storage.save();
            } else if (isValidToDo(command)) {
                response = list.addToDo(body);
                storage.save();
            } else if (isValidDeadline(command)) {
                response = list.addDeadline(body);
                storage.save();
            } else if (isValidEvent(command)) {
                response = list.addEvent(body);
                storage.save();
            } else if (isValidFind(command)) {
                response = list.find(body);
            } else if (isValidUndo(input)) {
                response = list.undo();
                storage.save();
            } else if (isValidExit(input)) {
                response = Ui.DUKE_EXIT;
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry,"
                        + " but I don't know what that means :-(");
            }

            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private boolean isValidList(String input) {
        return input.equals("list");
    }

    private boolean isValidDone(String command, int parameters) {
        return command.equals("done") && parameters == 2;
    }

    private boolean isValidDelete(String command, int parameters) {
        return command.equals("delete") && parameters == 2;
    }

    private boolean isValidToDo(String command) {
        return command.equals("todo");
    }

    private boolean isValidDeadline(String command) {
        return command.equals("deadline");
    }

    private boolean isValidEvent(String command) {
        return command.equals("event");
    }

    private boolean isValidFind(String command) {
        return command.equals("find");
    }

    private boolean isValidExit(String input) {
        return input.equals("bye");
    }

    private boolean isValidUndo(String input) {
        return input.equals("undo");
    }
}
