package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with making sense of the user command. Recognize the information containing in
 * the input, and returns respective Task or throws exceptions.
 */
public class Parser {
    private static final int MIN_DELETE_ITEM_LENGTH = 7;
    private static final int MIN_DONE_ITEM_LENGTH = 5;

    /**
     * Return whether the user's input equals to "bye".
     *
     * @param task The input of user.
     * @return Whether the user want to terminate program.
     */
    public static boolean isBye(String task) {
        return task.equals(InputTypes.BYE.getValue());
    }

    /**
     * Return the type of operation that user inputs.
     *
     * @param task The input of user.
     * @return the type of the operation that user wants.
     */
    public static InputTypes judgeType(String task) {
        InputTypes type = InputTypes.UNKNOWN;
        if (task.contains(InputTypes.TODO.getValue())) {
            type = InputTypes.TODO;
        } else if (task.contains(InputTypes.DEADLINE.getValue())) {
            type = InputTypes.DEADLINE;
        } else if (task.contains(InputTypes.EVENT.getValue())) {
            type = InputTypes.EVENT;
        } else if (task.contains(InputTypes.DONE.getValue())) {
            type = InputTypes.DONE;
        } else if (task.equals(InputTypes.LIST.getValue())) {
            type = InputTypes.LIST;
        } else if (task.contains(InputTypes.DELETE.getValue())) {
            type = InputTypes.DELETE;
        } else if (task.contains(InputTypes.FIND.getValue())) {
            type = InputTypes.FIND;
        }
        return type;
    }

    /**
     * Test whether it has a valid Todo task format. If yes, return the
     * corresponding Todo task. Otherwise, throw a exception saying that
     * it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding Todo task.
     * @throws Exception if the task does not have the correct Todo format.
     */
    public static Task isValidTodoTask(String task) throws Exception {
        if (!task.contains("todo") || task.length() <= 5) {
            throw new Exception("This is not a valid Todo task.");
        }
        String taskName = task.substring(5);
        return new Todo(taskName);
    }

    /**
     * Test whether the input operation has a valid Deadline task format. If yes, return the
     * corresponding Deadline task. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding Deadline task.
     * @throws Exception if the task does not have the correct Deadline format.
     */
    public static Task isValidDeadlineTask(String task) throws Exception {
        if (!task.contains("deadline") || !task.contains("/by")) {
            throw new Exception("This is not a valid Deadline task.");
        }
        int position = task.indexOf('/');
        String taskName = task.substring(9, position - 1);
        String deadlineTime = task.substring(position + 4);
        return new Deadline(taskName, deadlineTime);
    }

    /**
     * Test whether the input operation has a valid Event task format. If yes, return the
     * corresponding Deadline task. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding Event task.
     * @throws Exception if the task does not have the correct Event format.
     */
    public static Task isValidEventTask(String task) throws Exception {
        if (!task.contains("event") || !task.contains("/at")) {
            throw new Exception("This is not a valid Event task.");
        }
        int position = task.indexOf('/');
        String taskName = task.substring(6, position - 1);
        String eventTime = task.substring(position + 4);
        return new Event(taskName, eventTime);
    }

    /**
     * Test whether the input operation means to delete an item. If yes, return the
     * corresponding item's number. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding number of the item user want to delete.
     * @throws Exception if the format of the input is not valid.
     */
    public static int findDeleteItem(String task) throws Exception {
        if (task.length() <= MIN_DELETE_ITEM_LENGTH) {
            throw new Exception("Sorry I don't know which item you want to delete.");
        }
        return Integer.parseInt(task.substring(MIN_DELETE_ITEM_LENGTH));
    }

    /**
     * Test whether the input operation means to mark item as done. If yes, return the
     * corresponding item's number. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding number of the item user want to mark as done.
     * @throws Exception if the format of the input is not valid.
     */
    public static int findFinishedItem(String task) throws Exception {
        if (task.length() <= MIN_DONE_ITEM_LENGTH) {
            throw new Exception("Sorry I don't know which item you want to mark as done.");
        }
        return Integer.parseInt(task.substring(MIN_DONE_ITEM_LENGTH));
    }
}
