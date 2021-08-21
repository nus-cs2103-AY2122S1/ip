package duke.data;

import duke.task.DateTimeTask;
import duke.task.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

class Calendar {

    private TreeMap<LocalDateTime, ArrayList<DateTimeTask>> calendar;
    private TreeMap<LocalDateTime, ArrayList<DateTimeTask>> calendarEvents;
    private TreeMap<LocalDateTime, ArrayList<DateTimeTask>> calendarDeadlines;

    public Calendar() {
        calendar = new TreeMap<>();
        calendarEvents = new TreeMap<>();
        calendarDeadlines = new TreeMap<>();
    }

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

    public ArrayList<DateTimeTask> getEventsAt(LocalDateTime dt) {
        ArrayList<DateTimeTask> arrayList = new ArrayList<>();
        calendarEvents.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }

    public ArrayList<DateTimeTask> getDeadlinesBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> arrayList = new ArrayList<>();
        calendarDeadlines.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }

    public ArrayList<DateTimeTask> getAllBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> arrayList = new ArrayList<>();
        calendar.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }
}
