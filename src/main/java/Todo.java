public class Todo extends Task{
    /**
     * Constructor for Todo
     *
     * @param description description of task
     */
    public Todo(String description) {
        super(description);
        if (description.length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYTODO);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
