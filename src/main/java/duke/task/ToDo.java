package duke.task;

import java.util.Optional;

public class ToDo extends Task{

    private ToDo(String description) {
        super(description);
    }

    public static ToDo of(Optional<String> description) throws IllegalArgumentException {
        return new ToDo(description.orElseThrow(() -> new IllegalArgumentException(
                "☹ OOPS!!! The description of a todo cannot be empty."
        )));
    }

    public static Task of(boolean isDone, String description) {
        Task ret = new ToDo(description);
        return isDone ? ret.done() : ret;
    }

    public String getTaskType() { return "T"; }

    @Override
    public String toString() {
        return String.format("[%s]%s", this.getTaskType(), super.toString());
    }
}
