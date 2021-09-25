package duke.task;

import java.text.ParseException;

import duke.main.DukeException;
import duke.main.TaskDate;

/**
 * Represents tasks with specific timing.
 *
 * @author Gordon Yit
 * @version CS2103T, Semester 2
 */
public class Event extends Task {
    public static final String EVENT_MARKER = "E";
    private static final String TASK_KEYWORD = "event ";
    private static final String AT_CONNECTOR = "at ";
    private String eventDescription;
    private TaskDate eventDate;
    private String dateString;
    /**
     * Class constructor.
     *
     * @param description consisting of task description and timing.
     */
    public Event(String description) throws DukeException {
        super();
        int startOfDescriptionIndex = getStartingIndexAfter(description, TASK_KEYWORD);
        int startOfTimeIndex = getStartingIndexAfter(description, AT_CONNECTOR);
        eventDescription = getSubString(description, startOfDescriptionIndex,
                startOfTimeIndex - AT_CONNECTOR.length());
        String descriptionDate = getSubString(description, startOfTimeIndex);
        eventDate = new TaskDate(descriptionDate);
        dateString = getDateString();
        int startOfEventTags = getStartingIndexAfter(description, TaskTag.getTagSymbol());
        String tag = getSubString(description, startOfEventTags - TaskTag.getTagSymbol().length());
        this.addTag(tag);
        assert !isDone : false;
    }
    /**
     * Class constructor for loading tasks from storage file.
     *
     * @param eventDescription description of event task.
     * @param dateOfTask date of the event task.
     * @throws ParseException due to improper date format.
     */
    public Event(String eventDescription, String dateOfTask) throws DukeException {
        super();
        this.eventDescription = eventDescription;
        eventDate = TaskDate.convertDateStringToDate(dateOfTask);
        dateString = getDateString();
    }

    private String getDateString() {
        return eventDate.toString();
    }

    /**
     * Print out the event task,
     *
     * @return string format of the event task,
     * consisting of the task marker "E", task description and duration of the event.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s %s (at: %s) %s", EVENT_MARKER, super.toString(), eventDescription,
                dateString, getTag());
    }
    /**
     * Formats the task in to the storage format.
     *
     * @return storage format of the task.
     */
    public String formatToStore() {
        return String.format("%s %s %s | %s %s", EVENT_MARKER, super.formatToStore(),
            eventDescription, dateString, getTagFormattedForStorage());
    }
    /**
     * Checks if given datetime matches the tasks date time.
     *
     * @param dateString date to compare with in string form.
     * @return true if the task date time matches the date time given.
     */
    @Override
    public boolean isSameDateAs(String dateString) throws DukeException {
        assert eventDate != null : "even date must not be unassigned";
        return eventDate.equals(dateString);
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
