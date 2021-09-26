package duke.task;

import duke.main.DukeException;
import duke.main.TaskDate;

/**
 * Represents tasks with deadline.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 1
 */
public class Deadline extends Task {
    public static final String DEADLINE_MARKER = "D";
    private static final String DEADLINE_KEYWORD = "deadline ";
    private static final String BY_CONNECTOR = "by ";
    private String deadlineDescription;
    private TaskDate deadlineDate;
    private String dateString;
    /**
     * Class constructor.
     *
     * @param description consisting of duke.task description and deadline date.
     */
    public Deadline(String description) throws DukeException {
        super();
        int startOfDescriptionIndex = getStartingIndexAfter(description, DEADLINE_KEYWORD);
        if (!description.contains(BY_CONNECTOR)) {
            throw new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        }
        int startOfTimeIndex = getStartingIndexAfter(description, BY_CONNECTOR);
        if (startOfTimeIndex == -1) {
            throw new DukeException(DukeException.Exceptions.StringIndexOutOfBoundsException);
        }
        deadlineDescription = getSubString(description, startOfDescriptionIndex,
                startOfTimeIndex - BY_CONNECTOR.length());
        String descriptionDate = getSubString(description, startOfTimeIndex);
        String tag = "";
        if (description.contains(TaskTag.getTagSymbol())) {
            int startOfDeadlineTagIndex = getStartingIndexAfter(description, TaskTag.getTagSymbol());
            descriptionDate = getSubString(description, startOfTimeIndex, startOfDeadlineTagIndex);
            tag = getSubString(description, startOfDeadlineTagIndex - TaskTag.getTagSymbol().length());
        }
        deadlineDate = new TaskDate(descriptionDate);
        dateString = getDateString();
        this.addTag(tag);
        assert !isDone : false;
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param deadlineDescription description of deadline duke.task.
     * @param dateOfTask          date of the deadline duke.task.
     * @throws DukeException due to improper date format.
     */
    public Deadline(String deadlineDescription, String dateOfTask) throws DukeException {
        super();
        this.deadlineDescription = deadlineDescription;
        this.deadlineDate = TaskDate.convertDateStringToDate(dateOfTask);
        dateString = getDateString();
    }

    private String getDateString() {
        return deadlineDate.toString();
    }
    /**
     * Print out the deadline duke.task,
     *
     * @return string format of the deadline duke.task,
     * consisting of the duke.task marker "D", duke.task description and deadline of the duke.task.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s (by: %s) %s", DEADLINE_MARKER, super.toString(), deadlineDescription,
                dateString, getTag());
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s %s %s | %s %s", DEADLINE_MARKER, super.formatToStore(),
                deadlineDescription, dateString, getTagFormattedForStorage());
    }
    /**
     * Checks if given datetime matches the tasks date time.
     *
     * @param dateString date time (in string form) to compare with.
     * @return true if the duke.task date time matches the date time given.
     */
    @Override
    public boolean isSameDateAs(String dateString) throws DukeException {
        assert deadlineDate != null : "deadline date must not be unassigned";
        return this.deadlineDate.equals(dateString);
    }
    /**
     * Overrides contains in task by adding an additional check for date, if the search phrase is a date.
     *
     * @param searchPhrase the phrase or word or date of interest.
     * @return true if this deadline task contains the search phrase.
     */
    @Override
    public boolean contains(String searchPhrase) {
        if (searchPhrase.contains("/")) {
            try {
                return isSameDateAs(searchPhrase);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        return toString().contains(searchPhrase);
    }
}
