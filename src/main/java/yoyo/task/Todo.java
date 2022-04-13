package yoyo.task;

import static yoyo.utility.Constant.COMMA_SEPARATOR;
import static yoyo.utility.Constant.WHITESPACE;

/**
 * A subclass of Task of todo type.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class with name parameter.
     *
     * @param name Name of Todo.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Constructor for the Todo class with name and isDone parameter.
     *
     * @param name   Name of Todo.
     * @param isDone Completion status of task.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Constructor for the Todo class with name, isDone and tags parameter.
     *
     * @param name   Name of Todo.
     * @param isDone Completion status of this task.
     * @param tags   Array of tags belonging to this task.
     */
    public Todo(String name, boolean isDone, String[] tags) {
        super(name, isDone, tags);
    }

    /**
     * Returns a status string indicating type of task.
     *
     * @return An indicator string for the type of task.
     */
    @Override
    public String printType() {
        return "[T]";
    }

    /**
     * Produces a string containing task's status.
     *
     * @return a string containing task's status.
     */
    @Override
    public String showStatus() {
        return printType()
                + printCompletionStatus()
                + WHITESPACE
                + name
                + WHITESPACE
                + showTags();
    }

    @Override
    public String showStatusWrite() {
        return this.printType()
                + this.printCompletionStatus()
                + COMMA_SEPARATOR
                + this.name
                + this.showTagsWriteFormat();
    }


}
