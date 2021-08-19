/**
 * A deadline is a Task that recognises /by deadline
 *
 * @author Chen Yanyu
 */

class Deadline extends Task {
    public Deadline(String description) throws EmptyDescriptionException, WrongFormatException {
        super(processDeadline(description));
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
}
