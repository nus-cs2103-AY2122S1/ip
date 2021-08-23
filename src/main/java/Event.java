/**
 * An Event is a Task that recognises /at time
 *
 * @author Chen Yanyu
 */

class Event extends Task {
    private final String task;
    private final String time;

    public Event(String description) throws EmptyDescriptionException, WrongFormatException {
        super(processEventDescription(description));
        String[] descriptionTime = description.split(" /at ");
        this.task = descriptionTime[0];
        this.time = descriptionTime[1];
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

    /**
     * return the save string arrays for file writing
     *
     * @return the array of Strings representing the task
     */
    public String[] saveStrings() {
        String isDone = this.getIsDone() ? "1" : "0";
        return new String[]{"E", isDone, this.task, this.time};
    }
}
