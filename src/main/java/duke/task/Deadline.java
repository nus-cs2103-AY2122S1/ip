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
    private TaskTag deadlineTag;
    /**
     * Class constructor.
     *
     * @param description consisting of duke.task description and deadline date.
     */
    public Deadline(String description) throws DukeException {
        int startOfDescriptionIndex = getStartingIndexAfter(description, DEADLINE_KEYWORD);
        int startOfTimingIndex = getStartingIndexAfter(description, BY_CONNECTOR);
        deadlineDescription = getSubString(description, startOfDescriptionIndex,
                startOfTimingIndex - BY_CONNECTOR.length());
        String descriptionDate = getSubString(description, startOfTimingIndex);
        deadlineDate = new TaskDate(descriptionDate);
        dateString = getDateString();
        int startOfDeadlineTag = getStartingIndexAfter(description, TaskTag.getTagSymbol());
        String tag = getSubString(description, startOfDeadlineTag - TaskTag.getTagSymbol().length());
        deadlineTag = new TaskTag(tag);
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
                dateString, deadlineTag.getTag());
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s %s %s | %s %s", DEADLINE_MARKER, super.formatToStore(),
                deadlineDescription, dateString, deadlineTag.getTagInStoreFormat());
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
