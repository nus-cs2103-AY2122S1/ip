/**
 * A deadline is a Task that recognises /by deadline
 */

class Deadline extends Task {
    public Deadline(String description) {
        super(processDeadline(description));
    }

    private static String processDeadline(String description) {
        String eventDescription = description.split(" /by")[0];
        int len = eventDescription.length() + 5;
        if (len < description.length()) {
            return eventDescription + "(by: " + description.substring(len) + ")";
        } else {
            return eventDescription + "(by: )";
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
