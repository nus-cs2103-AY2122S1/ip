package jarvis.exception;

public class TaskDetailsEmptyException extends JarvisException {
    public TaskDetailsEmptyException(String emptyField) {
        super(String.format(
                "Violation of Stark Industries task %s format! It should not be empty!",
                emptyField
        ));
    }
}
