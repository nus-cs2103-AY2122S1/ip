package duke.task;

import java.util.stream.Stream;

public enum TaskType {
    TODO(ToDoTask.class), DEADLINE(DeadlineTask.class), EVENT(EventTask.class), DURATION(DurationTask.class);

    private Class<? extends Task> type;

    private TaskType(Class<? extends Task> type) {
        this.type = type;
    }

    static TaskType byTask(Class<? extends Task> taskClass) {
        return Stream.of(values()).filter(taskType -> taskType.type.equals(taskClass)).findFirst().orElse(null);
    }
}
