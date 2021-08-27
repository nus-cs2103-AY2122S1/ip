package duke.data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import duke.task.DateTimeTask;
import duke.task.Event;

class Calendar {

    /** Tree of all timed tasks */
    private TreeMap<LocalDateTime, ArrayList<DateTimeTask>> calendar;
    /** Tree of all Events */
    private TreeMap<LocalDateTime, ArrayList<DateTimeTask>> calendarEvents;
    /** Tree of all Deadlines */
    private TreeMap<LocalDateTime, ArrayList<DateTimeTask>> calendarDeadlines;

    /**
     * Returns a new Calendar object with its different trees initialized.
     */
    public Calendar() {
        calendar = new TreeMap<>();
        calendarEvents = new TreeMap<>();
        calendarDeadlines = new TreeMap<>();
    }

    /**
     * Adds a timed task to the main tree and its respective tree.
     * If task is an Event, add it to the Event tree.
     * If task is a Deadline, add it to the Deadline tree.
     *
     * @param dtTask Timed task to be added to Calendar.
     */
    public void add(DateTimeTask dtTask) {
        if (!calendar.containsKey(dtTask.getDateTime())) {
            calendar.put(dtTask.getDateTime(), new ArrayList<>());
        }
        calendar.get(dtTask.getDateTime()).add(dtTask);
        if (dtTask instanceof Event) {
            if (!calendarEvents.containsKey(dtTask.getDateTime())) {
                calendarEvents.put(dtTask.getDateTime(), new ArrayList<>());
            }
            calendarEvents.get(dtTask.getDateTime()).add(dtTask);
        } else {
            if (!calendarDeadlines.containsKey(dtTask.getDateTime())) {
                calendarDeadlines.put(dtTask.getDateTime(), new ArrayList<>());
            }
            calendarDeadlines.get(dtTask.getDateTime()).add(dtTask);
        }
    }

    /**
     * Returns an ArrayList of all Events occurring before or at the given date and time.
     *
     * @param dt Maximum date and time of Events.
     * @return ArrayList of Events.
     */
    public ArrayList<DateTimeTask> getEventsAt(LocalDateTime dt) {
        ArrayList<DateTimeTask> arrayList = new ArrayList<>();
        calendarEvents.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }

    /**
     * Returns an ArrayList of all Deadlines due before or at the given date and time.
     *
     * @param dt Maximum date and time of Deadlines.
     * @return ArrayList of Deadlines.
     */
    public ArrayList<DateTimeTask> getDeadlinesBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> arrayList = new ArrayList<>();
        calendarDeadlines.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }

    /**
     * Returns an ArrayList of all Events and Deadlines occurring before or at the given date and time.
     *
     * @param dt Maximum date and time of Events and Deadlines.
     * @return ArrayList of Events and Deadlines.
     */
    public ArrayList<DateTimeTask> getAllBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> arrayList = new ArrayList<>();
        calendar.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }
}
