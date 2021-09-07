package duke;

import java.util.ArrayList;

/**
 * Implements a TaskList object that contains an ArrayList of all the tasks.
 */
@SuppressWarnings("checkstyle:Regexp")
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
        assert !tasks.isEmpty();
        return tasks.get(index);
    }


    /**
     * Marks the Task in the ArrayList as completed.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @return A string representing the task completed.
     * @throws DukeException Thrown in the event the Task cannot be accessed.
     */
    public String handleDone(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(5));
            Task currTask = tasks.get(taskNumber - 1);
            currTask.markAsDone();
            storage.saveTask(tasks);
            return ui.done(currTask);
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("can type properly? liddis: 'done taskNumber'");
        } catch (NumberFormatException err) {
            throw new DukeException("can enter number only pls? dun anyhow");
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("you where got so many tasks?");
        }
    }

    private String add(Task task, Storage storage, Ui ui) {
        tasks.add(task);
        storage.saveTask(tasks);
        return ui.add(task, tasks.size());
    }

    /**
     * Adds a Todo object into the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @return A string representing the Todo added.
     * @throws DukeException Thrown in the event the user input is wrong.
     */
    public String handleTodo(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int tagIndex = locateTag(input);
            Todo todo = tagIndex == -1
                    ? new Todo(input.substring(5))
                    : new Todo(input.substring(5, tagIndex - 1), input.substring(tagIndex));
            return add(todo, storage, ui);
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("u never say what to do?");
        }
    }

    /**
     * Adds a Deadline object into the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @return A string representing the Deadline added.
     * @throws DukeException Thrown in the event the user input is wrong.
     */
    public String handleDeadline(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int split = input.indexOf("/");
            int tagIndex = locateTag(input);
            Deadline deadline = tagIndex == -1
                    ? new Deadline(input.substring(9, split - 1), input.substring(split + 4))
                    : new Deadline(input.substring(9, split - 1), input.substring(split + 4, tagIndex - 1),
                    input.substring(tagIndex));
            return add(deadline, storage, ui);
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("this one by when ah? can do it liddis or not: 'deadline task /by when'");
        }
    }

    /**
     * Adds an Event object into the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @return A string representing the Event added.
     * @throws DukeException Thrown in the event the user input is wrong.
     */
    public String handleEvent(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int split = input.indexOf("/");
            int tagIndex = locateTag(input);
            Event event = tagIndex == -1
                    ? new Event(input.substring(6, split - 1), input.substring(split + 4))
                    : new Event(input.substring(6, split - 1), input.substring(split + 4, tagIndex - 1),
                    input.substring(tagIndex));
            return add(event, storage, ui);
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("this one when ah? can do it liddis or not: 'event task /at when'");
        }
    }

    /**
     * Deletes a Task object from the ArrayList.
     * @param input The user input.
     * @param storage The Storage object.
     * @param ui The Ui object.
     * @return A string displaying the task deleted.
     * @throws DukeException Thrown in the event the Task cannot be accessed.
     */
    public String handleDelete(String input, Storage storage, Ui ui) throws DukeException {
        try {
            int taskNumber = Integer.parseInt(input.substring(7)) - 1;
            Task deletedTask = tasks.get(taskNumber);
            tasks.remove(taskNumber);
            storage.saveTask(tasks);
            return ui.delete(deletedTask, tasks.size());
        } catch (StringIndexOutOfBoundsException err) {
            throw new DukeException("what u wan delete?");
        } catch (NumberFormatException err) {
            throw new DukeException("can enter number only pls? dun anyhow");
        } catch (ArrayIndexOutOfBoundsException err) {
            throw new DukeException("you where got so many tasks?");
        }
    }

    /**
     * Finds the Tasks that matches the input from the ArrayList.
     * @param input The user input.
     * @param ui The Ui object.
     * @return A string displaying the matching list.
     */
    public String handleFind(String input, Ui ui) {
        String keyword = input.substring(5);
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword) || task.getTag().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return ui.displayMatchingList(new TaskList(matchingTasks));
    }

    private int locateTag(String input) {
        return input.indexOf('#');
    }
}
