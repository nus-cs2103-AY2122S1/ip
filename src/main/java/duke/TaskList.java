package duke;

import duke.task.Task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskList {
    
    private ArrayList<duke.task.Task> taskList;
    
    public TaskList(ArrayList<duke.task.Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        taskList = new ArrayList<>();
    }
    
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String entry = String.format("%d. %s\n",
                    i+1, task.toString());
            result.append(entry);
        }
        return result.toString();
    }
    
    public int size() {
        return taskList.size();
    }
    
    public String listSchedule(LocalDate dateFilter) {
        ArrayList<Task> filteredTaskList = taskList.stream()
                .filter(Task::hasSchedule).collect(Collectors.toCollection(ArrayList::new));
        StringBuilder result = new StringBuilder("");
        for (int i = 0; i < filteredTaskList.size(); i++) {
            Task task = filteredTaskList.get(i);
            String entry = String.format("%d. %s \n",
                    i+1, task.toString());
            result.append(entry);
        }
        return result.toString();

    }
    
    public void doneTask(int index) throws DukeException {
        Task holder = taskList.get(index);
        holder.doneTask();
    }
    
    public void deleteTask(int index) throws DukeException {
        String holder = taskList.get(index).toString();
        taskList.remove(index);
        taskList.trimToSize();
        System.out.println("____________________________________________________________\n"
                + "okie! I've removed this annoying duke.task: \n"
                + holder
                + "\nNow you have " + taskList.size() + " tasks in the list.\n"
                + "____________________________________________________________\n");
    }
    
    public void addTask(Task task) {
        taskList.add(task);
    }
    
    public Task getTask(int index) {
        return taskList.get(index);
    }
    
}
