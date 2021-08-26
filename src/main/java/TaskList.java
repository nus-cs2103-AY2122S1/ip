import java.util.ArrayList;
import java.util.List;

/**
 * Stores list of Tasks in an array for reference.
 */
public class TaskList {
    ArrayList<Task> taskarr;
    int counter;

    TaskList() {
        this.taskarr = new ArrayList<>(100);
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
        taskarr.add(new Todo(taskb.toString()));
        counter++;
    }

    public void addReadTodo(String[] strparse) throws DukeException {
        StringBuilder taskb = new StringBuilder();
        if (strparse.length == 1) {
            throw new IncorrectInputException("todo", "using 'todo (taskw)'");
        }
        for (int i = 0; i < strparse.length; i++) {
            taskb.append(strparse[i]);
            if (i != strparse.length - 1) {
                taskb.append(" ");
            }
        }
        taskarr.add(new Todo(taskb.toString()));
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
        taskarr.add(new Deadline(taskb.toString(), deadlineb.toString()));
        counter++;
    }

    public void addReadDeadline(String[] strparse) throws DukeException {
        if (strparse.length == 1) {
            throw new MissingInputException("deadline");
        }
        StringBuilder taskb = new StringBuilder();
        StringBuilder deadlineb = new StringBuilder();
        int i = 0;
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
        taskarr.add(new Deadline(taskb.toString(), deadlineb.toString()));
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
        taskarr.add(new Event(taskb.toString(), atb.toString()));
        counter++;
    }

    public void addReadEvent(String[] strparse) throws DukeException {
        if (strparse.length == 1) {
            throw new MissingInputException("event");
        }
        StringBuilder taskb = new StringBuilder();
        StringBuilder atb = new StringBuilder();
        int i = 0;
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
        taskarr.add(new Event(taskb.toString(), atb.toString()));
        counter++;
    }

    public int markDone(String[] strparse) throws DukeException {
        if (strparse.length == 1) {
            throw new MissingInputException("done");
        }
        try {
            int i = Integer.parseInt(strparse[1]) - 1;
        } catch (NumberFormatException e) {
            throw new MissingNoException("done");
        }
        int i = Integer.parseInt(strparse[1]) - 1;
        if (i >= counter || i < 0) {
            throw new MissingNoException("done");
        }
        if (taskarr.get(i).markAsDone()) {
            return i;
        } else {
            throw new TaskDoneError();
        }
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
        Task t = taskarr.get(i);
        counter--;
        taskarr.remove(i);

        return (t);
    }

    public int getTaskCounter() throws DukeException {
        return this.counter;
    }

    public String displayList() throws DukeException {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < this.counter; i++) {
            strb.append(i + 1).append(". ").append(this.taskarr.get(i).toString()).append('\n');
        }
        return strb.toString();
    }

    public String getTask(int i) throws DukeException {
        return (taskarr.get(counter - 1).toString());
    }

    public String lastAddedTask() throws DukeException {
        return this.getTask(this.counter - 1);
    }

    public String saveString() throws DukeException {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < this.counter; i++) {
            if (taskarr.get(i) instanceof Todo) {
                strb.append("T | ");
            } else if (taskarr.get(i) instanceof Deadline) {
                strb.append("D | ");
            } else if (taskarr.get(i) instanceof Event) {
                strb.append("E | ");
            } else {
                // nothing happens because it shouldn't reach here.
            }
            if (taskarr.get(i).isDone) {
                strb.append("1 | ");
            } else {
                strb.append("0 | ");
            }
            strb.append(this.getTask(i));
            strb.append('\n');
        }
        return strb.toString();
    }
}
