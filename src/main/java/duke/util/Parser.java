package duke.util;

import duke.exception.DukeException;

/**
 * Represents an object to parse user's input and execute it.
 */
public class Parser {
    private TaskList list;

    public Parser(TaskList list) {
        this.list = list;
    }

    /**
     * Extracts an integer from a string input of format "string integer".
     *
     * @param input String containing an integer at 2nd position.
     * @return Integer at second position of String.
     * @throws DukeException If String does not have more than one word.
     */
    public int extractInt(String input) throws DukeException {
        String[] parts = input.split(" "); //split along the whitespace to get the integer
        if (parts.length != 2) { // checking for wrong format
            throw new DukeException();
        }
        String numStr = parts[1];
        return Integer.parseInt(numStr);
    }

    /**
     * Parses the input and then executes the instructions.
     *
     * @param input String of instructions.
     * @return The response from duke.Duke.
     */
    public String parseAndExecute(String input) {
        try {
            if (input.startsWith("find ")) {
                String desc = input.replaceFirst("find ", ""); //remove find from input
                if (desc.isBlank()) { //check for incomplete input
                    throw new DukeException();
                }
                return list.findTask(desc);
            } else if(input.startsWith("edit ")){ //edit x /description replacement
                String[] parts = input.split("/", 2); //splits input in 2 at /
                int posEdit = extractInt(parts[0]);
                return list.editTask(posEdit, parts[1]);
            } else if (input.startsWith("done ")) {
                int numInt = extractInt(input);
                return list.setIndexDone(numInt);
            } else if (input.startsWith("delete ")) {
                int numInt = extractInt(input);
                return list.deleteTask(numInt);
            } else if (input.startsWith("todo ")) {
                String task = input.replaceFirst("todo ", ""); //remove to-do from input
                if (task.isBlank()) { //check for incomplete input
                    throw new DukeException();
                }
                return list.addToDo(task);
            } else if (input.startsWith("deadline ")) {
                String task = input.replaceFirst("deadline ", ""); //remove deadline from input
                if (task.isBlank()) { //checking incomplete input
                    throw new DukeException();
                }
                String[] parts = task.split("/by "); //split string along keyword /by
                return list.addDeadline(parts[0], parts[1]);
            } else if (input.startsWith("event ")) {
                String task = input.replaceFirst("event ", ""); //remove event from input
                if (task.isBlank()) { //checking incomplete input
                    throw new DukeException();
                }
                String[] parts = task.split("/at "); //split string along keyword /at
                return list.addEvent(parts[0], parts[1]);
            } else {
                switch (input.toLowerCase()) {
                case "bye":
                    return "Bye. Hope to see you again soon!";
                case "list":
                    return list.show();
                default:
                    throw new DukeException();
                }
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
