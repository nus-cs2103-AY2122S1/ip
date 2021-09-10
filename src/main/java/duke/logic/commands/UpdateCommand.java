package duke.logic.commands;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.logic.tasks.TaskList;

/**
 * Updates a task with the specified new information.
 */
public class UpdateCommand extends Command {
    static final String TASK_UPDATED_MSG = "I've updated this task:\n  %s";
    static final String EMPTY_TASK_LIST_MSG = "You don't have any tasks!";
    static final String TASK_NUMBER_OUT_OF_BOUNDS_MSG = "Invalid task number! Must be between 1 and %d";
    static final String NO_FIELD_PROVIDED_MSG = "Provide at lease one field to update.";

    private final int taskNo;
    private final UpdateTaskDescriptor updateTaskDescriptor;

    /**
     * Creates a command to update a task.
     *
     * @param taskNo The number of the task to be updated.
     * @param newDescription The new description, if any.
     * @param newBy The new deadline (for a deadline task), if any.
     * @param newAt The new starting time (for an event task), if any.
     * @param newEnd The new ending time (for an event task), if any.
     */
    public UpdateCommand(
            int taskNo, String newDescription, LocalDateTime newBy, LocalDateTime newAt, LocalDateTime newEnd) {
        this.taskNo = taskNo;
        this.updateTaskDescriptor = new UpdateTaskDescriptor(newDescription, newBy, newAt, newEnd);
    }

    /**
     * Updates the task with new information. No changes occur if the
     * task number is non-positive or greater than the task list size,
     * or if no fields are given to update.
     *
     * @return The execution result
     */
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

    /**
     * Encapsulates the possible fields to update and their values, if any.
     * Each non-empty field value will replace the corresponding field value of the task, provided
     * that the field exists in that task (e.g. a non-null field {@code at} will be ignored when
     * updating a deadline task).
     */
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

        /**
         * Checks if at least one field value is non-empty.
         *
         * @return true if any one field is not null, false if all fields are null.
         */
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
