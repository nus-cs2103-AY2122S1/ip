package duke.task;

// import duke packages
import duke.DukeException;
import duke.util.Parser;

// import java packages
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public int getLength() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void markDone(int i) throws DukeException {
        tasks.get(i).markDone();
    }

    public void add(Task t) throws DukeException {
        tasks.add(t);
    }

    public void remove(int i) throws DukeException {
        tasks.remove(i);
    }

    /**
     * Searches for tasks that matches the category.
     *
     * @param cat Category to search for.
     * @return ArrayList of matching tasks.
     */
    public ArrayList<Task> findCat(char cat) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task i: tasks) {
            if (i.getCat() == cat) {
                results.add(i);
            }
        }
        return results;
    }

    /**
     * Searches for tasks that matches the search query.
     *
     * @param query Keyword to search for.
     * @return ArrayList of matching tasks.
     */
    public ArrayList<Task> find(String query) {
        if (query.equals("todo")) {
            return findCat('T');
        } else if (query.equals("deadline")) {
            return findCat('D');
        } else if (query.equals("event")) {
            return findCat('E');
        } else {
            ArrayList<Task> results = new ArrayList<>();

            for (Task i : tasks) {
                String s = i.getName() + " " + i.getDesc();
                if (s.matches("^.*" + query + ".*")) {
                    results.add(i);
                }
            }
            return results;
        }
    }
}
