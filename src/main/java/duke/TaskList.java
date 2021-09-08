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
     */
    public void addTodo(Todo todo) {
        taskArr.add(todo);
        counter++;
    }

    /**
     * Adds Todo Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     */
    public void addReadTodo(String task, int isDoneInt) {
        taskArr.add(new Todo(task));
        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    /**
     * Adds Deadline Task to TaskList.
     * @param deadline Deadline task.
     */
    public void addDeadline(Deadline deadline) {
        taskArr.add(deadline);
        counter++;
    }

    /**
     * Adds Deadline Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     * @param date Date deadline task needs to be done by.
     * @param time Time deadline occurs by.
     */
    public void addReadDeadline(String task, int isDoneInt, String date, String time) {
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
     */
    public void addEvent(Event event) {
        taskArr.add(event);
        counter++;
    }

    /**
     * Adds Event Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     * @param date Date event occurs at.
     * @param time Time event occurs at.
     */
    public void addReadEvent(String task, int isDoneInt, String date, String time) {
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
     * @throws MissingInputException When there is a missing input after the keyword "done".
     * @throws MissingNoException When there is a missing number after the keyword.
     * @throws TaskDoneException When task is already marked as done.
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
     */
    public void markReadDone(int i) {
        boolean temp = taskArr.get(i).markAsDone();
    }

    /**
     * Removes Task from TaskList. Returns deleted Task.
     * @param strparse Array of Strings to be parsed.
     * @return Deleted Task.
     * @throws MissingInputException When there is a missing input after the keyword "delete".
     * @throws MissingNoException When there is a missing number after the keyword.
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
     */
    public int getTaskCounter() {
        return this.counter;
    }

    /**
     * Displays list of Tasks in TaskList, numbered.
     * @return List of Tasks in TaskList, numbered.
     */
    public String displayList() {
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
     */
    public Task getTask(int i) {
        return taskArr.get(counter - 1);
    }

    /**
     * Gets Task from TaskList, in String form.
     * @param i Specifies which Task to take.
     * @return String of Task as specified.
     */
    public String getTaskDescr(int i) {
        return taskArr.get(i - 1).toString();
    }

    /**
     * Returns last Task added to the TaskList, in String form.
     * @return Last Task added to the TaskList.
     */
    public String lastAddedTask() {
        return taskArr.get(counter - 1).toString();
    }

    /**
     * Converts TaskList to String form to save in file. Different from displayList.
     * @return TaskList in String form to save in file.
     */
    public String saveAsString() {
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
     * @throws KeywordNotFoundException When there is nothing to find.
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
