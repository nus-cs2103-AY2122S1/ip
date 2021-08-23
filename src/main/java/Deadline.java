/**
 * A deadline is a Task that recognises /by deadline
 *
 * @author Chen Yanyu
 */

class Deadline extends Task {
    private final String task;
    private final String time;

    public Deadline(String description) throws EmptyDescriptionException, WrongFormatException {
        super(processDeadline(description));
        String[] descriptionTime = description.split(" /by ");
        this.task = descriptionTime[0];
        this.time = descriptionTime[1];
    }

    private static String processDeadline(String description) throws WrongFormatException {
        String[] descriptionTime = description.split(" /by ");
        if (description.trim().equals("/by") || description.isBlank()) {
            return "";
        } else if (descriptionTime.length < 2) {
            throw new WrongFormatException("event <description> /by <time>");
        } else if (descriptionTime[0].isBlank() || descriptionTime[1].isBlank()) {
            return "";
        } else {
            return descriptionTime[0] + "(by: " + descriptionTime[1] + ")";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    /**
     * return the save string arrays for file writing
     *
     * @return the array of Strings representing the task
     */
    public String[] saveStrings() {
        String isDone = this.getIsDone() ? "1" : "0";
        return new String[]{"D", isDone, this.task, this.time};
    }
}
