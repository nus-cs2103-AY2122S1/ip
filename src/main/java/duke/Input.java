package duke;

import java.util.ArrayList;

/**
 * Input class to handle inputs from the user.
 */
public class Input {

    private String input;

    /**
     * Constructor for Input.
     *
     * @param input User input.
     */
    public Input(String input) {
        this.input = input;
    }

    /**
     * Gets the description of the task.
     *
     * @param tag Represents the type of task in the user input.
     * @return Description of the task.
     */
    public String getDescription(String tag) {
        if (tag.equals("todo")) {
            return input.replaceFirst("^todo", "");
        } else if (tag.equals("deadline")) {
            return this.input.replaceFirst("^deadline", "").split(" /")[0];
        } else if (tag.equals("event")) {
            return this.input.replaceFirst("^event", "").split(" /")[0];
        }
        return "";
    }

    /**
     * Gets the date of Deadline and Event tasks.
     *
     * @param tag Represents the type of task in the user input.
     * @return Date of the task.
     */
    public String getDate(String tag) {
        if (tag.equals("deadline")) {
            return this.input.substring(input.indexOf("/by") + 4);
        } else if (tag.equals("event")) {
            return this.input.substring(input.indexOf("/at") + 4);
        }
        return "";
    }

    /**
     * Handles the deletion and marking as done of multiple tasks at once.
     *
     * @param tag Represents the type of operation to be done.
     * @param lsSize Size of the current list.
     * @return An ArrayList of indexes representing the tasks to be marked as done or deleted.
     * @throws DukeException If the task number is invalid and the task does not exist in the list.
     */
    public ArrayList<Integer> getIndexArray(String tag, int lsSize) throws DukeException {
        if (tag.equals("delete")) {
            return generateIndexArray(lsSize, this.input.substring(7).split(" "));
        } else if (tag.equals("done")) {
            return generateIndexArray(lsSize, this.input.substring(5).split(" "));
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Generates an array of indexes that represent the tasks to be deleted or marked as done.
     *
     * @param lsSize Size of current list.
     * @param indexes Array of task numbers representing tasks to be deleted or marked as done.
     * @return An ArrayList of indexes representing the tasks to be marked as done or deleted.
     * @throws DukeException If the task number is invalid and the task does not exist in the list.
     */
    @SafeVarargs
    public final ArrayList<Integer> generateIndexArray(int lsSize, String... indexes) throws DukeException {
        ArrayList<Integer> arr = new ArrayList<>();
        for (String i : indexes) {
            int index = Integer.parseInt(i) - 1;
            if (index < 0 || index >= lsSize) {
                throw new DukeException("Item does not exist in the list.");
            }
            arr.add(index);
        }
        return arr;
    }

    /**
     * Gets the keyword from the user input.
     *
     * @return The keyword to be searched.
     */
    public String getKeyword() {
        return this.input.substring(5);
    }

    /**
     * Checks if the user input contains the keyword.
     *
     * @param keyword The keyword to be searched.
     * @return Boolean value representing whether the input contains the keyword.
     */
    public boolean checkIfContains(String keyword) {
        return this.input.contains(keyword);
    }

    /**
     * Checks if the user input has the delete or done command word only, without the task number.
     *
     * @param tag Represents whether the command is a delete or mark as done command.
     * @return Boolean value representing whether the input only has the command word.
     */
    public boolean hasCommandWordOnly(String tag) {
        if (tag.equals("delete")) {
            return (this.input.equals("delete") || input.equals("delete "));
        } else if (tag.equals("done")) {
            return (equals("done") || input.equals("done "));
        }
        return false;
    }

    /**
     * Checks if the Input object is equal to another object.
     *
     * @param o Object for comparison.
     * @return Boolean value representing whether the input Strings are equal.
     */
    @Override
    public boolean equals(Object o) {
        return this.input.equals(o);
    }

    /**
     * Checks the type of command the user input represents.
     *
     * @return A String tag corresponding to the type of command represented by the user command.
     */
    public String checkType() {
        if (input.equals("bye")) {
            return "bye";
        } else if (input.equals("list")) {
            return "list";
        } else if (input.startsWith("done")) {
            return "done";
        } else if (input.startsWith("delete")) {
            return "delete";
        } else if (input.startsWith("find")) {
            return "find";
        } else if (input.startsWith("todo")) {
            return "todo";
        } else if (input.startsWith("deadline")) {
            return "deadline";
        } else if (input.startsWith("event")) {
            return "event";
        } else {
            return "error";
        }
    }
}
