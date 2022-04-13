package exception;

public class InvalidDescription extends DukeException {

    private String type;

    /**
     * Creates an exception that highlights missing description when creating a task type.
     *
     * @param type the type of task that was intended for creation.
     */
    public InvalidDescription(String type) {
        this.type = type;
    }

    /**
     * Returns the information regarding the exception.
     *
     * @return message containing exception reason.
     */
    @Override
    public String getMessage() {
        return "Hey! I think you have missing fields needed to create the " + type;
    }
}
