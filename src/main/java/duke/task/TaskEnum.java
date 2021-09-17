package duke.task;

import duke.exception.DukeException;

//@@author {FergusMok}--reused
//{The following code is reused from Bryan Loh's repository. It is an exemplary method of implementing enums.}
//{https://github.com/Anonymxtrix/ip/blob/master/src/main/java/duke/task/TaskType.java}

public enum TaskEnum {
    TODO("todo"), EVENT("event"), DEADLINE("deadline");

    private final String taskType;
    private static final String ERROR_INVALID_TASK_COMMAND = "OOPS!!! This is an invalid task type.";

    TaskEnum(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String toString() {
        return this.taskType;
    }

    public static TaskEnum getTask(String command) throws DukeException {
        for (TaskEnum task : TaskEnum.values()) {
            if (task.taskType.equals(command)) {
                return task;
            }
        }
        throw new DukeException(ERROR_INVALID_TASK_COMMAND);
    }

}

//@@author