package model;

public enum TaskType {
    TODO, DEADLINE, EVENT;

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

