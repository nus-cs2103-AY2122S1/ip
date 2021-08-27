package gnosis.model;

/**
 * This enum specifies the different task types
 * a task has.
 *
 * @author Pawandeep Singh
 *
 * */
public enum TaskType {
    TODO, DEADLINE, EVENT;

    /**
     * Converts enum TaskType to character representative of task type.
     *
     * @param type The type of task.
     * @return String representative of event task.
     */
    public static char getTask(TaskType type) {
        switch (type) {
        case TODO:
            return 'T';
        case DEADLINE:
            return 'D';
        case EVENT:
            return 'E';
        default:
            return '-';
        }
    }

    /**
     * Converts character representative of task type to enum TaskType.
     *
     * @param type character representative of task type.
     * @return String representative of event task.
     */
    public static TaskType getTaskType(char type) {
        switch (type) {
        case 'T':
            return TODO;
        case 'D':
            return DEADLINE;
        case 'E':
            return EVENT;
        default:
            return null;
        }
    }

}

