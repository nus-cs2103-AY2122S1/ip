import java.time.LocalDate;
import java.util.ArrayList;

class TaskList {

    private ArrayList<Task> tasks;

    protected TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    protected TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    protected void add(Task task) {
        this.tasks.add(task);
    }

    protected void remove(int index) {
        this.tasks.remove(index);
    }

    protected ArrayList<Task> getList() {
        return (ArrayList<Task>) tasks.subList(0, tasks.size());
    }

    protected int size() {
        return this.tasks.size();
    }

    protected Task get(int index) {
        return this.tasks.get(index);
    }

    protected TaskList findByDate(LocalDate date) {


        TaskList foundTasks = new TaskList();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);

            if (task instanceof Event) {
                Event event = (Event) task;
                if (event.startDateTime.toLocalDate().compareTo(date) <= 0 && event.endDateTime.toLocalDate()
                        .compareTo(date) >= 0) {
                    foundTasks.add(task);
                }

            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.date.toLocalDate().equals(date)) {
                    foundTasks.add(task);
                }
            }
        }


        return foundTasks;
    }

}
