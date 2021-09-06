package duke.storage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Separates the date/time from the task, depending on the nature of the task.
 *
 * @author Benjamin Lui
 */

public class DesAndTime {

    private String taskDescription;
    private String timing;
    private String task;
    // skips 'by: ' to obtain the time information
    private final int skippedIndexToDate = 4;

    /**
     * Constructor of the class.
     * @param task the task to be processed
     */
    DesAndTime(String task) {
        this.task = task;
    }

    /**
     * Processes the task if it is a deadline task.
     */
    void deadLine() {
        char[] arr = task.toCharArray();
        String description = "";
        String time = "";
        int index = 0;
        while (index < arr.length) {
            if (arr[index] == '(') {
                break;
            }
            description += arr[index];
            index++;
        }
        index += skippedIndexToDate;
        while (index < arr.length) {
            if (arr[index] == ')') {
                break;
            }
            time += arr[index];
            index++;
        }
        this.taskDescription = description;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = LocalDate.parse(time.trim(), formatter);
        this.timing = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }

    /**
     * Processes the task if it's an event task.
     */
    void event() {
        char[] arr = task.toCharArray();
        String description = "";
        String time = "";
        int index = 0;
        while (index < arr.length) {
            if (arr[index] == '(') {
                break;
            }
            description += arr[index];
            index++;
        }
        index += skippedIndexToDate;
        while (index < arr.length) {
            if (arr[index] == ')') {
                break;
            }
            time += arr[index];
            index++;
        }
        this.taskDescription = description;
        this.timing = time;
    }
    String getDes() {
        return taskDescription;
    }

    String getTime() {
        return timing;
    }
}
