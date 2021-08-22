import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

/**
 * Event class, subclass of Task.
 * Encapsulates a Task with a start/end time
 */
public class Event extends Task {

    //The start time of the Event
    protected String dateTime;
    private final LocalDate date;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private static final HashMap<LocalDate, ArrayList<Event>> eventMap = new HashMap<>();

    public Event(String description, String dateTime, boolean isDone) {
        super(description.trim(), isDone);
        this.dateTime = (dateTime = dateTime.trim());
        String[] splitByWhiteSpace = dateTime.split(" ");
        this.date = parseDate(splitByWhiteSpace[0]);
        this.startTime = parseTime(splitByWhiteSpace[1]);
        this.endTime = parseTime(splitByWhiteSpace[2]);
        addEvent(this);
    }

    private void addEvent(Event event) {
        Optional<ArrayList<Event>> current = Optional.ofNullable(eventMap.get(date));
        if (current.isPresent()) {
            current.get().add(event);
        } else {
            ArrayList<Event> firstList = new ArrayList<>();
            firstList.add(event);
            eventMap.put(date, firstList);
        }
    }

    public static String eventsOnDate(String date) {
        LocalDate eventDate = Event.parseDate(date);
        int count = 1;
        Optional<ArrayList<Event>> current = Optional.ofNullable(eventMap.get(eventDate));
        if (current.isPresent()) {
            StringBuilder result = new StringBuilder();
            for (Event e: current.get()) {
                result.append("\n").append(count++).append(". ").append(e);
            }
            return result.toString();
        } else {
            return "No events on this day!";
        }
    }

    @Override
    public String strForSaving() {
        return "E|" + this.getStatusIcon() + "|" + this.description + "|" + this.dateTime;
    }

    private LocalTime parseTime(String time)  {
        String formattedTime = time.indexOf(":") > 0 ? time
                : time.substring(0, 2) + ":" + time.substring(2);
        return LocalTime.parse(formattedTime);
    }

    private static LocalDate parseDate(String date) {
        String[] ddMMYY = date.split("/");
        if (ddMMYY[0].length() == 1) { //if user types 2/12/2019 -> 02/12/2019
            ddMMYY[0] = "0" + ddMMYY[0];
        }
        return LocalDate.parse(ddMMYY[2] + "-" + ddMMYY[1] + "-" + ddMMYY[0]);
    }

    /**
     * Overridden toString method for the Event class
     * @return String representation of the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at " + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                .format(date) + " " + this.startTime + " to " + this.endTime + ")";
    }
}
