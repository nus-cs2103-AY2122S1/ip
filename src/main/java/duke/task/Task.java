package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task contains a title and can be completed or not completed.
 *
 * @author Samay Sagar
 * @version CS2103 AY21/22 Sem 1
 */
//Solution below adapted from https://github.com/jovyntls/ip
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;
    protected LocalDate timeDue;

    protected enum Type {
        TODO('T'),
        EVENT('E'),
        DEADLINE('D');

        private final char indicator;

        Type(char indicator) {
            this.indicator = indicator;
        }
        public void setType(Task task) {
            task.type = this.indicator;
        }
    }

    /**
     * A constructor for a Task.
     *
     * @param description a String representing the user-input title of the task.
     * @param task the type of the task
     */
    public Task(String description, Type task) {
        this.description = description;
        this.isDone = false;
        task.setType(this);

    }

    /**
     * A factory method for creating tasks with additional details.
     * @param taskType a TypeIndicator enum representing the type of the task
     * @param isDone a boolean representing whether or not the task has been completed
     * @param title a String representing the title of the task
     * @param timeDue a String representing when the task is due, if it is due.
     * @return a Task that is created from the given details
     */
    public static Task createTask(
            Type taskType, boolean isDone, String title, LocalDate timeDue) {
        switch (taskType) {
        case DEADLINE:
            return new Deadline(title, timeDue, isDone);
        case EVENT:
            return new Event(title, timeDue, isDone);
        default:
            // If the type cannot be parsed, it defaults to TO-DO.
            return new ToDo(title, isDone);
        }

    }

    /**
     * Parses structured text into a Task.
     * Text must be of the format [typeIndicator][done][deadline]|[title]
     * @param text The text to be parsed into a Task.
     * @return a Task based on the parsed text
     */
    public static Task parseTaskFromSavedText(String text) {
        char typeIndicator = text.charAt(0);
        char doneIndicator = text.charAt(1);
        boolean isDone = doneIndicator == '1';
        String timeDueString = text.substring(2, text.indexOf('|'));
        LocalDate timeDue = timeDueString.length() == 0 ? null : LocalDate.parse(timeDueString);
        String title = text.substring(text.indexOf('|') + 1);
        return createTask(charToTypeEnum(typeIndicator), isDone, title, timeDue);
    }

    /**
     * Convert a task to a String that can be loaded as load data.
     * @return a String that represents its saved state
     */
    public String toSaveData() {
        int doneIndicator = isDone ? 1 : 0;
        String timeDueString = timeDue == null
                ? ""
                : timeDue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("%s%s%s|%s\n",
                type, doneIndicator, timeDueString, description);
    }

    /**
     * Marks a task as done.
     */
    public void completeTask() {
        isDone = true;
    }

    /**
     * Checks if the task title contains a given String.
     * @param keyword a String that may be contained in the task title.
     * @return a boolean representing whether or not the task contains the keyword.
     */
    public boolean titleContains(String keyword) {
        return description.contains(keyword);
    }

    /**
     * Returns the string representation of a task.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        String doneIndicator = isDone ? "x" : " ";
        return String.format("[%s][%s] %s", type, doneIndicator, description);
    }

    /**
     * Given a char, convert it to a type Enum.
     * @param t The char to be converted to a Type Enum
     * @return a Type enum
     */
    public static Type charToTypeEnum(char t) {
        switch (t) {
        case 'D':
            return Type.DEADLINE;
        case 'E':
            return Type.EVENT;
        default:
            // If the type cannot be inferred, return a TO-DO as default.
            return Type.TODO;
        }
    }
}
