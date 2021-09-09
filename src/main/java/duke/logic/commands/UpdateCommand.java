package duke.logic.commands;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.logic.tasks.TaskList;

public class UpdateCommand extends Command {
     static final String TASK_UPDATED_MSG = "I've updated this task:\n  %s";
     static final String EMPTY_TASK_LIST_MSG = "You don't have any tasks!";
     static final String TASK_NUMBER_OUT_OF_BOUNDS_MSG = "Invalid task number! Must be between 1 and %d";
     static final String NO_FIELD_PROVIDED_MSG = "Provide at lease one field to update.";

    private final int taskNo;
    private final UpdateTaskDescriptor updateTaskDescriptor;

    public UpdateCommand(int taskNo, String newDescription, LocalDateTime newBy, LocalDateTime newAt, LocalDateTime newEnd) {
        this.taskNo = taskNo;
        this.updateTaskDescriptor = new UpdateTaskDescriptor(newDescription, newBy, newAt, newEnd);
    }

    @Override
    public CommandResult execute() {
        TaskList taskList = getTaskList();
        if (taskNo > taskList.size() || taskNo <= 0) {
            String msg = (taskList.size() == 0)
                    ? EMPTY_TASK_LIST_MSG
                    : String.format(TASK_NUMBER_OUT_OF_BOUNDS_MSG, taskList.size());
            return new CommandResult(msg);
        }
        if (!updateTaskDescriptor.isAnyFieldNonNull()) {
            return new CommandResult(NO_FIELD_PROVIDED_MSG);
        }

        String result = taskList.update(taskNo, updateTaskDescriptor).toString();
        return new CommandResult(String.format(TASK_UPDATED_MSG, result));
    }

    public static class UpdateTaskDescriptor {
        private final String description;
        private final LocalDateTime by;
        private final LocalDateTime at;
        private final LocalDateTime end;

        UpdateTaskDescriptor(String description, LocalDateTime by, LocalDateTime at, LocalDateTime end) {
            this.description = description;
            this.by = by;
            this.at = at;
            this.end = end;
        }

        public Optional<String> getDescription() {
            return Optional.ofNullable(description);
        }

        public Optional<LocalDateTime> getBy() {
            return Optional.ofNullable(by);
        }

        public Optional<LocalDateTime> getAt() {
            return Optional.ofNullable(at);
        }

        public Optional<LocalDateTime> getEnd() {
            return Optional.ofNullable(end);
        }

        public boolean isAnyFieldNonNull() {
            boolean isDescriptionNonNull = (description != null);
            boolean isNewByNonNull = (by != null);
            boolean isNewAtNonNull = (at != null);
            boolean isNewEndNonNull = (end != null);
            return isDescriptionNonNull || isNewByNonNull || isNewAtNonNull || isNewEndNonNull;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof UpdateTaskDescriptor)) {
                return false;
            }
            UpdateTaskDescriptor other = (UpdateTaskDescriptor) obj;
            return description.equals(other.description)
                    && by.equals(other.by)
                    && at.equals(other.at)
                    && end.equals(other.end);
        }
    }
}
