/**
 * An Event is a Task that recognises /at time
 *
 * @author Chen Yanyu
 */

class Event extends Task {
    public Event(String description) throws EmptyDescriptionException, WrongFormatException {
        super(processEventDescription(description));
    }

    private static String processEventDescription(String description) throws WrongFormatException {
        String[] descriptionTime = description.split(" /at ");
        if (description.trim().equals("/at") || description.isBlank()) {
            return "";
        } else if (descriptionTime.length < 2) {
            throw new WrongFormatException("event <description> /at <time>");
        } else if (descriptionTime[0].isBlank() || descriptionTime[1].isBlank()) {
            return "";
        } else {
            return descriptionTime[0] + "(at: " + descriptionTime[1] + ")";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
