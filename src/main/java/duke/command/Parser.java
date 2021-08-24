package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Parser {
    private static final int MIN_DELETE_ITEM_LENGTH = 7;
    private static final int MIN_DONE_ITEM_LENGTH = 5;
    public static boolean isBye(String task) {
        return task.equals(InputTypes.BYE.getValue());
    }

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
        }
        return type;
    }

    public static Task isValidTodoTask(String task) throws Exception {
        if (!task.contains("todo") || task.length() <= 5) {
            throw new Exception("This is not a valid Todo task.");
        }
        String taskName = task.substring(5);
        return new Todo(taskName);
    }

    public static Task isValidDeadlineTask(String task) throws Exception {
        //deadline 123 /by 2020-12-08 23:00
        if (!task.contains("deadline") || !task.contains("/by")) {
            throw new Exception("This is not a valid Deadline task.");
        }
        int position = task.indexOf('/');
        String taskName = task.substring(9, position - 1);
        String deadlineTime = task.substring(position + 4);
        return new Deadline(taskName, deadlineTime);
    }

    public static Task isValidEventTask(String task) throws Exception {
        if (!task.contains("event") || !task.contains("/at")) {
            throw new Exception("This is not a valid Event task.");
        }
        int position = task.indexOf('/');
        String taskName = task.substring(6, position - 1);
        String eventTime = task.substring(position + 4);
        return new Event(taskName, eventTime);
    }

    public static int findDeleteItem(String task) throws Exception {
        if (task.length() <= MIN_DELETE_ITEM_LENGTH) {
            throw new Exception("Sorry I don't know which item you want to delete.");
        }
        return Integer.parseInt(task.substring(MIN_DELETE_ITEM_LENGTH));
    }

    public static int findFinishedItem(String task) throws Exception {
        if (task.length() <= MIN_DONE_ITEM_LENGTH) {
            throw new Exception("Sorry I don't know which item you want to delete.");
        }
        return Integer.parseInt(task.substring(MIN_DONE_ITEM_LENGTH));
    }

}
