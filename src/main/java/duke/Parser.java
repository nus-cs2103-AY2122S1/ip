package duke;

import duke.task.DukeList;

/**
 * Encapsulates the parser of user input to duke.
 */
public class Parser {

    /** List of duke. */
    private final DukeList LIST;

    /** Storage of duke. */
    private final Storage STORAGE;


    /**
     * Constructs a Parser object.
     *
     * @param list The list of duke.
     * @param storage The storage of duke.
     */
    public Parser(DukeList list, Storage storage) {
        this.LIST = list;
        this.STORAGE = storage;
    }

    /**
     * Parses user input to duke and runs the appropriate function.
     *
     * @param input User input.
     */
    public void parse(String input) {
        String[] segment = input.split(" ", 2);

        try {
            if (input.equals("list")) {
                LIST.list();
            } else if (segment[0].equals("done") && segment.length == 2) {
                LIST.done(Integer.parseInt(segment[1]));
                STORAGE.save();
            } else if (segment[0].equals("delete") && segment.length == 2) {
                LIST.delete(Integer.parseInt(segment[1]));
                STORAGE.save();
            } else if (segment[0].equals("todo")) {
                LIST.addToDo(input.split("todo", 2)[1]);
                STORAGE.save();
            } else if (segment[0].equals("deadline")) {
                LIST.addDeadlines(input.split("deadline", 2)[1]);
                STORAGE.save();
            } else if (segment[0].equals("event")) {
                LIST.addEvents(input.split("event", 2)[1]);
                STORAGE.save();
            } else if (segment[0].equals("find")) {
                LIST.find(input.split("find", 2)[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry,"
                        + " but I don't know what that means :-(");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
