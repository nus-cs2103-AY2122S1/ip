package duke.task;

import java.text.ParseException;

import duke.main.DukeException;
import duke.main.TaskDate;


/**
 * Represents tasks with deadline.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 1
 */
public class Deadline extends Task {
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String DEADLINE_MARKER = "D";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String DEADLINE_KEYWORD = "deadline ";
    @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
    private final String BY_CONNECTOR = "by ";
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
        int startOfTimingIndex = getStartingIndexAfter(description, BY_CONNECTOR);
        deadlineDescription = getSubString(description, startOfDescriptionIndex,
                startOfTimingIndex - BY_CONNECTOR.length());
        String descriptionDate = getSubString(description, startOfTimingIndex);
        deadlineDate = new TaskDate(descriptionDate);
        dateString = getDateString();
    }

    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param deadlineDescription description of deadline duke.task.
     * @param dateOfTask          date of the deadline duke.task.
     * @throws ParseException due to improper date format.
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
        return String.format("[%s]%s %s (by: %s)", DEADLINE_MARKER, super.toString(), deadlineDescription,
            dateString);
    }

    /**
     * Formats the duke.task in to the storage format.
     *
     * @return storage format of the duke.task.
     */
    public String formatToStore() {
        return String.format("%s | %s | %s | %s", DEADLINE_MARKER, getStatusIcon() == " " ? 1 : 0,
            deadlineDescription, dateString);
    }
    /**
     * Checks if given datetime matches the tasks date time.
     *
     * @param dateString date time (in string form) to compare with.
     * @return true if the duke.task date time matches the date time given.
     */
    @Override
    public boolean isSameDateAs(String dateString) throws DukeException {
        return this.deadlineDate.equals(dateString);
    }

    /**
     * Overrides contains in task by adding an additional check for date, if the search phrase is a date.
     *
     * @param searchPhrase the phrase or word or date of interest.
     * @return
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
