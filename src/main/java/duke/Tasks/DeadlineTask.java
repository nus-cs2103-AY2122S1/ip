package duke.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This class encapsulates a Task element of Deadline type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */

public class DeadlineTask extends BaseTask {


    /** By default the 'by when' data will be stored in String form unless it is a date. */
    private String byWhenStr;

    /** If 'by when' data is given in date form, it will be stored here. */
    private LocalDate byWhenDate;

    /** Activates or deactivates Debug mode. */
    private boolean debugMode = true;



    /**
     * Used for creating a new Deadline Task.
     *
     * @param taskName the name or description of the task.
     * @param byWhen describes when the task should be completed by.
     */
    public DeadlineTask(String taskName, String byWhen) {
        super(taskName, false);
        this.parseByWhenInput(byWhen);
    }

    /**
     * Used for creating a new Deadline Task with ability to set all attributes.
     *
     * @param taskName the name or description of the task.
     * @param byWhen describes when the task should be completed by.
     * @param isCompleted true if task was already complete, false if incomplete.
     */
    public DeadlineTask(String taskName, String byWhen, boolean isCompleted) {
        super(taskName, isCompleted);
        this.parseByWhenInput(byWhen);
    }

    /**
     * Stores the byWhen data of this Deadline Task, either in String form or LocalDate form.
     * @param byWhen the time the task is upposed to be done by.
     */
    private void parseByWhenInput(String byWhen) {
        try {
            DateTimeFormatter usualStyleFormat = DateTimeFormatter.ofPattern("d/M/yy");
            LocalDate byDate = LocalDate.parse(byWhen, usualStyleFormat);

            this.byWhenDate = byDate;
            this.byWhenStr = null;

        } catch (DateTimeParseException e) {
            debugPrint("Date Time Parse Exception!");

            try {
                // Try a different format instead. (4 digits for year)
                DateTimeFormatter usualStyleFormat = DateTimeFormatter.ofPattern("d/M/yyyy");
                LocalDate byDate = LocalDate.parse(byWhen, usualStyleFormat);

                this.byWhenDate = byDate;
                this.byWhenStr = null;
            } catch (DateTimeParseException e2) {
                debugPrint("DTE 2");

                try {
                    // Try a third format since the last one failed. (And this is the format stored in save file)
                    DateTimeFormatter longDateFormat = DateTimeFormatter.ofPattern("d MMM uuuu");
                    LocalDate byDate = LocalDate.parse(byWhen, longDateFormat);

                    this.byWhenDate = byDate;
                    this.byWhenStr = null;
                } catch (DateTimeParseException e3) {
                    debugPrint("DTE 3");
                    this.byWhenStr = byWhen;
                    this.byWhenDate = null;
                }
            }
        }
    }

    /**
     * Prints String if debug mode is activated.
     * @param str the String to print.
     */
    private void debugPrint(String str) {
        if (debugMode) {
            System.out.println(str);
        }
    }

    /**
     * Gets the String representation of the by when.
     * @return String representation of the LocalDate or just the plain String, whichever is relevant.
     */
    private String getByWhenStr() {
        if (this.byWhenDate == null) {
            return this.byWhenStr;
        } else {
            DateTimeFormatter longDateFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");

            return this.byWhenDate.format(longDateFormat);
        }
    }

    /**
     * Gets the type of the Task in enum form.
     *
     * @return the enum representing the type of the current task.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns a string containing the letter representing the task type in square brackets.
     *
     * @return a string containing [X] where X is the letter representing the task type.
     */
    @Override
    public String getTaskTypeStringHeader() {
        return "[D]";
    }

    /**
     * Returns a string containing the task's name and additional descriptions if applicable.
     *
     * @return the string describing the task.
     */
    @Override
    public String getTaskDescription() {
        return this.getTaskName() + "(by: " + this.getByWhenStr() + ")";
    }


    /**
     * Returns the extra info this task contains, which is the by when data.
     *
     * @return the String containing the extra info of this task if any. Returns null if there is none.
     */
    @Override
    public String getTaskExtraInfo() {
        return this.getByWhenStr();
    }

    
}
