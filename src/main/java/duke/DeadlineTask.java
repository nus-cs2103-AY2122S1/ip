package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *  This class encapsulates a Task element of Deadline type in Duke's TDList, and features
 *  various methods to manipulate the Task.
 */
public class DeadlineTask extends BaseTask {


    /** 'by when' data is given in date form and stored here. */
    private LocalDate byWhenDate;



    /**
     * Creates a new Deadline Task.
     *
     * @param taskName the name or description of the task.
     * @param byWhen describes when the task should be completed by.
     *               (In the format: D/M/YY, DD/MM/YYYY or DD Month YYYY).
     * @throws DukeExceptionBase when the byWhen field is not given in an acceptable format.
     */
    public DeadlineTask(String taskName, String byWhen) throws DukeExceptionBase {
        super(taskName, false);
        this.parseByWhenInput(byWhen);
    }

    /**
     * Creates a new Deadline Task with ability to set all attributes.
     *
     * @param taskName the name or description of the task.
     * @param byWhen describes when the task should be completed by.
     * @param isCompleted true if task was already complete, false if incomplete.
     * @throws DukeExceptionBase when the byWhen field is not given in an acceptable format.
     */
    public DeadlineTask(String taskName, String byWhen, boolean isCompleted) throws DukeExceptionBase {
        super(taskName, isCompleted);
        this.parseByWhenInput(byWhen);
    }

    /**
     * Stores the byWhen data of this Deadline Task, in LocalDate form.
     *
     * @param byWhen the time the task is upposed to be done by.
     * @throws DukeExceptionBase when the byWhen field is not given in an acceptable format.
     */
    private void parseByWhenInput(String byWhen) throws DukeExceptionBase {
        if (byWhen.length() <= 5) {
            throw new DukeExceptionBase("DeadlineTask must contain a Date in 'D/M/YY',"
                    + " 'DD/MM/YYYY' or 'DD Month YYYY' formats.");
        }
        // Check if year is in YY or YYYY form
        String last4Char = byWhen.substring(byWhen.length() - 4);
        boolean hasSlash = last4Char.contains("/");
        String first3Char = byWhen.substring(0, 3);
        boolean hasSpaceAtFront = first3Char.contains(" ");
        DateTimeFormatter formatterToUse = null;

        if (hasSlash) {
            formatterToUse = DateTimeFormatter.ofPattern("d/M/yy");
        } else if (hasSpaceAtFront) {
            formatterToUse = DateTimeFormatter.ofPattern("d MMMM yyyy");
        } else {
            formatterToUse = DateTimeFormatter.ofPattern("d/M/yyyy");
        }

        // Tries parsing the date string.
        try {
            LocalDate byDate = LocalDate.parse(byWhen, formatterToUse);
            this.byWhenDate = byDate;
        } catch (DateTimeParseException e) {
            throw new DukeExceptionBase("Deadline Date does not follow the format required. "
                    + "DeadlineTask must contain a Date in 'D/M/YY', 'DD/MM/YYYY' or 'DD Month YYYY' formats");
        }
    }


    /**
     * Gets the String representation of the by when.
     *
     * @return String representation of the LocalDate or an empty String if the Date field is empty.
     */
    private String getByWhenStr() {
        if (this.byWhenDate == null) {
            return "";
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
