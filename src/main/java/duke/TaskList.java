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
    ArrayList<Task> tasks;
    private int counter;

    /**
     * Constructor for TaskList.
     */
    TaskList() {
        this.tasks = new ArrayList<>(100);
        this.counter = 0;
    }

    /**
     * Constructor for TaskList
     * @param tasks Arraylist for Tasks. Should have 100 capacity.
     */
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.counter = tasks.size();
    }

    /**
     * Adds Todo (Task) to TaskList.
     * @param todo Todo task.
     */
    public void addTodo(Todo todo) {
        tasks.add(todo);
        counter++;
    }

    /**
     * Adds Todo Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     */
    public void addReadTodo(String task, int isDoneInt) {
        tasks.add(new Todo(task));
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
        tasks.add(deadline);
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
        if (time == null) {
            tasks.add(new Deadline(task, LocalDate.parse(date)));
        } else {
            tasks.add(new Deadline(task, LocalDate.parse(date), LocalTime.parse(time)));
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
        tasks.add(event);
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
        if (time == null) {
            tasks.add(new Event(task, LocalDate.parse(date)));
        } else {
            tasks.add(new Event(task, LocalDate.parse(date), LocalTime.parse(time)));
        }        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    /**
     * Marks task as done. Returns number of task in TaskList that is done.
     * @param strParse Array of Strings to be parsed.
     * @return Number of tasks in TaskList that is done.
     * @throws MissingInputException When there is a missing input after the keyword "done".
     * @throws MissingNoException When there is a missing number after the keyword.
     * @throws TaskDoneException When task is already marked as done.
     */
    public int markDone(String[] strParse) throws DukeException {
        if (strParse.length == 1) {
            throw new MissingInputException("done");
        }
        try {
            int taskListNo = Integer.parseInt(strParse[1]);
        } catch (NumberFormatException e) {
            throw new MissingNoException("done");
        }
        int taskListNo = Integer.parseInt(strParse[1]);
        if ((taskListNo - 1) >= counter || (taskListNo - 1) < 0) {
            throw new MissingNoException("done");
        }
        if (tasks.get(taskListNo - 1).markAsDone()) {
            return taskListNo;
        } else {
            throw new TaskDoneException();
        }
    }

    /**
     * Marks task as done. Occurs when loading from file.
     * @param taskListNo Number of task in TaskList that is done.
     */
    public void markReadDone(int taskListNo) {
        boolean temp = tasks.get(taskListNo).markAsDone();
    }

    /**
     * Removes Task from TaskList. Returns deleted Task.
     * @param strParse Array of Strings to be parsed.
     * @return Deleted Task.
     * @throws MissingInputException When there is a missing input after the keyword "delete".
     * @throws MissingNoException When there is a missing number after the keyword.
     */
    public Task delete(String[] strParse) throws DukeException {
        if (strParse.length == 1) {
            throw new MissingInputException("delete");
        }
        try {
            int taskListNo = Integer.parseInt(strParse[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MissingNoException("delete");
        }
        int taskListNo = Integer.parseInt(strParse[1]) - 1;
        if (taskListNo >= counter || taskListNo < 0) {
            throw new MissingNoException("delete");
        }
        Task t = tasks.get(taskListNo);
        counter--;
        tasks.remove(taskListNo);

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
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < this.counter; i++) {
            strBuilder.append(i + 1).append(". ").append(this.tasks.get(i).toString()).append('\n');
        }
        return strBuilder.toString();
    }

    /**
     * Gets Task from TaskList, in String form.
     * @param taskListNo Specifies which Task to take.
     * @return String of Task as specified.
     */
    public String getTaskDescr(int taskListNo) {
        return tasks.get(taskListNo - 1).toString();
    }

    /**
     * Returns last Task added to the TaskList, in String form.
     * @return Last Task added to the TaskList.
     */
    public String lastAddedTask() {
        return tasks.get(counter - 1).toString();
    }

    /**
     * Converts TaskList to String form to save in file. Different from displayList.
     * @return TaskList in String form to save in file.
     */
    public String saveAsString() {
        StringBuilder strBuilder = new StringBuilder();
        for (int i = 0; i < this.counter; i++) {
            strBuilder.append(tasks.get(i).toStorageString());
            if (i < this.counter - 1) {
                strBuilder.append('\n');
            }
        }
        return strBuilder.toString();
    }

    /**
     * Finds Tasks that has the keyword for find.
     * @param keyword keyword to find.
     * @return String of Tasks found with the keyword.
     * @throws KeywordNotFoundException When there is nothing to find.
     */
    public String find(String keyword) throws DukeException {
        boolean isFound = false;
        StringBuilder strBuilder = new StringBuilder();

        for (int i = 0; i < counter; i++) {
            Task temp = tasks.get(i);
            if (temp.getTaskString().toLowerCase().contains(keyword.toLowerCase())) {
                isFound = true;
                strBuilder.append(i + 1).append(". ").append(temp.toString());
                if (i < counter - 1) {
                    strBuilder.append('\n');
                }
            }
        }
        if (isFound) {
            return strBuilder.toString();
        } else {
            throw new KeywordNotFoundException(keyword);
        }
    }

    /**
     * Clears taskList.
     */
    public void clearTaskList() {
        this.tasks = new ArrayList<>(100);
        this.counter = 0;
    }

    /**
     * Getter method for Tasks from tasklist.
     * @param taskNo Number task to get
     * @return Task as requested
     */
    public Task getTask(int taskNo) {
        return this.tasks.get(taskNo - 1);
    }

}
