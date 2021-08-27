package duke;

import java.util.ArrayList;

/**
 * Implements a TaskList object that contains an ArrayList of all the tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with an existing ArrayList.
     * @param list The ArrayList of tasks.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    /**
     * Constructs a TaskList object with a new ArrayList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the number of tasks in the list.
     * @return The integer value of the number of tasks in the list.
     */
    public int length() {
        return tasks.size();
    }

    /**
     * Gets the Task at the specific index.
     * @param index The task's index.
     * @return The Task at the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }


    /**
     * Marks the Task in the ArrayList as completed.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @throws DukeException Thrown in the event the Task cannot be accessed.
     */
    public void handleDone(String input, Storage storage, Ui ui) throws DukeException {
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

    /**
     * Adds a Todo object into the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @throws DukeException Thrown in the event the user input is wrong.
     */
    public void handleTodo(String input, Storage storage, Ui ui) throws DukeException {
        try {
            Todo todo = new Todo(input.substring(5));
            add(todo, storage, ui);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("u never say what to do?");
        }
    }


    /**
     * Adds a Deadline object into the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @throws DukeException Thrown in the event the user input is wrong.
     */
    public void handleDeadline(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int split = input.indexOf("/");
            Deadline deadline = new Deadline(input.substring(9, split - 1), input.substring(split + 4));
            add(deadline, storage, ui);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("this one by when ah? can do it liddis or not: 'deadline task /by when'");
        }
    }


    /**
     * Adds an Event object into the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @throws DukeException Thrown in the event the user input is wrong.
     */
    public void handleEvent(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int split = input.indexOf("/");
            Event event = new Event(input.substring(6, split - 1), input.substring(split + 4));
            add(event, storage, ui);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("this one when ah? can do it liddis or not: 'event task /at when'");
        }
    }


    /**
     * Deletes a Task object from the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @throws DukeException Thrown in the event the Task cannot be accessed.
     */
    public void handleDelete(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            ui.delete(tasks.get(taskNumber), tasks.size() - 1);
            tasks.remove(taskNumber);
            storage.saveTask(tasks);
        } catch(StringIndexOutOfBoundsException err) {
            throw new DukeException("what u wan delete?");
        } catch(NumberFormatException err) {
            throw new DukeException("can enter number only pls? dun anyhow");
        } catch(ArrayIndexOutOfBoundsException err) {
            throw new DukeException("you where got so many tasks?");
        }
    }
}
