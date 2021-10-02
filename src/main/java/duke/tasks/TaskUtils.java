package duke.tasks;

import java.time.LocalDate;

/** Utility class for handling things related to Task **/
public class TaskUtils {
    /** cached things */
    public static String DONE_MARKER = "1";
    public static String NOT_DONE_MARKER = "0";
    public static String DELIMITER = " | ";

    /**
     * Returns a task encoded by the given String
     *
     * @param str
     * @return Task specified by str
     */
    public static Task stringToTask(String str) {
        String[] segments = str.split(" \\| ");
        if (segments.length < 3) {
            return null;
        }
        boolean isDone = segments[1].equals(DONE_MARKER);
        Task toReturn;
        switch (segments[0]) {
            case "T":
                toReturn = new Todo(segments[2]);
                break;
            case "D":
                if (segments.length != 4) {
                    return null;
                }
                toReturn = new Deadline(segments[2], LocalDate.parse(segments[3]));
                break;
            case "E":
                if (segments.length != 4) {
                    return null;
                }
                toReturn = new Event(segments[2], LocalDate.parse(segments[3]));
                break;
            case "R":
                if (segments.length != 5) {
                    return null;
                }
                toReturn = new RecurringTask(segments[2], LocalDate.parse(segments[3]),
                        Integer.parseInt(segments[4]));
                break;
            default:
                return null;
        }
        if (isDone) {
            toReturn.markDone();
        }
        return toReturn;
    }

    /**
     * Returns a string representation of a task for saving
     *
     * @param task
     * @return specific string representation of a task
     */
    public static String taskToString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getIdentifier())
                .append(DELIMITER)
                .append(task.isDone() ? DONE_MARKER : NOT_DONE_MARKER)
                .append(DELIMITER)
                .append(task.getDetailsWithDelimiter(DELIMITER));
        return sb.toString();
    }
}
