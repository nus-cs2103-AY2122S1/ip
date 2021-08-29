package duke.task;


import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    protected ArrayList<Task> getList() {
        return (ArrayList<Task>) tasks.subList(0, tasks.size());
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public TaskList findByDate(LocalDate date) {


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

    public TaskList findByDescription(String input) {

        TaskList foundTasks = new TaskList();

        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.tasks.get(i);


            if (task.description.indexOf(input) != -1) {
                foundTasks.add(task);
            }
            ;
        }

        return foundTasks;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof TaskList) {
            TaskList taskList = (TaskList) o;

            return this.tasks.equals(taskList.tasks);
        }
        return false;


    }

    @Override
    public String toString() {
        return this.tasks.toString();
    }

}
