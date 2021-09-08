package duke;

import duke.exceptions.*;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Stores list of Tasks in an array for reference.
 * @author Ruth Poh
 */
public class TaskList {
    ArrayList<Task> taskArr;
    private int counter;

    /**
     * Constructor for TaskList.
     */
    TaskList() {
        this.taskArr = new ArrayList<>(100);
        this.counter = 0;
    }

    /**
     * Constructor for TaskList
     * @param taskArr Arraylist for Tasks. Should have 100 capacity.
     */
    TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
        this.counter = taskArr.size();

    }

    /**
     * Adds Todo (Task) to TaskList.
     * @param todo Todo task.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addTodo(Todo todo) throws DukeException {
        taskArr.add(todo);
        counter++;
    }

    /**
     * Adds Todo Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addReadTodo(String task, int isDoneInt) throws DukeException {
        taskArr.add(new Todo(task));
        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    /**
     * Adds Deadline Task to TaskList.
     * @param deadline Deadline task.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addDeadline(Deadline deadline) throws DukeException {
        taskArr.add(deadline);
        counter++;
    }

    /**
     * Adds Deadline Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     * @param date Date deadline task needs to be done by.
     * @param time Time deadline occurs by.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addReadDeadline(String task, int isDoneInt, String date, String time) throws DukeException {
        if (time != null) {
            taskArr.add(new Deadline(task, LocalDate.parse(date)));
        } else {
            taskArr.add(new Deadline(task, LocalDate.parse(date), LocalTime.parse(time)));
        }
        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    /**
     * Adds Event Task to TaskList.
     * @param event Event task.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addEvent(Event event) throws DukeException {
        taskArr.add(event);
        counter++;
    }

    /**
     * Adds Event Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     * @param date Date event occurs at.
     * @param time Time event occurs at.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addReadEvent(String task, int isDoneInt, String date, String time) throws DukeException {
        if (time != null) {
            taskArr.add(new Event(task, LocalDate.parse(date)));
        } else {
            taskArr.add(new Event(task, LocalDate.parse(date), LocalTime.parse(time)));
        }        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    /**
     * Marks task as done. Returns number of task in TaskList that is done.
     * @param strparse Array of Strings to be parsed.
     * @return Number of tasks in TaskList that is done.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public int markDone(String[] strparse) throws DukeException {
        if (strparse.length == 1) {
            throw new MissingInputException("done");
        }
        try {
            int i = Integer.parseInt(strparse[1]);
        } catch (NumberFormatException e) {
            throw new MissingNoException("done");
        }
        int i = Integer.parseInt(strparse[1]);
        if ((i - 1) >= counter || (i - 1) < 0) {
            throw new MissingNoException("done");
        }
        if (taskArr.get(i - 1).markAsDone()) {
            return i;
        } else {
            throw new TaskDoneException();
        }
    }

    /**
     * Marks task as done. Occurs when loading from file.
     * @param i Number of task in TaskList that is done.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void markReadDone(int i) throws DukeException {
        boolean temp = taskArr.get(i).markAsDone();
    }

    /**
     * Removes Task from TaskList. Returns deleted Task.
     * @param strparse Array of Strings to be parsed.
     * @return Deleted Task.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public Task delete(String[] strparse) throws DukeException {
        if (strparse.length == 1) {
            throw new MissingInputException("delete");
        }
        try {
            int i = Integer.parseInt(strparse[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MissingNoException("delete");
        }
        int i = Integer.parseInt(strparse[1]) - 1;
        if (i >= counter || i < 0) {
            throw new MissingNoException("delete");
        }
        Task t = taskArr.get(i);
        counter--;
        taskArr.remove(i);

        return (t);
    }

    /**
     * Gets current number of tasks in TaskList - 1 (because it is counter).
     * @return Current number of tasks in TaskList - 1
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public int getTaskCounter() throws DukeException {
        return this.counter;
    }

    /**
     * Displays list of Tasks in TaskList, numbered.
     * @return List of Tasks in TaskList, numbered.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public String displayList() throws DukeException {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < this.counter; i++) {
            strb.append(i + 1).append(". ").append(this.taskArr.get(i).toString()).append('\n');
        }
        return strb.toString();
    }

    /**
     * Gets Task from TaskList.
     * @param i Specifies which Task to take.
     * @return Task as specified.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public Task getTask(int i) throws DukeException {
        return taskArr.get(counter - 1);
    }

    /**
     * Gets Task from TaskList, in String form.
     * @param i Specifies which Task to take.
     * @return String of Task as specified.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public String getTaskDescr(int i) throws DukeException {
        return taskArr.get(i - 1).toString();
    }

    /**
     * Returns last Task added to the TaskList, in String form.
     * @return Last Task added to the TaskList.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public String lastAddedTask() throws DukeException {
        return taskArr.get(counter - 1).toString();
    }

    /**
     * Converts TaskList to String form to save in file. Different from displayList.
     * @return TaskList in String form to save in file.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public String saveAsString() throws DukeException {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < this.counter; i++) {
            strb.append(taskArr.get(i).toStorageString());
            if (i < this.counter - 1) {
                strb.append('\n');
            }
        }
        return strb.toString();
    }

    /**
     * Finds Tasks that has the keyword for find.
     * @param keyword keyword to find.
     * @return String of Tasks found with the keyword.
     */
    public String find(String keyword) throws DukeException {
        boolean isFound = false;
        StringBuilder strb = new StringBuilder();

        for (int i = 0; i < counter; i++) {
            Task temp = taskArr.get(i);
            if (temp.getTaskString().toLowerCase().contains(keyword.toLowerCase())) {
                isFound = true;
                strb.append(i).append(". ").append(temp.toString());
                if (i < counter - 1) {
                    strb.append('\n');
                }
            }
        }
        if (isFound) {
            return strb.toString();
        } else {
            throw new KeywordNotFoundException(keyword);
        }
    }

}
