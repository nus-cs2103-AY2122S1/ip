package duke;

/**
 * A class encapsulating methods related to the handling of a priority system for Tasks.
 *
 * @author Toh Wang Bin
 */
public class Priority {

    /**
     * The different levels of priority assignable to a Task.
     */
    public enum PriorityLevel { LOW, MEDIUM, HIGH, ASAP }

    /**
     * Returns the String representation of a priority level.
     *
     * @param priorityLevel The priority level specified.
     * @return A string.
     */
    public static String getPriorityString(Priority.PriorityLevel priorityLevel) {
        switch (priorityLevel) {
        //no need for breaks in intermediate cases, as function has terminated at the return statements
        case LOW:
            return " (Low Priority)";
        case MEDIUM:
            return " (Medium Priority)";
        case HIGH:
            return " (High Priority)";
        default:
            return " (ASAP)";
        }
    }

    /**
     * Returns the data string representation of a priority level.
     *
     * @param priorityLevel The priority level specified.
     * @return A string.
     */
    public static String toDataString(Priority.PriorityLevel priorityLevel) {
        switch (priorityLevel) {
        //no need for breaks in intermediate cases, as function has terminated at the return statements
        case LOW:
            return "low";
        case MEDIUM:
            return "medium";
        case HIGH:
            return "high";
        default:
            return "asap";
        }
    }

}
