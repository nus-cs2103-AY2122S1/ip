package duke.stubs;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;
import duke.testinginterface.TaskListInterface;


/**
 * Stub for TaskList class.
 */
public class TaskListStub implements TaskListInterface {
    private ArrayList<Task> tasks;

    /**
     * Constructor.
     *
     * Has stubs for Event, Deadline and Todo classes as its contents.
     */
    public TaskListStub() {
        this.tasks = new ArrayList<>();
        this.tasks.add(new TodoStub());
        this.tasks.add(new DeadlineStub());
        this.tasks.add(new EventStub());
    }

    @Override
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    @Override
    public void addTask(Task task) {
    }

    @Override
    public void deleteTask(int pos) {
    }

    @Override
    public ArrayList<Task> checkDate(LocalDate date) {

        if (date.equals(LocalDate.parse("2021-12-04"))) {
            ArrayList<Task> arrayList = new ArrayList<>();
            arrayList.add(new DeadlineStub());
            return arrayList;
        }
        return null;
    }
}
