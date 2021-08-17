public class EmptyTaskDescriptionException extends DukeException {
    private String taskType;

    public EmptyTaskDescriptionException(String errorMessage, String taskType) {
        super(errorMessage);
        this.taskType = taskType;
    }

    @Override
    void printErrorMessage() {
        switch (taskType) {
            case Duke.TODO:
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                break;
            case Duke.DEADLINE:
                System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                break;
            case Duke.EVENT:
                System.out.println("☹ OOPS!!! The description of a event cannot be empty.");
                break;

        }
    }
}
