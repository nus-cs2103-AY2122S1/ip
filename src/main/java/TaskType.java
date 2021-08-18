import java.util.regex.Matcher;

public enum TaskType {
    TODO,
    EVENT,
    DEADLINE;

    /**
     * Based on the type provided, return the corresponding class
     *
     * @param input Groups of information needed for the task
     * @param type Type of task to use
     * @return A Task that matches that of its TaskType
     */
    public static Task getTask(Matcher input, TaskType type) {
        switch(type) {
            case TODO:
                return new TaskTodo(input.group(1));
            case DEADLINE:
                return new TaskDeadline(input.group(1), input.group(2));
            case EVENT:
                return new TaskEvent(input.group(1), input.group(2));
            default: return null;
        }
    }


}
