package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class LastCommand {
    private String lastOperation;
    private Task lastTask;
    private int lastIndex;
    private ArrayList<Task> tasks;

    /**
     * Constructor containing all last tasks.
     *
     * @param lastOperation user's previous operation
     * @param lastTask user's previous task
     * @param lastIndex user's previous index
     * @param tasks user's taskList.
     */
    public LastCommand(String lastOperation, Task lastTask, int lastIndex, ArrayList<Task> tasks) {
        this.lastOperation = lastOperation;
        this.lastTask = lastTask;
        this.lastIndex = lastIndex;
        this.tasks = tasks;
    }

    private void resetDone() {
        this.tasks.get(lastIndex).resetDone();
    }

    private void undoDelete() {
        this.tasks.add(lastIndex, lastTask);
    }

    private void undoAdd() {
        this.tasks.remove(tasks.size() - 1);
    }

    /**
     * Returns a undo Message and help user to undo their previous work.
     *
     * @return String message showing that duke has helped user to undo his previous operation.
     */
    public String undo() {
        String text;
        switch (lastOperation) {
        case "add" -> {
            undoAdd();
            text = "Good! I have undone your newly added task!\n";
        }
        case "delete" -> {
            undoDelete();
            text = "Good! I have helped recovered your deleted task!\n";
        }
        case "done" -> {
            resetDone();
            text = "Good! I have recovered your task to undone again!\n";
        }
        default -> text = "OOPs! There is no previous operation!\n";
        }
        return text;
    }
}
