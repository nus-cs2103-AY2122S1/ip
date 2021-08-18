public class Deadline extends Task{
    private String by;

    /**
     * Constructor for Deadline tasks
     *
     * @param description description of task /by specific date or time
     */
    public Deadline(String description) {
        super(description.split("/by", -1)[0]);
        String[] arr = description.split("/by", -1);
        if (arr[0].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYDEADLINE);
        } else if (arr.length <= 1) {
            throw new AilurusException(AilurusException.Error.BY);
        } else if (arr[1].length() == 0) {
            throw new AilurusException(AilurusException.Error.EMPTYBY);
        } else {
            this.by = arr[1];
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
