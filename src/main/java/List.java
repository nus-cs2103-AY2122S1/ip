import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

public class List {
    private ArrayList<Task> items;
    private TreeMap<LocalDateTime, ArrayList<DateTimeable>> calendar;
    private TreeMap<LocalDateTime, ArrayList<DateTimeable>> calendarEvents;
    private TreeMap<LocalDateTime, ArrayList<DateTimeable>> calendarDeadlines;

    public List() {
        items = new ArrayList<>();
        calendar = new TreeMap<>();
        calendarEvents = new TreeMap<>();
        calendarDeadlines = new TreeMap<>();
    }

    public void addItem(Task task) {
        if (task instanceof DateTimeable) {
            DateTimeable dt = (DateTimeable) task;
            if (!calendar.containsKey(dt.getDateTime())) {
                calendar.put(dt.getDateTime(), new ArrayList<DateTimeable>());
            }
            calendar.get(dt.getDateTime()).add(dt);
            if (task instanceof Event) {
                if (!calendarEvents.containsKey(dt.getDateTime())) {
                    calendarEvents.put(dt.getDateTime(), new ArrayList<DateTimeable>());
                }
                calendarEvents.get(dt.getDateTime()).add(dt);
            } else {
                if (!calendarDeadlines.containsKey(dt.getDateTime())) {
                    calendarDeadlines.put(dt.getDateTime(), new ArrayList<DateTimeable>());
                }
                calendarDeadlines.get(dt.getDateTime()).add(dt);
            }
        }
        items.add(task);
    }

    public String[] returnItems() {
        String[] itemList = new String[items.size() + 1];
        itemList[0] = "Here are the tasks in your list:";
        for (int i = 0; i < items.size(); i++) {
            itemList[i + 1] = (i + 1) + "." + items.get(i);
        }
        return itemList;
    }

    public String returnItemCount() {
        return "Now you have " + items.size() + " tasks in the list.";
    }

    public String markDone(int index) throws DukeException{
        if (index > items.size() || index < 1) {
            throw new DukeException("index");
        }
        Task t = items.get(index - 1);
        t.markDone();
        return t.toString();
    }

    public String removeTask(int index) throws DukeException{
        if (index > items.size() || index < 1) {
            throw new DukeException("index");
        }
        return items.remove(index - 1).toString();
    }

    public ArrayList<DateTimeable> getEventsAt(LocalDateTime dt) {
        ArrayList<DateTimeable> arrayList = new ArrayList<>();
        calendarEvents.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }

    public ArrayList<DateTimeable> getEventsBy(LocalDateTime dt) {
        ArrayList<DateTimeable> arrayList = new ArrayList<>();
        calendarDeadlines.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }

    public ArrayList<DateTimeable> getEventsAll(LocalDateTime dt) {
        ArrayList<DateTimeable> arrayList = new ArrayList<>();
        calendar.headMap(dt, true).values().forEach(arrayList::addAll);
        return arrayList;
    }
}
