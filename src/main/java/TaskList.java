import java.io.IOException;
import java.util.ArrayList;

/**
 * This is a TaskList class that contains the task list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructors of TaskList
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addToList(Task t) {
        tasks.add(t);
    }

    public void printTasks(Ui ui) throws EmptyListException {
        if (this.tasks.size() <= 0) {
            throw new EmptyListException();
        }
        ui.printList(this.tasks);
    }

    public void safeTasks(Storage store) throws DukeFileException {
        try {
            store.safeFile(this.tasks);
        } catch (IOException e) {
            throw new DukeFileException();
        }
    }

    public void markTask(int index, Storage store, Ui ui)
                    throws TaskIsCompleteException, DukeFileException {
        try {
            Task t = tasks.get(index);
            if (t.isDone) {
                throw new TaskIsCompleteException(index + 1);
            } else {
                int listInd = index + 1;
                store.appendCommand("done " + listInd);
                t.markAsDone();
                ui.printMarkTaskDone(t);
            }
        } catch (IOException e) {
            throw new DukeFileException();
        }
    }

    public void deleteTask(int index, Storage store, Ui ui)
                    throws DukeFileException {
        try {
            Task t = tasks.get(index);
            int indOnList = index + 1;
            store.appendCommand("delete " + indOnList);
            tasks.remove(index);
            ui.printRemoveTask(t, tasks.size());
        } catch (IOException e) {
            throw new DukeFileException();
        }
    }

    public int getSize() {
        return tasks.size();
    }

}
