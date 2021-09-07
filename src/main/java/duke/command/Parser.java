package duke.command;

import duke.exception.InvalidInputException;
import duke.exception.InvalidTaskException;
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
     * Returns whether the user's input equals to "bye".
     *
     * @param task The input of user.
     * @return Whether the user want to terminate program.
     */
    public static boolean isBye(String task) {
        return task.equals(InputType.BYE.getValue());
    }

    /**
     * Returns the type of operation that user inputs.
     *
     * @param task The input of user.
     * @return the type of the operation that user wants.
     */
    public static InputType judgeType(String task) {
        InputType type = InputType.UNKNOWN;
        if (task.contains(InputType.TODO.getValue())) {
            type = InputType.TODO;
        } else if (task.contains(InputType.DEADLINE.getValue())) {
            type = InputType.DEADLINE;
        } else if (task.contains(InputType.EVENT.getValue())) {
            type = InputType.EVENT;
        } else if (task.contains(InputType.DONE.getValue())) {
            type = InputType.DONE;
        } else if (task.equals(InputType.LIST.getValue())) {
            type = InputType.LIST;
        } else if (task.contains(InputType.DELETE.getValue())) {
            type = InputType.DELETE;
        } else if (task.contains(InputType.FIND.getValue())) {
            type = InputType.FIND;
        } else if (task.contains(InputType.BYE.getValue())) {
            type = InputType.BYE;
        }
        return type;
    }

    /**
     * Tests whether it has a valid Todo task format. If yes, return the
     * corresponding Todo task. Otherwise, throw a exception saying that
     * it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding Todo task.
     * @throws InvalidTaskException if the task does not have the correct Todo format.
     */
    public static Task testTodoValidity(String task) throws InvalidTaskException {
        if (!task.contains("todo") || task.length() <= 5) {
            throw new InvalidTaskException("Todo");
        }
        String taskName = task.substring(5);
        return new Todo(taskName);
    }

    /**
     * Tests whether the input operation has a valid Deadline task format. If yes, return the
     * corresponding Deadline task. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding Deadline task.
     * @throws InvalidTaskException if the task does not have the correct Deadline format.
     */
    public static Task testDeadlineValidity(String task) throws InvalidTaskException {
        if (!task.contains("deadline") || !task.contains("/by")) {
            throw new InvalidTaskException("Deadline");
        }
        int position = task.indexOf('/');
        String taskName = task.substring(9, position - 1);
        String deadlineTime = task.substring(position + 4);
        return new Deadline(taskName, deadlineTime);
    }

    /**
     * Tests whether the input operation has a valid Event task format. If yes, return the
     * corresponding Deadline task. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding Event task.
     * @throws InvalidTaskException if the task does not have the correct Event format.
     */
    public static Task testEventValidity(String task) throws InvalidTaskException {
        if (!task.contains("event") || !task.contains("/at")) {
            throw new InvalidTaskException("Event");
        }
        int position = task.indexOf('/');
        String taskName = task.substring(6, position - 1);
        String eventTime = task.substring(position + 4);
        return new Event(taskName, eventTime);
    }

    /**
     * Tests whether the input operation means to delete an item. If yes, return the
     * corresponding item's number. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding number of the item user want to delete.
     * @throws InvalidInputException if the format of the input is not valid.
     */
    public static int findDeleteItem(String task) throws InvalidInputException {
        if (task.length() <= MIN_DELETE_ITEM_LENGTH) {
            throw new InvalidInputException("delete the item.");
        }
        return Integer.parseInt(task.substring(MIN_DELETE_ITEM_LENGTH));
    }

    /**
     * Tests whether the input operation means to mark item as done. If yes, return the
     * corresponding item's number. Otherwise, throw a exception saying that it is not valid.
     *
     * @param task The input of user.
     * @return The corresponding number of the item user want to mark as done.
     * @throws InvalidInputException if the format of the input is not valid.
     */
    public static int findFinishedItem(String task) throws InvalidInputException {
        if (task.length() <= MIN_DONE_ITEM_LENGTH) {
            throw new InvalidInputException("mark item as done.");
        }
        return Integer.parseInt(task.substring(MIN_DONE_ITEM_LENGTH));
    }
}
