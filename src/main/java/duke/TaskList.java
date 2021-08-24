package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.stream.IntStream;

import static duke.Ui.dukePrint;

public class TaskList {

    private ArrayList<Task> list;
    private Storage storage;

    public TaskList(ArrayList<Task> list, Storage storage) {
        this.list = list;
        this.storage = storage;
    }

    public TaskList(Storage storage) {
        this.list = new ArrayList<>();
        this.storage = storage;
    }



    public void delete(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= list.size()) {
                Task t =list.remove(i-1);
                storage.saveFile(list);
                dukePrint("Got it. I've removed this task:\n" + t + "\n" + "Now you have " +
                        list.size() + " task" + (list.size() < 2 ? " " : "s ") + "in the list.");
            } else {
                throw new DukeException("No such task found in list.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(str + " cannot be converted to a number.");
        }
    }

    public void done(String str) throws DukeException {
        try {
            int i = Integer.parseInt(str);

            if (i > 0 && i <= list.size()) {
                Task t = list.get(i - 1);
                t.markDone();
                dukePrint("Nice! I've marked this task as done:\n" + t);
                storage.saveFile(list);
            } else {
                throw new DukeException("No such task found in list.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException(str + " cannot be converted to a number.");
        }
    }

    public void addEvent(String str) throws DukeException {
        if (str.contains("/at ")) {
            String[] arr = str.split("/at ", 2);
            Task t = new Event(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }

    public void addDeadline(String str) throws DukeException {
        if (str.contains("/by ")) {
            String[] arr = str.split("/by ", 2);
            Task t = new Deadline(arr[0], arr[1]);
            addTask(t);
        } else {
            throw new DukeException("Date cannot be empty!");
        }
    }


    public void addToDo(String str) throws DukeException {
        Task t = new ToDo(str);
        addTask(t);
    }

    private void addTask(Task t) throws DukeException {
        list.add(t);
        dukePrint("Got it. I've added this task:\n" + t + "\n" + "Now you have " +
                list.size() + " task" + (list.size() < 2 ? " " : "s ") + "in the list.");
        storage.saveFile(list);
    }

    public void displayList() {
        if (list.size() == 0) {
            dukePrint("list empty");
            return;
        }
        dukePrint("Here are the tasks in your list:\n" +
                IntStream.range(0, list.size() ).mapToObj((i)-> Integer.toString(i + 1) + ". " + list.get(i).toString()).reduce("",(str1, str2)->str1 + str2+"\n"));


    }
}
