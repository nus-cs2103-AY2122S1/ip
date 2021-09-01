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


    /**
     * Constructs a Parser object.
     *
     * @param list The list of duke.
     * @param storage The storage of duke.
     */
    public Parser(DukeList list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    /**
     * Parses user input to duke and runs the appropriate function.
     *
     * @param input User input.
     * @return The response to the user's input.
     */
    public String parse(String input) {
        String[] segment = input.split(" ", 2);

        String response;

        try {
            if (input.equals("list")) {
                response = list.list();
            } else if (segment[0].equals("done") && segment.length == 2) {
                response = list.done(Integer.parseInt(segment[1]));
                storage.save();
            } else if (segment[0].equals("delete") && segment.length == 2) {
                response = list.delete(Integer.parseInt(segment[1]));
                storage.save();
            } else if (segment[0].equals("todo")) {
                response = list.addToDo(input.split("todo", 2)[1]);
                storage.save();
            } else if (segment[0].equals("deadline")) {
                response = list.addDeadline(input.split("deadline", 2)[1]);
                storage.save();
            } else if (segment[0].equals("event")) {
                response = list.addEvent(input.split("event", 2)[1]);
                storage.save();
            } else if (segment[0].equals("find")) {
                response = list.find(input.split("find", 2)[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry,"
                        + " but I don't know what that means :-(");
            }

            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
