package duke.data;

import duke.DukeException;
import duke.task.*;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    private ArrayList<Task> items;
    private Calendar calendar;

    public TaskList() {
        this.items = new ArrayList<>();
        this.calendar = new Calendar();
    }
    public TaskList(ArrayList<String[]> taskArrayList) throws DukeException, FileNotFoundException {
        this.items = new ArrayList<>();
        this.calendar = new Calendar();
        for (String[] taskElements : taskArrayList) {
            Task t = null;
            switch (taskElements[0]) {
            case "T":
                t = new Todo(taskElements[2]);
                break;
            case "E":
                t = new Event(Arrays.copyOfRange(taskElements, 2, taskElements.length));
                calendar.add((DateTimeTask) t);
                break;
            case "D":
                t = new Deadline(Arrays.copyOfRange(taskElements, 2, taskElements.length));
                calendar.add((DateTimeTask) t);
                break;
            }
            if (t != null) {
                if (taskElements[1].equals("X")) {
                    t.markDone();
                }
                this.items.add(t);
            }
        }
    }

    public void addItem(Task task, Storage storage) {
        this.items.add(task);
        if (task instanceof DateTimeTask) {
            DateTimeTask dt = (DateTimeTask) task;
            calendar.add(dt);
            storage.saveTask(task.getCode(), task.getStatus(), task.getDescription(), dt.getDateTime());
        } else {
            storage.saveTask(task.getCode(), task.getStatus(), task.getDescription());
        }
    }

    public String[] returnItems() {
        String[] itemList = new String[this.items.size() + 1];
        itemList[0] = "Here are the tasks in your list:";
        for (int i = 0; i < this.items.size(); i++) {
            itemList[i + 1] = (i + 1) + "." + this.items.get(i);
        }
        return itemList;
    }

    public String returnItemCount(int offset) {
        return "Now you have " + (this.items.size() - offset) + " tasks in the list.";
    }

    public Task markDone(int index, Storage storage) throws DukeException{
        if (index > this.items.size() || index < 1) {
            throw new DukeException(DukeException.Type.INDEX);
        }
        Task t = this.items.get(index - 1);
        t.markDone();
        storage.saveTaskDone(index);
        return t;
    }

    public String returnTask(int index) {
        return this.items.get(index - 1).toString();
    }

    public String returnLastTask() {
        return this.returnTask(items.size());
    }

    public Task removeTask(int index, Storage storage) throws DukeException{
        if (index > this.items.size() || index < 1) {
            throw new DukeException(DukeException.Type.INDEX);
        }
        storage.removeTask(index - 1);
        return items.remove(index - 1);
    }

    public String[] getEventsAt(LocalDateTime dt) {
        ArrayList<DateTimeTask> tasks = calendar.getEventsAt(dt);
        String[] strArr = new String[tasks.size() + 1];
        strArr[0] = "Here are the Events happening before "
                + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
        int count = 1;
        for (DateTimeTask dtTask : tasks) {
            strArr[count++] = dtTask.toString();
        }
        return strArr;
    }

    public String[] getDeadlinesBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> tasks = calendar.getDeadlinesBy(dt);
        String[] strArr = new String[tasks.size() + 1];
        strArr[0] = "Here are the Deadlines to be completed by "
                + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
        int count = 1;
        for (DateTimeTask dtTask : tasks) {
            strArr[count++] = dtTask.toString();
        }
        return strArr;
    }

    public String[] getAllBy(LocalDateTime dt) {
        ArrayList<DateTimeTask> tasks = calendar.getAllBy(dt);
        String[] strArr = new String[tasks.size() + 1];
        strArr[0] = "Here are the timed tasks occurring before "
                + dt.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")) + ":";
        int count = 1;
        for (DateTimeTask dtTask : tasks) {
            strArr[count++] = dtTask.toString();
        }
        return strArr;
    }

    public String[] returnFoundItem(String toFind) {
        ArrayList<String > strArr = new ArrayList<>();
        strArr.add("Here are the matching tasks in your list:");
        int count = 1;
        for (Task t : items) {
            if (t.getDescription().contains(toFind)) {
                strArr.add(count++ + "." + t);
            }
        }
        return strArr.toArray(new String[strArr.size()]);
    }
}
