public class InvalidDateAndTimeException extends DukeException {
    private String taskType;

    public InvalidDateAndTimeException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    @Override
    void printErrorMessage() {
        switch (taskType) {
            case Duke.DEADLINE:
                System.out.println("☹ OOPS!!! Please provide a deadline with /by.");
                break;
            case Duke.EVENT:
                System.out.println("☹ OOPS!!! Please provide a event time with /at.");
                break;

        }
    }
}

