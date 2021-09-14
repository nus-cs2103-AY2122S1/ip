package duke.executions;

import java.util.ArrayList;

import duke.task.Task;

/**
 * @@author Hang Zelin
 *
 * It stores previous operation info and methods to undo previous operation.
 */
public class LastExecution {
    //Constant values
    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String DONE = "done";

    private final String lastOperation;
    private final Task lastTask;
    private final int lastIndex;
    private final ArrayList<Task> tasks;

    /**
     * Constructor containing all last tasks.
     *
     * @param lastOperation user's previous operation
     * @param lastTask user's previous task
     * @param lastIndex user's previous index
     * @param tasks user's taskList.
     */
    public LastExecution(String lastOperation, Task lastTask, int lastIndex, ArrayList<Task> tasks) {
        this.lastOperation = lastOperation;
        this.lastTask = lastTask;
        this.lastIndex = lastIndex;
        this.tasks = tasks;
    }

    private String resetDone() {
        String text;
        this.tasks.get(lastIndex).resetDone();
        text = "Good! I have recovered your task to undone again!\n";
        return text;
    }

    private String undoDelete() {
        String text;
        this.tasks.add(lastIndex, lastTask);
        text = "Good! I have helped recovered your deleted task!\n";;
        return text;
    }

    private String undoAdd() {
        String text;
        this.tasks.remove(tasks.size() - 1);
        text = "Good! I have undone your newly added task!\n";;
        return text;
    }

    /**
     * Returns a message in undo to help user to undo their previous work.
     *
     * @return String message showing that duke has helped user to undo his previous operation.
     */
    public String undo() {
        String text;

        switch (lastOperation) {
        case ADD: text = undoAdd();
        break;
        case DELETE: text = undoDelete();
        break;
        case DONE: text = resetDone();
        break;
        default: text = "OOPs! There is no previous operation!\n";
        }
        return text;
    }
}
