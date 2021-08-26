package duke;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public int length() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void done(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(5));
            Task currTask = tasks.get(taskNumber - 1);
            currTask.markAsDone();
            ui.done(currTask);
            storage.saveTask(tasks);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("can type properly? liddis: 'done taskNumber'");
        } catch(NumberFormatException err) {
            throw new DukeException("can enter number only pls? dun anyhow");
        } catch(ArrayIndexOutOfBoundsException err) {
            throw new DukeException("you where got so many tasks?");
        }
    }

    private void add(Task task, Storage storage, Ui ui) {
        tasks.add(task);
        storage.saveTask(tasks);
        ui.add(task, tasks.size());
    }

    public void todo(String input, Storage storage, Ui ui) throws DukeException {
        try {
            Todo todo = new Todo(input.substring(5));
            add(todo, storage, ui);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("u never say what to do?");
        }
    }

    public void deadline(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int split = input.indexOf("/");
            Deadline deadline = new Deadline(input.substring(9, split - 1), input.substring(split + 4));
            add(deadline, storage, ui);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("this one by when ah? can do it liddis or not: 'deadline task /by when'");
        }
    }

    public void event(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int split = input.indexOf("/");
            Event event = new Event(input.substring(6, split - 1), input.substring(split + 4));
            add(event, storage, ui);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("this one when ah? can do it liddis or not: 'event task /at when'");
        }
    }

    public void delete(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            ui.delete(tasks.get(taskNumber), tasks.size() - 1);
            tasks.remove(taskNumber);
            storage.saveTask(tasks);
            System.out.println(taskNumber);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("what u wan delete?");
        } catch(NumberFormatException err) {
            throw new DukeException("can enter number only pls? dun anyhow");
        } catch(ArrayIndexOutOfBoundsException err) {
            throw new DukeException("you where got so many tasks?");
        }
    }
}
