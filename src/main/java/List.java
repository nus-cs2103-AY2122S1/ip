import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.TreeMap;

public class List {
    private ArrayList<Task> items;
    private TreeMap<LocalDateTime, ArrayList<DateTimeable>> calendar;
    private TreeMap<LocalDateTime, ArrayList<DateTimeable>> calendarEvents;
    private TreeMap<LocalDateTime, ArrayList<DateTimeable>> calendarDeadlines;
    private ArrayList<String> dataStorage;

    public List() {
        items = new ArrayList<>();
        calendar = new TreeMap<>();
        calendarEvents = new TreeMap<>();
        calendarDeadlines = new TreeMap<>();
        dataStorage = new ArrayList<>();
    }

    public void addItem(Task task) {
        items.add(task);
        String data;
        data = task.getCode() + "|" + task.getStatus() + "|" + task.getDescription();
        if (task instanceof DateTimeable) {
            DateTimeable dt = (DateTimeable) task;
            data = data + "|" + dt.getDateTime().format(DateTimeFormatter.ISO_DATE_TIME);
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
        dataStorage.add(data);
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
            throw new DukeException(DukeException.Type.INDEX);
        }
        Task t = items.get(index - 1);
        t.markDone();
        String data = dataStorage.get(index - 1);
        dataStorage.set(index - 1, data.substring(0, 2) + 'X' + data.substring(3));
        return t.toString();
    }

    public String removeTask(int index) throws DukeException{
        if (index > items.size() || index < 1) {
            throw new DukeException(DukeException.Type.INDEX);
        }
        dataStorage.remove(index - 1);
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

    public ArrayList<String> getDataStorage() {
        return dataStorage;
    }
}
