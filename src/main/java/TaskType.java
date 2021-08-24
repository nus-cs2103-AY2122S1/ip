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
}

