/**
 * An Event is a Task that recognises /at time
 */

class Event extends Task {
    public Event(String description) {
        super(processEventDescription(description));
    }

    private static String processEventDescription(String description) {
        String eventDescription = description.split(" /at")[0];
        int len = eventDescription.length() + 5;
        if (len < description.length()) {
            return eventDescription + "(at: " + description.substring(len) + ")";
        } else {
            return eventDescription + "(at: )";
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString();
    }
}
