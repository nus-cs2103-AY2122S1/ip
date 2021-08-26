import java.util.ArrayList;
import java.util.List;

/**
 * Stores list of Tasks in an array for reference.
 */
public class TaskList {
    ArrayList<Task> taskArr;
    int counter;

    TaskList() {
        this.taskArr = new ArrayList<>(100);
        this.counter = 0;
    }

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

    public void addReadTodo(String task, int isDoneInt) throws DukeException {
        taskArr.add(new Todo(task));
        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    public void addDeadline(String[] strparse) throws DukeException {
        if (strparse.length == 1) {
            throw new MissingInputException("deadline");
        }
        StringBuilder taskb = new StringBuilder();
        StringBuilder deadlineb = new StringBuilder();
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
        while (i < strparse.length) {
            deadlineb.append(strparse[i]);
            if (i != strparse.length - 1) {
                deadlineb.append(" ");
            }
            i++;
        }
        if (taskb.toString().equals("") || deadlineb.toString().equals("")) {
            throw new IncorrectInputException("deadline", "using 'deadline (task) /by (date)'");
        }
        taskArr.add(new Deadline(taskb.toString(), deadlineb.toString()));
        counter++;
    }

    public void addReadDeadline(String task, int isDoneInt, String by) throws DukeException {
        taskArr.add(new Deadline(task, by));
        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

    public void addEvent(String[] strparse) throws DukeException {
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
        while (i < strparse.length) {
            atb.append(strparse[i]);
            if (i != strparse.length - 1) {
                atb.append(" ");
            }
            i++;
        }
        if (taskb.toString().equals("") || atb.toString().equals("")) {
            throw new IncorrectInputException("event", "'event (event) /at (date)'");
        }
        taskArr.add(new Event(taskb.toString(), atb.toString()));
        counter++;
    }

    public void addReadEvent(String task, int isDoneInt, String at) throws DukeException {
        taskArr.add(new Event(task.toString(), at));
        if (isDoneInt == 1) {
            this.markReadDone(this.counter);
        }
        counter++;
    }

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

    public void markReadDone(int i) throws DukeException {
        boolean temp = taskArr.get(i).markAsDone();
    }

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

    public int getTaskCounter() throws DukeException {
        return this.counter;
    }

    public String displayList() throws DukeException {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < this.counter; i++) {
            strb.append(i + 1).append(". ").append(this.taskArr.get(i).toString()).append('\n');
        }
        return strb.toString();
    }

    public Task getTask(int i) throws DukeException {
        return taskArr.get(counter - 1);
    }

    public String getTaskDescr(int i) throws DukeException {
        return taskArr.get(i - 1).toString();
    }

    public String lastAddedTask() throws DukeException {
        return taskArr.get(counter - 1).toString();
    }

    public String saveString() throws DukeException {
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
                strb.append(taskArr.get(i).getTime());
            } else if (taskArr.get(i) instanceof Event) {
                strb.append(" | ");
                strb.append(taskArr.get(i).getTime());
            } else {
                // do nothing
            }
            strb.append('\n');
        }

        return strb.toString();
    }
}
