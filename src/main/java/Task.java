import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String title;
    protected boolean isDone;
    protected char typeIndicator;
    protected LocalDate timeDue;

    protected enum TypeIndicators {
        TODO('T'),
        EVENT('E'),
        DEADLINE('D');

        public final char indicator;

        TypeIndicators(char indicator) {
            this.indicator = indicator;
        }

        public void setIndicatorForTask(Task task) {
            task.typeIndicator = this.indicator;
        }
    }

    /**
     * A constructor for a Task.
     *
     * @param title a String representing the user-input title of the task.
     * @param taskType the type of the task
     */
    public Task(String title, TypeIndicators taskType) {
        this.title = title;
        this.isDone = false;
        taskType.setIndicatorForTask(this);
    }

    /**
     * A factory method for creating tasks with additional details.
     * @param taskType a TypeIndicator enum representing the type of the task
     * @param isDone a boolean representing whether or not the task has been completed
     * @param title a String representing the title of the task
     * @param timeDue a String representing when the task is due, if it is due.
     * @return
     */
    public static Task createTaskWithDetail(TypeIndicators taskType, boolean isDone, String title, LocalDate timeDue) {
        switch (taskType) {
            case TODO:
                return new Todo(title, isDone);
            case DEADLINE:
                return new Deadline(title, timeDue, isDone);
            case EVENT:
                return new Event(title, timeDue, isDone);
        }
        // If the type cannot be parsed, it defaults to TO-DO.
        return new Todo(title, isDone);
    }

    /**
     * Parses structured text into a Task.
     * Text must be of the format <typeIndicator><done><deadline>|<title>
     * @param text The text to be parsed into a Task.
     * @return a Task based on the parsed text
     */
    public static Task parseTaskFromSavedText(String text) {
        char typeIndicator = text.charAt(0);
        char doneIndicator = text.charAt(1);
        boolean isDone = doneIndicator == '1';
        LocalDate timeDue = LocalDate.parse(text.substring(2, text.indexOf('|')));
        String title = text.substring(text.indexOf('|') + 1);
        return createTaskWithDetail(charToTypeEnum(typeIndicator), isDone, title, timeDue);
    }


    /**
     * Convert a task to a String that can be loaded as load data.
     * @return a String that represents its saved state
     */
    public String toSaveData() {
        int doneIndicator = this.isDone ? 1 : 0;
        String timeDueString = this.timeDue == null ? "" : this.timeDue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return String.format("%s%s%s|%s\n", this.typeIndicator, doneIndicator, timeDueString, this.title);
    }

    /**
     * Marks a task as done.
     */
    public void completeTask() {
        this.isDone = true;
    }

    public static TypeIndicators charToTypeEnum(char t) {
        switch (t) {
            case 'T':
                return TypeIndicators.TODO;
            case 'D':
                return TypeIndicators.DEADLINE;
            case 'E':
                return TypeIndicators.EVENT;
        }
        // If the type cannot be inferred, return a TO-DO as default.
        return TypeIndicators.TODO;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        String doneIndicator = this.isDone ? "x" : " ";
        return String.format("[%s][%s] %s", typeIndicator, doneIndicator, this.title);
    }
}
