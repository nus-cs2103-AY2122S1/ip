package back;

import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {

    private static final String NEW_LINE = "\r\n";

    /**
     * Returns a string suitable for writing into the saveFile.
     * @param task task to be converted.
     * @return Suitable string.
     */
    public static String taskToString(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof Todo) {
            sb.append("T").append(NEW_LINE);
            sb.append(task.taskName).append(NEW_LINE);
        } else if (task instanceof Event) {
            Event e = (Event) task;
            sb.append("E").append(NEW_LINE);
            sb.append(task.taskName).append(NEW_LINE);
            sb.append(e.date).append(NEW_LINE);
        } else if (task instanceof Deadline) {
            Deadline e = (Deadline) task;
            sb.append("D").append(NEW_LINE);
            sb.append(task.taskName).append(NEW_LINE);
            sb.append(e.date).append(NEW_LINE);
        }
        sb.append(task.isDone ? "1" : "0").append(NEW_LINE);
        return sb.toString();
    }

    /**
     * Returns a string suitable for writing into the saveFile.
     * @param list list to be converted.
     * @return Suitable string.
     */
    public static String listToString(ArrayList<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (Task s : list) {
            sb.append(taskToString(s));
        }
        return sb.toString();
    }

    /**
     * Translate a string into a LocalDate object.
     * @param fullDate String to be translated.
     * @return Resultant LocalDate object.
     */
    public static LocalDate parseDateString (String fullDate) {
        assert(fullDate.matches("^[0-9]{4}([-])(((0[13578]|(10|12))\\1(0[1-9]|[1-2][0-9]|3[0-1]))|(02\\1(0[1-9]"
                + "|[1-2][0-9]))|((0[469]|11)\\1(0[1-9]|[1-2][0-9]|30)))$")) : "Date string of incorrect format.";
        String[] fullDateSplit = fullDate.split("-");
        return LocalDate.of(Integer.parseInt(fullDateSplit[0]),
                Integer.parseInt(fullDateSplit[1]), Integer.parseInt(fullDateSplit[2]));
    }
}
