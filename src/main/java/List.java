import java.util.ArrayList;

public class List {
    private ArrayList<Task> items;
    private ArrayList<String> dataStorage;

    public List() {
        items = new ArrayList<>();
        dataStorage = new ArrayList<>();
    }

    public void addItem(Task task) {
        items.add(task);
        String data;
        data = task.getCode() + "|" + task.getStatus() + "|" + task.getDescription();
        if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            data = data + "|" + d.getTimeAttr();
        } else if (task instanceof Event) {
            Event e = (Event) task;
            data = data + "|" + e.getTimeAttr();
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

    public ArrayList<String> getDataStorage() {
        return dataStorage;
    }
}
