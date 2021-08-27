package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Stores list of Tasks in an array for reference.
 * @author Ruth Poh
 */
public class TaskList {
    ArrayList<Task> taskArr;
    int counter;

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
        this.counter = 0;

    }

    /**
     * Adds Todo (Task) to TaskList.
     * @param strparse Array of Strings to be parsed.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addTodo(String[] strparse) throws DukeException {
        StringBuilder taskb = new StringBuilder();
        if (strparse.length == 1) {
            throw new IncorrectInputException("todo", "using 'todo (taskw)'");
        }
        for (int i = 1; i < strparse.length; i++) {
            taskb.append(strparse[i]);
            if (i != strparse.length - 1) {
                taskb.append(" ");
            }
        }
        taskArr.add(new Todo(taskb.toString()));
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
     * @param strparse Array of Strings to be parsed.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addDeadline(String[] strparse) throws DukeException {
        try {
            if (strparse.length == 1) {
                throw new MissingInputException("deadline");
            }
            StringBuilder taskb = new StringBuilder();
            int i = 1;
            while (i < strparse.length
                    && !strparse[i].equalsIgnoreCase("/by")) {
                taskb.append(strparse[i]);
                if (i != strparse.length - 1) {
                    taskb.append(" ");
                }
                i++;
            }
            i++;
            if (taskb.toString().equals("") || i != strparse.length - 1) {
                throw new IncorrectInputException("deadline", "using 'deadline (task) /by (yyyy-mm-dd format)'");
            }
            LocalDate deadline = LocalDate.parse(strparse[i]);
            taskArr.add(new Deadline(taskb.toString(), deadline));
            counter++;
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("deadline", "using 'deadline (task) /by (yyyy-mm-dd format)'");
        }
    }

    /**
     * Adds Deadline Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     * @param by String of Date deadline task needs to be done by.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addReadDeadline(String task, int isDoneInt, String by) throws DukeException {
        taskArr.add(new Deadline(task, LocalDate.parse(by)));
        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    /**
     * Adds Event Task to TaskList.
     * @param strparse Array of Strings to be parsed.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addEvent(String[] strparse) throws DukeException {
        try {
            if (strparse.length == 1) {
                throw new MissingInputException("event");
            }
            StringBuilder taskb = new StringBuilder();
            StringBuilder atb = new StringBuilder();
            int i = 1;
            while (i < strparse.length
                    && !strparse[i].equalsIgnoreCase("/at")) {
                taskb.append(strparse[i]);
                if (i != strparse.length - 1) {
                    taskb.append(" ");
                }
                i++;
            }
            i++;
            if (taskb.toString().equals("") || i != strparse.length - 1) {
                throw new IncorrectInputException("event", "'event (event) /at (date)'");
            }
            LocalDate deadline = LocalDate.parse(strparse[i]);
            taskArr.add(new Event(taskb.toString(), deadline));
            counter++;
        } catch (DateTimeParseException e) {
            throw new IncorrectInputException("deadline", "a cowwect (yyyy-mm-dd format)'");
        }
    }

    /**
     * Adds Event Task to TaskList. Occurs when loading from file.
     * @param task String of task.
     * @param isDoneInt Whether task is done or not. 0 is not done, 1 is done.
     * @param at String of Date event occurs at.
     * @throws DukeException Occurs when anything goes wrong during method.
     */
    public void addReadEvent(String task, int isDoneInt, String at) throws DukeException {
        taskArr.add(new Event(task.toString(), LocalDate.parse(at)));
        if (isDoneInt == 1) {
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
            throw new TaskDoneError();
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
            if (taskArr.get(i) instanceof Todo) {
                strb.append("T | ");
            } else if (taskArr.get(i) instanceof Deadline) {
                strb.append("D | ");
            } else if (taskArr.get(i) instanceof Event) {
                strb.append("E | ");
            } else {
                // nothing happens because it shouldn't reach here.
            }
            if (taskArr.get(i).isDone) {
                strb.append("1 | ");
            } else {
                strb.append("0 | ");
            }
            strb.append(taskArr.get(i).getTaskStr());
            if (taskArr.get(i) instanceof Deadline) {
                strb.append(" | ");
                strb.append(taskArr.get(i).getTimeStorage());
            } else if (taskArr.get(i) instanceof Event) {
                strb.append(" | ");
                strb.append(taskArr.get(i).getTimeStorage());
            } else {
                // do nothing
            }
            strb.append('\n');
        }
        return strb.toString();
    }
}
